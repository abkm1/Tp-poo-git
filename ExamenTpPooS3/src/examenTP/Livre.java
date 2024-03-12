package examenTP;
import java.util.ArrayList;
import java.util.Date;

public class Livre {
    static int idcount=1;
    protected String idf;
    protected String titre;
    protected ArrayList<String>autheurs=new ArrayList<>();
    protected Categorie categorie;
    protected ArrayList<Exemplaire> copies;
    protected int numcat;

    // constructeur
    public Livre(String titre,String auther, Categorie categorie) {

        this.titre = titre;
        this.categorie = categorie;
        this.idf=generateIdentifiant();
        this.copies = new ArrayList<>();
        this.autheurs.add(auther);
        idcount++;
    }

    private void addAuth(String AT){
        this.autheurs.add(AT);
    }
    // generer idf
    private String generateIdentifiant() {
        switch (categorie) {
            case Fiction:
                numcat=1;
                break;
            case Comics:
                numcat=2;
                break;
            case BioMemo:
                numcat=3;
                break;
            case Religion:
                numcat=4;
                break;
            case ArtPhotography:
                numcat=5;
                break;
            case Health:
                numcat=6;
                break;
            case History:
                numcat=7;
                break;
            case Novel:
                numcat=8;
                break;
            default:
                numcat=9;
        }
        return "B" + idcount + "C" + numcat;
    }
    //get set
    public String getTitre() {
        return titre;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public String getIdf() {
        return idf;
    }

    public ArrayList<Exemplaire> getCopies() {
        return copies;
    }

    public void addCopy(Exemplaire exemplaire) {
        copies.add(exemplaire);
    }


    public void removeCopy(Exemplaire copy) {
        copies.remove(copy);
    }

    public Exemplaire getAvailableCopy() {
        for (Exemplaire copy : copies) {
            if (copy.getState().equals("disponible")) {
                return copy;
            }
        }
        return null; // Retourne null s'il n'y a pas d'exemplaire disponible
    }


}
