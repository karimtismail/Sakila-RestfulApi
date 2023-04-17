package com.iti.sakilaapi.service;

import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.implementation.BaseEntityRepositoryImpl;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic base service for CRUD operations on entities with Data Transfer Objects (DTOs).
 *
 * @param <T>  The entity class.
 * @param <ID> The entity identifier class.
 * @param <D>  The Data Transfer Object class.
 */
public class BaseService<T, ID, D> {

    private final BaseEntityRepositoryImpl<T, ID> repository;
    private final ModelMapper mapper;
    private final Class<D> dtoClass;

    /**
     * Constructs a new BaseService instance.
     *
     * @param entityClass The class of the entity.
     * @param dtoClass    The class of the Data Transfer Object.
     */
    public BaseService(Class<T> entityClass, Class<D> dtoClass) {
        this.dtoClass = dtoClass;
        this.repository = new BaseEntityRepositoryImpl<>(new TransactionalEntityManager(), entityClass);
        this.mapper = new ModelMapper();
    }

    /**
     * Finds the entity with the specified identifier and returns its corresponding Data Transfer Object.
     *
     * @param id The identifier of the entity to find.
     * @return The corresponding Data Transfer Object.
     */
    public D findById(ID id) {
        T entity = repository.findById(id);
        return mapper.map(entity, dtoClass);
    }

    /**
     * Finds all entities and returns their corresponding Data Transfer Objects.
     *
     * @return The list of corresponding Data Transfer Objects.
     */
    public List<D> findAll() {
        List<T> entities = repository.findAll();
        List<D> dto = new ArrayList<>();
        for (T entity : entities) {
            dto.add(mapper.map(entity, dtoClass));
        }
        return dto;
    }

    /**
     * Saves the specified entity and returns its corresponding Data Transfer Object.
     *
     * @param entity The entity to save.
     * @return The corresponding Data Transfer Object.
     */
    public D save(T entity) {
        T savedEntity = repository.save(entity);
        return mapper.map(savedEntity, dtoClass);
    }

    /**
     * Updates the specified entity and returns its corresponding Data Transfer Object.
     *
     * @param entity The entity to update.
     * @return The corresponding Data Transfer Object.
     */
    public D update(T entity) {
        T updatedEntity = repository.update(entity);
        return mapper.map(updatedEntity, dtoClass);
    }

    /**
     * Deletes the entity with the specified identifier and returns its corresponding Data Transfer Object.
     *
     * @param id The identifier of the entity to delete.
     * @return The corresponding Data Transfer Object.
     */
    public D deleteById(ID id) {
        T deleteEntity = repository.deleteById(id);
        if (deleteEntity == null) {
            throw new NotFoundException("Not found this id " + id);
        }
        return mapper.map(deleteEntity, dtoClass);
    }
}