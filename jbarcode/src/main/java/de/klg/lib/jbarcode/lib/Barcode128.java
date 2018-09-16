/*
 * JBarcode - a Java package for drawing barcodes.
 *
 * Inspired by the barcode component of iText
 *
 * @author Boris Klug
 */
package de.klg.lib.jbarcode.lib;

import java.util.Map;

/**
 * Barcode generator class for the following barcodes:
 *
 * EAN 128 Code 128 Code 128 (subset A) Code 128 (Raw)
 *
 *
 * Description:
 *
 * Code 128 Generates the barcode by automatically selecting the right subset
 * for the given text. E.g. when only numbers are in the text, the subset C is
 * selected.
 *
 * EAN 128 like Code 128 but after the start a FNC1 is inserted.
 *
 * Code 128 A Uses only subset A for coding the barcode.
 *
 * Code 128 Raw The user has to generate the barcode sequence. Chars are valid
 * from 0 to 105. The caller has to build the code on its own - including start
 * sequence.
 *
 * @author Boris Klug
 */
public class Barcode128 extends Barcode {

  /**
   * The bars to generate the code.
   */
  static final byte[][] BARS = { { 2, 1, 2, 2, 2, 2 }, // 00
      { 2, 2, 2, 1, 2, 2 }, { 2, 2, 2, 2, 2, 1 }, { 1, 2, 1, 2, 2, 3 }, { 1, 2, 1, 3, 2, 2 }, { 1, 3, 1, 2, 2, 2 },
      { 1, 2, 2, 2, 1, 3 }, { 1, 2, 2, 3, 1, 2 }, { 1, 3, 2, 2, 1, 2 }, { 2, 2, 1, 2, 1, 3 }, { 2, 2, 1, 3, 1, 2 }, // 10
      // "*"
      { 2, 3, 1, 2, 1, 2 }, { 1, 1, 2, 2, 3, 2 }, { 1, 2, 2, 1, 3, 2 }, { 1, 2, 2, 2, 3, 1 }, { 1, 1, 3, 2, 2, 2 },
      { 1, 2, 3, 1, 2, 2 }, { 1, 2, 3, 2, 2, 1 }, { 2, 2, 3, 2, 1, 1 }, { 2, 2, 1, 1, 3, 2 }, { 2, 2, 1, 2, 3, 1 }, // 20
      // "4"
      { 2, 1, 3, 2, 1, 2 }, { 2, 2, 3, 1, 1, 2 }, { 3, 1, 2, 1, 3, 1 }, { 3, 1, 1, 2, 2, 2 }, { 3, 2, 1, 1, 2, 2 },
      { 3, 2, 1, 2, 2, 1 }, { 3, 1, 2, 2, 1, 2 }, { 3, 2, 2, 1, 1, 2 }, { 3, 2, 2, 2, 1, 1 }, { 2, 1, 2, 1, 2, 3 }, // 30
      { 2, 1, 2, 3, 2, 1 }, { 2, 3, 2, 1, 2, 1 }, { 1, 1, 1, 3, 2, 3 }, { 1, 3, 1, 1, 2, 3 }, { 1, 3, 1, 3, 2, 1 },
      { 1, 1, 2, 3, 1, 3 }, { 1, 3, 2, 1, 1, 3 }, { 1, 3, 2, 3, 1, 1 }, { 2, 1, 1, 3, 1, 3 }, { 2, 3, 1, 1, 1, 3 }, // 40
      // "H"
      { 2, 3, 1, 3, 1, 1 }, { 1, 1, 2, 1, 3, 3 }, { 1, 1, 2, 3, 3, 1 }, { 1, 3, 2, 1, 3, 1 }, { 1, 1, 3, 1, 2, 3 },
      { 1, 1, 3, 3, 2, 1 }, { 1, 3, 3, 1, 2, 1 }, { 3, 1, 3, 1, 2, 1 }, { 2, 1, 1, 3, 3, 1 }, { 2, 3, 1, 1, 3, 1 }, // 50
      { 2, 1, 3, 1, 1, 3 }, { 2, 1, 3, 3, 1, 1 }, { 2, 1, 3, 1, 3, 1 }, { 3, 1, 1, 1, 2, 3 }, { 3, 1, 1, 3, 2, 1 },
      { 3, 3, 1, 1, 2, 1 }, { 3, 1, 2, 1, 1, 3 }, { 3, 1, 2, 3, 1, 1 }, { 3, 3, 2, 1, 1, 1 }, { 3, 1, 4, 1, 1, 1 }, // 60
      { 2, 2, 1, 4, 1, 1 }, { 4, 3, 1, 1, 1, 1 }, { 1, 1, 1, 2, 2, 4 }, { 1, 1, 1, 4, 2, 2 }, { 1, 2, 1, 1, 2, 4 },
      { 1, 2, 1, 4, 2, 1 }, { 1, 4, 1, 1, 2, 2 }, { 1, 4, 1, 2, 2, 1 }, { 1, 1, 2, 2, 1, 4 }, { 1, 1, 2, 4, 1, 2 }, // 70
      { 1, 2, 2, 1, 1, 4 }, { 1, 2, 2, 4, 1, 1 }, { 1, 4, 2, 1, 1, 2 }, { 1, 4, 2, 2, 1, 1 }, { 2, 4, 1, 2, 1, 1 },
      { 2, 2, 1, 1, 1, 4 }, { 4, 1, 3, 1, 1, 1 }, { 2, 4, 1, 1, 1, 2 }, { 1, 3, 4, 1, 1, 1 }, { 1, 1, 1, 2, 4, 2 }, // 80
      { 1, 2, 1, 1, 4, 2 }, { 1, 2, 1, 2, 4, 1 }, { 1, 1, 4, 2, 1, 2 }, { 1, 2, 4, 1, 1, 2 }, { 1, 2, 4, 2, 1, 1 },
      { 4, 1, 1, 2, 1, 2 }, { 4, 2, 1, 1, 1, 2 }, { 4, 2, 1, 2, 1, 1 }, { 2, 1, 2, 1, 4, 1 }, { 2, 1, 4, 1, 2, 1 }, // 90
      { 4, 1, 2, 1, 2, 1 }, { 1, 1, 1, 1, 4, 3 }, { 1, 1, 1, 3, 4, 1 }, { 1, 3, 1, 1, 4, 1 }, { 1, 1, 4, 1, 1, 3 },
      { 1, 1, 4, 3, 1, 1 }, { 4, 1, 1, 1, 1, 3 }, { 4, 1, 1, 3, 1, 1 }, { 1, 1, 3, 1, 4, 1 }, // 99 Code C, 99
      { 1, 1, 4, 1, 3, 1 }, // 100 Code B, FNC4
      { 3, 1, 1, 1, 4, 1 }, // 101 Code A, FNC4
      { 4, 1, 1, 1, 3, 1 }, // 102 FNC1
      { 2, 1, 1, 4, 1, 2 }, // 103 Start Code A
      { 2, 1, 1, 2, 1, 4 }, // 104 Start Code B
      { 2, 1, 1, 2, 3, 2 } // 105 Start Code C
  };

  /**
   * The stop bars, it has 7 bars, all other chars have 6
   */
  static final byte[] BARS_STOP = { 2, 3, 3, 1, 1, 1, 2 };
  /**
   * The charset code change from A or B to C
   */
  public static final char CODE_AB_TO_C = 99;
  /**
   * The charset code change from A or C to B
   */
  public static final char CODE_AC_TO_B = 100;
  /**
   * The charset code change from B or C to A
   */
  public static final char CODE_BC_TO_A = 101;
  /**
   * FNC1, e.g. used in EAN-128/UCC.
   */
  public static final char FNC1 = 102;
  /**
   * The start code with subset A
   */
  public static final char START_A = 103;
  /**
   * The start code with subset B
   */
  public static final char START_B = 104;
  /**
   * The start code with subset C
   */
  public static final char START_C = 105;

  /**
   * Constructor - creates new Barcode128
   */
  public Barcode128() {
    fontname = "SanSerif";
    fontsize = 8;
    baseline = fontsize;
    barWidth = 1;
    preflightWidth = barWidth * 10;
    barHeight = fontsize * 3;
    codeType = CODE128;
    code = "01040123453333361503123110123456";
  }

  /**
   * Returns <CODE>true</CODE> if the next <CODE>numDigits</CODE> starting from
   * index <CODE>textIndex</CODE> are numeric.
   *
   * @param text      the text to check
   * @param textIndex where to check from
   * @param numDigits the number of digits to check
   * @return the check result
   */
  static boolean isNextDigits(String text, int textIndex, int numDigits) {
    if (textIndex + numDigits > text.length())
      return false;
    while (numDigits-- > 0) {
      char c = text.charAt(textIndex++);
      if (c < '0' || c > '9')
        return false;
    }
    return true;
  }

  /**
   * Packs the digits for charset C. It assumes that all the parameters are valid.
   * 
   * @param text      the text to pack
   * @param textIndex where to pack from
   * @param numDigits the number of digits to pack. It is always an even number
   * @return the packed digits, two digits per character
   */
  static String getPackedRawDigits(String text, int textIndex, int numDigits) {
    String out = "";
    while (numDigits > 0) {
      numDigits -= 2;
      int c1 = text.charAt(textIndex++) - '0';
      int c2 = text.charAt(textIndex++) - '0';
      out += (char) (c1 * 10 + c2);
    }
    return out;
  }

  /**
   * Converts the text to human readable text. This text is used for printing
   * below the barcode
   *
   * Only for EAN128 this method does something. It calles the routines in the
   * class BarcodeEAN128Textdecoder
   */
  @Override
  public String toHumanText(String text) {
    if (codeType != Barcode.EAN128) {
      return text;
    }

    // Get the fields of the EAN128 standard
    Map<String, BarcodeEAN128Field> ean128fields = BarcodeEAN128Textdecoder.getEAN128fieldlist();

    // Return the converted string
    String out = BarcodeEAN128Textdecoder.toHumanText(text, ean128fields);

    if (out != null) {
      return out;
    } else {
      return "<cant parse text>";
    }
  }

  /**
   * Converts the code text to the characters needed to create a barcode. Some
   * optimization is done to get the shortest code.
   *
   * What the method does depends on the codetype: code128 convert the text, try
   * to optimize the code by automaticly changeing the subsets EAN128/ucc same
   * like code128 but add the character FNC1 code128raw does nothing, just pass
   * the text
   *
   * @param text the text to convert
   * @return the converted code
   */
  @Override
  public String toRawText(String text) {
    boolean ean128 = false;

    // In mode "code128A" we force the charset A
    if (codeType == Barcode.CODE128_A) {
      String out = "" + START_A;
      int tLen = text.length();
      int i = 0;
      while (i < tLen) {
        char currentchar = text.charAt(i);
        if (currentchar > 127)
          throw new RuntimeException("There are illegal characters for barcode 128 in '" + text + "'.");
        if (currentchar < ' ')
          out += (char) (currentchar + 64); // control chars
        else
          out += (char) (currentchar - ' '); // normal chars
        i++;
      }

      return out;
    }

    // In raw mode, just return the text. The caller is responsible
    // to provide a valid code128 text
    if (codeType == Barcode.CODE128_RAW) {
      return text;
    }

    // In EAN128, we have to add a FNC1 in front of the code
    if (codeType == Barcode.EAN128) {
      ean128 = true;
    } else if (codeType != Barcode.CODE128) {
      return null; // we cant handle other codes than this
    }

    String out = "";
    int tLen = text.length();

    if (tLen == 0) { // empty barcode!
      out += START_B;
      if (ean128)
        out += FNC1;
      return out;
    }

    int c = 0;
    // check the characters inside the text
    for (int k = 0; k < tLen; ++k) {
      c = text.charAt(k);
      if (c > 127)
        throw new RuntimeException("There are illegal characters for barcode 128 in '" + text + "'.");
    }

    // Lookup which subset we start with
    c = text.charAt(0);
    char currentCode = START_B;
    int index = 0;
    if (isNextDigits(text, index, 2)) { // two digits or more, we start with subset C
      currentCode = START_C;
      out += currentCode;
      if (ean128)
        out += FNC1;
      out += getPackedRawDigits(text, index, 2);
      index += 2;
    } else if (c < ' ') { // no lowercase chars, we start with subset A
      currentCode = START_A;
      out += currentCode;
      if (ean128)
        out += FNC1;
      out += (char) (c + 64);
      ++index;
    } else { // OK, we have all kind of chars, we start with subset B
      out += currentCode;
      if (ean128)
        out += FNC1;
      out += (char) (c - ' ');
      ++index;
    }

    while (index < tLen) {
      switch (currentCode) {
      case START_A:
        if (isNextDigits(text, index, 4)) {
          currentCode = START_C;
          out += CODE_AB_TO_C;
          out += getPackedRawDigits(text, index, 4);
          index += 4;
        } else {
          c = text.charAt(index++);
          if (c > '_') {
            currentCode = START_B;
            out += CODE_AC_TO_B;
            out += (char) (c - ' ');
          } else if (c < ' ')
            out += (char) (c + 64);
          else
            out += (char) (c - ' ');
        }
        break;
      case START_B:
        if (isNextDigits(text, index, 4)) {
          currentCode = START_C;
          out += CODE_AB_TO_C;
          out += getPackedRawDigits(text, index, 4);
          index += 4;
        } else {
          c = text.charAt(index++);
          if (c < ' ') {
            currentCode = START_A;
            out += CODE_BC_TO_A;
            out += (char) (c + 64);
          } else {
            out += (char) (c - ' ');
          }
        }
        break;
      case START_C:
        if (isNextDigits(text, index, 2)) {
          out += getPackedRawDigits(text, index, 2);
          index += 2;
        } else {
          c = text.charAt(index++);
          if (c < ' ') {
            currentCode = START_A;
            out += CODE_BC_TO_A;
            out += (char) (c + 64);
          } else {
            currentCode = START_B;
            out += CODE_AC_TO_B;
            out += (char) (c - ' ');
          }
        }
        break;
      }
    }
    return out;
  }

  /**
   * Generates the bars. The input is the actual barcodes, not the human readable
   * text.
   *
   * @param text the barcode
   * @return the bars
   */
  @Override
  public byte[] getBars() {
    String text = toRawText(code);

    // In raw mode, there could be human reable text after the raw chars
    // so separate them
    int idx = text.indexOf('\uffff');
    if (idx >= 0)
      text = text.substring(0, idx);

    // Calculate checksum
    int chk = text.charAt(0);
    for (int k = 1; k < text.length(); ++k)
      chk += k * text.charAt(k);
    chk = chk % 103;
    text += (char) chk;

    // Generate the bars
    byte[] bars = new byte[(text.length()) * 6 + 7];
    int k;
    for (k = 0; k < text.length(); ++k)
      System.arraycopy(BARS[text.charAt(k)], 0, bars, k * 6, 6);

    // Add stop char
    System.arraycopy(BARS_STOP, 0, bars, k * 6, 7);
    return bars;
  }
}
