package fr.norsys.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="utilisateurs")

public class Utilisateur {

    @Id
    @Column(name="idUtilisateur")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idUtilisateur;


    @Column(name="nom")
    private String nom;

    @Column(name="email")
    private String email;



    public Utilisateur(){

    }
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Utilisateur( String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

}
