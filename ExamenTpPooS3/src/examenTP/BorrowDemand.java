package examenTP;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowDemand extends Operation{
    protected Abonne user;
    private Exemplaire copy;


    public BorrowDemand(Abonne user, Exemplaire copy ) {
        this.user = user;
        this.copy=copy;
    }

    public Abonne getUser() {
        return user;
    }

    public Exemplaire getCopy() {
        return copy;
    }

    @Override
    public String toString() {
        return "Demande d'emprunt [user=" + user.getId()  + "]";
    }
}
