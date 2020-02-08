package baseGui;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeChecker {
	Date set = null; int duration = 0;
	public String Checker(String time, String date, String dura) throws ParseException {
		set = new SimpleDateFormat("dd/MM/yy HH:mm").parse(date+" "+time);
		try{
		Object[] parts = dura.split("'");
		duration = Integer.parseInt(parts[0].toString().trim());
		}catch(Exception z){
			duration = 60;
		}
		long secs = set.getTime();
		long present = new Date().getTime();
		long diff = secs - present;
		long secDiff = diff/1000;
		double minutes = secDiff/60;
		double hours = secDiff/3600;
		double days = secDiff/(3600*24);
		double weeks = secDiff/(3600*24*7);
		if(weeks>0){
			return weeks+" weeks. ('"+(duration+minutes)+"' minutes remaining.)";
		}else if(days>0){
			return days+" days. ('"+(duration+minutes)+"' minutes remaining.)";
		}else if(hours > 0){
			return hours+" hours. ('"+(duration+minutes)+"' minutes remaining.)";
		}else if(minutes>0){
			return minutes+" minutes. ('"+(duration+minutes)+"' minutes remaining.)";
		}else if(secDiff>0){
			return secDiff+" seconds. ('"+(duration+minutes)+"' minutes remaining.)";
		}else if(secDiff < 0 && minutes > -(duration)){
			return "In Progress! ('"+(duration+minutes)+"' minutes remaining.)";
		}else if(secDiff < 0 && minutes < -(duration)){
			return "Due! ('"+(-1*(duration+minutes))+"' minutes due.)";
		}else if(secDiff < 0){
			return "Due! ('"+(-1*(duration+minutes))+"' minutes due.)";
		}
		
		return null;
		
	}
	public String AssignmentDueChecker(String time, String date) throws ParseException {
		String status = "";
		//(date+", "+time);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date dueDate = format.parse (date+" "+time);  
		Date today = new Date();
		if(today.before(dueDate)){
			//(today.toLocaleString()+" is before "+dueDate+". Assignment is not due.");
			status = "In Progress";
		}else{
			//(" Assignment has expired.");
			status = "Expired";
		}
		return status;
	}
}
