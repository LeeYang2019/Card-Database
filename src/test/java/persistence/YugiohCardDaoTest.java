package persistence;

import entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testUtils.Database;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void getByCardId() {
        YugiohCard newCard = yugiohDao.getByCardId(1);
        assertEquals(newCard.getCardName(), "Dark Magician");
    }


    @Test
    void getByCardName() {
        YugiohCard newCard = yugiohDao.getByCardName("Dark Magician");
        assertEquals(newCard.getCardName(), "Dark Magician");
    }

    @Test
    void getByCardType() {
        YugiohCard newCard = new YugiohCard();
        //newCard = yugiohDao.get
    }

    @Test
    void getByCardRarity() {
        YugiohCard newCard = new YugiohCard();
    }

    @Test
    void getByCardSet() {
        YugiohCard newCard = new YugiohCard();
    }

    @Test
    void getByCardPrice() {
        YugiohCard newCard = new YugiohCard();
    }

    @Test
    void saveOrUpdate() {
        YugiohCard newCard = new YugiohCard();
    }

    @Test
    void insert() {
        YugiohCard newCard = new YugiohCard();
        newCard.setCardName("Buster Blader");
        newCard.setCardRarity("Ultra");
        newCard.setCardPrice(30);
        newCard.setCardType("Monster");
        newCard.setCardSet("LON-035");
        newCard.setCardPrice(25);
        newCard.setCardQuantity(2);
       // newCard.setCollector();
        yugiohDao.insert(newCard);
        yugiohDao.getAll().size();
        assertEquals(3, yugiohDao.getAll().size());
    }

    @Test
    void delete() {
        YugiohCard newCard = new YugiohCard();
    }

    @Test
    void getAll() {
        assertEquals(2, yugiohDao.getAll().size());
    }
}