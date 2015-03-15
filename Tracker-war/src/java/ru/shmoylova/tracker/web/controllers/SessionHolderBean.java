package ru.shmoylova.tracker.web.controllers;

import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ru.shmoylova.tracker.interfaces.beans.EmployeeSessionBeanLocal;

/**
 *
 * @author Ksiona
 */
@ManagedBean
@ApplicationScoped
public class SessionHolderBean {

    private static final String PAGE_INDEX = "index";
    private static final String PAGE_ERROR = "error";
    protected static final String ERROR_LOGIN_PREFIX = "error_login_page_prefix";
    protected static final String ERROR_LOGIN = "login_failed";
    private int userID = 0;
    private String userLogin = null;
    private String userPassword = null;
    private String lastError = "";

    @EJB
    EmployeeSessionBeanLocal employeeBean;
    @Inject
    MessagesController messages;

    /**
     * Creates a new instance of SessionHolder
     */
    public SessionHolderBean() {
    }

    public String processExit() {
        userLogin = null;
        userPassword = null;
        userID = 0;
        return PAGE_INDEX;
    }

    public String processLogin() {
        this.userID = employeeBean.processLogin(userLogin, userPassword);
        if (userID > 0) {
            return PAGE_INDEX;
        } else {
            this.lastError = messages.getBundle(messages.BUNDLE_MSG_LOC, ERROR_LOGIN_PREFIX, ERROR_LOGIN);
            userLogin = null;
            userPassword = null;
            return PAGE_ERROR;
        }
    }

    public int getUserID() {
        return userID;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public void printLastError() {
        messages.printError(getLastError());
    }
}
