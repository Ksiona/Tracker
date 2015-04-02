package ru.shmoylova.tracker.interfaces.beans;

import java.net.URL;
import java.util.List;
import javax.ejb.Local;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;
import ru.shmoylova.tracker.logic.JaxbProcessor;

/**
 *
 * @author Ksiona
 */
@Local
public interface ManagementSessionBeanLocal {

    void reIndexEntireDatabase();

    boolean getXmlData(List<? extends BaseEntity> empList, String xhtmlPath, String fileName);

    JaxbProcessor getJaxbProcessor();
}
