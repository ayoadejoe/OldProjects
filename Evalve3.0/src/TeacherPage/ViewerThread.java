package TeacherPage;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import baseGui.BinaryNameClient;
import baseGui.BinaryReceiveClient;

public class ViewerThread {

	public ViewerThread(String ServerDirectory, String filetype, String filename, String LocalDirectory) {
		File LocalLocation = new File(LocalDirectory+"\\"+filename);
		if(!LocalLocation.exists()){
			
		BinaryNameClient sendadd = new BinaryNameClient(ServerDirectory, "send", filename);
		//("Sent File name");
		//("About to receive File");
		
		BinaryReceiveClient download = new BinaryReceiveClient(LocalDirectory, filetype, filename, "receivethread");
		download.start();
		}
		
	}
}
