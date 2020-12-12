/* ----------------------------------------------------------------------------
 * JBarcode - https://github.com/borisklug/jbarcode
 *
 * Written by Boris Klug, https://klg.de/
 * Licensed under the Apache License Version 2.0
 * ---------------------------------------------------------------------------- */
package de.klg.lib.jbarcode.lib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for barcode 128 generation
 *
 * @author Boris Klug
 */
public class Barcode128Test {

  @Test
  public void testGetBarsCode128() {
    Barcode128 bc128 = new Barcode128();
    bc128.setCode("01040123453333361503123110123456");
    bc128.setCodeType(BarcodeType.CODE128);

    byte[] expected = { 2, 1, 1, 2, 3, 2, 2, 2, 2, 1, 2, 2, 1, 2, 1, 3, 2, 2, 2, 2, 2, 1, 2, 2, 3, 1, 2, 1, 3, 1, 1, 1,
        3, 1, 2, 3, 1, 1, 1, 3, 2, 3, 1, 1, 1, 3, 2, 3, 1, 1, 2, 3, 1, 3, 1, 1, 3, 2, 2, 2, 1, 2, 1, 2, 2, 3, 1, 1, 2,
        2, 3, 2, 2, 1, 2, 3, 2, 1, 2, 2, 1, 3, 1, 2, 1, 1, 2, 2, 3, 2, 1, 3, 1, 1, 2, 3, 3, 3, 1, 1, 2, 1, 2, 2, 3, 1,
        1, 2, 2, 3, 3, 1, 1, 1, 2 };
    byte[] actual = bc128.getBars();
    Assertions.assertArrayEquals(expected, actual);
  }
}
