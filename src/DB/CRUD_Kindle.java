
package DB;
import Model.Client;
import java.util.*;

import Model.Kindle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class CRUD_Kindle {
    public ArrayList<Kindle> KindlesNonRendu(){
        ArrayList<Kindle> kindles = new ArrayList<>();
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt =con.createStatement();
            String query = "select * from kindle where Emprunte=true";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Kindle Kindle=new Kindle(rs.getInt("idKindle"),rs.getString("Modele"),rs.getString("Mac"),rs.getBoolean("Emprunte"));
                Kindle.setEmprunte(rs.getBoolean("Emprunte"));
                kindles.add(Kindle);
            }
            return kindles;
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }
    public ArrayList<Kindle> getAllKindles(){
        ArrayList<Kindle> kindles = new ArrayList<>();
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt =con.createStatement();
            String query = "select * from kindle";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Kindle Kindle=new Kindle(rs.getInt("idKindle"),rs.getString("Modele"),rs.getString("Mac"),rs.getBoolean("Emprunte"));
                Kindle.setEmprunte(rs.getBoolean("Emprunte"));
                kindles.add(Kindle);
            }
            return kindles;
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }
    public Kindle getKindleByID(int id){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt =con.createStatement();
            String query = "select * from kindle where idKindle =" + "'" + id + "'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return new Kindle(rs.getInt("idKindle"),rs.getString("Modele"),rs.getString("Mac"),rs.getBoolean("Emprunte"));
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }
    
    //Kindle
     public boolean ajouterKindle(Kindle k){
        boolean addStat=false;
        try{  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            String query=" insert into kindle (idKindle, Modele, Mac)"
        + " values (?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, k.getId());
            preparedStmt.setString (2, k.getModele());
            preparedStmt.setString (3, k.getMac());
            addStat = preparedStmt.execute();
            System.out.println(" "+k.toString()+" added !");
            con.close();
        }catch(SQLException e){
        System.out.println(e);
        }
        return addStat;
    }
    
//    public boolean modifierKindle(Kindle k){
//        return false;
//    }
      public ArrayList<Kindle> getKindleByModel(String Model){
         ArrayList<Kindle> kindles = new ArrayList<>();
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt =con.createStatement();
            String query = "select * from kindle where modele=" + "'" + Model + "'";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Kindle Kindle=new Kindle(rs.getInt("idKindle"),rs.getString("Modele"),rs.getString("Mac"),rs.getBoolean("Emprunte"));
                Kindle.setEmprunte(rs.getBoolean("Emprunte"));
                kindles.add(Kindle);
            }
            return kindles;
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
        }
     public Kindle getKindleByMac(String Mac){
       try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt =con.createStatement();
            String query = "select * from kindle where Mac =" + "'" + Mac + "'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return new Kindle(rs.getInt("idKindle"),rs.getString("Modele"),rs.getString("Mac"),rs.getBoolean("Emprunte"));
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }
    public boolean supprKindle(int idKindle){
    try
    {
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
      String query = "delete from Kindle where idKindle = ?";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt(1, idKindle);
      preparedStmt.execute();
      con.close();
      System.out.println("Kindle with id = "+idKindle+" deleted ");
      return true;
    }
    catch (Exception e)
    {
      System.err.println(e.getMessage());
      return false;
    }
    }
}
