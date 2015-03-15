package ru.shmoylova.tracker.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;

public class PermissionGroup implements Serializable, BaseEntity {

    private static final long serialVersionUID = 1L;
    private int groupId;
    private String groupName;
    private Set<Permission> permissionsForReadPer = new HashSet<>(0);
    private Set<Permission> permissionsForWritePer = new HashSet<>(0);
    private Set<GroupMember> groupMembers = new HashSet<>(0);

    public PermissionGroup() {
    }

    public PermissionGroup(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public PermissionGroup(int groupId, String groupName, Set<Permission> permissionsForReadPer, Set<Permission> permissionsForWritePer, Set<GroupMember> groupMembers) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.permissionsForReadPer = permissionsForReadPer;
        this.permissionsForWritePer = permissionsForWritePer;
        this.groupMembers = groupMembers;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Permission> getPermissionsForReadPer() {
        return this.permissionsForReadPer;
    }

    public void setPermissionsForReadPer(Set<Permission> permissionsForReadPer) {
        this.permissionsForReadPer = permissionsForReadPer;
    }

    public Set<Permission> getPermissionsForWritePer() {
        return this.permissionsForWritePer;
    }

    public void setPermissionsForWritePer(Set<Permission> permissionsForWritePer) {
        this.permissionsForWritePer = permissionsForWritePer;
    }

    public Set<GroupMember> getGroupMembers() {
        return this.groupMembers;
    }

    public void setGroupMembers(Set<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

    @Override
    public boolean equals(Object obj) {
        PermissionGroup permGr = (PermissionGroup) obj;
        if (permGr == this) {
            return true;
        }
        if ((obj == null) && !(obj instanceof PermissionGroup)) {
            return false;
        }
        return groupId == permGr.groupId
                && groupName.equals(permGr.groupName);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.groupId;
        hash = 29 * hash + Objects.hashCode(this.groupName);
        return hash;
    }

}
