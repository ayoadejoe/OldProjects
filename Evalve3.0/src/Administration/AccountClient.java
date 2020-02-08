package Administration;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class AccountClient {
	private Socket client; // socket to communicate with server
	private ObjectInputStream in;
	private static String which = null;
	public Vector Account(String dataPlan) {
		which = dataPlan;
		try {
			 client = new Socket("192.168.0.101", 7385);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         if(which.equals("IncomeHeader")){
	        	 out.writeUTF(which);
		         //("Sent>> "+which);
		         in = new ObjectInputStream(client.getInputStream());
		         Vector IncomeHeader = (Vector) in.readObject();
		         //("From Account Server: "+IncomeHeader);
		         return IncomeHeader;
	         }else if(which.equals("IncomeData")){
	        	 out.writeUTF(which);
	        	 in = new ObjectInputStream(client.getInputStream());
		         Vector IncomeData = (Vector) in.readObject();
		         //("From Account Server2: "+IncomeData);
		         return IncomeData;
	         }else if(which.equals("ArrangeStudent")){
	        	 out.writeUTF(which);
		         //("From Account Arrange: "+which);
	         }else if(which.equals("StudentData")){
	        	 out.writeUTF(which);
	        	 in = new ObjectInputStream(client.getInputStream());
		         Vector StudentData = (Vector) in.readObject();
		         //("From Account Server2: "+StudentData);
		         return StudentData;
	         }else if(which.equals("StudentHeader")){
	        	 out.writeUTF(which);
	        	 in = new ObjectInputStream(client.getInputStream());
		         Vector StudentHeader = (Vector) in.readObject();
		         //("From Account Server2: "+StudentHeader);
		         return StudentHeader;
	         }else if(which.equals("ExpenseHeader")){
	        	 out.writeUTF(which);
		         //("Sent>> "+which);
		         in = new ObjectInputStream(client.getInputStream());
		         Vector ExpenseHeader = (Vector) in.readObject();
		         //("From Account Server: "+ExpenseHeader);
		         return ExpenseHeader;
	         }else if(which.equals("ExpenseData")){
	        	 out.writeUTF(which);
	        	 in = new ObjectInputStream(client.getInputStream());
		         Vector ExpenseData = (Vector) in.readObject();
		         //("From Account Server2: "+ExpenseData);
		         return ExpenseData;
	         }else if(which.equals("Balancing")){
	        	 out.writeUTF(which);
		         //("From Account Balancing: "+which);
	         }else if(which.equals("BalanceHeader")){
	        	 out.writeUTF(which);
		         //("Sent>> "+which);
		         in = new ObjectInputStream(client.getInputStream());
		         Vector BalanceHeader = (Vector) in.readObject();
		         //("From Account Server: "+BalanceHeader);
		         return BalanceHeader;
	         }else if(which.equals("BalanceData")){
	        	 out.writeUTF(which);
	        	 in = new ObjectInputStream(client.getInputStream());
		         Vector BalanceData = (Vector) in.readObject();
		         //("From Account Server2: "+BalanceData);
		         return BalanceData;
	         }
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;  
		
	}
	public void change(String colName, Object aValue, Object row0, String account) {
		//("For Change: "+account);
		String changer = account+">"+colName+">"+aValue.toString()+">"+row0;
		
		try {
			client = new Socket("192.168.0.101", 7385);
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF(changer);
			//("Sent: "+changer);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void alterTable(String account, String newField, String newfieldName) {
		String edit = account+"<"+newField+"<"+newfieldName;
		try {
			client = new Socket("192.168.0.101", 7385);
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF(edit);
			//("Sent: "+edit);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
