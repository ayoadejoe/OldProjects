package baseGui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class BinarySendClient implements Runnable{
	private String filename = null;
	private String threadName = null; 
	private Thread t = null;
	public BinarySendClient(String filetosend, String threadname){
		filename = filetosend;
		threadName = threadname;
		//("About to send this file "+filename);
	      }
	
	public void run() {
		try {
			  File myFile = new File(filename);
		      if(myFile.exists()){
			  Socket sock = new Socket("192.168.0.101", 7382);
		      //(myFile.length());
		      byte[] mybytearray = new byte[(int) myFile.length()];
		      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
		      bis.read(mybytearray, 0, mybytearray.length);
		      OutputStream os = sock.getOutputStream();
		      os.write(mybytearray, 0, mybytearray.length);
		      os.flush();
		      sock.close();
		      }else{
		    	  //("File does not exist");
		      }
			} catch (IOException e) {
				//("You are offline");
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
