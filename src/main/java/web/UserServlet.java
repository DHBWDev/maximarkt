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

import ejb.UserBean;
import javax.ejb.EJB;
import java.io.IOException;
import ejb.ValidationBean;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jpa.User;

/**
 *
 * @author Fabio Kraemer
 */

@WebServlet(urlPatterns ="/app/user/")
public class UserServlet extends HttpServlet {
    
    @EJB 
    UserBean userBean;
    
     @EJB
    ValidationBean validationBean;
            
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("user", this.userBean.getCurrentUser());
        
        request.setAttribute("modify_email", this.userBean.getCurrentUser().getEmail());
        

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/login/modify.jsp").forward(request, response);
        
        HttpSession session = request.getSession();
        session.removeAttribute("modify_form");
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Formulareingaben auslesen
        request.setCharacterEncoding("utf-8");
        
        
        String username = request.getParameter("modify_name"); 
        String name = request.getParameter("modify_name");
        String anschrift = request.getParameter("modify_anschrift");
        String plz  = request.getParameter("modify_plz");
        String ort  = request.getParameter("modify_ort");
        String email  = request.getParameter("modify_email");
        String telefonnummer  = request.getParameter("modify_telefonnummer");
        
        User user = this.userBean.getCurrentUser();
        
        user.setName(name);
        user.setAnschrift(anschrift);
        user.setPlz(plz);
        user.setOrt(ort);
        user.setEmail(email);
        user.setTelefonnummer(telefonnummer);
        user.setPassword("123456");
        
        // Eingaben prüfen
        List<String> errors = this.validationBean.validate(user);
        this.validationBean.validate(user.getPassword(), errors);
       
        
        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            this.userBean.update(user);
            // Keine Fehler: Startseite aufrufen
            response.sendRedirect(WebUtils.appUrl(request, "/app/angebote/"));
        } else {
            //Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);
            
            HttpSession session = request.getSession();
            session.setAttribute("modify_form", formValues);
            
            response.sendRedirect(request.getRequestURI());
        }
        
    }
        
}
