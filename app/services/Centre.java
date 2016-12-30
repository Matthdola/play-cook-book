package services;

import java.util.ArrayList;

public class Centre extends Document {
    private String nom;
    private String pays;
    private String ville;
    private String type;
    private String telephone;
    private String email;
    private String cellulaire;
    private String logoUrl;
    private ArrayList<Integer> specialites;

    public Centre(){

    }

    public Centre(String nom){
        this.nom = nom;
    }

    public Centre(String nom, String telephone){
        this.nom = nom;
        this.telephone = telephone;
    }

    public Object toBson(){
        return null;
    }

    public Centre fromBson(Object bson){
        return null;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellulaire() {
        return cellulaire;
    }

    public void setCellulaire(String cellulaire) {
        this.cellulaire = cellulaire;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public ArrayList<Integer> getSpecialites() {
        return specialites;
    }

    public void setSpecialites(ArrayList<Integer> specialites) {
        this.specialites = specialites;
    }

    public void addSpecialite(int numeroSpecialite){
        this.specialites.add(numeroSpecialite);
    }
}
