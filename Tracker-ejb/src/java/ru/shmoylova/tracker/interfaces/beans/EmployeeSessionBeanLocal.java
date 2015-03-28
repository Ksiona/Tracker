package ru.shmoylova.tracker.interfaces.beans;

import java.util.List;
import javax.ejb.Local;
import ru.shmoylova.tracker.entity.Employee;

/**
 *
 * @author Ksiona
 */
@Local
public interface EmployeeSessionBeanLocal {

    List<Employee> getAllEmployees();

    int processLogin(String login, String pass);

    void insertOrUpdate(Employee employee);

    void remove(List<Employee> empList);

    List<Employee> find(String... arr);

}
