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
 *    EvaluationUtils.java
 *    Copyright (C) 2002-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.classifiers.evaluation;

import java.util.ArrayList;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.RevisionHandler;
import weka.core.RevisionUtils;

/**
 * Contains utility functions for generating lists of predictions in various
 * manners.
 * 
 * @author Len Trigg (len@reeltwo.com)
 * @version $Revision: 10153 $
 */
public class EvaluationUtils implements RevisionHandler {

  /** Seed used to randomize data in cross-validation */
  private int m_Seed = 1;

  /** Sets the seed for randomization during cross-validation */
  public void setSeed(int seed) {
    m_Seed = seed;
  }

  /** Gets the seed for randomization during cross-validation */
  public int getSeed() {
    return m_Seed;
  }

  /**
   * Generate a bunch of predictions ready for processing, by performing a
   * cross-validation on the supplied dataset.
   * 
   * @param classifier the Classifier to evaluate
   * @param data the dataset
   * @param numFolds the number of folds in the cross-validation.
   * @exception Exception if an error occurs
   */
  public ArrayList<Prediction> getCVPredictions(Classifier classifier,
    Instances data, int numFolds) throws Exception {

    ArrayList<Prediction> predictions = new ArrayList<Prediction>();
    Instances runInstances = new Instances(data);
    Random random = new Random(m_Seed);
    runInstances.randomize(random);
    if (runInstances.classAttribute().isNominal() && (numFolds > 1)) {
      runInstances.stratify(numFolds);
    }
    for (int fold = 0; fold < numFolds; fold++) {
      Instances train = runInstances.trainCV(numFolds, fold, random);
      Instances test = runInstances.testCV(numFolds, fold);
      ArrayList<Prediction> foldPred = getTrainTestPredictions(classifier,
        train, test);
      predictions.addAll(foldPred);
    }
    return predictions;
  }

  /**
   * Generate a bunch of predictions ready for processing, by performing a
   * evaluation on a test set after training on the given training set.
   * 
   * @param classifier the Classifier to evaluate
   * @param train the training dataset
   * @param test the test dataset
   * @exception Exception if an error occurs
   */
  public ArrayList<Prediction> getTrainTestPredictions(Classifier classifier,
    Instances train, Instances test) throws Exception {

    classifier.buildClassifier(train);
    return getTestPredictions(classifier, test);
  }

  /**
   * Generate a bunch of predictions ready for processing, by performing a
   * evaluation on a test set assuming the classifier is already trained.
   * 
   * @param classifier the pre-trained Classifier to evaluate
   * @param test the test dataset
   * @exception Exception if an error occurs
   */
  public ArrayList<Prediction> getTestPredictions(Classifier classifier,
    Instances test) throws Exception {

    ArrayList<Prediction> predictions = new ArrayList<Prediction>();
    for (int i = 0; i < test.numInstances(); i++) {
      if (!test.instance(i).classIsMissing()) {
        predictions.add(getPrediction(classifier, test.instance(i)));
      }
    }
    return predictions;
  }

  /**
   * Generate a single prediction for a test instance given the pre-trained
   * classifier.
   * 
   * @param classifier the pre-trained Classifier to evaluate
   * @param test the test instance
   * @exception Exception if an error occurs
   */
  public Prediction getPrediction(Classifier classifier, Instance test)
    throws Exception {

    double actual = test.classValue();
    double[] dist = classifier.distributionForInstance(test);
    if (test.classAttribute().isNominal()) {
      return new NominalPrediction(actual, dist, test.weight());
    } else {
      return new NumericPrediction(actual, dist[0], test.weight());
    }
  }

  /**
   * Returns the revision string.
   * 
   * @return the revision
   */
  @Override
  public String getRevision() {
    return RevisionUtils.extract("$Revision: 10153 $");
  }
}
