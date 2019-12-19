package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import edu.yang.entity.YugiohCardHistory;
import edu.yang.persistence.ProjectDao;
import edu.yang.service.ProductDetails;
import edu.yang.service.TcgPlayerAPI;
import edu.yang.service.YugiohCardProcessor;
import edu.yang.service.YugiohCardSetsFileReader;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple servlet to add Cards to the db
 * @author Yang
 */

@WebServlet(
        urlPatterns = {"/addEditedCard"}
)

public class AddEditedCard extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String fileName = "cardSets.txt";

    /**
     * POST Method
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //card dao being used
        ProjectDao newYugiohCardDao = new ProjectDao(YugiohCard.class);
        ProjectDao tsDao = new ProjectDao(YugiohCardHistory.class);
        ProjectDao userDao = new ProjectDao(User.class);

        HttpSession session = req.getSession();

        Map<String, Object> userInputs = new HashMap<>();

        //get current tmstamp
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

        //get user
        User loggedInUser = (User) userDao.getByProperty("userName", req.getRemoteUser());

        //get user inputs
        userInputs.put("cardName", req.getParameter("cardName"));
        userInputs.put("cardType", req.getParameter("cardType"));
        userInputs.put("cardRarity", req.getParameter("cardRarity"));
        userInputs.put("cardEdition", req.getParameter("cardEdition"));
        userInputs.put("cardSet", req.getParameter("cardSet"));
        userInputs.put("cardIndex", req.getParameter("cardIndex"));
        userInputs.put("cardQuantity", Integer.parseInt(req.getParameter("cardQuantity")));
        userInputs.put("user", loggedInUser);

        //get card that was updated
        int cardId = (int)session.getAttribute("cardToUpdate");
        ProjectDao yugiohCardDao = new ProjectDao(YugiohCard.class);
        YugiohCard updateCard = (YugiohCard)yugiohCardDao.getById(cardId);

        /*
        NOTE: was not able to fully implement, created the following to work on for PH2
        @TODO: if there is a change in qty, if qty is greater than current qty add
        @TODO: if there is a change in qty, if qty is less than current qty subtract
        @TODO: if there is a change in qty, if qty is less than or equal to 0, update qty and change card status to "delete"
         */

        //update card
        updateCard.setCardName((String)userInputs.get("cardName"));
        updateCard.setCardType((String)userInputs.get("cardType"));
        updateCard.setCardRarity((String)userInputs.get("cardRarity"));
        updateCard.setCardEdition((String)userInputs.get("cardEdition"));
        updateCard.setCardSet((String)userInputs.get("cardSet"));
        updateCard.setIndex((String)userInputs.get("cardIndex"));
        updateCard.setQuantity(Integer.parseInt(req.getParameter("cardQuantity")));
        yugiohCardDao.saveOrUpdate(updateCard);

        req.setAttribute("cards", loggedInUser.getCards());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }

}