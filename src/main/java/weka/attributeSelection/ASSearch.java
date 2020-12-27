/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 *    ASSearch.java
 *    Copyright (C) 1999-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.attributeSelection;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Vector;

import weka.core.*;

/** 
 * Abstract attribute selection search class.
 *
 * @author Mark Hall (mhall@cs.waikato.ac.nz)
 * @version $Revision: 15519 $
 */
public abstract class ASSearch
  implements Serializable, RevisionHandler, OptionHandler {

  /** for serialization */
  private static final long serialVersionUID = 7591673350342236548L;

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration<Option> listOptions() {
    Vector<Option> result = Option.listOptionsForClassHierarchy(this.getClass(), ASSearch.class);

    return result.elements();
  }

  /**
   * Parses a given list of options.
   * <p/>
   *
   * @param options the list of options as an array of strings
   * @throws Exception if an option is not supported
   */
  @Override
  public void setOptions(String[] options) throws Exception {
    Option.setOptionsForHierarchy(options, this, ASSearch.class);
    Utils.checkForRemainingOptions(options);
  }

  /**
   * Gets the current settings of the search.
   *
   * @return an array of strings suitable for passing to setOptions
   */
  @Override
  public String[] getOptions() {
    Vector<String> result = new Vector<String>();
    for (String s : Option.getOptionsForHierarchy(this, ASSearch.class)) {
      result.add(s);
    }

    return result.toArray(new String[result.size()]);
  }

  // ===============
  // Public methods.
  // ===============
  
  /**
   * Returns the revision string.
   * 
   * @return		the revision
   */
  public String getRevision() {
    return RevisionUtils.extract("$Revision: 15519 $");
  }
  
  /**
   * Searches the attribute subset/ranking space.
   *
   * @param ASEvaluator the attribute evaluator to guide the search
   * @param data the training instances.
   * @return an array (not necessarily ordered) of selected attribute indexes
   * @throws Exception if the search can't be completed
   */
  public abstract int [] search(ASEvaluation ASEvaluator,
				Instances data) throws Exception;

  /**
   * Creates a new instance of a search class given it's class name and
   * (optional) arguments to pass to it's setOptions method. If the
   * search method implements OptionHandler and the options parameter is
   * non-null, the search method will have it's options set.
   *
   * @param searchName the fully qualified class name of the search class
   * @param options an array of options suitable for passing to setOptions. May
   * be null.
   * @return the newly created search object, ready for use.
   * @throws Exception if the search class name is invalid, or the options
   * supplied are not acceptable to the search class.
   */
  public static ASSearch forName(String searchName,
				 String [] options) throws Exception {
    return (ASSearch)Utils.forName(ASSearch.class,
				   searchName,
				   options);
  }

  /**
   * Creates copies of the current search scheme. Note that this method
   * now uses Serialization to perform a deep copy, so the search
   * object must be fully Serializable. Any currently built model will
   * now be copied as well.
   *
   * @param model an example search scheme to copy
   * @param num the number of search scheme copies to create.
   * @return an array of search schemes.
   * @throws Exception if an error occurs 
   */
  public static ASSearch[] makeCopies(ASSearch model, int num) throws Exception {

    if (model == null)
      throw new Exception("No model search scheme set");
      
    ASSearch[] result = new ASSearch[num];
    SerializedObject so = new SerializedObject(model);
    for (int i = 0; i < result.length; i++)
      result[i] = (ASSearch) so.getObject();

    return result;
  }
}
