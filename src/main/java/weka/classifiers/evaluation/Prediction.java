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
 *    Prediction.java
 *    Copyright (C) 2002-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.classifiers.evaluation;

/**
 * Encapsulates a single evaluatable prediction: the predicted value plus the 
 * actual class value.
 *
 * @author Len Trigg (len@reeltwo.com)
 * @version $Revision: 8034 $
 */
public interface Prediction {

  /** 
   * Constant representing a missing value. This should have the same value
   * as weka.core.Instance.MISSING_VALUE 
   */
  double MISSING_VALUE 
    = weka.core.Utils.missingValue();

  /** 
   * Gets the weight assigned to this prediction. This is typically the weight
   * of the test instance the prediction was made for.
   *
   * @return the weight assigned to this prediction.
   */
  double weight();

  /** 
   * Gets the actual class value.
   *
   * @return the actual class value, or MISSING_VALUE if no
   * prediction was made.  
   */
  double actual();

  /**
   * Gets the predicted class value.
   *
   * @return the predicted class value, or MISSING_VALUE if no
   * prediction was made.  
   */
  double predicted();

}
