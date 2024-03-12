package examenTP;

import java.util.Date;

public class AbonneRegulier extends Abonne {
    public AbonneRegulier(String prenom, String nom) {
        super(prenom, nom, new Abonnement(PlanAbonnement.REGULIER));
    }

}
