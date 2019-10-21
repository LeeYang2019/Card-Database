package edu.yang.persistence;

import edu.yang.entity.YugiohPlayer;
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
 * CollectorDao object
 * @author Lee Yang
 */
public class CollectorDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory;

    /**
     * gets sessionFactory
     * @return sessionFactory
     */
    public SessionFactory getSessionFactory() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
        return this.sessionFactory;
    }

    /**
     * gets a collector by its id
     * @param id
     * @return collector
     */

    public YugiohPlayer getById(int id) {
        Session session = getSessionFactory().openSession();
        YugiohPlayer yugiohPlayer = session.get(YugiohPlayer.class, id);
        session.close();
        return yugiohPlayer;
    }

    public void saveOrUpdate(YugiohPlayer yugiohPlayer) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(yugiohPlayer);
        transaction.commit();
        session.close();
    }

    public int insert(YugiohPlayer yugiohPlayer) {
        int id = 0;
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(yugiohPlayer);
        transaction.commit();
        session.close();
        return id;
    }



    public void delete(YugiohPlayer yugiohPlayer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(yugiohPlayer);
        session.close();
    }

    public List<YugiohPlayer> getAll() {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<YugiohPlayer> query = builder.createQuery(YugiohPlayer.class);
        Root<YugiohPlayer> root = query.from(YugiohPlayer.class);
        List<YugiohPlayer> yugiohPlayers = session.createQuery(query).getResultList();
        session.close();
        return yugiohPlayers;
    }
}
