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
import java.util.List;

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

        logger.info("the value of searchTerm is " + searchTerm.isEmpty());

        //if user input is provided, return results matching the input
        if (!searchTerm.isEmpty()) {
            try {

                List<YugiohCard> userCards = newYugiohCardDao.getAllByProperty(searchType, searchTerm);

                logger.info("the size: " + userCards.size());

                for (YugiohCard card : userCards) {
                    if (!loggedInUser.getCards().contains(card)) {
                        logger.info("user collection contains this card: " + loggedInUser.getCards().contains(card));
                        userCards.remove(card);
                    }
                }

                //this returns all cards of a type; needs to be all cards of a type belonging to user
                req.setAttribute("cards", userCards);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { //return all cards in the users collection
            req.setAttribute("cards", loggedInUser.getCards()); //return all cards in the user collection
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);

    }
}