package ru.shmoylova.tracker.logic;

import ru.shmoylova.tracker.interfaces.beans.RoleSessionBeanLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.hibernate.Session;
import ru.shmoylova.tracker.dao.RoleDao;
import ru.shmoylova.tracker.entity.Role;

/**
 *
 * @author Ksiona
 */
@Stateless
@Interceptors(TransactionInterceptor.class)
public class RoleSessionBean implements RoleSessionBeanLocal {

    private RoleDao roleDao;
        private Session session;

    public RoleSessionBean() {
        roleDao = new RoleDao();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAll(session);
    }

}
