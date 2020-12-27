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
 *    Macro.java
 *    Copyright (C) 2015 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.expressionlanguage.core;

/**
 * Interface for compile time macros to enable meta programming</p>
 * 
 * Because macros have direct access to the AST (abstract syntax tree) they can
 * rewrite it as they please.
 * 
 * @author Benjamin Weber ( benweber at student dot ethz dot ch )
 * @version $Revision: 1000 $
 */
public interface Macro {
  /**
   * Applies a macro to a set of parameter nodes.
   * 
   * @param nodes the nodes this macro should be applied to
   * @return an AST node returned by the macro
   * @throws SemanticException
   */
  Node evaluate(Node... nodes) throws SemanticException;
}