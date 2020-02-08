package clients;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
public class CityClient {
	private Socket client; // socket to communicate with server
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private static String which = null;
	private int localserver = 21000;
	public ArrayList getIt(ArrayList dataPlan) {
		try {
			 client = new Socket(new getIP().IPget(), localserver);
			 OutputStream outToServer = client.getOutputStream();
			 client.setSoTimeout(80000); 
			 dataPlan.add(0, InetAddress.getLocalHost().getHostName());
	         out = new ObjectOutputStream(outToServer);
	         System.out.println("Client about to send out:"+dataPlan);
	         out.writeObject(dataPlan);
	         
	         in = new ObjectInputStream(client.getInputStream());
	         dataPlan = (ArrayList) in.readObject();
	         return dataPlan;
		} catch (IOException | ClassNotFoundException e) {
			if(!dataPlan.isEmpty())dataPlan.clear();
			dataPlan.add("Server Issues:"+e.getMessage());
			e.printStackTrace();
			return dataPlan;
		}
	}
	
}
