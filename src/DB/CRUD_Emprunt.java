package DB;
import Model.Client;
import Model.Emprunt;
import Model.Kindle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class CRUD_Emprunt {
        public Boolean ajouterEmprunt(int idClient,int idKindle,Date dateEmprunt){
            boolean addStat=false;
        try{  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            System.out.println("connected");
            String query="insert into emprunt (IdClient, IdKindle, dateEmprunt)"
        + " values (?, ?, ?)";
           PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, idClient);
            preparedStmt.setInt (2, idKindle);
            preparedStmt.setDate(3, new java.sql.Date(dateEmprunt.getTime()));
            addStat=preparedStmt.execute();
        }catch(SQLException e){
        System.out.println(e);
        }
          return addStat;  
    }
        public Boolean rendreEmprunt(int idEmpr){
             Calendar calendar = Calendar.getInstance();
             java.util.Date currentDate = calendar.getTime();
        try{  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            System.out.println("connected");
            String query="update emprunt set dateRetour = ? where idEmprunt = ?";
           PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setDate (1, new java.sql.Date(currentDate.getTime()));
            preparedStmt.setInt(2, idEmpr);
            preparedStmt.executeUpdate();
            return true;
        }catch(SQLException e){
        System.out.println(e);
        return false;
        }
        }
        public ArrayList<Emprunt> getEmpruntEnCour(){
            ArrayList<Emprunt> empr= new ArrayList<>();
        try{  
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
           System.out.println("connected");
           Statement stmt = con.createStatement();
           String query="select * from emprunt where dateRetour is null";
           ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Emprunt emp=new Emprunt(rs.getInt("idEmprunt"),rs.getInt("IdClient"),rs.getInt("IdKindle"),rs.getDate("dateEmprunt"),null);
                empr.add(emp);
            }
            return empr;
        }catch(SQLException e){
        System.out.println(e);
        return null;
        }
        }
        public Boolean SupprEmprunt(int IdEmprunt){
        try
        {
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
          String query = "delete from emprunt  where idEmprunt = ?";
          PreparedStatement preparedStmt = con.prepareStatement(query);
          preparedStmt.setInt(1, IdEmprunt);
          preparedStmt.execute();
          con.close();
          System.out.println("Emprunt with id = "+IdEmprunt+" deleted ");
          return true;
        }
        catch (Exception e)
        {
          System.err.println(e.getMessage());
          return false;
        }
        }
}
