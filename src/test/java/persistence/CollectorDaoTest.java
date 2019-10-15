package persistence;

import entity.Collector;
import entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
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
    }

    /**
     * Verify successful insert of an author and book
     */
    @Test
    void insertWithBookSuccess() {
        Collector newCollector = new Collector("George", "Orwell");
        YugiohCard newCard = new YugiohCard();

        int id = newDao.insert(newCollector);
        assertNotEquals(0, id);
    }

}