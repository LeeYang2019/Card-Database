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

public class AddCards extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String fileName = "cardSets.txt";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //card dao being used
        ProjectDao newYugiohCardDao = new ProjectDao(YugiohCard.class);
        ProjectDao tsDao = new ProjectDao(YugiohCardHistory.class);
        ProjectDao userDao = new ProjectDao(User.class);

        //get current tmstamp
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

        //get user input
        String cardName = req.getParameter("cardName");
        String cardType = req.getParameter("cardType");
        String cardRarity = req.getParameter("cardRarity");
        String cardEdition = req.getParameter("cardEdition");
        String cardSet = req.getParameter("cardSet");
        String cardIndex = req.getParameter("cardIndex");
        String cardQuantity = req.getParameter("cardQuantity");
        int qty = Integer.parseInt(cardQuantity);
        String imageUrl = "";

        //get this user
        HttpSession session = req.getSession();
        User loggedInUser = (User) userDao.getByProperty("userName", req.getRemoteUser());

        YugiohCardSetsFileReader newCardReader = new YugiohCardSetsFileReader();
        Map<String, String> newCardSetMap = newCardReader.readFile(fileName);
        String productName = newCardSetMap.get(cardSet);
        TcgPlayerAPI tcgPlayerAPI = new TcgPlayerAPI();

        int cardId = tcgPlayerAPI.getProductId(cardName, productName, cardRarity);

        List<ProductDetails> productDetailsList = tcgPlayerAPI.getProductDetails(cardId);
        double marketPrice = tcgPlayerAPI.getMarketPrice(cardId, cardEdition);

        for (int i = 0; i < productDetailsList.size(); i++) {

            cardName = productDetailsList.get(i).getCleanName().trim();
            imageUrl = productDetailsList.get(i).getImageUrl();
        }

        //create a card object
        YugiohCard newCard = new YugiohCard(cardName, cardType, cardRarity, cardEdition, cardSet, productName, cardIndex, marketPrice, qty, "unsold", imageUrl, loggedInUser);

        logger.info("new card to be inserted :" + newCard.toString());

        YugiohCardHistory entry = new YugiohCardHistory(marketPrice, newCard, ts);
        newCard.addEntry(entry);

        if (validateIfExist(newCard) == false) {
            int id = newYugiohCardDao.insert(newCard);
        } else {
            YugiohCard updateCard = (YugiohCard)newYugiohCardDao.getById(newCard.getId());
            updateCard.setStatus("unsold");
            newYugiohCardDao.saveOrUpdate(updateCard);
        }

        int entryId = tsDao.insert(entry);

        req.setAttribute("cards", loggedInUser.getCards());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * checks is a card already exists and returns a true/false response
     * @param card card to check if exists
     * @return boolean
     */
    public boolean validateIfExist(YugiohCard card) {

        ProjectDao newYugiohCardDao = new ProjectDao(YugiohCard.class);
        Map<String, Object> propsAndValues = new HashMap<>();
        propsAndValues.put("cardName", card.getCardName());
        propsAndValues.put("cardRarity", card.getCardRarity());
        propsAndValues.put("cardEdition", card.getCardEdition());
        propsAndValues.put("cardSetFullName", card.getSetName());

        List<YugiohCard> userCards = newYugiohCardDao.findByPropertyEqual(propsAndValues);

        for (YugiohCard e : userCards) {
            if (e.equals(card)) {
                return true;
            }
        }
        return false;
    }
}