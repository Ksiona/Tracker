package ru.shmoylova.tracker.logic;

import ru.shmoylova.tracker.interfaces.beans.EmployeeSessionBeanLocal;
import java.util.List;
import javax.ejb.Stateless;
import ru.shmoylova.tracker.dao.EmployeeDao;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.util.HibernateUtil;

/**
 *
 * @author Ksiona
 */
@Stateless
public class EmployeeSessionBean implements EmployeeSessionBeanLocal {

    private static final String MASK = "Klp889_93486739687";
    EmployeeDao empDao;

    public EmployeeSessionBean() {
        empDao = new EmployeeDao(HibernateUtil.getSessionFactory());
    }

    @Override
    public int processLogin(String login, String pass) {
        Employee emp = empDao.loginRequest(login, pass.concat(MASK));//What's that for?
        if (emp != null) {
            return emp.getEmpId();
        }
        return 0;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return empDao.findAll(Employee.class);
    }

    @Override
    public void insertOrUpdate(Employee employee) {
    }

    @Override
    public void remove(Employee employee) {
    }
    
    

}
