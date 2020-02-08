package FumsifolEsp;

import java.awt.Color;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class doorTestServer implements Runnable{
	
	private Socket sock;
	private JTextArea up;
	private String threadName = "good";
	private Thread t;  
	private static String USERNAME = null;
	private static String PASSWORD = null;
    private static String DATABASE_URL = null;
    private static String IP = null;
    private static int PORT = 0;
    private ServerSocket servsock;
    private SocketChannel clientele = null;
	private DataInputStream in;
	private DataOutputStream out;
	private static String which = null;
	private int localport = 8080;
	private int DHCPNo = 100;
	private String input= "";
	
	public doorTestServer() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		try {
			servsock = new ServerSocket(localport);
			while(true){
				System.out.println("Connecting for Basic Info Client...");
				System.out.println("\n\nWaiting to receive queries on >>>"+ InetAddress.getLocalHost().getHostAddress());
				sock = servsock.accept();
				in = new DataInputStream(sock.getInputStream());
				
				while(in.available()>0){
					char x = (char)in.read();
					input+=x;
				}
				System.out.println("\nInteracting with "+sock.getRemoteSocketAddress()+" on "+sock.getLocalPort());
				System.out.println("Value received:"+input);
				input = null;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void start (){      
		if (t == null){        
			t = new Thread (this, threadName);         
			t.start ();      
			}   
		}
	
	public static void main(String[] args) {
		doorTestServer door = new doorTestServer();
		door.start();
	}

}
