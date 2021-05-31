
package Model;

import java.util.Date;


public class Emprunt {
    private final int Id;
    private final int IdClient;
    private final int IdKindle;
    private final Date dateEmprunt;
    private final Date dateRetour;

    public Emprunt(int Id, int IdClient, int IdKindle, Date dateEmprunt, Date dateRetour) {
        this.Id = Id;
        this.IdClient = IdClient;
        this.IdKindle = IdKindle;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }
   
    public int getIdClient() {
        return IdClient;
    }

    public int getIdKindle() {
        return IdKindle;
    }

    public int getId() {
        return Id;
    }

    @Override
    public String toString() {
        if(dateRetour==null){
        return "Emprunt[" + "Id=" + Id + ", IdClient=" + IdClient + ", IdKindle=" + IdKindle + ", dateEmprunt=" + dateEmprunt + ']';
        }else{
            return "Emprunt[" + "Id=" + Id + ", IdClient=" + IdClient + ", IdKindle=" + IdKindle + ", dateEmprunt=" + dateEmprunt + ", dateRetour=" + dateRetour + ']';
        }
    }


    
}
