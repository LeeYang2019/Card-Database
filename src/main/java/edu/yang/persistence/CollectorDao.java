package edu.yang.persistence;

import edu.yang.entity.Collector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public Collector getById(int id) {
        Session session = getSessionFactory().openSession();
        Collector collector = session.get(Collector.class, id);
        session.close();
        return collector;
    }

    public void saveOrUpdate(Collector collector) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(collector);
        transaction.commit();
        session.close();
    }

    public int insert(Collector collector) {
        int id = 0;
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(collector);
        transaction.commit();
        session.close();
        return id;
    }

    public void delete(Collector collector) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(collector);
        session.close();
    }

    public List<Collector> getAll() {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Collector> query = builder.createQuery(Collector.class);
        Root<Collector> root = query.from(Collector.class);
        List<Collector> collectors = session.createQuery(query).getResultList();
        session.close();
        return collectors;
    }
}
