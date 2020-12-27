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
 *    AssociationRulesProducer.java
 *    Copyright (C) 2010-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.associations;


/**
 * Interface to something that can provide a list of
 * AssociationRules.
 * 
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: 8034 $
 */
public interface AssociationRulesProducer {
  
  /**
   * Gets the list of mined association rules.
   * 
   * @return the list of association rules discovered during mining.
   * Returns null if mining hasn't been performed yet.
   */
  AssociationRules getAssociationRules();
  
  /**
   * Gets a list of the names of the metrics output for
   * each rule. This list should be the same (in terms of
   * the names and order thereof) as that produced by
   * AssociationRule.getMetricNamesForRule().
   * 
   * @return an array of the names of the metrics available
   * for each rule learned by this producer.
   */
  String[] getRuleMetricNames();
  
  /**
   * Returns true if this AssociationRulesProducer can actually
   * produce rules. Most implementing classes will always return
   * true from this method (obviously :-)). However, an implementing
   * class that actually acts as a wrapper around things that may
   * or may not implement AssociationRulesProducer will want to
   * return false if the thing they wrap can't produce rules.
   * 
   * @return true if this producer can produce rules in its current
   * configuration
   */
  boolean canProduceRules();
}
