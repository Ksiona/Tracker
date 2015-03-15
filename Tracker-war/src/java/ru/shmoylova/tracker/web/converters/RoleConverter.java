package ru.shmoylova.tracker.web.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import ru.shmoylova.tracker.entity.Role;
import ru.shmoylova.tracker.web.controllers.PermissionController;

/**
 *
 * @author Ksiona
 */
@ManagedBean
@FacesConverter(forClass = Role.class)
public class RoleConverter implements Converter {

    @Inject
    PermissionController pc;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Role role = null;
        for (Role r : pc.getRoleList()) {
            if (r.getRoleId() == new Integer(value)) {
                role = r;
            }
        }
        System.out.println("converted to object: " + role + "(" + role.getRoleName() + ")");
        return role;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String res = String.valueOf(((Role) value).getRoleId());
        System.out.println("converted to string: " + res);
        return res;
    }

}
