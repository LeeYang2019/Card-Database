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

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProjectDao newYugiohCardDao = new ProjectDao(YugiohCard.class);

        //get user input
        String searchTerm = req.getParameter("searchTerm");
        String searchType = req.getParameter("searchType");

        logger.info("searchTerm: " + searchTerm);
        logger.info("searchType: " + searchType);

        //if there is not an input, searchTerm is null
        if (searchTerm != null) {
            try {
                req.setAttribute("cards", newYugiohCardDao.getAllByPropertyLike(searchType, searchTerm));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("cards", newYugiohCardDao.getAll()); //for view all users
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);

    }
}