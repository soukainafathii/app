/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;


/* client comment */

/*hello world */



import Model.Client;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CRUD_Client {
   
    public ArrayList<Client> getClients(){
        return null;
    }
    public Client getClientByid(int id){
        try{
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
        System.out.println("connected");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from client where idClient =" + "'" + id + "'");
        rs.next();
        int idClient=rs.getInt("idClient");
        String nom=rs.getString("nom");
        String Prenom=rs.getString("Prenom");
        int age =rs.getInt("Age");
        String TypeClient=rs.getString("TypeClient");
        String CIN=rs.getString("CIN");
        String CNE=rs.getString("CNE");
        switch(TypeClient) {
            case "Etudiant":
              return new Client(idClient,nom,Prenom,age,CNE);
            case "professeur":
              return new Client(idClient,nom,Prenom,age,CIN);
            default:
              System.err.println("TypeClient ERROR");
          }
             return new Client(idClient,nom,Prenom,age);
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }
    public ArrayList<Client> getClientByName(String Nom){   
        try {
            ArrayList<Client> ClientList = new ArrayList<>();
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            System.out.println("connected");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select idClient,nom,prenom,age from client where nom =" + "'" + Nom + "'");
            while(rs.next()){
            Client client=new Client(rs.getInt("idClient"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("age"));
            ClientList.add(client);
            }
            return ClientList;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Client.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ArrayList<Client> getAllClient(){
        ArrayList<Client> ClientList = new ArrayList<>();
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt =con.createStatement();
            String query = "select * from client";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                Client client=new Client(rs.getInt("idClient"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("age"));
                ClientList.add(client);
            }
            return ClientList;
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }
     //Client
    public Boolean ajouterClient(Client c){
        boolean addStat=false;
        try{  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            System.out.println("connected");
            String query=" insert into client (idClient, nom, Prenom, Age)"
        + " values (?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, c.getIdClient());
            preparedStmt.setString (2, c.getNom());
            preparedStmt.setString (3, c.getPrenom());
            preparedStmt.setInt(4, c.getAge());
            addStat = preparedStmt.execute();
            System.out.println("client added !");
            con.close();
            return addStat;
        }catch(SQLException e){
        System.out.println(e);
        return addStat;
        }
        
    }
    
   /* public Boolean modifierClient(Client c){
        return false;
    }*/
    
    public Boolean supprClient(int idClient){
       try
    {
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
      String query = "delete from client where 	idClient = ?";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt(1, idClient);
      preparedStmt.execute();      
      con.close();
      System.out.println("Client with id = "+idClient+" deleted ");
      return true;
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
      return false;
    }  
    }

}
