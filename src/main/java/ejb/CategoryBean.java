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
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 * Einfache EJB mit den üblichen CRUD-Methoden für Kategorien.
 */
@Stateless
@RolesAllowed("maximarkt-app-user")
public class CategoryBean extends EntityBean<Category, Long> {

    public CategoryBean() {
        super(Category.class);
    }

    /**
     * Auslesen aller Kategorien, alphabetisch sortiert.
     *
     * @return Liste mit allen Kategorien
     */
    public List<Category> findAllSorted() {
        return this.em.createQuery("SELECT c FROM Category c ORDER BY c.name").getResultList();
    }

    public Category findByName(String name) {
        if (name == null) {
            return null;
        }
        return this.em.find(Category.class, name);
    }

}
