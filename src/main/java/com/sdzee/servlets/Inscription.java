	package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLIntegrityConstraintViolationException;

import com.sdzee.beans.Utilisateur;
import com.sdzee.dao.DAOException;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.UtilisateurDao;

@WebServlet("/inscription")
public class Inscription extends HttpServlet {
    public static final String VUE = "/WEB-INF/inscription.jsp";

    private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( "daofactory" ) ).getUtilisateurDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        com.sdzee.forms.InscriptionForm form = new com.sdzee.forms.InscriptionForm();
        Utilisateur utilisateur = form.inscrireUtilisateur( request );

        if ( form.getErreurs().isEmpty() ) {
            try {
                utilisateurDao.creer( utilisateur );
                request.setAttribute( "succes", "Inscription réussie !" );
            } catch ( DAOException e ) {
                Throwable cause = e.getCause();
                if ( cause instanceof SQLIntegrityConstraintViolationException ) {
                    // Email déjà utilisé → message convivial
                    request.setAttribute( "erreur", "Cette adresse email est déjà utilisée. Veuillez en choisir une autre." );
                } else {
                    request.setAttribute( "erreur", "Une erreur est survenue lors de l'inscription. Veuillez réessayer." );
                }
            }
        } else {
            request.setAttribute( "form", form );
            request.setAttribute( "erreur", "Échec de l'inscription, vérifiez les champs." );
        }

        request.setAttribute( "utilisateur", utilisateur );
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
