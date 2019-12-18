package edu.yang.service;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

/**
 * reads cardSets.txt file
 * parses tokens and creates hashMap with key-value pairs
 * @author nyang
 */
public class YugiohCardProcessor {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());

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
}
