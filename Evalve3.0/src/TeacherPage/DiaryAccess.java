package TeacherPage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DiaryAccess {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private Diary newDiary;
	private static String USERNAME= null;
    private static String PASSWORD= null;
	private String IP = null, Query = null;
	private String DATABASE_URL = null;
	private int port;
	private PrintWriter eachLine = null;
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	private File file = new File("diary.evl");
	private PrintStream clear;
	private int  week; private String SubCol;
	public DiaryAccess(int iDNo2, int sub, String username, String classz, String subject, String term, int w) {
		
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			clear = new PrintStream(file);
			clear.println("WEEK"+ "TOPIC" + "PLAN");
			clear.close();
			
		Class.forName(JDBC_DRIVER);
    	Connection connection;
    	Scanner codeRetriever = new Scanner(new File("data.ini"));
		if (codeRetriever.hasNext()){
			USERNAME = codeRetriever.next();
			PASSWORD = codeRetriever.next();
			IP = codeRetriever.next();
			port = Integer.parseInt(codeRetriever.next());
		}
		DATABASE_URL = "jdbc:mysql://"+IP+":"+port+"/diary"; 
		connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		Statement stmt = connection.createStatement( );
		ResultSet results = null; int r = 0;
		try{
		results = stmt.executeQuery("SELECT * FROM diary."+term+" where Class = '"+classz+"' and Subject = '"+subject+"';");
		}catch(SQLException e){
		}
		while (results.next()){
			r = 1;
			////("Accessing Database: "+week+"  , w = "+w);
			int week = results.getInt("Week");
			String topic = results.getString("Topic");	
			String plan = results.getString("PlanStatus");	
					try{
					fw = new FileWriter(file, true);
			        bw = new BufferedWriter(fw);
			        eachLine = new PrintWriter(bw);
			        eachLine.append(week+"<> "+topic+"<> "+plan+" \n");
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, file+" File not found.");
							e.printStackTrace();
						}
					try {
						eachLine.close();
						bw.close();
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		}
		
		if (r == 0){
			CreateNew(iDNo2, sub, username, classz, subject, term, w);
			return;
		}
		} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"file not found.");
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
 					"INFO", JOptionPane.INFORMATION_MESSAGE );
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	public void rewrite(int rowIndex, int columnIndex, Object oldvalue, Object newValue, Object subjt, Object cls, Object tm, int week) {
		try{
		oldvalue = oldvalue.toString().trim(); newValue = newValue.toString().trim(); tm = tm.toString().trim();
		subjt = subjt.toString().trim(); cls = cls.toString().trim();
		}catch(NullPointerException g){
			g.printStackTrace();
		}catch(ClassCastException f){
			f.printStackTrace();
		}
		try {
			Connection connection;
	    	Scanner codeRetriever = new Scanner(new File("data.ini"));
			if (codeRetriever.hasNext()){
				USERNAME = codeRetriever.next();
				PASSWORD = codeRetriever.next();
				IP = codeRetriever.next();
				port = Integer.parseInt(codeRetriever.next());
			}
				String DATABASE_URL = "jdbc:mysql://"+IP+":"+port+"/diary"; 
				
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			String UpdateQuery = null; PreparedStatement updateStatement = null;
		
		switch(columnIndex){
		case 0:
			UpdateQuery = "UPDATE diary."+tm+" SET Week = ? where Subject = ? and Class = ? and Week = ?;";
			updateStatement = connection.prepareStatement(UpdateQuery);
			updateStatement.setObject(1, newValue);
			updateStatement.setObject(2, subjt);
			updateStatement.setObject(3, cls);
			updateStatement.setObject(4, week);
			break;
		case 1:
			UpdateQuery = "UPDATE diary."+tm+" SET Topic = ? where Subject = ? and Class = ? and Week = ?;";
			updateStatement = connection.prepareStatement(UpdateQuery);
			updateStatement.setObject(1, newValue);
			updateStatement.setObject(2, subjt);
			updateStatement.setObject(3, cls);
			updateStatement.setObject(4, week);
			break;
		case 2:
			UpdateQuery = "UPDATE diary."+tm+" SET PlanStatus = ? where Subject = ? and Class = ? and Week = ?;";
			updateStatement = connection.prepareStatement(UpdateQuery);
			updateStatement.setObject(1, newValue);
			updateStatement.setObject(2, subjt);
			updateStatement.setObject(3, cls);
			updateStatement.setObject(4, week);
			break;
		default:
			break;
		}
			//JOptionPane.showMessageDialog(null, UpdateQuery +">> "+newValue+", "+subjt+", "+cls+", "+week);
			updateStatement.executeUpdate();
			updateStatement.closeOnCompletion();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"file not found.");
			e.printStackTrace();
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"ERROR", JOptionPane.INFORMATION_MESSAGE );
			e.printStackTrace();
		}	
	}
	
	public void CreateNew(int iDNo2, int sub, String username, String classz,
			String subject, String term, int w) {
		////("Creating New: "+week+"  , w = "+w);
		Date today = new Date(); String topic = " ? "; int wkk = 0;boolean plan = false;
		int year = Integer.parseInt(today.toLocaleString().substring(7, 11)); int year2 = year+1;
		String session = year+"/"+year2;
		try {
		Class.forName(JDBC_DRIVER);
    	Connection connection;
    	Scanner codeRetriever = new Scanner(new File("data.ini"));
		if (codeRetriever.hasNext()){
			USERNAME = codeRetriever.next();
			PASSWORD = codeRetriever.next();
			IP = codeRetriever.next();
			port = Integer.parseInt(codeRetriever.next());
		}
		DATABASE_URL = "jdbc:mysql://"+IP+":"+port+"/signinrecord"; 
		connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		Statement stmt = connection.createStatement( );
		ResultSet results = null;
		try{
		results = stmt.executeQuery("SELECT Weeks FROM signinrecord.termsignin where Term = '"+sub+"' and Session = '"+session+"';");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage()+"> Week not found.");
		}
		while (results.next()){
			week = results.getInt("Weeks");
			wkk = week;
		}
		
		while(week>0){
			
			if(w>1){
				topic = "X";
			}
			if(w>2){
				topic = "Enter Topic";
			}
		try{
			fw = new FileWriter(file, true);
	        bw = new BufferedWriter(fw);
	        eachLine = new PrintWriter(bw);
	        eachLine.append(week+"<> "+topic+"<> "+plan+" \n");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, file+" File not found.");
					e.printStackTrace();
				}
			try {
				eachLine.close();
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		week--;	
		}
		stmt.close();
		String plann = "false";
		DATABASE_URL = "jdbc:mysql://"+IP+":"+port+"/diary"; 
		connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		String UpdateQuery = null; PreparedStatement updateStatement = null;
		UpdateQuery = "INSERT INTO diary."+term+" (Class, Subject, Topic, Week, PlanStatus) VALUES (?, ?, ?, ?, ?);";
		while(wkk>0){
		updateStatement = connection.prepareStatement(UpdateQuery);
		updateStatement.setObject(1, classz);
		updateStatement.setObject(2, subject);
		updateStatement.setObject(3, topic);
		updateStatement.setObject(4, wkk);
		updateStatement.setObject(5, plann);
		updateStatement.executeUpdate();
		wkk--;
		}
		updateStatement.closeOnCompletion();
		} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"file not found.");
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
 					"INFO", JOptionPane.INFORMATION_MESSAGE );
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
	}

}

