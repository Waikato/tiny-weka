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
 *    Matchable.java
 *    Copyright (C) 1999-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

/** 
 * Interface to something that can be matched with tree matching
 * algorithms.
 *
 * @author Len Trigg (trigg@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public interface Matchable {

  /**
   * Returns a string that describes a tree representing
   * the object in prefix order.
   *
   * @return the tree described as a string
   * @exception Exception if the tree can't be computed
   */
  String prefix() throws Exception;
}








