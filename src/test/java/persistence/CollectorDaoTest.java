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
        logger.info("hello lee");
    }

    /**
     * verify success insert of a collector
     */
    void insert() {
        Collector newCollector = new Collector("groggySam", "2k19Sun");
        int id = newDao.insert(newCollector);
        assertNotEquals(0, id);
    }

    /**
     * verify successful saveOrUpdate of a collector
     */
    void saveOrUpdate() {
        Collector newCollector = newDao.getById(1);
        newCollector.setUserName("leeyang2020");
        newDao.saveOrUpdate(newCollector);
        assertEquals("leeyang2020", newDao.getById(1).getUserName());
    }

    /**
     * Verify successful insert of an collector and card
     */
    @Test
    void insertWithCardSuccess() {
        Collector newCollector = new Collector("George", "Orwell");
        YugiohCard newCard = new YugiohCard();

        int id = newDao.insert(newCollector);
        assertNotEquals(0, id);
    }

    /**
     * verify successful delete of a collector
     */
    @Test
    void deleteCollector() {
        Collector newCollector = newDao.getById(2);
        newDao.delete(newCollector);
        assertEquals(1, newDao.getAll().size());
    }

    /**
     * verify successful get size of the number of collectors
     */
    @Test
    void getCollectorById() {
        Collector newCollector = newDao.getById(1);
        assertEquals(newCollector.getUserName(), "leeyang2019");
    }

}