package services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Medecin extends Utilisateur {
    private ArrayList<String> specialites;
    private ArrayList<String> centres;
    private ArrayList<String> joursConsultation;
    public static List<Medecin> medecins;

    static {
        medecins = new ArrayList<Medecin>();
        medecins.add(new Medecin("Ma"));
        medecins.add(new Medecin("Me"));
        medecins.add(new Medecin("MC"));
        medecins.add(new Medecin("MD"));
        medecins.add(new Medecin("ME"));
    }
    
    public Medecin(String id) {
        super();
        this.setId(id);
        this.specialites = new ArrayList<>();
        this.centres = new ArrayList<>();
        this.joursConsultation = new ArrayList<>();
    }

    public Medecin() {
        super();
        this.specialites = new ArrayList<>();
        this.centres = new ArrayList<>();
        this.joursConsultation = new ArrayList<>();
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

    public void addSpecialite(String specialite){
        this.specialites.add(specialite);
    }

    public  void addCentre(String centre){
        this.centres.add(centre);
    }

    public  void addJourConsultation(String jour){
        this.joursConsultation.add(jour);
    }

    public ArrayList<String> getJoursConsultation() {
        return joursConsultation;
    }

    public void setJoursConsultation(ArrayList<String> joursConsultation) {
        this.joursConsultation = joursConsultation;
    }

    public ArrayList<String> getCentres() {
        return centres;
    }

    public void setCentres(ArrayList<String> centres) {
        this.centres = centres;
    }

    public static List<Medecin> findAll() {
        return new ArrayList<>(medecins);
    }

    public static List<Medecin> listByCentre(String centre) {
        ArrayList<Medecin> meds = new ArrayList<>();
        medecins.forEach(m -> {
            if(m.getCentres().contains(centre)){
                meds.add(m);
            }
        });
        return meds;
    }

    public static List<Medecin> listBySpecialite(String specialite) {
        ArrayList<Medecin> meds = new ArrayList<>();
        medecins.forEach(m -> {
            if(m.getSpecialites().contains(specialite)){
                meds.add(m);
            }
        });
        return meds;
    }

    public static Medecin findById(String id){
        for (Medecin candidate : medecins){
            if(candidate.getId().equals(id)){
                return candidate;
            }
        }
        return null;
    }

    public static List<Medecin> findByName(String name){
        final  List<Medecin> results = new ArrayList<>();
        for (Medecin candidate : medecins){
            if(candidate.getNom().equals(name)){
                results.add(candidate);
            }
        }
        return results;
    }

    public static boolean remove(Medecin medecin){
        return medecin.remove(medecin);
    }

    public static void save(Medecin medecin){
        medecins.add(medecin);
    }

    public static void update(Medecin medecin){
        Predicate<Medecin> medecinPredicate = p -> p.getId().equals(medecin.getId());
        medecins.removeIf(medecinPredicate);
        medecins.add(medecin);
    }
}
