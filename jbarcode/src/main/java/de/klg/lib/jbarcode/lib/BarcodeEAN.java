/*
 * JBarcode - a Java package for drawing barcodes.
 *
 * Inspired by the barcode component of iText
 *
 * @author Boris Klug
 */
package de.klg.lib.jbarcode.lib;

/**
 * Barcode generator class for the following barcodes:
 *
 * EAN 13 EAN 8
 *
 * In the near future: UPCA, UPCE, suplemental 2 and 5
 *
 * @author Boris Klug
 */
public class BarcodeEAN extends Barcode {

  /** The bar positions that are guard bars. */
  static final int[] GUARD_EMPTY = {};
  /** The bar positions that are guard bars. */
  static final int[] GUARD_UPCA = { 0, 2, 4, 6, 28, 30, 52, 54, 56, 58 };
  /** The bar positions that are guard bars. */
  static final int[] GUARD_EAN13 = { 0, 2, 28, 30, 56, 58 };
  /** The bar positions that are guard bars. */
  static final int[] GUARD_EAN8 = { 0, 2, 20, 22, 40, 42 };
  /** The bar positions that are guard bars. */
  static final int[] GUARD_UPCE = { 0, 2, 28, 30, 32 };
  /** The basic bar widths. */
  static final byte[][] BARS = { { 3, 2, 1, 1 }, // 0
      { 2, 2, 2, 1 }, // 1
      { 2, 1, 2, 2 }, // 2
      { 1, 4, 1, 1 }, // 3
      { 1, 1, 3, 2 }, // 4
      { 1, 2, 3, 1 }, // 5
      { 1, 1, 1, 4 }, // 6
      { 1, 3, 1, 2 }, // 7
      { 1, 2, 1, 3 }, // 8
      { 3, 1, 1, 2 } // 9
  };

  /** The total number of bars for EAN13. */
  static final int TOTALBARS_EAN13 = 11 + 12 * 4;
  /** The total number of bars for EAN8. */
  static final int TOTALBARS_EAN8 = 11 + 8 * 4;
  /** The total number of bars for UPCE. */
  static final int TOTALBARS_UPCE = 9 + 6 * 4;
  /** The total number of bars for supplemental 2. */
  static final int TOTALBARS_SUPP2 = 13;
  /** The total number of bars for supplemental 5. */
  static final int TOTALBARS_SUPP5 = 31;
  /** Marker for odd parity. */
  static final int ODD = 0;
  /** Marker for even parity. */
  static final int EVEN = 1;

  /** Sequence of parities to be used with EAN13. */
  static byte[][] PARITY13 = { { ODD, ODD, ODD, ODD, ODD, ODD }, // 0
      { ODD, ODD, EVEN, ODD, EVEN, EVEN }, // 1
      { ODD, ODD, EVEN, EVEN, ODD, EVEN }, // 2
      { ODD, ODD, EVEN, EVEN, EVEN, ODD }, // 3
      { ODD, EVEN, ODD, ODD, EVEN, EVEN }, // 4
      { ODD, EVEN, EVEN, ODD, ODD, EVEN }, // 5
      { ODD, EVEN, EVEN, EVEN, ODD, ODD }, // 6
      { ODD, EVEN, ODD, EVEN, ODD, EVEN }, // 7
      { ODD, EVEN, ODD, EVEN, EVEN, ODD }, // 8
      { ODD, EVEN, EVEN, ODD, EVEN, ODD } // 9
  };

  /** Sequence of parities to be used with supplemental 2. */
  static byte[][] PARITY2 = { { ODD, ODD }, // 0
      { ODD, EVEN }, // 1
      { EVEN, ODD }, // 2
      { EVEN, EVEN } // 3
  };

  /** Sequence of parities to be used with supplemental 2. */
  static byte[][] PARITY5 = { { EVEN, EVEN, ODD, ODD, ODD }, // 0
      { EVEN, ODD, EVEN, ODD, ODD }, // 1
      { EVEN, ODD, ODD, EVEN, ODD }, // 2
      { EVEN, ODD, ODD, ODD, EVEN }, // 3
      { ODD, EVEN, EVEN, ODD, ODD }, // 4
      { ODD, ODD, EVEN, EVEN, ODD }, // 5
      { ODD, ODD, ODD, EVEN, EVEN }, // 6
      { ODD, EVEN, ODD, EVEN, ODD }, // 7
      { ODD, EVEN, ODD, ODD, EVEN }, // 8
      { ODD, ODD, EVEN, ODD, EVEN } // 9
  };

  /** Sequence of parities to be used with UPCE. */
  static byte[][] PARITYE = { { EVEN, EVEN, EVEN, ODD, ODD, ODD }, // 0
      { EVEN, EVEN, ODD, EVEN, ODD, ODD }, // 1
      { EVEN, EVEN, ODD, ODD, EVEN, ODD }, // 2
      { EVEN, EVEN, ODD, ODD, ODD, EVEN }, // 3
      { EVEN, ODD, EVEN, EVEN, ODD, ODD }, // 4
      { EVEN, ODD, ODD, EVEN, EVEN, ODD }, // 5
      { EVEN, ODD, ODD, ODD, EVEN, EVEN }, // 6
      { EVEN, ODD, EVEN, ODD, EVEN, ODD }, // 7
      { EVEN, ODD, EVEN, ODD, ODD, EVEN }, // 8
      { EVEN, ODD, ODD, EVEN, ODD, EVEN } // 9
  };

  /** Creates new BarcodeEAN */
  public BarcodeEAN() {
    fontname = "SanSerif";
    fontsize = 9;
    baseline = fontsize;
    barWidth = 1;
    barHeight = fontsize * 3;
    preflightWidth = 10 * barWidth;
    guardBars = true;
    useCharPos = true;
    textAlignment = ALIGN_LEFT;
    codeType = EAN13;
    code = "4001513000620"; // Gerolsteiner Stille Quelle
  }

  /**
   * Calculates the EAN parity character.
   * 
   * @param code the code
   * @return the parity character
   */
  public static int calculateEANParity(String code) {
    int mul = 3;
    int total = 0;
    for (int k = code.length() - 1; k >= 0; --k) {
      int n = code.charAt(k) - '0';
      total += mul * n;
      mul ^= 2;
    }
    return (10 - (total % 10)) % 10;
  }

  /**
   * Converts an UPCA code into an UPCE code. If the code can not be converted a
   * <CODE>null</CODE> is returned.
   * 
   * @param text the code to convert. It must have 12 numeric characters
   * @return the 8 converted digits or <CODE>null</CODE> if the code could not be
   *         converted
   */
  public static String convertUPCAtoUPCE(String text) {
    if (text.length() != 12 || !(text.startsWith("0") || text.startsWith("1")))
      return null;
    if (text.substring(3, 6).equals("000") || text.substring(3, 6).equals("100")
        || text.substring(3, 6).equals("200")) {
      if (text.substring(6, 8).equals("00"))
        return text.substring(0, 1) + text.substring(1, 3) + text.substring(8, 11) + text.substring(3, 4)
            + text.substring(11);
    } else if (text.substring(4, 6).equals("00")) {
      if (text.substring(6, 9).equals("000"))
        return text.substring(0, 1) + text.substring(1, 4) + text.substring(9, 11) + "3" + text.substring(11);
    } else if (text.substring(5, 6).equals("0")) {
      if (text.substring(6, 10).equals("0000"))
        return text.substring(0, 1) + text.substring(1, 5) + text.substring(10, 11) + "4" + text.substring(11);
    } else if (text.charAt(10) >= '5') {
      if (text.substring(6, 10).equals("0000"))
        return text.substring(0, 1) + text.substring(1, 6) + text.substring(10, 11) + text.substring(11);
    }
    return null;
  }

  /**
   * Creates the bars for the barcode EAN13 and UPCA.
   *
   * @return the barcode
   */
  public byte[] getBarsEAN13() {
    String _code = toRawText(code);

    int code[] = new int[_code.length()];
    for (int k = 0; k < code.length; ++k) {
      code[k] = _code.charAt(k) - '0';
    }

    byte[] bars = new byte[TOTALBARS_EAN13];
    int pb = 0;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    byte[] sequence = PARITY13[code[0]];
    for (int k = 0; k < sequence.length; ++k) {
      int c = code[k + 1];
      byte[] stripes = BARS[c];
      if (sequence[k] == ODD) {
        bars[pb++] = stripes[0];
        bars[pb++] = stripes[1];
        bars[pb++] = stripes[2];
        bars[pb++] = stripes[3];
      } else {
        bars[pb++] = stripes[3];
        bars[pb++] = stripes[2];
        bars[pb++] = stripes[1];
        bars[pb++] = stripes[0];
      }
    }
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    for (int k = 7; k < 13; ++k) {
      int c = code[k];
      byte[] stripes = BARS[c];
      bars[pb++] = stripes[0];
      bars[pb++] = stripes[1];
      bars[pb++] = stripes[2];
      bars[pb++] = stripes[3];
    }
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb] = 1;
    return bars;
  }

  /**
   * Creates the bars for the barcode EAN8.
   *
   * @return the barcode
   */
  public byte[] getBarsEAN8() {
    String _code = toRawText(code);
    int[] code = new int[_code.length()];
    for (int k = 0; k < code.length; ++k) {
      code[k] = _code.charAt(k) - '0';
    }
    byte[] bars = new byte[TOTALBARS_EAN8];
    int pb = 0;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    for (int k = 0; k < 4; ++k) {
      int c = code[k];
      byte[] stripes = BARS[c];
      bars[pb++] = stripes[0];
      bars[pb++] = stripes[1];
      bars[pb++] = stripes[2];
      bars[pb++] = stripes[3];
    }
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    for (int k = 4; k < 8; ++k) {
      int c = code[k];
      byte[] stripes = BARS[c];
      bars[pb++] = stripes[0];
      bars[pb++] = stripes[1];
      bars[pb++] = stripes[2];
      bars[pb++] = stripes[3];
    }
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb] = 1;
    return bars;
  }

  /**
   * Creates the bars for the barcode UPCE.
   *
   * @return the barcode
   */
  public byte[] getBarsUPCE() {
    String _code = toRawText(code);
    int[] code = new int[_code.length()];
    for (int k = 0; k < code.length; ++k) {
      code[k] = _code.charAt(k) - '0';
    }

    byte[] bars = new byte[TOTALBARS_UPCE];
    boolean flip = (code[0] != 0);
    int pb = 0;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    byte[] sequence = PARITYE[code[code.length - 1]];
    for (int k = 1; k < code.length - 1; ++k) {
      int c = code[k];
      byte[] stripes = BARS[c];
      if (sequence[k - 1] == (flip ? EVEN : ODD)) {
        bars[pb++] = stripes[0];
        bars[pb++] = stripes[1];
        bars[pb++] = stripes[2];
        bars[pb++] = stripes[3];
      } else {
        bars[pb++] = stripes[3];
        bars[pb++] = stripes[2];
        bars[pb++] = stripes[1];
        bars[pb++] = stripes[0];
      }
    }
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb] = 1;
    return bars;
  }

  /**
   * Creates the bars for the barcode supplemental 2.
   *
   * @return the barcode
   */
  public byte[] getBarsSupplemental2() {
    String _code = toRawText(code);
    int code[] = new int[2];
    for (int k = 0; k < code.length; ++k)
      code[k] = _code.charAt(k) - '0';
    byte bars[] = new byte[TOTALBARS_SUPP2];
    int pb = 0;
    int parity = (code[0] * 10 + code[1]) % 4;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 2;
    byte sequence[] = PARITY2[parity];
    for (int k = 0; k < sequence.length; ++k) {
      if (k == 1) {
        bars[pb++] = 1;
        bars[pb++] = 1;
      }
      int c = code[k];
      byte stripes[] = BARS[c];
      if (sequence[k] == ODD) {
        bars[pb++] = stripes[0];
        bars[pb++] = stripes[1];
        bars[pb++] = stripes[2];
        bars[pb++] = stripes[3];
      } else {
        bars[pb++] = stripes[3];
        bars[pb++] = stripes[2];
        bars[pb++] = stripes[1];
        bars[pb++] = stripes[0];
      }
    }
    return bars;
  }

  /**
   * Creates the bars for the barcode supplemental 5.
   *
   * @param _code the text with 5 digits
   * @return the barcode
   */
  public static byte[] getBarsSupplemental5(String theCode) {
    int[] code = new int[5];
    for (int k = 0; k < code.length; ++k) {
      code[k] = theCode.charAt(k) - '0';
    }

    byte[] bars = new byte[TOTALBARS_SUPP5];
    int pb = 0;
    int parity = (((code[0] + code[2] + code[4]) * 3) + ((code[1] + code[3]) * 9)) % 10;
    bars[pb++] = 1;
    bars[pb++] = 1;
    bars[pb++] = 2;
    byte sequence[] = PARITY5[parity];
    for (int k = 0; k < sequence.length; ++k) {
      if (k != 0) {
        bars[pb++] = 1;
        bars[pb++] = 1;
      }
      int c = code[k];
      byte stripes[] = BARS[c];
      if (sequence[k] == ODD) {
        bars[pb++] = stripes[0];
        bars[pb++] = stripes[1];
        bars[pb++] = stripes[2];
        bars[pb++] = stripes[3];
      } else {
        bars[pb++] = stripes[3];
        bars[pb++] = stripes[2];
        bars[pb++] = stripes[1];
        bars[pb++] = stripes[0];
      }
    }
    return bars;
  }

  /**
   * Return the bars of an EAN13 or EAN8 barcode
   *
   * @return a byte array this the widths of the bars or null when something when
   *         wrong
   */
  @Override
  public byte[] getBars() {
    if (codeType == EAN13) {
      if (code.length() != 13) {
        return null;
      }
      return getBarsEAN13();

    } else if (codeType == EAN8) {
      if (code.length() != 8) {
        return null;
      }
      return getBarsEAN8();
    }

    return null; // cant generate this bars
  }

  /**
   * Get the position of the guard bars (if any). The position is given as an int
   * array, first bar is 0.
   *
   * @return int array with the guard bars
   */
  @Override
  public int[] getGuardBarsArray() {
    if (codeType == EAN13) {
      return GUARD_EAN13;
    } else if (codeType == EAN8) {
      return GUARD_EAN8;
    } else {
      return GUARD_EMPTY;
    }
  }

  /**
   * Returns a hashmap with the positions of each character. The two-dim array
   * describes the x/y coordinate of the character. The point of origin is:
   *
   * x => the left side of the barcode (excluding the preflight area) y => the
   * baseline where normal text drawing would start
   *
   * For is only true if textAlignment is set to left!
   */
  @Override
  public BarcodePoint[] getCharPosArray() {

    BarcodePoint[] pos = new BarcodePoint[13];
    if (codeType == EAN13) {
      // 13 chars..
      int strech = 7;
      int x = 5;
      pos[0] = new BarcodePoint(-8, 0);
      pos[1] = new BarcodePoint(x + (strech * 0), 0);
      pos[2] = new BarcodePoint(x + (strech * 1), 0);
      pos[3] = new BarcodePoint(x + (strech * 2), 0);
      pos[4] = new BarcodePoint(x + (strech * 3), 0);
      pos[5] = new BarcodePoint(x + (strech * 4), 0);
      pos[6] = new BarcodePoint(x + (strech * 5), 0);

      x = 50;
      pos[7] = new BarcodePoint(x + (strech * 0), 0);
      pos[8] = new BarcodePoint(x + (strech * 1), 0);
      pos[9] = new BarcodePoint(x + (strech * 2), 0);
      pos[10] = new BarcodePoint(x + (strech * 3), 0);
      pos[11] = new BarcodePoint(x + (strech * 4), 0);
      pos[12] = new BarcodePoint(x + (strech * 5), 0);

    } else if (codeType == EAN8) {
      // 8 chars to go
      int strech = 7;
      int x = 4;
      pos[0] = new BarcodePoint(x + (strech * 0), 0);
      pos[1] = new BarcodePoint(x + (strech * 1), 0);
      pos[2] = new BarcodePoint(x + (strech * 2), 0);
      pos[3] = new BarcodePoint(x + (strech * 3), 0);

      x = 37;
      pos[4] = new BarcodePoint(x + (strech * 0), 0);
      pos[5] = new BarcodePoint(x + (strech * 1), 0);
      pos[6] = new BarcodePoint(x + (strech * 2), 0);
      pos[7] = new BarcodePoint(x + (strech * 3), 0);
    }

    return pos;
  }

}
