package fr.norsys.dao;

import fr.norsys.configuration.Config;
import fr.norsys.entity.Projet;
import fr.norsys.entity.Tache;
import fr.norsys.entity.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class TacheDao {
    Config config=new Config();



    public List<Tache> rechercher() {
        EntityManager entityManager = config.getEntityManager();
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
        EntityManager entityManager = config.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Projet projet = entityManager.find(Projet.class , idProjet);
            if (projet == null) {
                throw new IllegalArgumentException("Projet avec ID " + idProjet + " non trouvé");
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
