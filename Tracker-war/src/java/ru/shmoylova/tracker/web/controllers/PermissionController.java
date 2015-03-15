package ru.shmoylova.tracker.web.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ru.shmoylova.tracker.entity.Permission;
import ru.shmoylova.tracker.entity.Role;
import ru.shmoylova.tracker.interfaces.beans.PermissionSessionBeanLocal;
import ru.shmoylova.tracker.interfaces.beans.RoleSessionBeanLocal;

/**
 *
 * @author Ksiona
 */
@ManagedBean
@SessionScoped
public class PermissionController implements Serializable {

    @EJB
    RoleSessionBeanLocal roleBean;
    @EJB
    PermissionSessionBeanLocal permBean;
    private List<Role> roleList;
    private List<Permission> permList;

    /**
     * Creates a new instance of PermissionsController
     */
    public PermissionController() {
    }

    @PostConstruct
    public void init() {
        this.roleList = roleBean.getAllRoles();
        this.permList = permBean.getAllPermissions();
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public List<Permission> getPermList() {
        return permList;
    }
}
