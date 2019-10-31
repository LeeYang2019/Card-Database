package edu.yang.persistence;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCardHistory;
import edu.yang.entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.yang.testUtils.Database;
import java.util.List;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * unit tests for user class
 * @author Yang
 */
class UserDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    ProjectDao projectDao;

    /**
     * run these before each test
     */
    @BeforeEach
    void setUp() {
        projectDao = new ProjectDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verify success in getting a collector by id
     */
    @Test
    void getUserByIdSuccess() {
        User newUser = (User)projectDao.getById(1);
        assertEquals("leeyang2019", newUser.getUserName());
    }

    /**
     * verify success in updating an existing user
     */
    @Test
    void saveOrUpdateSuccess() {
        User newUser = (User)projectDao.getById(1);
        newUser.setUserName("jimmybutler");
        assertEquals("jimmybutler", newUser.getUserName());
    }

    /**
     * verify successful insert of a new user
     */
    @Test
    void insertUserSuccess() {
        User newUser = new User("redRainbow19", "brownTurnip", "general");
        int id = projectDao.insert(newUser);
        User updatedUser = (User)projectDao.getById(4);
        assertNotEquals(0, id);
        assertEquals("redRainbow19", updatedUser.getUserName());
    }

    /**
     * verify successful get of cards by user
     */
    @Test
    void getAllCardsByUserSuccess() {
        User newUser = (User)projectDao.getById(1);
        newUser.getCards();
        assertEquals(2, newUser.getCards().size());

        for (YugiohCard card : newUser.getCards()) {
            System.out.println(card.getCardName());
        }
    }

    /**
     * verify successful get general users
     */
    @Test
     void getAllUsersByGeneralRoleSuccess() {
         List<User> users = projectDao.getAllByProperty("role", "general");
         assertEquals(2, users.size());
     }

    /**
     * verify success get of admin users
     */
    @Test
     void getAllUsersByAdminRoleSuccess() {
        List<User> users = projectDao.getAllByProperty("role", "admin");
        assertEquals(1, users.size());
     }

    /**
     * verify successful delete of a user
     */
    @Test
    void deleteUserSuccess() {
        User newUser = (User) projectDao.getById(2);
        projectDao.delete(newUser);
        assertEquals(2, projectDao.getAll().size());
    }
}