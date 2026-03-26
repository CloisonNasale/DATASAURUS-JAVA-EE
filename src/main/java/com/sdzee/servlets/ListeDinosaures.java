package com.sdzee.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Dinosaure;
import com.sdzee.dao.DAOException;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.DinosaureDao;

@WebServlet("/dinosaures")
public class ListeDinosaures extends HttpServlet {
    public static final String VUE = "/WEB-INF/listerDinosaures.jsp";

    private DinosaureDao dinosaureDao;

    public void init() throws ServletException {
        this.dinosaureDao = ( (DAOFactory) getServletContext().getAttribute( "daofactory" ) ).getDinosaureDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // Redirection si l'utilisateur n'est pas connecté
        HttpSession session = request.getSession();
        if ( session.getAttribute( "sessionUtilisateur" ) == null ) {
            response.sendRedirect( request.getContextPath() + "/connexion" );
            return;
        }

        String recherche = request.getParameter( "recherche" );
        String tri = request.getParameter( "tri" );
        String ordre = request.getParameter( "ordre" );
        
        List<Dinosaure> dinosaures = null;

        try {
            if ( recherche != null && !recherche.trim().isEmpty() ) {
                dinosaures = dinosaureDao.rechercher( recherche );
            } else {
                if ( tri == null || tri.isEmpty() ) {
                    tri = "id";
                }
                boolean ascendant = true;
                if ( "desc".equalsIgnoreCase( ordre ) ) {
                    ascendant = false;
                }
                dinosaures = dinosaureDao.lister( tri, ascendant );
            }
        } catch ( DAOException e ) {
            request.setAttribute( "erreur", "Erreur lors de la récupération des dinosaures : " + e.getMessage() );
        }

        request.setAttribute( "dinosaures", dinosaures );
        
        // On conserve les paramètres pour les liens de tri
        request.setAttribute( "triActuel", tri != null ? tri : "id" );
        request.setAttribute( "ordreActuel", ordre != null ? ordre : "asc" );
        request.setAttribute( "rechercheActuelle", recherche != null ? recherche : "" );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
