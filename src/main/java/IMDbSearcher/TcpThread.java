package IMDbSearcher;

import java.io.*;
import java.net.*;
import java.util.List;
 
/**
 * Controla a conex�o do cliente
 * @author Marcelo Mejia
 */
public class TcpThread extends Thread {
    private Socket socket;
 
    public TcpThread(Socket socket) {
        this.socket = socket;
    }
 
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
           // SQLiteDB db = new SQLiteDB();
             writer.println("Digite o texto a pesquisar ou digite \"sair\" e pressione Enter ");
 
            String text;
 
            do {
                text = reader.readLine();
                
                String payload = processQuery(text);
                
            	writer.println(payload.length() + ":" + payload);
 
            } while (!text.equals("sair"));
 
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public String processQuery(String query) {
    	
        if ( query.length() < 3 ) {
        	return ("ERRO:Escreva pelo menos 3 caracteres");
        }

        SQLiteDB db = new SQLiteDB();
        List<String> titles = db.searchTitles(query);

        if ( titles.size() <= 0 ) {
        	return("ERRO:Nao foi encontrado nenhum titulo");
        }                
        
        StringBuilder sbTitles = new StringBuilder("");
        
        for (String temp : titles) {
        	sbTitles.append(temp + "\n");
        }
        
        return sbTitles.toString();
    }
    
}