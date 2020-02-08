package Administration;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ResultClient {
	private Socket client; // socket to communicate with server
	private File file = new File("Buffer.txt");
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private OutputStream os = null;
	
	private int dataRead, current = 0, dataSize = 6022386;
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private Socket sock = null;
	private ServerSocket servsock = null;
	private String receivedFile;
	public ResultClient(String which) {
			//if (file1.exists() && file2.exists()){
				//return;
			//}
			try {
				client = new Socket("192.168.0.101", 7375);
				OutputStream outToServer = client.getOutputStream();
		         DataOutputStream out =
		                       new DataOutputStream(outToServer);

		         out.writeUTF(which);
		         //client.close();
			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
				e.printStackTrace();
			}  
			try{
				//("Connection waiting to receive "+which+"...");
				if(which.equals("result")){
				receivedFile = "allresult.evl";
				}else if (which.equals("master")){
					receivedFile = "master.evl";
				}
				{
				byte[] myfile = new byte[dataSize];
				InputStream is = sock.getInputStream();
				fos = new FileOutputStream(receivedFile);
				bos = new BufferedOutputStream(fos);
				dataRead = is.read(myfile, 0, myfile.length);
				current = dataRead;
				do{
					dataRead = is.read(myfile, current, (myfile.length -current));
					if(dataRead >= 0) current+= dataRead;
				} while(dataRead> -1);
				
				bos.write(myfile, 0, current);
				bos.flush();
				return;
				}
				//Thread.sleep(500);
			}catch (IOException e) {
				e.printStackTrace();
			}
			finally{
					try {
				if(fos != null)fos.close();
				if(bos != null) bos.close();
				if (sock != null)sock.close();
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						if (servsock != null)
							try {
								servsock.close();
							} catch (IOException e) {
								JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
								e.printStackTrace();
							}
					}
			}
		}

}
