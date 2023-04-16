package com.iti.sakilaapi.repository;

import com.iti.sakilaapi.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This class manages transactions and sessions with the database using the EntityManager and Hibernate SessionFactory.
 * It provides methods to execute database queries in a transaction and with parameters.
 */
public class TransactionalEntityManager {
    /**
     * The entity manager factory used to create entity managers.
     */
    private final EntityManagerFactory factory;

    /**
     * The Hibernate session factory used to open sessions.
     */
    private final SessionFactory sessionFactory;

    /**
     * Creates a new {@code TransactionalEntityManager} using the default entity manager factory.
     */
    public TransactionalEntityManager() {
        this.factory = JPAUtil.getEntityManagerFactory();
        this.sessionFactory = JPAUtil.getEntityManagerFactory().unwrap(SessionFactory.class);
    }

    /**
     * Executes a transactional operation on the entity manager.
     *
     * @param transactionFunction the function to execute within the transaction
     * @param <R>                 the return type of the transaction
     * @return the result of the transaction
     */
    public <R> R executeInTransaction(Function<EntityManager, R> transactionFunction) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try (EntityManager entityManager = factory.createEntityManager()) {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            try {
                R result = transactionFunction.apply(entityManager);
                entityTransaction.commit();
                return result;
            } catch (Exception e) {
                entityTransaction.rollback();
                throw e;
            }
        } finally {
            transaction.commit();
            session.close();
        }
    }

    /**
     * Executes a transactional operation on the entity manager without returning a result.
     *
     * @param transactionFunction the function to execute within the transaction
     */
    public void executeInTransactionWithoutResult(Consumer<EntityManager> transactionFunction) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try (EntityManager entityManager = factory.createEntityManager()) {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            try {
                transactionFunction.accept(entityManager);
                entityTransaction.commit();
            } catch (Exception e) {
                entityTransaction.rollback();
                throw e;
            }
        } finally {
            transaction.commit();
            session.close();
        }
    }

    /**
     * Executes a select query with a parameter on the entity manager.
     *
     * @param query       the JPQL query string to execute
     * @param parameter   the parameter to set on the query
     * @param resultClass the class of the result type
     * @param <T>         the result type
     * @return a list of results
     */
    public <T> List<T> executeSelectQueryWithParameter(String query, Object parameter, Class<T> resultClass) {
        final int parameterIndex = 1;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            return executeInTransaction(entityManager ->
                    entityManager.createQuery(query, resultClass)
                            .setParameter(parameterIndex, parameter)
                            .getResultList());
        } finally {
            transaction.commit();
            session.close();
        }
    }
}