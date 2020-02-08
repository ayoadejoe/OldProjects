package baseGui;

import java.text.ParseException;
import java.util.Date;

import Administration.BasicInfoClient;

public class Termer {
	private Date day = new Date();
	private BasicInfoClient test = new BasicInfoClient();
	private String termStatus = null, thisterm = null;
	public String chckTerm(){
		String tday = day.toLocaleString();
		termStatus = test.termCheck("Present, "+tday);
		switch(termStatus){
			case "empty":
				thisterm = "NoTerm";
			break;
			case 1+"":
				thisterm = "First";
			break;
			case 2+"":
				thisterm = "Second";			
			break;
			case 3+"":
				thisterm = "Third";		
			break;
			case "false":
				thisterm = "NoTerm";
			break;
			default:
				thisterm = "NoTerm";
				break;
						}
		return thisterm;
	}
}
