package com.iti.sakilaapi.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * The JPAUtil class provides a utility method for creating and managing an
 * EntityManagerFactory.
 */
public class JPAUtil {
    /**
     * The singleton instance of the EntityManagerFactory.
     */
    private volatile static EntityManagerFactory entityManagerFactory = null;

    /**
     * Private constructor to prevent instantiation of JPAUtil.
     */
    private JPAUtil() {
    }

    /**
     * Returns the singleton instance of EntityManagerFactory. If the instance
     * does not exist, it is created.
     *
     * @return EntityManagerFactory singleton instance.
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            synchronized (JPAUtil.class) {
                if (entityManagerFactory == null) {
                    entityManagerFactory = Persistence.createEntityManagerFactory("sakila");
                }
            }
        }
        return entityManagerFactory;
    }

    /**
     * Closes the EntityManagerFactory if it exists and sets the instance to null.
     */
    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }
}