package edu.yang.controller;

import edu.yang.entity.YugiohCard;
import edu.yang.persistence.ProjectDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A simple servlet that takes a card id and then gets a card by the id and pushes it the editCard jsp
 * @author Yang
 */

@WebServlet(
        urlPatterns = {"/editCard"}
)

public class EditCard extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String input = req.getParameter("param");
        int id = Integer.parseInt(input);

        logger.info("id to remove: " + id);

        ProjectDao yugiohCardDao = new ProjectDao(YugiohCard.class);
        YugiohCard newYugiohCard = (YugiohCard)yugiohCardDao.getById(id);

        logger.info("returned card is : " + newYugiohCard.getCardName());

        req.setAttribute("card", newYugiohCard);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/editCard.jsp");
        dispatcher.forward(req, resp);

    }
}