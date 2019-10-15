package persistence;

import entity.User;
import entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testUtils.Database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verify successful insert of an author and book
     */
    @Test
    void insertWithBookSuccess() {
        User newUser = new User("George", "Orwell");
        YugiohCard newCard = new YugiohCard();

        int id = userDao.insert(newUser);
        assertNotEquals(0, id);
    }
}