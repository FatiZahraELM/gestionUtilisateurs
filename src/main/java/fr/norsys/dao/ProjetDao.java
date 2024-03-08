package fr.norsys.dao;

import fr.norsys.utils.PersistenceManager;
import fr.norsys.entity.Projet;
import fr.norsys.entity.Tache;
import fr.norsys.entity.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class ProjetDao {

    PersistenceManager persistenceManager=new PersistenceManager();

    public void saveProject(Projet project) {
        EntityManager entityManager = persistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {

            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(project);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Failed to save project: " + ex);
            throw new RuntimeException("Failed to save project", ex);
        }
    }

    public List<Projet> rechercher() {
        EntityManager entityManager = persistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            String hql = "From Projet P ORDER BY idProjet ASC ";
            Query query = entityManager.createQuery(hql, Projet.class);
            List<Projet> result = query.getResultList();
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

    public void save(Projet projet){
        EntityManager entityManager = persistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {

            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(projet);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Failed to save project: " + ex);
            throw new RuntimeException("Failed to save project", ex);
        }
    }

    public void deleteTaches(int idProjet){
        EntityManager entityManager = persistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            String hql = "delete From Tache t  WHERE t.idTache =: ";
            Query query = entityManager.createQuery(hql, Utilisateur.class);
            query.setParameter("IdProject", idProjet);
             query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }
    public void nvTacheProjet(Long idProjet, Tache tache) {
        EntityManager entityManager = persistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Projet projet = entityManager.find(Projet.class, idProjet);
            if (projet == null) {
                throw new IllegalArgumentException("Projet avec ID " + idProjet + " non trouvé");
            }

            tache.setProjet(projet);
            entityManager.persist(tache);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}
