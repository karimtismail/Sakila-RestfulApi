package com.iti.sakilaapi.data.repository.interfaces;

import java.util.List;

public interface BaseEntityRepository<T, ID> {
    T findById(ID id);
    List<T> findAll();
    T save(T entity);
    T update(T entity);
    T deleteById(ID id);
}