package ru.shmoylova.tracker.interfaces.dao;

import java.util.List;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.entity.Role;

/**
 *
 * @author Ksiona
 */
public interface IEmployeeDao extends GenericDao<Employee> {

    List<Employee> find(String... searchTerm);

    List<Employee> find(Department dept);

    List<Employee> find(Department dept, String jobTitle);

    List<Employee> find(Role role);

    Employee loginRequest(String login, String pass);
    
    
    List<Employee> findAll();

}
