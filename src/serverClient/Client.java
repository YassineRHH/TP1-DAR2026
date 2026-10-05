package serverClient;
import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args)
            throws IOException {
        System.out.println("Je suis un client pas encore connecté");
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Je suis connecté");
        socket.close();

    }}
