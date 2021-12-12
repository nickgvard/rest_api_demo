package service;

import java.util.List;

/**
 * @author Nikita Gvardeev 10.12.2021
 * email gvardeev@po-korf.ru
 */
public interface GenericService<T, ID> {

    T getById(ID id);
    List<T> findAll();
    T save(T t);
    T update(T t);
    void deleteById(ID id);
}