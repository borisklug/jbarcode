/*
 * PDIS Process Data Information System
 *
 *  Project: Java Barcode
 * Customer: control IT
 *
 * ----------------------------------------------------------------------------
 * (c) copyright 2000-2003 by control Information Technology GmbH,
 * Koblenz, Germany, http://control.de/, all rights reserved
 * ----------------------------------------------------------------------------
 *
 * $Id: $
 */
package de.klg.lib.jbarcode.app;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JBarcodeSampleAboutBox extends JDialog implements ActionListener {

  private JPanel panel1 = new JPanel();
  private JPanel panel2 = new JPanel();
  private JPanel insetsPanel1 = new JPanel();
  private JPanel insetsPanel3 = new JPanel();
  private JButton button1 = new JButton();
  private JLabel productLabel = new JLabel();
  private JLabel label3 = new JLabel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private BorderLayout borderLayout2 = new BorderLayout();
  private GridLayout gridLayout1 = new GridLayout();
  private String product;

  public JBarcodeSampleAboutBox(Frame parent, String product) {
    super(parent);
    this.product = product;
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Component initialization
  private void jbInit() throws Exception {
    this.setTitle("About");
    panel1.setLayout(borderLayout1);
    panel2.setLayout(borderLayout2);
    gridLayout1.setRows(4);
    gridLayout1.setVgap(5);
    gridLayout1.setColumns(1);
    productLabel.setText(product);
    label3.setText("written by Boris A. Klug");
    insetsPanel3.setLayout(gridLayout1);
    insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
    button1.setText("OK");
    button1.addActionListener(this);
    this.getContentPane().add(panel1, null);
    insetsPanel3.add(productLabel, null);
    insetsPanel3.add(label3, null);
    panel2.add(insetsPanel3, BorderLayout.CENTER);
    insetsPanel1.add(button1, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    panel1.add(panel2, BorderLayout.NORTH);
    setResizable(true);
  }

  // Overridden so we can exit when window is closed
  @Override
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }

  // Close the dialog
  void cancel() {
    dispose();
  }

  // Close the dialog on a button event
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      cancel();
    }
  }
}