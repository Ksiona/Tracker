package ru.shmoylova.tracker.dao;

import java.util.List;
import org.hibernate.Session;
import ru.shmoylova.tracker.entity.Role;
import ru.shmoylova.tracker.interfaces.dao.IRoleDao;

/**
 *
 * @author Ksiona
 */
public class RoleDao extends GenericDaoHibernateImpl<Role> implements IRoleDao {

    public RoleDao() {
    }

    @Override
    public List<Role> getAll(Session session) {
        List<Role> roleList = session.createQuery("from Role").list();
        return roleList;
    }

}
