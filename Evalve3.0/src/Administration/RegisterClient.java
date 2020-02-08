package Administration;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class RegisterClient {
	private Socket client; // socket to communicate with server
	private ObjectInputStream in;
	private static String which = null;
	public RegisterClient(String message) {
		which = message;
	}
	
	public Vector Registration(){
		try {
			 client = new Socket("192.168.0.101", 7375);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(which);
	         //("Sent>> "+which);
	         if (which.equals("student")){
	        	in = new ObjectInputStream(client.getInputStream());
	        	Vector received = (Vector) in.readObject();
	            //("From Student Register Server: "+received);
	            return received;
	 			}else if(which.equals("teacher")){
	 				in = new ObjectInputStream(client.getInputStream());
		        	Vector received = (Vector) in.readObject();
		            //("From teacher register Server: "+received);
		            return received;
	 			}
				//Thread.sleep(500);
	         //client.close();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;  
		
		
	}

	}
