package examenTP;
import examenTP.*;
import java.util.ArrayList;
import java.util.Date;

public class Agent {
    public ArrayList<Operation> operations; // Il y a deux types d'opérations : BorrowDemand et Loan
    public static ArrayList<BorrowDemand> borrowDemands=new ArrayList<>(); // File d'attente globale des demandes d'emprunt

    public Agent() {
        this.operations = new ArrayList<>();
        borrowDemands = new ArrayList<>();
    }

    public static void processStartOfDay() {
        // Nettoyer toutes les files d'attente
        borrowDemands.clear();
    }

    public  static void processEndOfDay() {
        // Calculer les amendes de retard pour les abonnés
        CalculerDebt();
        canLoanBook();

    }
    // ajouter les livres dans la bibliotheque
    public static void addBook(Livre book) {
        Bibliotheque.livres.add(book);
    }
    // ajouter une copie a un livre déja existe
    public void addNewCopy(Livre book) {
        // Ajouter un nouvel exemplaire à un livre existant
        Exemplaire newCopy = new Exemplaire(book);
    }
    // ajouter des utilisateurs
    public static void addNewUser(String firstname, String lastname, Date birthdate, PlanAbonnement plan) {
        // Créer un nouvel abonné avec le plan spécifié
        Abonne newUser = (plan == PlanAbonnement.REGULIER) ?
                new AbonneRegulier(firstname, lastname) :
                new AbonneVIP(firstname, lastname);

        Bibliotheque.utilisateurs.add(newUser);
    }
    public static void adduser(Abonne ab){
        Bibliotheque.utilisateurs.add(ab);
    }

    // calcule
    public static void CalculerDebt(){
        for(Abonne user : Bibliotheque.utilisateurs){
            double sum=user.lateFin;
            for(Loan loan: user.getLoans()){
                 sum+=loan.calculateFine();
            }
            user.lateFin=sum;
        }
    }

    public static void canLoanBook() {
        // check if it is possible to launch this borrow demands
        for (BorrowDemand i : borrowDemands) {
            System.out.println("L'utilisateur " + i.user.firstname + " " + i.user.lastname + "  souhaite emprunter le livre intitulé" + i.getCopy().titre);

            if(i.user.lateFin!=0) {
                System.out.println("user " + i.user.firstname + " " + i.user.lastname + " is in debt!!");
                i.getCopy().setState("Available");
                continue;
            }

            if(i.user.empruntsPleine()) {
                System.out.println("L'utilisateur " + i.user.firstname + " " + i.user.lastname + "  ne peut pas emprunter un autre livre !");
                i.getCopy().setState("Available");
                continue;
            }

            Loan loan = new Loan(i.getCopy(), i.user);
            i.user.getLoans().add(loan);
            System.out.println("L'utilisateur " + i.user.firstname + " " + i.user.lastname + " Vient d'emprunter " + i.getCopy().titre);
        }
    }
}
