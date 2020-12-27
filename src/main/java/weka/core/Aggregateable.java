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
 *    Aggregateable.java
 *    Copyright (C) 2013 University of Waikato, Hamilton, New Zealand
 */

package weka.core;

/**
 * Interface to something that can aggregate an object of the same type with
 * itself.
 * 
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: 9784 $
 */
public interface Aggregateable<E> {

  /**
   * Aggregate an object with this one
   * 
   * @param toAggregate the object to aggregate
   * @return the result of aggregation
   * @throws Exception if the supplied object can't be aggregated for some
   *           reason
   */
  E aggregate(E toAggregate) throws Exception;

  /**
   * Call to complete the aggregation process. Allows implementers to do any
   * final processing based on how many objects were aggregated.
   * 
   * @throws Exception if the aggregation can't be finalized for some reason
   */
  void finalizeAggregation() throws Exception;
}
