/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package web;

import ejb.CategoryBean;
import ejb.AngebotBean;
import jpa.Category;
import jpa.Angebot;
import jpa.TaskStatus;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet für die Startseite bzw. jede Seite, die eine Liste der Aufgaben
 * zeigt.
 */
@WebServlet(urlPatterns = {"/app/tasks/"})
public class AngebotListServlet extends HttpServlet {

    @EJB
    private CategoryBean categoryBean;
    
    @EJB
    private AngebotBean angebotBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verfügbare Kategorien und Stati für die Suchfelder ermitteln
        request.setAttribute("categories", this.categoryBean.findAllSorted());
        request.setAttribute("statuses", TaskStatus.values());

        // Suchparameter aus der URL auslesen
        String searchText = request.getParameter("search_text");
        String searchCategory = request.getParameter("search_category");
        String searchArt = request.getParameter("search_art");

        // Anzuzeigende Aufgaben suchen
        Category category = null;
        

        if (searchCategory != null) {
            try {
                category = this.categoryBean.findById(Long.parseLong(searchCategory));
            } catch (NumberFormatException ex) {
                category = null;
            }
        }

        List<Angebot> angebote = this.angebotBean.search(searchText, category, searchArt);
        request.setAttribute("angebote", angebote);

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/app/angebot_list.jsp").forward(request, response);
    }
}
