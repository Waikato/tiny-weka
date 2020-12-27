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
 *    VariableDeclarations.java
 *    Copyright (C) 2015 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.expressionlanguage.core;

import java.io.Serializable;

/**
 * Interface to expose variables to a program.</p>
 * 
 * It is deliberately kept very simple to give as little constraints
 * as possible to implementations.</p>
 * 
 * There is an implied invariant here:</br>
 * <code>{@link #hasVariable(String)} == true ->
 * {@link #getVariable(String)} != null</code></p>
 * 
 * {@link #hasVariable(String)} should be pure i.e. have no side effects.</br>
 * Whereas {@link #getVariable(String)} may have side effects.</br>
 * (This is useful for creating variables on the fly in {@link #getVariable(String)})
 * 
 * @author Benjamin Weber ( benweber at student dot ethz dot ch )
 * @version $Revision: 1000 $
 */
public interface VariableDeclarations extends Serializable {

  /**
   * Whether the variable is declared
   * 
   * @param name name of the variable being queried
   * @return whether the variable is declared
   */
  public boolean hasVariable(String name);
  
  /**
   * Tries to fetch a variable</p>
   * 
   * Before a variable is fetched it should be checked whether it is declared
   * through {@link #hasVariable(String)}.
   * 
   * @param name name of the variable to be fetched
   * @return an AST (abstract syntax tree) node representing the variable
   */
  public Node getVariable(String name);
}
