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
 *    UnsupervisedSubsetEvaluator.java
 *    Copyright (C) 1999-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.attributeSelection;

import weka.clusterers.Clusterer;

/** 
 * Abstract unsupervised attribute subset evaluator.
 *
 * @author Mark Hall (mhall@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public abstract class UnsupervisedSubsetEvaluator 
  extends ASEvaluation
  implements SubsetEvaluator {

  /** for serialization */
  static final long serialVersionUID = 627934376267488763L;
  
  /**
   * Return the number of clusters used by the subset evaluator
   *
   * @return the number of clusters used
   * @exception Exception if an error occurs
   */
  public abstract int getNumClusters() throws Exception;

  /**
   * Get the clusterer
   *
   * @return the clusterer
   */
  public abstract Clusterer getClusterer();

  /**
   * Set the clusterer to use
   *
   * @param d the clusterer to use
   */
  public abstract void setClusterer(Clusterer d);
}
