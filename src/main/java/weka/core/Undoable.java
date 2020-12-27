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
 *    Copyable.java
 *    Copyright (C) 2005-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

/**
 * Interface implemented by classes that support undo.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 8034 $
 */
public interface Undoable {
  /**
   * returns whether undo support is enabled
   */
  public boolean isUndoEnabled();
  
  /**
   * sets whether undo support is enabled
   */
  public void setUndoEnabled(boolean enabled);

  /**
   * removes the undo history
   */
  public void clearUndo();
  
  /**
   * returns whether an undo is possible, i.e. whether there are any undo points
   * saved so far
   * 
   * @return returns TRUE if there is an undo possible 
   */
  public boolean canUndo();
  
  /**
   * undoes the last action
   */
  public void undo();
  
  /**
   * adds an undo point to the undo history 
   */
  public void addUndoPoint();
}
