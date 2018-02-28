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

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Eine zu erledigende Aufgabe.
 */
@Entity
public class Angebot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "task_ids")
    @TableGenerator(name = "task_ids", initialValue = 0, allocationSize = 50)
    private long id;
    
    @NotNull(message = "Die Art darf nicht leer sein.")
    private String art = "";
    @NotNull(message = "Der Titel darf nicht leer sein.")
    private String titel = "";

    @Lob
    @NotNull(message = "Die Beschreibung darf nicht leer sein.")
    @Size(min = 1, max = 50, message = "Die Bezeichnung muss zwischen ein und 50 Zeichen lang sein.")
    private String beschreibung = "";

    private java.util.Date erstellungsDatum = null;
    private java.util.Date onlineBis = null;

    @NotNull(message = "Der Preis darf nicht leer sein.")
    private double preisVorstellung = 0;
    
    @NotNull(message = "Die Preisart darf nicht leer sein.")
    private String artDesPreises = "";

    @NotNull(message = "Die PLZ darf nicht leer sein.")
    private int plz = 0;
    
    @NotNull(message = "Der Ort darf nicht leer sein.")
    private String ort = "";
    
    
    @ManyToOne
    @NotNull(message = "Die Aufgabe muss einem Benutzer geordnet werden.")
    private User owner;

    @ManyToOne
    private Category category;

    

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Angebot() {
    }

    public Angebot(User owner, Category category, String art, String titel, 
            String beschreibung, Date erstellungsDatum, Time onlineBis,
            Double preisVorstellung, String artDesPreises, int plz, String ort) {
        this.owner = owner;
        this.category = category;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public java.util.Date getErstellungsDatum() {
        return erstellungsDatum;
    }

    public void setErstellungsDatum(java.util.Date erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
    }

    public java.util.Date getOnlineBis() {
        return onlineBis;
    }

    public void setOnlineBis(java.util.Date onlineBis) {
        this.onlineBis = onlineBis;
    }

    public double getPreisVorstellung() {
        return preisVorstellung;
    }

    public void setPreisVorstellung(double preisVorstellung) {
        this.preisVorstellung = preisVorstellung;
    }

    public String getArtDesPreises() {
        return artDesPreises;
    }

    public void setArtDesPreises(String artDesPreises) {
        this.artDesPreises = artDesPreises;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
}
