package baseGui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DiaryClient {
	private Socket client; // socket to communicate with server
	private ObjectInputStream in;
	private static String which = null;
	private File file;
	private PrintStream till;
	public DiaryClient() {
	}
	
	public boolean lessonSaver(Vector<String> giant){
		boolean latent = false;
		String Subject  = giant.get(13).toString().trim().toLowerCase();
		String Term = giant.get(19).toString().trim().toLowerCase();
		String ClassS  = giant.get(14).toString().trim().toLowerCase();
		String topic = giant.get(15).toString().trim().toLowerCase();
		String thisfile = ClassS+"`"+topic;
		 File file4 = new File("C:\\LessonNotes\\"+Term+"\\"+Subject);
		 	if (!file4.exists()) {
		 		new File("C:\\LessonNotes\\"+Term+"\\"+Subject).mkdirs();
		 		//(file4+ " now exists.");
		 		}
		file = new File("C:\\LessonNotes\\"+Term+"\\"+Subject+"\\"+thisfile+".evl");
		try {till = new PrintStream(file);} catch (FileNotFoundException e) {e.printStackTrace(); latent = false;}
		int no = giant.size(); int g = 0;
		while(no>0){
			till.print(giant.get(g)+"` ");
			g++; no--;
		}
		giant.clear();
		latent = true;
		return latent;
	}
	
	public boolean lessonSubmit(Vector<String> giant){
		boolean latent = false;
		 try {
		 client = new Socket("192.168.0.101", 7384);
		 OutputStream outToServer = client.getOutputStream();
         ObjectOutputStream out = new ObjectOutputStream(outToServer);
         out.writeObject(giant);
         //("Sent>> "+giant);
         giant.clear();
         latent = true;
/*
        	in = new ObjectInputStream(client.getInputStream());
        	Vector received = (Vector) in.readObject();
            //("From Student Register Server: "+received);
            return received;
 			}else if(which.equals("teacher")){
 				in = new ObjectInputStream(client.getInputStream());
	        	Vector received = (Vector) in.readObject();
	            //("From teacher register Server: "+received);
	            return received;
 			}*/
		 } catch (IOException e) {
				lessonSaver(giant);
				JOptionPane.showMessageDialog(null,  "Your work could not be submitted " +
						"due to connection issues, it will be saved. Remember to check the box in " +
						"your diary later to submit it.",e.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		return latent;
	}
}
