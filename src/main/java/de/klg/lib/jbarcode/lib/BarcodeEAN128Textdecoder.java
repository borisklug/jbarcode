/* ----------------------------------------------------------------------------
 * JBarcode - https://github.com/borisklug/jbarcode
 *
 * Written by Boris Klug, https://klg.de/
 * Licensed under the Apache License Version 2.0
 * ---------------------------------------------------------------------------- */
package de.klg.lib.jbarcode.lib;

import java.util.HashMap;
import java.util.Map;

/**
 * This class decodes the texts for EAN128 barcodes
 *
 * @author Boris Klug
 */
public class BarcodeEAN128Textdecoder {

  private static Map<String, BarcodeEAN128Field> numberToText = new HashMap<>();

  static {
    BarcodeEAN128Field field;
    // fieldident -----vv vv--- infostr onlynumbers ---vv vv--- fixed content length
    field = new BarcodeEAN128Field("00", "Nummer Versandeinheit (NVE)", 18, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("01", "EAN der Handelseinheit", 14, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("02", "EAN der enhaltenen Einheit", 14, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("10", "Losnummer/Chargennr", 20, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("11", "Herstellungsdatum (JJMMTT)", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("12", "Fälligkeitsdatum (JJMMTT)", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("13", "Packdatum (JJMMTT)", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("15", "Mindeshaltbarkeitsdatum (JJMMTT)", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("17", "Verfallsdatum (JJMMTT)", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("20", "Produktvariante", 2, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("21", "Seriennummer", 20, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("240", "zusätzliche Produktinformation", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("241", "Kundenteilennummer", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("250", "Seriennummer integriertes Bauteil", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("251", "Quellenreferenz", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("252", "EAN-Identnummer Einzelfertigung", 27, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("30", "Menge in Stück (mengenvariable Handelseinheit)", 8, true, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("310", "Nettogewicht in Kilogramm (mengenvariable Handelseinheit)", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("311", "Länge/1. Dimension in Meter (mengenvariable Handelseinheit)", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("312", "Breite/Durchmesser/2. Dimension in Meter (mengenvariable Handelseinheit)", 6,
        true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("313", "Höhe/3. Dimension in Meter (mengenvariable Handelseinheit)", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("314", "Fläche in Quadratmeter (mengenvariable Handelseinheit)", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("315", "(Netto)Volumen in Liter (mengenvariable Handelseinheit)", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("316", "(Netto)Volumen in Kubikmeter (mengenvariable Handelseinheit)", 6, true,
        true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("330", "Bruttogewicht in Kilogramm", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("331", "Länge/1. Dimension in Meter", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("332", "Breite/Durchmesser/2. Dimension in Meter", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("333", "Höhe/3.Dimension in Meter", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("334", "Fläche in Quadratmeter", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("335", "(Brutto)Volumen in Liter", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("336", "(Brutto)Volumen in Kubikmeter", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("337", "Kilogramm je Quardatmeter", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("37", "Anzahl enthaltene Einheiten", 8, true, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("390", "Zahlungsbetrag lokale Währung", 15, true, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("391", "Zahlungsbetrag mit ISO-Währungsschlüssel", 15, true, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("392", "Verkaufsbetrag - einheitlicher Währungsbereich", 15, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("393", "Verkaufsbetrag mit vorangestelltem 3stelligen ISO-Währungscode", 18, true,
        false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("400", "Bestellnummer des Warenempfängers", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("401", "Sendungsnummer (enthält min. 7stellige Basisnummer)", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("402", "Lieferungsnummer", 17, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("403", "Leitcode", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("410", "ILN des Waremempfängers", 13, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("411", "ILN des Rechnungsempfängers", 13, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("412", "ILN des Lieferanten", 13, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("413", "ILN des Endempfängers bei gebrochenen Transporten", 13, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("414", "ILN auf der physischen Lokation", 13, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("415", "ILN des Rechnungsausstellers", 13, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("420", "PLZ des Empfängers in nationalem Format", 9, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("421",
        "PLZ des Empfängers in internationem Format (vorangestellter 3stelliger ISO-Ländercode)", 12, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("422", "Ursprungsland des Produktes", 3, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("423", "Land/Länder der ersten Verarbeitungsstufe", 15, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("424", "Land der Verarbeitung", 3, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("425", "Land der Zerlegung", 3, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("426", "Land aller Verarbeitungsstufen", 3, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("7001", "NATO-Lagerhaltungsnummer", 13, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("7002", "Klassifikation der UN/ECE für Fleischzuschnitte", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("7030-7039", "Zulassungsnummer des Verarbeitungsbetriebs", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("8001", "Rollenprodukte (Breite, Länge, Kerndurchmesser, ...)", 14, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("8003", "EAN-Idennummer für Mehrwegtransportverpackungen", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("8004", "Serielle EAN-Objekt- bzw. Behälternummer", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("8005", "Abgabepreis pro Maßeinheit der inliegenden Einheit", 6, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("8006", "Artikelkomponenten", 18, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("8007", "IBAN International Bank Account Number", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("8008", "Herstellungsdatum und -uhrzeit", 12, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("8018", "EAN-Servicebezugsnummer", 18, true, true);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("8020", "Referenznummer des Zahlungsträgers", 25, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("90", "Interne und/oder bilaterale bestimmte Anwendungen", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("91", "Intern - Rohmeterial, Verpackung, Komponenten", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("92", "Intern - Rohmeterial, Verpackung, Komponenten", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("93", "Intern - Hersteller", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("94", "Intern - Hersteller", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("95", "Intern - Transporteure (Fachbrief-Nr. etc.)", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("96", "Intern - Transporteure", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("97", "Intern - Groß- und Einzelhandel", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("98", "Intern - Groß- und Einzelhandel", 30, false, false);
    numberToText.put(field.getField(), field);
    field = new BarcodeEAN128Field("99", "Bilateral vereibarte Texte", 30, false, false);
    numberToText.put(field.getField(), field);
  }

  /**
   * Convers the EAN128 text in a human readable format with brackets. E.g.:
   *
   * "01040123453333361503123110123456" will be converted to
   * "(01)04012345333336(15)031231(10)123456"
   *
   * Also fields with variable length in the middle of the barcode are are
   * supported, just add a Barcode128.FNC1 after the field. Example:
   * 
   * {@code
   *   "010401234533333610123456<FNC1>15031231" will be converted to
   *   "(01)04012345333336(10)123456(15)031231"
   * }
   *
   * @param text  the text to be converted
   * @return the converted string or null if there is a problem
   */
  public static String toHumanText(String text) {

    // If text is not OK, return null
    if (!isTextOK(text)) {
      return null;
    }

    String out = "";
    int count = 0;
    int len = 0;
    BarcodeEAN128Field field = null;
    while (count < text.length()) {
      // test the startstring with 2, 3 and 4 chars
      field = numberToText.get(text.substring(count, count + 2));
      if (field == null) {
        field = numberToText.get(text.substring(count, count + 3));
      }
      if (field == null) {
        field = numberToText.get(text.substring(count, count + 4));
      }

      if (field == null) { // something went wrong!
        return "err";
      }
      out = out + "(" + field.getField() + ")";

      // Fixed len of this field
      if (field.getFixcontentlen()) {
        len = field.getField().length() + field.getContentlen();
        out = out + text.substring(count + 2, count + len);
        count = count + len;
      } else { // Variable len of the field

        // search for FNC1 or end of barcode
        int i = count + field.getField().length() + 1;
        boolean found = false;
        while (i < text.length()) {
          if (text.charAt(i) == Barcode128.FNC1) { // found a FNC1
            found = true;
            break;
          }
          i++;
        }
        if (found) { // found a FNC1
          out = out + text.substring(count + 2, i - 1);
          count = i + 1; // +1 for the fnc1
        } else { // not found we must be at the end
          out = out + text.substring(count + 2, count + len);
          count = count + len;
        }
      }
    }

    return out;
  }

  /**
   * Checks if the text is OK in terms of the EAN128 specification
   *
   * @return true if the text is OK, false if not
   */
  public static boolean isTextOK(String text) {
    // always ok ;-)
    return true;
  }
}
