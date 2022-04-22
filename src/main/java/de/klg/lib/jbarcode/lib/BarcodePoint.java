/* ----------------------------------------------------------------------------
 * JBarcode - https://github.com/borisklug/jbarcode
 *
 * Written by Boris Klug, https://klg.de/
 * Licensed under the Apache License Version 2.0
 * ---------------------------------------------------------------------------- */
package de.klg.lib.jbarcode.lib;

/**
 * This simple class is similar to java.awt.Point - we use BarcodePoint instead
 * so the core JBarcode classes do not rely on AWT and/or Swing.
 *
 * @author Boris Klug
 */
public class BarcodePoint {

  private float x;
  private float y;

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public BarcodePoint(float x, float y) {
    this.x = x;
    this.y = y;
  }

}
