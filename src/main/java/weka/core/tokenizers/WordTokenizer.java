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
 * SimpleStringTokenizer.java
 * Copyright (C) 2007-2012 University of Waikato, Hamilton, New Zealand
 */

package weka.core.tokenizers;

import java.util.StringTokenizer;

import weka.core.RevisionUtils;

/**
 * <!-- globalinfo-start --> A simple tokenizer that is using the
 * java.util.StringTokenizer class to tokenize the strings.
 * <p/>
 * <!-- globalinfo-end -->
 * 
 * <!-- options-start --> Valid options are:
 * <p/>
 * 
 * <pre>
 * -delimiters &lt;value&gt;
 *  The delimiters to use
 *  (default ' \r\n\t.,;:'"()?!').
 * </pre>
 * 
 * <!-- options-end -->
 * 
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 10203 $
 */
public class WordTokenizer extends CharacterDelimitedTokenizer {

  /** for serialization */
  private static final long serialVersionUID = -930893034037880773L;

  /** the actual tokenizer */
  protected transient StringTokenizer m_Tokenizer;

  /**
   * Returns a string describing the stemmer
   * 
   * @return a description suitable for displaying in the explorer/experimenter
   *         gui
   */
  @Override
  public String globalInfo() {
    return "A simple tokenizer that is using the java.util.StringTokenizer "
      + "class to tokenize the strings.";
  }

  /**
   * Tests if this enumeration contains more elements.
   * 
   * @return true if and only if this enumeration object contains at least one
   *         more element to provide; false otherwise.
   */
  @Override
  public boolean hasMoreElements() {
    return m_Tokenizer.hasMoreElements();
  }

  /**
   * Returns the next element of this enumeration if this enumeration object has
   * at least one more element to provide.
   * 
   * @return the next element of this enumeration.
   */
  @Override
  public String nextElement() {
    return m_Tokenizer.nextToken();
  }

  /**
   * Sets the string to tokenize. Tokenization happens immediately.
   * 
   * @param s the string to tokenize
   */
  @Override
  public void tokenize(String s) {
    m_Tokenizer = new StringTokenizer(s, getDelimiters());
  }

  /**
   * Returns the revision string.
   * 
   * @return the revision
   */
  @Override
  public String getRevision() {
    return RevisionUtils.extract("$Revision: 10203 $");
  }

  /**
   * Runs the tokenizer with the given options and strings to tokenize. The
   * tokens are printed to stdout.
   * 
   * @param args the commandline options and strings to tokenize
   */
  public static void main(String[] args) {
    runTokenizer(new WordTokenizer(), args);
  }
}
