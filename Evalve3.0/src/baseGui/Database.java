package baseGui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Database {
	private PrintWriter eachLine = null;
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String USERNAME= null;
    static final String PASSWORD= null;

	public void load(String ClassS, String Subject, String Examday, String Examtime, String Duration, String Terms, String Examiner,
			String Exam, int newNoQ, int pointing, int Alt, int pt, String ExamType,String EvalType) throws IOException {
		
		Scanner baseCheck;
		String datB = Subject;
			baseCheck = new Scanner(new File("DatabaseList.txt"));
			while(baseCheck.hasNextLine()){
				//(Subject+" == "+datB);
			     if(datB.equals(baseCheck.nextLine().trim())){
			        //("Database Exists");
			        loadConfirm(ClassS, Subject, Examday, Examtime, Duration, Terms, Examiner,
							Exam, newNoQ, pointing, Alt, pt, ExamType, EvalType );
			        //("At load Database confirm load");
			        return;
			      }
			}
			Scanner baseCheck2;
			String datB2 = Subject;
				baseCheck2 = new Scanner(new File("DatabaseList.txt"));
			while(baseCheck2.hasNextLine()){
			     if(datB2.equals(baseCheck2.nextLine().trim())){
			        //("Database Exists");
			        return;
			      }else{
			    	  createDataB(ClassS, Subject, Examday, Examtime, Duration, Terms, Examiner,
								Exam, newNoQ, pointing, Alt, pt, ExamType, EvalType );
			    	  //("At Create Database load");
			    	  return;
			      }
			}
			Scanner baseCheck1;
			String datB1 = Subject;
				baseCheck1 = new Scanner(new File("DatabaseList.txt"));
				//(Subject+" == "+datB1);
			 if(baseCheck1.hasNextLine()==false){
			        //("Empty Database List");
			        createDataB(ClassS, Subject, Examday, Examtime, Duration, Terms, Examiner,
							Exam, newNoQ, pointing, Alt, pt, ExamType, EvalType );
		    	  return;
			      }
	}   	  
		
	public void loadConfirm(String ClassS, String Subject, String Examday, String Examtime, String Duration, String Terms, String Examiner,
			String Exam, int newNoQ, int pointing, int Alt, int pt, String ExamType,String EvalType){
		try {
			Class.forName(JDBC_DRIVER);
		//("Connected");
    	Connection connection;
    	Scanner codeRetriever = new Scanner(new File("data.ini"));
		while (codeRetriever.hasNext()){
			String USERNAME = codeRetriever.next();
			String PASSWORD = codeRetriever.next();
			String IP = codeRetriever.next();
			int port = Integer.parseInt(codeRetriever.next());
		
			String DATABASE_URL = "jdbc:mysql://"+IP+":"+port+"/"+Subject; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    		Statement stmt = connection.createStatement( );
    		
    		File buff= new File("C:\\Users\\Lani\\Desktop\\Workspace\\EVALVE\\EVALVE\\Buffer.txt");
    		String path = buff.getAbsolutePath();
    		//(path);
    		String sql2 = "LOAD DATA LOCAL INFILE'"+ "Buffer.txt"+"'INTO TABLE "+ClassS+" FIELDS TERMINATED BY '`';";
			stmt.executeUpdate(sql2);
			if (buff.exists()){
				buff.delete();
				//("Data Entered Successfully.");
			}else{
				System.err.println("I cannot find '"+buff+ "'('" + buff.getAbsolutePath()+"')");
			}                                           
		    	  stmt.close();                        
		          connection.close();                       
		}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public void createDataB(String ClassS, String Subject, String Examday, String Examtime, String Duration, String Terms, String Examiner,
			String Exam, int newNoQ, int pointing, int Alt, int pt, String ExamType,String EvalType){
	//("Check: Database does not Exists");
	  try {
		Class.forName(JDBC_DRIVER);
	//(Subject+ " :Connected for new database.");
	Connection connection;
	Scanner codeRetriever = new Scanner(new File("data.ini"));
	while (codeRetriever.hasNext()){
		String USERNAME = codeRetriever.next();
		String PASSWORD = codeRetriever.next();
		String IP = codeRetriever.next();
		int port = Integer.parseInt(codeRetriever.next());
		String DATABASE_URL = "jdbc:mysql://"+IP+":"+port+"/maths"; 
		connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		Statement stmt = connection.createStatement( );
		String createsql = "CREATE DATABASE "+Subject+";";
		stmt.executeUpdate(createsql);
		String DATABASE_URL2 = "jdbc:mysql://"+IP+":"+port+"/"+Subject; 
		connection = DriverManager.getConnection(DATABASE_URL2, USERNAME, PASSWORD);
		//("Connection to new database "+Subject);

		for (int d = 1; d<=3; d++){
			String clas = "jss"+d;
		String createTables = "CREATE TABLE `"+Subject+"`.`"+clas+"` (`Questions` VARCHAR(200) NULL,`OptionA` VARCHAR(100) NULL," +
				"`OptionB` VARCHAR(100) NULL,`OptionC` VARCHAR(100) NULL,`OptionD` VARCHAR(100) NULL," +
				"`OptionE` VARCHAR(100) NULL,`Answer` VARCHAR(2) NULL,`Diagram` VARCHAR(45) NULL," +
				"`ExamDate` VARCHAR(100) NULL,`ExamTime` VARCHAR(100) NULL,`NoOfQuestions` INT(11) NULL," +
				"`Examiner` VARCHAR(25) NULL,`Pointing` INT(11) NULL,`Duration` VARCHAR(45) NULL," +
				"`Term` VARCHAR(11) NULL,`EvalType` VARCHAR(20) NULL,`ExamType` VARCHAR(20) NULL, `No` INT(100) NOT NULL AUTO_INCREMENT,PRIMARY KEY (`No`));;";
		
		stmt.executeUpdate(createTables);
		}
		for (int d = 1; d<=3; d++){
			String clas = "sss"+d;
		String createTables = "CREATE TABLE `"+Subject+"`.`"+clas+"` (`Questions` VARCHAR(200) NULL,`OptionA` VARCHAR(100) NULL," +
				"`OptionB` VARCHAR(100) NULL,`OptionC` VARCHAR(100) NULL,`OptionD` VARCHAR(100) NULL," +
				"`OptionE` VARCHAR(100) NULL,`Answer` VARCHAR(2) NULL,`Diagram` VARCHAR(10) NULL," +
				"`ExamDate` VARCHAR(100) NULL,`ExamTime` VARCHAR(100) NULL,`NoOfQuestions` INT(11) NULL," +
				"`Examiner` VARCHAR(25) NULL,`Pointing` INT(11) NULL,`Duration` VARCHAR(45) NULL," +
				"`Term` VARCHAR(11) NULL,`EvalType` VARCHAR(20) NULL,`ExamType` VARCHAR(20) NULL,`No` INT(100) NOT NULL AUTO_INCREMENT,PRIMARY KEY (`No`));;";
		stmt.executeUpdate(createTables);
		}
		File dataCheck = new File("DatabaseList.txt");
		if(dataCheck.canWrite()){
		try{
		fw = new FileWriter(dataCheck, true);
      bw = new BufferedWriter(fw);
      eachLine = new PrintWriter(bw);
      eachLine.append("\n");
      eachLine.append(Subject);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Buffer file not found.");
			e.printStackTrace();
		} catch (NoSuchElementException e4) {
			JOptionPane.showMessageDialog(null, e4.getMessage());
			e4.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}		
		finally{
	        try{
		           if( eachLine != null ){
		              eachLine.close(); // Will close bw and fw too
		           }
		           else if( bw != null ){
		              bw.close(); // Will close fw too
		           }
		           else if( fw != null ){
		              fw.close();
		           }
		           else{
		        	   JOptionPane.showMessageDialog(null, "Get the Software Architect" +
		        	   		" on 08033757577. Buffer Runtime failure");
		           }
		        }
		        catch( IOException e ){
		        	JOptionPane.showMessageDialog(null, "Get the Software Architect" +
		        	   		" on 08033757577. Buffer Runtime failure");
		        }
		     }
		}else{
			JOptionPane.showMessageDialog(null,"Buffer is not Writable.");
		}
      //(Subject+" Tables created successfully");
	    	  stmt.close();                        
	          connection.close(); 
	}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	  loadConfirm(ClassS, Subject, Examday, Examtime, Duration, Terms, Examiner,
				Exam, newNoQ, pointing, Alt, pt, ExamType, EvalType );
}

}
