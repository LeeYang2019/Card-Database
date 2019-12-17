package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import edu.yang.persistence.ProjectDao;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Login servlet to get the remote user and store it in the session
 * @author Lee Yang
 */

@WebServlet(
        urlPatterns = {"/login"}
)

public class Login extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String fileName = "cardSets.txt";

    /**
     * handles HTTP GET requests.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("entering login servlet");

        //local variables
        Map<String, Object> propsAndValues = new HashMap<>();
        List<YugiohCard> userCards;

        //create session
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher;

        //get remote user from session
        String userName = req.getRemoteUser();

        //get user from db
        ProjectDao userDao = new ProjectDao(User.class);
        User loggedInUser = (User) userDao.getByProperty("userName", userName);

        //store user in session
        session.setAttribute("user", loggedInUser);

        //push user to map
        propsAndValues.put("user", loggedInUser);

        //return a list of cards based on user
        userCards = updateYugiohCards(propsAndValues);

        req.setAttribute("cards", userCards);

        if (loggedInUser.getCards().size() == 0) {
            dispatcher = req.getRequestDispatcher("/fileupload.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("/index.jsp");
        }

        dispatcher.forward(req, resp);
    }

    /**
     *
     * @param propertyMap
     * @return
     */
    public List<YugiohCard> updateYugiohCards(Map<String, Object> propertyMap) {

        TcgPlayerAPI newPlayerAPI = new TcgPlayerAPI();
        ProjectDao yugiohCardDao = new ProjectDao(YugiohCard.class);
        List<YugiohCard> yugiohCardList = yugiohCardDao.findByPropertyEqual(propertyMap);

        //for each card, get new pricing and update
        for (YugiohCard card : yugiohCardList) {

            int cardId = newPlayerAPI.getProductId(card.getCardName(),getProductSet(card.getCardSet()),card.getCardRarity());
            double marketPrice = newPlayerAPI.getMarketPrice(cardId);
            YugiohCard updateCard  = (YugiohCard)yugiohCardDao.getById(card.getId());
            updateCard.setPrice(marketPrice);
            yugiohCardDao.saveOrUpdate(updateCard);
        }
        return yugiohCardList;
    }

    /**
     * returns the productName
     * @param cardSet cardSet from YugiohCardDb
     * @return productName from cardSetsMap
     */
    private String getProductSet(String cardSet) {

        YugiohCardSetsFileReader newCardReader = new YugiohCardSetsFileReader();
        Map<String, String> newCardSetMap = newCardReader.readFile(fileName);
        newCardSetMap.get(cardSet);
        String productSetName = newCardSetMap.get(cardSet);
        return productSetName;
    }
}