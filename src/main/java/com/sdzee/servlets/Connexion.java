package com.sdzee.servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Utilisateur;
import com.sdzee.dao.DAOException;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.UtilisateurDao;

@WebServlet("/connexion")
public class Connexion extends HttpServlet {
    public static final String VUE = "/WEB-INF/connexion.jsp";

    private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( "daofactory" ) ).getUtilisateurDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        request.getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String email = request.getParameter( "email" );
        String motDePasse = request.getParameter( "motdepasse" );

        Utilisateur utilisateur = null;
        try {
            utilisateur = utilisateurDao.trouver( email );
        } catch ( DAOException e ) {
            request.setAttribute( "erreur", "Erreur lors de la recherche du compte : " + e.getMessage() );
        request.getRequestDispatcher( VUE ).forward( request, response );
            return;
        }

        if ( utilisateur == null ) {
            request.setAttribute( "erreur", "Aucun compte trouvé avec cette adresse email." );
        } else {
            String motDePasseSaisiHache = hashMotDePasse( motDePasse );
            if ( utilisateur.getMotDePasse().equals( motDePasseSaisiHache ) ) {
                // Succès : création de la session
                HttpSession session = request.getSession();
                session.setAttribute( "sessionUtilisateur", utilisateur );
                response.sendRedirect( request.getContextPath() + "/dinosaures" );
                return;
            } else {
                request.setAttribute( "erreur", "Mot de passe incorrect." );
            }
        }

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    private String hashMotDePasse( String motDePasse ) {
        try {
            MessageDigest md = MessageDigest.getInstance( "SHA-256" );
            md.update( motDePasse.getBytes() );
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for ( byte b : digest ) {
                sb.append( String.format( "%02x", b ) );
            }
            return sb.toString();
        } catch ( NoSuchAlgorithmException e ) {
            throw new RuntimeException( "Erreur lors du hachage du mot de passe", e );
        }
    }
}
