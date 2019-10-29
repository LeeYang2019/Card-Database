package edu.yang.persistence;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.yang.testUtils.Database;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * YugiohCardDaoTest
 */
class YugiohCardDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    ProjectDao projectDao;
    ProjectDao userDao;

    @BeforeEach
    void setUp() {
        projectDao = new ProjectDao(YugiohCard.class);
        userDao = new ProjectDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verify successful get of a card by id
     */
    @Test
    void getCardByIDSuccess() {
        YugiohCard newCard = (YugiohCard)projectDao.getById(1);
        logger.info("new card: " + newCard.getCardName());
        assertEquals("Dark Magician", newCard.getCardName());
    }

    /**
     * verify successful get of all cards by cardName
     */
    @Test
    void getCardsByCardName() {
        List<YugiohCard> cards = projectDao.getAllByProperty("cardName", "Dark Magician");
        assertEquals(2, cards.size());

        logger.info("List of returned cards by similar names: ");
        for (YugiohCard card : cards) {
            logger.info(card.getCardName());
        }
        logger.info("------------------------------------------");
    }

    /**
     * verify successful get of all cards by cardType
     */
    @Test
    void getCardsByCardType() {
        List<YugiohCard> cards = projectDao.getAllByProperty("cardType", "Monster");
        assertEquals(2, cards.size());

        logger.info("List of returned cards by type: ");
        for (YugiohCard card : cards) {
            logger.info(card.getCardName());
        }
        logger.info("------------------------------------------");
    }

    /**
     * verify successfule get of all cards by cardRarity
     */
    @Test
    void getCardsByCardRarity() {
        List<YugiohCard> cards = projectDao.getAllByProperty("cardRarity", "Secret");
        assertEquals(1, cards.size());

        logger.info("List of returned cards by rarity: ");
        for (YugiohCard card : cards) {
            logger.info(card.getCardName());
        }
        logger.info("------------------------------------------");
    }

    @Test
    void getCardsByCardSet() {
        List<YugiohCard> cards = projectDao.getAllByProperty("cardSet", "LOB");
        assertEquals(1, cards.size());

        logger.info("List of returned cards by card set: ");
        for (YugiohCard card : cards) {
            logger.info(card.getCardName());
        }
        logger.info("------------------------------------------");

    }


    /**
     * verify successful insert
     */
    @Test
    void insertWithUserSuccess() {
        User updateUser = (User)userDao.getById(1);
        YugiohCard newCard = new YugiohCard("Buster Blader", "Monster", "Ultra", "PSV", "EN0035", 52.00, 1, "not sold", "hello", updateUser);
        updateUser.addCard(newCard);
        int id = projectDao.insert(newCard);

        logger.info(updateUser.getCards().size());
        logger.info("List of returned cards by user: " + updateUser.getUserName());
        for (YugiohCard card : updateUser.getCards()) {
            logger.info(card.getCardName());
        }
        logger.info("------------------------------------------");


    }

    /**
     * verify successful get all by cardSet
     */
    @Test
    void getAllCards() {
        // List<YugiohCard> cards = yugiohDao.getAllByCardSet("Dark Magician");
        //assertEquals(cards.size(), 2);
        //logger.info(cards.toString());
    }



}