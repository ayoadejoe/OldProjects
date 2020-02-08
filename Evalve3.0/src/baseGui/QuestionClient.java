package baseGui;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class QuestionClient {
	private Socket QClient;
	private JTextArea displayArea; // display information to user
	private ObjectOutputStream output; // output stream to server
	private ObjectInputStream input; // input stream from server
	private Socket client; // socket to communicate with server
	private String message = ""; // message from server
	private File file = new File("Buffer.txt");
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private OutputStream os = null;
	private ServerSocket servsock = null;
	public void check(String classS, String subject, String examday, String examtime, String duration, 
			String terms, String examiner, String exam, int linenumber, int pointing, int alt, int pt, 
			String examType, String evalType, File file6) {

		 //("file6 = "+file6);
		try {
			String message =  classS+"`"+subject+"`"+terms+"`"+examtime+"`"+examday+"`"+examType+"`"+evalType
					+"`"+duration+"`"+linenumber+"`"+examiner+"`"+pointing;
			client = new Socket("192.168.0.101", 7374);
			OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out =
	                       new DataOutputStream(outToServer);

	         out.writeUTF(message);
	         //("Sent>> "+message);
	         //client.close();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		}  
		return;
		////("Connected to: " +QClient.getInetAddress().getHostName());
	}
	
	public void StreamFiles(String ServerDirectory, String localname, String filename){
			//( "Attempting connection for Questions\n" );
			new BinaryThreader(localname, ServerDirectory, "receive", filename);
	}
	
	public boolean Work(int IDNo, final String Work, final String Username, final String ClassS, 
			final String Subject, final String Examday, final String Examtime, final String Duration, final String Terms, 
			final int NoQ, final int pointing, final String ExamType, String thisfile, String Topic, int Week,
			int weight){
	
		
		try {
			String message =  IDNo+">"+Work+">"+Username+">"+ClassS+">"+Subject+">"+Examday+">"+Examtime
					+">"+Duration+">"+Terms+">"+thisfile+">"+NoQ+">"+pointing+">"+weight+">"+ExamType+">"+Topic+">"+Week;
			client = new Socket("192.168.0.101", 7374);
			OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out =
	                       new DataOutputStream(outToServer);

	         out.writeUTF(message);
	         //("Sent>> "+message);
	         //client.close();
		} catch (UnknownHostException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			return false;
		} catch (IOException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			return false;
		}  
		return true;
	}

	public void DiagramStream(ArrayList qArray, int noQ, String Subject) {
		int Boundary = (noQ*2);			// so as to cover the location of the diagrams
		int d = noQ;
		String Serverdirectory = "C:\\ServerTeachers\\Diagrams\\"+Subject.toLowerCase().trim();
		String localdirectory = "C:\\Teachers\\Diagrams\\"+Subject.toLowerCase().trim();
		while(d<=Boundary){
			
			try{
			String DiagLoc = qArray.get(d).toString().trim();
			File Diagram = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+DiagLoc+".evl");
			if(Diagram.exists()){
				//(Diagram.toString()+" exists!");
				BinaryThreader sendname = new BinaryThreader(Diagram.getAbsolutePath(), Serverdirectory, "$DiagramsTeacher$", DiagLoc);
			}else{
				//(Diagram.toString()+" does not exist.");
				}
			d++;
			}catch(Exception f){
				//(d+", "+Boundary);
				Boundary = d;
				break;
			}
		}
	}
}
