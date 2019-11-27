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
 * A simple servlet to search and return cards in the user's database
 */
@WebServlet(
        urlPatterns = {"/sellCard"}
)

public class SellCard extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String input = req.getParameter("param");
        int id = Integer.parseInt(input);

        ProjectDao yugiohCardDao = new ProjectDao(YugiohCard.class);
        YugiohCard newYugiohCard = (YugiohCard)yugiohCardDao.getById(id);
        newYugiohCard.setStatus("Selling");
        yugiohCardDao.saveOrUpdate(newYugiohCard);

        req.setAttribute("card", newYugiohCard);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/card.jsp");
        dispatcher.forward(req, resp);
    }
}