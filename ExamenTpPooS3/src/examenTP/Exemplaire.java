package examenTP;

public class Exemplaire extends Livre{
    private static int idCounter = 1;
    protected String idf;
    protected String State;
    protected boolean disponible;
    protected Loan emprunt;

    public Exemplaire(Livre livre) {
        super(livre.getTitre(),livre.getCategorie());
        this.idf=generateIdentifiant()+livre.idf;
        this.State="disponible" ;
        disponible=true;
        livre.addCopy(this);
        idCounter++;
    }

    public boolean isAvailable() {
        return disponible;
    }


    private String generateIdentifiant() {
        return "E" + idCounter;
    }

    public void setIdf(String idf) {
        this.idf = idf;
    }

    public String getIdf() {
        return idf;
    }

    public void setState(String state) {
        State = state;
    }

    public String getState() {
        return State;
    }

    public void borrow(Loan emprunt) {
        this.State="Emprunté";
        this.disponible = false;
        this.emprunt = emprunt;
    }
}
