package Controller;


import DB.*;
import Model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class mediatheque {
    CRUD_Client cd;
    CRUD_Document dd;
    CRUD_Kindle dk;
    CRUD_Emprunt ce;
    public mediatheque(){
        cd=new CRUD_Client();
        dd=new CRUD_Document();
        dk=new CRUD_Kindle();
        ce=new CRUD_Emprunt();
    }
    //GETTERS DE CLIENT
    public String getClientById(){
        Scanner sc=new Scanner(System.in);
        System.out.println("afficher Client par id ");
        System.out.println("Id Client : ");
        int id=sc.nextInt();
        Client c=cd.getClientByid(id);
        String ClientString=c.toString();
        return ClientString;
    }
    public String getClientByName(){
        Scanner sc=new Scanner(System.in);
        System.out.println("afficher Client par nom ");
        System.out.println("nom Client : ");
        String nom=sc.nextLine();
        ArrayList<Client> Client = (ArrayList<Client>)(cd.getClientByName(nom)).clone();
        String results="";
        for (Client Client1 : Client) {
            results += Client1.toString() + "\n";
        }
        if(results==""){
            System.out.println("Aucun resultat");
        }
        return results;
    }
    
    public String getAllClients(){
        System.out.println("---- tout les Client ----");
        ArrayList<Client> Client = (ArrayList<Client>)(cd.getAllClient()).clone();
        String results="";
        for (Client Client1 : Client) {
            results += Client1.toString() + "\n";
        }
        if(results==""){
            System.out.println("Aucun resultat");
        }
        return results;
    }
    //GETTERS DE DOCUMENT
    public String getDocumentByISBN(){
        System.out.println("---- get document by ISBN ----");
        Scanner sc=new Scanner(System.in);
        System.out.println("document ISBN : ");
        String ISBN=sc.nextLine();
        return dd.getDocumentByISBN(ISBN).toString();
    }
    public String getDocumentByTitle(){
        System.out.println("---- get document by title ----");
        Scanner sc=new Scanner(System.in);
        System.out.println("document title : ");
        String title=sc.nextLine();
        return dd.getDocumentByTitle(title).toString();
    }
    public String getDocumentById(){
        System.out.println("---- get document by id ----");
        Scanner sc=new Scanner(System.in);
        System.out.println("document id : ");
        int id=sc.nextInt();
        return dd.getDocumentByIid(id).toString();
    }
    public String getDocumentByEditor(){
        System.out.println("---- get document by Editeur ----");
        Scanner sc=new Scanner(System.in);
        System.out.println("document Editeur : ");
        String Editeur=sc.nextLine();
        return dd.getDocumentByEditor(Editeur).toString();
    }
  
    public String getDocumentByAnneEdition(){
        System.out.println("---- get document by Anne d'Edition ----");
        Scanner sc=new Scanner(System.in);
        System.out.println("document Anne d'Edition : ");
        int AnneEdition=sc.nextInt();
        return dd.getDocumentByAnneEdition(AnneEdition).toString();
    }
    public String getAllDocuments(){
        System.out.println("---- tout les Documents ----");
        ArrayList<Document> Document = (ArrayList<Document>)(dd.getAllDocuments()).clone();
        String results="";
        for (Document doc : Document) {
            results += doc.toString() + "\n";
        }
        if(results==""){
            System.out.println("Aucun resultat");
        }
        return results;
    }
    //GETTERS DE KINDLE
    public String getKindleById(){
        System.out.println("---- All Kindles By ID ----");
        Scanner sc =new Scanner(System.in);
        System.out.println("enter id Kindle : ");
        int id=sc.nextInt();
        return dk.getKindleByID(id).toString();
    }
    public String getKindleByModel(){
        System.out.println("---- All Kindles By Model ----");
        Scanner sc=new Scanner(System.in);
        System.out.println("enter Modele Kindle :");
        String model=sc.nextLine();
        ArrayList<Kindle> kind = (ArrayList<Kindle>)(dk.getKindleByModel(model)).clone();
        String results="";
        for (Kindle kd : kind) {
            results += kd.toString() + "\n";
        }
        if(results==""){
            System.out.println("Aucun resultat");
        }
        return results;
    }
    
    public String getKindleByMac(){
        System.out.println("---- All Kindles By Mac ----");
        Scanner sc =new Scanner(System.in);
        System.out.println("enter Mac Kindle : ");
        String Mac=sc.nextLine();
        return dk.getKindleByMac(Mac).toString();
    }
    
    public String getAllKindles(){
        System.out.println("---- All Kindles ----");
        ArrayList<Kindle> Kindle = (ArrayList<Kindle>)(dk.getAllKindles()).clone();
        String results="";
        for (Kindle kind : Kindle) {
            results += kind.toString() + "\n";
        }
        if(results==""){
            System.out.println("Aucun resultat");
        }
        return results;
    } 
    //Client
    public Boolean ajouterClient(){
        Scanner sc =new Scanner(System.in);
            System.out.println("saisir nom :");
            String nom=sc.nextLine();
            System.out.println("saisir prenom :");
            String prenom=sc.nextLine();
            System.out.println("saisir age :");
            int age=sc.nextInt();
            Client c=new Client(nom,prenom,age);
            System.out.println("le Client a éte ajouter");
            return cd.ajouterClient(c);
    }
    
   
    public Boolean supprClient(){
        Scanner sc =new Scanner(System.in);
        System.out.println("saisir IdClient :");
        int idClient=sc.nextInt();
        System.out.println("le Client a éte supprimer");
        return cd.supprClient(idClient);  
    }
    //Document
    public Boolean ajouterDocument(){
	  String ISBN;
	  String titre,editeur;
          String url;
	  ArrayList<String> auteurs = new ArrayList<>();
          int anneeEdition;
          String rpns="y";
        Scanner sc=new Scanner(System.in);
        System.out.println("---- ajouter Document ----");
        System.out.println("ISBN :");
            ISBN=sc.nextLine();
        System.out.println("titre :");
            titre=sc.nextLine();
        System.out.println("editeur :");
            editeur=sc.nextLine();
        System.out.println("url :");
            url=sc.nextLine();
        System.out.println("annee Edition :");
        anneeEdition = sc.nextInt();
       sc.nextLine();
        while("y".equals(rpns)){
             System.out.println("auteur : ");
             auteurs.add(sc.nextLine());
             System.out.println("voulez-vous ajouter un autre auteur ? [y/n]");
             rpns=sc.nextLine();
        }
        Document d=new Document(ISBN,titre,auteurs,editeur,anneeEdition,url);
        System.out.println("le Document a éte ajouter");
        return dd.ajouterDocument(d);
    }
    public Boolean supprDocument(){
        Scanner sc=new Scanner(System.in);
        System.out.println("saisir idDocument :");
        int idDocument=sc.nextInt();
        System.out.println("le Document a éte supprimer");
        return dd.supprDocument(idDocument);
    }
    //Kindle
     public Boolean ajouterKindle(){
         Scanner sc=new Scanner(System.in);
        System.out.println("---- ajouter Kindle ----");
        System.out.println("entre modele Kindle : ");
        String modele = sc.nextLine();
        System.out.println("entre Mac address de Kindle : ");
        String MAC = sc.nextLine();
        System.out.println("le kindle Bien ajouter");
        return dk.ajouterKindle(new Kindle(MAC,modele));
    }
    
    public Boolean supprKindle(){
        Scanner sc=new Scanner(System.in);
        System.out.println("saisir idKindle :");
        int idKindle=sc.nextInt();
        System.out.println("le kindle a éte supprimer");
        return dk.supprKindle(idKindle);
    }
    
    //Gestion des emprunts
    
    public Boolean ajouterEmprunt() throws ParseException{
            int ClientID,KindleID;
            String date;
            Scanner sc=new Scanner(System.in);
            System.out.println("---- ajouter Emprunt ----");
            System.out.println("enter id Client ");
            ClientID =sc.nextInt();
            System.out.println("enter id Kindle ");
            KindleID =sc.nextInt();
            System.out.println("enter date ");
            date =sc.next();
            Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            System.out.println("l'emprunt a éte ajouter");
            return ce.ajouterEmprunt(ClientID, KindleID, utilDate);
    }
    
    public Boolean rendreEmprunt(){
        System.out.println("---- Rendre emprunt ----");
        Scanner sc=new Scanner(System.in);
        System.out.println("id de votre emprunt :");
        int id=sc.nextInt();
        System.out.println("l'emprunt a éte rendu");
        return ce.rendreEmprunt(id);
    }
    
    public String KindlesNonRendu(){
        System.out.println("---- kindles Non Rendu ----");
        ArrayList<Kindle> Kindle = (ArrayList<Kindle>)(dk.KindlesNonRendu()).clone();
        String results="";
        for (Kindle kind : Kindle) {
            results +="\n"+ kind.toString()  ;
        }
        if(results==""){
            System.out.println("Aucun resultat");
        }
        return results;
    }
    
    public String getEmpruntEnCour(){
        System.out.println("---- Emprunt kindle En cours ----");
         ArrayList<Emprunt> Emprunt = (ArrayList<Emprunt>)(ce.getEmpruntEnCour()).clone();
        String results="";
        for (Emprunt emp : Emprunt) {
            results += emp.toString() + "\n";
        }
        if(results==""){
            System.out.println("Aucun resultat");
        }
        return results;
    }
    
    public Boolean SupprEmprunt(){
        System.out.println("---- Delete Emprunt ----");
        System.out.println("---- enter id Emprunt ----");
        Scanner sc=new Scanner(System.in);
        int id=sc.nextInt();
        System.out.println("l'emprunt a éte supprimer");
        return ce.SupprEmprunt(id);
    }
    public void menuPrincipale(){
     mediatheque med=new mediatheque();
        System.out.println("Menu Gerant");
        System.out.println("1-Gestion des clients");
        System.out.println("2-Gestion des kindles");
        System.out.println("3-Gestion des documents");
        System.out.println("votre choix (1, 2, 3) ? : ");
        Scanner sc=new Scanner(System.in);
        String choix=sc.nextLine();
        switch(choix) {
        case "1":
          System.out.println("---- GESTION DES CLIENTS ----");
          System.out.println("1-Liste des client");
          System.out.println("2-Ajouter Client");
          System.out.println("3-Supprimer Client ");
          System.out.println("4-Recherche ");
          System.out.println("5-Menu Principale");
          System.out.println("votre choix (1, 2, 3, 4, 5) ? : ");
          String choixCleint =sc.nextLine();
                switch(choixCleint) {
                        case "1":
                          System.out.println(med.getAllClients());
                          med.menuPrincipale();
                          break;
                        case "2":
                          med.ajouterClient();
                          med.menuPrincipale();
                          break;
                        case "3":
                          med.supprClient();
                          med.menuPrincipale();
                          break;
                         case "4":
                          System.out.println("---- Recherche par ----");
                          System.out.println("1-Id ");
                          System.out.println("2-Nom ");
                          System.out.println("3-Menu Principale ");
                          System.out.println("votre choix (1, 2, 3) ? : ");
                            String recherchClient=sc.nextLine();
                                if(recherchClient.equals("1")){
                                    System.out.println(med.getClientById());
                                    med.menuPrincipale();
                                    break;
                                }else if(recherchClient.equals("2")){
                                    System.out.println(med.getClientByName());
                                    med.menuPrincipale();
                                    break;
                                }
                                else if(recherchClient.equals("3")){
                                    med.menuPrincipale();
                                }
                        case "5":
                          med.menuPrincipale();
                        default:
                          System.out.println("erorr");
                      }
          break;
        case "2":
          System.out.println("---- GESTION DES KINDLES ----");
          System.out.println("1-Liste des Kindles");
          System.out.println("2-Ajouter Kidnles");
          System.out.println("3-Supprimer Kindle ");
          System.out.println("4-List Kindles Non rendu");
          System.out.println("5-Recherche ");
          System.out.println("6-Menu Principale ");
          System.out.println("votre choix (1, 2, 3, 4, 5, 6) ? : ");
          String choixKindles=sc.nextLine();
                switch(choixKindles) {
                    case "1":
                      System.out.println(med.getAllKindles());
                      med.menuPrincipale();
                      break;
                    case "2":
                      med.ajouterKindle();
                      med.menuPrincipale();
                      break;
                    case "3":
                      med.supprKindle();
                      med.menuPrincipale();
                      break;
                    case "4":
                      System.out.println(med.KindlesNonRendu());
                      med.menuPrincipale();
                      break;
                    case "5":
                      System.out.println("---- Recherche par ----");
                          System.out.println("1-Id ");
                          System.out.println("2-Modle ");
                          System.out.println("3-Mac Adress ");
                          System.out.println("4-Menu Principale ");
                          System.out.println("votre choix (1, 2, 3, ') ? : ");
                          String recherKindle=sc.nextLine();
                          if(recherKindle.equals("1")){
                              System.out.println(med.getKindleById());
                              med.menuPrincipale();
                              break;
                          }else if(recherKindle.equals("2")){
                              System.out.println(med.getKindleByModel());
                              med.menuPrincipale();
                              break;
                          }else if(recherKindle.equals("3")){
                              System.out.println(med.getKindleByMac());
                              med.menuPrincipale();
                              break;
                          }else if(recherKindle.equals("4")){
                              med.menuPrincipale();
                          }
                    case "6":
                        med.menuPrincipale();
                    default:
                      System.out.println("erorr");
                        med.menuPrincipale();
                  }
          break;
        case "3":
          System.out.println("---- GESTION DES DOCUMENTS ----");
          System.out.println("1-Liste des Documents");
          System.out.println("2-Ajouter Document");
          System.out.println("3-Supprimer Documents ");
          System.out.println("4-Recherche ");
          System.out.println("5-Menu Principale ");
          System.out.println("votre choix (1, 2, 3, 4, 5) ? : ");
          String choixDoc=sc.nextLine();
                switch(choixDoc) {
                case "1":
                  System.out.println(med.getAllDocuments());
                  med.menuPrincipale();
                  break;
                case "2":
                  med.ajouterDocument();
                  med.menuPrincipale();
                  break;
                case "3":
                  med.supprClient();
                  med.menuPrincipale();
                  break;
                case "4":
                  System.out.println("---- Recherche par ----");
                          System.out.println("1-Id ");
                          System.out.println("2-Editor ");
                          System.out.println("3-Anne d'Edition ");
                          System.out.println("4-ISBN ");
                          System.out.println("5-Titre");
                          System.out.println("6-Menu Principale ");
                          System.out.println("votre choix (1, 2, 3, 4, 5, 6) ? : ");
                          String recherchDoc=sc.nextLine();
                          switch(recherchDoc) {
                                case "1":
                                  System.out.println(med.getDocumentById());
                                  med.menuPrincipale();
                                  break;
                                case "2":
                                  System.out.println(med.getDocumentByEditor());
                                  med.menuPrincipale();
                                  break;
                                case "3":
                                  System.out.println(med.getDocumentByAnneEdition());
                                  med.menuPrincipale();
                                  break;
                                case "4":
                                  System.out.println(med.getDocumentByISBN());
                                  med.menuPrincipale();
                                  break;
                                case "5":
                                  System.out.println(med.getDocumentByTitle());
                                  med.menuPrincipale();
                                  break;
                                case "6":
                                  med.menuPrincipale();
                                default:
                                  System.out.println("error");
                                    med.menuPrincipale();
                              }
                case "5":
                    med.menuPrincipale();
                default:
                  System.out.println("error");
                    med.menuPrincipale();
              }
          break;
        default:
          System.out.println("error");
            med.menuPrincipale();
      }
    }
    
    //////////////MAIN////////////////////
    public static void main(String []args) throws SQLException, ParseException {
        //afficher le menu au gerant
        //1)Gestion des clients
        //2)Gestion des kindles
        //3)Gestion des documents
       mediatheque med=new mediatheque();
       med.menuPrincipale();
        }    
}