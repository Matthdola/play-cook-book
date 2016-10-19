package services;

import java.util.ArrayList;

/**
 * Created by matth on 28/09/2016.
 */
public class Medecin extends Utilisateur {
    private ArrayList<String> specialites;
    private int joursConsultation;

    public Medecin() {
        super();
    }

    public ArrayList<String> getSpecialites() {
        return specialites;
    }

    public String typeUtilisateur(){
        return "medecin";
    }

    public void setSpecialites(ArrayList<String> specialites) {
        this.specialites = specialites;
    }

    public int getJoursConsultation() {
        return joursConsultation;
    }

    public void setJoursConsultation(int joursConsultation) {
        this.joursConsultation = joursConsultation;
    }
}
