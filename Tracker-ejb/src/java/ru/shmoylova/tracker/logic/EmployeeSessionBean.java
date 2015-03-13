package ru.shmoylova.tracker.logic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import ru.shmoylova.tracker.interfaces.beans.EmployeeSessionBeanLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;
import ru.shmoylova.tracker.dao.EmployeeDao;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.util.HibernateUtil;

/**
 *
 * @author Ksiona
 */
@Stateless
public class EmployeeSessionBean implements EmployeeSessionBeanLocal {

    private static final String MASK = "Klp889_93486739687";
    private static final String CRYPTO_ALGORYTHM = "MD5";
    private static final String ENCODING = "UTF-8";
    EmployeeDao empDao;

    public EmployeeSessionBean() {
        empDao = new EmployeeDao(HibernateUtil.getSessionFactory());
    }

    @Override
    public int processLogin(String login, String pass) {
        Employee emp = empDao.loginRequest(login, getHash(pass.concat(MASK)));
        if (emp != null) {
            return emp.getEmpId();
        }
        return 0;
    }

    public String getHash(String pass) {
        String hash = null;
        try {
            hash = DatatypeConverter.printHexBinary(MessageDigest.getInstance(CRYPTO_ALGORYTHM).digest(pass.getBytes(ENCODING)));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return empDao.findAll(Employee.class);
    }

    @Override
    public void insertOrUpdate(Employee employee) {
        Employee checkEmp;
        String hash = getHash(employee.getPass());
        if ((checkEmp = empDao.get(Employee.class, employee.getEmpId())) != null) {
            if (checkEmp.getPass() != employee.getPass()) {
                employee.setPass(hash);
            }
            empDao.update(employee);
        } else {
            employee.setPass(hash);
            empDao.create(employee);
        }
    }

    @Override
    public void remove(Employee employee) {
    }

}
