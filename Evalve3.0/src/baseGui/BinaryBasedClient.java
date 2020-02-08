package baseGui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class BinaryBasedClient implements Runnable{
	private String filename = null;
	private String threadName = null; 
	private Thread t = null;
	public BinaryBasedClient(String filetoget, String threadname){
		filename = filetoget;
		threadName = threadname;
	      }
	
	public void run() {
		try {
			  File myFile = new File(filename);
		      if(myFile.exists()){
			  Socket sock = new Socket("192.168.0.101", 7373);
		      //(myFile.length());
		      byte[] mybytearray = new byte[(int) myFile.length()];
		      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
		      bis.read(mybytearray, 0, mybytearray.length);
		      OutputStream os = sock.getOutputStream();
		      os.write(mybytearray, 0, mybytearray.length);
		      os.flush();
		      sock.close();
		      }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	public void start (){      
		////("Starting " +  threadName );      
		if (t == null){        
			t = new Thread (this, threadName);         
			t.start ();      
			}   
		} 
}
