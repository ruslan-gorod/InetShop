package dao;

import java.util.List;
import model.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class RoleDaoHibImpl {

    private static final Logger logger = Logger.getLogger(RoleDaoHibImpl.class);

    public static Role findById(int id) {
        logger.info("Find role by id - " + id);
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Role.class, id);
    }

    public static List<Role> getAllRoles(){
        List<Role> roles = (List<Role>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Role").list();
        logger.info("Find and return all roles");
        return roles;
    }
    public static void save(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(role);
        tx1.commit();
        session.close();
        logger.info("Save role " + role.getName() + " to db");
    }

    public static void update(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(role);
        tx1.commit();
        session.close();
        logger.info("Update role " + role.getName() + " to db");
    }

    public static void delete(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(role);
        tx1.commit();
        session.close();
        logger.info("Delete role " + role.getName() + " from db");
    }
}
