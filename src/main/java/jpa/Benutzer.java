/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.Nachricht;
import jpa.Anzeige;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Fabio Kraemer
 */
@Entity
public class Benutzer implements Serializable {

    @Id
    private String benutzername = "";
    private String passwort = "";

    private String vorname = "";
    private String nachname = "";

    private String strasse = "";
    private int hausnummer = 0;
    private int plz = 0;
    private String ort = "";
    private String land = "";

    private String email = "";
    private String telefonnummer = "";

    @OneToMany(mappedBy = "benutzer")
    List<Nachricht> nachrichten = new ArrayList<>();

    @OneToMany(mappedBy = "releasedBenutzer")
    List<Anzeige> releasedAnzeigen = new ArrayList<>();

    @ManyToMany(mappedBy = "noticedBenutzer")
    List<Anzeige> noticedAnzeigen = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Konstrukturen">
    public Benutzer() {
    }

    public Benutzer(String benutzername, String passwort, String vorname, String nachname, String strasse, int hausnummer, int plz, String ort, String land, String email, String telefonnummer) {
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.land = land;
        this.email = email;
        this.telefonnummer = telefonnummer;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public void setNachrichten(List<Nachricht> nachrichten) {
        this.nachrichten = nachrichten;
    }

    public void setReleasedAnzeigen(List<Anzeige> releasedAnzeigen) {
        this.releasedAnzeigen = releasedAnzeigen;
    }

    public void setNoticedAnzeigen(List<Anzeige> noticedAnzeigen) {
        this.noticedAnzeigen = noticedAnzeigen;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getStrasse() {
        return strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public int getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public String getLand() {
        return land;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public List<Nachricht> getNachrichten() {
        return nachrichten;
    }

    public List<Anzeige> getReleasedAnzeigen() {
        return releasedAnzeigen;
    }

    public List<Anzeige> getNoticedAnzeigen() {
        return noticedAnzeigen;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Von Objekt geerbter Kram">
    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    //</editor-fold>

}
