package persistence;

import entity.Collector;
import entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import testUtils.Database;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CollectorDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    CollectorDao newDao;

    @BeforeEach
    void setUp() {
        newDao = new CollectorDao();
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
        //Collector newCollector = new Collector("George", "Orwell");
        //YugiohCard newCard = new YugiohCard();

        //int id = newDao.insert(newCollector);
        //assertNotEquals(0, id);
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