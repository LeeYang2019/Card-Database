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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple servlet to search and return cards in the user's database
 */
@WebServlet(
        urlPatterns = {"/searchCards"}
)

public class SearchCards extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //create session
        HttpSession session = req.getSession();

        //get parameters
        String searchTerm = req.getParameter("searchTerm");
        String searchType = req.getParameter("searchType");

        List<YugiohCard> list = getList(session, searchTerm, searchType);

        session.setAttribute("cards", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * searches the YugiohCard database matching user inputs
     * @param session
     * @param searchTerm
     * @param searchType
     * @return list of YugiohCard objects
     */
    public List<YugiohCard> getList(HttpSession session, String searchTerm, String searchType) {

        ProjectDao yugiohCardDao = new ProjectDao(YugiohCard.class);
        Map<String, Object> propsAndValues = new HashMap<>();
        List<YugiohCard> userCards;

        //get user from session
        User loggedInUser = (User)session.getAttribute("user");
        logger.info("in search servlet, user is : " + loggedInUser.getUserName());

        //if searchTerm is not empty and searchType is choose, return all cards by searchTerm
        if (!searchTerm.isEmpty()) {

            if (searchType.equalsIgnoreCase("All Cards")) {

                logger.info("searchTerm is " + searchTerm + " and searchType is All Cards");
                propsAndValues.put("cardName", searchTerm);
                propsAndValues.put("user", loggedInUser);
                userCards = yugiohCardDao.findByPropertyLike(propsAndValues);

                logger.info("the size of the list is " + userCards.size());
                return userCards;

            } else if (!searchType.equalsIgnoreCase("All Cards")) {

                logger.info("searchTerm is " + searchTerm + " and searchType is " + searchType);
                propsAndValues.put("cardName", searchTerm);
                propsAndValues.put("cardType", searchType);
                propsAndValues.put("user", loggedInUser);
                userCards = yugiohCardDao.findByPropertyLike(propsAndValues);

                logger.info("the size of the list is " + userCards.size());
                return userCards;

            }

        } else {

            if (!searchType.equalsIgnoreCase("All Cards")) {

                logger.info("searchTerm is empty and searchType is All Cards");
                propsAndValues.put("cardType", searchType);
                propsAndValues.put("user", loggedInUser);
                userCards = yugiohCardDao.findByPropertyEqual(propsAndValues);
                return userCards;
            }
        }

        //DEFAULT: searchTerm is empty and searchType is choose, return call cards
        logger.info("searchTerm is empty and searchType is All Cards");
        propsAndValues.put("user", loggedInUser);
        userCards = yugiohCardDao.findByPropertyEqual(propsAndValues);
        return userCards;
    }
}