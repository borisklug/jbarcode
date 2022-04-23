/* ----------------------------------------------------------------------------
 * JBarcode - https://github.com/borisklug/jbarcode
 *
 * Written by Boris Klug, https://klg.de/
 * Licensed under the Apache License Version 2.0
 * ---------------------------------------------------------------------------- */
package de.klg.lib.jbarcode.app;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.klg.lib.jbarcode.lib.Barcode;
import de.klg.lib.jbarcode.lib.Barcode128;
import de.klg.lib.jbarcode.lib.BarcodeComponent;
import de.klg.lib.jbarcode.lib.BarcodeEAN;
import de.klg.lib.jbarcode.lib.BarcodeInter25;
import de.klg.lib.jbarcode.lib.BarcodeRenderer;
import de.klg.lib.jbarcode.lib.BarcodeType;

/**
 * The frame with the barcode and stuff of the sample application for JBarcode
 *
 * @author Boris Klug
 */
public class JBarcodeSampleframe extends JFrame {

  private JTextField jCodeTextfield = new JTextField();
  private JLabel jVersionlabel = new JLabel();

  private JMenuBar jMenuBar = new JMenuBar();
  private JMenu jMenuFile = new JMenu();
  private JMenuItem jMenuItemExport = new JMenuItem();
  private JMenuItem jMenuFileExit = new JMenuItem();
  private JMenu jMenuBarcode = new JMenu();

  private BarcodeComponent bcComponent = new BarcodeComponent();
  private JPanel jPanel1 = new JPanel();
  private JLabel jTypeLabel = new JLabel();

  private BorderLayout borderLayout1 = new BorderLayout();
  private JMenu jMenuProps = new JMenu();
  private JCheckBoxMenuItem jCheckBoxMenuItemDrawText = new JCheckBoxMenuItem();
  private JMenu jMenuMagn = new JMenu();
  private JMenu jMenuHelp = new JMenu();
  private JMenuItem jMenuItemAbout = new JMenuItem();
  private JMenuItem jMenuItemMag400 = new JMenuItem();
  private JMenuItem jMenuItemMag200 = new JMenuItem();
  private JMenuItem jMenuItemMag100 = new JMenuItem();

  private JRadioButtonMenuItem jMenuItemCode128 = new JRadioButtonMenuItem();
  private JRadioButtonMenuItem jMenuItemEAN128 = new JRadioButtonMenuItem();
  private JRadioButtonMenuItem jMenuItemEAN13 = new JRadioButtonMenuItem();
  private JRadioButtonMenuItem jMenuItemEAN8 = new JRadioButtonMenuItem();
  private JRadioButtonMenuItem jMenuItemInter25 = new JRadioButtonMenuItem();

  // This button group hold all barcode menus
  private ButtonGroup barcodegroup = new ButtonGroup();
  private JPanel jPanel3 = new JPanel();
  private JLabel jLabel1 = new JLabel();
  private BorderLayout borderLayout2 = new BorderLayout();
  private JMenu jMenuTextalign = new JMenu();
  private JMenuItem jMenuItemTextLeft = new JMenuItem();
  private JMenuItem jMenuItemTextCenter = new JMenuItem();
  private JMenuItem jMenuItemTextRight = new JMenuItem();
  private JMenu jMenuTextfont = new JMenu();
  JMenuItem jMenuItemPrint = new JMenuItem();

  /**
   * Constructor for this frame
   */
  public JBarcodeSampleframe() {

    try {
      enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      jbInit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Setup a barcode for the start
    Barcode bc = new Barcode128();
    bc.setCode("Boris Klug");
    bc.setCodeType(BarcodeType.CODE128);
    bc.setMagnification(2.0f);
    bcComponent.setBc(bc);
    jMenuItemCode128.setSelected(true);

    setupMainFrame();
  }

  // Component initialization
  private void jbInit() {
    JPanel contentPane = (JPanel) this.getContentPane();

    this.setSize(new Dimension(526, 309));
    this.setTitle("JBarcodeSampleApp");

    jCodeTextfield.setPreferredSize(new Dimension(80, 17));
    jCodeTextfield.setToolTipText("Please enter the code ");
    jCodeTextfield.setText("");

    jCodeTextfield.addActionListener(this::jCodeTextfieldActionPerformed);

    contentPane.setEnabled(true);
    jVersionlabel.setHorizontalAlignment(SwingConstants.RIGHT);
    jVersionlabel.setText("JBarcodeApp");

    jTypeLabel.setHorizontalAlignment(SwingConstants.LEFT);
    jPanel1.setLayout(borderLayout1);

    jMenuProps.setText("Properties");
    jCheckBoxMenuItemDrawText.setText("Draw Text");
    jCheckBoxMenuItemDrawText.addActionListener(this::jCheckBoxMenuItemDrawTextActionPerformed);
    jMenuMagn.setText("Magnification");
    jMenuHelp.setText("Help");
    jMenuItemAbout.setText("About");
    jMenuItemAbout.addActionListener(this::jMenuItemAboutActionPerformed);

    jMenuItemMag400.setText("400%");
    jMenuItemMag400.addActionListener(this::jMenuItemMagXActionPerformed);
    jMenuItemMag200.setText("200%");
    jMenuItemMag200.addActionListener(this::jMenuItemMagXActionPerformed);
    jMenuItemMag100.setText("100%");
    jMenuItemMag100.addActionListener(this::jMenuItemMagXActionPerformed);

    jMenuItemCode128.setText("Code128");
    jMenuItemCode128.setActionCommand("code128");
    jMenuItemCode128.addActionListener(this::jMenuItemSetBarcodeActionPerformed);
    jMenuItemEAN128.setText("EAN128");
    jMenuItemEAN128.addActionListener(this::jMenuItemSetBarcodeActionPerformed);
    jMenuItemEAN13.setText("EAN13");
    jMenuItemEAN13.addActionListener(this::jMenuItemSetBarcodeActionPerformed);
    jMenuItemEAN8.setText("EAN8");
    jMenuItemEAN8.addActionListener(this::jMenuItemSetBarcodeActionPerformed);
    jMenuItemInter25.setText("2 of 5 interleaved");
    jMenuItemInter25.setActionCommand("2of5i");
    jMenuItemInter25.addActionListener(this::jMenuItemSetBarcodeActionPerformed);

    jPanel3.setLayout(borderLayout2);
    jLabel1.setText("Code:");
    jMenuTextalign.setText("Textalign");
    jMenuItemTextLeft.setText("Left");
    jMenuItemTextLeft.addActionListener(this::jMenuItemTextAlignActionPerformed);
    jMenuItemTextCenter.setText("Center");
    jMenuItemTextCenter.addActionListener(this::jMenuItemTextAlignActionPerformed);
    jMenuItemTextRight.setText("Right");
    jMenuItemTextRight.addActionListener(this::jMenuItemTextAlignActionPerformed);
    jMenuTextfont.setText("Textfont");

    jMenuItemPrint.setText("Print Barcode...");
    jMenuItemPrint.addActionListener(this::jMenuItemPrintActionPerformed);
    jPanel1.add(jTypeLabel, BorderLayout.WEST);
    jPanel1.add(jVersionlabel, BorderLayout.CENTER);
    contentPane.add(jPanel3, BorderLayout.NORTH);
    jPanel3.add(jLabel1, BorderLayout.WEST);
    jPanel3.add(jCodeTextfield, BorderLayout.CENTER);
    contentPane.add(bcComponent, BorderLayout.CENTER);
    contentPane.add(jPanel1, BorderLayout.SOUTH);

    // Build the menu
    jMenuFile.setText("File");
    jMenuFileExit.setText("Exit");
    jMenuFileExit.addActionListener(this::jMenuFileExitActionPerformed);
    jMenuItemExport.setText("Export to /tmp/barcode.jpg");
    jMenuItemExport.addActionListener(this::jMenuItemExportActionPerformed);

    jMenuBarcode.setText("Barcode");
    jMenuFile.add(jMenuItemPrint);
    jMenuFile.add(jMenuItemExport);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileExit);
    jMenuBar.add(jMenuFile);
    jMenuBar.add(jMenuBarcode);
    jMenuBar.add(jMenuProps);
    jMenuBar.add(jMenuHelp);

    jMenuBarcode.add(jMenuItemCode128);
    jMenuBarcode.add(jMenuItemEAN128);
    jMenuBarcode.add(jMenuItemEAN13);
    jMenuBarcode.add(jMenuItemEAN8);
    jMenuBarcode.add(jMenuItemInter25);

    barcodegroup.add(jMenuItemCode128);
    barcodegroup.add(jMenuItemEAN128);
    barcodegroup.add(jMenuItemEAN13);
    barcodegroup.add(jMenuItemEAN8);
    barcodegroup.add(jMenuItemInter25);

    jMenuProps.add(jCheckBoxMenuItemDrawText);
    jMenuProps.add(jMenuMagn);
    jMenuProps.add(jMenuTextfont);
    jMenuProps.add(jMenuTextalign);
    jMenuMagn.add(jMenuItemMag100);
    jMenuMagn.add(jMenuItemMag200);
    jMenuMagn.add(jMenuItemMag400);
    jMenuHelp.add(jMenuItemAbout);
    jMenuTextalign.add(jMenuItemTextLeft);
    jMenuTextalign.add(jMenuItemTextCenter);
    jMenuTextalign.add(jMenuItemTextRight);

    // add fontnames to menu "Textfont"
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    String[] fontnames = ge.getAvailableFontFamilyNames();
    for (int i = 0; i < fontnames.length; ++i) {
      JMenuItem menuitem = new JMenuItem(fontnames[i]);
      menuitem.setActionCommand(fontnames[i]);
      menuitem.addActionListener(this::jFontMenuActionPerformed);
      jMenuTextfont.add(menuitem);
    }

    this.setJMenuBar(jMenuBar);
  }

  /**
   * User chooses another font so set the font name in the barcode object
   */
  public void jFontMenuActionPerformed(ActionEvent e) {
    if (bcComponent.getBc() != null) {
      bcComponent.getBc().setFontname(e.getActionCommand());
      this.repaint();
      setupMainFrame();
    }
  }

  /**
   * Exit program
   */
  public void jMenuFileExitActionPerformed(ActionEvent e) {
    System.exit(0);
  }

  // Overridden so we can exit when window is closed
  @Override
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  /**
   * Setup the menus and the label according to the barcode
   */
  private void setupMainFrame() {
    Barcode bc = bcComponent.getBc();

    if (bc == null) { // hu? no barcode?
      jCodeTextfield.setText("");
      bcComponent.setBc(bc);
      jTypeLabel.setText("<no barcode>");
      return;
    }

    // We have a barcode, so setup
    jCodeTextfield.setText(bc.getCode());
    bcComponent.setBc(bc);
    String status = bc.getCodeType().toString();
    int mag = (int) (bc.getMagnification() * 100);
    status = status + " " + mag + "%";
    jTypeLabel.setText(status);
    jCheckBoxMenuItemDrawText.setState(bc.isDrawtext());

    switch (mag) {
    case 200:
      jMenuItemMag100.setSelected(false);
      jMenuItemMag200.setSelected(true);
      jMenuItemMag400.setSelected(false);
      break;
    case 400:
      jMenuItemMag100.setSelected(false);
      jMenuItemMag200.setSelected(false);
      jMenuItemMag400.setSelected(true);
      break;
    default:
      jMenuItemMag100.setSelected(true);
      jMenuItemMag200.setSelected(false);
      jMenuItemMag400.setSelected(false);
      break;
    }

  }

  /**
   * The textfield with the code was changed so build a new barcode
   */
  void jCodeTextfieldActionPerformed(ActionEvent e) {
    String code = jCodeTextfield.getText();
    bcComponent.getBc().setCode(code);
    // System.err.println(bcComponent.getBc().toString());
    bcComponent.repaint();
    this.repaint();
  }

  /**
   * Sets a new type of barcode when selected in the menu
   *
   * @param e the action event
   */
  void jMenuItemSetBarcodeActionPerformed(ActionEvent e) {
    Barcode bc = null;

    if (e.getActionCommand().endsWith("2of5i")) {
      bc = new BarcodeInter25();
    } else if (e.getActionCommand().endsWith("EAN13")) {
      bc = new BarcodeEAN();
      bc.setCodeType(BarcodeType.EAN13);
    } else if (e.getActionCommand().endsWith("EAN8")) {
      bc = new BarcodeEAN();
      bc.setCodeType(BarcodeType.EAN8);
      bc.setCode("40125435");
    } else if (e.getActionCommand().endsWith("code128")) {
      bc = new Barcode128();
      bc.setCodeType(BarcodeType.CODE128);
      bc.setCode("JBarcode");
    } else if (e.getActionCommand().endsWith("EAN128")) {
      bc = new Barcode128();
      bc.setCodeType(BarcodeType.EAN128);
      bc.setCode("01040123453333361503123110123456");
      // bc.setCode("010401234533333610123456" + Barcode128.FNC1 + "15031231");
    } else {
      return;
    }

    bc.setMagnification(2.0f);
    bcComponent.setBc(bc);
    setupMainFrame();
    bcComponent.repaint();
  }

  /**
   * Menucallback: Draw text below barcode on/off
   */
  void jCheckBoxMenuItemDrawTextActionPerformed(ActionEvent e) {
    if (bcComponent.getBc() != null) { // barcode exists!!
      bcComponent.getBc().setDrawtext(jCheckBoxMenuItemDrawText.getState());
      bcComponent.repaint();
      setupMainFrame();
    }
  }

  /**
   * Show the about box
   */
  void jMenuItemAboutActionPerformed(ActionEvent e) {
    JBarcodeSampleAboutBox aboutboxdlg = new JBarcodeSampleAboutBox(this, "JBarcode");
    Dimension dlgSize = aboutboxdlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    aboutboxdlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    aboutboxdlg.setModal(true);
    aboutboxdlg.pack();
    aboutboxdlg.setVisible(true);
  }

  /**
   * Sets the magnification of the displayed barcode to 100%,200 or 400%
   *
   * @param e the actionevent from the menu
   */
  void jMenuItemMagXActionPerformed(ActionEvent e) {
    if (bcComponent.getBc() == null) {
      return;
    }

    float mag = 1.0f;
    if (e.getActionCommand().endsWith("400%")) {
      mag = 4.0f;
    } else if (e.getActionCommand().endsWith("200%")) {
      mag = 2.0f;
    }

    bcComponent.getBc().setMagnification(mag);
    setupMainFrame();
    bcComponent.repaint();
  }

  /**
   * Sets the text adjustment to left, center or right
   *
   * @param e the action event from the menu
   */
  void jMenuItemTextAlignActionPerformed(ActionEvent e) {
    if (bcComponent.getBc() == null) {
      return;
    }

    int align = Barcode.ALIGN_LEFT;
    if (e.getActionCommand().endsWith("Center")) {
      align = Barcode.ALIGN_CENTER;
    } else if (e.getActionCommand().endsWith("Right")) {
      align = Barcode.ALIGN_RIGHT;
    }

    bcComponent.getBc().setTextAlignment(align);
    setupMainFrame();
    bcComponent.repaint();
  }

  /**
   * Exports the selected barcode to a jpg file
   *
   * @param e the actionevent from the menu
   */
  void jMenuItemExportActionPerformed(ActionEvent e) {
    if (bcComponent.getBc() != null) {
      try {
        BarcodeRenderer.exportJpg(bcComponent.getBc(), "/tmp/barcode.jpg");
      } catch (Exception ex) {
        System.err.println("Cant export barcode to '/tmp/barcode.jpg'");
      }
    }
  }

  /**
   * Callback for printing the barcode
   *
   * @param e the action event from the menu
   */
  void jMenuItemPrintActionPerformed(ActionEvent e) {
    // does nothing
  }

}