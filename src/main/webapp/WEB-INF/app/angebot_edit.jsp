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

<template:base>
    <jsp:attribute name="title">
        <c:choose>
            <c:when test="${edit}">
                Aufgabe bearbeiten
            </c:when>
            <c:otherwise>
                Aufgabe anlegen
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/angebot_edit.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/angebote/"/>">Übersicht</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <form method="post" class="stacked">
            <div class="column">
                <%-- CSRF-Token --%>
                <input type="hidden" name="csrf_token" value="${csrf_token}">

                <%-- Eingabefelder --%>

                <label for="angebot_category">
                    Kategorie:
                </label>
                <div class="side-by-side">
                    <select name="angebot_category">
                        <option value="">Keine Kategorie</option>

                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}"${angebot_form.values["angebot_category"][0] == category.id ? 'selected' : ''}>
                                <c:out value="${category.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <label for="angebot_art">
                    Art des Angebots:   
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <select name="angebot_art" value="${angebot_form.values["angebot_art"][0]}">
                        <option>Biete</option>
                        <option>Suche</option>
                    </select>
                </div>

                <label for="angebot_bezeichnung">
                    Bezeichnung:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="angebot_bezeichnung" value="${angebot_form.values["angebot_bezeichnung"][0]}">
                </div>

                <label for="angebot_beschreibung">
                    Beschreibung:
                </label>
                <div class="side-by-side">
                    <textarea name="angebot_beschreibung" value="${angebot_form.values["angebot_beschreibung"][0]}"></textarea>
                </div>

                <label for="angebot_preis">
                    Preis
                </label>
                <div class="side-by-side">
                    <select name="angebot_preisart" value="${angebot_form.values["angebot_preisart"][0]}">
                        <option>Festpreis</option>
                        <option>Verhandlungsbasis</option>
                        <option>Sonstiges</option>
                    </select>
                    <input name="angebot_preis" type="number" min="0" step="0.01" data-number-to-fixed="2" maxlength="100000" value="${angebot_form.values["angebot_preis"][0]}">
                </div>

                <%-- Button zum Abschicken --%>
                <div class="side-by-side">
                    <button class="icon-pencil" type="submit" name="action" value="save">
                        Sichern
                    </button>

                    <c:if test="${edit}">
                        <button class="icon-trash" type="submit" name="action" value="delete">
                            Löschen
                        </button>
                    </c:if>
                </div>
            </div>

            <%-- Fehlermeldungen --%>
            <c:if test="${!empty angebot_form.errors}">
                <ul class="errors">
                    <c:forEach items="${angebot_form.errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </form>
    </jsp:attribute>
</template:base>