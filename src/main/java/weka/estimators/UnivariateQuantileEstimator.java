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
 *    UnivariateQuantileEstimator.java
 *    Copyright (C) 2009-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.estimators;

/**
 * Interface that can be implemented by simple weighted univariate
 * quantile estimators.
 *
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public interface UnivariateQuantileEstimator {

  /**
   * Adds a value to the interval estimator.
   *
   * @param value the value to add
   * @param weight the weight of the value
   */
  void addValue(double value, double weight);

  /**
   * Returns the quantile for the given percentage
   *
   * @param value the value at which to evaluate
   * @return the quantile
   */
  double predictQuantile(double quantile);
}