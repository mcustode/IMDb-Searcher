package imdb.searcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


import org.junit.Test;

public class TcpListenerTest implements Runnable   {

	public void run()  
	{    
		String[] args = new String[1];
		args[0] = "3300";
		TcpListener.main(args);
	}    

	@Test (expected = Test.None.class)
	public void testConnection() throws Exception, IOException {
		
		final String ip = "localhost";

		TcpListenerTest r1=new TcpListenerTest();    
		Thread t1 =new Thread(r1);    
		t1.start();    
		
		try (
		Socket  socket = new Socket(ip, 3300);
		BufferedReader in = new BufferedReader(new InputStreamReader( socket.getInputStream(), "UTF8"));
		OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");) {

		writer.write("x");
		writer.flush();

		in.close();
		writer.close();
		socket.close();
		}
		catch (Exception e) {
			throw e;
		}
	}

}
