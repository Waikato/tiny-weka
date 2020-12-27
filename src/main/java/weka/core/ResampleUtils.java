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

/**
 * ResampleUtils.java
 * Copyright (C) 2015 University of Waikato, Hamilton, NZ
 */

package weka.core;

import java.util.Random;

/**
 * Helper class for resampling.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 12226 $
 */
public class ResampleUtils {

  /**
   * Checks whether there are any instance weights other than 1.0 set.
   *
   * @param insts	the dataset to check
   * @return		true if instance weights other than 1.0 are set
   */
  public static boolean hasInstanceWeights(Instances insts) {
    boolean result = false;
    for (int i = 0; i < insts.numInstances(); i++) {
      if (insts.instance(i).weight() != 1.0) {
        result = true;
        break;
      }
    }
    return result;
  }

  /**
   * Resamples the dataset using {@link Instances#resampleWithWeights(Random)}
   * if there are any instance weights other than 1.0 set. Simply returns the
   * dataset if no instance weights other than 1.0 are set.
   *
   * @param insts	the dataset to resample
   * @param rand	the random number generator to use
   * @return		the (potentially) resampled dataset
   */
  public static Instances resampleWithWeightIfNecessary(Instances insts, Random rand) {
    if (hasInstanceWeights(insts))
      return insts.resampleWithWeights(rand);
    else
      return insts;
  }
}
