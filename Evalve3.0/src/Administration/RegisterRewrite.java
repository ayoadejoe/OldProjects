package Administration;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class RegisterRewrite {
	
	private Socket client; // socket to communicate with server
	private static String which = null;
	
	public RegisterRewrite(String who){
		//("This is who: "+who);
		which = who+"s";
		//("This is which: "+which);
	}
	
	public void rewrite(int rowIndex, int columnIndex, String oldname, Object newValue, int iDno, String who) {
		oldname = oldname.trim(); newValue = newValue.toString().trim();
		String Query = rowIndex +">"+columnIndex +">"+ oldname +">"+newValue +">"+ iDno;
			try {
				client = new Socket("192.168.0.101", 7375);
				OutputStream outToServer = client.getOutputStream();
		         DataOutputStream out = new DataOutputStream(outToServer);
		         String message = who+">"+Query;
		         out.writeUTF(message);
		         //("Sent>> "+message);
		         client.close();
			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
				e.printStackTrace();
			}  
		}
		
}
