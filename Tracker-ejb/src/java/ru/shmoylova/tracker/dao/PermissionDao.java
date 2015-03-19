/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shmoylova.tracker.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.shmoylova.tracker.entity.Permission;
import ru.shmoylova.tracker.interfaces.dao.IPermissionDao;
import ru.shmoylova.tracker.util.GenericDaoHibernateImpl;

/**
 *
 * @author Ksiona
 */
public class PermissionDao extends GenericDaoHibernateImpl<Permission> implements IPermissionDao {

    public PermissionDao(SessionFactory factory) {
        super(factory);
    }

    @Override
    public List<Permission> findAll() {
        List<Permission> permList;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            permList = session.createQuery("from Permission").list();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
        return permList;
    }

}
