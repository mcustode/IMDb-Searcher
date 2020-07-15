package IMDbSearcher;

import java.io.*;
import java.net.*;
import java.util.List;
 
/**
 * Controla a conexão do cliente
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
         //    writer.println("Digite o texto a pesquisar ou digite \"sair\" e pressione Enter ");
 
            String tcpQuery;
 
            do {
            	tcpQuery = reader.readLine();
                
                String[] queryData = tcpQuery.split(":");
                
                if (queryData.length != 2) {
                	writer.println("48:ERRO|Consulta incorreta, enviar Tamanho:Consulta" );
                	continue;	
                }

                Integer querySize = Integer.valueOf(queryData[0]);
                
                if (! querySize.equals(queryData[1].length())) {
                	writer.println("48:ERRO|Tamanho incorreto, enviar Tamanho:Consulta" );
                	continue;	
                }

                
                String payload = processQuery(queryData[1]);
                
            	writer.println(payload.length() + ":" + payload);
 
            } while (!tcpQuery.equals("sair"));
 
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