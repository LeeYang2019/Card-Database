package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import edu.yang.entity.YugiohCardHistory;
import edu.yang.persistence.ProjectDao;
import edu.yang.service.ProductDetails;
import edu.yang.service.TcgPlayerAPI;
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

        Map<String, Object> userInputs = new HashMap<>();

        //get current tmstamp
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

        User loggedInUser = (User) userDao.getByProperty("userName", req.getRemoteUser());

        userInputs.put("cardName", req.getParameter("cardName"));
        userInputs.put("cardType", req.getParameter("cardType"));
        userInputs.put("cardRarity", req.getParameter("cardRarity"));
        userInputs.put("cardEdition", req.getParameter("cardEdition"));
        userInputs.put("cardSet", req.getParameter("cardSet"));
        userInputs.put("cardIndex", req.getParameter("cardIndex"));
        userInputs.put("cardQuantity", Integer.parseInt(req.getParameter("cardQuantity")));
        userInputs.put("user", loggedInUser);

        YugiohCard newYugiohCard = cardProcessor(userInputs);

        //update tms
        YugiohCardHistory entry = new YugiohCardHistory(newYugiohCard.getPrice(), newYugiohCard, ts);
        newYugiohCard.addEntry(entry);
        int id = newYugiohCardDao.insert(newYugiohCard);
        int entryId = tsDao.insert(entry);

        req.setAttribute("cards", loggedInUser.getCards());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }

    public YugiohCard cardProcessor(Map<String, Object> userInputs) {

        //local variables
        YugiohCardSetsFileReader helper = new YugiohCardSetsFileReader(); //returning null
        TcgPlayerAPI apiHelper = new TcgPlayerAPI();

        //get the fullset name from cardSet
        String prodFullName = helper.getProductName((String)userInputs.get("cardSet"));

        logger.info((String)userInputs.get("cardName"));
        logger.info(prodFullName);
        logger.info((String)userInputs.get("cardRarity"));
        logger.info((String)userInputs.get("cardSet"));

        //get the productId
        int productId = apiHelper.getProductId((String)userInputs.get("cardName"), prodFullName, (String)userInputs.get("cardRarity"));

        //get the correct name
        String correctName = apiHelper.getCardName(productId);

        //get the marketPrice
        Double marketPrice =  apiHelper.getMarketPrice(productId, (String)userInputs.get("cardEdition"));

        //get the image
        String imageUrl = apiHelper.getCardImage(productId);

        return new YugiohCard(correctName, (String)userInputs.get("cardType"), (String)userInputs.get("cardRarity"),
                (String)userInputs.get("cardEdition"),(String)userInputs.get("cardSet"), prodFullName,
                (String)userInputs.get("cardIndex"), marketPrice, (int) userInputs.get("cardQuantity"),"unsold", imageUrl,
                (User) userInputs.get("user"));
    }


    /**
     * checks is a card already exists and returns a true/false response
     * @param card card to check if exists
     * @return boolean
     */
    public boolean validateIfExist(YugiohCard card) {
        return false;
    }
}
