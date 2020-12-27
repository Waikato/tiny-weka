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
 *    AttributeEvaluator.java
 *    Copyright (C) 1999-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.attributeSelection;


/** 
 * Interface for classes that evaluate attributes individually.
 *
 * @author Mark Hall (mhall@cs.waikato.ac.nz)
 * @version $Revision: 8034 $
 */
public interface AttributeEvaluator {
  
    /**
     * evaluates an individual attribute
     *
     * @param attribute the index of the attribute to be evaluated
     * @return the "merit" of the attribute
     * @exception Exception if the attribute could not be evaluated
     */
    public abstract double evaluateAttribute(int attribute) throws Exception;
}




