package com.incubator.app.dao;

public interface GenericDao<T> {
    void insert(T entity);
    void update(T entity);
    void delete(long id);
    T findById(long id);
}
