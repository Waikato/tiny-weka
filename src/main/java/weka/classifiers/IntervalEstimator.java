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
 *    IntervalEstimator.java
 *    Copyright (C) 2005-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.classifiers;

import weka.core.Instance;

/**
 * Interface for numeric prediction schemes that can output prediction
 * intervals.
 *
 * @author Kurt Driessens (kurtd@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public interface IntervalEstimator {

  /**
   * Returns an N * 2 array, where N is the number of prediction
   * intervals. In each row, the first element contains the lower
   * boundary of the corresponding prediction interval and the second
   * element the upper boundary.
   *
   * @param inst the instance to make the prediction for.
   * @param confidenceLevel the percentage of cases that the interval should cover.
   * @return an array of prediction intervals
   * @exception Exception if the intervals can't be computed
   */
  double[][] predictIntervals(Instance inst, double confidenceLevel) throws Exception;
}

