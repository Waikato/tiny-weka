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
 * RevisionHandler.java
 * Copyright (C) 2008-2012 University of Waikato, Hamilton, New Zealand
 */

package weka.core;

/**
 * For classes that should return their source control revision.
 * 
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 8034 $
 * @see     weka.core.RevisionUtils
 */
public interface RevisionHandler {

  /**
   * Returns the revision string.
   * 
   * @return		the revision
   */
  public String getRevision();
}
