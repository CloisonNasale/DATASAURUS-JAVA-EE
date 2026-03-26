package com.sdzee.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sdzee.beans.Utilisateur;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.UtilisateurDao;

@WebServlet("/supprimerCompte")
public class SupprimerCompte extends HttpServlet {
    private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
        this.utilisateurDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getUtilisateurDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");

        if (utilisateur != null) {
            try {
                utilisateurDao.supprimer(utilisateur.getId());
                
                session.invalidate();
                
                response.sendRedirect(request.getContextPath() + "/inscription");
                return;
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/dinosaures");
                return;
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/connexion");
    }
}