package espTest1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EspDerby {
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
	
	public EspDerby() {
	}
	
	String createAddressTable(){
		try {
			Class.forName(DERBY_EMBEDDED_DRIVER);
			String connectString = DERBY_URL+DATABASE_TO_USE+DERBY_ATTRIBUTE+DERBY_USERNAME+DERBY_PASSWORD;
			
			 derbyconn = DriverManager.getConnection(connectString);
			 stmt = derbyconn.createStatement();
			 String Sql = "CREATE TABLE AddressTable (ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
			 		" SSID VARCHAR(20), IP VARCHAR(20), PASSWORD VARCHAR(20), LASTCONN VARCHAR(20), NoCONN INT, " +
				 		"CONSTRAINT primary_key PRIMARY KEY (ID))";
			 
			 stmt.executeUpdate(Sql);
	         if (stmt != null){ stmt.close();}
	         if (derbyconn != null){
	                derbyconn.close();
	            }       
	         //.println("Table Created!");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Address Table Created";
	}
	
	
	boolean createDataTable(String Table){
		try {
			Class.forName(DERBY_EMBEDDED_DRIVER);
			String connectString = DERBY_URL+DATABASE_TO_USE+DERBY_ATTRIBUTE+DERBY_USERNAME+DERBY_PASSWORD;
			String Sql ="";
			 derbyconn = DriverManager.getConnection(connectString);
			 stmt = derbyconn.createStatement();
			 if(Table.equals("RawDataTable")){
				 Sql = "CREATE TABLE RawDataTable (ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
			 		" BlueTankLevel INT, GrayTankLevel INT, BigGenFuel LONG VARCHAR, SmallGenFuel LONG VARCHAR, BigGenOffOn LONG VARCHAR, " +
			 		"SmallGenOffOn LONG VARCHAR, BigGenFillTime LONG VARCHAR, SmallGenFillTime LONG VARCHAR, BigTimeMins INT, SmallTimeMins INT," +
			 		" ThingTime VARCHAR(200), ComputerTime VARCHAR(200), CONSTRAINT ID PRIMARY KEY (ID))";
				 //.println(Sql);
			 }else{};
			 stmt.executeUpdate(Sql);
	         if (stmt != null){ stmt.close();}
	         if (derbyconn != null){
	                derbyconn.close();
	            }       
	         //.println("RawDataTable Created!");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	boolean dropTable(String table){
		try {
			Class.forName(DERBY_EMBEDDED_DRIVER);
			String connectString = DERBY_URL+DATABASE_TO_USE+DERBY_ATTRIBUTE+DERBY_USERNAME+DERBY_PASSWORD;
			
			 derbyconn = DriverManager.getConnection(connectString);
			 stmt = derbyconn.createStatement();
			 String Sql = "drop table "+table;
			 
			 stmt.executeUpdate(Sql);
	         if (stmt != null){ stmt.close();}
	         if (derbyconn != null){
	                derbyconn.close();
	            }       
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	boolean enterRawData(int bleu, int noir, List<Object>bigData , List<Object> smallData, String bigOffOn, String smallOffOn, 
			String bigFill, String smallFill, int bigTime, int smallTime, String ThingTime ){
		try {
			Class.forName(DERBY_EMBEDDED_DRIVER);
			String connectString = DERBY_URL+DATABASE_TO_USE+DERBY_ATTRIBUTE+DERBY_USERNAME+DERBY_PASSWORD;
			
			 derbyconn = DriverManager.getConnection(connectString);
			 stmt = derbyconn.createStatement();
			 
			 String Sql = "INSERT INTO RawDataTable (BlueTankLevel, GrayTankLevel, BigGenFuel, SmallGenFuel, BigGenOffOn, " +
			 		"SmallGenOffOn, BigGenFillTime, SmallGenFillTime, BigTimeMins, SmallTimeMins, ThingTime, ComputerTime) " +
			 		"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 
			 /*Blob blob = derbyconn.createBlob();
			 blob.setBytes(3, getData(blob, smallData));
			blob.setBytes(4, getData(blob, bigData));
				*/
			    
			 	insertStatement = derbyconn.prepareStatement(Sql);
				insertStatement.setObject(1, bleu);
				insertStatement.setObject(2, noir);
				insertStatement.setObject(3, getStringed(bigData));
				insertStatement.setObject(4, getStringed(smallData));
				insertStatement.setObject(5, bigOffOn);
				insertStatement.setObject(6, smallOffOn);
				insertStatement.setObject(7, bigFill);
				insertStatement.setObject(8, smallFill);
				insertStatement.setObject(9, bigTime);
				insertStatement.setObject(10, smallTime);
				insertStatement.setObject(11, ThingTime);
				insertStatement.setObject(12, generalFormat.format(new Date()));
				insertStatement.executeUpdate();
				insertStatement.closeOnCompletion();
				
	         if (stmt != null){ stmt.close();}
	         if (derbyconn != null){
	                derbyconn.close();
	            }       
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private Object getStringed(List<Object> data) {
		String val="";
		for(int y= 0; y<data.size(); y++){
			 val+= data.get(y)+">";
		 }
		//.println(val);
		return val;
	}

	private byte[] getData(Blob blob, List<Object>data) {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bos);
			 oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    byte[] bytes = bos.toByteArray();
	   //.println(bytes.length);
		return  bytes;
	}

	boolean updateRawData(int bleu, int noir, List<Object> bigData, List<Object> smallData, String bigOffOn, String smallOffOn, 
			String bigFill, String smallFill, int bigTime, int smallTime, String ThingTime, int ID ){
		try {
			Class.forName(DERBY_EMBEDDED_DRIVER);
			String connectString = DERBY_URL+DATABASE_TO_USE+DERBY_ATTRIBUTE+DERBY_USERNAME+DERBY_PASSWORD;
			
			 derbyconn = DriverManager.getConnection(connectString);
			 stmt = derbyconn.createStatement();
			 
			 String sql = "UPDATE RawDataTable SET	BlueTankLevel = ?, GrayTankLevel = ?, BigGenFuel = ?, SmallGenFuel = ?,"
						+"BigGenOffOn = ?,	SmallGenOffOn = ?, BigGenFillTime = ?," +	"SmallGenFillTime = ?,BigTimeMins = ?, " +
								"SmallTimeMins = ?, ThingTime = ?, ComputerTime = ? WHERE ID = ?";
						updateStatement = derbyconn.prepareStatement(sql);
						
						updateStatement.setObject(1, bleu);
						updateStatement.setObject(2, noir);
						updateStatement.setObject(3, getStringed(bigData));
						updateStatement.setObject(4, getStringed(smallData));
						updateStatement.setObject(5, bigOffOn);
						
						updateStatement.setObject(6, smallOffOn);
						updateStatement.setObject(7, bigFill);
						updateStatement.setObject(8, smallFill);
						updateStatement.setObject(9, bigTime);
						updateStatement.setObject(10, smallTime);	
						
						updateStatement.setObject(11, ThingTime);
						updateStatement.setObject(12, generalFormat.format(new Date()));
						updateStatement.setObject(13, ID);	
			 
						updateStatement.executeUpdate();
						updateStatement.closeOnCompletion();
						
			 if (stmt != null){ stmt.close();}
	         if (derbyconn != null){
	                derbyconn.close();
	            }       
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	ProcessData inicialRaw(){
		ProcessData data = new ProcessData();
		try {
			Class.forName(DERBY_EMBEDDED_DRIVER);
			String connectString = DERBY_URL+DATABASE_TO_USE+DERBY_ATTRIBUTE+DERBY_USERNAME+DERBY_PASSWORD;
			
			derbyconn = DriverManager.getConnection(connectString);
			
			 if (derbyconn != null) {
	            }else{
	            	//.println("could not connect");
	            }
			 
			 stmt = derbyconn.createStatement();
			 
			 String Sql = "SELECT * FROM RawDataTable ORDER BY ID DESC FETCH FIRST ROW ONLY";
			 
			 try{
				 checkResult = stmt.executeQuery(Sql);
				 ResultSetMetaData meta = checkResult.getMetaData();
			 }catch(Exception f){
				 //.println(f.getMessage());
				 return null;
			 }
			 
				if(checkResult != null && checkResult.next() ){
					data.setID(checkResult.getInt(1));
					data.setBleu(checkResult.getInt(2));
					data.setNoir(checkResult.getInt(3));
					String set = checkResult.getString(4);
					if(set != null)data.setBigLevel(getStringtoList(set));
					String get = checkResult.getString(5);
					if(get != null)data.setSmallLevel(getStringtoList(get));
					data.setBigOffOn(checkResult.getString(6));
					data.setSmallOffOn(checkResult.getString(7));
					data.setBigFill(checkResult.getString(8));
					data.setSmallFill(checkResult.getString(9));
					data.setBigTime(checkResult.getInt(10));
					data.setSmallTime(checkResult.getInt(11));
					data.setThingTime(checkResult.getString(12));
					data.setComputerTime(checkResult.getString(13));
				}else{
					data.setID(0);
					data.setBleu(0);
					data.setNoir(0);
					data.setBigLevel(null);
					data.setSmallLevel(null);
					data.setBigOffOn("No Data");
					data.setSmallOffOn("No Data");
					data.setBigFill("No Data");
					data.setSmallFill("No Data");
					data.setBigTime(0);
					data.setSmallTime(0);
					data.setThingTime("No data");
					data.setComputerTime(new Date().toString());
					try{
						if(!createDataTable("RawDataTable"));//.println("No Data available yet");
					}catch(Exception c){
						//.println("passed here");
						c.printStackTrace();
						return null;
					}
				}
			 
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	private List<Object> getStringtoList(String string) {
		List<String> info = Arrays.asList(string.split(">"));
		List<Object> vals = new ArrayList<Object>();
		for(int d=0; d<info.size(); d++){
			try{
			vals.add(Integer.parseInt(info.get(d)));
			}catch(NumberFormatException g){
				vals.add(info.get(d));
			}
		}
		return vals;
	}

	private List<Object> getValue(byte[] bs) {
		
		ByteArrayInputStream bb = new ByteArrayInputStream(bs);
		List<Object> out = null;
		ObjectInputStream oo;
		try {
			oo = new ObjectInputStream(bb);
			out = (List<Object>) oo.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return out;
	}

	ResultSet accessDerby(String Table){
		try {
			Class.forName(DERBY_EMBEDDED_DRIVER);
			String connectString = DERBY_URL+DATABASE_TO_USE+DERBY_ATTRIBUTE+DERBY_USERNAME+DERBY_PASSWORD;
			
			derbyconn = DriverManager.getConnection(connectString);
			
			 if (derbyconn != null) {
	            }else{
	            	//.println("could not connect");
	            }
			 //String Sql = "select * from sys.systables";
			 String Sql = "select * from sys.systables where TABLETYPE='T'";
			 stmt = derbyconn.createStatement();
			 checkResult = stmt.executeQuery(Sql);
			 
			 Sql = "select * from "+Table;
			 checkResult = stmt.executeQuery(Sql);
			 
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			if(e.getMessage().contains("'RAWDATATABLE' does not exist")){
				if(createDataTable("RawDataTable")){
					//.println("Raw Data Table created");
					return accessDerby("RAWDATATABLE");
				}
			}
			
			//e.printStackTrace();
			return null;
		}
		
		return checkResult;
	}
	
	boolean closeDerby(){
		if(stmt != null){ 
			try {
			stmt.close();
		} catch (SQLException e) {
			return false;
		}}
		
        if (derbyconn != null){try {
			derbyconn.close();
		} catch (SQLException e) {
			return false;
		} }   
		return true;
	}

	public static void main(String[] args) throws SQLException {
		EspDerby derby =  new EspDerby();
		derby.dropTable("RawDataTable");
		/*//.println(derby.createAddressTable());
		ResultSet sett = derby.accessDerby("AddressTable");
			while(sett.next()){
				//.println("functional:");
				//.println(sett.getString("SSID"));
			}
			if(derby.closeDerby()){
				//.println("close");;
			}else{//.println("not closed");}
			
			new EspMain();*/
		//if(derby.dropTable("AddressTable")){//.println("Table has been dropped");}
		//accessDerby access = new accessDerby();
		//if(derby.enterAddress("cityfm", "2thatsmycityy5", "192.168.7.198", 3)){	//.println("Process executed...");}
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
	
}

/* while(checkResult.next()){
for (int x = resultNo.getColumnCount(); x>0; x--)
//.println(x+". "+ resultNo.getColumnName(x)+"-"+checkResult.getObject(x));
ResultSetMetaData resultNo = checkResult.getMetaData();
while(checkResult.next()){
for (int x = resultNo.getColumnCount(); x>0; x--)
//.println(x+". "+ resultNo.getColumnName(x)+"-"+checkResult.getObject(x));
}

}*/

