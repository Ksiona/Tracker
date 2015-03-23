package ru.shmoylova.tracker.dao;

import java.util.List;
import org.hibernate.Session;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.interfaces.dao.IDepartmentDao;

/**
 *
 * @author Ksiona
 */
public class DepartmentDao extends GenericDaoHibernateImpl<Department> implements IDepartmentDao{
    
    private static final String HQL_QUERY_DEPARTMENTS = "from Department";

    public DepartmentDao() {
    }

    @Override
    public List<Department> getAll(Session session) {
        List<Department> deptList = session.createQuery(HQL_QUERY_DEPARTMENTS).list();
        return deptList;
    }

}
