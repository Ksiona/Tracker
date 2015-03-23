package ru.shmoylova.tracker.dao;

import java.util.List;
import org.hibernate.Session;
import ru.shmoylova.tracker.interfaces.dao.BaseEntity;
import ru.shmoylova.tracker.interfaces.dao.GenericDao;

/**
 *
 * @author Ksiona
 * @param <T>
 */
public abstract class GenericDaoHibernateImpl<T extends BaseEntity> implements GenericDao<T> {

    public GenericDaoHibernateImpl() {
    }

    @Override
    public T get(Session session, Class<T> type, int id) {
        T result = (T) session.get(type, id);
        return result;
    }

    @Override
    public void create(Session session, T entity) {
        session.saveOrUpdate(entity);
    }

    @Override
    public void update(Session session, T entity) {
        session.merge(entity);
    }

    @Override
    public void delete(Session session, T entity) {
        session.delete(entity);
    }
    
    public abstract List<T> getAll(Session session);
}
