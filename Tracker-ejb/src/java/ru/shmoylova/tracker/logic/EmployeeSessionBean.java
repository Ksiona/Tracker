package ru.shmoylova.tracker.logic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;

import ru.shmoylova.tracker.dao.EmployeeDao;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.interfaces.beans.EmployeeSessionBeanLocal;

/**
 *
 * @author Ksiona
 */
@Stateless
@Interceptors(TransactionInterceptor.class)
public class EmployeeSessionBean implements EmployeeSessionBeanLocal {

    private static final String MASK = "Klp889_93486739687";
    private static final String CRYPTO_ALGORYTHM = "MD5";
    private static final String ENCODING = "UTF-8";
    private EmployeeDao empDao;
    private Session session;
    private FullTextSession fullTextSession;

    public EmployeeSessionBean() {
        empDao = new EmployeeDao();
    }

    @Override
    public int processLogin(String login, String pass) {
        Employee emp = empDao.loginRequest(session, login, getHash(pass.concat(MASK)));
        if (emp != null) {
            return emp.getEmpId();
        }
        return 0;
    }

    @ExcludeClassInterceptors
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
        return empDao.getAll(session);
    }

    @Override
    public void insertOrUpdate(Employee employee) {
        Employee checkEmp;
        String hash = getHash(employee.getPass().concat(MASK));
        if ((checkEmp = empDao.get(session, Employee.class, employee.getEmpId())) != null) {
            if (!employee.getPass().equals("")) {
                employee.setPass(hash);
            } else {
                employee.setPass(checkEmp.getPass());
            }
            empDao.update(session, employee);
        } else {
            employee.setPass(hash);
            empDao.create(session, employee);
        }
    }

    @Override
    public void remove(List<Employee> empList) {
        empDao.delete(session, empList);
    }

    @ExcludeClassInterceptors
    @Interceptors(SearchTransactionInterceptor.class)
    @Override
    public List<Employee> find(String... arr) {
        return empDao.find(fullTextSession, arr);
    }
}
