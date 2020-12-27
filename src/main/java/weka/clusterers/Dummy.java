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
 * Dummy.java
 * Copyright (C) 2020 University of Waikato, Hamilton, NZ
 */

package weka.clusterers;

import weka.core.Instances;

/**
 * TODO: What class does.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class Dummy
  extends AbstractClusterer {

  /**
   * Generates a clusterer. Has to initialize all fields of the clusterer that
   * are not being set via options.
   *
   * @param data set of instances serving as training data
   * @throws Exception if the clusterer has not been generated successfully
   */
  @Override
  public void buildClusterer(Instances data) throws Exception {
    throw new Exception("Not implemented");
  }

  /**
   * Returns the number of clusters.
   *
   * @return the number of clusters generated for a training dataset.
   * @throws Exception if number of clusters could not be returned
   *                   successfully
   */
  @Override
  public int numberOfClusters() throws Exception {
    throw new Exception("Not implemented");
  }
}
