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
 *    PartitionGenerator.java
 *    Copyright (C) 2012 University of Waikato, Hamilton, New Zealand
 *
 */
package weka.core;

/**
 * This interface can be implemented by algorithms that generate
 * a partition of the instance space (e.g., decision trees).
 *
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @version $Revision: 9117 $
 */
public interface PartitionGenerator extends CapabilitiesHandler {

  /**
   * Builds the classifier to generate a partition.
   */
  public void generatePartition(Instances data) throws Exception;
  
  /**
   * Computes an array that has a value for each element in the partition.
   */
  public double[] getMembershipValues(Instance inst) throws Exception;
  
  /**
   * Returns the number of elements in the partition.
   */
  public int numElements() throws Exception;
}
