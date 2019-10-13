package persistence;

import entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.List;


/**
 * Dao to be used with YugiohCard class
 */
public class CardDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     *
     * @param cardName
     * @return card
     */
    public YugiohCard getByCardName(String cardName) {
        Session session = sessionFactory.openSession();
        YugiohCard card = session.get( YugiohCard.class, cardName);
        session.close();
        return card;
    }

    /**
     *
     * @param cardId
     * @return card
     */
    public YugiohCard getByCardId(int cardId) {
        Session session = sessionFactory.openSession();
        YugiohCard card = session.get( YugiohCard.class, cardId);
        session.close();
        return card;
    }

    /**
     *
     * @param cardType
     * @return card
     */
    public YugiohCard getByCardType(String cardType) {
        Session session = sessionFactory.openSession();
        YugiohCard card = session.get( YugiohCard.class, cardType);
        session.close();
        return card;
    }

    /**
     *
     * @param cardRarity
     * @return card
     */
    public YugiohCard getByCardRarity(String cardRarity) {
        Session session = sessionFactory.openSession();
        YugiohCard card = session.get( YugiohCard.class, cardRarity);
        session.close();
        return card;
    }

    /**
     *
     * @param cardSet
     * @return card
     */
    public YugiohCard getByCardSet(String cardSet) {
        Session session = sessionFactory.openSession();
        YugiohCard card = session.get( YugiohCard.class, cardSet);
        session.close();
        return card;
    }

    /**
     *
     * @param cardPrice
     * @return card
     */
    public YugiohCard getByCardPrice(Double cardPrice) {
        Session session = sessionFactory.openSession();
        YugiohCard card = session.get( YugiohCard.class, cardPrice);
        session.close();
        return card;
    }

    /**
     * update Yugiohcard
     * @param card  yugiohcard to be inserted or updated
     */
    public void saveOrUpdate(YugiohCard card) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(card);
        session.close();
    }

    /**
     * update a Yugiohcard
     * @param card  Yugiohcard to be inserted or updated
     */
    public int insert(YugiohCard card) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(card);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a Yugiohcard
     * @param card Yugiohcard to be deleted
     */
    public void delete(YugiohCard card) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(card);
        transaction.commit();
        session.close();
    }

    /** Return a list of all yugiohcards
     *
     * @return All Yugiohcards
     */
    public List<YugiohCard> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<YugiohCard> query = builder.createQuery( YugiohCard.class );
        Root<YugiohCard> root = query.from( YugiohCard.class );
        List<YugiohCard> cards = session.createQuery( query ).getResultList();
        session.close();
        return cards;
    }


}


