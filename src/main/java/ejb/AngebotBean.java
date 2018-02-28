/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package ejb;

import jpa.Category;
import jpa.Angebot;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Einfache EJB mit den üblichen CRUD-Methoden für Aufgaben
 */
@Stateless
@RolesAllowed("maximarkt-app-user")
public class AngebotBean extends EntityBean<Angebot, Long> { 
   
    public AngebotBean() {
        super(Angebot.class);
    }
    
    /**
     * Suche nach Aufgaben anhand ihrer Bezeichnung, Kategorie und Status.
     * 
     * Anders als in der Vorlesung behandelt, wird die SELECT-Anfrage hier
     * mit der CriteriaBuilder-API vollkommen dynamisch erzeugt.
     * 
     * @param search In der Kurzbeschreibung enthaltener Text (optional)
     * @param category Kategorie (optional)
     * @param status Status (optional)
     * @return Liste mit den gefundenen Aufgaben
     */
    public List<Angebot> search(String search, Category category, String angebotsart) {
        // Hilfsobjekt zum Bauen des Query
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        
        // SELECT t FROM Angebot t
        CriteriaQuery<Angebot> query = cb.createQuery(Angebot.class);
        Root<Angebot> from = query.from(Angebot.class);
        query.select(from);

        // ORDER BY dueDate, dueTime
        query.orderBy(cb.asc(from.get("erstellungsDatum")), cb.asc(from.get("erstellungsDatum")));
        
        // WHERE t.shortText LIKE :search
        if (search != null && !search.trim().isEmpty()) {
            query.where(cb.like(from.get("titel"), "%" + search + "%"));
        }
        
        // WHERE t.category = :category
        if (category != null) {
            query.where(cb.equal(from.get("category"), category));
        }
        
        // WHERE t.status = :status
        if (angebotsart != null) {
            query.where(cb.equal(from.get("angebotsart"), angebotsart));
        }
        
        return em.createQuery(query).getResultList();
    }
}
