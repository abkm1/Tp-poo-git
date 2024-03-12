package examenTP;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Loan extends Operation {
    private static int loanCount = 0;
    private int id;
    private Exemplaire copyLoaned;
    private Abonne userBorrower;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    private static final double FINE_DELAY_REGULAR_USER = 50;  // Modify according to your requirements
    private static final double FINE_DELAY_VIP_USER = 30;  // Modify according to your requirements


    public Loan(Exemplaire copy, Abonne user) {
        this.id = ++loanCount;
        this.copyLoaned = copy;
        this.userBorrower = user;
        this.dateEmprunt = LocalDate.now();
        if (userBorrower.getAbonnement().getPlan() == PlanAbonnement.REGULIER) {
            this.dateRetour = this.dateEmprunt.plusWeeks(2);
        } else {
            this.dateRetour = this.dateEmprunt.plusWeeks(4);
        }
        // Mettez à jour l'état de la copie
        copy.setState("Emprunté");
    }


   // get set
    public int getId() {
        return id;
    }
    public Exemplaire getCopyLoaned() {
        return copyLoaned;
    }
    public Abonne getUserBorrower() {
        return userBorrower;
    }
    public Exemplaire getExemplaire() {
        return copyLoaned;
    }
    public Abonne getAbonne() {
        return userBorrower;
    }
    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }
    public LocalDate getDateRetour() {
        return dateRetour;
    }



    // si l'utilisateur est en retard
    public boolean estEnRetard() {
        LocalDate dateActuelle = LocalDate.now();
        return dateActuelle.isAfter(dateRetour);
    }
// le nombre de retard en jours
    public long calculerRetardEnJours() {
        if (estEnRetard()) {
            return ChronoUnit.DAYS.between(dateRetour, LocalDate.now());
        } else {
            return 0;
        }
    }
// calculer les detes
    public double calculateFine() {
        if (estEnRetard()) {
            long daysLate = calculerRetardEnJours();
            if (userBorrower.getAbonnement().getPlan() == PlanAbonnement.REGULIER) {
                return FINE_DELAY_REGULAR_USER * daysLate;
            } else if (userBorrower.getAbonnement().getPlan() == PlanAbonnement.VIP) {
                return FINE_DELAY_VIP_USER * daysLate;
            }
        }
        return 0; // No fine if not late
    }

    @Override
    public String toString() {
        return "Emprunt [id=" + id + ", copyLoaned=" + copyLoaned.getIdf() +
                ", userBorrower=" + userBorrower.getId() + ", startLoan=" + dateRetour + "]";
    }
}
