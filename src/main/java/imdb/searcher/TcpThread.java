package imdb.searcher;

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
		try (BufferedReader in = new BufferedReader(new InputStreamReader( socket.getInputStream(), "UTF8"));
			 OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(),"UTF-8"); ){
			StringBuffer inputText = new StringBuffer();

			do {
				char character =  (char) in.read();
				int textLenght = 0;

				if (character == ':') {

						//Obtem mensagem do cliente 
					try {
						textLenght = Integer.valueOf(inputText.toString());
						
						StringBuffer queryText = new StringBuffer();

						do {
							queryText.append( (char) in.read());
						} while (queryText.length() < textLenght);

						System.out.println("Mensagem recebida:" + queryText);

						// Pesquisa títulos

						ImdbParserTitles parser = new ImdbParserTitles();
						List<String> titles = parser.getTitles(String.valueOf(queryText));


						//Formata e envia o resultado da busca

						StringBuilder payload = new StringBuilder();

						for (String title : titles ) {
							payload.append(title + "\r\n");
						}

						writer.write(payload.length() + ":" + payload);
						writer.flush();

					} catch(NumberFormatException ex) {
						continue;
					}
					inputText.setLength(0);

				}
				else if (Character.isDigit(character)) {
					//armazena digitos indicadores do tamanho da mensagem
					inputText.append(character);
					
				}
				else if (character == 'x') {
					//finaliza o loop
					break;
				}
				else {
					//Reinicia o buffer do texto recebido se encontrar caracteres não numericos durante a obtenção do tamanho da mensagem 
					inputText.setLength(0);
				}

			} while (true) ;

			System.out.println("Desconectando cliente");
			socket.close();
		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}


}