package ru.shmoylova.tracker.logic;

import java.util.List;
import ru.shmoylova.tracker.interfaces.beans.PermissionSessionBeanLocal;
import javax.ejb.Stateless;
import ru.shmoylova.tracker.dao.PermissionDao;
import ru.shmoylova.tracker.entity.Permission;
import ru.shmoylova.tracker.util.HibernateUtil;

/**
 *
 * @author Ksiona
 */
@Stateless
public class PermissionSessionBean implements PermissionSessionBeanLocal {

    private PermissionDao permDao;
    
    public PermissionSessionBean(){
        permDao = new PermissionDao(HibernateUtil.getSessionFactory());
    }
    
    @Override
    public List<Permission> getAllPermissions() {
       return permDao.findAll();
    }
}
