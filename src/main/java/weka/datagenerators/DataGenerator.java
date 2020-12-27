/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * DataGenerator.java
 * Copyright (C) 2005-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.datagenerators;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.Option;
import weka.core.OptionHandler;
import weka.core.Randomizable;
import weka.core.RevisionHandler;
import weka.core.Utils;

/**
 * Abstract superclass for data generators that generate data for classifiers
 * and clusterers.
 * 
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 15437 $
 */
public abstract class DataGenerator implements OptionHandler, Randomizable,
  Serializable, RevisionHandler {

  /** for serialization */
  private static final long serialVersionUID = -3698585946221802578L;

  /** Debugging mode */
  protected boolean m_Debug = false;

  /** The format for the generated dataset */
  protected Instances m_DatasetFormat = null;

  /** Relation name specified by the user (relation name will be auto-generated if empty) */
  protected String m_RelationName = "";

  /**
   * Number of instances that should be produced into the dataset this number is
   * by default m_NumExamples, but can be reset by the generator
   */
  protected int m_NumExamplesAct;

  /** default output (stdout) */
  protected transient PrintWriter m_DefaultOutput = new PrintWriter(
    new java.io.OutputStreamWriter(System.out));

  /** PrintWriter for outputting the generated data */
  protected transient PrintWriter m_Output = m_DefaultOutput;

  /** random number generator seed */
  protected int m_Seed;

  /** random number generator */
  protected Random m_Random = null;

  /** This flag is no longer used (left here to maintain compatibility for serialization) */
  protected boolean m_CreatingRelationName = false;

  /**
   * a black list for options not to be listed (for derived generators) in the
   * makeOptionString method
   * 
   * @see #makeOptionString(DataGenerator)
   */
  protected static HashSet<String> m_OptionBlacklist;
  static {
    m_OptionBlacklist = new HashSet<String>();
  }

  /**
   * initializes with default settings. <br/>
   * Note: default values are set via a default&lt;name&gt; method. These
   * default methods are also used in the listOptions method and in the
   * setOptions method. Why? Derived generators can override the return value of
   * these default methods, to avoid exceptions.
   */
  public DataGenerator() {
    clearBlacklist();

    setNumExamplesAct(defaultNumExamplesAct());
    setSeed(defaultSeed());
  }

  /**
   * Returns an enumeration describing the available options.
   * 
   * @return an enumeration of all the available options
   */
  @Override
  public Enumeration<Option> listOptions() {

    Vector<Option> result = new Vector<Option>();

    result.addElement(new Option("\tPrints this help.", "h", 1, "-h"));

    result.addElement(new Option(
      "\tThe name of the output file, otherwise the generated data is\n"
        + "\tprinted to stdout.", "o", 1, "-o <file>"));

    result.addElement(new Option("\tThe name of the relation.", "r", 1,
      "-r <name>"));

    result.addElement(new Option("\tWhether to print debug informations.", "d",
      0, "-d"));

    result.addElement(new Option("\tThe seed for random function (default "
      + defaultSeed() + ")", "S", 1, "-S"));

    return result.elements();
  }

  /**
   * Convenience method. Turns the given enumeration of options into a vector.
   */
  public Vector<Option> enumToVector(Enumeration<Option> enu) {

    Vector<Option> options = new Vector<Option>();
    options.addAll(Collections.list(enu));
    return options;
  }

  /**
   * Parses a list of options for this object.
   * <p/>
   * 
   * For list of valid options see class description.
   * <p/>
   * 
   * @param options the list of options as an array of strings
   * @throws Exception if an option is not supported
   */
  @Override
  public void setOptions(String[] options) throws Exception {
    String tmpStr;

    // remove unwanted options
    options = removeBlacklist(options);

    tmpStr = Utils.getOption('r', options);
    if (tmpStr.length() != 0) {
      setRelationName(Utils.unquote(tmpStr));
    } else {
      setRelationName("");
    }

    tmpStr = Utils.getOption('o', options);
    if (tmpStr.length() != 0) {
      setOutput(new PrintWriter(new FileOutputStream(tmpStr)));
    } else if (getOutput() == null) {
      throw new Exception("No Output defined!");
    }

    setDebug(Utils.getFlag('d', options));

    tmpStr = Utils.getOption('S', options);
    if (tmpStr.length() != 0) {
      setSeed(Integer.parseInt(tmpStr));
    } else {
      setSeed(defaultSeed());
    }
  }

  /**
   * Gets the current settings of the datagenerator RDG1. Removing of
   * blacklisted options has to be done in the derived class, that defines the
   * blacklist-entry.
   * 
   * @return an array of strings suitable for passing to setOptions
   * @see #removeBlacklist(String[])
   */
  @Override
  public String[] getOptions() {
    Vector<String> result = new Vector<String>();

    if (getRelationName().length() > 0) {
      result.add("-r");
      result.add(Utils.quote(getRelationName()));
    }

    if (getDebug()) {
      result.add("-d");
    }

    result.add("-S");
    result.add("" + getSeed());

    return result.toArray(new String[result.size()]);
  }

  /**
   * Constructs the Instances object representing the format of the generated data.
   *
   * This default implementation simply returns the Instances object that holds the dataset format
   * currently stored in m_DatasetFormat.
   * 
   * @return the format for the dataset
   * @throws Exception if the generating of the format failed
   * @see #defaultRelationName()
   */
  public Instances defineDataFormat() throws Exception {

    return m_DatasetFormat;
  }

  /**
   * Generates one example of the dataset.
   * 
   * @return the generated example
   * @throws Exception if the format of the dataset is not yet defined
   * @throws Exception if the generator only works with generateExamples which
   *           means in non single mode
   */
  public abstract Instance generateExample() throws Exception;

  /**
   * Generates all examples of the dataset.
   * 
   * @return the generated dataset
   * @throws Exception if the format of the dataset is not yet defined
   * @throws Exception if the generator only works with generateExample, which
   *           means in single mode
   */
  public abstract Instances generateExamples() throws Exception;

  /**
   * Generates a comment string that documentates the data generator. By default
   * this string is added at the beginning of the produced output as ARFF file
   * type, next after the options.
   * 
   * @return string contains info about the generated rules
   * @throws Exception if the generating of the documentation fails
   */
  public abstract String generateStart() throws Exception;

  /**
   * Generates a comment string that documentates the data generator. By default
   * this string is added at the end of the produced output as ARFF file type.
   * 
   * @return string contains info about the generated rules
   * @throws Exception if the generating of the documentation fails
   */
  public abstract String generateFinished() throws Exception;

  /**
   * Return if single mode is set for the given data generator mode depends on
   * option setting and or generator type.
   * 
   * @return single mode flag
   * @throws Exception if mode is not set yet
   */
  public abstract boolean getSingleModeFlag() throws Exception;

  /**
   * Sets the debug flag.
   * 
   * @param debug the new debug flag
   */
  public void setDebug(boolean debug) {
    m_Debug = debug;
  }

  /**
   * Gets the debug flag.
   * 
   * @return the debug flag
   */
  public boolean getDebug() {
    return m_Debug;
  }

  /**
   * Returns the tip text for this property
   * 
   * @return tip text for this property suitable for displaying in the
   *         explorer/experimenter gui
   */
  public String debugTipText() {
    return "Whether the generator is run in debug mode or not.";
  }

  /**
   * Sets the relation name the dataset should have.
   * 
   * @param relationName the new relation name
   */
  public void setRelationName(String relationName) {
    m_RelationName = relationName;
  }

  /**
   * returns a relation name based on the options
   * 
   * @return a relation name based on the options
   */
  protected String defaultRelationName() {
    StringBuffer result;
    String[] options;
    String option;
    int i;

    result = new StringBuffer(this.getClass().getName());

    options = getOptions();
    for (i = 0; i < options.length; i++) {
      option = options[i].trim();
      if (i > 0) {
        result.append("_");
      }
      result.append(option.replaceAll(" ", "_"));
    }

    return result.toString();
  }

  /**
   * returns the relation name to use, i.e., in case the currently set relation
   * name is empty, a generic one is returned. Must be used in
   * defineDataFormat()
   * 
   * @return the relation name
   * @see #defaultRelationName()
   * @see #defineDataFormat()
   */
  protected String getRelationNameToUse() {
    String result;

    result = getRelationName();
    if (result.length() == 0) {
      result = defaultRelationName();
    }

    return result;
  }

  /**
   * Gets the relation name the dataset should have.
   * 
   * @return the relation name the dataset should have
   */
  public String getRelationName() {
    return m_RelationName;
  }

  /**
   * Returns the tip text for this property
   * 
   * @return tip text for this property suitable for displaying in the
   *         explorer/experimenter gui
   */
  public String relationNameTipText() {
    return "The relation name of the generated data (if empty, a generic one will be supplied).";
  }

  /**
   * returns the default number of actual examples
   * 
   * @return the default number of actual examples
   */
  protected int defaultNumExamplesAct() {
    return 0;
  }

  /**
   * Sets the number of examples the dataset should have.
   * 
   * @param numExamplesAct the new number of examples
   */
  protected void setNumExamplesAct(int numExamplesAct) {
    m_NumExamplesAct = numExamplesAct;
  }

  /**
   * Gets the number of examples the dataset should have.
   * 
   * @return the number of examples the dataset should have
   */
  public int getNumExamplesAct() {
    return m_NumExamplesAct;
  }

  /**
   * Returns the tip text for this property
   * 
   * @return tip text for this property suitable for displaying in the
   *         explorer/experimenter gui
   */
  protected String numExamplesActTipText() {
    return "The actual number of examples to generate.";
  }

  /**
   * Sets the print writer.
   * 
   * @param newOutput the new print writer
   */
  public void setOutput(PrintWriter newOutput) {
    m_Output = newOutput;
    m_DefaultOutput = null;
  }

  /**
   * Gets the print writer.
   * 
   * @return print writer object
   */
  public PrintWriter getOutput() {
    return m_Output;
  }

  /**
   * Gets writer, which is used for outputting to stdout. A workaround for the
   * problem of closing stdout when closing the associated Printwriter.
   * 
   * @return writer object
   */
  public PrintWriter defaultOutput() {
    return m_DefaultOutput;
  }

  /**
   * Returns the tip text for this property
   * 
   * @return tip text for this property suitable for displaying in the
   *         explorer/experimenter gui
   */
  public String outputTipText() {
    return "The output writer to use for printing the generated data.";
  }

  /**
   * Sets the format of the dataset that is to be generated.
   * 
   * @param newFormat the new dataset format of the dataset
   */
  public void setDatasetFormat(Instances newFormat) {
    m_DatasetFormat = new Instances(newFormat, 0);
  }

  /**
   * Gets the format of the dataset that is to be generated.
   * 
   * @return the dataset format of the dataset
   */
  public Instances getDatasetFormat() {
    if (m_DatasetFormat != null) {
      return new Instances(m_DatasetFormat, 0);
    } else {
      return null;
    }
  }

  /**
   * Returns the tip text for this property
   * 
   * @return tip text for this property suitable for displaying in the
   *         explorer/experimenter gui
   */
  public String formatTipText() {
    return "The data format to use.";
  }

  /**
   * returns the default seed
   * 
   * @return the default seed
   */
  protected int defaultSeed() {
    return 1;
  }

  /**
   * Gets the random number seed.
   * 
   * @return the random number seed.
   */
  @Override
  public int getSeed() {
    return m_Seed;
  }

  /**
   * Sets the random number seed.
   * 
   * @param newSeed the new random number seed.
   */
  @Override
  public void setSeed(int newSeed) {
    m_Seed = newSeed;
    m_Random = new Random(newSeed);
  }

  /**
   * Returns the tip text for this property
   * 
   * @return tip text for this property suitable for displaying in the
   *         explorer/experimenter gui
   */
  public String seedTipText() {
    return "The seed value for the random number generator.";
  }

  /**
   * Gets the random generator.
   * 
   * @return the random generator
   */
  public Random getRandom() {
    if (m_Random == null) {
      m_Random = new Random(getSeed());
    }

    return m_Random;
  }

  /**
   * Sets the random generator.
   * 
   * @param newRandom is the random generator.
   */
  public void setRandom(Random newRandom) {
    m_Random = newRandom;
  }

  /**
   * Returns the tip text for this property
   * 
   * @return tip text for this property suitable for displaying in the
   *         explorer/experimenter gui
   */
  public String randomTipText() {
    return "The random number generator to use.";
  }

  /**
   * Returns a string representing the dataset in the instance queue.
   * 
   * @return the string representing the output data format
   */
  protected String toStringFormat() {
    if (m_DatasetFormat == null) {
      return "";
    }
    return m_DatasetFormat.toString();
  }

  /**
   * removes all entries from the options blacklist
   */
  protected static void clearBlacklist() {
    m_OptionBlacklist.clear();
  }

  /**
   * adds the given option, e.g., for "-V" use "V", to the blacklist of options
   * that are not to be output via the makeOptionString method
   * 
   * @param option the option to exclude from listing
   * @see #makeOptionString(DataGenerator)
   */
  protected static void addToBlacklist(String option) {
    m_OptionBlacklist.add(option);
  }

  /**
   * checks, whether the given option is in the blacklist of options not to be
   * output by makeOptionString
   * 
   * @param option the option to check
   * @return true if the option is on the blacklist
   * @see #makeOptionString(DataGenerator)
   */
  protected static boolean isOnBlacklist(String option) {
    return m_OptionBlacklist.contains(option);
  }

  /**
   * removes all the options from the options array that are blacklisted
   * 
   * @param options the options to remove from the blacklist
   * @return the processed options array
   */
  protected String[] removeBlacklist(String[] options) {

    Hashtable<String, Option> pool;
    Option option;

    // retrieve options that are on blacklist
    Enumeration<Option> enm = listOptions();
    pool = new Hashtable<String, Option>();
    while (enm.hasMoreElements()) {
      option = enm.nextElement();
      if (isOnBlacklist(option.name())) {
        pool.put(option.name(), option);
      }
    }

    // remove options
    Enumeration<String> enm2 = pool.keys();
    while (enm2.hasMoreElements()) {
      option = pool.get(enm2.nextElement());
      try {
        if (option.numArguments() == 0) {
          Utils.getFlag(option.name(), options);
        } else {
          Utils.getOption(option.name(), options);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return options;
  }

  /**
   * returns all the options in a string
   * 
   * @param generator the DataGenerator to return all the options for
   * @return the assembled option string
   */
  protected static String makeOptionString(DataGenerator generator) {
    StringBuffer result;
    Enumeration<Option> enm;
    Option option;

    result = new StringBuffer();
    result.append("\nData Generator options:\n\n");

    enm = generator.listOptions();
    while (enm.hasMoreElements()) {
      option = enm.nextElement();
      // skip option if on blacklist
      if (isOnBlacklist(option.name())) {
        continue;
      }
      result.append(option.synopsis() + "\n" + option.description() + "\n");
    }

    return result.toString();
  }

  /**
   * Gets the prologue string.
   *
   * @return prologue
   */
  public String getPrologue() throws Exception {

    StringBuilder sb = new StringBuilder();

    // output of options
    sb.append("%");
    sb.append("% Commandline");
    sb.append("%");
    sb.append("% " + getClass().getName() + " "  + Utils.joinOptions(getOptions()));
    sb.append("%");

    // comment at beginning of ARFF File
    String commentAtStart = generateStart();

    if (commentAtStart.length() > 0) {
      sb.append("%");
      sb.append("% Prologue");
      sb.append("%");
      sb.append(commentAtStart.trim());
      sb.append("%");
    }

    return sb.toString();
  }

  /**
   * Gets the epilogue string.
   *
   * @return epilogue
   */
  public String getEpilogue() throws Exception {

    StringBuilder sb = new StringBuilder();

    // comment at end of ARFF File
    String commentAtEnd = generateFinished();

    if (commentAtEnd.length() > 0) {
      sb.append("%");
      sb.append("% Epilogue");
      sb.append("%");
      sb.append(commentAtEnd.trim());
      sb.append("%");
    }

    return sb.toString();
  }

  /**
   * Calls the data generator.
   * 
   * @param generator one of the data generators
   * @param options options of the data generator
   * @throws Exception if there was an error in the option list
   */
  public static void makeData(DataGenerator generator, String[] options)
    throws Exception {

    // help?
    boolean printhelp = (Utils.getFlag('h', options));

    // read options
    if (!printhelp) {
      try {
        options = generator.removeBlacklist(options);
        generator.setOptions(options);

        // check for left-over options, but don't raise exception
        Vector<String> unknown = new Vector<String>();
        for (int i = 0; i < options.length; i++) {
          if (options[i].length() != 0) {
            unknown.add(options[i]);
          }
        }
        if (unknown.size() > 0) {
          System.out.print("Unknown options:");
          for (int i = 0; i < unknown.size(); i++) {
            System.out.print(" " + unknown.get(i));
          }
          System.out.println();
        }
      } catch (Exception e) {
        e.printStackTrace();
        printhelp = true;
      }
    }

    if (printhelp) {
      System.out.println(makeOptionString(generator));
      return;
    }

    // define dataset format
    // computes actual number of examples to be produced
    generator.setDatasetFormat(generator.defineDataFormat());

    // get print writer and print header
    PrintWriter output = generator.getOutput();
    output.println(generator.getPrologue());

    // ask data generator which mode
    boolean singleMode = generator.getSingleModeFlag();

    // start data producer
    if (singleMode) {
      // output of dataset header
      output.println(generator.toStringFormat());
      for (int i = 0; i < generator.getNumExamplesAct(); i++) {
        // over all examples to be produced
        output.println(generator.generateExample());
      }
    } else { // generator produces all instances at once
      Instances data = generator.generateExamples();
      // output of dataset
      for (int i = 0; i < data.numInstances(); i++) {
        if (i % 1000 == 0) {
          output.flush();
        }
        output.println(data.instance(i));
      }
      output.flush();
    }

    output.println(generator.getEpilogue());
    output.flush();

    if (generator.getOutput() != generator.defaultOutput()) {
      output.close();
    }
  }

  /**
   * runs the datagenerator instance with the given options.
   * 
   * @param datagenerator the datagenerator to run
   * @param options the commandline options
   */
  public static void runDataGenerator(DataGenerator datagenerator,
    String[] options) {
    try {
      DataGenerator.makeData(datagenerator, options);
    } catch (Exception e) {
      if ((e.getMessage() != null)
        && (e.getMessage().indexOf("Data Generator options") == -1)) {
        e.printStackTrace();
      } else {
        System.err.println(e.getMessage());
      }
    }
  }
}
