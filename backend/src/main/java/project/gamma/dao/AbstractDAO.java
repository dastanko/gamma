package project.gamma.dao;

import project.gamma.data.Client;
import project.gamma.data.WithId;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: sergey
 * Date: 04.02.11
 */
public abstract class AbstractDAO<T extends WithId> implements DAO<T> {
    @PersistenceContext
    EntityManager entityManager;

    final Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        T t = entityManager.find(entityClass, (entity).getId());
        entityManager.remove(t);
    }

    public List<T> getAll() {
        TypedQuery<T> typedQuery = entityManager.createQuery("select o from " + entityClass.getName() + " o", entityClass);
        return typedQuery.getResultList();
    }
}
