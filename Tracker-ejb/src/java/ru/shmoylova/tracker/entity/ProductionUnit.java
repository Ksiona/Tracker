package ru.shmoylova.tracker.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;

public class ProductionUnit implements Serializable, BaseEntity {

    private static final long serialVersionUID = 1L;
    private int unitId;
    private Employee employee;
    private Permission permission;
    private String unitTitle;
    private String unitDesc;
    private Date conclusionDate;
    private Date expireDate;
    private Set<Activity> activities = new HashSet<>(0);

    public ProductionUnit() {
    }

    public ProductionUnit(int unitId, Employee employee, Permission permission, String unitTitle, Date conclusionDate, Date expireDate) {
        this.unitId = unitId;
        this.employee = employee;
        this.permission = permission;
        this.unitTitle = unitTitle;
        this.conclusionDate = conclusionDate;
        this.expireDate = expireDate;
    }

    public ProductionUnit(int unitId, Employee employee, Permission permission, String unitTitle, String unitDesc, Date conclusionDate, Date expireDate, Set<Activity> activities) {
        this.unitId = unitId;
        this.employee = employee;
        this.permission = permission;
        this.unitTitle = unitTitle;
        this.unitDesc = unitDesc;
        this.conclusionDate = conclusionDate;
        this.expireDate = expireDate;
        this.activities = activities;
    }

    public int getUnitId() {
        return this.unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
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

    public String getUnitTitle() {
        return this.unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    public String getUnitDesc() {
        return this.unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }

    public Date getConclusionDate() {
        return this.conclusionDate;
    }

    public void setConclusionDate(Date conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public Date getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Set<Activity> getActivities() {
        return this.activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object obj) {
        ProductionUnit unit = (ProductionUnit) obj;
        if (unit == this) {
            return true;
        }
        if ((obj == null) && !(obj instanceof ProductionUnit)) {
            return false;
        }
        return unitId == unit.unitId
                && unitTitle.equals(unit.unitTitle);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.unitId;
        hash = 89 * hash + Objects.hashCode(this.unitTitle);
        return hash;
    }

}
