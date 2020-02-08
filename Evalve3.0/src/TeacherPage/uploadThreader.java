package TeacherPage;

import java.io.File;

import javax.swing.JOptionPane;

import baseGui.ProgressDisplay;

public class uploadThreader {

	public uploadThreader(String filename, String SubClaz, String fileloc, String fileType) {
		File myFile = new File (fileloc);
        if(myFile.length() >= 100000000){
      	  JOptionPane.showMessageDialog(null, "You can not load files above 100MB", "!OVERSIZE!", 
      			  JOptionPane.ERROR_MESSAGE);
        }else if((myFile.length() <= 0)){
        	 JOptionPane.showMessageDialog(null, "This file has issues. Check the file.", "!PROBABLE CORRUPTION ERROR!", 
         			  JOptionPane.ERROR_MESSAGE);
        }else{
		UploadNamer f = new UploadNamer(SubClaz, filename, "Namer", fileType);
		f.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Upload e = new Upload("upload", fileloc);
		e.start();
        }
	}

}
