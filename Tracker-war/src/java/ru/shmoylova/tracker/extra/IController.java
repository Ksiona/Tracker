/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.shmoylova.tracker.extra;

import java.util.List;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;

/**
 *
 * @author Ksiona
 */
public interface IController<T extends BaseEntity> {

    void recreateModel();

    List<T> getList();

    void setList(List<T> list);
}
