package fr.norsys.dao;

import fr.norsys.utils.PersistenceManager;
import fr.norsys.entity.Projet;
import fr.norsys.entity.Tache;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class TacheDao {
    PersistenceManager persistenceManager=new PersistenceManager();

    public void saveTache(Tache tache) {
        EntityManager entityManager = persistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {

            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(tache);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(" tache non enregistré: " + ex);
            throw new RuntimeException("tache non enregistré: ", ex);
        }
    }

    public List<Tache> rechercher() {
        EntityManager entityManager = persistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            String hql = "From Tache P ORDER BY idTache ASC ";
            Query query = entityManager.createQuery(hql, Tache.class);
            List<Tache> result = query.getResultList();
            transaction.commit();
            return result;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    public void deleteTaches(int idProjet){
        EntityManager entityManager = persistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Projet projet = entityManager.find(Projet.class , idProjet);
            if (projet == null) {
                throw new IllegalArgumentException("tache avec ID " + idProjet + " non trouvé");
            }
            String hql = "DELETE From Tache u WHERE u.projet = :projet  ";
            Query query = entityManager.createQuery(hql, Tache.class);
            query.setParameter("projet", projet);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();

        }

    }
}
