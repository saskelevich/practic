package by.practic.datalayer;

import java.util.List;

public interface IDao<T, Y> {
    T get(Integer id);

    T insert(T entity);

    void update(T entity);

    void delete(Integer id);

    List<T> getAll();

    Y find(Integer id);

    Y findByName(String name);

}
