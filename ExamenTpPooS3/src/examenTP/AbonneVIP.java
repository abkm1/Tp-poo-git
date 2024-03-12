package examenTP;

import java.util.Date;

public class AbonneVIP extends Abonne {
    public AbonneVIP(String prenom, String nom) {
        super(prenom, nom , new Abonnement(PlanAbonnement.VIP));
    }

}
