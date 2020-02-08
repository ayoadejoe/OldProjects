package registration;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;


public class ImageFilter extends FileFilter {

	 //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }else{
        	 JOptionPane.showMessageDialog(null, "File not found in the directory.", "File Format Error", JOptionPane.ERROR_MESSAGE);
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.tiff) ||
                extension.equals(Utils.tif) ||
                extension.equals(Utils.gif) ||
                extension.equals(Utils.jpeg) ||
                extension.equals(Utils.jpg) ||
                extension.equals(Utils.pdf) ||
                extension.equals(Utils.png)) {
                    return true;
            } else {
            	 JOptionPane.showMessageDialog(null, "You are to load Documents and Images only.", "File Format Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Just Images";
    }
}