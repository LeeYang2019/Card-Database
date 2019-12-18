package edu.yang.controller;

import edu.yang.entity.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //local variables
        Map<String, Object> propsAndValues = new HashMap<>();
        List<YugiohCard> userCards;

        //create session
        HttpSession session = req.getSession();
        User updatedUser = (User)session.getAttribute("user");

        String input = req.getParameter("param");
        int id = Integer.parseInt(input);

        ProjectDao yugiohCardDao = new ProjectDao(YugiohCard.class);
        YugiohCard updateCard = (YugiohCard)yugiohCardDao.getById(id);
        updateCard.setQuantity(0);
        updateCard.setStatus("Delete");
        yugiohCardDao.saveOrUpdate(updateCard);

        //push user to map
        propsAndValues.put("status", "unsold");
        propsAndValues.put("user", updatedUser);

        userCards = yugiohCardDao.findByPropertyEqual(propsAndValues);

        req.setAttribute("cards", userCards);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }
}