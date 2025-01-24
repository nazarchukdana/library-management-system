package services;

import java.lang.reflect.Field;
import java.util.List;

public interface EntityService<T>{
    void create(T entity);

    void delete(int id);

    void update(T entity);
    List<T> getAll();
    <U> U getReference(Field field, int id);

    T getById(int id);
}
