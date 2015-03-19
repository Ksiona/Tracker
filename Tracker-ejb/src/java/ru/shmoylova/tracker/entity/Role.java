package ru.shmoylova.tracker.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;

@Indexed
public class Role implements Serializable, BaseEntity {

    private static final long serialVersionUID = 1L;
    @DocumentId
    private int roleId;
    @Field
    private String roleName;
    private Set<GroupMember> groupMembers = new HashSet<>(0);
    @IndexedEmbedded
    private Set<Employee> employees = new HashSet<>(0);

    public Role() {
    }

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Role(int roleId, String roleName, Set<GroupMember> groupMembers, Set<Employee> employees) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.groupMembers = groupMembers;
        this.employees = employees;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<GroupMember> getGroupMembers() {
        return this.groupMembers;
    }

    public void setGroupMembers(Set<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object obj) {
        Role role = (Role) obj;
        if (role == this) {
            return true;
        }
        if ((obj == null) && !(obj instanceof Role)) {
            return false;
        }
        return roleId == role.roleId
                && roleName.equals(role.roleName);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.roleId;
        hash = 71 * hash + Objects.hashCode(this.roleName);
        return hash;
    }
}
