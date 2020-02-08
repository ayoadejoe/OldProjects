package espTest1;

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
	private int localport = 8080;
	private int DHCPNo = 100;
	private String address= "";
	private Thread t = null;
	String data = "STOP75X STOP75X STOP75X STOP75X STOP75X STOP75X";
	String data2 = "STP100X STP100X STP100X STP100X STP100X STP100X";
	String data3 = "STRT75X STRT75X STRT75X STRT75X STRT75X STRT75X";
	String data4 = "SETESPX SETESPX SETESPX SETESPX SETESPX SETESPX";
	String data5 = "RETIMEX RETIMEX RETIMEX 1;04:14,10,2016:16,50,00:";
	String data6 = "SHUTDNX SHUTDNX SHUTDNX SHUTDNX SHUTDNX SHUTDNX";
	String data8 = "STT100X STT100X STT100X STT100X STT100X STT100X";
	
	private static String data7 = "Query";
	private String request = null; 
	private String info = "raw";
	
	EspClient(String address, String request) {
		this.address = address;
		this.request = request;
	}


	static void main(String[] args) {
		EspClient client  = new EspClient("192.168.8.105", data7);
	}
	
	void redress(String address, String request) {
		this.address = address;
		this.request = request;
	}

	String connect() {
			//requestCheck();
				
			try {
				 client = new Socket("192.168.8.105", localport);
				 client.setSoTimeout(20000); 
				 OutputStream outToServer = client.getOutputStream();
		         out = new DataOutputStream(outToServer);
		         out.writeUTF(request);//#r
		         
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
	
	private void requestCheck() {
		String roll = request.substring(8, 8+6);
		int len = request.length();
		int xlocator=-2;	
		for(int x=-2; (x+8)<len; x++){
			xlocator = request.indexOf('X', x);
			System.out.println(x);
			String xvalue = request.substring(xlocator+2, xlocator+8);
			System.out.println(xvalue);
			x=xlocator;
			break;
		}
		
	}
	
	

}
