package fr.norsys.configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Config {
    private final EntityManager entityManager;

    public Config() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}

