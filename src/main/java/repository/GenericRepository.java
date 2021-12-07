package repository;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */
public interface GenericRepository<T, ID> {

    T getById(ID id);
    List<T> findAll();
    T save(T entity);
    void update(T entity);
    void deleteById(ID id);
}
