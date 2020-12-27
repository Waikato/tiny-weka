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
 *    NumberOfClustersRequestable.java
 *    Copyright (C) 2004-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.clusterers;

/**
 * Interface to a clusterer that can generate a requested number of
 * clusters
 *
 * @author Mark Hall
 * @version $Revision: 8034 $
 */
public interface NumberOfClustersRequestable {
  
  /**
   * Set the number of clusters to generate
   *
   * @param numClusters the number of clusters to generate
   * @exception Exception if the requested number of 
   * clusters in inapropriate
   */
  void setNumClusters(int numClusters) throws Exception;

}

