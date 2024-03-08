package fr.norsys.entity;


import javax.persistence.*;

@Entity
@Table(name="taches")
public class Tache {

    @Id
    @Column(name="idTache")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idTache;


    @Column(name="titre")
    private String titre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idprojet")
    private Projet projet;


    public Tache(String nomTache) {
        this.titre = nomTache;
    }

    public Tache(){

    }

    public int getIdTache() {
        return idTache;
    }

    public void setIdTache(int idTache) {
        this.idTache = idTache;
    }



    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
