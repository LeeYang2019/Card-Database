package persistence;

import org.hibernate.SessionFactory;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UserDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();


}
