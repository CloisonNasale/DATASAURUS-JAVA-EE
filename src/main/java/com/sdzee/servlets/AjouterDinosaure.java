package com.sdzee.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.DinosaureDao;
import com.sdzee.beans.Dinosaure;

@WebServlet("/ajouterDinosaure")
public class AjouterDinosaure extends HttpServlet {
    private DinosaureDao dinosaureDao;

    public void init() throws ServletException {
        this.dinosaureDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getDinosaureDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterDinosaure.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nomDino");
        String espece = request.getParameter("especeDino");
        String ere = request.getParameter("ereDino");
        String regime = request.getParameter("regimeDino");

        Dinosaure dino = new Dinosaure();
        dino.setNom(nom);
        dino.setEspece(espece);
        dino.setEre(ere);
        dino.setRegimeAlimentaire(regime);

        try {
            dinosaureDao.creer(dino);
            request.setAttribute("succes", "Dinosaure ajouté avec succès !");
        } catch (Exception e) {
            request.setAttribute("erreur", "Erreur : " + e.getMessage());
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterDinosaure.jsp").forward(request, response);
    }
}