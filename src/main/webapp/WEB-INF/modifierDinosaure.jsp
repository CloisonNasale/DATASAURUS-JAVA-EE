<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Modifier un dinosaure</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
                integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
                crossorigin="anonymous">
            <link rel="stylesheet" href="css/styles.css">
              <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
        </head>

        <body>
            <jsp:include page="/WEB-INF/header.jsp" />
            <div class="form-wrapper animate__animated animate__zoomIn">
                <h1>Modifier un Dinosaure</h1>
                <form method="post" action="<c:url value='/modifier'/>">
                    <input type="hidden" name="idDino" value="${dinosaure.id}" />

                    <div class="form-group">
                        <label>Nom :</label>
                        <input type="text" name="nomDino" value="<c:out value='${dinosaure.nom}'/>" />
                    </div>

                    <div class="form-group">
                        <label>Espèce :</label>
                        <input type="text" name="especeDino" value="<c:out value='${dinosaure.espece}'/>" />
                    </div>

                    <div class="form-group">
                        <label>Ère :</label>
                        <select name="ereDino">
                            <option value="Trias" ${dinosaure.ere=='Trias' ? 'selected' : '' }>Trias</option>
                            <option value="Jurassique" ${dinosaure.ere=='Jurassique' ? 'selected' : '' }>Jurassique</option>
                            <option value="Crétacé" ${dinosaure.ere=='Crétacé' ? 'selected' : '' }>Crétacé</option>
                        </select>
                    </div>

                    <div class="form-actions">
                        <a href="<c:url value='/dinosaures'/>" class="btn-custom-danger">Annuler</a>
                        <button type="submit">Enregistrer les modifications</button>
                    </div>
                </form>
            </div>
        </body>

        </html>