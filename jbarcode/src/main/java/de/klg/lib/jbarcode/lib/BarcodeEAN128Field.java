/*
 * JBarcode - a Java package for drawing barcodes.
 *
 * Inspired by the barcode component of iText
 *
 * @author Boris Klug
 */
package de.klg.lib.jbarcode.lib;

/**
 * This class represents one EAN128 field, e.g. "00" for NVE
 *
 * @author Boris Klug
 */
public class BarcodeEAN128Field {
  /**
   * Field descriptor, is a 2,3 or 4 chars number, e.g. "01"
   */
  private String field = "";
  /**
   * A info about this field, e.g. "EAN der Handelseinheit"
   */
  private String fieldinfo = "";
  /**
   * Length of the content of the field if len is fixed, otherwise maximal lenfth
   * of the content
   */
  private int contentlen;
  /**
   * True if the content can only contain numbers false if alphanumerical chars
   * and numbers are allowed
   */
  private boolean contentonlynr;
  /**
   * True if the length of the contents is fix, false if length of contents is
   * variable
   */
  private boolean fixcontentlen;

  /**
   * Constructor
   */
  public BarcodeEAN128Field(String field, String fieldinfo, int contentlen, boolean contentonlynr,
      boolean fixcontentlen) {

    this.field = field;
    this.fieldinfo = fieldinfo;
    this.contentlen = contentlen;
    this.contentonlynr = contentonlynr;
    this.fixcontentlen = fixcontentlen;
  }

  public String getField() {
    return field;
  }

  public String getFieldinfo() {
    return fieldinfo;
  }

  public int getContentlen() {
    return contentlen;
  }

  public boolean getContentonlynr() {
    return contentonlynr;
  }

  public boolean getFixcontentlen() {
    return fixcontentlen;
  }
}
