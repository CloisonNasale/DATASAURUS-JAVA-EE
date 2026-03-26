package com.sdzee.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Dinosaure;
import com.sdzee.dao.DAOException;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.DinosaureDao;

/**
 * Servlet implementation class ModifierDinosaure
 */
@WebServlet("/modifier")
public class ModifierDinosaure extends HttpServlet {
    private DinosaureDao dinosaureDao;

    public void init() throws ServletException {
        this.dinosaureDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getDinosaureDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            Dinosaure dinosaure = dinosaureDao.trouver(id);
            request.setAttribute("dinosaure", dinosaure);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/modifierDinosaure.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("idDino"));
        String nom = request.getParameter("nomDino");
        String espece = request.getParameter("especeDino");
        String ere = request.getParameter("ereDino");
        String regime = request.getParameter("regimeDino");

        Dinosaure dino = new Dinosaure();
        dino.setId(id);
        dino.setNom(nom);
        dino.setEspece(espece);
        dino.setEre(ere);
        dino.setRegimeAlimentaire(regime);

        try {
            dinosaureDao.modifier(dino);
            response.sendRedirect(request.getContextPath() + "/dinosaures");
        } catch (DAOException e) {
            request.setAttribute("erreur", e.getMessage());
            this.getServletContext().getRequestDispatcher("/WEB-INF/modifierDinosaure.jsp").forward(request, response);
        }
    }
}
