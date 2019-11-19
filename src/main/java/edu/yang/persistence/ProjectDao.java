package edu.yang.persistence;

import edu.yang.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * projectDao object
 * @author Lee Yang
 */
public class ProjectDao<T> {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory;

    /**
     * projectDao arg constructor
     *
     * @param type
     */
    public ProjectDao(Class<T> type) {
        this.type = type;
    }

    /**
     * gets sessionFactory
     *
     * @return sessionFactory
     */
    public Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * gets an entity by its id
     *
     * @param id id of the entity to get
     * @return a entity
     */

    public <T> T getById(int id) {
        Session session = getSession();
        T entity = (T) session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * updates entity
     *
     * @param entity the entity to be inserted or deleted
     */
    public void saveOrUpdate(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * inserts entity
     *
     * @param entity entity to be inserted
     * @return id
     */
    public int insert(T entity) {
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * deletes entity
     *
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * gets list of entities
     *
     * @return list of entities
     */
    public List<T> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * gets an entity by its property
     *
     * @param property property to specify
     * @param value    value of property
     * @return entity
     */
    public T getByProperty(String property, String value) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(property);
        query.where(builder.like(propertyPath, "%" + value + "%"));
        T entity = session.createQuery(query).getSingleResult();
        session.close();
        return entity;
    }

    /**
     * gets a list of cards with similar names
     * @param property property to specify
     * @param value    value of property
     * @return list of entities
     */
    public List<T> getAllByPropertyLike(String property, String value) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(property);
        query.where(builder.like(propertyPath, "%" + value + "%"));
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * gets a list of cards with similar names
     * @param propertyMap property and value pairs
     * @return entities with properties equal to those passed in the map
     * @return list of entities
     */
    public List<T> findByPropertyLike(Map<String, Object> propertyMap) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Map.Entry entry : propertyMap.entrySet()) {
            if (entry.getKey() == "cardName") {
                predicates.add(builder.like(root.get((String)entry.getKey()), "%" + entry.getValue() + "%"));
            } else {
                predicates.add(builder.equal(root.get((String) entry.getKey()), entry.getValue()));
            }
        }

        query.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        return session.createQuery(query).getResultList();
    }

    /**
     * Finds entities by multiple properties.
     * Inspired by https://stackoverflow.com/questions/11138118/really-dynamic-jpa-criteriabuilder
     *
     * @param propertyMap property and value pairs
     * @return entities with properties equal to those passed in the map
     */
    public List<T> findByPropertyEqual(Map<String, Object> propertyMap) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Map.Entry entry : propertyMap.entrySet()) {
            predicates.add(builder.equal(root.get((String) entry.getKey()), entry.getValue()));
        }
        query.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        return session.createQuery(query).getResultList();
    }
}
