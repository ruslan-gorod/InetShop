package dao;

import java.util.List;
import model.Good;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class GoodDoaHibImpl {

    private static final Logger logger = Logger.getLogger(GoodDoaHibImpl.class);

    public static Good findById(int id) {
        logger.info("Find good by id - " + id);
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Good.class, id);
    }

    public static List<Good> getAllGoods() {
        List<Good> goods = (List<Good>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Good").list();
        logger.info("Find and return all goods");
        return goods;
    }

    public static void save(Good good) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(good);
        tx1.commit();
        session.close();
        logger.info("Save good " + good.getName() + " to db");
    }

    public static void update(Good good) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(good);
        tx1.commit();
        session.close();
        logger.info("Update good " + good.getName() + " to db");
    }

    public static void delete(Good good) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(good);
        tx1.commit();
        session.close();
        logger.info("Delete good " + good.getName() + " from db");
    }
}
