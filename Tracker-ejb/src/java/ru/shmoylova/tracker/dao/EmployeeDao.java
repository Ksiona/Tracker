package ru.shmoylova.tracker.dao;

import java.util.List;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.entity.Role;
import ru.shmoylova.tracker.interfaces.dao.IEmployeeDao;

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
    private static final String HQL_QUERY_EMPLOYEES = "from Employee";

    public EmployeeDao() {
    }

    @Override
    public List<Employee> find(FullTextSession fullTextSession, String... searchTerm) {
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(Employee.class).get();
        Query luceneQuery = queryBuilder
                .keyword()
                .onFields(FIELD_LASTNAME, FIELD_FIRSTNAME, FIELD_SURNAME, FIELD_JOB, FIELD_LOGIN, FIELD_DEPT)
                .matching(searchTerm[0] + SEARCH_PARAMETR_ANY)
                .createQuery();

        List<Employee> empList = fullTextSession.createFullTextQuery(luceneQuery).list();
        return empList;
    }

    @Override
    public List<Employee> find(Session session, Department dept) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> find(Session session, Department dept, String jobTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> find(Session session, Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee loginRequest(Session session, String login, String pass) {
        Employee emp = (Employee) session.createQuery(HQL_QUERY_LOGIN_CHECK)
                .setString(FIELD_PASS, pass)
                .setString(FIELD_LOGIN, login.trim()).uniqueResult();

        return emp;
    }

    @Override
    public List<Employee> getAll(Session session) {
        List<Employee> empList = session.createQuery(HQL_QUERY_EMPLOYEES).list();
        return empList;
    }
}
