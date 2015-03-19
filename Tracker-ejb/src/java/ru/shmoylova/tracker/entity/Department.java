package ru.shmoylova.tracker.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;

@Indexed
public class Department implements Serializable, BaseEntity {

    private static final long serialVersionUID = 1L;
    @DocumentId
    private int deptId;
    private Permission permission;
    @Field
    private String deptName;
    @IndexedEmbedded
    private Set<ActivityType> activityTypes = new HashSet<>(0);
    @ContainedIn
    private Set<Employee> employees = new HashSet<>(0);

    public Department() {
    }

    public Department(int deptId, Permission permission, String deptName) {
        this.deptId = deptId;
        this.permission = permission;
        this.deptName = deptName;
    }

    public Department(int deptId, Permission permission, String deptName, Set<ActivityType> activityTypes, Set<Employee> employees) {
        this.deptId = deptId;
        this.permission = permission;
        this.deptName = deptName;
        this.activityTypes = activityTypes;
        this.employees = employees;
    }

    public int getDeptId() {
        return this.deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Set<ActivityType> getActivityTypes() {
        return this.activityTypes;
    }

    public void setActivityTypes(Set<ActivityType> activityTypes) {
        this.activityTypes = activityTypes;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object obj) {
        Department dept = (Department) obj;
        if (dept == this) {
            return true;
        }
        if ((obj == null) && !(obj instanceof Department)) {
            return false;
        }
        return deptId == dept.deptId
                && deptName.equals(dept.deptName);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.deptId;
        hash = 23 * hash + Objects.hashCode(this.deptName);
        return hash;
    }

}
