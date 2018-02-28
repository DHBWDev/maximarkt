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
import ejb.UserBean;
import ejb.ValidationBean;
import jpa.Angebot;
import jpa.TaskStatus;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jpa.Category;

/**
 * Seite zum Anlegen oder Bearbeiten einer Aufgabe.
 */
@WebServlet(urlPatterns = "/app/angebot/")
public class AngebotEditServlet extends HttpServlet {

    @EJB
    AngebotBean angebotBean;

    @EJB
    CategoryBean categoryBean;

    @EJB
    UserBean userBean;

    @EJB
    ValidationBean validationBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //
        request.setAttribute("categories", this.categoryBean.findAllSorted());
        
        // Zu bearbeitende Aufgabe einlesen
        HttpSession session = request.getSession();

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/app/angebot_edit.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Angeforderte Aktion ausführen
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");

        //if (action == null) {
        //  action = "";
        //}
        if (action == "save") {
            this.saveAngebot(request, response);
        }
    }

    /**
     * Aufgerufen in doPost(): Neue oder vorhandene Aufgabe speichern
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void saveAngebot(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Eingaben prüfen
        List<String> errors = new ArrayList<>();

        String angebotKategorie = request.getParameter("angebot_kategorie");
        String angebotArt = request.getParameter("angebot_art");
        String angebotBezeichnung = request.getParameter("angebot_bezeichnung");
        String angebotBeschreibung = request.getParameter("angebot_beschreibung");
        String angebotPreisArt = request.getParameter("angebot_preisart");
        double angebotPreis = Double.parseDouble(request.getParameter("angebot_preis"));
        
        Angebot angebot = new Angebot(this.userBean.getCurrentUser(), this.categoryBean.findById(1), "ARt", "Art", "blal", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 0, "", 0, "" );
        
        if (angebotKategorie != null && !angebotKategorie.trim().isEmpty()) {
            try {
                angebot.setCategory(this.categoryBean.findById(Long.parseLong(angebotKategorie)));
            } catch (NumberFormatException ex) {
                // Ungültige oder keine ID mitgegeben
            }
        }
        
        
        
        angebot.setErstellungsDatum(new Date(System.currentTimeMillis()));
        angebot.setOnlineBis(new Date(System.currentTimeMillis()));
        angebot.setOrt("Ort");
        angebot.setPlz(2324);
        angebot.setTitel("sdfdsfdsf");
        angebot.setBeschreibung("sadsad");
        angebot.setArt("Art");
        angebot.setArtDesPreises("yosl");
        angebot.setPreisVorstellung(545);
        angebot.setOwner(this.userBean.getCurrentUser());
        this.validationBean.validate(angebot, errors);

        // Datensatz speichern
         if (errors.isEmpty()) {
        this.angebotBean.saveNew(angebot);
        System.out.println("jjdasojsd");
        }
        
        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            response.sendRedirect(WebUtils.appUrl(request, "/app/angebot/"));
        } else {
            // Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);

            HttpSession session = request.getSession();
            session.setAttribute("angebot_form", formValues);

            response.sendRedirect(request.getRequestURI());
        }
        
    }

    /**
     * Aufgerufen in doPost: Vorhandene Aufgabe löschen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteAngebot(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Datensatz löschen
        Angebot angebot = this.getRequestedTask(request);
        this.angebotBean.delete(angebot);

        // Zurück zur Übersicht
        response.sendRedirect(WebUtils.appUrl(request, "/app/angebot/"));
    }

    /**
     * Zu bearbeitende Aufgabe aus der URL ermitteln und zurückgeben. Gibt
     * entweder einen vorhandenen Datensatz oder ein neues, leeres Objekt
     * zurück.
     *
     * @param request HTTP-Anfrage
     * @return Zu bearbeitende Aufgabe
     */
    private Angebot getRequestedTask(HttpServletRequest request) {
        // Zunächst davon ausgehen, dass ein neuer Satz angelegt werden soll
        Angebot angebot = new Angebot();
        angebot.setOwner(this.userBean.getCurrentUser());
        //task.setDueDate(new Date(System.currentTimeMillis()));
        //task.setDueTime(new Time(System.currentTimeMillis()));

        // ID aus der URL herausschneiden
        String angebotId = request.getPathInfo();

        if (angebotId == null) {
            angebotId = "";
        }

        angebotId = angebotId.substring(1);

        if (angebotId.endsWith("/")) {
            angebotId = angebotId.substring(0, angebotId.length() - 1);
        }

        // Versuchen, den Datensatz mit der übergebenen ID zu finden
        try {
            angebot = this.angebotBean.findById(Long.parseLong(angebotId));
        } catch (NumberFormatException ex) {
            // Ungültige oder keine ID in der URL enthalten
        }

        return angebot;
    }

    /**
     * Neues FormValues-Objekt erzeugen und mit den Daten eines aus der
     * Datenbank eingelesenen Datensatzes füllen. Dadurch müssen in der JSP
     * keine hässlichen Fallunterscheidungen gemacht werden, ob die Werte im
     * Formular aus der Entity oder aus einer vorherigen Formulareingabe
     * stammen.
     *
     * @param task Die zu bearbeitende Aufgabe
     * @return Neues, gefülltes FormValues-Objekt
     */
    private FormValues createTaskForm(Angebot angebot) {
        Map<String, String[]> values = new HashMap<>();

        values.put("task_owner", new String[]{
            angebot.getOwner().getUsername()
        });

        if (angebot.getCategory() != null) {
            values.put("task_category", new String[]{
                angebot.getCategory().toString()
            });
        }

        values.put("task_due_date", new String[]{ //WebUtils.formatDate(task.getDueDate()) 
        });

        values.put("task_due_time", new String[]{ //WebUtils.formatTime(task.getDueTime())
        });

        values.put("task_status", new String[]{ // task.getStatus().toString()
        });

        values.put("task_short_text", new String[]{ // task.getShortText()
        });

        values.put("task_long_text", new String[]{ // task.getLongText()
        });

        FormValues formValues = new FormValues();
        formValues.setValues(values);
        return formValues;
    }

}
