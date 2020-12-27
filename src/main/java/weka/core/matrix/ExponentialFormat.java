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
 *    ExponentialFormat.java
 *    Copyright (C) 2002-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.core.matrix;

import java.text.DecimalFormat;
import java.text.FieldPosition;

import weka.core.RevisionHandler;
import weka.core.RevisionUtils;

/**
 * @author Yong Wang
 * @version $Revision: 8034 $
 */
public class ExponentialFormat
  extends DecimalFormat
  implements RevisionHandler {

  /** for serialization */
  private static final long serialVersionUID = -5298981701073897741L;
    
  protected DecimalFormat nf ;
  protected boolean sign;
  protected int digits;
  protected int exp;
  protected boolean trailing = true;

  public ExponentialFormat () {
    this( 5 );
  }
    
  public ExponentialFormat( int digits ) {
    this( digits, false );
  }

  public ExponentialFormat( int digits, boolean trailing ) {
    this( digits, 2, true, trailing );
  }
    
  public ExponentialFormat( int digits, int exp, boolean sign, 
			    boolean trailing ) {
    this.digits = digits;
    this.exp = exp;
    this.sign = sign;
    this.trailing = trailing;
    nf = new DecimalFormat( pattern() );
    nf.setPositivePrefix("+");
    nf.setNegativePrefix("-");
  }
    
  public int width () {
    if( !trailing ) throw new RuntimeException( "flexible width" );
    if( sign ) return 1 + digits + 2 + exp;
    else return digits + 2 + exp;
  }

  public StringBuffer format(double number, StringBuffer toAppendTo, 
			     FieldPosition pos) {
    StringBuffer s = new StringBuffer( nf.format(number) );
    if( sign ) {
      if( s.charAt(0) == '+' ) s.setCharAt(0, ' ');
    }
    else {
      if( s.charAt(0) == '-' ) s.setCharAt(0, '*');
      else s.deleteCharAt(0);
    }
	
    return toAppendTo.append( s );
  }
    
  private String  pattern() {
    StringBuffer s = new StringBuffer();      // "-##0.00E-00"   // fw.d
    s.append("0.");
    for(int i = 0; i < digits - 1; i ++)
      if( trailing ) s.append('0');
      else s.append('#');
	
    s.append('E');
    for(int i = 0; i < exp; i ++)
      s.append('0');
	
    return s.toString();
  }
  
  /**
   * Returns the revision string.
   * 
   * @return		the revision
   */
  public String getRevision() {
    return RevisionUtils.extract("$Revision: 8034 $");
  }
}
