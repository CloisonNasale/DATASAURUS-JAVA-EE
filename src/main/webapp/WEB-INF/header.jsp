<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar mb-4 animate__animated animate__fadeIn position-relative d-flex align-items-center">
    
    <a class="navbar-brand position-absolute" style="left: 50%; transform: translateX(-50%); margin: auto;" href="<c:url value='/dinosaures'/>">
        <h1 class="logo d-none d-xl-block" style="margin-bottom:0;">Datasaurus</h1>
    </a>
    
    <ul class="navbar-nav ml-auto d-flex flex-row align-items-center">
        <li class="nav-item">
            <span class="text-white mr-3">Bienvenue, <b>${sessionUtilisateur.nom}</b></span>
        </li>
        <li class="nav-item mr-3">
            <a href="<c:url value='/deconnexion'/>" class="btn-custom">Se déconnecter</a>
        </li>
        <li class="nav-item">
            <a href="<c:url value='/supprimerCompte'/>" 
               class="btn-custom-danger"
               onclick="return confirm('ATTENTION : Voulez-vous vraiment supprimer votre compte ?');">
               Supprimer mon compte
            </a>
        </li>
    </ul>
</nav>