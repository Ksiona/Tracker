package ru.shmoylova.tracker.dao;

import java.util.List;
import org.hibernate.Session;
import ru.shmoylova.tracker.entity.ProductionUnit;
import ru.shmoylova.tracker.interfaces.dao.IProductionUnitDao;

/**
 *
 * @author Ksiona
 */
public class ProductionUnitDao extends GenericDaoHibernateImpl<ProductionUnit> implements IProductionUnitDao {

    private static final String HQL_QUERY_UNITS = "from ProductionUnit";
    private static final String HQL_QUERY_UNITS_FOR_OWNER = "from ProductionUnit unit where unit.ownerId = :empId";
    private static final String FIELD_EMP_ID = "empId";

    public ProductionUnitDao() {
    }

    @Override
    public List<ProductionUnit> getAll(Session session) {
        List<ProductionUnit> unitList = session.createQuery(HQL_QUERY_UNITS).list();
        return unitList;
    }

    @Override
    public List<ProductionUnit> getPrUnits(int empId, Session session) {
        List<ProductionUnit> unitList = session.createQuery(HQL_QUERY_UNITS_FOR_OWNER)
                .setParameter(FIELD_EMP_ID, empId)
                .list();
        return unitList;
    }

    @Override
    public ProductionUnit find(String unitTitle, Session session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
