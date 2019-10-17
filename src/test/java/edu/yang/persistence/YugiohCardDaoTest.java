package edu.yang.persistence;

import edu.yang.entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.yang.testUtils.Database;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * YugiohCardDaoTest
 */
class YugiohCardDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    YugiohCardDao yugiohDao;

    @BeforeEach
    void setUp() {
        yugiohDao = new YugiohCardDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verify successful insert
     */
    @Test
    void insert() {
        YugiohCard newCard = new YugiohCard();
        newCard.setCardName("Buster Blader");
        newCard.setCardRarity("Ultra");
        newCard.setCardSet("LON-EN035");
        newCard.setCardQuantity(1);
        newCard.setCardType("Monster");

    }

    /**
     * verify successful get by id
     */
    @Test
    void getByCardId() {
        YugiohCard newCard = yugiohDao.getByCardId(1);
        assertEquals(newCard.getCardName(), "Dark Magician");
        logger.info(newCard.getCardName());
    }

    /**
     * verify successful get by cardName
     */
    @Test
    void getByCardName() {
        YugiohCard newCard = yugiohDao.getByCardName("Dark Magician");
        assertEquals(newCard.getCardName(), "Dark Magician");
        logger.info(newCard.getCardName());
    }

    /**
     * verify successful get all by cardName
     */
    @Test
    void getAllByCardName() {
        List<YugiohCard> cards = yugiohDao.getAllByCardName("Dark Magician");
        assertEquals(cards.size(), 2);
        logger.info(cards.toString());

   }

    /**
     * verify successful get all by cardType
     */
   @Test
   void getAllByCardType() {

   }

    /**
     * verify successful get all by cardSet
     */
   @Test
   void getAllByCardSet() {

   }

}