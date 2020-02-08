package Administration;

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

public class BasicInfoDatabase {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String USERNAME= null;
    static final String PASSWORD= null;
    
    private PrintWriter eachLine = null;
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	private static File file3 = new File("Checks.txt");
	private static File file = new File("TChecks.txt");
	public void loadStudent(String name, String address, String phone1, String phone2,
			String email, String password, String username, String birthday,
			String admission, String class1, String gender, String comment, String picture) {
		JOptionPane.showMessageDialog(null, "Name:"+name+"- Username:"+username);
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
		
			String DATABASE_URL = "jdbc:mysql://"+IP+":"+port+"/GeneralRegister"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    		Statement stmt = connection.createStatement( );
    		String pClass = null;
    		String sql2 = "INSERT INTO Students ( NAME, ADDRESS, PHONE1, PHONE2, EMAIL, PASSWORD," +
    				" USERNAME, BIRTHDAY, ADMITTED, CLASS, PRESENT_CLASS, GENDER, COMMENT, PASSPORTS, REGISTRATION_DATE) " +
					"VALUES ('"+name+"','"+address+"','"+phone1+"','"+phone2+"','"+email+"','"+password+"','"+username+
					"','"+birthday+"','"+admission+"','"+class1+"','"+pClass+"','"+gender+"','"+comment+"','"+picture+"',"+" NOW()"+");";
			stmt.executeUpdate(sql2);
		    	  stmt.close();                        
		          connection.close();                       
		}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"ERROR", JOptionPane.INFORMATION_MESSAGE );
			JOptionPane.showMessageDialog(null, "'Data entry not successfull' ", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
			return;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PersonSave(name,address,phone1,phone2,email,password,username,birthday,admission,class1,gender,comment);
	}
	
		
	

	public void loadTeacher(String name, String address, String phone1,
			String phone2, String email, String password, String username,
			String birthday, String admission, int salary, String gender,
			String comment, String Subject1, String Subject2, String Subject3
			, String Class1, String Class2, String Class3, String picture) {
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
		
			String DATABASE_URL = "jdbc:mysql://"+IP+":"+port+"/GeneralRegister"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    		Statement stmt = connection.createStatement( );
    		String pClass = null;
    		String sql2 = "INSERT INTO Teachers ( NAME, ADDRESS, PHONE1, PHONE2, EMAIL, PASSWORD," +
    				" USERNAME, BIRTHDAY, ADMITTED, CLASS1, CLASS2, CLASS3, SUBJECT1, SUBJECT2, SUBJECT3," +
    				" GENDER, SALARY, PASSPORT, COMMENT, REGISTRATION_DATE) " +
					"VALUES ('"+name+"','"+address+"','"+phone1+"','"+phone2+"','"+email+"','"+password+"','"+username+
					"','"+birthday+"','"+admission+"','"+Class1+"','"+Class2+"','"+Class3+"','"+Subject1
					+"','"+Subject2+"','"+Subject3+"','"+gender+"','"+salary+"','"+picture+"','"+comment+"',"+" NOW()"+");";

			stmt.executeUpdate(sql2);
		    	  stmt.close();                        
		          connection.close();                       
		}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"ERROR", JOptionPane.INFORMATION_MESSAGE );
			JOptionPane.showMessageDialog(null, "'Data entry not successfull' ", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
			return;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		TeacherSave(name,address,phone1,phone2,email,password,username,birthday,admission,Class1,Class2, Class3, Subject1, 
				Subject2, Subject3, gender,salary, picture, comment);
	}
	
	private void PersonSave(String Name,String Address,String Phone1,String Phone2,String Email,String Password,String IDNo,
			String Birthday,String Admission,String Class,String Gender,String Comment) {
		try{
			
		  fw = new FileWriter(file3, true);
	      bw = new BufferedWriter(fw);
	      eachLine = new PrintWriter(bw);
	      eachLine.append(Name+"`"+Address+"`"+Phone1+"`"+Phone2+"`"+Email+"`"+Password+"`"+IDNo+
					"`"+Birthday+"`"+Admission+"`"+Class+"`"+Gender+"`"+Comment+"\n");
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
	}
	
	private void TeacherSave(String name, String address, String phone1,
			String phone2, String email, String password2, String username2,
			String birthday, String admission, String class1, String class2,
			String class3, String subject1, String subject2, String subject3,
			String gender, int salary, String picture, String comment) {
		try{
			
		  fw = new FileWriter(file, true);
	      bw = new BufferedWriter(fw);
	      eachLine = new PrintWriter(bw);
	      eachLine.append(name+"`"+address+"`"+phone1+"`"+phone2+"`"+email+"`"+password2+"`"+username2+"`"+birthday+"`"+
					"`"+admission+"`"+class1+"`"+class2+"`"+"`"+class3+"`"+subject1+"`"+subject2+"`"+subject3+"`"+
					"`"+gender+"`"+salary+"`"+picture+"`"+"`"+comment+"\n");
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
	}
}
