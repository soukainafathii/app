package Model;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;

public class Server {
        static float durée;
        public static HashMap<String,Integer > coordonnées = new HashMap<String,Integer>(){{
                            put("x1",50);
                            put("x2",250);
                            put("y1",20);
                            put("y2",130);
        }};
        public static void main(String[] args) throws IOException {
         int port = 1000;
        ServerSocket sersoc = new ServerSocket (port) ; 
        System.out.println ("Serveur du mediathéque " ) ;
        while(true){ 
            Socket soc = sersoc.accept();
            Thread t= new Thread( new ThreadKindel(soc));
            t.start();
        }
        
    }
}
