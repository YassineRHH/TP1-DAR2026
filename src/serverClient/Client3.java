package serverClient;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client3 {
    static final String IP_SERVEUR = "192.168.1.10"; // adresse IP de la machine serveur
    static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        InetAddress adresseIp = InetAddress.getByName(IP_SERVEUR);
        InetSocketAddress adresseServeur = new InetSocketAddress(adresseIp, PORT);

        System.out.println("Je suis un client pas encore connecté…");
        Socket socket = new Socket();            // socket créée, pas encore connectée
        socket.connect(adresseServeur, 5000);    // abandon après 5000 ms sans réponse
        System.out.println("Je suis un client connecté à " + IP_SERVEUR + ":" + PORT);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        // Menu de choix de l'opération
        System.out.println("===== Calculatrice =====");
        System.out.println("1. Addition");
        System.out.println("2. Soustraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.print("Votre choix : ");
        int choix = sc.nextInt();

        System.out.print("Entrez la valeur de x : ");
        int x = sc.nextInt();

        // Envoi du choix et de x au serveur
        dos.writeInt(choix);
        dos.writeInt(x);

        // Réception et affichage du résultat
        double resultat = dis.readDouble();
        System.out.println("Résultat reçu du serveur : " + resultat);

        dis.close();
        dos.close();
        socket.close();
        sc.close();
    }
}