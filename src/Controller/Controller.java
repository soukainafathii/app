package Controller;

import Model.Client;
import Model.Emprunt;
import Model.Kindle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Controller {

    public Controller() {
    }
    
   public Client authClient(String login,String passwd){
        try{
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
        System.out.println("\n un kindle client essay de ce connecté ");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from client where Login =" + "'" + login + "' and password =" + "'" + passwd + "'");
        rs.next();
        int idClient=rs.getInt("idClient");
        String nom=rs.getString("nom");
        String Prenom=rs.getString("Prenom");
        int age =rs.getInt("Age");
        String TypeClient=rs.getString("TypeClient");
        if("etudiant".equals(TypeClient)){
        String CNE=rs.getString("CNE");
        return new Client(idClient,nom,Prenom,TypeClient,age,CNE);
        }else{
        String CIN=rs.getString("CIN");
        return new Client(idClient,nom,Prenom,TypeClient,age,CIN);
        }
        }catch(SQLException e){
            return null;
        }
    }
   public Kindle getKindleClient(int IdClient){
       try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt =con.createStatement();
            String query = "select IdKindle from emprunt where IdClient =" + "'" + IdClient + "'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            int kindleId = rs.getInt("IdKindle");
            String query2 = "select * from kindle where IdKindle =" + "'" + kindleId + "'";
            ResultSet rs2 = stmt.executeQuery(query2);
            rs2.next();
            return new Kindle(rs2.getInt("idKindle"),rs2.getString("Mac"),rs2.getString("Modele"),rs2.getBoolean("Emprunte"));
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
       
   }
   public static void main(String[] args){
       Controller cn=new Controller();
       System.out.println(cn.getKindleClient(2).toString());
   }
}
