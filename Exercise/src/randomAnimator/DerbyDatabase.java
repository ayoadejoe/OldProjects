package randomAnimator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DerbyDatabase {
	private static String DERBY_NETWORK_DRIVER = "org.apache.derby.jdbc.ClientDriver";
	private static String DERBY_EMBEDDED_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	
	private String DERBY_URL = "jdbc:derby:";
	private String DERBY_ATTRIBUTE = "create=true;";
	private String DATABASE_TO_USE = "myFirstDerbyDatabase;";
	private String DERBY_USERNAME = "user=joseph;";
	private String DERBY_PASSWORD = "password=glass;";
	
	private PreparedStatement checkStmt = null, insertStatement = null, updateStatement = null;
	private Statement stmt = null;
	private SimpleDateFormat generalFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private ResultSet checkResult;
	private Connection derbyconn;
	public DerbyDatabase() {
		try {
			Class.forName(DERBY_EMBEDDED_DRIVER);
			String connectString = DERBY_URL+DATABASE_TO_USE+DERBY_ATTRIBUTE+DERBY_USERNAME+DERBY_PASSWORD;
			
			derbyconn = DriverManager.getConnection(connectString);
			 if (derbyconn != null) {
	                System.out.println("Connected to database #1");
	            }else{
	            	System.out.println("could not connect");
	            }
			 
			 stmt = derbyconn.createStatement();
			 
			 String Sql = "CREATE TABLE FirstDbTable (ID INT, Name VARCHAR(20), City VARCHAR(20))";;
			 System.out.println("Table Created!");
			 stmt.executeUpdate(Sql);
			 
			 int id = 1; String restName = "Blessed Joseph"; String cityName = "Lagos";
	         stmt.execute("insert into FirstDbTable values (" +id + ",'" + restName + "','" + cityName +"')");
	         System.out.println("Data inserted");
	         
	         if (stmt != null){ stmt.close();}
	         if (derbyconn != null){
	                DriverManager.getConnection(connectString + ";shutdown=true");
	                derbyconn.close();
	            }       
	         
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//DerbyDatabase derby =  new DerbyDatabase();
		accessDerby access = new accessDerby();
	}

}


class accessDerby{
	private String DERBY_EMBEDDED_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private String DERBY_URL = "jdbc:derby:";
	private String DERBY_ATTRIBUTE = "create=true;";
	private String DATABASE_TO_USE = "myFirstDerbyDatabase;";
	private String DERBY_USERNAME = "user=joseph;";
	private String DERBY_PASSWORD = "password=glass;";
	private ResultSet checkResult;
	private Connection derbyconn;
	private Statement stmt = null;
	public accessDerby(){
		try {
			Class.forName(DERBY_EMBEDDED_DRIVER);
			
			String connectString = DERBY_URL+DATABASE_TO_USE+DERBY_ATTRIBUTE+DERBY_USERNAME+DERBY_PASSWORD;
			
			derbyconn = DriverManager.getConnection(connectString);
			
			 if (derbyconn != null) {
	                System.out.println("Connected to database #1");
	            }else{
	            	System.out.println("could not connect");
	            }
			 //String Sql = "select * from sys.systables";
			 String Sql = "select * from sys.systables where TABLETYPE='T'";
			 stmt = derbyconn.createStatement();
			 checkResult = stmt.executeQuery(Sql);
			 ResultSetMetaData resultNo = checkResult.getMetaData();
					 
			 while(checkResult.next()){
				 for (int x = resultNo.getColumnCount(); x>0; x--)
				 System.out.println(x+". "+ resultNo.getColumnName(x)+"-"+checkResult.getObject(x));
			 }
			
			 System.out.println(" ");
			 Sql = "select * from FirstDbTable";
			 checkResult = stmt.executeQuery(Sql);
			 resultNo = checkResult.getMetaData();
			 while(checkResult.next()){
				 for (int x = resultNo.getColumnCount(); x>0; x--)
				 System.out.println(x+". "+ resultNo.getColumnName(x)+"-"+checkResult.getObject(x));
			 }
			 
			 if(stmt != null){ stmt.close();}
	            if (derbyconn != null){derbyconn.close(); }           
			 
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
