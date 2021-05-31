
package DB;
import Model.Client;
import Model.Document;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CRUD_Document {
    
    public Document getDocumentByIid(int id){
        try{
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from document where idDocument =" + "'" + id + "'");
        rs.next();
        int idDocument=rs.getInt("idDocument");
        String ISBN=rs.getString("ISBN");
        String titre=rs.getString("titre");
        String url=rs.getString("url");
        String editeur=rs.getString("editeur");
        int anneeEdition=rs.getInt("anneeEdition");
        ArrayList<String> auteurs = new ArrayList<>();
        Statement stmt2 = con.createStatement();
        ResultSet rs2 = stmt2.executeQuery("select nom from auteur where idDoc=" + "'" + id + "'");
        while(rs2.next()){
            auteurs.add(rs2.getString("nom"));
        }
             return new Document(idDocument,ISBN,titre,auteurs,editeur,anneeEdition,url);
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }
    //Document
    public boolean ajouterDocument(Document d){
        boolean addStat=false;
        try{  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            String query=" insert into document (idDocument, ISBN, titre, url, anneeEdition, editeur)"
        + " values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, d.getId());
            preparedStmt.setString (2, d.getISBN());
            preparedStmt.setString (3, d.getTitre());
            preparedStmt.setString(4, d.getUrl());
            preparedStmt.setInt(5, d.getAnneeEdition());
            preparedStmt.setString(6, d.getEditeur());
            addStat = preparedStmt.execute();
            System.out.println("test1");
            String query1 = "select idDocument from document ORDER BY idDocument DESC LIMIT 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            rs.next();
            int idDoc=rs.getInt("idDocument");
            ArrayList<String> auteurs = (ArrayList<String>)(d.getAuteurs()).clone(); 
            String query2="INSERT into auteur(idAuteur,idDoc,Nom) VALUES(?,?,?)";
            PreparedStatement preparedStmt1 = con.prepareStatement(query2);
            for (String auteur : auteurs) {
                preparedStmt1.setInt(1, d.getId());
                preparedStmt1.setInt(2, idDoc);
                preparedStmt1.setString(3, auteur);
                preparedStmt1.execute();
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return addStat;
    }
     public Document getDocumentByEditor(String Editeur){
        try {
            ArrayList<String> auteurList=new ArrayList<>();
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select idDocument,ISBN,titre,url,anneeEdition,editeur from Document where ISBN =" + "'" + Editeur + "'");
            rs.next();
            int Docid=rs.getInt("idDocument");
            String ISBn=rs.getString("ISBN");
            String title=rs.getString("titre");
            String url=rs.getString("url");
            String editeur=rs.getString("editeur");
            int anneeEdition=rs.getInt("anneeEdition");
            Statement stmt2 =con.createStatement();
            String query = "select * from auteur where idDoc=" + "'" + Docid + "'";
            ResultSet rs2 = stmt2.executeQuery(query);
            while(rs2.next()){
                auteurList.add(rs2.getString("nom"));
            }
            Document Document=new Document(Docid,ISBn,title,auteurList,editeur,anneeEdition,url);
            return Document;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Client.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        public Document getDocumentByAnneEdition(int AnneEdition){
        try {
            ArrayList<String> auteurList=new ArrayList<>();
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select idDocument,ISBN,titre,url,anneeEdition,editeur from Document where ISBN =" + "'" + AnneEdition + "'");
            rs.next();
            int Docid=rs.getInt("idDocument");
            String ISBn=rs.getString("ISBN");
            String title=rs.getString("titre");
            String url=rs.getString("url");
            String editeur=rs.getString("editeur");
            int anneeEdition=rs.getInt("anneeEdition");
            Statement stmt2 =con.createStatement();
            String query = "select * from auteur where idDoc=" + "'" + Docid + "'";
            ResultSet rs2 = stmt2.executeQuery(query);
            while(rs2.next()){
                auteurList.add(rs2.getString("nom"));
            }
            Document Document=new Document(Docid,ISBn,title,auteurList,editeur,anneeEdition,url);
            return Document;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Client.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
    public Document getDocumentByISBN(String ISBN){
       try {
            ArrayList<String> auteurList=new ArrayList<>();
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select idDocument,ISBN,titre,url,anneeEdition,editeur from Document where ISBN =" + "'" + ISBN + "'");
            rs.next();
            int Docid=rs.getInt("idDocument");
            String ISBn=rs.getString("ISBN");
            String title=rs.getString("titre");
            String url=rs.getString("url");
            String editeur=rs.getString("editeur");
            int anneeEdition=rs.getInt("anneeEdition");
            Statement stmt2 =con.createStatement();
            String query = "select * from auteur where idDoc=" + "'" + Docid + "'";
            ResultSet rs2 = stmt2.executeQuery(query);
            while(rs2.next()){
                auteurList.add(rs2.getString("nom"));
            }
            Document Document=new Document(Docid,ISBn,title,auteurList,editeur,anneeEdition,url);
            return Document;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Client.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ArrayList<Document> getAllDocuments(){
        ArrayList<Document> Document = new ArrayList<>();
        ArrayList<Integer> idDocuments =new ArrayList<>();
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt =con.createStatement();
            String query = "select * from document";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                idDocuments.add(rs.getInt("idDocument"));
            }
            for(Integer doc : idDocuments){
                Document.add(this.getDocumentByIid(doc));
            }
            return Document;
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }
    public boolean modifierDocument(Document d){
        return false;
    }
    public Document getDocumentByTitle(String titre){
        try {
            ArrayList<String> auteurList=new ArrayList<>();
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select idDocument,ISBN,titre,url,anneeEdition,editeur from Document where titre =" + "'" + titre + "'");
            rs.next();
            int Docid=rs.getInt("idDocument");
            String ISBN=rs.getString("ISBN");
            String title=rs.getString("titre");
            String url=rs.getString("url");
            String editeur=rs.getString("editeur");
            int anneeEdition=rs.getInt("anneeEdition");
            Statement stmt2 =con.createStatement();
            String query = "select * from auteur where idDoc=" + "'" + Docid + "'";
            ResultSet rs2 = stmt2.executeQuery(query);
            while(rs2.next()){
                auteurList.add(rs2.getString("nom"));
            }
            Document Document=new Document(Docid,ISBN,title,auteurList,editeur,anneeEdition,url);
            return Document;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Client.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public boolean supprDocument(int idDocument){
    try
    {
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
      String query = "delete from Document where idDocument = ?";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt(1, idDocument);
      preparedStmt.execute();
      con.close();
      System.out.println("Document with id = "+idDocument+" deleted ");
      return true;
    }
    catch (Exception e)
    {
      System.err.println(e.getMessage());
      return false;
    }
    }

}
