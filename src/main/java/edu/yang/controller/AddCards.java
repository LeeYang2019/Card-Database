package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import edu.yang.entity.YugiohCardHistory;
import edu.yang.persistence.ProjectDao;
import edu.yang.service.PriceObject;
import edu.yang.service.ProductDetails;
import edu.yang.service.TcgPlayerAPI;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //card dao being used
        ProjectDao newYugiohCardDao = new ProjectDao(YugiohCard.class);
        ProjectDao tsDao = new ProjectDao(YugiohCardHistory.class);
        ProjectDao userDao = new ProjectDao(User.class);

        TcgPlayerAPI tcgPlayerAPI = new TcgPlayerAPI();

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

        //get user input
        String cardName = req.getParameter("cardName");
        String cardType = req.getParameter("cardType");
        String cardRarity = req.getParameter("cardRarity");
        String cardSet = req.getParameter("cardSet");
        String cardIndex = req.getParameter("cardIndex");
        String cardPrice = req.getParameter("cardPrice");
        double price = Double.parseDouble(cardPrice);
        String cardQuantity = req.getParameter("cardQuantity");
        int qty = Integer.parseInt(cardQuantity);


        String imageUrl = "";

        //get this user
        HttpSession session = req.getSession();
        User loggedInUser = (User) userDao.getByProperty("userName", req.getRemoteUser());

        //check if exists from web and get information back

        Map<String, String> cardSetsMap = (Map) req.getAttribute("yugiohSetsMap");

        System.out.println(cardSetsMap.size());

        cardSetsMap.get(cardSet);

        System.out.println(cardSetsMap.get(cardSet));

        int cardId = tcgPlayerAPI.getProductId(cardName, "The Legend of Blue Eyes White Dragon", "Ultra");
        List<ProductDetails> productDetailsList = tcgPlayerAPI.getProductDetails(cardId);
        List<PriceObject> pricingList = tcgPlayerAPI.getMarketPrice(cardId);

        for (int i = 0; i < productDetailsList.size(); i++) {
            imageUrl = productDetailsList.get(i).getImageUrl();

        }

        for (int i = 0; i < pricingList.size(); i++) {
            if (pricingList.get(i).getSubTypeName().equalsIgnoreCase("Unlimited")) {
                price = (double) pricingList.get(i).getMarketPrice();
            }
        }


        //create a card object
        YugiohCard newCard = new YugiohCard(cardName, cardType, cardRarity, cardSet, cardIndex, price, qty, "unsold", imageUrl, loggedInUser);
        YugiohCardHistory entry = new YugiohCardHistory(price, newCard, ts);
        newCard.addEntry(entry);

        int id = newYugiohCardDao.insert(newCard);
        int entryId = tsDao.insert(entry);

        req.setAttribute("cards", loggedInUser.getCards());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);

    }
}