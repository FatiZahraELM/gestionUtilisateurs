package fr.norsys.entity;


import javax.persistence.*;
import java.util.List;

@Entity
//@NamedQuery(name="Projet.persist", query="insert into Projet (nomProjet,descreption) values()")
@Table(name="projets")
public class Projet {

    @Id
    @Column(name="idProjet")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idProjet;


    @Column(name="nomProjet")
    private String nomProjet;

    @Column(name="descreption")
    private String descreption;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }




    public int getIdProjet() {
        return idProjet;
    }

    public Projet(String nomProjet, String descreption,Utilisateur utilisateur) {
        this.nomProjet = nomProjet;
        this.descreption = descreption;
        this.utilisateur=utilisateur;
    }

    public Projet(){

    }
}
