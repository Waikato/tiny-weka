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
 * StopwordsHandler.java
 * Copyright (C) 2014 University of Waikato, Hamilton, New Zealand
 */

package weka.core.stopwords;

/**
 * Interface for classes that support stopword handling.
 *
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 10978 $
 */
public interface StopwordsHandler {

  /**
   * Returns true if the given string is a stop word.
   *
   * @param word the word to test
   * @return true if the word is a stopword
   */
  public boolean isStopword(String word);
}
