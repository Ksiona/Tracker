/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shmoylova.tracker.logic;

import ru.shmoylova.tracker.interfaces.beans.DepartmentSessionBeanLocal;
import java.util.List;
import javax.ejb.Stateless;
import ru.shmoylova.tracker.dao.DepartmentDao;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.util.HibernateUtil;

/**
 *
 * @author Ksiona
 */
@Stateless
public class DepartmentSessionBean implements DepartmentSessionBeanLocal {

    private DepartmentDao deptDao;

    public DepartmentSessionBean() {
        deptDao = new DepartmentDao(HibernateUtil.getSessionFactory());
    }

    @Override
    public List<Department> getAllDepartments() {
        return deptDao.findAll();
    }

}
