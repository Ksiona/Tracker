package ru.shmoylova.tracker.web.controllers;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.entity.Permission;
import ru.shmoylova.tracker.entity.Role;
import ru.shmoylova.tracker.extra.IController;
import ru.shmoylova.tracker.interfaces.beans.EmployeeSessionBeanLocal;

@ManagedBean
@SessionScoped
public class EmployeeController_1 implements IController {

    @EJB
    EmployeeSessionBeanLocal employeeBean;
    @Inject
    MessagesController messages;

    private static final String ERROR_PREFIX_FOR_LIST = "error_employee_table_prefix";
    private static final String ERROR_PREFIX_FOR_EDIT = "error_edit_employee_prefix";
    private static final String ERROR_PREFIX_FOR_DELETE = "error_delete_employee_prefix";
    private static final String ERROR_REQUEST = "request_error";
    private static final String PAGE_EMPLOYEE = "emplist";
    private static final String PAGE_BROWSE = "browse";
    private static final String PAGE_EDIT_EMP = "edit-emp";
    private static final String PAGE_DELETE_EMP = "delete-emp";
    private static final String FORMATTER_FULL_NAME = "%s %s %s";
    private static final int ROWS_ON_PAGE = 10;
    private int empId;
    private DataModel empDataModel;
    private List<Integer> checkedList;
    private List<Employee> empList;

    private int startId = 1;
    private int endId = 10;
    private int recordCount;

    private Employee current;
    private int selectedItemIndex;

    public EmployeeController_1() {
    }

    @Override
    public List<Employee> getList() {
        return employeeBean.getAllEmployees();
    }

    public String save() {
        try {
            employeeBean.insertOrUpdate(current);
        } catch (Exception e) {
            messages.printError(messages.getBundle(messages.BUNDLE_MSG_LOC, ERROR_PREFIX_FOR_EDIT, ERROR_REQUEST));
        }
        current = null;
        recreateModel();
        return PAGE_EMPLOYEE;
    }

    public String delete() {
        try {
            employeeBean.remove(current);
            current = null;
        } catch (Exception e) {
            messages.printError(messages.getBundle(messages.BUNDLE_MSG_LOC, ERROR_PREFIX_FOR_DELETE, ERROR_REQUEST));
        }
        recreateModel();
        return PAGE_EMPLOYEE;
    }

    public String cancel() {
        current = null;
        return PAGE_EMPLOYEE;
    }

    public Employee getSelected() {
        if (current == null) {
            current = new Employee(new Department(), new Role(), new Permission());
            selectedItemIndex = -1;
        }
        return current;
    }

    public List<Integer> checkedList(int checkedEmpId, boolean value) {
        if (checkedList == null) {
            checkedList = new ArrayList<>();
        }
            checkedList.add(checkedEmpId);
        Logger.getLogger(EmployeeController.class.getName()).log(Level.INFO, String.valueOf("checked: " + checkedList.size()));
        return checkedList;
    }

    public String fullName() {
        Formatter fmt = new Formatter();
        fmt.format(FORMATTER_FULL_NAME, current.getLastName(), current.getFirstName(), current.getSurName());
        return fmt.toString();
    }

    public DataModel getEmpDataModel() {
        try {
            if (empDataModel == null) {
                empDataModel = new ListDataModel(getPageContent());
            }
        } catch (NullPointerException npe) {
            messages.printError(messages.getBundle(messages.BUNDLE_MSG_LOC, ERROR_PREFIX_FOR_LIST, ERROR_REQUEST));
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, npe);
        }
        return empDataModel;
    }

    public void setEmpDataModel(DataModel empDataModel) {
        this.empDataModel = empDataModel;
    }

    public List<Employee> getPageContent() {
        if (empList == null) {
            this.empList = getList();
        }
        List<Employee> pageContent = new ArrayList<>();
        for (int i = startId - 1; i < endId; i++) {
            if (empList.size() == i) {
                break;
            }
            pageContent.add(empList.get(i));
        }
        recordCount = empList.size();
        return pageContent;
    }

    @Override
    public void recreateModel() {
        setEmpDataModel(null);
        setList(null);
    }

    public boolean isHasNextPage() {
        if (endId <= recordCount) {
            return true;
        }
        return false;
    }

    public boolean isHasPreviousPage() {
        if (startId - ROWS_ON_PAGE > 0) {
            return true;
        }
        return false;
    }

    public String next() {
        startId = endId + 1;
        endId = endId + ROWS_ON_PAGE;
        recreateModel();
        return PAGE_EMPLOYEE;
    }

    public String previous() {
        startId = startId - ROWS_ON_PAGE;
        endId = endId - ROWS_ON_PAGE;
        recreateModel();
        return PAGE_EMPLOYEE;
    }

    public int getPageSize() {
        return ROWS_ON_PAGE;
    }

    public String prepareView() {
        current = (Employee) getEmpDataModel().getRowData();
        return PAGE_BROWSE;
    }

    public String prepareEdit() {
        current = (Employee) getEmpDataModel().getRowData();
        return PAGE_EDIT_EMP;
    }

    public String prepareDelete() {
        current = (Employee) getEmpDataModel().getRowData();
        return PAGE_DELETE_EMP;
    }

    public String prepareList() {
        recreateModel();
        return PAGE_EMPLOYEE;
    }

    @Override
    public void setList(List empList) {
        this.empList = empList;
    }
}
