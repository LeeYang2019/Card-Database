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
    ProjectDao updateHistoryDao;
    ProjectDao userDao;
    ProjectDao cardDao;

    @BeforeEach
    void setUp() {
        updateHistoryDao = new ProjectDao(YugiohCardHistory.class);
        userDao = new ProjectDao(User.class);
        cardDao = new ProjectDao(YugiohCard.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getByIdSuccess() {
        YugiohCardHistory entry = (YugiohCardHistory)updateHistoryDao.getById(1);
        System.out.println("Entry id: " + entry.getId());
        System.out.println("Entry cardId: " + entry.getYugiohCard());
        System.out.println("Entry update_dt: " + entry.getTimeStamp());
    }

    @Test
    void insertWithCardAndUserSuccess() {

        User updateUser = (User)userDao.getById(1);
        YugiohCard newYugiohCard = new YugiohCard();
        YugiohCardHistory entry = new YugiohCardHistory();

        newYugiohCard.setCardName("Dark Magician of Chaos");
        newYugiohCard.setCardType("Monster");
        newYugiohCard.setUser(updateUser);

        //add card to user collection
        updateUser.addCard(newYugiohCard);

        entry.setYugiohCard(newYugiohCard);
        entry.setPrice(35.00);

        //add entry to card entries
        newYugiohCard.addEntry(entry);

        int insertCardId = cardDao.insert(newYugiohCard);
        int id = updateHistoryDao.insert(entry);

        //assertEquals(0, id);

        System.out.println("this yugiohCard: " + newYugiohCard.getCardName() + " is owned by " + newYugiohCard.getUser().getUserName());
        System.out.println("an update was made on: " + entry.getTimeStamp() + " for the card: " + entry.getYugiohCard().getCardName());
    }

    @Test
    void deleteSuccessWithCardSuccess() {

    }

}