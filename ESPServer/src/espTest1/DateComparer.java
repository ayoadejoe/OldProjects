package espTest1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class DateComparer {
	private SimpleDateFormat getDateOnly = new SimpleDateFormat("dd/MM/yy"); 
	private Date today = new Date();
	private Calendar cal = Calendar.getInstance();
	private DateFormat formatter = DateFormat.getInstance();
	private DateFormat timeOnly = DateFormat.getTimeInstance(DateFormat.SHORT); 
	private DateFormat dateOnly = DateFormat.getDateInstance(DateFormat.SHORT);
	public DateComparer() {
		
	}

	boolean todayEntry(List<Object> toCheck){
		boolean checker = false;
		//System.out.println(toCheck);
		int l = toCheck.size();
		for(int c=0; c<l; c++){
			if(toCheck.get(c).getClass().isInstance(today)){
				Date recDate = null;
				Date day = null;
				try {
					recDate = getDateOnly.parse(dateOnly.format((Date)toCheck.get(c)));
					day = getDateOnly.parse(dateOnly.format((Date)today));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
					checker = false;
				}
				
				if(recDate.equals(day)){
					//System.out.println(recDate+" is today "+day);
					checker = true;
				}else{
					//System.out.println(recDate+" is NOT today "+day);
					checker = false;
				}
			}
		}
		return checker;
	}
	
	List<Object> getDataOnDay(List<Object> toCheck, Date dataDate){
		int l = toCheck.size(); int begining =0, end=0; boolean found=false; List<Object> test = new ArrayList<Object>();
		for(int c=0; c<l; c++){
			if(toCheck.get(c).getClass().isInstance(today)){
				Date recDate = null;
				Date day = null;
				try {
					recDate = getDateOnly.parse(dateOnly.format((Date)toCheck.get(c)));
					day = getDateOnly.parse(dateOnly.format((Date)dataDate));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
				if(found){
					end=c;
				System.out.println(recDate+" found at "+begining+", ends at "+end);
				for(int y=begining; y<end; y++){
					test.add(toCheck.get(y));
				}
				return test;
				}
				
				if(recDate.equals(dataDate)){
					begining = c;	found=true;
				}
			}
		}
		return toCheck;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
