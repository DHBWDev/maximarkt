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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
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
    @Size(min = 1, max = 50, message = "Die Beschreibung muss zwischen ein und 50 Zeichen lang sein.")
    private String beschreibung = "";

    private Date erstellungsDatum = null;

    @NotNull(message = "Der Preis darf nicht leer sein.")
    private double preisVorstellung = 0;
    
    @NotNull(message = "Die Preisart darf nicht leer sein.")
    private String artDesPreises = "";
    
    @ManyToOne
    private User user = null;

    @ManyToOne
    private Category category = null;

    

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Angebot() {
    }

    public Angebot(User owner, Category category, String art, String titel, 
            String beschreibung, Date erstellungsDatum,
            Double preisVorstellung, String artDesPreises) {
        this.user = owner;
        this.category = category;
        this.art = art;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.erstellungsDatum = erstellungsDatum;
        this.preisVorstellung = preisVorstellung;
        this.artDesPreises = artDesPreises;
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

    public Date getErstellungsDatum() {
        return erstellungsDatum;
    }

    public void setErstellungsDatum(Date erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
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

    public User getOwner() {
        return user;
    }

    public void setOwner(User owner) {
        this.user = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
}
