package baseGui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

public class TimeUpdaterClient {
	private Socket client; // socket to communicate with server
	private OutputStream os = null;
	private DataInputStream in;
	private DataOutputStream out;
	private int SIPort = 7377;
	//IPs
	private String IP = "192.168.0.101";
	
	
	public TimeUpdaterClient() {
		
	}
	
	public String UpdaterClient(String subject, int No, String Clazz) {
		String timx = null;
		//("In Time Update: "+ subject+", "+No);
		try {
			 client = new Socket(IP, SIPort);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(subject+"'"+No+"'"+Clazz+"'TimeUpdate");
	         //("Connection waiting to receive Time server ...");
			 	DataInputStream is = new DataInputStream(client.getInputStream());
	            timx = is.readUTF();
	            //("From teacher Server: "+timx);
	            out.close();
	            client.close();
	            
		} catch (IOException e) {
			//("You are currently offline.");
			return "01:10";
			//e.printStackTrace();
		}
		return timx.trim();  
		
	}

}
