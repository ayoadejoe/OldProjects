package TeacherPage;

import java.io.File;

import javax.swing.ImageIcon;


public class Utils {
	 public final static String jpeg = "jpeg";
	    public final static String jpg = "jpg";
	    public final static String gif = "gif";
	    public final static String tiff = "tiff";
	    public final static String tif = "tif";
	    public final static String png = "png";
	    
	    public final static String doc = "docx";
	    public final static String pdf = "pdf";
	    public final static String xlsx = "xlsx";
	    public final static String mp3 = "mp3";
	    public final static String txt = "txt";
	    
	    public final static String pptx = "pptx";
	    public final static String mp4 = "mp4";
	    public final static String avi = "avi";
	    public final static String wma = "wma";
	    public final static String wave = "wave";
	    public final static String gp3 = "3gp";

	    /*
	     * Get the extension of a file.
	     */
	    public static String getExtension(File f) {
	        String ext = null;
	        String s = f.getName();
	        int i = s.lastIndexOf('.');

	        if (i > 0 &&  i < s.length() - 1) {
	            ext = s.substring(i+1).toLowerCase();
	        }
	        return ext;
	    }

	    /** Returns an ImageIcon, or null if the path was invalid. */
	    public static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = Utils.class.getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else {
	            System.err.println("Couldn't find file: " + path);
	            return null;
	        }
	    }
	}