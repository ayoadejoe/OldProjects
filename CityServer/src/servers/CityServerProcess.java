package servers;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class CityServerProcess implements Runnable{
	private static String USERNAME = null;
	private static String PASSWORD = null;
    private static String DATABASE_URL = null;
    private static String IP = null;
    private static int PORT = 0;
    private ServerSocket servsock;
    private SocketChannel clientele = null;
	private Socket sock;
	private JTextArea up;
	private String threadName, received;
	private Thread t;  
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private ArrayList input = new ArrayList();
	private DataPlacer order = new DataPlacer();
	private String client = "";
	public CityServerProcess(String ip, int port, String username, String password, JTextArea updates, String name) {
		IP = ip;
		PORT= port;
		USERNAME = username;
		PASSWORD = password;
		up = updates;
		threadName = name;
		order.Data(IP,PORT,USERNAME,PASSWORD,up);
	}

	public void run() {
		try {
			servsock = new ServerSocket(21000);
			while(true){
				System.out.println("Connecting for Basic Info Client...");
				up.setFont(new Font("georgia", Font.PLAIN, 13));
				up.append("\n\nWaiting to receive queries>>>");
				sock = servsock.accept();
				in = new ObjectInputStream(sock.getInputStream());
				input = (ArrayList) in.readObject();
				received = input.get(1).toString();
				client = input.get(0).toString();
				up.append("\nInteracting with "+client+" on "+sock.getRemoteSocketAddress());
				up.append("\n"+client+" request for "+input.get(2));
				up.setBackground(Color.black);
				up.setForeground(Color.green);
				switch(received){
				case "^checkregister^":
					input = order.FragmentRegister(input);
					System.out.println("gotten back:"+input);
					out = new ObjectOutputStream(sock.getOutputStream());
					out.writeObject(input);
					System.out.println("Sent query result back: "+input);
					input.clear();
					break;
				case "%registernew%":
					input = order.NewRegistration(input);
					System.out.println("gotten back:"+input);
					out = new ObjectOutputStream(sock.getOutputStream());
					out.writeObject(input);
					System.out.println("Sent query result back: "+input);
					input.clear();
					break;
				case "&getcityStaff&":
					break;
				case "&ALL&":
					input = order.getAll(input);
					System.out.println("gotten back:"+input);
					out = new ObjectOutputStream(sock.getOutputStream());
					out.writeObject(input);
					System.out.println("Sent query result back: "+input);
					input.clear();
					break;
				case "$getmemos$":
					break;
				case "*editmemo*":
					break;
				case "(newmemo)":
					break;
				case ".getmynotes.":
					break;
				case "{editnotes}":
					break;
				case "<engineerlogs>":
					break;
				case "<editenglogs>":
					break;
				case "^getstandards$":		//it could get part(column) of the standard,or all 
					input = order.getStandards(input);
					System.out.println("gotten back:"+input);
					out = new ObjectOutputStream(sock.getOutputStream());
					out.writeObject(input);
					System.out.println("Sent query result back: "+input);
					input.clear();
					break;
				case "^updatestandards":
					input = order.updateStandards(input);
					System.out.println("gotten back:"+input);
					out = new ObjectOutputStream(sock.getOutputStream());
					out.writeObject(input);
					System.out.println("Updating standards: "+input);
					input.clear();
					break;
				case "^checkstandards":
					input = order.checkStandards(input);
					System.out.println("gotten back:"+input);
					out = new ObjectOutputStream(sock.getOutputStream());
					out.writeObject(input);
					System.out.println("Sent query result back: "+input);
					input.clear();
					break;
				case "^enterdept&":			//enter dept works with standard table
					input = order.enterDept(input);
					System.out.println("gotten back:"+input);
					out = new ObjectOutputStream(sock.getOutputStream());
					out.writeObject(input);
					System.out.println("Sent query result back: "+input);
					input.clear();
					break;
				case "*editappraisal!":
					break;
				case "^getappraisals!":		//will specify which appraisal
					break;
				default:
					input.clear();
					input.add("Stray data!!!");
					out = new ObjectOutputStream(sock.getOutputStream());
					out.writeObject(input);
					up.append("\n"+client+" sent stray data: "+input.get(2));
					break;
				}
			}
		} catch (Exception e) {
			up.append("\n"+e.getMessage());
			e.printStackTrace();
		} 
		input.clear();
	}
	
	public void start (){      
		//System.out.println("Starting " +  threadName );      
		if (t == null){        
			t = new Thread (this, threadName);         
			t.start ();      
			}   
		}
	
	public void stop (){      
		//System.out.println("Starting " +  threadName );      
		if (t != null){ 
				try {
					if(in != null)in.close();
					if(sock !=null)sock.close();
					if(servsock != null)servsock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			t.stop();      
			}   
		}
}
