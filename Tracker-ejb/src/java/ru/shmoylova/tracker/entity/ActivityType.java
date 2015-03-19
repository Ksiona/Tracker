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
public class ActivityType implements Serializable, BaseEntity {

    private static final long serialVersionUID = 1L;
    @DocumentId
    private int typeId;
    @ContainedIn
    private Department department;
    private Permission permission;
    @Field
    private String typeTitle;
    @IndexedEmbedded
    private Set<Activity> activities = new HashSet<>(0);

    public ActivityType() {
    }

    public ActivityType(int typeId, Department department, Permission permission, String typeTitle) {
        this.typeId = typeId;
        this.department = department;
        this.permission = permission;
        this.typeTitle = typeTitle;
    }

    public ActivityType(int typeId, Department department, Permission permission, String typeTitle, Set<Activity> activities) {
        this.typeId = typeId;
        this.department = department;
        this.permission = permission;
        this.typeTitle = typeTitle;
        this.activities = activities;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    public String getTypeTitle() {
        return this.typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public Set<Activity> getActivities() {
        return this.activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object obj) {
        ActivityType type = (ActivityType) obj;
        if (type == this) {
            return true;
        }
        if ((obj == null) && !(obj instanceof ActivityType)) {
            return false;
        }
        return typeId == type.typeId
                && typeTitle.equals(type.typeTitle);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.typeId;
        hash = 37 * hash + Objects.hashCode(this.typeTitle);
        return hash;
    }
}
