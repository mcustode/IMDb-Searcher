package IMDbSearcher;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controla o banco de dados
 * @author Marcelo Mejia
 */
public class SQLiteDB {

    /**
     * Conecta com o Banco de dados
     * @return Connection
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:db/imdb.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    
    /**
     * Pesquisa na tabela de títulos
     * @return Lista de títulos
     */
    public List<String> searchTitles(String textToSearch){
    	List<String> titles = new ArrayList<String>();
    	
        String sql = "SELECT Id, Title FROM Title where Title like ?";
        
        try (Connection conn = this.connect();
        	
        	PreparedStatement pstmt = conn.prepareStatement(sql)){ 
        	pstmt.setString(1, "%" + textToSearch + "%");
        		
            ResultSet rs    = pstmt.executeQuery();
            
            while (rs.next()) {
            	titles.add( rs.getString("Title"));
            }
            
            return  titles;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
       
    public String processQuery(String query) {
    	
        if ( query.length() < 3 ) {
        	return ("ERRO:Escreva pelo menos 3 caracteres");
        }

        List<String> titles = searchTitles(query);

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