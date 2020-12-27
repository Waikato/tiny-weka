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
 *    AggregateableEvaluation.java
 *    Copyright (C) 2011-2012 University of Waikato, Hamilton, New Zealand
 */

package weka.classifiers;

import weka.core.Instances;

/**
 * Subclass of Evaluation that provides a method for aggregating the results
 * stored in another Evaluation object. Delegates to the actual implementation
 * in weka.classifiers.evaluation.AggregateableEvaluation.
 * 
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: 9320 $
 */
public class AggregateableEvaluation extends Evaluation {

  /** For serialization */
  private static final long serialVersionUID = 6850546230173753210L;

  /**
   * Constructs a new AggregateableEvaluation object
   * 
   * @param data the Instances to use
   * @throws Exception if a problem occurs
   */
  public AggregateableEvaluation(Instances data) throws Exception {
    super(data);
    m_delegate = new weka.classifiers.evaluation.AggregateableEvaluation(data);
  }

  /**
   * Constructs a new AggregateableEvaluation object
   * 
   * @param data the Instances to use
   * @param costMatrix the cost matrix to use
   * @throws Exception if a problem occurs
   */
  public AggregateableEvaluation(Instances data, CostMatrix costMatrix)
      throws Exception {
    super(data, costMatrix);
    m_delegate = new weka.classifiers.evaluation.AggregateableEvaluation(data,
        costMatrix);
  }

  /**
   * Constructs a new AggregateableEvaluation object based on an Evaluation
   * object
   * 
   * @param eval the Evaluation object to use
   */
  public AggregateableEvaluation(Evaluation eval) throws Exception {
    super(eval.getHeader());
    m_delegate = new weka.classifiers.evaluation.AggregateableEvaluation(
        eval.m_delegate);
  }

  /**
   * Adds the statistics encapsulated in the supplied Evaluation object into
   * this one. Does not perform any checks for compatibility between the
   * supplied Evaluation object and this one.
   * 
   * @param evaluation the evaluation object to aggregate
   */
  public void aggregate(Evaluation evaluation) {
    ((weka.classifiers.evaluation.AggregateableEvaluation) m_delegate)
        .aggregate(evaluation.m_delegate);
  }
}
