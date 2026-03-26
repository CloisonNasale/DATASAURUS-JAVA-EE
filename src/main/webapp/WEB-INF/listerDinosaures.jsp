<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Collection de Dinosaures</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    	<link rel="stylesheet" href="css/styles.css">
    	  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
    </head>
    <body>
    <jsp:include page="/WEB-INF/header.jsp" />
        <div class="container animate__animated animate__fadeIn">

            <c:if test="${!empty erreur}">
                <p class="error"><c:out value="${erreur}" /></p>
            </c:if>

<div class="row controls animate__animated animate__fadeIn">
    
    <div class="col-12 col-lg-8"> 
        <div class="search-bar">
            <form action="dinosaures" method="get" class="d-flex">
                <input type="text" name="recherche" class="form-control" placeholder="Nom, ère, régime..." value="<c:out value='${rechercheActuelle}' />" />
                <button type="submit" class="ml-2">Rechercher</button>
                <c:if test="${!empty rechercheActuelle}">
                    <a href="dinosaures" class="btn-custom-danger ml-2 align-self-center">Effacer</a>
                </c:if>
            </form>
        </div>
    </div>

    <div class="col-12 col-lg-4 align-self-center">
        <a href="<c:url value='ajouterDinosaure'/>" class="btn-custom">
            Ajouter un dinosaure
        </a>
    </div>
    
</div>

            <table class="table-responsive animate__animated animate__fadeIn">
                <thead>
                    <tr>
                        <th><a href="<c:url value="/dinosaures?tri=nom&ordre=${ordreActuel == 'asc' ? 'desc' : 'asc'}"/>">Nom</a></th>
                        <th><a href="<c:url value="/dinosaures?tri=espece&ordre=${ordreActuel == 'asc' ? 'desc' : 'asc'}"/>">Espèce</a></th>
                        <th><a href="<c:url value="/dinosaures?tri=ere&ordre=${ordreActuel == 'asc' ? 'desc' : 'asc'}"/>">Ère</a></th>
                        <th><a href="<c:url value="/dinosaures?tri=regime_alimentaire&ordre=${ordreActuel == 'asc' ? 'desc' : 'asc'}"/>">Régime</a></th>
                        <th class="text-center align-middle">Modification</th>
                        <th class="text-center align-middle">Suppression</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty dinosaures}">
                            <tr>
                                <td colspan="4" class="empty">Aucun dinosaure trouvé dans la collection.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${dinosaures}" var="dino">
                                <tr>
                                    <td><c:out value="${dino.nom}" /></td>
                                    <td><c:out value="${dino.espece}" /></td>
                                    <td><c:out value="${dino.ere}" /></td>
                                    <td><c:out value="${dino.regimeAlimentaire}" /></td>
                                    <td class="text-center align-middle">
                                        <a href="<c:url value='/modifier?id=${dino.id}'/>" class="btn-custom">Modifier</a>
                                    </td>
                                    <td class="text-center align-middle">
                                        <a href="<c:url value='/supprimer?id=${dino.id}'/>" class="btn-custom-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ${dino.nom} ?');">Supprimer</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </body>
</html>
