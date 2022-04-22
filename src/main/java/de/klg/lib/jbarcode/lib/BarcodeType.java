/* ----------------------------------------------------------------------------
 * JBarcode - https://github.com/borisklug/jbarcode
 *
 * Written by Boris Klug, https://klg.de/
 * Licensed under the Apache License Version 2.0
 * ---------------------------------------------------------------------------- */
package de.klg.lib.jbarcode.lib;

/**
 * @author Boris Klug
 */
public enum BarcodeType {

  EAN8, EAN13,

  CODE128, CODE128_RAW, CODE128_A,

  EAN128,

  CODE128_UCC, CODE25INTER;

  /*
   * not yet implemented : UPCA, UPCE, SUPP2, SUPPS, POSTNET, PLANET, CODABAR
   */

  @Override
  public String toString() {
    switch (this) {
    case CODE128_A:
      return "code128A";
    case CODE25INTER:
      return "2/5 interleave";
    default:
      return name();
    }
  }

}
