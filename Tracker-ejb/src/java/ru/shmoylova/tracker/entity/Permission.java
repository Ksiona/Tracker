package ru.shmoylova.tracker.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;

public class Permission implements Serializable, BaseEntity {

    private static final long serialVersionUID = 1L;
    private int permId;
    private PermissionGroup permissionGroupByReadPer;
    private PermissionGroup permissionGroupByWritePer;
    private Set<ProductionUnit> productionUnits = new HashSet<>(0);
    private Set<Activity> activities =  new HashSet<>(0);

    public Permission() {
    }

    public Permission(int permId, PermissionGroup permissionGroupByReadPer, PermissionGroup permissionGroupByWritePer) {
        this.permId = permId;
        this.permissionGroupByReadPer = permissionGroupByReadPer;
        this.permissionGroupByWritePer = permissionGroupByWritePer;
    }

    public Permission(int permId, PermissionGroup permissionGroupByReadPer, 
            PermissionGroup permissionGroupByWritePer, Set<ProductionUnit> productionUnits, Set<Activity> activities) {
        this.permId = permId;
        this.permissionGroupByReadPer = permissionGroupByReadPer;
        this.permissionGroupByWritePer = permissionGroupByWritePer;
        this.productionUnits = productionUnits;
        this.activities = activities;
    }

    public int getPermId() {
        return this.permId;
    }

    public void setPermId(int permId) {
        this.permId = permId;
    }

    public PermissionGroup getPermissionGroupByReadPer() {
        return this.permissionGroupByReadPer;
    }

    public void setPermissionGroupByReadPer(PermissionGroup permissionGroupByReadPer) {
        this.permissionGroupByReadPer = permissionGroupByReadPer;
    }

    public PermissionGroup getPermissionGroupByWritePer() {
        return this.permissionGroupByWritePer;
    }

    public void setPermissionGroupByWritePer(PermissionGroup permissionGroupByWritePer) {
        this.permissionGroupByWritePer = permissionGroupByWritePer;
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
        Permission perm = (Permission) obj;
        if (perm == this) {
            return true;
        }
        if ((obj == null) && !(obj instanceof Permission)) {
            return false;
        }
        return permId == perm.permId ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.permId;
        return hash;
    }

}
