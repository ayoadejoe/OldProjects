package TeacherPage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class UploadNamer implements Runnable{
	private Thread t = null;
	private String threadName = null, fileName = null, clasz = null, Filetype;
	private Socket client = null;
	public UploadNamer(String claz, String filename, String name, String fileType) {
		threadName = name;
		fileName  = filename;
		clasz = claz;
		Filetype = fileType;
	}

	public void run() {
		try{
			client = new Socket("192.168.0.101", 7390);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(fileName+","+clasz+","+Filetype);
	         //("Sent>> "+fileName+","+clasz+","+Filetype);
	         client.close();
		} catch (IOException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			//e.printStackTrace();
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
