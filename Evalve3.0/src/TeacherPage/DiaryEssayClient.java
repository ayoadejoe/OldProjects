package TeacherPage;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DiaryEssayClient {
	private Socket client; // socket to communicate with server
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private OutputStream os = null;
	private ObjectInputStream in;
	private static String which = null;
	
	public DiaryEssayClient() {
	}
	public Vector DiaryEssay(int iDNo2, int sub, String username, String Clazz, String subjct, String tem, int w){
		which = iDNo2+">"+sub+">"+username+">"+Clazz+">"+subjct+">"+tem+">"+w;
		try {
			 client = new Socket("192.168.0.101", 7383);
			 while(true){
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(which);
	         //("Sent>> "+which);
	        	in = new ObjectInputStream(client.getInputStream());
	        	Vector received = (Vector) in.readObject();
	            ////("From Diary/Essay Server: "+received);
	            return received;
			 }
				//Thread.sleep(500);
	         //client.close();
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
	
	public void rewrite(int rowIndex, int columnIndex, Object oldvalue, 
			Object newValue, Object subjt, Object cls, Object tm, int week){
		String message = rowIndex+"`"+columnIndex+"`"+oldvalue+"`"+newValue+"`"+subjt+"`"+cls+"`"+tm+"`"+week;
		try {
			 client = new Socket("192.168.0.101", 7383);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(message);
	         //("Sent>> "+message);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		}
	}
	public Vector getAllDiaries(String term) {
		try{
			 client = new Socket("192.168.0.101", 7383);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF("AllDiaries<"+term);
	         //("Sent>> All Diaries<"+term);
	         in = new ObjectInputStream(client.getInputStream());
	        	Vector received = (Vector) in.readObject();
	            //("From Diary/Essay Server: "+received);
	            return received;
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Vector PartSubject(String subject, String clasz, String topic,
			int wk, String term) {
		which = "AdminCheck"+"#"+subject+"#"+topic+"#"+clasz+"#"+term+"#"+wk;
		try {
			 client = new Socket("192.168.0.101", 7383);
			 while(true){
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(which);
	         //("Sent>> "+which);
	        	in = new ObjectInputStream(client.getInputStream());
	        	Vector received = (Vector) in.readObject();
	            //("From Diary/Essay Server: "+received);
	            return received;
			 }
				//Thread.sleep(500);
	         //client.close();
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
	
	public Vector UploadGet() {
		which = "filelist";
		try {
			 client = new Socket("192.168.0.101", 7383);
			 while(true){
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(which);
	         //("Sent>> "+which);
	        	in = new ObjectInputStream(client.getInputStream());
	        	Vector received = (Vector) in.readObject();
	            //("From Diary/Essay for file: "+received);
	            return received;
			 }
				//Thread.sleep(500);
	         //client.close();
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
	
	
}
