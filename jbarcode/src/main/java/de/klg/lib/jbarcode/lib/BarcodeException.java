/* ----------------------------------------------------------------------------
 * JBarcode - https://github.com/borisklug/jbarcode
 *
 * Written by Boris Klug, https://klg.de/
 * Licensed under the Apache License Version 2.0
 * ---------------------------------------------------------------------------- */
package de.klg.lib.jbarcode.lib;

/**
 * Exception thrown while generation or drawing the barcode
 *
 * @author Boris Klug
 */
@SuppressWarnings("serial")
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