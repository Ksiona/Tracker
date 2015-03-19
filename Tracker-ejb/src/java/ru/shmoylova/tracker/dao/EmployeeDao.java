package ru.shmoylova.tracker.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
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

    private static final String FIELD_LASTNAME = "lastName";
    private static final String FIELD_FIRSTNAME = "firstName";
    private static final String FIELD_SURNAME = "surName";
    private static final String FIELD_JOB = "jobTitle";
    private static final String FIELD_LOGIN = "login";
    private static final String FIELD_PASS = "pass";
    private static final String FIELD_DEPT = "department.deptName";
    private static final String SEARCH_PARAMETR_ANY = "*";
    private static final String HQL_QUERY_LOGIN_CHECK = "from Employee emp where emp.login = :login and emp.pass = :pass";

    public EmployeeDao(SessionFactory factory) {
        super(factory);
    }

    @Override
    public List<Employee> find(String... searchTerm) {
        List<Employee> empList;
        Session session = getSessionFactory().openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();
        try {
            QueryBuilder qb = fullTextSession.getSearchFactory()
                    .buildQueryBuilder().forEntity(Employee.class).get();
            org.apache.lucene.search.Query luceneQuery = qb
                    .keyword()
                    .onFields(FIELD_LASTNAME, FIELD_FIRSTNAME, FIELD_SURNAME, FIELD_JOB, FIELD_LOGIN, FIELD_DEPT)
                    .matching(searchTerm[0] + SEARCH_PARAMETR_ANY)
                    .createQuery();

            empList = fullTextSession.createFullTextQuery(luceneQuery).list();

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
                    .setString(FIELD_PASS, pass)
                    .setString(FIELD_LOGIN, login.trim()).uniqueResult();
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
