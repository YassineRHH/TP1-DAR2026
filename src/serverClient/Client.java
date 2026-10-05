package serverClient;
import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args)
            throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Je suis connecté");
    }}
