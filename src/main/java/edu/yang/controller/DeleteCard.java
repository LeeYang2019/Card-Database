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
        urlPatterns = {"/deleteCard"}
)

public class DeleteCard extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String input = req.getParameter("param");
        int id = Integer.parseInt(input);

        ProjectDao yugiohCardDao = new ProjectDao(YugiohCard.class);
        YugiohCard updateCard = (YugiohCard)yugiohCardDao.getById(id);
        updateCard.setStatus("Delete");
        yugiohCardDao.saveOrUpdate(updateCard);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}