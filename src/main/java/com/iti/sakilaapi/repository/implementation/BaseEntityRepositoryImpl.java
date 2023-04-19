package com.iti.sakilaapi.repository.implementation;

import com.iti.sakilaapi.repository.TransactionalEntityManager;
import com.iti.sakilaapi.repository.interfaces.BaseEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.NotFoundException;
import org.hibernate.TransactionException;

import java.util.List;

/**
 * The {@code BaseEntityRepositoryImpl} class is a base implementation of the {@link BaseEntityRepository} interface
 * that provides basic CRUD operations for a specific entity type managed by this repository.
 *
 * @param <T>  the type of entity managed by this repository
 * @param <ID> the type of the identifier for the entity
 */
public class BaseEntityRepositoryImpl<T, ID> implements BaseEntityRepository<T, ID> {

    protected final TransactionalEntityManager transactionalEntityManager;
    private final Class<T> entityClass;

    /**
     * Constructs a new {@code BaseEntityRepositoryImpl} instance with the given {@link TransactionalEntityManager}
     * and entity class.
     *
     * @param transactionalEntityManager the {@link TransactionalEntityManager} to use for executing database operations
     * @param entityClass                the entity class managed by this repository
     */
    public BaseEntityRepositoryImpl(TransactionalEntityManager transactionalEntityManager, Class<T> entityClass) {
        this.transactionalEntityManager = transactionalEntityManager;
        this.entityClass = entityClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * Finds an entity by its identifier.
     *
     * @param id the identifier of the entity to find
     * @return the entity with the given identifier, or {@code null} if not found
     */
    @Override
    public T findById(ID id, EntityManager entityManager) {
        try {
            return entityManager.find(entityClass, id);
        } catch (Exception ex) {
            throw new NotFoundException(entityClass.getSimpleName() + " id " + id + " is not exist into our database");
        }
//        return transactionalEntityManager.executeInTransaction(entityManager.find(entityClass, id));
    }

    /**
     * Finds all entities managed by this repository.
     *
     * @return a list of all entities managed by this repository
     */
    @Override
    public List<T> findAll(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Saves an entity to the database.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    @Override
    public T save(T entity) {
        transactionalEntityManager.executeInTransactionWithoutResult(entityManager -> entityManager.persist(entity));
        return entity;
    }

    /**
     * Updates an entity in the database.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    @Override
    public T update(T entity) {
        transactionalEntityManager.executeInTransactionWithoutResult(entityManager -> entityManager.merge(entity));
        return entity;
    }

    /**
     * Deletes the specified entity from the database.
     *
     * @param entity the entity to delete
     * @throws IllegalArgumentException if the entity is not managed by the persistence context
     * @throws TransactionException     if an error occurs while executing the delete operation within a transaction
     */
    @Override
    public void delete(T entity) throws IllegalArgumentException, TransactionException {
        transactionalEntityManager.executeInTransactionWithoutResult(entityManager -> {
            if (entityManager.contains(entity)) {
                entityManager.remove(entity);
            } else {
                T managedEntity = entityManager.merge(entity);
                if (!entityManager.contains(managedEntity)) {
                    throw new IllegalArgumentException("Cannot delete a detached entity");
                }
                entityManager.remove(managedEntity);
            }
        });
    }
}