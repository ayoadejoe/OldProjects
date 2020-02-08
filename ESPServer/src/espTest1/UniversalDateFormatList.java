package espTest1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UniversalDateFormatList {

	private SimpleDateFormat getDateForm11 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private SimpleDateFormat getDateForm12 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private SimpleDateFormat getDateForm13 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private SimpleDateFormat getDateForm14 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	private SimpleDateFormat getDateForm19 = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
	private SimpleDateFormat getDateForm20 = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
	private SimpleDateFormat getDateForm21 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	private SimpleDateFormat getDateForm22 = new SimpleDateFormat("dd.MMM.yyyy HH:mm:ss");
	
	private SimpleDateFormat getDateOnly = new SimpleDateFormat("dd/MM/yy");
	private SimpleDateFormat getDateSlash = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	private SimpleDateFormat getDateDash = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
	private SimpleDateFormat getDateDot = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
	private SimpleDateFormat getDateSpace = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
	private SimpleDateFormat getDateWords = new SimpleDateFormat("ddd MMM yyyy HH:mm:ss");
	private SimpleDateFormat getDateYearFirst = new SimpleDateFormat("yyyy MMM ddd HH:mm:ss");
	private SimpleDateFormat getDateyear = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
	
	private SimpleDateFormat getDateForm1 = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
	private SimpleDateFormat getDateForm2 = new SimpleDateFormat("EEE, MMM d, ''yy");
	private SimpleDateFormat getDateForm3 = new SimpleDateFormat("hh 'o''clock' a, zzzz");
	private SimpleDateFormat getDateForm4 = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
	private SimpleDateFormat getDateForm5 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	
	private SimpleDateFormat getDateForm6 = new SimpleDateFormat("yyMMddHHmmssZ");
	private SimpleDateFormat getDateForm7 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	private SimpleDateFormat getDateForm8 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	private SimpleDateFormat getDateForm9 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	private SimpleDateFormat getDateForm10 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	
	private SimpleDateFormat getDateForm15 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private SimpleDateFormat getDateForm16 = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat getDateForm17 = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat getDateForm18 = new SimpleDateFormat("dd.MM.yyyy");
	
	private SimpleDateFormat getTime1 = new SimpleDateFormat("h:mm a" );
	private SimpleDateFormat getTimeOnly = new SimpleDateFormat("HH:mm");
	private List<SimpleDateFormat> simpDate = new ArrayList();
	
	
	{
		simpDate.add(getDateOnly);
		simpDate.add(getDateSlash);
		simpDate.add(getDateDash);
		simpDate.add(getDateDot);
		simpDate.add(getDateWords);
		simpDate.add(getDateSpace);
		simpDate.add(getDateYearFirst);
		simpDate.add(getDateDash);
		simpDate.add(getTimeOnly);
		simpDate.add(getDateyear);
		
		simpDate.add(getDateForm1);
		simpDate.add(getDateForm2);
		simpDate.add(getDateForm3);
		simpDate.add(getDateForm4);
		simpDate.add(getDateForm5);
		simpDate.add(getDateForm6);
		simpDate.add(getDateForm7);
		simpDate.add(getDateForm8);
		simpDate.add(getDateForm9);
		simpDate.add(getDateForm10);
		
		simpDate.add(getDateForm11);
		simpDate.add(getDateForm12);
		simpDate.add(getDateForm13);
		simpDate.add(getDateForm14);
		
		simpDate.add(getDateForm15);
		simpDate.add(getDateForm16);
		simpDate.add(getDateForm17);
		simpDate.add(getDateForm18);
		
		simpDate.add(getDateForm19);
		simpDate.add(getDateForm20);
		simpDate.add(getDateForm21);
		simpDate.add(getDateForm22);
		
		simpDate.add(getTime1);
		
	}
	
	public Date UniversalDateFormatList(String date) {
		Date thid = null;
		for(int y=0; y<simpDate.size(); y++){
			try {
				thid = simpDate.get(y).parse(date);
			} catch (ParseException e) {
				continue;
			}
		}
		return thid;
	}
}
