package ru.shmoylova.tracker.interfaces.dao;

import java.util.List;
import org.hibernate.Session;
import ru.shmoylova.tracker.entity.ProductionUnit;

/**
 *
 * @author Ksiona
 */
public interface IProductionUnitDao extends GenericDao<ProductionUnit> {

    List<ProductionUnit> getPrUnits(int empId, Session session);
    ProductionUnit find(String unitTitle, Session session);
}
