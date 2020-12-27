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
 *    Randomizable.java
 *    Copyright (C) 2003-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

/** 
 * Interface to something that has random behaviour that is able to be
 * seeded with an integer.
 *
 * @author Richard Kirkby (rkirkby@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public interface Randomizable {

  /**
   * Set the seed for random number generation.
   *
   * @param seed the seed 
   */
  void setSeed(int seed);
  
  /**
   * Gets the seed for the random number generations
   *
   * @return the seed for the random number generation
   */
  int getSeed();
}
