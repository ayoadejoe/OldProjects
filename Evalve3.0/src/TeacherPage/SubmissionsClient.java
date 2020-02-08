package TeacherPage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Vector;

public class SubmissionsClient {
	private Socket client;
	private int DEPort = 7383;
	private DataOutputStream submit = null;
	private ObjectInputStream accept = null;
	private Vector evaluations = null, headings = null;
	public Vector getSubmissionsTable(String cl, String subject,
			String claxx, String evalType) {
		//("Trying to connect to result: "+subject); 
		subject = subject.toLowerCase();
		String SubData = "$Submissions$"+"@"+subject+"@"+claxx+"@"+evalType+"@"+"$evaluations$";
		try {
			client = new Socket("192.168.0.101", DEPort);
			submit = new DataOutputStream(client.getOutputStream());
			submit.writeUTF(SubData);
			accept = new ObjectInputStream(client.getInputStream());
			try {
				evaluations = (Vector) accept.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			submit.close();
			accept.close();
			client.close();
			//("Got evaluations :"+evaluations);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return evaluations;
	}

	public Vector getSubmissionsColumns(String cl, String subject,
			String claxx, String evalType) {
		//("Trying to connect to result: "+subject); 
		subject = subject.toLowerCase();
		String SubData = "$Submissions$"+"@"+subject+"@"+claxx+"@"+evalType+"@"+"$headings$";
		try {
			client = new Socket("192.168.0.101", DEPort);
			submit = new DataOutputStream(client.getOutputStream());
			submit.writeUTF(SubData);
			accept = new ObjectInputStream(client.getInputStream());
			try {
				headings = (Vector) accept.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			submit.close();
			accept.close();
			client.close();
			//("Got headings :"+headings);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return headings;
	}

	public void submitEssayScore(Object iDNO, String subject, String claxx,
			String evalType, int week, int val, int wgt) {
		subject = subject.toLowerCase();
		String SubData = "$Submissions$"+"@"+subject+"@"+claxx+"@"+evalType+"@"+week+"@"+val+"@"+iDNO.toString()+"@"+wgt+"@"+"$%Score%$";
		//(SubData);
		try {
			client = new Socket("192.168.0.101", DEPort);
			submit = new DataOutputStream(client.getOutputStream());
			submit.writeUTF(SubData);
			submit.close();
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Vector getClassIDs(String claxx) {
		claxx = claxx.toUpperCase().trim();
		Vector IDs = null;
		String SubData = claxx+" "+"%StdId%";
		try {
			client = new Socket("192.168.0.101", 7375);
			submit = new DataOutputStream(client.getOutputStream());
			submit.writeUTF(SubData);
			accept = new ObjectInputStream(client.getInputStream());
			try {
				IDs = (Vector) accept.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			submit.close();
			accept.close();
			client.close();
			System.out.println("Got IDs :"+IDs);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return IDs;
	}
}
