package persistence;

import entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testUtils.Database;

import static org.junit.jupiter.api.Assertions.*;

class CardDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    CardDao yugiohDao;

    @BeforeEach
    void setUp() {
        yugiohDao = new CardDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getByCardId() {
        YugiohCard newCard = new YugiohCard();
        newCard = yugiohDao.getByCardId(1);
        assertEquals(newCard.getCardName(), "Dark Magician");
    }


    @Test
    void getByCardName() {
        YugiohCard newCard = new YugiohCard();
        YugiohCard secondCard = new YugiohCard();
        newCard = yugiohDao.getByCardName("Dark Magician");
        secondCard = yugiohDao.getByCardId(1);
        assertEquals(newCard.getCardName(), secondCard.getCardName());
    }

    @Test
    void getByCardType() {
    }

    @Test
    void getByCardRarity() {
    }

    @Test
    void getByCardSet() {
    }

    @Test
    void getByCardPrice() {
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void insert() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }
}