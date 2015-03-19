package ru.shmoylova.tracker.interfaces.dao;

import java.util.List;
import ru.shmoylova.tracker.entity.Department;

/**
 *
 * @author Ksiona
 */
public interface IDepartmentDao extends GenericDao<Department> {

    List<Department> findAll();
}
