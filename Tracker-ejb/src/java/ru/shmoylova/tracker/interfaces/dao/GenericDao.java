package ru.shmoylova.tracker.interfaces.dao;

import java.util.List;

/**
 * @author Ksiona
 * @param <T>
 */
public interface GenericDao<T extends BaseEntity> {

    T get(Class<T> type, int id);

    void create(T entity);

    void update(T entity);

    void delete(T entity);

}
