package services;

/**
 * Created by matth on 28/09/2016.
 */
public class Specialite extends Document {
    private String id;
    private String nom;
    private String domaine;

    public Specialite(){

    }

    public Specialite(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
}
