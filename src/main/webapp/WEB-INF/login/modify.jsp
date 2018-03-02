<%-- 
    Copyright © 2018 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="base_url" value="<%=request.getContextPath()%>" />

<template:base>
    <jsp:attribute name="title">
        Benutzerprofil bearbeiten
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/login.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
         <a href="<c:url value="/app/angebote/"/>">Übersicht</a>
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="container">
           <form method="post" class="stacked">
                <div class="column">
                    <%-- CSRF-Token --%>
                    <input type="hidden" name="csrf_token" value="${csrf_token}">

                    <%-- Eingabefelder --%>
                    <label for="modify_username">
                        Benutzername:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="modify_username" value="${user.username}">
                    </div>
                    
                    <h2>Anschrift</h2>
                    
                    <label for="modify_name">
                        Vor- und Nachname:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="name" name="modify_name" value="${user.name}">
                    </div>
                    
                    <label for="modify_anschrift">
                        Straße und Hausnummer:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                            <input type="anschrift" name="modify_anschrift" value="${user.anschrift}">
                    </div>
                    
                    <label for="modify_plz">
                        Postleitzahl und Ort:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="plz" name="modify_plz" value="${user.plz}">
                        <input type="ort" name="modify_ort" value="${user.ort}">
                    </div>
                    
                    <h2>Kontaktdaten</h2>
                    
                    <label for="modify_email">
                        Emailadresse:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="email" name="modify_email" value="${user.email}">
                    </div>
                    
                    <label for="modify_telefonnummer">
                        Telefonnummer:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="telefonnummer" name="modify_telefonnummer" value="${user.telefonnummer}">
                    </div>
                    
                    <%-- Button zum Abschicken --%>
                    <div class="side-by-side">
                        <button class="icon-pencil" type="submit">
                            Speichern
                        </button>
                    </div>
                </div>

                <%-- Fehlermeldungen --%>
                <c:if test="${!empty modify_form.errors}">
                    <ul class="errors">
                        <c:forEach items="${modify_form.errors}" var="error">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </c:if>
                    
                    
                </div>
           </form>
        </div>
    </jsp:attribute>
</template:base>