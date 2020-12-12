/* ----------------------------------------------------------------------------
 * JBarcode - https://github.com/borisklug/jbarcode
 *
 * Written by Boris Klug, https://klg.de/
 * Licensed under the Apache License Version 2.0
 * ---------------------------------------------------------------------------- */
package de.klg.lib.jbarcode.app;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A small sample swing application which demonstrate the use of JBarcode
 * classes
 *
 * @author Boris Klug
 */
public class JBarcodeSampleApp {

  // Construct the application
  public JBarcodeSampleApp() {
    JBarcodeSampleframe frame = new JBarcodeSampleframe();

    frame.validate();

    // Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  // Main method - just get a new application object
  public static void main(String[] args) {
    new JBarcodeSampleApp();
  }
}