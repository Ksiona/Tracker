package ru.shmoylova.tracker.interfaces.dao;

import java.util.List;
import org.hibernate.Session;

/**
 * @author Ksiona
 * @param <T>
 */
public interface GenericDao<T extends BaseEntity> {

    T get(Session session, Class<T> type, int id);

    void create(Session session, T entity);

    void update(Session session, T entity);

    void delete(Session session, List<T> entities);

}
