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
 * @param <DR> The Data Transfer Object class for requests.
 * @param <DS> The Data Transfer Object class for responses.
 */
public class BaseService<T, ID, DR, DS> {

    private final BaseEntityRepositoryImpl<T, ID> repository;
    private final ModelMapper mapper;
    private final Class<DR> dtoClassRequest;
    private final Class<DS> dtoClassResponse;
    private final TransactionalEntityManager transactionalEntityManager;

    /**
     * Constructs a new BaseService instance.
     *
     * @param entityClass      The class of the entity.
     * @param dtoClassRequest  The class of the Data Transfer Object for requests.
     * @param dtoClassResponse The class of the Data Transfer Object for responses.
     */
    public BaseService(Class<T> entityClass, Class<DR> dtoClassRequest, Class<DS> dtoClassResponse) {
        this.dtoClassRequest = dtoClassRequest;
        this.dtoClassResponse = dtoClassResponse;
        this.repository = new BaseEntityRepositoryImpl<>(new TransactionalEntityManager(), entityClass);
        this.mapper = new ModelMapper();
        this.transactionalEntityManager = new TransactionalEntityManager();
    }

    /**
     * Finds the entity with the specified identifier and returns its corresponding Data Transfer Object for response.
     *
     * @param id The identifier of the entity to find.
     * @return The corresponding Data Transfer Object for response.
     */
    public DS findById(ID id) {
        return transactionalEntityManager.executeInTransaction(entityManager -> {
            T entity = repository.findById(id, entityManager);
            return mapper.map(entity, dtoClassResponse);
        });
    }

    /**
     * Finds all entities and returns their corresponding Data Transfer Objects for response.
     *
     * @return The list of corresponding Data Transfer Objects for response.
     */
    public List<DS> findAll() {
        return transactionalEntityManager.executeInTransaction(entityManager -> {
            List<T> entities = repository.findAll(entityManager);
            List<DS> dto = new ArrayList<>();
            for (T entity : entities) {
                dto.add(mapper.map(entity, dtoClassResponse));
            }
            return dto;
        });
    }

    /**
     * Saves the specified entity and returns its corresponding Data Transfer Object for response.
     *
     * @param dtoRequest The Data Transfer Object for request.
     * @return The corresponding Data Transfer Object for response.
     */
    public DS save(DR dtoRequest) {
        T entity = mapper.map(dtoRequest, repository.getEntityClass());
        T savedEntity = repository.save(entity);
        return mapper.map(savedEntity, dtoClassResponse);
    }

    /**
     * Updates the entity with the specified identifier using the provided DTO request object
     * and returns its corresponding Data Transfer Object for response.
     *
     * @param id         The identifier of the entity to update.
     * @param dtoRequest The Data Transfer Object for request that contains the updates to be made to the entity.
     * @return The corresponding Data Transfer Object for response.
     * @throws NotFoundException        if the entity with the specified identifier is not found.
     * @throws IllegalArgumentException if the provided DTO request object is null.
     */
    public DS update(ID id, DR dtoRequest) {
        if (dtoRequest == null) {
            throw new IllegalArgumentException("DTO request cannot be null");
        }
        var entityTrans = transactionalEntityManager.executeInTransaction(
                entityManager -> repository.findById(id, entityManager)
        );
        if (entityTrans == null) {
            throw new NotFoundException("Entity not found with id: " + id);
        }
        mapper.map(dtoRequest, entityTrans);
        T updatedEntity = repository.update(entityTrans);
        return mapper.map(updatedEntity, dtoClassResponse);
    }

    /**
     * Deletes the entity with the specified identifier and returns its corresponding Data Transfer Object for response.
     *
     * @param id The identifier of the entity to delete.
     * @return The corresponding Data Transfer Object for response.
     * @throws NotFoundException if the entity with the specified identifier is not found.
     */
    public DS deleteById(ID id) {

        var entityTrans = transactionalEntityManager.executeInTransaction(
                entityManager -> repository.findById(id, entityManager)
        );
        if (entityTrans == null) {
            throw new NotFoundException("Entity not found with id: " + id);
        }
        repository.delete(entityTrans);
        return mapper.map(entityTrans, dtoClassResponse);
    }
}