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
 *    StandardEvaluationMetric.java
 *    Copyright (C) 2011-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.classifiers.evaluation;

import weka.core.Instance;

/**
 * Primarily a marker interface for a "standard" evaluation metric - i.e. one
 * that would be part of the normal output in Weka without having to turn
 * specific display options.
 * 
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: 9320 $
 */
public interface StandardEvaluationMetric {

  /**
   * Return a formatted string (suitable for displaying in console or GUI
   * output) containing all the statistics that this metric computes.
   * 
   * @return a formatted string containing all the computed statistics
   */
  String toSummaryString();

  /**
   * Updates the statistics about a classifiers performance for the current test
   * instance. Gets called when the class is nominal. Implementers need only
   * implement this method if it is not possible to compute their statistics
   * from what is stored in the base Evaluation object.
   * 
   * @param predictedDistribution the probabilities assigned to each class
   * @param instance the instance to be classified
   * @throws Exception if the class of the instance is not set
   */
  void updateStatsForClassifier(double[] predictedDistribution,
      Instance instance) throws Exception;

  /**
   * Updates the statistics about a predictors performance for the current test
   * instance. Gets called when the class is numeric. Implementers need only
   * implement this method if it is not possible to compute their statistics
   * from what is stored in the base Evaluation object.
   * 
   * @param predictedValue the numeric value the classifier predicts
   * @param instance the instance to be classified
   * @throws Exception if the class of the instance is not set
   */
  void updateStatsForPredictor(double predictedValue, Instance instance)
      throws Exception;
}
