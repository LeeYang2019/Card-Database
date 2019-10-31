package edu.yang.persistence;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
import edu.yang.entity.YugiohCardHistory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.yang.testUtils.Database;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * YugiohCardDaoTest
 */
class YugiohCardDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    ProjectDao cardDao;
    ProjectDao userDao;
    ProjectDao tsDao;

    @BeforeEach
    void setUp() {
        cardDao = new ProjectDao(YugiohCard.class);
        userDao = new ProjectDao(User.class);
        tsDao = new ProjectDao(YugiohCardHistory.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verify successful get of a card by id
     */
    @Test
    void getCardByIDSuccessSuccess() {
        YugiohCard newCard = (YugiohCard)cardDao.getById(1);
        logger.info("new card: " + newCard.getCardName());
        assertEquals("Dark Magician", newCard.getCardName());
    }

    /**
     * verify successful get of all cards by cardName
     */
    @Test
    void getCardsByCardNameSuccess() {
        List<YugiohCard> cards = cardDao.getAllByPropertyLike("cardName", "Dark Magician");
        assertEquals(2, cards.size());
    }

    /**
     * verify successful get of all cards by cardType
     */
    @Test
    void getCardsByCardTypeSuccess() {
        List<YugiohCard> cards = cardDao.getAllByProperty("cardType", "Monster");
        assertEquals(2, cards.size());
    }

    /**
     * verify successful get of all cards by cardRarity
     */
    @Test
    void getCardsByCardRaritySuccess() {
        List<YugiohCard> cards = cardDao.getAllByProperty("cardRarity", "Secret");
        assertEquals(1, cards.size());
    }

    /**
     * verify successful get of all cards by cardSet
     */
    @Test
    void getCardsByCardSetSuccess() {
        List<YugiohCard> cards = cardDao.getAllByProperty("cardSet", "LOB");
        assertEquals(1, cards.size());
    }

    /**
     * verify successfule get of all cards by status (card)
     */
    @Test
    void getCardsByCardStatusSuccess() {
        List<YugiohCard> cards = cardDao.getAllByProperty("status", "sold");
        assertEquals(0, cards.size());
    }

    /**
     * verify successful get all by cardSet
     */
    @Test
    void getAllCardsSuccess() {
        List<YugiohCard> cards = cardDao.getAll();
        assertEquals(cards.size(), 2);
        logger.info(cards.toString());
    }

    /**
     * verify successful insert with user and timestamp (yugiohcardHistory) success
     */
    @Test
    void insertWithUserAndTimeStampSuccess() {

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

        User updateUser = (User)userDao.getById(1);
        YugiohCard newCard = new YugiohCard("Buster Blader", "Monster", "Ultra", "PSV", "EN035", 52.00, 1, "not sold", "hello", updateUser);
        YugiohCardHistory entry = new YugiohCardHistory(80.00, newCard, ts);
        updateUser.addCard(newCard);
        newCard.addEntry(entry);
        int id = cardDao.insert(newCard);
        int entryId = tsDao.insert(entry);

        //assertEquals(0, id);
        //assertEquals(0, entryId);
        assertEquals(3, updateUser.getCards().size());

        for (YugiohCard card : updateUser.getCards()) {
            logger.info("card : " + card.getCardName());
        }

    }

    /**
     * verify successful save or update
     */
    @Test
    void saveOrUpdateSuccess() {
        YugiohCard updateCard = (YugiohCard)cardDao.getById(1);
        updateCard.setCardName("Dark Magician of Chaos");
        assertEquals("Dark Magician of Chaos", updateCard.getCardName());
        logger.info("updated card: " + updateCard.getCardName());
    }

    /**
     * verify successful delete of a card
     */
    @Test
    void deleteWithUserAndYugiohCardUpdateHistorySuccess() {

        YugiohCard deleteCard = (YugiohCard) cardDao.getById(2); //dark magician girl
        cardDao.delete(deleteCard);

    }
}