package project.gamma.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.gamma.data.WithId;

import java.util.List;

/**
 * User: sergey
 * Date: 04.02.11
 */
@Transactional
@Repository
public interface DAO<T extends WithId> {
    void create(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> getAll();
}
