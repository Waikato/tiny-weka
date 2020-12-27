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
 *    FontHelper.java
 *    Copyright (C) 2015 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core;

import java.awt.Font;

/**
 * Wrapper class for Font objects. Fonts wrapped in this class can
 * be serialized by Weka's XML serialization mechanism.
 *
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: $
 */
public class FontHelper {

  /** The name of the font */
  protected String m_fontName;

  /** The style of the font */
  protected int m_fontStyle;

  /** The font size */
  protected int m_fontSize;

  /**
   * Constructor
   *
   * @param font the font to wrap
   */
  public FontHelper(Font font) {
    m_fontName = font.getFontName();
    m_fontSize = font.getSize();
    m_fontStyle = font.getStyle();
  }

  /**
   * No-op constructor (for beans conformity)
   */
  public FontHelper() {
  }

  /**
   * Set the font name
   *
   * @param fontName the name of the font
   */
  public void setFontName(String fontName) {
    m_fontName = fontName;
  }

  /**
   * Get the font name
   *
   * @return the font name
   */
  public String getFontName() {
    return m_fontName;
  }

  /**
   * Set the font style (see constants in Font class)
   *
   * @param style the style of the font
   */
  public void setFontStyle(int style) {
    m_fontStyle = style;
  }

  /**
   * Get the font style (see constants in Font class)
   *
   * @return the style of the font
   */
  public int getFontStyle() {
    return m_fontStyle;
  }

  /**
   * Set the font size
   *
   * @param size the size
   */
  public void setFontSize(int size) {
    m_fontSize = size;
  }

  /**
   * Get the font size
   *
   * @return the size
   */
  public int getFontSize() {
    return m_fontSize;
  }

  /**
   * Get the Font wrapped by this instance
   *
   * @return the Font object
   */
  public Font getFont() {
    if (m_fontName != null) {
      return new Font(m_fontName, m_fontStyle, m_fontSize);
    }
    return new Font("Monospaced", Font.PLAIN, 12);
  }
}
