package persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardDaoTest {

    CardDao yugiohDao;

    @BeforeEach
    void setUp() {
        yugiohDao = new CardDao();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getByCardName() {
        yugiohDao.getByCardName("Dark Magician");
        assertEquals(yugiohDao.getByCardName("Dark Magician"), "Dark Magician");
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