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
 *    SubsetEvaluator.java
 *    Copyright (C) 1999-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.attributeSelection;

import java.util.BitSet;

/** 
 * Interface for attribute subset evaluators.
 *
 * @author Mark Hall (mhall@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public interface SubsetEvaluator {
    
  /**
   * evaluates a subset of attributes
   *
   * @param subset a bitset representing the attribute subset to be 
   * evaluated 
   * @return the "merit" of the subset
   * @exception Exception if the subset could not be evaluated
   */
  double evaluateSubset(BitSet subset) throws Exception;
}

