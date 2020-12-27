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
 *    AbstractLoader.java
 *    Copyright (C) 2002-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.converters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import weka.core.CommandlineRunnable;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Abstract class gives default implementation of setSource methods. All other
 * methods must be overridden.
 * 
 * @author Richard Kirkby (rkirkby@cs.waikato.ac.nz)
 * @version $Revision: 12184 $
 */
public abstract class AbstractLoader implements Loader {

  /** ID to avoid warning */
  private static final long serialVersionUID = 2425432084900694551L;
  /** The current retrieval mode */
  protected int m_retrieval;

  /**
   * Sets the retrieval mode.
   * 
   * @param mode the retrieval mode
   */
  @Override
  public void setRetrieval(int mode) {

    m_retrieval = mode;
  }

  /**
   * Gets the retrieval mode.
   * 
   * @return the retrieval mode
   */
  protected int getRetrieval() {

    return m_retrieval;
  }

  /**
   * Default implementation throws an IOException.
   * 
   * @param file the File
   * @exception IOException always
   */
  @Override
  public void setSource(File file) throws IOException {

    throw new IOException("Setting File as source not supported");
  }

  /**
   * Default implementation sets retrieval mode to NONE
   * 
   * @exception never.
   */
  @Override
  public void reset() throws Exception {
    m_retrieval = NONE;
  }

  /**
   * Default implementation throws an IOException.
   * 
   * @param input the input stream
   * @exception IOException always
   */
  @Override
  public void setSource(InputStream input) throws IOException {

    throw new IOException("Setting InputStream as source not supported");
  }

  /*
   * To be overridden.
   */
  @Override
  public abstract Instances getStructure() throws IOException;

  /*
   * To be overridden.
   */
  @Override
  public abstract Instances getDataSet() throws IOException;

  /*
   * To be overridden.
   */
  @Override
  public abstract Instance getNextInstance(Instances structure)
    throws IOException;
}
