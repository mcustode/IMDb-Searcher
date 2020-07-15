package IMDbSearcher;

import java.io.*;
import java.net.*;


 
/**
 * Servidor TCP 
 * @author Marcelo Mejía
 */
public class TcpListener {
 
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java TCPListener <porta>");
        	return;
        }
 
        int port = Integer.parseInt(args[0]);
 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("O servidor está escutando na porta " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Novo cliente conectado");
                
                    new TcpThread(socket).start();
                }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}