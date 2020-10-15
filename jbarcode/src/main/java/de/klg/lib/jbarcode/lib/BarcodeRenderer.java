/*
 * JBarcode - a Java package for drawing barcodes.
 *
 * Inspired by the barcode component of iText
 *
 * @author Boris Klug
 */
package de.klg.lib.jbarcode.lib;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * This static class renders a barcode in a buffered image. It also provides a
 * method for writing this generated image to a jpg file (JDK 1.4 needed).
 *
 * ToDo: 1) Drawing the text above the barcode (baseline < 0)
 *
 * @author Boris Klug
 */
public class BarcodeRenderer {

  /**
   * Returns the buffered image with the rendered barcode
   */
  public static BufferedImage getBarcodeBufimage(Barcode bc) throws BarcodeException {
    return getBarcodeBufimage(bc, null);
  }

  /**
   * Returns the buffered image with the rendered barcode but replace the text
   * below (or above) the barcode with the given replacementtext
   */
  public static BufferedImage getBarcodeBufimage(Barcode bc, String replacementtext) throws BarcodeException {
    // If no barcode is present, just draw this text
    if (bc == null) {
      throw new BarcodeException("not barcode given to draw");
    }

    // The maginication factor. We cast to int here
    int mag = (int) bc.getMagnification();

    int fontbaseline = bc.getBaseline();
    String fontname = bc.getFontname();
    int x = bc.getPreflightWidth();
    int y = 1;

    // text has to be placed below the barcode, move barcode to south
    if (bc.isDrawtext() && fontbaseline <= 0) {
      y = 1 + fontbaseline + bc.getFontsize();
    }

    // Get the barcode bars
    byte[] bars = bc.getBars();
    if (bars == null) {
      throw new BarcodeException("cant generate bars for barcode");
    }

    // Here we have to calculate the size of the barcode and
    // get the bufimage according to this
    int imgwidth = (bc.getBarsum(bars) * bc.getBarWidth()) + (2 * bc.getPreflightWidth());
    int imgheight;
    if (bc.isDrawtext()) { // draw text
      imgheight = bc.getBarHeight() + 2 + bc.getBaseline();
    } else { // do not draw text
      imgheight = bc.getBarHeight() + 2;
    }

    // Create buffered image and the graphics2D
    BufferedImage bufimage = new BufferedImage(imgwidth * mag, imgheight * mag, BufferedImage.TYPE_BYTE_GRAY);
    Graphics2D g = bufimage.createGraphics();

    // Setup colors
    g.setColor(java.awt.Color.black);
    g.setBackground(java.awt.Color.white);
    g.clearRect(0, 0, imgwidth * mag, imgheight * mag);

    // Draw the barcode
    boolean print = true;
    int[] guard = bc.getGuardBarsArray(); // guard bars if any
    int gd = 0;
    if (bc.isGuardBars() && bc.isDrawtext() && fontbaseline > 0) {
      gd = fontbaseline / 2;
    }

    int bcx = 0;
    int barwidth = bc.getBarWidth();
    int barheight = bc.getBarHeight();
    for (int k = 0; k < bars.length; ++k) {
      int barvalue = bars[k];
      if (print) {
        if (Arrays.binarySearch(guard, k) >= 0) {
          g.fillRect((x + bcx) * mag, y, (barvalue * barwidth) * mag, (y + barheight + gd) * mag);
        } else {
          g.fillRect((x + bcx) * mag, y, (barvalue * barwidth) * mag, (y + barheight) * mag);
        }
      }
      bcx = bcx + bars[k];
      print = !print;
    }

    // When text is not wanted -> thats all
    if (!bc.isDrawtext()) {
      return bufimage;
    }

    // Check the font (if text daring is desired)
    Font font = new Font(fontname, Font.PLAIN, bc.getFontsize() * mag);
    FontMetrics fontmetric = g.getFontMetrics(font);

    // Draw the text below the barcode
    g.setFont(font);
    String texttodraw;
    if (replacementtext != null) {
      texttodraw = replacementtext;
    } else {
      texttodraw = bc.toHumanText(bc.getCode());
    }
    int txtwidth = fontmetric.stringWidth(texttodraw);
    int txtx = 0;

    // find the right x position for the text
    switch (bc.getTextAlignment()) {
    case Barcode.ALIGN_LEFT:
      txtx = x * mag;
      break;
    case Barcode.ALIGN_RIGHT:
      txtx = ((x + bcx) * mag) - txtwidth;
      break;
    default: // center
      txtx = ((x + (bcx / 2)) * mag) - (txtwidth / 2);
      break;
    }

    // find the y position
    int txty;
    if (bc.getBaseline() < 0) { // above the barcode
      txty = (y + bc.getBaseline()) * mag; // bc.getBaseline() < 0 !
    } else { // below the barcode
      txty = (y + bc.getBarHeight() + bc.getBaseline()) * mag;
    }

    // No special char positioning, just draw the string
    if (!bc.isUseCharPos()) {
      g.drawString(texttodraw, txtx, txty);
      return bufimage;
    }

    // Special char positining, e.g. for EAN13
    BarcodePoint[] charpos = bc.getCharPosArray();

    for (int i = 0; i < texttodraw.length(); i++) {
      g.drawString(texttodraw.substring(i + 0, i + 1), (txtx + mag * (int) charpos[i].getX()),
          (txty + mag * (int) charpos[i].getY()));

    }
    return bufimage;
  }

  /**
   * Export the barcode to a jpg file. png would be better
   *
   * @return 0 if no error occures, !=0 in case of an error
   */
  public static void exportJpg(Barcode bc, String filename) throws BarcodeException {
    File out = new File(filename);
    BufferedImage image = getBarcodeBufimage(bc);
    try {
      ImageIO.write(image, "jpg", out);
    } catch (Exception ex) {
      throw new BarcodeException("Cant export to file '" + filename + "'");
    }
  }

}
