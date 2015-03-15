package ru.shmoylova.tracker.web.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.interfaces.beans.DepartmentSessionBeanLocal;

/**
 *
 * @author Ksiona
 */
@ManagedBean
@SessionScoped
public class DepartmentController implements Serializable {

    @EJB
    DepartmentSessionBeanLocal departmentBean;
    @Inject
    MessagesController messages;
    private static final String ERROR_PREFIX_FOR_LIST = "error_department_table_prefix";
    private static final String ERROR_REQUEST = "request_error";
    private DataModel deptDataModel;
    private Department current;

    private List<Department> deptList;

    /**
     * Creates a new instance of DeparmentController
     */
    public DepartmentController() {
    }

    @PostConstruct
    public void init() {
        this.deptList = departmentBean.getAllDepartments();
    }

    public List<Department> getDeptList() {
        return deptList;
    }

    public DataModel getDeptDataModel() {
        try {
            if (deptDataModel == null) {
                deptDataModel = new ListDataModel(getDeptList());
            }
        } catch (NullPointerException npe) {
            messages.printError(messages.getBundle(messages.BUNDLE_MSG_LOC, ERROR_PREFIX_FOR_LIST, ERROR_REQUEST));
        }
        return deptDataModel;
    }
}
