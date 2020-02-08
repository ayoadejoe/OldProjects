package registration;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;


public class DocFileView extends FileView {
	 	ImageIcon jpgIcon = Utils.createImageIcon("JPG-32.png");
	    ImageIcon gifIcon = Utils.createImageIcon("GIF-32.png");
	    ImageIcon tiffIcon = Utils.createImageIcon("TIF-32.png");
	    ImageIcon pngIcon = Utils.createImageIcon("PNG-32.png");
	    ImageIcon docxIcon = Utils.createImageIcon("DOC-32.png");
	    ImageIcon docIcon = Utils.createImageIcon("DOC-32.png");
	    ImageIcon pdfIcon = Utils.createImageIcon("PDF-32.png");
	    ImageIcon txtIcon = Utils.createImageIcon("TXT-32.png");
	    ImageIcon pptxIcon = Utils.createImageIcon("PPT-32.png");
	    ImageIcon xlsxIcon = Utils.createImageIcon("XLS-32.png");

	    public String getName(File f) {
	        return null; //let the L&F FileView figure this out
	    }

	    public String getDescription(File f) {
	        return null; //let the L&F FileView figure this out
	    }

	    public Boolean isTraversable(File f) {
	        return null; //let the L&F FileView figure this out
	    }

	    public String getTypeDescription(File f) {
	        String extension = Utils.getExtension(f);
	        String type = null;

	        if (extension != null) {
	            if (extension.equals(Utils.jpeg) ||
	                extension.equals(Utils.jpg)) {
	                type = "JPEG Image";
	            } else if (extension.equals(Utils.gif)){
	                type = "GIF Image";
	            } else if (extension.equals(Utils.tiff) ||
	                       extension.equals(Utils.tif)) {
	                type = "TIFF Image";
	            } else if (extension.equals(Utils.png)){
	                type = "PNG Image";
	            }else if (extension.equals(Utils.pdf)){
	                type = "PDF Image";
	            } else if (extension.equals(Utils.doc) ||
	                       extension.equals(Utils.docx)) {
	                type = "DOC Image";
	            } else if (extension.equals(Utils.pptx)){
	                type = "PPT Image";
	            }else if (extension.equals(Utils.xlsx)) {
	                type = "XLS Image";
	            } else if (extension.equals(Utils.txt)){
	                type = "TXT Image";
	            }
	        }
	        return type;
	    }

	    public Icon getIcon(File f) {
	        String extension = Utils.getExtension(f);
	        Icon icon = null;

	        if (extension != null) {
	            if (extension.equals(Utils.jpeg) ||
	                extension.equals(Utils.jpg)) {
	                icon = jpgIcon;
	            } else if (extension.equals(Utils.gif)) {
	                icon = gifIcon;
	            } else if (extension.equals(Utils.tiff) ||
	                       extension.equals(Utils.tif)) {
	                icon = tiffIcon;
	            } else if (extension.equals(Utils.png)) {
	                icon = pngIcon;
	            }else if (extension.equals(Utils.pdf)) {
	                icon = pdfIcon;
	            } else if (extension.equals(Utils.doc) ||
	                       extension.equals(Utils.docx)) {
	                icon = docIcon;
	            } else if (extension.equals(Utils.pptx)) {
	                icon = pptxIcon;
	            }else if (extension.equals(Utils.xlsx)) {
	                icon = xlsxIcon;
	            }else if (extension.equals(Utils.txt)) {
	                icon = txtIcon;
	            }
	        }
	        return icon;
	    }
	}