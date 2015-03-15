package ru.shmoylova.tracker.interfaces.dao;

import java.util.List;
import ru.shmoylova.tracker.entity.Role;

/**
 *
 * @author Ksiona
 */
public interface IRoleDao extends GenericDao<Role> {
    
    List<Role> findAll();

}
