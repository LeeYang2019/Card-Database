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
 * A simple servlet to gets the id of a card, retrieves the card corresponding to the id and forwards the card to the displayCard.jsp
 * @author Yang
 */
@WebServlet(
        urlPatterns = {"/displayCard"}
)

public class DisplayCard extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * GET METHOD
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get card from param
        String input = req.getParameter("param");
        int id = Integer.parseInt(input);
        ProjectDao yugiohCardDao = new ProjectDao(YugiohCard.class);
        YugiohCard newYugiohCard = (YugiohCard)yugiohCardDao.getById(id);

        //redirects card to display.jsp
        req.setAttribute("card", newYugiohCard);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/displayCard.jsp");
        dispatcher.forward(req, resp);
    }
}