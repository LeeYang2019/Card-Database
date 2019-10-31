package edu.yang.controller;

import edu.yang.entity.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A simple servlet to search.
 */

@WebServlet(
        urlPatterns = {"/searchCards"}
)

public class SearchCards extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        ProjectDao userDao = new ProjectDao(User.class);

       logger.info("this user is : " + req.getRemoteUser());

       User loggedInUser = (User) userDao.getByProperty("userName", req.getRemoteUser());

       logger.info("this user id is : " + loggedInUser.getId());

        //card dao being used
        ProjectDao newYugiohCardDao = new ProjectDao(YugiohCard.class);

        //get user input
        String searchTerm = req.getParameter("searchTerm");
        //String searchType = req.getParameter("searchType");
        String searchType = "cardName";

        //get cards with the search parameters
        if (searchTerm != null) {
            try {
                req.setAttribute("cards", newYugiohCardDao.getAllByPropertyLike(searchType, searchTerm));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("cards", newYugiohCardDao.getAll()); //return all cards in the user collection
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);

    }
}