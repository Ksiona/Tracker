package ru.shmoylova.tracker.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.shmoylova.tracker.interfaces.beans.ManagementSessionBeanLocal;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.xml.bind.JAXBException;
import org.hibernate.CacheMode;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;

/**
 *
 * @author Ksiona
 */
@Stateless
public class ManagementSessionBean implements ManagementSessionBeanLocal {

    private static final String FILEPATH_XSL_TEMPLATE = "http://localhost:8080/Tracker-war/resources/xsl/test.xsl";
    private JaxbProcessor jaxbProcessor;
    private XsltProcessor xslt;
    private URL xslUrl;
    private FullTextSession fullTextSession;

    public ManagementSessionBean() throws JAXBException {
        jaxbProcessor = new JaxbProcessor();
    }

    @Interceptors(SearchTransactionInterceptor.class)
    @Override
    public void reIndexEntireDatabase() {
        MassIndexer index = null;
        index = fullTextSession
                .createIndexer()
                .typesToIndexInParallel(2)
                .batchSizeToLoadObjects(25)
                .cacheMode(CacheMode.NORMAL)
                .threadsToLoadObjects(2)
                .idFetchSize(150);
        index.start();
        fullTextSession.flushToIndexes();
    }

    @Override
    public boolean getXmlData(List empList, String xhtmlName, String fileName) {
        XmlResult root = new XmlResult(empList, null, null);
        File xml = new File("./", fileName);
        try (OutputStream xmlStream = new FileOutputStream(xml)){
            jaxbProcessor.executeMarshal(root, xmlStream);

            xslUrl = new URL(FILEPATH_XSL_TEMPLATE);
            xslt = new XsltProcessor(xml, xslUrl, xhtmlName);
            xslt.transform();
        } catch (JAXBException | IOException e) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }

    @Override
    public JaxbProcessor getJaxbProcessor() {
        return jaxbProcessor;
    }
}
