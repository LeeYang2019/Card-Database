package edu.yang.controller;

import edu.yang.entity.YugiohCard;
import edu.yang.persistence.ProjectDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 */

@WebServlet(
        urlPatterns = {"/searchCards"}
)

public class SearchCards extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProjectDao newYugiohCardDao = new ProjectDao(YugiohCard.class);
        //final Logger logger = LogManager.getLogger(this.getClass());

        //get user input
        String searchTerm = req.getParameter("searchTerm");
        String searchType = req.getParameter("searchType");

        System.out.println("searchTerm: " + searchTerm);
        System.out.println("searchType: " + searchType);

        //if there is not an input, searchTerm is null
        if (searchTerm != null) {
            try {
                req.setAttribute("users", newYugiohCardDao.getAllByPropertyLike(searchTerm, searchType));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("users", newYugiohCardDao.getAll()); //for view all users
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);

    }
}