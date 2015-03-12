/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shmoylova.tracker.util;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        session.beginTransaction();//TODO: if you manage the transaction for yourself, who's giong to commit/rollback it?
        return session;
    }

    @Override
    public T get(Class<T> type, int id) {
        T result = (T) getSession().load(type, id);
        return result;
    }

    @Override
    public void create(T entity) {
        getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public List<T> findAll(Class<T> type) {
        return getSession().createCriteria(type).list();
    }
}
