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
        Übersicht
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/task_list.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/angebot/new/"/>">Angebot anlegen</a>
        </div>

        <div class="menuitem">
            <a href="<c:url value="/app/categories/"/>">Kategorien bearbeiten</a>
        </div>
        
        <div class="menuitem">
            <a href="<c:url value="/app/user/"/>">Benutzer bearbeiten</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <%-- Suchfilter --%>
        <form method="GET" class="horizontal" id="search">
            <input type="text" name="search_text" value="${param.search_text}" placeholder="Beschreibung"/>

            <select name="search_category">
                <option value="">Alle Kategorien</option>

                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}" ${param.search_category == category.id ? 'selected' : ''}>
                        <c:out value="${category.name}" />
                    </option>
                </c:forEach>
            </select>

            <select name="search_status">
                <option value="">Alle Stati</option>

                <c:forEach items="${arten}" var="art">
                    <option value="${art}" ${param.search_art == status ? 'selected' : ''}>
                        <c:out value="${art}"/>
                    </option>
                </c:forEach>
            </select>

            <button class="icon-search" type="submit">
                Suchen
            </button>
        </form>

        <%-- Gefundene Aufgaben --%>
        <c:choose>
            <c:when test="${empty angebote}">
                <p>
                    Es wurden keine Aufgaben gefunden. 🐈
                </p>
            </c:when>
            <c:otherwise>
                <jsp:useBean id="utils" class="web.WebUtils"/>
                
                <table>
                    <thead>
                        <tr>
                            <th>Bezeichnung</th>
                            <th>Kategorie</th>
                            <th>Benutzer</th>
                            <th>Angebotstyp</th>
                            <th>Preis</th>
                            <th>Preistyp</th>
                            <th>Datum</th>
                        </tr>
                    </thead>
                    <c:forEach items="${angebote}" var="angebot">
                        <tr>
                            <td>
                                <a href="<c:url value="/app/angebote/${angebot.id}/"/>">
                                    <c:out value="${angebot.titel}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${angebot.category.name}"/>
                            </td>
                            <td>
                                <c:out value="${angebot.owner.username}"/>
                            </td>
                            <td>
                                <c:out value="${angebot.art}"/>
                            </td>
                            <td>
                                <c:out value="${angebot.preisVorstellung}"/>
                            </td>
                            <td>
                                <c:out value="${angebot.artDesPreises}"/>
                            </td>
                            <td>
                                <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss.SSS" value="${angebot.erstellungsDatum}" var="parsedDate" />
                                <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
                                <c:out value="${parsedDate}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</template:base>
