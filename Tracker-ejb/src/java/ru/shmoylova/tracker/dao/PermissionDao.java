package ru.shmoylova.tracker.dao;

import java.util.List;
import org.hibernate.Session;
import ru.shmoylova.tracker.entity.Permission;
import ru.shmoylova.tracker.interfaces.dao.IPermissionDao;

/**
 *
 * @author Ksiona
 */
public class PermissionDao extends GenericDaoHibernateImpl<Permission> implements IPermissionDao {

    private static final String HQL_QUERY_PERMISSIONS = "from Permission";

    public PermissionDao() {
    }

    @Override
    public List<Permission> getAll(Session session) {
        List<Permission> permList = session.createQuery(HQL_QUERY_PERMISSIONS).list();
        return permList;
    }

}
