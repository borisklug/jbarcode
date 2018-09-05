/*
 * JBarcode - a Java package for drawing barcodes.
 *
 * Inspired by the barcode component of iText
 *
 * @author Boris Klug
 */
package de.klg.lib.jbarcode.lib;

/**
 * Exception thrown while generation or drawing the barcode
 *
 * @author Boris Klug
 */
public class BarcodeException extends Exception {

  /**
   * Constructor of a barcode exception
   *
   * @param msg message of the exception
   */
  public BarcodeException(String msg) {
    super(msg);
  }
}