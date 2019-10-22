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
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    UserDao newDao;
    ProjectDao projectDao;

    @BeforeEach
    void setUp() {
        newDao = new UserDao();
        projectDao = new ProjectDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verify success in getting a collector by id
     */
    @Test
    void getCollectorByIdSuccess() {
        User newUser = (User)projectDao.getById(1);
        assertEquals("leeyang2019", newUser.getUserName());
    }

    @Test
    void saveOrUpdateSuccess() {
        User newUser = (User)projectDao.getById(1);
        newUser.setUserName("jimmybutler");
        newDao.saveOrUpdate(newUser);
        assertEquals("jimmybutler", newUser.getUserName());
    }

    /**
     * verify successful insert of a collector
     */
    @Test
    void insertCollectorSuccess() {
        User newUser = new User("redRainbow19", "brownTurnip");
        int id = projectDao.insert(newUser);
        assertNotEquals(0, id);
        assertEquals("redRainbow19", newDao.getById(3).getUserName());
    }

    /**
     * verify successful delete of a collector
     */
    @Test
    void deleteCollectorSuccess() {
        User newUser = (User)projectDao.getById(1);
        System.out.println("The name to delete is " + newUser.getUserName());
        projectDao.delete(newUser);
        System.out.println(newUser.getUserName());
        assertEquals(2, newDao.getAll().size());
    }
}