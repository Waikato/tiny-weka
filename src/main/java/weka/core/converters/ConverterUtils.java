/*
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *    ConverterUtils.java
 *    Copyright (C) 2000-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.converters;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.RevisionHandler;
import weka.core.RevisionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;

/**
 * Utility routines for the converter package.
 * 
 * @author Mark Hall (mhall@cs.waikato.ac.nz)
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 14285 $
 * @see Serializable
 */
public class ConverterUtils implements Serializable, RevisionHandler {

  /** for serialization. */
  static final long serialVersionUID = -2460855349276148760L;

  /**
   * Helper class for loading data from files and URLs. Via the ConverterUtils
   * class it determines which converter to use for loading the data into
   * memory. If the chosen converter is an incremental one, then the data will
   * be loaded incrementally, otherwise as batch. In both cases the same
   * interface will be used (<code>hasMoreElements</code>,
   * <code>nextElement</code>). Before the data can be read again, one has to
   * call the <code>reset</code> method. The data source can also be initialized
   * with an Instances object, in order to provide a unified interface to files
   * and already loaded datasets.
   * 
   * @author FracPete (fracpete at waikato dot ac dot nz)
   * @version $Revision: 14285 $
   * @see #hasMoreElements(Instances)
   * @see #nextElement(Instances)
   * @see #reset()
   * @see DataSink
   */
  public static class DataSource implements Serializable, RevisionHandler {

    /** for serialization. */
    private static final long serialVersionUID = -613122395928757332L;

    /** the file to load. */
    protected File m_File;

    /** the URL to load. */
    protected URL m_URL;

    /** the loader. */
    protected Loader m_Loader;

    /** whether the loader is incremental. */
    protected boolean m_Incremental;

    /** the instance counter for the batch case. */
    protected int m_BatchCounter;

    /** the last internally read instance. */
    protected Instance m_IncrementalBuffer;

    /** the batch buffer. */
    protected Instances m_BatchBuffer;

    /**
     * Tries to load the data from the file. Can be either a regular file or a
     * web location (http://, https://, ftp:// or file://).
     * 
     * @param location the name of the file to load
     * @throws Exception if initialization fails
     */
    public DataSource(String location) throws Exception {
      super();

      // file or URL?
      if (location.startsWith("http://") || location.startsWith("https://")
        || location.startsWith("ftp://") || location.startsWith("file://")) {
        m_URL = new URL(location);
      } else {
        m_File = new File(location);
      }

      // quick check: is it ARFF?
      if (isArff(location)) {
        m_Loader = new ArffLoader();
      } else {
        throw new IOException("For non-ARFF files, you need to specify the loader!");
      }

      // incremental loader?
      m_Incremental = (m_Loader instanceof IncrementalConverter);

      reset();
    }

    /**
     * Initializes the datasource with the given dataset.
     * 
     * @param inst the dataset to use
     */
    public DataSource(Instances inst) {
      super();

      m_BatchBuffer = inst;
      m_Loader = null;
      m_File = null;
      m_URL = null;
      m_Incremental = false;
    }

    /**
     * Initializes the datasource with the given Loader.
     * 
     * @param loader the Loader to use
     */
    public DataSource(Loader loader) {
      super();

      m_BatchBuffer = null;
      m_Loader = loader;
      m_File = null;
      m_URL = null;
      m_Incremental = (m_Loader instanceof IncrementalConverter);

      initBatchBuffer();
    }

    /**
     * Initializes the datasource with the given input stream. This stream is
     * always interpreted as ARFF.
     * 
     * @param stream the stream to use
     */
    public DataSource(InputStream stream) {
      super();

      m_BatchBuffer = null;
      m_Loader = new ArffLoader();
      try {
        m_Loader.setSource(stream);
      } catch (Exception e) {
        m_Loader = null;
      }
      m_File = null;
      m_URL = null;
      m_Incremental = (m_Loader instanceof IncrementalConverter);

      initBatchBuffer();
    }

    /**
     * initializes the batch buffer if necessary, i.e., for non-incremental
     * loaders.
     */
    protected void initBatchBuffer() {
      try {
        if (!isIncremental()) {
          m_BatchBuffer = m_Loader.getDataSet();
        } else {
          m_BatchBuffer = null;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    /**
     * returns whether the extension of the location is likely to be of ARFF
     * format, i.e., ending in ".arff" or ".arff.gz" (case-insensitive).
     * 
     * @param location the file location to check
     * @return true if the location seems to be of ARFF format
     */
    public static boolean isArff(String location) {
      if (location.toLowerCase().endsWith(
        ArffLoader.FILE_EXTENSION.toLowerCase())
        || location.toLowerCase().endsWith(
          ArffLoader.FILE_EXTENSION_COMPRESSED.toLowerCase())) {
        return true;
      } else {
        return false;
      }
    }

    /**
     * returns whether the loader is an incremental one.
     * 
     * @return true if the loader is a true incremental one
     */
    public boolean isIncremental() {
      return m_Incremental;
    }

    /**
     * returns the determined loader, null if the DataSource was initialized
     * with data alone and not a file/URL.
     * 
     * @return the loader used for retrieving the data
     */
    public Loader getLoader() {
      return m_Loader;
    }

    /**
     * returns the full dataset, can be null in case of an error.
     * 
     * @return the full dataset
     * @throws Exception if resetting of loader fails
     */
    public Instances getDataSet() throws Exception {
      Instances result;

      result = null;

      // reset the loader
      reset();

      try {
        if (m_BatchBuffer == null) {
          result = m_Loader.getDataSet();
        } else {
          result = m_BatchBuffer;
        }
      } catch (Exception e) {
        e.printStackTrace();
        result = null;
      }

      return result;
    }

    /**
     * returns the full dataset with the specified class index set, can be null
     * in case of an error.
     * 
     * @param classIndex the class index for the dataset
     * @return the full dataset
     * @throws Exception if resetting of loader fails
     */
    public Instances getDataSet(int classIndex) throws Exception {
      Instances result;

      result = getDataSet();
      if (result != null) {
        result.setClassIndex(classIndex);
      }

      return result;
    }

    /**
     * resets the loader.
     * 
     * @throws Exception if resetting fails
     */
    public void reset() throws Exception {
      if (m_File != null) {
        ((AbstractFileLoader) m_Loader).setFile(m_File);
      } else if (m_URL != null) {
        ((URLSourcedLoader) m_Loader).setURL(m_URL.toString());
      } else if (m_Loader != null) {
        m_Loader.reset();
      }

      m_BatchCounter = 0;
      m_IncrementalBuffer = null;

      if (m_Loader != null) {
        if (!isIncremental()) {
          m_BatchBuffer = m_Loader.getDataSet();
        } else {
          m_BatchBuffer = null;
        }
      }
    }

    /**
     * returns the structure of the data.
     * 
     * @return the structure of the data
     * @throws Exception if something goes wrong
     */
    public Instances getStructure() throws Exception {
      if (m_BatchBuffer == null) {
        return m_Loader.getStructure();
      } else {
        return new Instances(m_BatchBuffer, 0);
      }
    }

    /**
     * returns the structure of the data, with the defined class index.
     * 
     * @param classIndex the class index for the dataset
     * @return the structure of the data
     * @throws Exception if something goes wrong
     */
    public Instances getStructure(int classIndex) throws Exception {
      Instances result;

      result = getStructure();
      if (result != null) {
        result.setClassIndex(classIndex);
      }

      return result;
    }

    /**
     * returns whether there are more Instance objects in the data.
     * 
     * @param structure the structure of the dataset
     * @return true if there are more Instance objects available
     * @see #nextElement(Instances)
     */
    public boolean hasMoreElements(Instances structure) {
      boolean result;

      result = false;

      if (isIncremental()) {
        // user still hasn't collected the last one?
        if (m_IncrementalBuffer != null) {
          result = true;
        } else {
          try {
            m_IncrementalBuffer = m_Loader.getNextInstance(structure);
            result = (m_IncrementalBuffer != null);
          } catch (Exception e) {
            e.printStackTrace();
            result = false;
          }
        }
      } else {
        result = (m_BatchCounter < m_BatchBuffer.numInstances());
      }

      return result;
    }

    /**
     * returns the next element and sets the specified dataset, null if none
     * available.
     * 
     * @param dataset the dataset to set for the instance
     * @return the next Instance
     */
    public Instance nextElement(Instances dataset) {
      Instance result;

      result = null;

      if (isIncremental()) {
        // is there still an instance in the buffer?
        if (m_IncrementalBuffer != null) {
          result = m_IncrementalBuffer;
          m_IncrementalBuffer = null;
        } else {
          try {
            result = m_Loader.getNextInstance(dataset);
          } catch (Exception e) {
            e.printStackTrace();
            result = null;
          }
        }
      } else {
        if (m_BatchCounter < m_BatchBuffer.numInstances()) {
          result = m_BatchBuffer.instance(m_BatchCounter);
          m_BatchCounter++;
        }
      }

      if (result != null) {
        result.setDataset(dataset);
      }

      return result;
    }

    /**
     * convencience method for loading a dataset in batch mode.
     * 
     * @param location the dataset to load
     * @return the dataset
     * @throws Exception if loading fails
     */
    public static Instances read(String location) throws Exception {
      DataSource source;
      Instances result;

      source = new DataSource(location);
      result = source.getDataSet();

      return result;
    }

    /**
     * convencience method for loading a dataset in batch mode from a stream.
     * 
     * @param stream the stream to load the dataset from
     * @return the dataset
     * @throws Exception if loading fails
     */
    public static Instances read(InputStream stream) throws Exception {
      DataSource source;
      Instances result;

      source = new DataSource(stream);
      result = source.getDataSet();

      return result;
    }

    /**
     * convencience method for loading a dataset in batch mode.
     * 
     * @param loader the loader to get the dataset from
     * @return the dataset
     * @throws Exception if loading fails
     */
    public static Instances read(Loader loader) throws Exception {
      DataSource source;
      Instances result;

      source = new DataSource(loader);
      result = source.getDataSet();

      return result;
    }

    /**
     * for testing only - takes a data file as input.
     * 
     * @param args the commandline arguments
     * @throws Exception if something goes wrong
     */
    public static void main(String[] args) throws Exception {
      if (args.length != 1) {
        System.out.println("\nUsage: " + DataSource.class.getName()
          + " <file>\n");
        System.exit(1);
      }

      DataSource loader = new DataSource(args[0]);

      System.out.println("Incremental? " + loader.isIncremental());
      System.out.println("Loader: " + loader.getLoader().getClass().getName());
      System.out.println("Data:\n");
      Instances structure = loader.getStructure();
      System.out.println(structure);
      while (loader.hasMoreElements(structure)) {
        System.out.println(loader.nextElement(structure));
      }

      Instances inst = loader.getDataSet();
      loader = new DataSource(inst);
      System.out.println("\n\nProxy-Data:\n");
      System.out.println(loader.getStructure());
      while (loader.hasMoreElements(structure)) {
        System.out.println(loader.nextElement(inst));
      }
    }

    /**
     * Returns the revision string.
     * 
     * @return the revision
     */
    @Override
    public String getRevision() {
      return RevisionUtils.extract("$Revision: 14285 $");
    }
  }

  /**
   * Helper class for saving data to files. Via the ConverterUtils class it
   * determines which converter to use for saving the data. It is the logical
   * counterpart to <code>DataSource</code>.
   * 
   * @author FracPete (fracpete at waikato dot ac dot nz)
   * @version $Revision: 14285 $
   * @see DataSource
   */
  public static class DataSink implements Serializable, RevisionHandler {

    /** for serialization. */
    private static final long serialVersionUID = -1504966891136411204L;

    /** the saver to use for storing the data. */
    protected Saver m_Saver = null;

    /** the stream to store the data in (always in ARFF format). */
    protected OutputStream m_Stream = null;

    /**
     * initializes the sink to save the data to the given file.
     * 
     * @param filename the file to save data to
     * @throws Exception if set of saver fails
     */
    public DataSink(String filename) throws Exception {
      m_Stream = null;

      if (DataSource.isArff(filename)) {
        m_Saver = new ArffSaver();
      } else {
        throw new IOException("For non-ARFF files a saver needs to be specified!");
      }

      ((AbstractFileSaver) m_Saver).setFile(new File(filename));
    }

    /**
     * initializes the sink to save the data to the given Saver (expected to be
     * fully configured).
     * 
     * @param saver the saver to use for saving the data
     */
    public DataSink(Saver saver) {
      m_Saver = saver;
      m_Stream = null;
    }

    /**
     * initializes the sink to save the data in the stream (always in ARFF
     * format).
     * 
     * @param stream the output stream to use for storing the data in ARFF
     *          format
     */
    public DataSink(OutputStream stream) {
      m_Saver = null;
      m_Stream = stream;
    }

    /**
     * writes the given data either via the saver or to the defined output
     * stream (depending on the constructor). In case of the stream, the stream
     * is only flushed, but not closed.
     * 
     * @param data the data to save
     * @throws Exception if saving fails
     */
    public void write(Instances data) throws Exception {
      if (m_Saver != null) {
        m_Saver.setInstances(data);
        m_Saver.writeBatch();
      } else {
        m_Stream.write(data.toString().getBytes());
        m_Stream.flush();
      }
    }

    /**
     * writes the data to the given file.
     * 
     * @param filename the file to write the data to
     * @param data the data to store
     * @throws Exception if writing fails
     */
    public static void write(String filename, Instances data) throws Exception {
      DataSink sink;

      sink = new DataSink(filename);
      sink.write(data);
    }

    /**
     * writes the data via the given saver.
     * 
     * @param saver the saver to use for writing the data
     * @param data the data to store
     * @throws Exception if writing fails
     */
    public static void write(Saver saver, Instances data) throws Exception {
      DataSink sink;

      sink = new DataSink(saver);
      sink.write(data);
    }

    /**
     * writes the data to the given stream (always in ARFF format).
     * 
     * @param stream the stream to write the data to (ARFF format)
     * @param data the data to store
     * @throws Exception if writing fails
     */
    public static void write(OutputStream stream, Instances data)
      throws Exception {
      DataSink sink;

      sink = new DataSink(stream);
      sink.write(data);
    }

    /**
     * for testing only - takes a data file as input and a data file for the
     * output.
     * 
     * @param args the commandline arguments
     * @throws Exception if something goes wrong
     */
    public static void main(String[] args) throws Exception {
      if (args.length != 2) {
        System.out.println("\nUsage: " + DataSource.class.getName()
          + " <input-file> <output-file>\n");
        System.exit(1);
      }

      // load data
      Instances data = DataSource.read(args[0]);

      // save data
      DataSink.write(args[1], data);
    }

    /**
     * Returns the revision string.
     * 
     * @return the revision
     */
    @Override
    public String getRevision() {
      return RevisionUtils.extract("$Revision: 14285 $");
    }
  }

  /**
   * Returns the revision string.
   * 
   * @return the revision
   */
  @Override
  public String getRevision() {
    return RevisionUtils.extract("$Revision: 14285 $");
  }
}
