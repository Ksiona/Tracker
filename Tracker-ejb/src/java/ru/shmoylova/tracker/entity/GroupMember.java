package ru.shmoylova.tracker.entity;

import java.io.Serializable;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;

public class GroupMember implements Serializable, BaseEntity {

    private static final long serialVersionUID = 1L;
    private int memberId;
    private PermissionGroup permissionGroup;
    private Role role;

    public GroupMember() {
    }

    public GroupMember(int memberId, PermissionGroup permissionGroup, Role role) {
        this.memberId = memberId;
        this.permissionGroup = permissionGroup;
        this.role = role;
    }

    public int getMemberId() {
        return this.memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public PermissionGroup getPermissionGroup() {
        return this.permissionGroup;
    }

    public void setPermissionGroup(PermissionGroup permissionGroup) {
        this.permissionGroup = permissionGroup;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        GroupMember grMem = (GroupMember) obj;
        if (grMem == this) {
            return true;
        }
        if ((obj == null) && !(obj instanceof GroupMember)) {
            return false;
        }
        return memberId == grMem.memberId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.memberId;
        return hash;
    }

}
