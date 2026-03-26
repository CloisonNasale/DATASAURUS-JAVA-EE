package com.sdzee.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sdzee.dao.DAOException;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.DinosaureDao;


@WebServlet("/supprimer")
public class SupprimerDinosaure extends HttpServlet {
    private DinosaureDao dinosaureDao;

    public void init() throws ServletException {
        this.dinosaureDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getDinosaureDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        
        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                dinosaureDao.supprimer(id);
            } catch (NumberFormatException | DAOException e) {
            }
        }

        response.sendRedirect(request.getContextPath() + "/dinosaures");
    }
}