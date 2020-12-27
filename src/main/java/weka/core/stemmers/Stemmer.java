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
 * Stemmer.java
 * Copyright (C) 2005-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.stemmers;

import java.io.Serializable;

import weka.core.RevisionHandler;

/**
 * Interface for all stemming algorithms.
 *
 * @author    FracPete (fracpete at waikato dot ac dot nz)
 * @version   $Revision: 8034 $
 */
public interface Stemmer 
  extends Serializable, RevisionHandler {

  /**
   * Stems the given word and returns the stemmed version
   *
   * @param word      the unstemmed word
   * @return          the stemmed word
   */
  public String stem(String word);
}
