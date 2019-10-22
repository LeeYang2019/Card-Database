package edu.yang.persistence;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCardHistory;
import edu.yang.entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.yang.testUtils.Database;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    UserDao newDao;
    YugiohCard yugiohCardDao;

    @BeforeEach
    void setUp() {
        newDao = new UserDao();
       // yugiohCardDao = new YugiohCardDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        logger.info("hello from the other side, Lee Yang");
    }

    /**
     * verify success in getting a collector by id
     */
    @Test
    void getCollectorById() {
        User newUser = newDao.getById(1);
        assertEquals("leeyang2019", newUser.getUserName());
    }

    @Test
    void saveOrUpdate() {
        User newUser = newDao.getById(1);
        newUser.setUserName("jimmybutler");
        newDao.saveOrUpdate(newUser);
        assertEquals("jimmybutler", newUser.getUserName());
    }

    /**
     * verify successful insert of a collector
     */
    @Test
    void insertCollector() {
        User newUser = new User("redRainbow19", "brownTurnip");
        int id = newDao.insert(newUser);
        assertNotEquals(0, id);
        assertEquals(3, newDao.getAll().size());
    }


    /**
     * Verify successful insert of an collector and card
     */
    @Test
    void insertWithCardSuccess() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        User newUser = new User("Jimmer", "yang201917");
        YugiohCard newYugiohCard = new YugiohCard("Dark Magician of Chaos", "Monster", newUser);
        YugiohCardHistory entry = new YugiohCardHistory(ts, 1, 35, newYugiohCard);

    }

    /**
     * verify successful delete of a collector
     */
    @Test
    void deleteCollector() {
        User newUser = newDao.getById(2);
        newDao.delete(newUser);
        assertEquals(2, newDao.getAll().size());
    }
}