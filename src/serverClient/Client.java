package serverClient;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    static final String IP_SERVEUR = "192.168.1.10"; // adresse IP de la machine serveur
    static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        InetAddress adresseIp = InetAddress.getByName(IP_SERVEUR);
        InetSocketAddress adresseServeur = new InetSocketAddress(adresseIp, PORT);

        System.out.println("Je suis un client pas encore connecté…");
        Socket socket = new Socket();
        socket.connect(adresseServeur);   // abandon après 5000 ms sans réponse
        System.out.println("Je suis un client connecté à " + IP_SERVEUR + ":" + PORT);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        System.out.print("x : ");
        int x = sc.nextInt();
        System.out.print("Opération (+ - * /) : ");
        char op = sc.next().charAt(0);
        System.out.print("y : ");
        int y = sc.nextInt();

        dos.writeInt(x);
        dos.writeChar(op);
        dos.writeInt(y);

        System.out.println("Résultat : " + x + " " + op + " " + y + " = " + dis.readUTF());

        socket.close();
        sc.close();
    }
}