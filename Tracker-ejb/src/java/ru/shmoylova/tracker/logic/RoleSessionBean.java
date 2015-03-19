package ru.shmoylova.tracker.logic;

import ru.shmoylova.tracker.interfaces.beans.RoleSessionBeanLocal;
import java.util.List;
import javax.ejb.Stateless;
import ru.shmoylova.tracker.dao.RoleDao;
import ru.shmoylova.tracker.entity.Role;
import ru.shmoylova.tracker.util.HibernateUtil;

/**
 *
 * @author Ksiona
 */
@Stateless
public class RoleSessionBean implements RoleSessionBeanLocal {

    private RoleDao roleDao;

    public RoleSessionBean() {
        roleDao = new RoleDao(HibernateUtil.getSessionFactory());
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

}
