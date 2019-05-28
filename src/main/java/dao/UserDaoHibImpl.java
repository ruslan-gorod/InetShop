package dao;

import java.util.List;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class UserDaoHibImpl {

    private static final Logger logger = Logger.getLogger(UserDaoHibImpl.class);

    public static User findById(int id) {
        logger.info("Find user by id - " + id);
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(User.class, id);
    }

    public static List<User> getAllUsers() {
        List<User> users = (List<User>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("From User")
                .list();
        logger.info("Find and return all users");
        return users;
    }

    public static void save(User user) {
        Transaction transaction;
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
        }
        transaction.commit();
        logger.info("Save user " + user.getName() + " to db");
    }

    public static void update(User user) {
        Transaction transaction;
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
        }
        transaction.commit();
        logger.info("Update user " + user.getName() + " to db");
    }

    public static void delete(User user) {
        Transaction transaction;
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
        }
        transaction.commit();
        logger.info("Delete user " + user.getName() + " from db");
    }
}
