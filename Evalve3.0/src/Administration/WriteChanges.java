package Administration;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class WriteChanges {
	private Socket client; // socket to communicate with server
	private static String which = null; 
	private File subject = new File("allresult.evl");
	public void rewrite(int rowIndex, int columnIndex, Object oldname, Object newValue, int iDno, Object term, Object subj) {
		oldname = ((String) oldname).trim(); newValue = newValue.toString().trim(); term = term.toString().trim();
		subj = subj.toString().trim();
		String Query = rowIndex +"`"+columnIndex +"`"+ oldname +"`"+newValue +"`"+ iDno+"`"+term ;
			try {
				which = "editresults";
				client = new Socket("192.168.0.101", 7375);
				OutputStream outToServer = client.getOutputStream();
		         DataOutputStream out =
		                       new DataOutputStream(outToServer);
		         String message = which+"`"+Query+"`"+subj;
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
		}
}
