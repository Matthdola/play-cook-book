package services;

/**
 * Created by matth on 28/09/2016.
 */
public class Patient extends Utilisateur {
    private String codeAssurance;
    private String idAssureur;

    public Patient(){

    }

    public String typeUtilisateur(){
        return "patien";
    }

    public String getCodeAssurance() {
        return codeAssurance;
    }

    public void setCodeAssurance(String codeAssurance) {
        this.codeAssurance = codeAssurance;
    }

    public String getIdAssureur() {
        return idAssureur;
    }

    public void setIdAssureur(String idAssureur) {
        this.idAssureur = idAssureur;
    }
}
