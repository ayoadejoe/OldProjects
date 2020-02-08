package registration;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import clients.CityClient;

public class GeneralInfo {
	private CityClient staffData = new CityClient(); 
	private ArrayList deptVector = new ArrayList();
	private  String computerused = null,table = null,deptID = null,superTop = null,dept = null ,head = null, 
			email=null, numbers=null;
	private byte[] complogo = null,  deptlogo = null;
	private Vector designation = null, credentials = null, description = null, parameters = null, linkparameters = null,
			sanctions=null, commendations=null, strengths=null, weaknesses=null;
	String fileNames = null;
	private String promotionChain= null, promotionAdv= null, incrementAdv = null, disengageAdv = null,
			mvpAdv = null, warningAdv = null, appraiser=null, lastUpdate=null, supportAdv=null;
	boolean useCondition = false;
	private int exitSupportScore=0, needsImproveScore=0, averageScore=0,
			roleModelScore=0, outstandingScore = 0, aboveAverageScore = 0;
	private boolean period;
	private String periodDays = null;
	private byte[] deptImage = null;
	Thread getCluster;
	private ArrayList allDepts = new ArrayList<String>();
	private ArrayList allDesig = new ArrayList<Object>();
	private ArrayList allStaff = new ArrayList<String>();
	private ArrayList allHeads = new ArrayList<String>();
	private ArrayList allEmail = new ArrayList<String>();
	private ArrayList allDeptNos = new ArrayList<String>();
	
	public GeneralInfo(UpdateDepartment updateDepartment, String deptsend) {
		try{
		deptVector.add("^getstandards$"); deptVector.add("standards");deptVector.add("*"); deptVector.add(deptsend); //remember address is on position 0
		
		deptVector = staffData.getIt(deptVector);
		System.out.println("DEPTVECTOR:"+deptVector);
		deptID = deptVector.get(0).toString();
		superTop = deptVector.get(1).toString();
		dept = deptVector.get(2).toString();
		designation = (Vector) deptVector.get(3);
		head = deptVector.get(4).toString();
		email = deptVector.get(5).toString();
		numbers = deptVector.get(6).toString();
		complogo = (byte[]) deptVector.get(7);
		deptlogo = (byte[]) deptVector.get(8);
		description = (Vector) deptVector.get(9);
		parameters = (Vector) deptVector.get(10);
		linkparameters = (Vector) deptVector.get(11);
		credentials = (Vector) deptVector.get(12);
		
		appraiser = deptVector.get(13).toString();
		promotionChain= deptVector.get(14).toString();
		sanctions = (Vector)deptVector.get(15);
		commendations = (Vector)deptVector.get(16);
		strengths = (Vector)deptVector.get(17);
		weaknesses = (Vector)deptVector.get(18);
		
		exitSupportScore = (int)deptVector.get(19);
		needsImproveScore= (int)deptVector.get(20); 
		averageScore= (int)deptVector.get(21);
		roleModelScore= (int)deptVector.get(22); 
		outstandingScore = (int)deptVector.get(23); 
		aboveAverageScore = (int)deptVector.get(24);
		
		warningAdv = deptVector.get(25).toString();
		System.out.println("TEST:"+deptVector.get(26).toString().trim());
		useCondition = Boolean.parseBoolean(deptVector.get(26).toString().trim());
		System.out.println("CONDITION IS>"+useCondition);
		incrementAdv = deptVector.get(27).toString(); 
		promotionAdv = deptVector.get(28).toString();  
		disengageAdv = deptVector.get(29).toString(); 
		
		periodDays = (String)deptVector.get(30);
		
		mvpAdv = deptVector.get(31).toString(); 
		lastUpdate = deptVector.get(32).toString();
		fileNames = deptVector.get(33).toString();
		
		System.out.println("fileNames:"+fileNames);
		
		}catch(Exception x){
			x.printStackTrace();
			//JOptionPane.showMessageDialog(updateDepartment, "A problem occured while trying to connect to update database. Check your connection.");
		}
		
		getCluster = new getCluster();
		System.out.println("Cluster starting");
		getCluster.start();
	}
	
    class getCluster extends Thread{
    	public void run() {
    		System.out.println("In get Cluster");
    		for(int dd = 0; dd<7; dd++){
    			switch(dd){
    			case 1:
    				allDepts.add("&ALL&"); allDepts.add("standards");allDepts.add("*"); allDepts.add("Department"); //remember address is on position 0
    				allDepts = staffData.getIt(allDepts);
    				if(allDepts ==null)allDepts = new ArrayList<String>();
    				break;
    			case 2:
    				allDesig.add("&ALL&"); allDesig.add("standards");allDesig.add("*"); allDesig.add("Designations"); //remember address is on position 0
    				allDesig = staffData.getIt(allDesig);
    				if(allDesig ==null)allDesig = new ArrayList<Object>();
    				break;
    			case 3:
    				allEmail.add("&ALL&"); allEmail.add("standards");allEmail.add("*"); allEmail.add("DeptEmail"); //remember address is on position 0
    				allEmail = staffData.getIt(allEmail);
    				if(allEmail ==null)allEmail = new ArrayList<String>();
    				break;
    			case 4:
    				allHeads.add("&ALL&"); allHeads.add("standards");allHeads.add("*"); allHeads.add("Head"); //remember address is on position 0
    				allHeads = staffData.getIt(allHeads);
    				if(allHeads ==null)allHeads = new ArrayList<String>();
    				break;
    			case 5:
    				allStaff.add("&ALL&"); allStaff.add("stafftable");allStaff.add("*"); allStaff.add("Name, Phone1, Email"); //remember address is on position 0
    				allStaff = staffData.getIt(allStaff);
    				if(allStaff ==null)allStaff = new ArrayList<String>();
    				break;
    			case 6:
    				allDeptNos.add("&ALL&"); allDeptNos.add("standards");allDeptNos.add("*"); allDeptNos.add("DeptNumbers"); //remember address is on position 0
    				allDeptNos = staffData.getIt(allDeptNos);
    				if(allDeptNos ==null)allDeptNos = new ArrayList<String>();
    				break;
    			}
    		}
    		getCluster.interrupt();
    	 }
    }
	public String getFileNames() {
		return fileNames;
	}


	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}


	public CityClient getStaffData() {
		return staffData;
	}

	public void setStaffData(CityClient staffData) {
		this.staffData = staffData;
	}

	public ArrayList getDeptVector() {
		return deptVector;
	}

	public void setDeptVector(ArrayList deptVector) {
		this.deptVector = deptVector;
	}

	public String getComputerused() {
		return computerused;
	}

	public void setComputerused(String computerused) {
		this.computerused = computerused;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getSuperTop() {
		return superTop;
	}

	public void setSuperTop(String superTop) {
		this.superTop = superTop;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public byte[] getComplogo() {
		return complogo;
	}

	public void setComplogo(byte[] complogo) {
		this.complogo = complogo;
	}

	public Vector getDesignation() {
		return designation;
	}

	public void setDesignation(Vector designation) {
		this.designation = designation;
	}

	public Vector<byte[]> getCredentials() {
		return credentials;
	}

	public void setCredentials(Vector credentials) {
		this.credentials = credentials;
	}

	public byte[] getDeptlogo() {
		return deptlogo;
	}

	public void setDeptlogo(byte[] deptlogo) {
		this.deptlogo = deptlogo;
	}



	public Vector getDescription() {
		return description;
	}



	public void setDescription(Vector description) {
		this.description = description;
	}



	public Vector getParameters() {
		return parameters;
	}



	public void setParameters(Vector parameters) {
		this.parameters = parameters;
	}



	public Vector getLinkparameters() {
		return linkparameters;
	}



	public void setLinkparameters(Vector linkparameters) {
		this.linkparameters = linkparameters;
	}



	public String getPromotionChain() {
		return promotionChain;
	}



	public void setPromotionChain(String promotionChain) {
		this.promotionChain = promotionChain;
	}



	public String getPromotionAdv() {
		return promotionAdv;
	}



	public void setPromotionAdv(String promotionAdv) {
		this.promotionAdv = promotionAdv;
	}



	public String getIncrementAdv() {
		return incrementAdv;
	}



	public void setIncrementAdv(String incrementAdv) {
		this.incrementAdv = incrementAdv;
	}


	public String getDisengageAdv() {
		return disengageAdv;
	}



	public void setDisengageAdv(String disengageAdv) {
		this.disengageAdv = disengageAdv;
	}



	public String getMvpAdv() {
		return mvpAdv;
	}



	public void setMvpAdv(String mvpAdv) {
		this.mvpAdv = mvpAdv;
	}



	public String getWarningAdv() {
		return warningAdv;
	}



	public void setWarningAdv(String warningAdv) {
		this.warningAdv = warningAdv;
	}



	public boolean isPeriod() {
		return period;
	}



	public void setPeriod(boolean period) {
		this.period = period;
	}



	public boolean isUseCondition() {
		return useCondition;
	}

	public void setUseCondition(boolean useCondition) {
		this.useCondition = useCondition;
	}


	public String getPeriodDays() {
		return periodDays;
	}


	public void setPeriodDays(String periodDays) {
		this.periodDays = periodDays;
	}
	
	public ArrayList getAllDepts() {
		return allDepts;
	}


	public void setAllDepts(ArrayList allDepts) {
		this.allDepts = allDepts;
	}


	public ArrayList getAllDesig() {
		return allDesig;
	}


	public void setAllDesig(ArrayList allDesig) {
		this.allDesig = allDesig;
	}


	public ArrayList getAllStaff() {
		return allStaff;
	}


	public void setAllStaff(ArrayList allStaff) {
		this.allStaff = allStaff;
	}


	public ArrayList getAllHeads() {
		return allHeads;
	}


	public void setAllHeads(ArrayList allHeads) {
		this.allHeads = allHeads;
	}


	public ArrayList getAllEmail() {
		return allEmail;
	}


	public void setAllEmail(ArrayList allEmail) {
		this.allEmail = allEmail;
	}


	public ArrayList getAllDeptNos() {
		return allDeptNos;
	}


	public void setAllDeptNos(ArrayList allDeptNos) {
		this.allDeptNos = allDeptNos;
	}


	public String getAppraiser() {
		return appraiser;
	}


	public void setAppraiser(String appraiser) {
		this.appraiser = appraiser;
	}


	public Vector getSanctions() {
		return sanctions;
	}


	public void setSanctions(Vector sanctions) {
		this.sanctions = sanctions;
	}


	public Vector getCommendations() {
		return commendations;
	}


	public void setCommendations(Vector commendations) {
		this.commendations = commendations;
	}


	public Vector getStrengths() {
		return strengths;
	}


	public void setStrengths(Vector strengths) {
		this.strengths = strengths;
	}


	public Vector getWeaknesses() {
		return weaknesses;
	}


	public void setWeaknesses(Vector weaknesses) {
		this.weaknesses = weaknesses;
	}


	public String getLastUpdate() {
		return lastUpdate;
	}


	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	public int getExitSupportScore() {
		return exitSupportScore;
	}


	public void setExitSupportScore(int exitSupportScore) {
		this.exitSupportScore = exitSupportScore;
	}


	public int getNeedsImproveScore() {
		return needsImproveScore;
	}


	public void setNeedsImproveScore(int needsImproveScore) {
		this.needsImproveScore = needsImproveScore;
	}


	public int getAverageScore() {
		return averageScore;
	}


	public void setAverageScore(int averageScore) {
		this.averageScore = averageScore;
	}


	public int getRoleModelScore() {
		return roleModelScore;
	}


	public void setRoleModelScore(int roleModelScore) {
		this.roleModelScore = roleModelScore;
	}


	public int getOutstandingScore() {
		return outstandingScore;
	}


	public void setOutstandingScore(int outstandingScore) {
		this.outstandingScore = outstandingScore;
	}


	public int getAboveAverageScore() {
		return aboveAverageScore;
	}


	public void setAboveAverageScore(int aboveAverageScore) {
		this.aboveAverageScore = aboveAverageScore;
	}


	public String getSupportAdv() {
		return supportAdv;
	}


	public void setSupportAdv(String supportAdv) {
		this.supportAdv = supportAdv;
	}

	

}
