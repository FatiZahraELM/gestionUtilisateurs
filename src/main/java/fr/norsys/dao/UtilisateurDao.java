package fr.norsys.dao;

import fr.norsys.configuration.Config;
import fr.norsys.entity.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class UtilisateurDao {

    Config config =new Config();

    public List<Utilisateur> rechercher() {
        EntityManager entityManager = config.getEntityManager();
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
        EntityManager entityManager = config.getEntityManager();
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
