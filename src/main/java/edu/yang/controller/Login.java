package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import edu.yang.persistence.ProjectDao;
import edu.yang.service.PriceObject;
import edu.yang.service.TcgPlayerAPI;
import edu.yang.service.UploadFileReader;
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
import javax.validation.constraints.Null;
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

        //create session
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher;

        Map<String, Object> propsAndValues = new HashMap<>();
        List<YugiohCard> userCards;

        UploadFileReader cardSetReader = new UploadFileReader();

        //get remote user
        String userName = req.getRemoteUser();

        //get user from db
        ProjectDao userDao = new ProjectDao(User.class);
        User loggedInUser = (User) userDao.getByProperty("userName", userName);

        //store user in session
        session.setAttribute("user", loggedInUser);

        propsAndValues.put("user", loggedInUser);

        userCards = updateYugiohCards(propsAndValues);

        req.setAttribute("cards", userCards);

        if (loggedInUser.getCards().size() == 0) {
            dispatcher = req.getRequestDispatcher("/fileupload.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("/index.jsp");
        }

        dispatcher.forward(req, resp);
    }

    public List<YugiohCard> updateYugiohCards(Map<String, Object> propertyMap) {

        ProjectDao yugiohCardDao = new ProjectDao(YugiohCard.class);
        List<YugiohCard> yugiohCardList = yugiohCardDao.findByPropertyEqual(propertyMap);
        TcgPlayerAPI newPlayerAPI = new TcgPlayerAPI();

        for (YugiohCard card : yugiohCardList) {

            //get productName
            int cardId = newPlayerAPI.getProductId(card.getCardName(),getProductSet(card.getCardSet()),card.getCardRarity());

            logger.info("cardId is : " + cardId);

            //get marketprice of the card
            double marketPrice = getProductMarketPrice(cardId, newPlayerAPI);
        }

        return yugiohCardList;
    }

    private String getProductSet(String cardSet) {

        YugiohCardSetsFileReader newCardReader = new YugiohCardSetsFileReader();
        Map<String, String> newCardSetMap = newCardReader.readFile();
        newCardSetMap.get(cardSet);
        String productSetName = newCardSetMap.get(cardSet);

        logger.info("inside getProductSet method, productName is : " + productSetName);

        return productSetName;
    }

    private double getProductMarketPrice(int cardId, TcgPlayerAPI newApi) {

        List<PriceObject> pricingList  = newApi.getMarketPrice(cardId);

        for (int i = 0; i < pricingList.size(); i++) {
            if (pricingList.get(i).getSubTypeName().equalsIgnoreCase("1st Edition")){
                double marketPrice = (double) pricingList.get(i).getMarketPrice();
                return marketPrice;
            }
        }
        return 0; //0 is failure
    }
}