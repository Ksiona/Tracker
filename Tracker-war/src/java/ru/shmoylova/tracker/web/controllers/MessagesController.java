package ru.shmoylova.tracker.web.controllers;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Класс для вывода сообщений
 *
 * @author Ksiona
 */
@ManagedBean
@ApplicationScoped
public class MessagesController {

    protected static final String BUNDLE_MSG_LOC = "ru.shmoylova.tracker.web.nls.messages";
    protected static final String BUNDLE_CONTENT_LOC = "ru.shmoylova.tracker.web.nls.content";
    private ResourceBundle bundle;

    public MessagesController() {

    }

    public String getBundle(String bundleLocation, String... arr) {
        bundle = ResourceBundle.getBundle(bundleLocation, FacesContext.getCurrentInstance().getViewRoot().getLocale());
        String result = " ";
        for (int i = 0; i < arr.length; i++) {
            result += bundle.getString(arr[i]) + " ";
        }
        return result;
    }

    final public FacesMessage printMessage(String text, Severity level) {
        if (level == null) {
            level = FacesMessage.SEVERITY_ERROR;
        }
        FacesMessage fm = new FacesMessage(text);
        fm.setSeverity(level);
        FacesContext.getCurrentInstance().addMessage(null, fm);
        return fm;
    }

    final public void printError(String text) {
        printMessage(text, FacesMessage.SEVERITY_ERROR);
    }

    final public void printInfo(String text) {
        printMessage(text, FacesMessage.SEVERITY_INFO);
    }
}
