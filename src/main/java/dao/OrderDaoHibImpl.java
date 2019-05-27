package dao;

import java.util.List;
import model.Order;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class OrderDaoHibImpl {

    private static final Logger logger = Logger.getLogger(OrderDaoHibImpl.class);

    public static Order findById(int id) {
        logger.info("Find order by id - " + id);
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Order.class, id);
    }

    public static List<Order> getAllOrders() {
        List<Order> orders = (List<Order>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Order").list();
        logger.info("Find and return all orders");
        return orders;
    }

    public static void save(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(order);
        tx1.commit();
        session.close();
        logger.info("Save order " + order.getId() + " with code - " + order.getCode() + " to db");
    }

    public static void update(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(order);
        tx1.commit();
        session.close();
        logger.info("Update order " + order.getId() + " with code - " + order.getCode() + " to db");
    }

    public static void delete(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(order);
        tx1.commit();
        session.close();
        logger.info("Delete order " + order.getId() + " with code - " + order.getCode() + " from db");
    }
}
