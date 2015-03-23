package ru.shmoylova.tracker.logic;

import java.util.List;
import ru.shmoylova.tracker.interfaces.beans.PermissionSessionBeanLocal;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.hibernate.Session;
import ru.shmoylova.tracker.dao.PermissionDao;
import ru.shmoylova.tracker.entity.Permission;

/**
 *
 * @author Ksiona
 */
@Stateless
@Interceptors(TransactionInterceptor.class)
public class PermissionSessionBean implements PermissionSessionBeanLocal {

    private PermissionDao permDao;
    private Session session;

    public PermissionSessionBean() {
        permDao = new PermissionDao();
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permDao.getAll(session);
    }
}
