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
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.batchindexing.MassIndexerProgressMonitor;
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
    private EmployeeDao empDao;

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
        return empDao.findAll();
    }

    @Override
    public void insertOrUpdate(Employee employee) {
        Employee checkEmp;
        String hash = getHash(employee.getPass().concat(MASK));
        if ((checkEmp = empDao.get(Employee.class, employee.getEmpId())) != null) {
            if (!employee.getPass().equals("")) {
                employee.setPass(hash);
            } else {
                employee.setPass(checkEmp.getPass());
            }
            empDao.update(employee);
        } else {
            employee.setPass(hash);
            empDao.create(employee);
        }
    }

    @Override
    public void remove(Employee employee) {
        empDao.delete(employee);
    }

    @Override
    public List<Employee> find(String... arr) {
        return empDao.find(arr);
    }

    @Override
    public void reIndexEntireDatabase() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        try {
            fullTextSession
                    .createIndexer()
                    .typesToIndexInParallel(2)
                    .batchSizeToLoadObjects(25)
                    .cacheMode(CacheMode.NORMAL)
                    .threadsToLoadObjects(5)
                    .idFetchSize(150)
                    .startAndWait();
        } catch (InterruptedException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        } finally {
//            fullTextSession.close();
//            session.close();
//        }
    }

}
