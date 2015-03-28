package ru.shmoylova.tracker.util;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ManagedSessionContext;

public class HibernateUtil {

    private static StandardServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void openSession() {
        Session openSession = getSessionFactory().openSession();
        openSession.setFlushMode(FlushMode.MANUAL);
        ManagedSessionContext.bind(openSession);
    }

    public static Connection closeSession() {
        Connection cnt = null;
        try {
            Session session = getSessionFactory().getCurrentSession();
            ManagedSessionContext.unbind(sessionFactory);
            session.clear();
            cnt = session.close();
            return cnt;
        } catch (HibernateException | NullPointerException ex) {
            Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, ex.getCause().toString());
            return null;
        }
    }
}
