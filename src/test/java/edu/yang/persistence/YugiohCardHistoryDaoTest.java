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

    /**
     * verify successful get by id
     */
    @Test
    void getByIdSuccess() {
        YugiohCardHistory entry = (YugiohCardHistory)updateHistoryDao.getById(1);
        logger.info("Entry id: " + entry.getId());
        logger.info("Entry cardId: " + entry.getYugiohCard());
        logger.info("Entry update_dt: " + entry.getTimeStamp());
    }

    /**
     * verify successful insert with new card from existing user
     */
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

        //add entry to card collection
        newYugiohCard.addEntry(entry);

        int insertCardId = cardDao.insert(newYugiohCard);
        int id = updateHistoryDao.insert(entry);

        //size should be one for new card
        assertEquals(1, newYugiohCard.getEntries().size());

        //user should now have 3 cards in his/her collection
        assertEquals(3, updateUser.getCards().size());
    }

    /**
     * verify successful delete
     */
    @Test
    void deleteFromCardSuccess() {
        YugiohCardHistory entry = (YugiohCardHistory) updateHistoryDao.getById(1);
        logger.info(entry.getYugiohCard().getEntries().size()); //this card has two entries

        int id = entry.getYugiohCard().getId();
        logger.info("this entry's card id : " + id);
        entry.getYugiohCard().removeEntry(entry);

        updateHistoryDao.delete(entry);

        YugiohCard updatedCard = (YugiohCard) cardDao.getById(id);
        logger.info(updatedCard.getEntries().size());

        assertEquals(2, updateHistoryDao.getAll().size());
        assertEquals(1, updatedCard.getEntries().size()); //this card should have one entry left
    }

}