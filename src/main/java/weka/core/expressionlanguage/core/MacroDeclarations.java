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
 *    MacroDeclarations.java
 *    Copyright (C) 2015 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.expressionlanguage.core;

/**
 * Interface to expose macros to a program.</p>
 * 
 * It is deliberately kept very simple to give as little constraints
 * as possible to implementations.</p>
 * 
 * There is an implied invariant here:</br>
 * <code>{@link #hasMacro(String)} == true ->
 * {@link #getMacro(String)} != null</code></p>
 * 
 * {@link #hasMacro(String)} should be pure i.e. have no side effects.</br>
 * Whereas {@link #getMacro(String)} may have side effects.</br>
 * (This is useful for creating macros on the fly in {@link #getMacro(String)})
 * 
 * @author Benjamin Weber ( benweber at student dot ethz dot ch )
 * @version $Revision: 1000 $
 */
public interface MacroDeclarations {
  
  /**
   * Whether the macro is declared
   * 
   * @param name name of the macro being queried
   * @return whether the macro is declared
   */
  public boolean hasMacro(String name);
  
  /**
   * Tries to fetch the macro</p>
   * 
   * Before a macro is fetched it shold be checked whether it is declared
   * through {@link #hasMacro(String)}
   * @param name
   * @return
   */
  public Macro getMacro(String name);
}
