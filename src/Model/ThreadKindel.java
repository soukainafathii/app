package Model;
import Controller.Controller;
import Controller.mediatheque;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadKindel implements Runnable{
        Socket soc;
        static boolean repeat=true;

    ThreadKindel(Socket soc) {
        this.soc=soc;
    }
    @Override
    public void run() {
        try { 
            OutputStream flux1;
            flux1 = soc.getOutputStream();
            OutputStreamWriter sortie = new OutputStreamWriter (flux1) ;
            InputStream flux2 = soc.getInputStream();
            BufferedReader entree = new BufferedReader (new InputStreamReader (flux2));
            String login=entree.readLine();
            String passwd=entree.readLine();
            Controller cont=new Controller();
            Client client=cont.authClient(login, passwd);
            ObjectOutputStream obj=new ObjectOutputStream(soc.getOutputStream());
            obj.writeObject(client);
            if(client!= null){
            String Kindle =cont.getKindleClient(client.getIdClient()).toString();
            System.out.println(client.toString() + "a été connecté par le "+Kindle);
            sortie.write(Kindle);
            sortie.flush();
            }else{
                System.out.print("client echoué de ce connecté");
            }
            }catch (IOException ex) {
                System.out.print("\nClient deconnecté ");
            }
            
        }
     } 
    
