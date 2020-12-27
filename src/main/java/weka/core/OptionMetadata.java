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
 *    OptionMetadata.java
 *    Copyright (C) 2015 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

import java.lang.annotation.*;

/**
 * Method annotation that can be used with scheme parameters to provide a nice
 * display-ready name for the parameter, help information and, if applicable,
 * command line option details
 *
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: 15237 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OptionMetadata {

  /**
   * The nice GUI displayable name for this parameter
   *
   * @return a nice displayable name
   */
  String displayName();

  /**
   * Description of this parameter. Displayed as a tool tip and help in the GUI,
   * and on the command line.
   *
   * @return the description text of this parameter
   */
  String description();

  /**
   * Optional category for the parameter. GUI dialog can use this to group
   * certain options for display in child dialogs
   *
   * @return the category for this option
   */
  String category() default "";

  /**
   * The order (low to high), relative to other parameters, that this property
   * should be displayed in the GUI and, if applicable, on the command line help
   *
   * @return the order (default 100)
   */
  int displayOrder() default 100;

  /**
   * The name of the command line version of this parameter (without leading -).
   * If this parameter is not a command line one, then just leave at the default
   * empty string.
   *
   * @return the name of the command line version of this parameter
   */
  String commandLineParamName() default "";

  /**
   * True if the command line version of this parameter is a flag (i.e. binary
   * parameter).
   *
   * @return true if the command line version of this parameter is a flag
   */
  boolean commandLineParamIsFlag() default false;

  /**
   * The synopsis to display on in the command line help for this parameter
   * (e.g. -Z <integer>). If this parameter is not a command line one, then just
   * leave at the default empty string.
   *
   * @return the command line synopsis for this parameter
   */
  String commandLineParamSynopsis() default "";
}
