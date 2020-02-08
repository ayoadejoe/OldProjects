package TeacherPage;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import baseGui.ProgressDisplay;

public class Upload implements Runnable{
	private Socket client; // socket to communicate with server
	private ObjectInputStream in;
	private File file = new File("Buffer.txt");
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private OutputStream os = null;
	private ServerSocket servsock = null;
	private boolean got = false;
	private Socket QClient;
	private JTextArea displayArea; // display information to user
	private ObjectOutputStream output; // output stream to server
	private ObjectInputStream input; // input stream from server
	private String message = "", threadName = null, fileLocation = null; // message from server
	private BufferedImage imag = null; private PrintStream detect;
	private Thread t = null;
	
	public Upload(String name, String fileloc){
		threadName = name;
		fileLocation  = fileloc;
		 	}
	
	public void Image(File source, String SubClasx, String fileName, String whichfile){
			//( "Attempting connection for Passport\n" );
			try {
				imag = ImageIO.read(source);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try{
				client = new Socket("192.168.0.101", 7389);
				 OutputStream outToServer = client.getOutputStream();
		         DataOutputStream out = new DataOutputStream(outToServer);
		         out.writeUTF(fileName+","+SubClasx+","+whichfile);
		         //("Sent>> "+fileName+","+SubClasx+","+whichfile);
		         got = true;
		         client.close();
			} catch (IOException e) {
				//JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
				//e.printStackTrace();
			}
			if(got == true){
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					//("Client could not sleep!");
				}
			try{
				//( "Wait, while your file is being uploaded");
				try{
				client = new Socket("192.168.0.101", 7389);
				//( "Connected to: " +client.getInetAddress().getHostName() );
				DataInputStream in=new DataInputStream(client.getInputStream());
				DataOutputStream out = new DataOutputStream(client.getOutputStream());
				ImageIO.write(imag,"JPG",client.getOutputStream());
				JOptionPane.showMessageDialog(null, "File Uploaded Successfully");
			}finally{
				if(bis != null)bis.close();
				if(os != null)os.close();
				if(client != null) client.close();
				}
			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
				e.printStackTrace();
			}
			}
	}
	//File source, String Clasx, 
	public void run() {
		final int SOCKET_PORT = 13267;      // you may change this
		 final String SERVER = "192.168.0.101";  // 192.168.0.101
		 ProgressDisplay progress = new ProgressDisplay(null, "Uploading");
			progress.setVisible(true);
			progress.setAlwaysOnTop(true);
			progress.setMaximum(3);
		 Socket sock = null;
		    try {
		      sock = new Socket(SERVER, SOCKET_PORT);
		      //("Connecting... to send file: "+fileLocation+ " on thread:"+threadName);
		      // send file
	          File myFile = new File (fileLocation);
	          progress.setValue(1);
	          byte [] mybytearray  = new byte [(int)myFile.length()];
	          fis = new FileInputStream(myFile);
	          bis = new BufferedInputStream(fis);
	          bis.read(mybytearray,0,mybytearray.length);
	          
	          os = sock.getOutputStream();
	          //("Sending " + fileLocation + "(" + mybytearray.length + " bytes)");
	         
	          progress.setValue(2);
	          os.write(mybytearray,0,mybytearray.length);
	          os.flush();
	        } catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        finally {
			try {
			  if (bis != null)bis.close();
	          if (os != null) os.close();
	          if (sock!=null) sock.close();
	          	progress.setValue(3);
	 		 	progress.setVisible(false);
	 		 	 JOptionPane.showMessageDialog(null, "Success! File Uploaded Server.");
				} catch (IOException e) {
					e.printStackTrace();
				}
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
