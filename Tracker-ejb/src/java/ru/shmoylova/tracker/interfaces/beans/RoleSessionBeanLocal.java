/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shmoylova.tracker.interfaces.beans;

import java.util.List;
import javax.ejb.Local;
import ru.shmoylova.tracker.entity.Role;

/**
 *
 * @author Ksiona
 */
@Local
public interface RoleSessionBeanLocal {

    List<Role> getAllRoles();
    
}
