package fr.norsys.dao;

import fr.norsys.utils.PersistenceManager;
import fr.norsys.entity.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class UtilisateurDao {

    PersistenceManager persistenceManager =new PersistenceManager();
    public void saveUtilisateur(Utilisateur utilisateur) {
        EntityManager entityManager = persistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {

            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(utilisateur);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(" utilisateur non enregistré: " + ex);
            throw new RuntimeException(" utilisateur non enregistré: ", ex);
        }
    }

    public List<Utilisateur> rechercher() {
        EntityManager entityManager = persistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            String hql = "From Utilisateur u  ORDER BY idUtilisateur ASC ";
            Query query = entityManager.createQuery(hql, Utilisateur.class);
            List<Utilisateur> result = query.getResultList();
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

    public void deleteUtilisateur()
    {
        EntityManager entityManager = persistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            String hql = "DELETE From Utilisateur u WHERE u.idUtilisateur =:idUtilisateur  ";
            Query query = entityManager.createQuery(hql, Utilisateur.class);
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
