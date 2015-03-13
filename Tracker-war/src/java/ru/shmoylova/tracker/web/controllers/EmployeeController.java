package ru.shmoylova.tracker.web.controllers;

import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.interfaces.beans.EmployeeSessionBeanLocal;

@ManagedBean
@SessionScoped
public class EmployeeController {

    @EJB
    EmployeeSessionBeanLocal employeeBean;
    @Inject
    SessionHolderBean shBean;
    private static final String ERROR_PREFIX_FOR_LIST = "error_employee_table_prefix";
    private static final String ERROR_PREFIX_FOR_EDIT = "error_edit_employee_prefix";
    private static final String ERROR_PREFIX_FOR_DELETE = "error_delete_employee_prefix";
    private static final String PAGE_EMPLOYEE = "emplist";
    private static final String PAGE_INDEX = "index";
    private static final String PAGE_BROWSE = "browse";
    private static final String PAGE_EDIT_EMP = "edit-emp";
    private static final String REQUEST_ERROR = "request_error";
    private static final String BUNDLE_LOC = "ru.shmoylova.tracker.web.nls.messages";
    private final ResourceBundle bundle;
    private int empId;
    private MessagesController printer = null;
    private DataModel empList;
    private int startId = 1;
    private int endId = 4;
    private int recordCount = 1000;
    private int pageSize = 10;

    private Employee current;
    private int selectedItemIndex;
    private String emptyPass;

    public EmployeeController() {
        printer = new MessagesController();
        bundle = ResourceBundle.getBundle(BUNDLE_LOC, FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    public String save() {
        current.setPass(getEmptyPass());
        try {
            employeeBean.insertOrUpdate(current);
        } catch (Exception e) {
            printer.printError(shBean.getBundle(BUNDLE_LOC, ERROR_PREFIX_FOR_EDIT, REQUEST_ERROR));
        }
        setEmptyPass("");
        return PAGE_EMPLOYEE;
    }

    public String delete() {
        try {
            employeeBean.remove(current);
        } catch (Exception e) {
            printer.printError(shBean.getBundle(BUNDLE_LOC, ERROR_PREFIX_FOR_DELETE, REQUEST_ERROR));
        }
        return PAGE_EMPLOYEE;
    }

    public String cancel() {
        current = null;
        return PAGE_EMPLOYEE;
    }

    public Employee getSelected() {
        if (current == null) {
            current = new Employee();
            selectedItemIndex = -1;
        }
        return current;
    }

    public DataModel getEmpList() {
        try {
            if (empList == null) {
                empList = new ListDataModel(employeeBean.getAllEmployees());
            }
        } catch (NullPointerException npe) {
            printer.printError(shBean.getBundle(BUNDLE_LOC, ERROR_PREFIX_FOR_LIST, REQUEST_ERROR));
        }
        return empList;
    }
    
    public String getEmptyPass() {
        return emptyPass;
    }

    public void setEmptyPass(String emptyPass) {
        this.emptyPass = emptyPass;
    }

    void recreateModel() {
        empList = null;
    }

    public boolean isHasNextPage() {
        if (endId + pageSize <= recordCount) {
            return true;
        }
        return false;
    }

    public boolean isHasPreviousPage() {
        if (startId - pageSize > 0) {
            return true;
        }
        return false;
    }

    public String next() {
        startId = endId + 1;
        endId = endId + pageSize;
        recreateModel();
        return PAGE_INDEX;
    }

    public String previous() {
        startId = startId - pageSize;
        endId = endId - pageSize;
        recreateModel();
        return PAGE_INDEX;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String prepareView() {
        current = (Employee) getEmpList().getRowData();
        return PAGE_BROWSE;
    }

    public String prepareEdit() {
        current = (Employee) getEmpList().getRowData();
        return PAGE_EDIT_EMP;
    }

    public String prepareList() {
        // recreateModel();
        return PAGE_EMPLOYEE;
    }
}
