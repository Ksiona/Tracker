package ru.shmoylova.tracker.web.controllers;

import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.xml.sax.SAXException;
import ru.shmoylova.tracker.entity.Employee;
import ru.shmoylova.tracker.interfaces.beans.ManagementSessionBeanLocal;

/**
 *
 * @author Ksiona
 */
@ManagedBean(name = "fileManager")
@SessionScoped
public class FileManager implements Serializable {

    @EJB
    ManagementSessionBeanLocal manager;

    private static final String BUNDLE_LOC = "ru.shmoylova.tracker.web.nls.messages";
    private static final String PAGE_EMPLOYEE = "emplist";
    private static final String PAGE_EXPORT = "export";
    private static final String SCHEME_LOC = "http://localhost:8080/Tracker-war/TrackerXMLScheme.xsd";
    private static final String URL_PATH = "http://localhost:8080/Tracker-war/exportedData/";
    private static final String EXTENTION_XML = ".xml";
    private static final String EXTENTION_XHTML = ".xhtml";
    private static final String ERROR_XML_VALIDATION = "not_valid";
    private static final String ERROR_BAD_FILE = "file_corrupted";
    private static final String UPLOAD_SUCCESS = "upload_success";
    private StreamedContent file;
    private String exportPath;

    /**
     * Creates a new instance of FileManager
     */
    public FileManager() {
    }

    public void getXmlData(List<Employee> empList) throws IOException {
        String fileName = String.valueOf(System.currentTimeMillis());
        setExportPath(fileName + EXTENTION_XHTML);
        URL xmlOut = compileUrl(URL_PATH, fileName, EXTENTION_XML);
        URL xhtmlOut = compileUrl(URL_PATH, fileName, EXTENTION_XHTML);
        manager.getXmlData(empList, xhtmlOut, xmlOut);
        navigatiionHandler(PAGE_EMPLOYEE, PAGE_EXPORT);
    }

    public StreamedContent getFile() {
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/exportedData/" + exportPath);
        file = new DefaultStreamedContent(stream, "markup/xml", exportPath);
        return file;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_LOC, FacesContext.getCurrentInstance().getViewRoot().getLocale());
        InputStream xml;
        try {
            xml = event.getFile().getInputstream();
            manager.getJaxbProcessor().executeUnmarshalValidate(xml, SCHEME_LOC);
            print(bundle.getString(UPLOAD_SUCCESS) + event.getFile().getFileName(), FacesMessage.SEVERITY_INFO);
        } catch (SAXException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            print(bundle.getString(ERROR_XML_VALIDATION), FacesMessage.SEVERITY_ERROR);
        } catch (JAXBException | MalformedURLException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            print(bundle.getString(ERROR_BAD_FILE), FacesMessage.SEVERITY_ERROR);
        }
    }

    public URL compileUrl(String path, String fileName, String extention) throws MalformedURLException {
        StringBuilder sb = new StringBuilder();
        sb.append(path).append(fileName).append(extention);
        return new URL(sb.toString());
    }

    public String getExportPath() {
        return exportPath;
    }

    public void setExportPath(String exportPath) {
        this.exportPath = exportPath;
    }

    public void print(String text, FacesMessage.Severity level) {
        FacesMessage message = new FacesMessage(level, null, text);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void navigatiionHandler(String actionPage, String outcomePage) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, actionPage, outcomePage);
    }
}
