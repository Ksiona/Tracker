package ru.shmoylova.tracker.entity;

import java.io.Serializable;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;

/**
 * @author Ksiona
 *
 * Class for entity employee, with:
 * <p>
 * - getters and setters for String fields (lastName, firstName, surName,
 * jobTitle, login, pass)</p>
 * <p>
 * - getters and setters for referense fields
 * ({@link Department}, {@link Permission}, {@link ProductionUnit}, {@link Activity}, {@link Role})<br/>
 * Department and Employee = one-to-many relation<br/>
 * Permission and Employee = one-to-many relation, in this case "permission" is
 * the permission to modify the object Employee, containing this field <br/>
 * ProductionUnit and Employee = many-to-one relation, one employee can be
 * owner(creator) of any units. In this case "production unit" is the object on
 * which employees have to perform the work, "owner" is a responsible for the
 * execution manager <br/>
 * Activity and Employee = one-to-many relation, in this case "activity" is the
 * concrete work on concrete production unit <br/>
 * Role and Employee = one-to-many relation, in this case "role" is the the
 * access level that determines the right to change or read any database
 * objects</p>
 * <p>
 * - hibernate search annotations</p>
 * <p>
 * --@Indexed - indicates that the associated table should be indexed</p>
 * <p>
 * --@DocumentId - indicates that the field is the unique identifier for the
 * indexed document</p>
 * <p>
 * --@Field - specified over the fields added to the index</p>
 * @see Employee.hbm.xml - xml mapping and the rules for the relationship
 */
@Indexed
public class Employee implements Serializable, BaseEntity {

    private static final long serialVersionUID = 1L;
    private static final String FORMATTER_FULL_NAME = "%s %s %s";
    @DocumentId
    private int empId;
    @IndexedEmbedded
    private Department department;
    private Permission permission;
    @ContainedIn
    private Role role;
    @Field
    private String lastName;
    @Field
    private String firstName;
    @Field
    private String surName;
    @Field
    private String jobTitle;
    @Field
    private String login;
    private String pass;
    @IndexedEmbedded
    private Set<ProductionUnit> productionUnits = new HashSet<>(0);
    @IndexedEmbedded
    private Set<Activity> activities = new HashSet<>(0);

    public Employee() {
    }

    public Employee(int empId, Department department, Permission permission, Role role, String lastName, String firstName, String jobTitle, String login, String pass) {
        this.empId = empId;
        this.department = department;
        this.permission = permission;
        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
        this.jobTitle = jobTitle;
        this.login = login;
        this.pass = pass;
    }

    public Employee(int empId, Department department, Permission permission, Role role, String lastName, String firstName, String surName, String jobTitle, String login, String pass, Set<ProductionUnit> productionUnits, Set<Activity> activities) {
        this.empId = empId;
        this.department = department;
        this.permission = permission;
        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.jobTitle = jobTitle;
        this.login = login;
        this.pass = pass;
        this.productionUnits = productionUnits;
        this.activities = activities;
    }

    public Employee(Department department, Role role, Permission permission) {
        this.department = department;
        this.role = role;
        this.permission = permission;
    }

    public int getEmpId() {
        return this.empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return this.surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Set<ProductionUnit> getProductionUnits() {
        return this.productionUnits;
    }

    public void setProductionUnits(Set<ProductionUnit> productionUnits) {
        this.productionUnits = productionUnits;
    }

    public Set<Activity> getActivities() {
        return this.activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object obj) {
        Employee emp = (Employee) obj;
        if (emp == this) {
            return true;
        }
        if ((obj == null) && !(obj instanceof Employee)) {
            return false;
        }
        return empId == emp.empId
                && lastName.equals(emp.lastName)
                && firstName.equals(emp.firstName);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.empId;
        hash = 83 * hash + Objects.hashCode(this.lastName);
        hash = 83 * hash + Objects.hashCode(this.firstName);
        return hash;
    }

    @Override
    public String toString() {
        Formatter fmt = new Formatter();
        fmt.format(FORMATTER_FULL_NAME, lastName, firstName, surName);
        return fmt.toString();
    }

}
