package ru.shmoylova.tracker.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.entity.Role;
import ru.shmoylova.tracker.interfaces.dao.IEmployeeDao;
import ru.shmoylova.tracker.util.GenericDaoHibernateImpl;

/**
 *
 * @author Ksiona
 */
public class EmployeeDao extends GenericDaoHibernateImpl<Employee> implements IEmployeeDao {

    private static final String PARAMETR_USER_NAME = "userName";
    private static final String PARAMETR_USER_PASS = "userPass";
    private static final String HQL_QUERY_LOGIN_CHECK = "from Employee emp where emp.login = :userName and emp.pass = :userPass";

    public EmployeeDao(SessionFactory factory) {
        super(factory);
    }

    @Override
    public List<Employee> find(String... searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> find(Department dept) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> find(Department dept, String jobTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> find(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee loginRequest(String login, String pass) {
        Employee emp;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            emp = (Employee) getSession().createQuery(HQL_QUERY_LOGIN_CHECK)
                    .setString(PARAMETR_USER_PASS, pass)
                    .setString(PARAMETR_USER_NAME, login.trim()).uniqueResult();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
        return emp;
    }
    
        @Override
    public List<Employee> findAll() {
        List<Employee> empList;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
           empList = session.createCriteria(Employee.class).list();
             for (Employee emp : empList) {
            Hibernate.initialize(emp.getDepartment());
        }
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        } finally {
            session.close();
        }
        return empList;
    }

}
