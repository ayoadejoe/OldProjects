package baseGui;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import TeacherPage.ViewerThread;

public class BinaryReceiveClient implements Runnable{
	private String filename = null;
	private String threadName = null; 
	private Thread t = null;
	private String FileType = null, localdirectory = null;
	private int current = 0;
	private static int dataSize = 150022386;
	public BinaryReceiveClient(String LocalDirectory, String filetype, String fileName, String threadname) {
		filename = fileName;
		threadName = threadname;
		FileType = filetype;
		localdirectory = LocalDirectory;
	}

	public void run() {
		 ProgressDisplay progress = new ProgressDisplay(null, "Downloading");
			progress.setVisible(true);
			progress.setAlwaysOnTop(true);
			progress.setMaximum(dataSize);
		try {
			ServerSocket servsock = new ServerSocket(7370);
			File myFile = new File(localdirectory);
		    //("In Receiver: About to run: "+localdirectory);
		    progress.setValue(10000);
		    if(!myFile.exists()){
		    	new File(localdirectory).mkdirs();
		 		//(localdirectory+ " now exists.");
		    }
		    File outputfile = new File(localdirectory+"\\"+filename);
		    //(outputfile+ " now exists.");
		    Socket sock = servsock.accept();
		    progress.setValue(20000);
		    byte[] mybytearray = new byte[dataSize];
		    InputStream is = sock.getInputStream();
		    FileOutputStream fos = new FileOutputStream(outputfile);
		    BufferedOutputStream bos = new BufferedOutputStream(fos);
		    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
		    current = bytesRead;
		    do {
	             bytesRead =
	                is.read(mybytearray, current, (mybytearray.length-current));
	             if(bytesRead >= 0) current += bytesRead;
	             progress.setValue(current);
	          } while(bytesRead > -1);
		    bos.write(mybytearray, 0, current);
		    progress.setValue(dataSize);
		    progress.setVisible(false);
		    bos.flush();
	          System.out.println("File " + myFile
	              + " downloaded (" + current + " bytes read)");
		    bos.close();
		    sock.close();
		    
		    } catch (IOException e) {
				e.printStackTrace();
			}
		JOptionPane.showMessageDialog(null, "The file has been acquired.");
		viewFiles();
	}

	public void start (){      
		if (t == null){        
			t = new Thread (this, threadName);         
			t.start ();      
			}   
		} 
	
	public void viewFiles(){
		File LocalLocation = new File(localdirectory+"\\"+filename);
		if(!LocalLocation.exists()){
			JOptionPane.showMessageDialog(null, "This file was not downloaded successfully, this may be due to server error.");
		}else{
			   switch(FileType.toString()){
	    	   case "Audio":
	    		   try {
						Process P = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+LocalLocation);
		    		    P.waitFor();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    		   //("Done");
	    		   break;
	    	   case "Video":
	    		   try {
						Process P = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+LocalLocation);
		    		    P.waitFor();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    		   //("Done");
	    		   break;
	    	   case "Document":
				try {
					Process P = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+LocalLocation);
	    		    P.waitFor();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		   //("Done");
	    		   break;
	    	   case "Image":
	    		   try {
						Process P = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+LocalLocation);
		    		    P.waitFor();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    		   //("Done");
	    		   break;
	    	   default:
	    		   break;
	    	   }
		}
	}
}
