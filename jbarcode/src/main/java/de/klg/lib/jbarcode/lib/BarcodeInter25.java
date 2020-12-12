/* ----------------------------------------------------------------------------
 * JBarcode - https://github.com/borisklug/jbarcode
 *
 * Written by Boris Klug, https://klg.de/
 * Licensed under the Apache License Version 2.0
 * ---------------------------------------------------------------------------- */
package de.klg.lib.jbarcode.lib;

/**
 * This barcode class renders the barcode type "Code 2/5 Interleave".
 *
 * Code 2/5 interleave is a two-width code. A small width is represented as a
 * "1" in the bars[] array (see getBars()), a wide bar is represented by "2"
 *
 * @author Boris Klug
 */
public class BarcodeInter25 extends Barcode {

  /**
   * The bars to generate the code.
   */
  static final byte[][] BARS = { { 1, 1, 2, 2, 1 }, { 2, 1, 1, 1, 2 }, { 1, 2, 1, 1, 2 }, { 2, 2, 1, 1, 1 },
      { 1, 1, 2, 1, 2 }, { 2, 1, 2, 1, 1 }, { 1, 2, 2, 1, 1 }, { 1, 1, 1, 2, 2 }, { 2, 1, 1, 2, 1 },
      { 1, 2, 1, 2, 1 } };

  /**
   * Creates new BarcodeInter25
   */
  public BarcodeInter25() {
    fontsize = 8;
    baseline = fontsize;
    barHeight = fontsize * 3;
    barWidth = 1;
    smallwideMultiplier = 3;
    preflightWidth = 10 * barWidth;
    codeType = BarcodeType.CODE25INTER;
    code = "4001513000620";
  }

  /**
   * Deletes all the non numeric characters from <CODE>text</CODE>.
   * 
   * @param text the text
   * @return a <CODE>String</CODE> with only numeric characters
   */
  public static String keepNumbers(String text) {
    StringBuilder sb = new StringBuilder();
    for (int k = 0; k < text.length(); ++k) {
      char c = text.charAt(k);
      if (c >= '0' && c <= '9') {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  /**
   * The raw text for this barcode is just the text with only numbers
   */
  @Override
  public String toRawText(String text) {
    return keepNumbers(text);
  }

  /**
   * Calculates the checksum.
   *
   * @param text the numeric text
   */
  public static char getChecksum(String text) {
    int mul = 3;
    int total = 0;
    for (int k = text.length() - 1; k >= 0; --k) {
      int n = text.charAt(k) - '0';
      total += mul * n;
      mul ^= 2;
    }
    return (char) (((10 - (total % 10)) % 10) + '0');
  }

  /**
   * Creates the bars for the barcode.
   *
   * @param text the text. It can contain non numeric characters, they are
   *             stripped before processing
   * @return the barcode
   */
  @Override
  public byte[] getBars() {
    String text = toRawText(code);
    text = keepNumbers(text);
    byte[] bars = new byte[text.length() * 5 + 7];
    int pb = 0;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    int len = text.length() / 2;
    for (int k = 0; k < len; ++k) {
      int c1 = text.charAt(k * 2) - '0';
      int c2 = text.charAt(k * 2 + 1) - '0';
      byte[] b1 = BARS[c1];
      byte[] b2 = BARS[c2];
      for (int j = 0; j < 5; ++j) {
        bars[pb++] = b1[j];
        bars[pb++] = b2[j];
      }
    }
    bars[pb++] = 2;
    bars[pb++] = 1;
    bars[pb] = 1;
    return bars;
  }

}
