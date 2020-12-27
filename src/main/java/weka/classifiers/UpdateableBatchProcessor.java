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
 *    UpdateableBatchProcessor.java
 *    Copyright (C) 2014 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.classifiers;

/**
 * Updateable classifiers can implement this if they wish to be informed at the
 * end of the training stream. This could be useful for cleaning up temporary
 * data structures, pruning dictionaries etc.
 * 
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: 11061 $
 */
public interface UpdateableBatchProcessor {

  /**
   * Signal that the training data is finished (for now).
   * 
   * @throws Exception if a problem occurs
   */
  public void batchFinished() throws Exception;
}
