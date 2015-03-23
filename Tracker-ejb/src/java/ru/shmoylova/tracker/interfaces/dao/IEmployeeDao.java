package ru.shmoylova.tracker.interfaces.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.entity.Role;

/**
 *
 * @author Ksiona
 */
public interface IEmployeeDao extends GenericDao<Employee> {

    List<Employee> find(FullTextSession fullTextSession, String... searchTerm);

    List<Employee> find(Session session, Department dept);

    List<Employee> find(Session session, Department dept, String jobTitle);

    List<Employee> find(Session session, Role role);

    Employee loginRequest(Session session, String login, String pass);

    List<Employee> getAll(Session session);

}
