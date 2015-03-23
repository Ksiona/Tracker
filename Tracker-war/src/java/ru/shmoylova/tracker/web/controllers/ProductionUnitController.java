/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shmoylova.tracker.web.controllers;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import ru.shmoylova.tracker.entity.ProductionUnit;
import ru.shmoylova.tracker.extra.IController;
import ru.shmoylova.tracker.interfaces.beans.ProductionUnitSessionBeanLocal;

/**
 *
 * @author Ksiona
 */
@Named(value = "productionUnitController")
@SessionScoped
public class ProductionUnitController implements IController {
    
    @EJB
    ProductionUnitSessionBeanLocal unitBean;
    private List<ProductionUnit> list;
    
    public ProductionUnitController() {
    }
    
    @Override
    public List<ProductionUnit> getList() {
        list = unitBean.getAllUnits();
        return list;
    }

    public List<ProductionUnit> getList(int empId) {
        list = unitBean.getUnitForOwner(empId);
        return list;
    }
    
    @Override
    public void recreateModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setList(List list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
