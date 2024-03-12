package examenTP;
import java.util.ArrayList;

public class main {
    public static void main(String[] args){

        Agent.processStartOfDay();
        // cree des livres
          Livre liv1=new Livre("History of Humankind","autheur",Categorie.History);
          Livre liv2=new Livre("Little Life","autheur",Categorie.ArtPhotography);

          System.out.println(liv1.getIdf());
        // creer les examplaire
          for(int i=0;i<4;i++){
              liv1.addCopy(new Exemplaire(liv1));
          }
          for(int i=0;i<4;i++){
            liv2.addCopy(new Exemplaire(liv2));
          }

         // cree les exemplaires
          Exemplaire copy1=new Exemplaire(liv1);
        Exemplaire copy2=new Exemplaire(liv2);
        // creer les abonnes
        AbonneVIP ab1=new AbonneVIP("karim","guenane");
        AbonneRegulier ab2=new AbonneRegulier("souhil","dn");
        ab2.borrow(copy2);
        ab1.borrow(copy1);
        ab1.borrow(copy2);

        // tester les methodes
        Agent.addBook(liv1);
        Agent.addBook(liv2);
        // enregistrer les utilisateurs dans la bibliotheque
        Agent.adduser(ab1);
        Agent.adduser(ab2);
        Agent.processEndOfDay();
        Bibliotheque.chercherLivresParTitre("Little Life");
        Bibliotheque.chercherLivresParTitre("Little");




    }
}
