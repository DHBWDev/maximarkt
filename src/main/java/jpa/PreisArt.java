/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package jpa;

/**
 *
 * @author Fabio Kraemer
 */
public enum PreisArt {
    Festpreis, Verhandlungsbasis, Sonstiges;

    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getLabel() {
        switch (this) {
            case Festpreis:
                return "Festpreis";
            case Verhandlungsbasis:
                return "Verhandlungsbasis";   
            case Sonstiges:
                return "Sonstiges";
            default:
                return this.toString();
        }
    }
}

