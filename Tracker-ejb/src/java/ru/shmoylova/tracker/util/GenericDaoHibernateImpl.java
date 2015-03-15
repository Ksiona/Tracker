package ru.shmoylova.tracker.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;
import ru.shmoylova.tracker.interfaces.dao.GenericDao;

/**
 *
 * @author Ksiona
 * @param <T>
 */
public abstract class GenericDaoHibernateImpl<T extends BaseEntity> implements GenericDao<T> {

    private static StandardServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    public GenericDaoHibernateImpl(SessionFactory factory) {
        setSessionFactory(factory);
    }

    protected static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected static void setSessionFactory(SessionFactory sessionFactory) {
        GenericDaoHibernateImpl.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }

    @Override
    public T get(Class<T> type, int id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        T result = null;
        try {
            tx = session.beginTransaction();
            result = (T) session.get(type, id);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void create(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
    }
}
