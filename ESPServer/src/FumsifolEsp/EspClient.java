package FumsifolEsp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

class EspClient{
	private Socket client; // socket to communicate with server
	private DataInputStream in;
	private DataOutputStream out;
	private static String which = null;
	private int localport = 80;
	private int DHCPNo = 100;
	private String address= "";
	private Thread t = null;
	

	public static void main(String[] args) {
		System.out.println("Launching");
		EspClient client  = new EspClient();
		client.connect("192.168.4.1", "LEDOff");
	}
	

	String connect(String address, String request) {
		String info = "no outcome";
			try {
				 client = new Socket(address, localport);
				 client.setSoTimeout(20000); 
				 OutputStream outToServer = client.getOutputStream();
		         out = new DataOutputStream(outToServer);
		         out.writeUTF(request);
		         System.out.println("Request sent");
					in = new DataInputStream(client.getInputStream());
					info = in.readLine();
			} catch (IOException e) {
				System.out.println(address+" "+e.getMessage());
				info = e.getMessage();
				e.printStackTrace();
				return info;
			}
			return info;
		
	}
}
