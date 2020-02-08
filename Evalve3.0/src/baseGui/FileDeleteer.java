package baseGui;
import java.io.File;
import java.io.IOException;


public class FileDeleteer {
	private static File file1 = new File("register.evl");
	private static File file2 = new File("Tregister.evl");
	public FileDeleteer () 	{
		if (file1.exists() && file2.exists()){
			file1.delete();
			file2.delete();
		}
		File directory = new File("C:/EvalveInfo");
    	//make sure directory exists
    	if(!directory.exists()){
           //("Directory does not exist.");
        }else{
           try{
               delete(directory);
           }catch(IOException e){
               e.printStackTrace();
               System.exit(0);
           }
        }
		return;

	}
	 public static void delete(File file)
		    	throws IOException{
		 
		    	if(file.isDirectory()){
		 
		    		//directory is empty, then delete it
		    		if(file.list().length==0){
		 
		    		   file.delete();
		    		   //("Directory is deleted :+ file.getAbsolutePath());
		 
		    		}else{
		 
		    		   //list all the directory contents
		        	   String files[] = file.list();
		 
		        	   for (String temp : files) {
		        	      //construct the file structure
		        	      File fileDelete = new File(file, temp);
		        	      
		        	      //recursive delete
		        	     delete(fileDelete);
		        	   }
		 
		        	   //check the directory again, if empty then delete it
		        	   if(file.list().length==0){
		           	     file.delete();
		        	     //("Directory is deleted : " + file.getAbsolutePath());
		        	   }
		    		}
		 
		    	}else{
		    		//if file, then delete it
		    		file.delete();
		    		////("File is deleted : " + file.getAbsolutePath());
		    	}
		    }
}
