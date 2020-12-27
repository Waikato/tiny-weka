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
 *    IncrementalEstimator.java
 *    Copyright (C) 2004-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.estimators;


/** 
 * Interface for an incremental probability estimators.<p>
 *
 * @author Gabi Schmidberger (gabi@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public interface IncrementalEstimator {

  /**
   * Add one value to the current estimator.
   *
   * @param data the new data value 
   * @param weight the weight assigned to the data value 
   */
  void addValue(double data, double weight);

}








