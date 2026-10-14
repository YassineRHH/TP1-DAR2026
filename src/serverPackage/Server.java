package serverPackage;

import java.io.*;
import java.net.*;

public class Server {
    static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        // IP de cette machine : en argument, sinon détectée automatiquement
        String ip = (args.length > 0) ? args[0] : InetAddress.getLocalHost().getHostAddress();

        InetAddress adresseIp = InetAddress.getByName(ip);
        InetSocketAddress adresseServeur = new InetSocketAddress(adresseIp, PORT);

        ServerSocket serveur = new ServerSocket();
        serveur.bind(adresseServeur);
        System.out.println("Je suis un serveur en attente d'un client sur " + ip + ":" + PORT + " ...");

        Socket socket = serveur.accept();
        System.out.println("Un client est connecté : " + socket.getInetAddress().getHostAddress());

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        int x = dis.readInt();
        char op = dis.readChar();
        int y = dis.readInt();
        System.out.println("Reçu : " + x + " " + op + " " + y);

        String resultat;
        if (op == '+') resultat = "" + (x + y);
        else if (op == '-') resultat = "" + (x - y);
        else if (op == '*') resultat = "" + (x * y);
        else if (op == '/' && y != 0) resultat = "" + ((double) x / y);
        else if (op == '/') resultat = "Erreur : division par zéro";
        else resultat = "Erreur : opération invalide";

        dos.writeUTF(resultat);
        System.out.println("Résultat envoyé : " + resultat);

        socket.close();
        serveur.close();
    }
}