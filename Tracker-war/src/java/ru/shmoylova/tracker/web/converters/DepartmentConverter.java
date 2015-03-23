package ru.shmoylova.tracker.web.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import ru.shmoylova.tracker.entity.Department;
import ru.shmoylova.tracker.web.controllers.DepartmentController;

/**
 *
 * @author Ksiona
 */
@ManagedBean
@FacesConverter(forClass = Department.class)
public class DepartmentConverter implements Converter {

    @Inject
    DepartmentController dc;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Department dept = null;
        for (Department d : dc.getDeptList()) {
            if (d.getDeptId() == new Integer(value)) {
                dept = d;
            }
        }
        return dept;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String res = String.valueOf(((Department) value).getDeptId());
        return res;
    }

}
