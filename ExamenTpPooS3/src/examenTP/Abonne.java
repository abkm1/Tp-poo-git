package examenTP;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Abonne {
    protected static int count =0;
    protected int id;
    protected String firstname,lastname;
    protected Abonnement abonnement;
    private ArrayList<Loan> emprunts=new ArrayList<>();
    public double lateFin=0;

    public Abonne(String firstnam, String lastname,Abonnement abonnement){
        this.id=count;
        this.firstname=firstnam;
        this.lastname=lastname;
        this.abonnement=abonnement;
        count++;
    }

    public ArrayList<Livre> chercherLivrePartitre(String booktitle) {
        return Bibliotheque.chercherLivresParTitre(booktitle);
    }

    public ArrayList<Livre> chercherLivreCategorie(Categorie category) {
       return Bibliotheque.chercherLivresParCategorie(category);
    }

    public ArrayList<Loan> getLoans() {
        ArrayList<Loan> loans = new ArrayList<>();
        for (Loan emprunt : emprunts) {
            if (emprunt.getDateRetour().isAfter(LocalDate.now())) {
                loans.add(emprunt);
            }
        }
        return loans;
    }

    public void addLoan(Loan emprunt) {
        emprunts.add(emprunt);
    }
    public boolean empruntsPleine(){
        return emprunts.size() >= getMaxEmprunts();
    }
    public int getMaxEmprunts() {
        if(abonnement.getPlan() == PlanAbonnement.REGULIER){
            return 2;
        }else{
            return 5;
        }
    }

    public int getId() {
        return id;
    }
    public Abonnement getAbonnement() {
        return abonnement;
    }
 // la method borrow
    public void borrow(Exemplaire copy) {
        if (copy.disponible&&lateFin==0&&!empruntsPleine()) {
            // Mettre à jour l'état de l'exemplaire
           copy.disponible = false;
           // Créer un nouvel emprunt et l'ajouter à la liste d'emprunts de l'utilisateur
            BorrowDemand br= new BorrowDemand(this, copy);
            Agent.borrowDemands.add(br);

            System.out.println("L'exemplaire a été emprunté avec succès.");
        } else {
            System.out.println("L'exemplaire n'est pas disponible pour l'emprunt.");
        }
    }


}
class Abonnement {
    private final PlanAbonnement plan;

    public Abonnement(PlanAbonnement plan) {
        this.plan = plan;
    }

    public PlanAbonnement getPlan() {
        return plan;
    }
}


