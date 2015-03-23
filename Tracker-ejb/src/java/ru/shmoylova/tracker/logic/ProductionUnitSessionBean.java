package ru.shmoylova.tracker.logic;

import ru.shmoylova.tracker.interfaces.beans.ProductionUnitSessionBeanLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.hibernate.Session;
import ru.shmoylova.tracker.dao.ProductionUnitDao;
import ru.shmoylova.tracker.entity.ProductionUnit;

/**
 *
 * @author Ksiona
 */
@Stateless
@Interceptors(TransactionInterceptor.class)
public class ProductionUnitSessionBean implements ProductionUnitSessionBeanLocal {

    private ProductionUnitDao unitDao;
    private static Session session;

    public ProductionUnitSessionBean() {
        unitDao = new ProductionUnitDao();
    }

    @Override
    public List<ProductionUnit> getAllUnits() {
        List<ProductionUnit> units = unitDao.getAll(session);
        return units;
    }

    @Override
    public List<ProductionUnit> getUnitForOwner(int empId) {
        List<ProductionUnit> units = unitDao.getPrUnits(empId, session);
        return units;
    }

    @Override
    public ProductionUnit getUnit(String unitTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductionUnit getUnit(int unitId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
