package persistence;

import entity.Player;
import entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * Player Dao
 */
public class PlayerDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     *
     */
    public Player getById(int id) {
        Session session = sessionFactory.openSession();
        Player newPlayer = session.get( Player.class, id);
        session.close();
        return newPlayer;
    }


    /**
     *
     */
    public int insert(Player player) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(player);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     *
     */
    public void delete(Player player) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(player);
        transaction.commit();
        session.close();
    }

    /**
     *
     *
     */
    public List<Player> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Player> query = builder.createQuery( Player.class );
        Root<Player> root = query.from( Player.class );
        List<Player> players = session.createQuery( query ).getResultList();
        session.close();
        return players;
    }


}


