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
 * Statuswerte einer Aufgabe.
 */
public enum AngebotsTyp {
    Suche, Biete;

    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getLabel() {
        switch (this) {
            case Suche:
                return "Suche";
            case Biete:
                return "Biete";       
            default:
                return this.toString();
        }
    }
}
