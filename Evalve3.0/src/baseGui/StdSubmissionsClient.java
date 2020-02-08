package baseGui;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class StdSubmissionsClient {
	private Socket client;
	private int DEPort = 7383, inicialscore;
	private boolean StatusCheck = true;
	private DataOutputStream submit = null;
	private String Status = "";
	private PrintWriter eachLine = null;
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	public StdSubmissionsClient() {
	}
	
	public void mcqSubmissions(String filename, String class1, int iDNo, String subject, String evalType, 
			String time2, String date2, String duration2, int baseno, Vector<Object> answerArray, 
			String submitStatus, int weight, int pointing, int week, String examiner, String tem){
		//(answerArray+", Size = "+answerArray.size());
		int sizi = -1; int presentscore= 0;
		
		int i;
		int scores = 0;
		for(i = 0; i < answerArray.size(); i++){
		    scores += (int)answerArray.get(i);
		}
		//("Submitting Present Score: "+scores);
		Status = submitStatus;
		String dateTime = new Date().toLocaleString();
		String SubData = "$Submissions$"+"_"+"$MCQ$"+"_"+ filename+"_"+class1+"_"+iDNo+"_"+subject+"_"+evalType+"_"+dateTime+"_"+scores+"_"+submitStatus+"_"+weight;
		try {
			client = new Socket("192.168.0.101", DEPort);
			submit = new DataOutputStream(client.getOutputStream());
			submit.writeUTF(SubData);
			submit.close();
			client.close();
			//("Submitting :"+SubData);
		} catch (Exception e) {
			StatusCheck = false;
			AnswerLogger(SubData, filename, iDNo);
			//e.printStackTrace();
		}
		
	}
	
	public void essaysubmissions(String filename, String class1, int iDNo, String subject, String evalType, 
			String time2, String date2, String duration2, int baseno, Vector<Object> essayAns, String submitStatus, String weight){
		//("Submitting Present Answers: "+essayAns); 
		String EssayAns = "";
		Status = submitStatus;
		if(subject.contains("_")){
			subject = subject.replace("_", "");
		}
		if(essayAns.toString().contains("_")){
			EssayAns = essayAns.toString().replace("_", "-");
		}else{
			EssayAns = essayAns.toString();
		}
		
		String dateTime = new Date().toLocaleString();
		String SubData = "$Submissions$"+"_"+"$Essay$"+"_"+filename+"_"+class1+"_"+iDNo+"_"+subject+"_"+evalType+"_"+dateTime+"_"+EssayAns+"_"+submitStatus+"_"+weight;
		try {
			client = new Socket("192.168.0.101", DEPort);
			submit = new DataOutputStream(client.getOutputStream());
			submit.writeUTF(SubData);
			submit.close();
			client.close();
			//("Submitting :"+SubData);
		} catch (Exception e) {
			StatusCheck = false;
			AnswerLogger(SubData, filename, iDNo);
			//e.printStackTrace();
		}
	}

	private void AnswerLogger(String subData, String filename, int iDNo) {
		//("This is the status: "+Status+ " StatusCheck:"+StatusCheck);
		if(StatusCheck == false && Status.equals("Signed")){
		File LossLogger = new File(filename+"_"+iDNo+"_"+"Logger");
		 try {
	    		FileOutputStream fos = new FileOutputStream(LossLogger);
	 		    OutputStreamWriter oos = new OutputStreamWriter(fos);
	 		    BufferedWriter bw = new BufferedWriter(oos);
	 		    bw.write(subData);
	 		    bw.flush();
	 		   if(oos != null) oos.close();
			   if(fos != null) oos.close();
	    	 } catch (IOException e) {
	    		 e.printStackTrace();
	 		}
		
		new LoggerCrypter(LossLogger);
		
		if(LossLogger.exists()){
			System.gc();
			LossLogger.delete();
			//("LossLogger deleted");
		}
		}
	}
}
