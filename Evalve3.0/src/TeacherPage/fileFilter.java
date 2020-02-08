package TeacherPage;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;


public class fileFilter extends FileFilter {
	String extension = null;
	 //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
        	return true;
        }

        extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.tiff) ||
                extension.equals(Utils.tif) ||
                extension.equals(Utils.gif) ||
                extension.equals(Utils.jpeg) ||
                extension.equals(Utils.jpg) ||
                
                extension.equals(Utils.doc) ||
                extension.equals(Utils.mp3) ||
                extension.equals(Utils.mp4) ||
                extension.equals(Utils.pdf) ||
                
                extension.equals(Utils.avi) ||
                extension.equals(Utils.pptx) ||
                extension.equals(Utils.xlsx) ||
                extension.equals(Utils.txt) ||
                
                extension.equals(Utils.gp3) ||
                extension.equals(Utils.wave) ||
                extension.equals(Utils.wma) ||
                
                extension.equals(Utils.png)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Image, Video(3gp, mp4, avi), Doc(docx, txt, pptx, xlsx, pdf), Audio(mp3, wave, wma)";
    }
    public String getDescription2() {
        return "You can only load certain formats. This is obviously not one of them. Please convert it.";
    }
    
    public String getNature(File fg) {
    	 extension = Utils.getExtension(fg);
         if (extension != null) {
             if (extension.equals(Utils.tiff) ||
                 extension.equals(Utils.tif) ||
                 extension.equals(Utils.gif) ||
                 extension.equals(Utils.jpeg) ||
                 extension.equals(Utils.png)||
                 extension.equals(Utils.jpg)){
            	 return "Image";
             }else if(
                 extension.equals(Utils.doc) ||
                 extension.equals(Utils.pptx) ||
                 extension.equals(Utils.xlsx) ||
                 extension.equals(Utils.txt)||
                 extension.equals(Utils.pdf)){
            	 return "Document";
             }else if(
            	 extension.equals(Utils.mp4) ||
                 extension.equals(Utils.avi) ||
                 extension.equals(Utils.gp3)
                 ){
            	 return "Video";
             }else if(
                 extension.equals(Utils.wave) ||
                 extension.equals(Utils.wma) ||
                 extension.equals(Utils.mp3) 
                 ) {
                     return "Audio";
             } else {
            	 JOptionPane.showMessageDialog(null, "You are attempting to load an unrecognised format. This file" +
            	 		" cannot be uploaded.", "INVALID FORMAT ERROR", JOptionPane.ERROR_MESSAGE);
            	 return "You can only load certain formats. This is obviously not one of them. Please convert it.";
             }
    }
		return null;}
}