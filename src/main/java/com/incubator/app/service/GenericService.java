package com.incubator.app.service;

public interface GenericService<T> {
    void insert(T entity);
    void update(T entity);
    void delete(long id);
    T findById(long id);

}