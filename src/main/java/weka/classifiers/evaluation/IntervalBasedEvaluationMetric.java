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
 *    IntervalBasedEvaluationMetric.java
 *    Copyright (C) 2011-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.classifiers.evaluation;

import weka.classifiers.IntervalEstimator;
import weka.core.Instance;

/**
 * Primarily a marker interface for interval-based evaluation metrics to
 * implement. Allows the command line interface to display these metrics or not
 * based on user-supplied options
 * 
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: 9320 $
 */
public interface IntervalBasedEvaluationMetric {

  /**
   * Updates stats for interval estimator based on current test instance.
   * Implementers need only implement this method if it is not possible to
   * compute their statistics from what is stored in the base Evaluation object.
   * 
   * @param classifier the interval estimator
   * @param classMissing the instance for which the intervals are computed,
   *          without a class value
   * @param classValue the class value of this instance
   * @throws Exception if intervals could not be computed successfully
   */
  void updateStatsForIntervalEstimator(IntervalEstimator classifier,
      Instance classMissing, double classValue) throws Exception;

  /**
   * Return a formatted string (suitable for displaying in console or GUI
   * output) containing all the statistics that this metric computes.
   * 
   * @return a formatted string containing all the computed statistics
   */
  String toSummaryString();
}
