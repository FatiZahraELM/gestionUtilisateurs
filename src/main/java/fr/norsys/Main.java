package fr.norsys;

import fr.norsys.dao.ProjetDao;
import fr.norsys.dao.UtilisateurDao;
import fr.norsys.entity.Projet;
import fr.norsys.entity.Utilisateur;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UtilisateurDao utilisateurDao =new UtilisateurDao();

        Utilisateur user1 =new Utilisateur("elmouden","elmouden@gmail.com");
        Utilisateur user2 =new Utilisateur("fati","elmouden@gmail.com");
        Utilisateur user3 =new Utilisateur("zahra","elmouden@gmail.com");

        utilisateurDao.saveUtilisateur(user1);
        utilisateurDao.saveUtilisateur(user2);
        utilisateurDao.saveUtilisateur(user3);

        ProjetDao projetDao=new ProjetDao();
        Projet projet1=new Projet("pj1","le premier projet",user1);

        projetDao.saveProject(projet1);


    }
}