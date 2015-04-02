package ru.shmoylova.tracker.logic;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import ru.shmoylova.tracker.entity.Activity;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.entity.ProductionUnit;

/**
 *
 * @author Ksiona
 */
@XmlRootElement(namespace = "http:\\\\localhost\\8080\\Tracker-war")
public class XmlResult {

    private List<Employee> employee;
    private List<ProductionUnit> productionUnit;
    private List<Activity> activity;

    public XmlResult() {
    }

    public XmlResult(List<Employee> employee, List<ProductionUnit> productionUnit, List<Activity> activity) {
        this.employee = employee;
        this.productionUnit = productionUnit;
        this.activity = activity;
        clearEmployees();
    }

        private void clearEmployees() {
        for (Employee emp : employee) {
            emp.getDepartment().setEmployees(null);
            emp.getDepartment().setActivityTypes(null);
            emp.getRole().setEmployees(null);
            emp.getRole().setGroupMembers(null);
            for (ProductionUnit unit : emp.getProductionUnits()) {
                unit.setActivities(null);
                unit.setEmployee(null);
                unit.setPermission(null);
            }
            for (Activity unit : emp.getActivities()) {
                unit.setActivityType(null);
                unit.setEmployee(null);
                unit.setPermission(null);
                unit.setProductionUnit(null);
            }
        }
    }
        
    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public List<ProductionUnit> getProductionUnit() {
        return productionUnit;
    }

    public void setProductionUnit(List<ProductionUnit> productionUnit) {
        this.productionUnit = productionUnit;
    }

    public List<Activity> getActivity() {
        return activity;
    }

    public void setActivity(List<Activity> activity) {
        this.activity = activity;
    }
}
