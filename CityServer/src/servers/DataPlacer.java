package servers;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTextArea;

public class DataPlacer {
	private static String USERNAME = null;
	private static String PASSWORD = null;
    private static String DATABASE_URL = null;
    private static String IP = null;
    private static int PORT = 0;
    private JTextArea up;
    private Connection connection = null;
    private PreparedStatement checkStmt = null, insertStatement = null, updateStatement = null;
    private Statement stmt = null;
    private SimpleDateFormat generalFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private ResultSet checkResult;
	public void Data(String ip, int port, String username, String password, JTextArea updates) {
		IP = ip;
		PORT= port;
		USERNAME = username;
		PASSWORD = password;
		up = updates;
		up.append("\nProcessing query in Data");
	}
	
	public ArrayList FragmentRegister(ArrayList input){
		String column = input.get(2).toString();
		String code = input.get(3).toString();
		String table = input.get(4).toString();
		up.append("\nAccessing "+column+" for "+code);
		String DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/cityfame"; 
		 try {
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			String checksql = "select "+column+", Name from cityfame."+table+" where "+column+" = ?;";
			System.out.println(checksql);
			checkStmt = connection.prepareStatement(checksql);
			checkStmt.setObject(1, code);
			checkResult = checkStmt.executeQuery();
			input.clear();
			if(checkResult.next()){
				Object codeexist = checkResult.getObject(column);
				String name = checkResult.getString("Name");
				System.out.println(codeexist+" exists");
				up.append("\ndata received exists");
				input.add("Clash");	input.add(name);
			}else{
				up.append("\ndata received does not exists");
				input.add("New");
			}
		} catch (Exception e) {
			input.add("FragmentRegister Error");
			e.printStackTrace();
			return input;
		}
		return input;
	}
	
	public ArrayList NewRegistration(ArrayList input){
		 String computerused = null,table = null,staffid = null,surname = null,user = null ,othernames = null, dept = null,
				 pwd = null,homeadd = null, Phone1 = null, Phone2 = null, dob = null, empdate = null, gender = null, 
				 state = null,stateadd = null, nextOfKin = null, nokadd = null, designation = null, email = null, 
				 Salary = null;
		 
		 Object thumprint = null;
		 Object passport = null;
		 Object credentials = null;
		 
		 try{
		 computerused = input.get(0).toString();
		 table = input.get(2).toString();
		 staffid = input.get(3).toString();
		 surname = input.get(4).toString();
		 othernames = input.get(5).toString();
		 dept = input.get(6).toString();
		 user = input.get(7).toString();
		 pwd = input.get(8).toString();
		 homeadd = input.get(9).toString();
		 Phone1 = input.get(10).toString();
		 Phone2 = input.get(11).toString();
		 dob = input.get(12).toString();
		 empdate = input.get(13).toString();
		 gender = input.get(14).toString();
		 state = input.get(15).toString();
		 nextOfKin = input.get(16).toString();
		 designation = input.get(17).toString();
		 nokadd = input.get(18).toString();
		 thumprint = input.get(19);//	image form already. so just use
		 passport = input.get(20);//		image form already. so just use
		 Salary = input.get(21).toString();
		 credentials = input.get(22);
		 email = input.get(23).toString();
		 }catch(Exception d){
			 d.printStackTrace();
		 }
		 System.out.println("email="+email+" gender="+gender+" nokadd="+nokadd+" passport="+passport+" Sal="+Salary+" designation="+designation);
		 String name = surname+", "+othernames;
		 computerused = computerused+" on "+generalFormat.format(new Date());
		 
		 up.append("\n"+computerused+" accessing "+table+" for registration");
			String DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/cityfame"; 
			 try {
				input.clear();
				connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				String sql = "INSERT IGNORE INTO "+table+"(`StaffNo`,`Name`,`Department`,`Designation`,`Salary`," +
						"`Address`,`Email`,`DateOfBirth`,`EmploymentDate`,`Signaturecode`,`NextOfKin`,`NOKAdress`," +
						"`LastIncreDate`,`AllInfo`,`ProfilePic`,`Username`,`Password`,`ThumbPrint`,`LastPromotDate`," +
						"`Gender`,`Files`,`LastComputerUsed`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
						" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				System.out.println(sql);
				insertStatement = connection.prepareStatement(sql);
				insertStatement.setObject(1, staffid);
				insertStatement.setObject(2, name);
				insertStatement.setObject(3, dept);
				insertStatement.setObject(4, designation);
				insertStatement.setObject(5, Salary);
				insertStatement.setObject(6, homeadd);
				insertStatement.setObject(7, email);
				insertStatement.setObject(8, dob);
				insertStatement.setObject(9, empdate);
				insertStatement.setObject(10, pwd);
				insertStatement.setObject(11, nextOfKin);
				insertStatement.setObject(12, nokadd);
				insertStatement.setObject(13, generalFormat.format(new Date()));
				insertStatement.setObject(14, input);
				insertStatement.setObject(15, passport);
				insertStatement.setObject(16, user);
				insertStatement.setObject(17, pwd);
				insertStatement.setObject(18, thumprint);
				insertStatement.setObject(19, generalFormat.format(new Date()));
				insertStatement.setObject(20, gender);
				insertStatement.setObject(21, credentials);
				insertStatement.setObject(22, computerused);
				insertStatement.executeUpdate();
				insertStatement.closeOnCompletion();
				System.out.println("Newly inserted staff");
				input.add(name+" is now registered.");
				insertStatement.closeOnCompletion();
				up.setText(dept+" is now registered.");
			} catch (Exception e) {
				input.add("Register Error");
				e.printStackTrace();
				return input;
			}
		 
		return input;
	}

	public ArrayList checkStandards(ArrayList input) {
		//deptVector.add("^checkstandards"); deptVector.add("DeptID"); deptVector.add(DeptNo.getText().trim());deptVector.add("standards");
		String column = input.get(2).toString();
		String code = input.get(3).toString();
		String table = input.get(4).toString();
		up.append("\nAccessing "+column+" for "+code);
		String DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/cityfame"; 
		 try {
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			String checksql = "select "+column+", Department from cityfame."+table+" where "+column+" = ?;";
			System.out.println(checksql);
			checkStmt = connection.prepareStatement(checksql);
			checkStmt.setObject(1, code);
			checkResult = checkStmt.executeQuery();
			input.clear();
			if(checkResult.next()){
				Object codeexist = checkResult.getObject(column);
				String dept = checkResult.getString("Department");
				System.out.println(codeexist+" exists");
				up.append("\ndata received exists");
				input.add("Clash");	input.add(dept);
			}else{
				up.append("\ndata received does not exists");
				input.add("New");
			}
		} catch (Exception e) {
			input.add("Standards Error");
			e.printStackTrace();
			return input;
		}
		return input;
	}

	public ArrayList enterDept(ArrayList input) {
		 String computerused = null,table = null,deptID = null,superTop = null,dept = null ,head = null, email=null, numbers=null;
		 
		 Object complogo = null;
		 Object designation = null;
		 Object credentials = null;
		 Object deptlogo = null;
		 Object fileNames = null;
		 try{
		 computerused = input.get(0).toString();
		 table = input.get(2).toString();
		 deptID = input.get(3).toString();
		 superTop = input.get(4).toString();
		 dept = input.get(5).toString();
		 head = input.get(6).toString();
		 complogo = input.get(7);
		 designation = input.get(8);
		 credentials = input.get(9);
		 deptlogo = input.get(10);
		 email = input.get(11).toString();
		 numbers = input.get(12).toString();
		 fileNames = input.get(13).toString();
		 }catch(Exception d){
			 d.printStackTrace();
		 }
		 System.out.println("email="+email+" number="+numbers+" nokadd="+table+" head="+head+" deptlogo="+deptlogo+" designation="+designation);
		 computerused = computerused+" on "+generalFormat.format(new Date());
		 
		 up.append("\n"+computerused+" accessing "+table+" for registration");
			String DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/cityfame"; 
			 try {
				input.clear();
				connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

				String sql = "INSERT IGNORE INTO "+table+"(`DeptID`,`Super`,`Department`,`Designations`,`Head`," +
						"`DeptEmail`,`DeptNumbers`,`DepartmentFiles`,`DepartmentLogo`,`CompanyLogo`, `LastComputerUsed`, `DeptFileNames`) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				System.out.println(sql);
				insertStatement = connection.prepareStatement(sql);
				insertStatement.setObject(1, deptID);
				insertStatement.setObject(2, superTop);
				insertStatement.setObject(3, dept);
				insertStatement.setObject(4, designation);
				insertStatement.setObject(5, head);
				insertStatement.setObject(6, email);
				insertStatement.setObject(7, numbers);
				insertStatement.setObject(8, credentials);
				insertStatement.setObject(9, deptlogo);
				insertStatement.setObject(10, complogo);
				insertStatement.setObject(11, computerused);
				insertStatement.setObject(12, fileNames);
				insertStatement.executeUpdate();
				insertStatement.closeOnCompletion();
				System.out.println("Newly inserted Department");
				input.add(dept+" is now registered.");
				insertStatement.closeOnCompletion();
				up.setText(dept+" is now registered.");
			} catch (Exception e) {
				input.add("Dept Register Error");
				e.printStackTrace();
				return input;
			}
		 
		return input;
	}

	public ArrayList getStandards(ArrayList input) {
		//deptVector.add("^getstandards$"); deptVector.add("standards");deptVector.add("*"); //remember address is on position 0
		String table = input.get(2).toString();		String checksql = "";
		String column = input.get(3).toString();
		String where = input.get(4).toString();
		String name = input.get(0).toString();
		up.append("\nAccessing "+column+" for "+name);
		String DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/cityfame"; 
		 try {
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			if(column.equals("*")){
				checksql = "select "+column+" from cityfame."+table+" where Department = '"+where+"';";
			}else{
				checksql = "select "+column+" from cityfame."+table+";";
			}
			
			System.out.println(checksql);
			checkStmt = connection.prepareStatement(checksql);
			checkResult = checkStmt.executeQuery();
			int col  = checkStmt.getMetaData().getColumnCount();
			input.clear();	Object res = null;  byte[] v = null;
			if(column.equals("*")){
				while(checkResult.next()){
					for(int r= 1; col>=r; r++){
						System.out.println("col ="+r+" colname="+checkStmt.getMetaData().getColumnName(r)+
								" class="+checkStmt.getMetaData().getColumnClassName(r));
						if(r==34)System.out.println(checkResult.getObject(r));
						if(r==4||r==10||r==11||r==12||r==13||r==16||r==17||r==18||r==19){
							try{
							v = checkResult.getBytes(r);
							ByteArrayInputStream bb = new ByteArrayInputStream(v);
							ObjectInputStream oo = new ObjectInputStream(bb);
							res = (Vector) oo.readObject();
							}catch(Exception c){
								//c.printStackTrace();
								Vector rep = new Vector(); rep.add("empty field");
								res = rep;
							}
						}else{
							res = checkResult.getObject(r);
							if(res == null)res = "empty field";
							if(checkStmt.getMetaData().getColumnClassName(r).toString().contains("Integer")&checkResult.getObject(r)==null){
								System.out.println("Integer for:"+checkStmt.getMetaData().getColumnName(r));
								res = 0;
							}
						}
					
					input.add(res);
					}
				}
			}else{
			while(checkResult.next()){
				res = checkResult.getObject("Department");
				input.add(res);
			}
			}
			if(input.isEmpty()) input.add("No departments found");
		} catch (Exception e) {
			input.add("Standards Error");
			e.printStackTrace();
			return input;
		}
		return input;
	}

	public ArrayList getAll(ArrayList input) {
		//deptVector.add("address"); 
		//allDeptNos.add("&ALL&"); allDeptNos.add("standards");allDeptNos.add("*"); allDepts.add("DeptNumbers"); 
		String table = input.get(2).toString();
		String code = input.get(3).toString();
		String column = input.get(4).toString();
		byte[] v = null;
		up.append("\nAccessing "+column+" for "+code);
		String DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/cityfame"; 
		 try {
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			String checksql = "select "+column+" from cityfame."+table+";";
			System.out.println(checksql);
			checkStmt = connection.prepareStatement(checksql);
			checkResult = checkStmt.executeQuery();
			input.clear();	Object codeexist=null;
			while(checkResult.next()){
				if(column.equals("Designations")){
					v = checkResult.getBytes(column);
					ByteArrayInputStream bb = new ByteArrayInputStream(v);
					ObjectInputStream oo = new ObjectInputStream(bb);
					codeexist = (Vector) oo.readObject();
				}else{
				codeexist = checkResult.getObject(column);
				}
				System.out.println(codeexist+" exists");
				up.append("\ndata received exists");
				input.add(codeexist);
			}
		} catch (Exception e) {
			input = null;
			e.printStackTrace();
			return input;
		}
		return input;
	}

	public ArrayList updateStandards(ArrayList input) {
		String computerused = input.get(0).toString();
		computerused = computerused+" on "+generalFormat.format(new Date());
		System.out.println("Input received:"+input);
		 up.append("\n"+computerused+" accessing standards for registration");
			String DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/cityfame"; 
			String sql = "UPDATE `cityfame`.`standards` SET	`Super` = ?, `Department` = ?, `Designations` = ?,`Head` = ?,"
			+"`DeptEmail` = ?,	`DeptNumbers` = ?,`CompanyLogo` = ?," +	"`DepartmentLogo` = ?,`Descriptions` = ?, " +
					"`GeneralParameters` = ?,`SynergyParameters` = ?, `DepartmentFiles` = ?, `Appraiser` = ?," +
					"`PromotionChain` = ?,`Sanctions` = ?,	`Commendations` = ?, `Strengths` = ?,	`Weaknesses` = ?," +
					"`ExitSupport` = ?,`NeedsImprovement` = ?,`Average` = ?,`RoleModel` = ?,`Outstanding` = ?," +
					"`AboveAverage` = ?, `WarningCondition` = ?,`UseCondition` = ?, `IncrementCondition` = ?," +
					"`PromotionCondition` = ?, `DisengageCondition` = ?,`ConditionRange` = ?, `MVPCondition` = ?," +
					"`LastComputerUsed` = ?, `DeptFileNames` = ? WHERE `DeptID` = ?;";
			String dept = input.get(2).toString();
			System.out.println(sql);
			 try {
			updateStatement = connection.prepareStatement(sql);
			updateStatement.setObject(1, input.get(3));	System.out.println("Update Input 1:"+input.get(3));
			updateStatement.setObject(2, input.get(4));
			updateStatement.setObject(3, input.get(5));
			updateStatement.setObject(4, input.get(6));
			updateStatement.setObject(5, input.get(7));
			
			updateStatement.setObject(6, input.get(8));
			updateStatement.setObject(7, input.get(9));
			updateStatement.setObject(8, input.get(10));
			updateStatement.setObject(9, input.get(11));
			updateStatement.setObject(10, input.get(12));	System.out.println("Update Input 10:"+input.get(12));
			
			updateStatement.setObject(11, input.get(13));
			updateStatement.setObject(12, input.get(14));
			updateStatement.setObject(13, input.get(15));
			updateStatement.setObject(14, input.get(16));
			updateStatement.setObject(15, input.get(17));
			
			updateStatement.setObject(16, input.get(18));
			updateStatement.setObject(17, input.get(19));
			updateStatement.setObject(18, input.get(20));
			updateStatement.setObject(19, input.get(21));
			updateStatement.setObject(20, input.get(22));	
			
			updateStatement.setObject(21, input.get(23));
			updateStatement.setObject(22, input.get(24));
			updateStatement.setObject(23, input.get(25));
			updateStatement.setObject(24, input.get(26));
			updateStatement.setObject(25, input.get(27));
			updateStatement.setObject(26, input.get(28));	System.out.println("Update Input 26:"+input.get(28));
			
			updateStatement.setObject(27, input.get(29));
			updateStatement.setObject(28, input.get(30));
			updateStatement.setObject(29, input.get(31));
			updateStatement.setObject(30, input.get(32));
			updateStatement.setObject(31, input.get(33));
			updateStatement.setObject(32, computerused);
			updateStatement.setObject(33, input.get(35));
			
			updateStatement.setObject(34, input.get(2));	System.out.println("Update Input 34:"+input.get(35));
			
			updateStatement.executeUpdate();
			updateStatement.closeOnCompletion();
			
			input.clear();
			input.add(dept+ " Info Updated");
			 } catch (Exception e) {
				 input.clear();
					input.add(dept+ "Info could not be updated");
					e.printStackTrace();
					return input;
				}

		return input;
	}
}
