package view;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 11.12.2021
 */
public interface GenericView<T, ID> {

    T getById(ID id);
    List<T> findAll();
    T save(T t);
    T update(T t);
    T deleteById(ID id);
}