package edu.yang.persistence;

import edu.yang.entity.YugiohCardHistory;
import edu.yang.entity.YugiohPlayer;
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

class YugiohPlayerDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    CollectorDao newDao;
    YugiohCard yugiohCardDao;

    @BeforeEach
    void setUp() {
        newDao = new CollectorDao();
       // yugiohCardDao = new YugiohCardDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        logger.info("hello from the other side");
    }

    /**
     * verify success in getting a collector by id
     */
    @Test
    void getCollectorById() {
        YugiohPlayer newYugiohPlayer = newDao.getById(1);
        assertEquals("leeyang2019", newYugiohPlayer.getUserName());
    }

    @Test
    void saveOrUpdate() {
        YugiohPlayer newYugiohPlayer = newDao.getById(1);
        newYugiohPlayer.setUserName("jimmybutler");
        newDao.saveOrUpdate(newYugiohPlayer);
        assertEquals("jimmybutler", newYugiohPlayer.getUserName());
    }

    /**
     * verify successful insert of a collector
     */
    @Test
    void insertCollector() {
        YugiohPlayer newYugiohPlayer = new YugiohPlayer("redRainbow19", "brownTurnip");
        int id = newDao.insert(newYugiohPlayer);
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

        YugiohPlayer newYugiohPlayer = new YugiohPlayer("Jimmer", "yang201917");
        YugiohCard newYugiohCard = new YugiohCard("Dark Magician of Chaos", "Monster", "Ultra", "IOC-EN035", 35, 1, newYugiohPlayer);
        YugiohCardHistory entry = new YugiohCardHistory(ts, 1, 35, newYugiohCard);

    }

    /**
     * verify successful delete of a collector
     */
    @Test
    void deleteCollector() {
        YugiohPlayer newYugiohPlayer = newDao.getById(2);
        newDao.delete(newYugiohPlayer);
        assertEquals(2, newDao.getAll().size());
    }
}