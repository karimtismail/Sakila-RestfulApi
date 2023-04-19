package com.iti.sakilaapi.repository.interfaces;

import jakarta.persistence.EntityManager;
import org.hibernate.TransactionException;

import java.util.List;

/**
 * The {@code BaseEntityRepository} interface defines the basic CRUD operations for a specific entity type.
 *
 * @param <T>  the type of entity managed by this repository
 * @param <ID> the type of the identifier for the entity
 */
public interface BaseEntityRepository<T, ID> {

    /**
     * Finds an entity by its identifier.
     *
     * @param id the identifier of the entity to find
     * @return the entity with the given identifier, or {@code null} if not found
     */
    T findById(ID id, EntityManager entityManager);

    /**
     * Finds all entities managed by this repository.
     *
     * @return a list of all entities managed by this repository
     */
    List<T> findAll(EntityManager entityManager);

    /**
     * Saves an entity to the database.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    T save(T entity);

    /**
     * Updates an entity in the database.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    T update(T entity);

    /**
     * Deletes the specified entity from the database.
     *
     * @param entity the entity to delete
     * @throws IllegalArgumentException if the entity is not managed by the persistence context
     * @throws TransactionException     if an error occurs while executing the delete operation within a transaction
     */
    void delete(T entity) throws IllegalArgumentException, TransactionException;
}
