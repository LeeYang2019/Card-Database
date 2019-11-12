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

        HttpSession session = req.getSession();
        ProjectDao userDao = new ProjectDao(User.class);
        ProjectDao yugiohCardDao = new ProjectDao(YugiohCard.class);

        User loggedInUser = (User)userDao.getByProperty("userName", req.getRemoteUser());

        Map<String, Object> propsAndValues = new HashMap<>();

        String searchTerm = req.getParameter("searchTerm");
        String searchType = req.getParameter("searchType");

        logger.info("searchTerm : " + searchTerm);
        logger.info("searchType : " + searchType);

        //if user input is provided, return results matching the input
        if (!searchTerm.isEmpty()) {

            propsAndValues.put("cardName", searchTerm);
            propsAndValues.put("user", loggedInUser);

            try {

                if (!searchType.equalsIgnoreCase("Monster") ) {
                    logger.info(searchType.equalsIgnoreCase("Choose..."));
                    propsAndValues.put("cardType", searchType);
                }

                List<YugiohCard> userCards =
                        yugiohCardDao.findByPropertyEqual(propsAndValues);

                logger.info("this collection size is " + userCards.size());

                req.setAttribute("cards", userCards);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            req.setAttribute("cards", loggedInUser.getCards());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}