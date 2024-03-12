package examenTP;
import java.util.ArrayList;

public class Bibliotheque {
    static public ArrayList<Livre> livres=new ArrayList<>();
    static public ArrayList<Abonne> utilisateurs=new ArrayList<>();


    public static ArrayList<Livre> chercherLivresParTitre(String titre) {
        ArrayList<Livre> resultats = new ArrayList<>();
        String str="";
        for (Livre livre : livres) {
            if (livre.getTitre().equalsIgnoreCase(titre)) {
                resultats.add(livre);
                str+= livres.indexOf(livre)+" /";
            }
        }
        if(str!=""){
            System.out.println("le livre "+ "'" +titre+"' " + "est trouver dans "+ str + "etage");
        }else{
            System.out.println("le livre "+ "'" +titre +"' "+ "n'existe pas dans la bibliotheque");
        }
        return resultats;
    }

    public static ArrayList<Livre> chercherLivresParCategorie(Categorie categorie) {
        ArrayList<Livre> resultats = new ArrayList<>();
        StringBuilder str= new StringBuilder();
        for (Livre livre : livres) {
            if (livre.getCategorie() == categorie) {
                resultats.add(livre);
                str.append(livres.indexOf(livre)).append(" /");
            }
        }

        System.out.println("le livre de"+ categorie + "est trouver dans "+ str + "etage");

        return resultats;
    }
}
