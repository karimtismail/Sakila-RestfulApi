package com.iti.sakilaapi.repository;

import com.iti.sakilaapi.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class TransactionalEntityManager {

    /**
     * The EntityManagerFactory used to create EntityManager instances.
     */
    private final EntityManagerFactory factory;

    /**
     * Constructs a new TransactionalEntityManager using the default EntityManagerFactory.
     */
    public TransactionalEntityManager() {
        this.factory = JPAUtil.getEntityManagerFactory();
    }

    /**
     * Executes a transactional operation that returns a result.
     *
     * @param transactionFunction the transactional operation to be executed.
     * @param <R>                 the type of the result.
     * @return the result of the transactional operation.
     * @throws RuntimeException if there is an error executing the operation or committing the transaction.
     */
    public <R> R executeInTransaction(Function<EntityManager, R> transactionFunction) {
        try (EntityManager entityManager = factory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            try {
                R result = transactionFunction.apply(entityManager);
                transaction.commit();
                return result;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    /**
     * Executes a transactional operation that doesn't return a result.
     *
     * @param transactionFunction the transactional operation to be executed.
     * @throws RuntimeException if there is an error executing the operation or committing the transaction.
     */
    public void executeInTransactionWithoutResult(Consumer<EntityManager> transactionFunction) {
        try (EntityManager entityManager = factory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            try {
                transactionFunction.accept(entityManager);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    /**
     * Executes a SELECT query with a single parameter and returns the result list.
     *
     * @param query       the JPQL query to be executed.
     * @param parameter   the query parameter.
     * @param resultClass the type of the result.
     * @param <T>         the type of the result.
     * @return the result list.
     * @throws RuntimeException if there is an error executing the query or committing the transaction.
     */
    public <T> List<T> executeSelectQueryWithParameter(String query, Object parameter, Class<T> resultClass) {
        final int parameterIndex = 1;
        return executeInTransaction(entityManager ->
                entityManager.createQuery(query, resultClass)
                        .setParameter(parameterIndex, parameter)
                        .getResultList());
    }
}