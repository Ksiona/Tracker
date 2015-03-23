package ru.shmoylova.tracker.web.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import ru.shmoylova.tracker.entity.Permission;
import ru.shmoylova.tracker.web.controllers.PermissionController;

/**
 *
 * @author Ksiona
 */
@ManagedBean
@FacesConverter(forClass = Permission.class)
public class PermissionConverter implements Converter {

    @Inject
    PermissionController pc;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Permission perm = null;
        for (Permission p : pc.getPermList()) {
            if (p.getPermId() == new Integer(value)) {
                perm = p;
            }
        }
        return perm;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String res = String.valueOf(((Permission) value).getPermId());
        return res;
    }

}
