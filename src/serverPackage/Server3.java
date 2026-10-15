package serverPackage;

import java.io.*;
import java.net.*;

public class Server3 {
    static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        // IP réelle de cette machine : en argument, sinon détectée automatiquement
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

        // Réception du choix de l'opération et de la valeur x
        int choix = dis.readInt();
        int x = dis.readInt();
        System.out.println("Le serveur a reçu : choix = " + choix + ", x = " + x);

        // Calcul côté serveur selon l'opération choisie
        double resultat;
        switch (choix) {
            case 1:
                resultat = x + 5;
                System.out.println("Calcul du serveur : " + x + " + 5 = " + resultat);
                break;
            case 2:
                resultat = x - 5;
                System.out.println("Calcul du serveur : " + x + " - 5 = " + resultat);
                break;
            case 3:
                resultat = x * 5;
                System.out.println("Calcul du serveur : " + x + " * 5 = " + resultat);
                break;
            case 4:
                resultat = x / 5.0;
                System.out.println("Calcul du serveur : " + x + " / 5 = " + resultat);
                break;
            default:
                resultat = Double.NaN;
                System.out.println("Choix invalide : " + choix);
        }

        // Envoi du résultat au client
        dos.writeDouble(resultat);
        System.out.println("Le résultat est envoyé au client : " + resultat);

        dis.close();
        dos.close();
        socket.close();
        serveur.close();
    }
}