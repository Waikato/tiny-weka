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
 *    ThresholdProducingMetric.java
 *    Copyright (C) 2014 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.classifiers.evaluation;

/**
 * Some evaluation measures may optimize thresholds on the
 * class probabilities. These measures should implement this interface.
 *
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public interface ThresholdProducingMetric {

  /**
   * Returns the threshold values, one for each class value,  associated with the value
   * of the measure that is returned.
   * 
   * @return thresholds
   */
  public double[] getThresholds();
}