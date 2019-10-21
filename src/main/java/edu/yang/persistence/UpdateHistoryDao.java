package edu.yang.persistence;

import edu.yang.entity.YugiohCardHistory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * UpdateHistoryDao object
 * @author Lee Yang
 */
public class UpdateHistoryDao {

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
     * update YugiohCardHistory table
     * @param entry
     */
    public void saveOrUpdate(YugiohCardHistory entry) {
        Session session = getSessionFactory().openSession();
        session.saveOrUpdate(entry);
        session.close();
    }

    /**
     * update YugiohCardHistory table
     * @param entry
     * @return id
     */
    public int insert(YugiohCardHistory entry) {
        int id = 0;
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entry);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * deletes an entry from the YugiohCardHistory table
     * @param entry
     */
    public void delete(YugiohCardHistory entry) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entry);
        transaction.commit();
        session.close();
    }
}
