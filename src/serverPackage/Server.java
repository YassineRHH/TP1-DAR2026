package serverPackage;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args)throws IOException {
        ServerSocket socketServeur = new ServerSocket(1234);
        System.out.println("Je suis un serveur en attente la connexion d'un client");
        Socket socket = socketServeur.accept();
        System.out.println("un client est connecté");

    }
}
