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
 *    NoMacros.java
 *    Copyright (C) 2015 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.expressionlanguage.common;

import weka.core.expressionlanguage.core.Macro;
import weka.core.expressionlanguage.core.MacroDeclarations;

/**
 * A macro declarations that contains no macros at all
 * 
 * @author Benjamin Weber ( benweber at student dot ethz dot ch )
 * @version $Revision: 1000 $
 */
public class NoMacros implements MacroDeclarations {

  /**
   * Whether the macro is declared. Always returns <code>false</code>
   * 
   * @param name name of the macro
   * @return whether the macro is declared. Always <code>false</code>.
   */
  @Override
  public boolean hasMacro(String name) {
    return false;
  }

  /**
   * Tries to fetch a macro. Will always fail.</p>
   * 
   * The same invariant of {@link MacroDeclarations} applies here too.
   * 
   * @param name name of the macro
   * @return nothing
   */
  @Override
  public Macro getMacro(String name) {
    throw new RuntimeException("Macro '" + name + "' doesn't exist!");
  }

}
