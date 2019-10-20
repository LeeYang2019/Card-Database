package edu.yang.persistence;

import edu.yang.entity.Collector;
import edu.yang.entity.UpdateHistory;
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

class CollectorDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    CollectorDao newDao;
    YugiohCard yugiohCardDao;

    @BeforeEach
    void setUp() {
        newDao = new CollectorDao();
        yugiohCardDao = new YugiohCardDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        logger.info("hello from the other side");
    }

    /**
     * verify success in getting a collector by id
     */
    @Test
    void getCollectorById() {
        Collector newCollector = newDao.getById(1);
        assertEquals("leeyang2019", newCollector.getUserName());
    }

    @Test
    void saveOrUpdate() {
        Collector newCollector = newDao.getById(1);
        newCollector.setUserName("jimmybutler");
        newDao.saveOrUpdate(newCollector);
        assertEquals("jimmybutler", newCollector.getUserName());
    }

    /**
     * verify successful insert of a collector
     */
    @Test
    void insertCollector() {
        Collector newCollector = new Collector("redRainbow19", "brownTurnip");
        int id = newDao.insert(newCollector);
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

        Collector newCollector = new Collector("Jimmer", "yang201917");
        YugiohCard newYugiohCard = new YugiohCard("Dark Magician of Chaos", "Monster", "Ultra", "IOC-EN035", 35, 1, newCollector);
        UpdateHistory entry = new UpdateHistory(ts, 1, 35, newYugiohCard);
        int id = new
    }

    /**
     * verify successful delete of a collector
     */
    @Test
    void deleteCollector() {
        Collector newCollector = newDao.getById(2);
        newDao.delete(newCollector);
        assertEquals(2, newDao.getAll().size());
    }
}