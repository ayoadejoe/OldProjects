package servers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Structure {
	private static String USERNAME = null;
	private static String PASSWORD = null;
    private static String DATABASE_URL = null;
    private static String IP = null;
    private static int PORT = 0;
    private static Connection connection = null;
    private PreparedStatement checkStmt = null;
    private Statement stmt = null;
    private ResultSet checkResult;
	public Structure(String ip, int port, String username, String password) {
		IP = ip;
		PORT= port;
		USERNAME = username;
		PASSWORD = password;
	}

	public boolean checkExistDatabase() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			return true;
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage());
			//e.printStackTrace();
			return false;
		}
	}
	
	public boolean createDatabase() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"information_schema"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			String sql = "create database cityfame;";
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			stmt.closeOnCompletion();
			System.out.println("database created");
			return true;
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkExistStaffTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			String checkSql = "SELECT * FROM StaffTable;";
			checkStmt = connection.prepareStatement(checkSql);
			checkResult = checkStmt.executeQuery();
			if (checkResult.next())return true;
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage());
			//e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean createStaffTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				String Sql = "create table stafftable (`StaffNo` varchar(75) NOT NULL, " +
						"`Name` varchar(105) NULL, `Department` varchar(45) NULL,  `Designation` varchar(45) NULL,`Salary` int(11) NULL," +
						" `Address` varchar(505) NULL, `Email` varchar(75) NULL, `DateOfBirth` varchar(35) NULL, " +
						" `EmploymentDate` varchar(35) NULL,`Signaturecode` varchar(35) NULL, `NextOfKin` varchar(105) DEFAULT NULL," +
						" `NOKAdress` varchar(350) DEFAULT NULL,`LastIncreDate` varchar(35) DEFAULT NULL,`AllInfo` longblob,`ProfilePic` longblob," +
						" `Username` varchar(25) DEFAULT NULL, `Password` varchar(25) DEFAULT NULL,`ThumbPrint` longblob," +
						" `LastPromotDate` varchar(35) DEFAULT NULL, `Gender` varchar(10) DEFAULT NULL,`Files` longblob," +
						" `Phone1` varchar(35) DEFAULT NULL, `Phone2` varchar(32) DEFAULT NULL," +
						" `LastComputerUsed` varchar(250) DEFAULT NULL, PRIMARY KEY (`StaffNo`));";
				Statement stmt = connection.createStatement( );
				stmt.executeUpdate(Sql);
		    	stmt.closeOnCompletion();
		    	return true;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			return false;
			}
	}
	
	public boolean checkExistMemoTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			String checkSql = "SELECT * FROM internalmemo;";
			checkStmt = connection.prepareStatement(checkSql);
			checkResult = checkStmt.executeQuery();
			if (checkResult.next())return true;
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage());
			//e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean createMemoTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				String Sql = "create table internalmemo (`StaffNo` varchar(75) NOT NULL, " +
						" `Name` varchar(105) NULL, `Department` varchar(45) NULL,  `Designation` varchar(45) NULL,`Amount` double DEFAULT NULL," +
						" `Subject` varchar(175) NULL, `Request` varchar(505) NULL,`Date` varchar(35) NULL, " +
						" `Time` varchar(35) NULL, `Weblink` varchar(205) DEFAULT NULL," +
/*code is confirmed*/	" `Signature` varchar(35) DEFAULT NULL,`Quotations` longblob,`ItemImage` longblob," +
						" `AdminApprove` char(1) DEFAULT NULL, `AdminComment` varchar(505) DEFAULT NULL, `AuditApprove` char(1) DEFAULT NULL," +
						" `AuditComment` varchar(505) DEFAULT NULL,`MDApprove` char(1) DEFAULT NULL, `MDComment` varchar(505) DEFAULT NULL,`ThumbPrint` longblob," +
						" `AccountApprove` char(1) DEFAULT NULL, `AccountComment` varchar(500) DEFAULT NULL, `RefundAmount` double DEFAULT NULL," +
						" `Revoked` char(1) DEFAULT NULL, `LessDisburse` char(1) DEFAULT NULL, `ExcessDisburse` char(1) DEFAULT NULL," +
						" `AdvancedCash` char(1) DEFAULT NULL, PRIMARY KEY (`StaffNo`) );";
				Statement stmt = connection.createStatement( );
				stmt.executeUpdate(Sql);
		    	stmt.closeOnCompletion();
		    	return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean checkExistNotifyTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			String checkSql = "SELECT * FROM NotifyTable;";
			checkStmt = connection.prepareStatement(checkSql);
			checkResult = checkStmt.executeQuery();
			if (checkResult.next())return true;
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	
	public boolean createNotifyTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				String Sql = "create table NotifyTable (`StaffNo` varchar(75) NOT NULL, " +
						" `Name` varchar(105) NULL, `Department` varchar(45) NULL,  `Designation` varchar(45) NULL,`NoNotices` double DEFAULT NULL," +
						" `Subject` varchar(175) NULL, `Note` varchar(505) NULL,`Date` varchar(35) NULL, " +
						" `Time` varchar(35) NULL, `Weblink` varchar(205) DEFAULT NULL," +
/*code is confirmed*/	" `Signature` varchar(35) DEFAULT NULL,`Document` longblob,`Picture` longblob," +
						" `Reply` char(1) DEFAULT NULL,`Video` longblob," +
						" `InConversation` blob, PRIMARY KEY (`StaffNo`) );";
				Statement stmt = connection.createStatement( );
				stmt.executeUpdate(Sql);
		    	stmt.closeOnCompletion();
		    	return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean checkExistEngineerTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			String checkSql = "SELECT * from engineerlog;";
			checkStmt = connection.prepareStatement(checkSql);
			checkResult = checkStmt.executeQuery();
			if (checkResult.next())return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}
	
	public boolean createEngineerTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				String Sql = "create table engineerlog (`StaffNo` varchar(75) NOT NULL, " +
						" `Name` varchar(105) NULL, `Department` varchar(45) NULL,  `Designation` varchar(45) NULL,`Attendance` double DEFAULT NULL," +
						" `Time` varchar(35) NULL, `Date` varchar(35) DEFAULT NULL," +
/*code is confirmed*/	" `Signature` varchar(35) DEFAULT NULL,`Morning` blob,`Afternoon` blob,`Night` blob," +
						" `Transmission` char(1) DEFAULT NULL, `TransmissionIssue` varchar(505) DEFAULT NULL, `Power` char(1) DEFAULT NULL," +
						" `PowerIssue` varchar(505) DEFAULT NULL,`Studio` char(1) DEFAULT NULL, `StudioIssue` varchar(505) DEFAULT NULL,`IssuePicture` longblob," +
						" `OtherFault` char(1) DEFAULT NULL, `OtherFaultIssue` varchar(500) DEFAULT NULL, `FaultsRectified` double DEFAULT NULL," +
						" `RectifiedLists` blob,`PendingFaultsLists` blob,`ItemsReceivedList` blob,`ItemReceivedTime` varchar(35) NULL, `ItemPicture` longblob," +
						" `PowerRectified` char(1) DEFAULT NULL, `TransRectified` char(1) DEFAULT NULL, `OthersRectified` char(1) DEFAULT NULL," +
						" PRIMARY KEY (`StaffNo`) );";
				Statement stmt = connection.createStatement( );
				stmt.executeUpdate(Sql);
		    	stmt.closeOnCompletion();
		    	return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean checkExistStandardTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			String checkSql = "SELECT * from standards;";
			checkStmt = connection.prepareStatement(checkSql);
			checkResult = checkStmt.executeQuery();
			if (checkResult.next())return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}
	
	public boolean createStandardTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				String Sql = "create table standards (`DeptID` varchar(15) NOT NULL, " +
						" `Super` varchar(35) NULL, `Department` varchar(45) NULL,  `Designations` longblob, `Head` varchar(45) NULL," +
						" `DeptEmail` varchar(235) NULL, `DeptNumbers` varchar(245) NULL, `CompanyLogo` longblob, `DepartmentLogo` longblob," +
						" `Descriptions` longblob, `GeneralParameters` longblob, `SynergyParameters` longblob, `DepartmentFiles` longblob," +
/*code is confirmed*/	" `Appraiser` varchar(35) DEFAULT NULL,`PromotionChain` longblob,`Sanctions` longblob,`Commendations` longblob," +
						" `Strengths` longblob, `Weaknesses` longblob, `ExitSupport` int(11) NULL, `NeedsImprovement` int(11) NULL," +
						" `Average` int(11) NULL,`RoleModel` int(11) NULL,`Outstanding` int(11) NULL,`AboveAverage` int(11) NULL, " +
						" `WarningCondition` varchar(20) NULL, `UseCondition` varchar(5) NULL, `IncrementCondition` varchar(20) NULL, `PromotionCondition` varchar(20) NULL, " +
						" `DisengageCondition` varchar(20) NULL,`ConditionRange` varchar(20) NULL, `MVPCondition` varchar(20) NULL, `LastComputerUsed` varchar(145) NULL," +
						" `DeptFileNames` varchar(435) DEFAULT NULL, `SupportAdvice` varchar(20) NULL, PRIMARY KEY (`DeptID`) );";
				//noInc, noPromotion all depend on (In order) Outstanding, GoodMark, PassMark, FailMark (e.g no of Outstanding in a year)
				//condition range = Quarterly, Half-Year, Year, 2 Years, 3 years, 5 years, Lifetime
				Statement stmt = connection.createStatement( );
				stmt.executeUpdate(Sql);
		    	stmt.closeOnCompletion();
		    	return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean checkExistAppraisalTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			String checkSql = "SELECT * from appraisals;";
			checkStmt = connection.prepareStatement(checkSql);
			checkResult = checkStmt.executeQuery();
			if (checkResult.next())return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}
	
	public boolean createAppraisalTable() {
		try {
			DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"cityfame"; 
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				String Sql = "create table appraisals (`StaffNo` varchar(75) NOT NULL, " +
						" `Name` varchar(140) NULL, `Department` varchar(45) NULL,  `Designation` varchar(45) NULL," +
						" `Month` varchar(15) NULL, `Year` varchar(15) NULL, `DateSubmitted` varchar(45) NULL, `Parameters` longblob," +
/*code is confirmed*/	" `TotalScore` double DEFAULT NULL,`TotalWeight` double default null,`Alarm` varchar(115) NULL," +
						" `Warning` varchar(115) NULL, `Commendation` blob NULL, `Sanction` blob NULL, `SalaryToReceive` double DEFAULT NULL," +
						" `General Remarks` blob,`GoodMark` double DEFAULT NULL, `WarningCondition` blob,`ConsistencyCondition` longblob," +
						" `IncrementCondition` blob, `Signatures` varchar(215) NULL, `DateAccessed` varchar(115) NULL," +
						" `OutstandingNo` int(11) NULL, `GoodMarkNo` int(11) NULL, `PassMarkNo` int(11) NULL, `FailMarkNo` int(11) NULL," +
						" PRIMARY KEY (`StaffNo`) );";
				//noInc, noPromotion all depend on (In order) Outstanding, GoodMark, PassMark, FailMark (e.g no of Outstanding in a year)
				//condition range = Quarterly, Half-Year, Year, 2 Years, 3 years, 5 years, Lifetime
				//trimester
				Statement stmt = connection.createStatement( );
				stmt.executeUpdate(Sql);
		    	stmt.closeOnCompletion();
		    	return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
