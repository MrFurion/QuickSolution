package by.trubetski.quick.solution.interfaces;

import by.trubetski.quick.solution.models.User;

import java.util.List;

public interface EntityServices<T> {
    T findById(int id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(int id);
}
