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
 * A simple servlet to add Cards.
 */

@WebServlet(
        urlPatterns = {"/addCards"}
)

public class AddCards extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //card dao being used
        ProjectDao newYugiohCardDao = new ProjectDao(YugiohCard.class);

        //get user input
        String cardName = req.getParameter("cardName");
        String cardType = req.getParameter("cardType");
        String cardRarity = req.getParameter("cardRarity");
        String cardSet = req.getParameter("cardSet");
        String cardIndex = req.getParameter("cardIndex");
        //double cardPrice = req.getParameter("cardPrice");

        //get this user


        //create a card object
        YugiohCard newCard = new YugiohCard();


        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);

    }
}