package ru.shmoylova.tracker.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlType;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Resolution;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;

@Indexed
@XmlType(propOrder = {"activityId", "activityDesc",  "dateWorks", "timeWorked", "activityType", "employee", "permission", "productionUnit"})
public class Activity implements Serializable, BaseEntity {

    private static final long serialVersionUID = 1L;
    @DocumentId
    private int activityId;
    @ContainedIn
    private ActivityType activityType;
    @ContainedIn
    private Employee employee;
    private Permission permission;
    @ContainedIn
    private ProductionUnit productionUnit;
    @Field
    @DateBridge(resolution = Resolution.DAY)
    private Date dateWorks;
    @Field
    @NumericField
    private BigDecimal timeWorked;
    private String activityDesc;

    public Activity() {
    }

    public Activity(int activityId, ActivityType activityType, Employee employee, Permission permission, ProductionUnit productionUnit, Date dateWorks, BigDecimal timeWorked) {
        this.activityId = activityId;
        this.activityType = activityType;
        this.employee = employee;
        this.permission = permission;
        this.productionUnit = productionUnit;
        this.dateWorks = dateWorks;
        this.timeWorked = timeWorked;
    }

    public Activity(int activityId, ActivityType activityType, Employee employee, Permission permission, ProductionUnit productionUnit, Date dateWorks, BigDecimal timeWorked, String activityDesc) {
        this.activityId = activityId;
        this.activityType = activityType;
        this.employee = employee;
        this.permission = permission;
        this.productionUnit = productionUnit;
        this.dateWorks = dateWorks;
        this.timeWorked = timeWorked;
        this.activityDesc = activityDesc;
    }

    public int getActivityId() {
        return this.activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public ActivityType getActivityType() {
        return this.activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public ProductionUnit getProductionUnit() {
        return this.productionUnit;
    }

    public void setProductionUnit(ProductionUnit productionUnit) {
        this.productionUnit = productionUnit;
    }

    public Date getDateWorks() {
        return this.dateWorks;
    }

    public void setDateWorks(Date dateWorks) {
        this.dateWorks = dateWorks;
    }

    public BigDecimal getTimeWorked() {
        return this.timeWorked;
    }

    public void setTimeWorked(BigDecimal timeWorked) {
        this.timeWorked = timeWorked;
    }

    public String getActivityDesc() {
        return this.activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    @Override
    public boolean equals(Object obj) {
        Activity activity = (Activity) obj;
        if (activity == this) {
            return true;
        }
        if ((obj == null) && !(obj instanceof Activity)) {
            return false;
        }
        return activityId == activity.activityId
                && activityType.equals(activity.activityType);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.activityId;
        hash = 97 * hash + Objects.hashCode(this.activityType);
        return hash;
    }
}
