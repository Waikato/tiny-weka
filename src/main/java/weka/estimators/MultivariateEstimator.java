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
 *    MultivariateEstimator.java
 *    Copyright (C) 2013 University of Waikato
 */

package weka.estimators;

/**
 * Interface to Multivariate Distribution Estimation
 * 
 * @author Uday Kamath, PhD candidate George Mason University
 * @version $Revision: 12743 $
 */
public interface MultivariateEstimator {

  /**
   * Fits the value to the density estimator.
   * 
   * @param value the value to add
   * @param weight the weight of the value
   */
  void estimate(double[][] value, double[] weight);

  /**
   * Returns the natural logarithm of the density estimate at the given point.
   * 
   * @param value the value at which to evaluate
   * @return the natural logarithm of the density estimate at the given value
   */
  double logDensity(double[] value);

}
