package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import edu.yang.entity.YugiohCardHistory;
import edu.yang.persistence.ProjectDao;
import edu.yang.service.YugiohCardProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        urlPatterns = {"/addCards"}
)

public class AddCard extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //card dao being used
        ProjectDao newYugiohCardDao = new ProjectDao(YugiohCard.class);
        ProjectDao tsDao = new ProjectDao(YugiohCardHistory.class);
        ProjectDao userDao = new ProjectDao(User.class);

        //local variables
        Map<String, Object> userInputs = new HashMap<>();
        Map<String, Object> propsAndValues = new HashMap<>();
        List<YugiohCard> userCards;


        //get current tmstamp
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

        //get the loggedInUser
        User loggedInUser = (User) userDao.getByProperty("userName", req.getRemoteUser());

        //get the loggedInUser's collection of cards
        propsAndValues.put("user", loggedInUser);
        userCards = newYugiohCardDao.findByPropertyEqual(propsAndValues);

        //get user inputs
        userInputs.put("cardName", req.getParameter("cardName"));
        userInputs.put("cardType", req.getParameter("cardType"));
        userInputs.put("cardRarity", req.getParameter("cardRarity"));
        userInputs.put("cardEdition", req.getParameter("cardEdition"));
        userInputs.put("cardSet", req.getParameter("cardSet"));
        userInputs.put("cardIndex", req.getParameter("cardIndex"));
        userInputs.put("cardQuantity", Integer.parseInt(req.getParameter("cardQuantity")));
        userInputs.put("user", loggedInUser);

        //call the YugiohCardProcessor and pass the user inputs to create a new yugiohCard
        YugiohCardProcessor cardProcessor = new YugiohCardProcessor();
        YugiohCard newYugiohCard = cardProcessor.cardProcessor(userInputs);

        //check in the user's collection to see if the same card already exists and update it
        for (YugiohCard card : loggedInUser.getCards()) {
            if (card.getCardName().equalsIgnoreCase(newYugiohCard.getCardName())
                    && card.getCardRarity().equalsIgnoreCase(newYugiohCard.getCardRarity())
                    && card.getCardEdition().equalsIgnoreCase(newYugiohCard.getCardEdition()) &&
                    card.getSetName().equalsIgnoreCase(newYugiohCard.getSetName())) {

                card.setQuantity(card.getQuantity() + 1);
                card.setStatus("unsold");
                newYugiohCardDao.saveOrUpdate(card);

                logger.info("card was updated");

            } else  {

                YugiohCardHistory entry = new YugiohCardHistory(newYugiohCard.getPrice(), newYugiohCard, ts);
                newYugiohCard.addEntry(entry);
                int id = newYugiohCardDao.insert(newYugiohCard);
                int entryId = tsDao.insert(entry);

                logger.info("card was inserted");
            }
        }

        req.setAttribute("cards", loggedInUser.getCards());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }
}
