package ru.shmoylova.tracker.web.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ru.shmoylova.tracker.web.validators.EmpFormValidator")
public class EmpFormValidator implements Validator {

    private static final String BUNDLE_LOC = "ru.shmoylova.tracker.web.nls.messages";
    private static final String ERROR_PASS_REQUIRED = "pass_required";
    private static final String ERROR_EMPTY_FIELD = "empty_field_error";
    private static final String ERROR_LOGIN_FIRST_LETTER = "first_letter_error";
    private static final String COMPONENT_LAST_NAME = "lastName";
    private static final String COMPONENT_FIRST_NAME = "firstName";
    private static final String COMPONENT_SUR_NAME = "surName";
    private static final String COMPONENT_DEPT_NAME = "deptName";
    private static final String COMPONENT_JOB_NAME = "jobName";
    private static final String COMPONENT_LOGIN = "empLogin";
    private static final String COMPONENT_ID = "empId";

    public String getProperty(String name, UIComponent component) {
        EditableValueHolder holder = (EditableValueHolder) component.getParent().findComponent(name);
        return holder.getValue().toString();
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_LOC, FacesContext.getCurrentInstance().getViewRoot().getLocale());

        String names[] = new String[]{COMPONENT_LAST_NAME, COMPONENT_FIRST_NAME, COMPONENT_DEPT_NAME, COMPONENT_JOB_NAME, COMPONENT_LOGIN};
        List<String> values = new ArrayList<>();
        for (String property : names) {
            values.add(getProperty(property, component));
        }

        String surName = getProperty(COMPONENT_SUR_NAME, component);
        String empId = getProperty(COMPONENT_ID, component);

        try {
            String newValue = value.toString();

            if (newValue.length() < 1 && empId != null) {
                throw new IllegalArgumentException(bundle.getString(ERROR_PASS_REQUIRED));
            }

            for (String propValue : values) {
                if (propValue.length() < 1) {
                    throw new IllegalArgumentException(bundle.getString(ERROR_EMPTY_FIELD));
                }

                if (!Character.isLetter(propValue.charAt(0))) {
                    throw new IllegalArgumentException(bundle.getString(ERROR_LOGIN_FIRST_LETTER));
                }
            }
            if (!Character.isLetter(surName.charAt(0))) {
                throw new IllegalArgumentException(bundle.getString(ERROR_LOGIN_FIRST_LETTER));
            }
        } catch (IllegalArgumentException e) {
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

    }
}
