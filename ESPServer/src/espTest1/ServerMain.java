package espTest1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerMain implements Runnable{
	private DataInputStream in;
	private DataOutputStream out;
	private String input;
	private String received;
	private ServerSocketChannel server = null;
	private Thread t;  
	private String value = "";
	private static ByteBuffer c;
	private SocketChannel client = null;
	public ServerMain() {
		System.out.println("ServerMain about to run");
	}

	public static void main(String[] args) {
		ServerMain main = new ServerMain();
		main.start();
	}

	public void start (){      
		if (t == null){        
			t = new Thread (this);         
			t.start ();      
			}   
		} 
	
	public void run() {
		try { 
			server = ServerSocketChannel.open(); 
			server.socket().bind(new InetSocketAddress(8081)); 
			server.configureBlocking(true); 
			c = ByteBuffer.allocate(1024);

			System.out.println(InetAddress.getLocalHost());
			while (true) {
				client = server.accept(); 
				System.out.println("Waiting for esp to connect on port ..8080");
				if(client != null){
				System.out.println("Connection established with..." + client.getRemoteAddress());
				client.read(c);
				c.flip();
				value = "";
				byte[] array = c.array();
				for(byte x:array){
					char charBuffer = (char)x;		//get the char value by casting the integer to char
					if((int)charBuffer!=0 && (int)charBuffer!=10 && (int)charBuffer!=13){		
						// accumulate the characters into a string buffer
						value+= charBuffer;
					}
				}
				c.clear();
				
				System.out.println("Received Data:"+ value);
				}
				}
				} catch (Exception e) {
					e.printStackTrace();
					}
	}

}
/*This made me know that this client is actually receiving something but cannot decode it
 * for(int x=0; in.read()>c; x++){
					System.out.println(x);
					
					for(byte x:c){
					char charBuffer = (char)x;		//get the char value by casting the integer to char
					if((int)charBuffer!=0 && (int)charBuffer!=10 && (int)charBuffer!=13){		
						// accumulate the characters into a string buffer
						value=value + charBuffer;
					}
				}*/
