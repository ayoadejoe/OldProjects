package servers;

public class DatabaseCred {
	private static String USERNAME= null;
	private static String PASSWORD= null;
    private static String IP = null;
    private static int PORT = 0;
	public DatabaseCred() {
				USERNAME = "root";
				PASSWORD = "blessme";
				IP = "localhost";
				PORT = 3306;
	}
	public static String getUSERNAME() {
		return USERNAME;
	}
	public static void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public static String getPASSWORD() {
		return PASSWORD;
	}
	public static void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public static String getIP() {
		return IP;
	}
	public static void setIP(String iP) {
		IP = iP;
	}
	public static int getPORT() {
		return PORT;
	}
	public static void setPORT(int pORT) {
		PORT = pORT;
	}
	

	
}
