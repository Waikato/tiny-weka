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
 *    UnivariateIntervalEstimator.java
 *    Copyright (C) 2009-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.estimators;

/**
 * Interface that can be implemented by simple weighted univariate
 * interval estimators.
 *
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public interface UnivariateIntervalEstimator {

  /**
   * Adds a value to the interval estimator.
   *
   * @param value the value to add
   * @param weight the weight of the value
   */
  void addValue(double value, double weight);

  /**
   * Returns the intervals at the given confidence value. Each row has
   * one interval. The first element in each row is the lower bound,
   * the second element the upper one.
   *
   * @param confidenceValue the value at which to evaluate
   * @return the interval
   */
  double[][] predictIntervals(double confidenceValue);
}