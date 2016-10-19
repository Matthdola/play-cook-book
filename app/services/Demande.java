package services;

import java.time.LocalDateTime;

/**
 * Created by matth on 28/09/2016.
 */
public class Demande extends Document {
    private String id;
    private LocalDateTime dateDemande;
    private String idPatient;
    private String etat;

    public Demande(){

    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
