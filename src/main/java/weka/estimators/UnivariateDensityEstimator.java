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
 *    UnivariateDensityEstimator.java
 *    Copyright (C) 2009-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.estimators;

import weka.core.RevisionHandler;

/**
 * Interface that can be implemented by simple weighted univariate
 * density estimators.
 *
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @version $Revision: 11318 $
 */
public interface UnivariateDensityEstimator extends RevisionHandler {

  /**
   * Adds a value to the density estimator.
   *
   * @param value the value to add
   * @param weight the weight of the value
   */
  void addValue(double value, double weight);

  /**
   * Returns the natural logarithm of the density estimate at the given
   * point.
   *
   * @param value the value at which to evaluate
   * @return the natural logarithm of the density estimate at the given
   * value
   */
  double logDensity(double value);
}