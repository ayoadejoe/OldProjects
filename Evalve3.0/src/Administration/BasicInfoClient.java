package Administration;

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
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class BasicInfoClient {
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
	private String message = ""; // message from server
	private BufferedImage imag = null; private PrintStream detect;
	public BasicInfoClient() {
	}
	
	public  Vector DataCheck(String which){
		 try {
		 client = new Socket("192.168.0.101", 7386);
		 OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         out.writeUTF(which);
         //("Sent>> "+which);
         if (which.equals("password")){
        	in = new ObjectInputStream(client.getInputStream());
        	Vector received = (Vector) in.readObject();
            //("From Basic Info Server: "+received);
            return received;
 			}else if(which.equals("username")){
 		        	in = new ObjectInputStream(client.getInputStream());
 		        	Vector received = (Vector) in.readObject();
 		            //("From Basic Info Server: "+received);
 		            return received;
 			}else if(which.equals("email")){
		        	in = new ObjectInputStream(client.getInputStream());
		        	Vector received = (Vector) in.readObject();
		            //("From Basic Info Server: "+received);
		            return received;
			}else if(which.equals("passport")){
	        	in = new ObjectInputStream(client.getInputStream());
	        	Vector received = (Vector) in.readObject();
	            //("From Basic Info Server: "+received);
	            return received;
			}else if(which.equals("Calendar")){
	        	in = new ObjectInputStream(client.getInputStream());
	        	Vector received = (Vector) in.readObject();
	            //("From Basic Info Server: "+received);
	            return received;
		}
		 } catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public  boolean AdminCheck(String which){
		 try {
		 client = new Socket("192.168.0.101", 7386);
		 OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
        out.writeUTF(which);
        //("Sent>> "+which);
        if(which.contains("admin")){
        	in = new ObjectInputStream(client.getInputStream());
        	boolean confirm = (boolean) in.readObject();
            //("From Basic Info Server: Admin is "+confirm);
            return confirm;
	}
		 } catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public  String termCheck(String which){
		 try {
		 client = new Socket("192.168.0.101", 7386);
		 OutputStream outToServer = client.getOutputStream();
		 DataOutputStream out = new DataOutputStream(outToServer);
		 out.writeUTF(which);
		 //("Sent>> "+which);
		 if (which.contains("Present")){
			 in = new ObjectInputStream(client.getInputStream());
			 String confirm = (String) in.readObject();
			 return confirm;
		 }
		 } catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void rewrite(int rowIndex, int columnIndex, Object term,
			Object newValue, int no) {
		String which = "rewriteCal,"+rowIndex+","+columnIndex+","+term+","+newValue+","+no;
		 try {
		 client = new Socket("192.168.0.101", 7386);
		 OutputStream outToServer = client.getOutputStream();
       DataOutputStream out = new DataOutputStream(outToServer);
       out.writeUTF(which);
       //("Sent>> "+which);
		 } catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public  Vector DataPaspp(BufferedImage pasp, String which){
		
		//( "Attempting connection for Passport\n" );
		try{
			client = new Socket("192.168.0.101", 7387);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(which);
	         //("Sent>> "+which);
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
			client = new Socket("192.168.0.101", 7387);
			//( "Connected to: " +client.getInetAddress().getHostName() );
			DataInputStream in=new DataInputStream(client.getInputStream());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			ImageIO.write(pasp,"JPG",client.getOutputStream());
			//("File Uploaded Successfully");
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
		return null;
	}

	public void getPassport(String passport){ int d = 0;
		//( "Attempting connection to get your Passport" );
		try{
		
			 client = new Socket("192.168.0.101", 7388);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(passport.trim());
	         //("Sent>> "+passport);
	         //client.close();
	         try {
					Thread.sleep(1500);
					//("Wakes up");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	//client = new Socket("192.168.0.101", 7388);
	 			//("Now connecting for passport ...");
	 			BufferedImage img=ImageIO.read(ImageIO.createImageInputStream(client.getInputStream()));
	 			  File file = new File("C:\\LocalPassports\\");
	 			 	if (!file.exists()) {
	 			 		new File("C:\\LocalPassports\\").mkdirs();
	 			 		//(file+ " now exists.");
	 			 		}
	 			 File outputfile = new File("C:\\LocalPassports\\"+passport);
	 			ImageIO.write(img, "JPG", outputfile);
	 			//("Passport gotten: "+outputfile); 
	 			return;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error for passport acquisition.");
			e.printStackTrace();
		}
	}
	
	public void loadTeacher(String name, String address, String phone1,
			String phone2, String email, String password, String username,
			String birthday, String admission, int salary, String gender,
			String comment, String SubClassCL, String picture) {
		String OneTeacher = name+"#"+address+"#"+phone1+"#"+phone2+"#"+email+"#"+password+"#"+username+"#"+birthday+"#"+admission+
				"#"+salary+"#"+gender+"#"+comment+"#"+SubClassCL+"#"+picture;
		//(OneTeacher);
		try{
			client = new Socket("192.168.0.101", 7386);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(OneTeacher);
	         //("Sent>> "+OneTeacher);
	         client.close();
		} catch (IOException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			//e.printStackTrace();
		}
	}

}
