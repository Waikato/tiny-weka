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
 *    ConditionalDensityEstimator.java
 *    Copyright (C) 2009-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.classifiers;

import weka.core.Instance;

/**
 * Interface for numeric prediction schemes that can output conditional
 * density estimates.
 *
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public interface ConditionalDensityEstimator {

  /**
   * Returns natural logarithm of density estimate for given value based on given instance.
   *
   * @param instance the instance to make the prediction for.
   * @param value the value to make the prediction for.
   * @return the natural logarithm of the density estimate
   * @exception Exception if the density cannot be computed
   */
  public double logDensity(Instance instance, double value) throws Exception;
}

