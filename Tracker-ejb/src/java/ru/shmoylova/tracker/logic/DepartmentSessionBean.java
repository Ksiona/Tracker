package ru.shmoylova.tracker.logic;

import ru.shmoylova.tracker.interfaces.beans.DepartmentSessionBeanLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.hibernate.Session;
import ru.shmoylova.tracker.dao.DepartmentDao;
import ru.shmoylova.tracker.entity.Department;

/**
 *
 * @author Ksiona
 */
@Stateless
@Interceptors(TransactionInterceptor.class)
public class DepartmentSessionBean implements DepartmentSessionBeanLocal {

    private DepartmentDao deptDao;
    private Session session;

    public DepartmentSessionBean() {
        deptDao = new DepartmentDao();
    }

    @Override
    public List<Department> getAllDepartments() {
        return deptDao.getAll(session);
    }

}
