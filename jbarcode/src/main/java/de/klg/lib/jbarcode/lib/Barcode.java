/*
 * JBarcode - a Java package for drawing barcodes.
 *
 * Inspired by the barcode component of iText
 *
 * @author Boris Klug
 */
package de.klg.lib.jbarcode.lib;

/**
 * Base class for all barcode generator. It contains all properties and methods
 * common to all barcode.
 *
 * @author Boris Klug
 */
public abstract class Barcode {

  /**
   * The version number of the JBarcode lib. The int just increases by every
   * release. For a human readable version, see JBARCODE_VERSIONSTR
   */
  public static final int JBARCODE_VERSION = 1;

  /** A type of barcode */
  public static final int EAN13 = 1;

  /** A type of barcode */
  public static final int EAN8 = 2;

  /** A type of barcode */
  public static final int CODE128 = 3;

  /** A type of barcode */
  public static final int CODE128_RAW = 4;

  /** Code 128 with only charset A */
  public static final int CODE128_A = 5;

  /** A type of barcode */
  public static final int EAN128 = 6;

  /** A type of barcode */
  public static final int CODE128_UCC = 6; // same like EAN128

  /** A type of barcode */
  public static final int CODE25INTER = 7;

  /*
   * not yet implemented : public static final int UPCA = 99; public static final
   * int UPCE = 99; public static final int SUPP2 = 99; public static final int
   * SUPP5 = 99; public static final int POSTNET = 99; public static final int
   * PLANET = 99; public static final int CODABAR = 99;
   */
  /** Textalignment */
  public static final char ALIGN_LEFT = 1;
  public static final char ALIGN_RIGHT = 2;
  public static final char ALIGN_CENTER = 3;

  /**
   * Returns the type of the barcode as a string, e.g. "EAN13" or "code 128"
   */
  public String getBarcodetypename() {
    switch (codeType) {
    case EAN13:
      return "EAN13";
    case EAN8:
      return "EAN8";
    case CODE128:
      return "code128";
    case EAN128:
      return "EAN128";
    case CODE128_RAW:
      return "code128raw";
    case CODE128_A:
      return "code128A";
    case CODE25INTER:
      return "2/5 interleave";
    default:
      return "???";
    }
  }

  /**
   * The name of the font to use for writing the code below the barcode. If null,
   * the text is omitted
   */
  protected String fontname;

  /**
   * If true, draw the human readable text below or above the barcode
   */
  protected boolean drawtext = true;

  /**
   * The size of the text which displayes the code in human readable form
   */
  protected int fontsize = 10;

  /**
   * If positive, the text distance under the bars. If zero or negative, the text
   * distance above the bars.
   */
  protected int baseline;

  /**
   * The height of the bars in pixel
   */
  protected int barHeight;

  /**
   * The width of the smalles bars in pixel
   */
  protected int barWidth;

  /**
   * The The multiplyer for the relation small bar <-> wide bar. Normally it is 1
   *
   * IT IS NOT USED IN THIS VERSION!
   */
  protected int smallwideMultiplyer = 1;

  /**
   * The area left and right of the barcode which is supposed to stay white.
   * (German: Ruhezone). This area is supposed to be 10 * barWidth but at least
   * 2,5mm.
   */
  protected int preflightWidth;

  /**
   * The magnification factor for drawing the whole barcode. Everything is scaled
   * with this factor. For using non integer values here is for your own risk
   */
  protected float magnification = 1.0f;

  /**
   * The text alignment. Can be <CODE>Element.ALIGN_LEFT</CODE>,
   * <CODE>Element.ALIGN_CENTER</CODE> or <CODE>Element.ALIGN_RIGHT</CODE>.
   */
  protected int textAlignment = ALIGN_CENTER;

  /**
   * The code to generate.
   */
  protected String code = "";

  /**
   * Show the guard bars for barcode EAN.
   */
  protected boolean guardBars;

  /**
   * The code type, see constants at the start of this document
   */
  protected int codeType;

  /**
   * If true, use the array returned by getCharPos() to position the text below
   * the barcode. This is used e.g. for EAN13 / EAN8 enconding
   */
  protected boolean useCharPos;

  /**
   * If true, the human readable text is draw below or above the barcode If false,
   * no text is drawn
   */
  public boolean isDrawtext() {
    return drawtext;
  }

  /**
   * If set to true, the human readable text is draw below or above the barcode
   */
  public void setDrawtext(boolean drawtext) {
    this.drawtext = drawtext;
  }

  /**
   * Get the java font name for the text below the bar code
   * 
   * @return font name as a string
   */
  public String getFontname() {
    return fontname;
  }

  /**
   * Set the java font name for the text below the bar code. Set to null for no
   * text
   * 
   * @param fontname The name of the Java font.
   */
  public void setFontname(String fontname) {
    this.fontname = fontname;
  }

  /**
   * Gets the size of the text.
   * 
   * @return the size of the text
   */
  public int getFontsize() {
    return fontsize;
  }

  /**
   * Sets the size of the text.
   * 
   * @param size the size of the text
   */
  public void setFontsize(int fontsize) {
    this.fontsize = fontsize;
  }

  /**
   * Gets the text baseline. If positive, the text distance under the bars. If
   * zero or negative, the text distance above the bars.
   * 
   * @return the baseline.
   */
  public int getBaseline() {
    return baseline;
  }

  /**
   * Sets the text baseline. If positive, the text distance under the bars. If
   * zero or negative, the text distance above the bars.
   * 
   * @param baseline the baseline.
   */
  public void setBaseline(int baseline) {
    this.baseline = baseline;
  }

  /**
   * Gets the height of the bars.
   * 
   * @return the height of the bars
   */
  public int getBarHeight() {
    return barHeight;
  }

  /**
   * Sets the height of the bars.
   * 
   * @param barHeight the height of the bars
   */
  public void setBarHeight(int barHeight) {
    this.barHeight = barHeight;
  }

  /**
   * Returns the width of the preflight area
   * 
   * @return the width of the preflight area
   */
  public int getPreflightWidth() {
    return preflightWidth;
  }

  /**
   * Set the preflight width
   */
  public void setPreflightWidth(int preflightWidth) {
    this.preflightWidth = preflightWidth;
  }

  /**
   * Gets the width of the smalles bar in pixel
   * 
   * @return the width of the smalles bar in pixel
   */
  public int getBarWidth() {
    return barWidth;
  }

  /**
   * Sets the width of the smalles bar in pixel
   */
  public void setBarWidth(int barWidth) {
    this.barWidth = barWidth;
  }

  /**
   * If true, the array returned by getCharPos() is used for text drawing
   */
  public boolean isUseCharPos() {
    return useCharPos;
  }

  /**
   * Set the state for special char drawing
   */
  public void setUseCharPos(boolean useCharPos) {
    this.useCharPos = useCharPos;
  }

  /**
   * Get the relation between small bars and wide bars. Normally 1
   */
  public int getSmallwideMultiplyer() {
    return smallwideMultiplyer;
  }

  /**
   * Set the relation between small bars and wide bars. Normally 1
   */
  public void setSmallwideMultiplyer(int smallwideMultiplyer) {
    this.smallwideMultiplyer = smallwideMultiplyer;
  }

  /**
   * Gets the text alignment. Can be <CODE>Element.ALIGN_LEFT</CODE>,
   * <CODE>Element.ALIGN_CENTER</CODE> or <CODE>Element.ALIGN_RIGHT</CODE>.
   * 
   * @return the text alignment
   */
  public int getTextAlignment() {
    return textAlignment;
  }

  /**
   * Sets the text alignment. Can be <CODE>Element.ALIGN_LEFT</CODE>,
   * <CODE>Element.ALIGN_CENTER</CODE> or <CODE>Element.ALIGN_RIGHT</CODE>.
   * 
   * @param textAlignment the text alignment
   */
  public void setTextAlignment(int textAlignment) {
    this.textAlignment = textAlignment;
  }

  /**
   * Gets the code to generate.
   * 
   * @return the code to generate
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets the code to generate.
   * 
   * @param code the code to generate
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Gets the property to show the guard bars for barcode EAN.
   * 
   * @return value of property guardBars
   */
  public boolean isGuardBars() {
    return guardBars;
  }

  /**
   * Sets the property to show the guard bars for barcode EAN.
   * 
   * @param guardBars new value of property guardBars
   */
  public void setGuardBars(boolean guardBars) {
    this.guardBars = guardBars;
  }

  /**
   * Gets the code type.
   * 
   * @return the code type
   */
  public int getCodeType() {
    return codeType;
  }

  /**
   * Sets the code type.
   * 
   * @param codeType the code type
   */
  public void setCodeType(int codeType) {
    this.codeType = codeType;
  }

  /**
   * Gets the magnification facotr
   */
  public float getMagnification() {
    return magnification;
  }

  /**
   * Sets the maginication factor
   */
  public void setMagnification(float magnification) {
    this.magnification = magnification;
  }

  /**
   * Converts the code text to the raw text needed for the barcode.
   *
   * E.g. in code128, the selection of subsets and the compression for numbers in
   * subset C is done here. In most barcodes, just the normal text is coded so no
   * translation is done.
   */
  public String toRawText(String text) {
    return text;
  }

  /**
   * Converts the code text to human readable text. Used for printing the code
   * text under the barcode. E.g. in EAN128, the implementation could mark field
   * limiters with brackets, e.g. (00)123293(01)99332
   */
  public String toHumanText(String text) {
    return text;
  }

  /**
   * Returns the bars which represents the text of the barcode. For some barcode
   * (e.g. code 128), the text is converted before the bars are calculated (e.g.
   * for different subsets).
   */
  public abstract byte[] getBars();

  /**
   * Returns the sum of all bars in this array. Used for calculating the width of
   * the barcode
   *
   * @return the sum of all bars in the array
   */
  public int getBarsum(byte[] bars) {
    int sum = 0;
    for (int k = 0; k < bars.length; ++k) {
      sum = sum + bars[k];
    }
    return sum;
  }

  /**
   * Get the position of the guard bars (if any)
   */
  public int[] getGuardBarsArray() {
    return new int[0];
  }

  /**
   * Get the position for special text drawing like in EAN13. For special text
   * drawing, "useCharPos" has to be true
   *
   * x => the left side of the barcode (excluding the preflight area) y => the
   * baseline where normal text drawing would start
   */
  public BarcodePoint[] getCharPosArray() {
    return new BarcodePoint[0];
  }

  /**
   * Get the position of the guard bars (if any). The position is given as an int
   * array, first bar is 0.
   *
   * @return int array with the guard bars
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Class: ");
    sb.append(this.getClass().getSimpleName());
    sb.append(" - , barcode type = " + getBarcodetypename());
    sb.append("\ncode = '" + code + "'");

    byte[] bars;
    try {
      bars = getBars();
    } catch (Exception ex) {
      sb.append("\ncant generate bars.");
      return sb.toString();
    }

    for (int k = 0; k < bars.length; ++k) {
      sb.append("\n bar " + (k + 1) + " -> " + bars[k]);
    }

    return sb.toString();
  }

}
