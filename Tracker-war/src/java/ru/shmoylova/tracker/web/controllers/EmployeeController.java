package ru.shmoylova.tracker.web.controllers;

import java.io.Serializable;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.entity.Role;
import ru.shmoylova.tracker.extra.IController;
import ru.shmoylova.tracker.interfaces.beans.EmployeeSessionBeanLocal;

@ManagedBean(name = "employeeController")
@SessionScoped
public class EmployeeController implements IController, Serializable {

    @EJB
    EmployeeSessionBeanLocal employeeBean;
    @ManagedProperty(value = MANAGED_PROPERTY_MESSAGES)
    private MessagesController messages;

    private static final String MANAGED_PROPERTY_MESSAGES = "#{messageController}";
    private static final String ERROR_PREFIX_FOR_EDIT = "error_edit_employee_prefix";
    private static final String ERROR_PREFIX_FOR_DELETE = "error_delete_employee_prefix";
    private static final String ERROR_REQUEST = "request_error";
    private static final String PAGE_EMPLOYEE = "emplist";
    private static final String PAGE_BROWSE = "browse";
    private static final String PAGE_EDIT_EMP = "edit-emp";
    private static final String PAGE_DELETE_EMP = "delete-emp";
    private static final String FORMATTER_FULL_NAME = "%s %s %s";
    private int empId;
    private List<Employee> selectedList;
    private List<Employee> list;
    private Employee current;

    public EmployeeController() {
    }

    @PostConstruct
    public void init() {
        list = getList();
    }

    public void setMessages(MessagesController messages) {
        this.messages = messages;
    }

    public List<Employee> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<Employee> selectedList) {
        this.selectedList = selectedList;
    }

    @Override
    public List<Employee> getList() {
        if (list == null) {
            return employeeBean.getAllEmployees();
        } else {
            return list;
        }
    }

    @Override
    public void setList(List empList) {
        this.list = empList;
    }

    public String save() {
        try {
            employeeBean.insertOrUpdate(current);
        } catch (Exception e) {
            messages.printError(messages.getBundle(messages.BUNDLE_MSG_LOC, ERROR_PREFIX_FOR_EDIT, ERROR_REQUEST));
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, e);
        }
        current = null;
        recreateModel();
        return PAGE_EMPLOYEE;
    }

    public void delete() {
        employeeBean.remove(selectedList);
        selectedList = null;
        navigatiionHandler(PAGE_EMPLOYEE, PAGE_EMPLOYEE);

    }

    public String cancel() {
        current = null;
        return PAGE_EMPLOYEE;
    }

    public Employee getSelected() {
        if (current == null) {
            current = new Employee(new Department(), new Role());
        }
        return current;
    }

    public String fullName() {
        Formatter fmt = new Formatter();
        fmt.format(FORMATTER_FULL_NAME, current.getLastName(), current.getFirstName(), current.getSurName());
        return fmt.toString();
    }

    @Override
    public void recreateModel() {
        selectedList = null;
        list = employeeBean.getAllEmployees();
    }

    public void prepareView() {
        navigatiionHandler(PAGE_EMPLOYEE, PAGE_BROWSE);
    }

    public void prepareEdit(Employee emp) {
        if (emp != null) {
            current = emp;
        }
        navigatiionHandler(PAGE_EMPLOYEE, PAGE_EDIT_EMP);
    }

    public String prepareDelete() {
        return PAGE_DELETE_EMP;
    }

    public String prepareList() {
        return PAGE_EMPLOYEE;
    }

    public void print() {
        messages.printInfo("Success");
    }

    public void navigatiionHandler(String actionPage, String outcomePage) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, actionPage, outcomePage);
    }
}
