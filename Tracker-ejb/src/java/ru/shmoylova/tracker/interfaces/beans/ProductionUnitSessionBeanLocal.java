package ru.shmoylova.tracker.interfaces.beans;

import java.util.List;
import javax.ejb.Local;
import ru.shmoylova.tracker.entity.ProductionUnit;

/**
 *
 * @author Ksiona
 */
@Local
public interface ProductionUnitSessionBeanLocal {

    List<ProductionUnit> getAllUnits();

    List<ProductionUnit> getUnitForOwner(int empId);

    ProductionUnit getUnit(String unitTitle);

    ProductionUnit getUnit(int unitId);
}
