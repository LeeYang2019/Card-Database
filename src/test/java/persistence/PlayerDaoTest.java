package persistence;

import entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDaoTest {

    PlayerDao newPlayerDao;

    @BeforeEach
    void setUp() {
        newPlayerDao = new PlayerDao();
    }

    @Test
    void insert() {

        Player newPlayer = new Player("john", "michael");
        newPlayerDao.insert(newPlayer);
        assertEquals(2, newPlayerDao.getAll().size());
    }

    @Test
    void getById() {

        Player newPlayer = newPlayerDao.getById(1);
        assertEquals("john", newPlayer.getPlayerName());
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }
}