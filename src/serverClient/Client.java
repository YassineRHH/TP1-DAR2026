package serverClient;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args)
            throws IOException {
        System.out.println("Je suis un client pas encore connecté");
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Je suis connecté");

        Scanner scanner=new Scanner(System.in);
        System.out.println("Entrer un entier x :");
        int x=scanner.nextInt();

        OutputStream os=socket.getOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.writeInt(x);
        System.out.println("Nombre envoyé au serveur "+x);

        InputStream is=socket.getInputStream();
        DataInputStream dis=new DataInputStream(is);
        int xx=dis.readInt();
        System.out.println("Result recu du serveur "+ xx);

        dis.close();
        dos.close();
        scanner.close();




        socket.close();

    }}
