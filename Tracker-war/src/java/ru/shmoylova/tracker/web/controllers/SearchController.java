package ru.shmoylova.tracker.web.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.extra.IController;
import ru.shmoylova.tracker.extra.ResultItem;
import ru.shmoylova.tracker.interfaces.beans.EmployeeSessionBeanLocal;

/**
 *
 * @author Ksiona
 */
@ManagedBean
@SessionScoped
public class SearchController {

    private static final String SEARCH_RESULT = "result";
    private static final String TABLE_EMPLOYEE = "/web/category/employee/include/employeeTable.xhtml";
    private static final String MANAGED_PROPERTY_EMPLOYEE = "#{employeeController}";
    private List<ResultItem> resultItems;
    private String query;
    @EJB
    EmployeeSessionBeanLocal empBean;
    @ManagedProperty(value = MANAGED_PROPERTY_EMPLOYEE)
    private EmployeeController employeeController;

    public SearchController() {
    }

    public void setEmployeeController(EmployeeController ec) {
        this.employeeController = ec;
    }

    public String search() {
        resultItems = new ArrayList<>();

        List<Employee> empList = empBean.find(query);
        addResultItem(empList, employeeController, TABLE_EMPLOYEE);
        return SEARCH_RESULT;
    }

    public void addResultItem(List list, IController controller, String page) {
        if (list.size() > 0) {
            controller.recreateModel();
            controller.setList(list);
            resultItems.add(new ResultItem(list.size(), page));
        }
    }

    public List<ResultItem> getResultItems() {
        return resultItems;
    }

    public void setResultItems(List<ResultItem> resultItems) {
        this.resultItems = resultItems;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
