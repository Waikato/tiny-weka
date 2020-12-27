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
 *    StreamTokenizerUtils.java
 *    Copyright (C) 2000-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.converters;

import java.io.IOException;
import java.io.Serializable;
import java.io.StreamTokenizer;

import weka.core.RevisionHandler;
import weka.core.RevisionUtils;

/**
 * Helper class for using stream tokenizers
 * 
 * @author Mark Hall (mhall{[at]}pentaho{[dot]}com)
 * @version $Revision: 9284 $
 */
public class StreamTokenizerUtils implements Serializable, RevisionHandler {

  /** For serialization */
  private static final long serialVersionUID = -5786996944597404253L;

  @Override
  public String getRevision() {
    return RevisionUtils.extract("$Revision: 9284 $");
  }

  /**
   * Gets token, skipping empty lines.
   * 
   * @param tokenizer the stream tokenizer
   * @throws IOException if reading the next token fails
   */
  public static void getFirstToken(StreamTokenizer tokenizer)
      throws IOException {

    while (tokenizer.nextToken() == StreamTokenizer.TT_EOL) {
    }
    ;
    if ((tokenizer.ttype == '\'') || (tokenizer.ttype == '"')) {
      tokenizer.ttype = StreamTokenizer.TT_WORD;
    } else if ((tokenizer.ttype == StreamTokenizer.TT_WORD)
        && (tokenizer.sval.equals("?"))) {
      tokenizer.ttype = '?';
    }
  }

  /**
   * Gets token.
   * 
   * @param tokenizer the stream tokenizer
   * @throws IOException if reading the next token fails
   */
  public static void getToken(StreamTokenizer tokenizer) throws IOException {

    tokenizer.nextToken();
    if (tokenizer.ttype == StreamTokenizer.TT_EOL) {
      return;
    }

    if ((tokenizer.ttype == '\'') || (tokenizer.ttype == '"')) {
      tokenizer.ttype = StreamTokenizer.TT_WORD;
    } else if ((tokenizer.ttype == StreamTokenizer.TT_WORD)
        && (tokenizer.sval.equals("?"))) {
      tokenizer.ttype = '?';
    }
  }

  /**
   * Throws error message with line number and last token read.
   * 
   * @param theMsg the error message to be thrown
   * @param tokenizer the stream tokenizer
   * @throws IOException containing the error message
   */
  public static void errms(StreamTokenizer tokenizer, String theMsg)
      throws IOException {

    throw new IOException(theMsg + ", read " + tokenizer.toString());
  }
}
