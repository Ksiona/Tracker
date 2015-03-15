package ru.shmoylova.tracker.interfaces.dao;

import java.util.List;
import ru.shmoylova.tracker.entity.Permission;

/**
 *
 * @author Ksiona
 */
public interface IPermissionDao extends GenericDao<Permission> {
    
    List<Permission> findAll();

}
