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
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.interfaces.dao.IDepartmentDao;
import ru.shmoylova.tracker.util.GenericDaoHibernateImpl;

/**
 *
 * @author Ksiona
 */
public class DepartmentDao extends GenericDaoHibernateImpl<Department> implements IDepartmentDao{

    public DepartmentDao(SessionFactory factory) {
        super(factory);
    }

    @Override
    public List<Department> findAll() {
        List<Department> deptList;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            deptList = session.createQuery("from Department").list();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
        return deptList;
    }
    
}
