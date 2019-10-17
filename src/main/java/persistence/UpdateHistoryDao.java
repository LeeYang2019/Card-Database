package persistence;

import entity.Collector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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


}
