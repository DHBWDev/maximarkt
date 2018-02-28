/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import jpa.Benutzer;
import jpa.Foto;
import jpa.Kategorie;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabio Kraemer
 */
@Entity
public class Anzeige implements Serializable {

    @Id
    @GeneratedValue
    private long id = 0;

    private String art = "";
    private String titel = "";

    @Lob
    private String beschreibung = "";

    private Date erstellungsDatum = null;
    private Date onlineBis = null;

    private double preisVorstellung = 0;
    private String artDesPreises = "";

    private int plz = 0;
    private String ort = "";

    @ManyToOne
    Benutzer releasedBenutzer = null;

    @ManyToMany
    List<Benutzer> noticedBenutzer = new ArrayList<>();

    @OneToMany(mappedBy = "anzeige")
    List<Foto> fotos = new ArrayList<>();

    @ManyToMany
    List<Kategorie> kategorien = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Konstrukturen">
    public Anzeige() {
    }

    public Anzeige(String art, String titel, String beschreibung, Date erstellungsDatum, Date onlineBis, double preisVorstellung, String artDesPreises, int plz, String ort) {
        this.art = art;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.erstellungsDatum = erstellungsDatum;
        this.onlineBis = onlineBis;
        this.preisVorstellung = preisVorstellung;
        this.artDesPreises = artDesPreises;
        this.plz = plz;
        this.ort = ort;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public void setId(long id) {
        this.id = id;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setErstellungsDatum(Date erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
    }

    public void setOnlineBis(Date onlineBis) {
        this.onlineBis = onlineBis;
    }

    public void setPreisVorstellung(double preisVorstellung) {
        this.preisVorstellung = preisVorstellung;
    }

    public void setArtDesPreises(String artDesPreises) {
        this.artDesPreises = artDesPreises;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setReleasedBenutzer(Benutzer releasedBenutzer) {
        this.releasedBenutzer = releasedBenutzer;
    }

    public void setNoticedBenutzer(List<Benutzer> noticedBenutzer) {
        this.noticedBenutzer = noticedBenutzer;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public void setKategorien(List<Kategorie> kategorien) {
        this.kategorien = kategorien;
    }
    
     public long getId() {
        return id;
    }

    public String getArt() {
        return art;
    }

    public String getTitel() {
        return titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Date getErstellungsDatum() {
        return erstellungsDatum;
    }

    public Date getOnlineBis() {
        return onlineBis;
    }

    public double getPreisVorstellung() {
        return preisVorstellung;
    }

    public String getArtDesPreises() {
        return artDesPreises;
    }

    public int getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public Benutzer getReleasedBenutzer() {
        return releasedBenutzer;
    }

    public List<Benutzer> getNoticedBenutzer() {
        return noticedBenutzer;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public List<Kategorie> getKategorien() {
        return kategorien;
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
