<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription - SGBD Dinosaures</title>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    	<link rel="stylesheet" href="css/styles.css">
    	  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
    </head>
    <body>
        <div class="form-wrapper animate__animated animate__zoomIn" style="max-width: 500px;">
            <h1>Inscription</h1>

            <c:if test="${!empty succes}">
                <div class="alert alert-success animate__animated animate__pulse">${succes}</div>
            </c:if>
            <c:if test="${!empty erreur}">
                <div class="alert alert-error animate__animated animate__headShake">${erreur}</div>
            </c:if>

            <form method="post" action="<c:url value='/inscription'/>">
<div class="form-group">
    <label for="nom">Nom d'utilisateur</label>
    <input type="text" id="nom" name="nom" value="<c:out value='${utilisateur.nom}'/>" />
    <c:if test="${!empty form.erreurs['nom']}">
        <span class="error">${form.erreurs['nom']}</span>
    </c:if>
</div>

<div class="form-group">
    <label for="email">Adresse email</label>
    <input type="email" id="email" name="email" value="<c:out value='${utilisateur.email}'/>" />
    <c:if test="${!empty form.erreurs['email']}">
        <span class="error">${form.erreurs['email']}</span>
    </c:if>
</div>

<div class="form-group">
    <label for="motdepasse">Mot de passe</label>
    <input type="password" id="motdepasse" name="motdepasse" />
    <c:if test="${!empty form.erreurs['motdepasse']}">
        <span class="error">${form.erreurs['motdepasse']}</span>
    </c:if>
</div>
                
                <div class="form-actions" style="justify-content: center;">
                    <button type="submit" style="width: 100%;">S'inscrire</button>
                </div>
            </form>
            
            <p style="text-align:center; margin-top: 1.5rem;">
                Déjà un compte ? <a href="<c:url value='/connexion'/>">Connectez-vous</a>
            </p>
        </div>
    </body>
</html>
