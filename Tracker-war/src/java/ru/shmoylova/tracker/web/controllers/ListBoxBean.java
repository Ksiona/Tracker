package ru.shmoylova.tracker.web.controllers;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author adminko
 */
@ManagedBean
@RequestScoped
public class ListBoxBean {

    @Inject
    EmployeeController ec;
    private static final String BUNDLE_LOC_CONTENT = "ru.shmoylova.tracker.web.nls.content";
    private static final String KEY_EMPTY = "action_listBox_empty";
    private static final String EMPTY_NAVIGATION = "null";
    private static final String KEY_EDIT = "action_listBox_edit";
    private static final String PAGE_VALUE_EDIT = "edit-emp";
    private static final String KEY_REMOVE = "action_listBox_remove";
    private static final String PAGE_REMOVE_EMP = "delete-emp";
    private static final String KEY_BROWSE = "action_listBox_browse";
    private static final String PAGE_BROWSE_EMP = "browse";
    private static final String PAGE_EMPLIST = "empList";
    private final ResourceBundle bundle;
    private String actionList;
    private Map<String, String> actionListItems;

    /**
     * Creates a new instance of ListBoxBean
     */
    public ListBoxBean() {
        bundle = ResourceBundle.getBundle(BUNDLE_LOC_CONTENT, FacesContext.getCurrentInstance().getViewRoot().getLocale());

    }

    private void putIntoMap(String key, String value) {
        actionListItems.put(bundle.getString(key), value);
    }

    public Map<String, String> getActionListItems() {
        actionListItems = new TreeMap<>();
        putIntoMap(KEY_EMPTY, EMPTY_NAVIGATION);
        putIntoMap(KEY_EDIT, PAGE_VALUE_EDIT);
        putIntoMap(KEY_REMOVE, PAGE_REMOVE_EMP);
        putIntoMap(KEY_BROWSE, PAGE_BROWSE_EMP);
        return actionListItems;
    }

    public void setActionListItems(Map<String, String> actionListItems) {
        this.actionListItems = actionListItems;
    }

    public String getActionList() {
        return actionList;
    }

    public void setActionList(String actionList) {
        this.actionList = actionList;
    }

    public void performAction(ValueChangeEvent event) {
        String selected = (String) event.getNewValue();
        FacesContext context = FacesContext.getCurrentInstance();
        for (String action : actionListItems.keySet()) {
            if (selected.equals(actionListItems.get(action)) && !selected.equals(EMPTY_NAVIGATION)) {
                context.getApplication().getNavigationHandler().handleNavigation(context, PAGE_EMPLIST, actionListItems.get(action));
            }
        }
    }
}
