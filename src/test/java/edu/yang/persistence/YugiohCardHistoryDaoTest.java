package edu.yang.persistence;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCardHistory;
import edu.yang.entity.YugiohCard;
import edu.yang.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

class YugiohCardHistoryDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    ProjectDao cardDao;
    ProjectDao collectorDao;
    ProjectDao entryDao;

    @BeforeEach
    void setUp() {
        cardDao = new ProjectDao(YugiohCard.class);
        collectorDao = new ProjectDao(User.class);
        entryDao = new ProjectDao(edu.yang.entity.YugiohCardHistory.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }


    @Test
    void getById() {
        YugiohCard newYugiohCard = (YugiohCard)cardDao.getById(1);
        logger.info(newYugiohCard.toString());
       // assertEquals("Dark Magician", newYugiohCard.getCardName());
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

    @Test
    void getByProperty() {
    }

    @Test
    void getAllByProperty() {
    }
}