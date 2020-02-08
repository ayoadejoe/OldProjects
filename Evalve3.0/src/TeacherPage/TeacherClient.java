package TeacherPage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

public class TeacherClient{
	private Socket client; // socket to communicate with server
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private OutputStream os = null;
	private int dataRead, current = 0, dataSize = 6022386;
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private DataInputStream in;
	private DataOutputStream out;
	private Socket sock = null;
	private ServerSocket servsock = null;
	private static String which = null;
	
	//ports
	private int SIPort = 7377;
	private int ChatPort1 = 7378; private int ChatPort2 = 7379; private int ChatPort3 = 7380;
	private int Lists = 7381;
	
	//IPs
	private String IP = "192.168.0.101";
	
	//files
	private String SetList;
	private String Essays;
	private String Results;
	private String Diaries;
	private String Chats;
	private static File file1 = new File("SetList.evl");
	private static File file2 = new File("Essays.evl");
	private static File file3 = new File("Diaries.evl");
	private static File file4 = new File("Chats.evl");
	private static File file5 = new File("Results.evl");
	private File file = new File("Buffer.txt");
	private String confirm = null, received = null, r = null;
	private String term;
	private String today = new Date().toLocaleString();
	public TeacherClient() {
	}

	public String signIn(String user, String password) {
		which = "TeacherP%"+"`"+user+"`"+password;
		try {
			 client = new Socket(IP, SIPort);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(which);
	         //("Sent>> "+which);
			 //("Connection waiting to receive teacher server ...");
			 {
					in = new DataInputStream(client.getInputStream());
		            received = in.readUTF();
		            //("From teacher Server: "+received);
		            r = received;
		            
		            in = new DataInputStream(client.getInputStream());
		            received = in.readUTF();
		            switch(received){
		            case "true":
		            	confirm = "true";
		            	break;
		    		case "empty":
		    			JOptionPane.showMessageDialog(null, "Your School Calendar is not ready. Contact your Admin");
		    			break;
		    		case 1+"":
		    			JOptionPane.showMessageDialog(null, today+" is a day in the First Term of this session. ");
		    		term  = "first_term";
		    			break;
		    		case 2+"":
		    			term  = "second_term";
		    			JOptionPane.showMessageDialog(null, today+" is a day in the Second Term of this session. ");
		    			break;
		    		case 3+"":
		    			term  = "third_term";
		    			JOptionPane.showMessageDialog(null, today+" is a day in the Third Term of this session. ");
		    			break;
		    		case "false":
		    			confirm = "false";
		    			break;
		    		case "void":
		    			JOptionPane.showMessageDialog(null, "Your School Calendar is not ready. Contact your Admin");
		    			break;
		    		default:
		    			break;
		    					}
		            confirm = r+"`"+term;
		            //("confirm: "+confirm);
		            return confirm;
			 }
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		}  
		return confirm;
	}

	public Vector getSetQ(String term2, String userName, String SubClassCl) {
		try {
			 client = new Socket(IP, SIPort);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(term2+"#"+userName+"#"+SubClassCl);
	         //("Sent from getList >> "+term2+", username: "+userName);
			 //("Connection waiting to receive teacher server ...");
				 	ObjectInputStream is = new ObjectInputStream(client.getInputStream());
		            Vector received = (Vector) is.readObject();
		            //("From teacher Server: "+received);
		            return received;
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Cannot cast the List Object to List");
			e.printStackTrace();
		}
		return null;  
		
	}
	public void rewrite(int columnIndex, Object oldvalue, Object newValue, Object No,
			Object sub, Object clas, Object evaltype) {
		Object Queue = columnIndex +">"+oldvalue +">"+ newValue +">"+No +">"+ sub+">"+clas +">"+evaltype;
		String Query = "SetList"+">"+Queue.toString();
			try {
				client = new Socket(IP, SIPort);
				OutputStream outToServer = client.getOutputStream();
		         DataOutputStream out = new DataOutputStream(outToServer);
		         out.writeUTF(Query);
		         //("Sent>> "+Query.toString());
		         return;
		         //client.close();
			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
				e.printStackTrace();
			}  
		}

	public void DeleteWork(String delQuery, String Term, String serverCopy) {
		try {
			client = new Socket(IP, SIPort);
			OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(Term+" > "+delQuery+" > "+serverCopy);
	         //("Sent>> term = "+term+" Query = "+delQuery.toString());
	         return;
	         //client.close();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		}  
	}

}
