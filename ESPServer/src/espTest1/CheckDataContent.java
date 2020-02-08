package espTest1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

	class CheckDataContent {
		private SimpleDateFormat getDateOnly = new SimpleDateFormat("dd/MM/yy"); 
		private Date today = new Date();
		private Calendar cal = Calendar.getInstance();
		private DateFormat formatter = DateFormat.getInstance();
		private DateFormat timeOnly = DateFormat.getTimeInstance(DateFormat.SHORT); 
		private DateFormat dateOnly = DateFormat.getDateInstance(DateFormat.SHORT);
	CheckDataContent() {
		
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
	
	
	String dateRange(){
		
		return "";
	}

	List<Object> next(List<Object> completeData, int page){
		Vector next = new Vector();
		int fullSize = completeData.size();
		if(fullSize>50){
			
			if(page==(-1)){
				int pages = fullSize/50;
				int lastPage = fullSize%50;
				int startFrom = (50*pages);
				int a =0;
				for (int w = startFrom; w<fullSize; w++){a++;
					try{
					next.add(completeData.get(w));
					}catch(Exception d){
						d.printStackTrace();
						return next;
					}
					if(a==50){
						return next;
					}
				}
				return next;
			}
			
			int a =0;
			for (int w = page*50; w<fullSize; w++){a++;
				try{
				next.add(completeData.get(w));
				}catch(Exception d){
					d.printStackTrace();
					return next;
				}
				if(a==50){
					return next;
				}
			}
		}else{
			return completeData;
		}
		//System.out.println("Complete Data returned. next is:"+next);
		return completeData;
	}

	List<Object> prev(List<Object> completeData, int page){
		Vector prev = new Vector();  int lastPage =0;
		int fullSize = completeData.size();
		if(page==0){
			lastPage = fullSize%50;
			int a =0;
			for (int w = lastPage; w<fullSize; w++){a++;
				try{
				prev.add(completeData.get(w));
				}catch(Exception d){
					d.printStackTrace();
					return prev;
				}
				if(a==50){
					return prev;
				}
			}
		}
		
		if(page>0){
		if(fullSize>50){
			int u =0;
			for (int w = page*50; w<fullSize; w++){u++;
			try{
			prev.add(completeData.get(w));
			}catch(Exception d){
				d.printStackTrace();
				return prev;
			}
			if(u==50){
				return prev;
			}
		}
		}
		}
		
		//System.out.println("Complete Data returned. next is:"+prev);
		return completeData;
	}
	
	List<Object> dataToDelete(List<Object> completeData, List<Object> who){
		who = rearrange(who);
		 for(int y = 0; y<who.size(); y++){
			 int address =-1;
			 try{
			 address = (int)who.get(y);
			 System.out.println("address:"+(address));
			 System.out.println(completeData.get(address));
			 completeData.remove(address);
			 }catch(Exception j){j.printStackTrace();}
			 
		 }
		return completeData;
	}
	
	private List<Object> rearrange(List<Object> who) {
		Comparator comp = Collections.reverseOrder();
		Collections.sort(who, comp);
		System.out.println(who);
		return who;
	}

	public static void main(String[] args) {
		List<Object> f = new ArrayList<Object>(); f.add(100); f.add(200); f.add(50); f.add(20); f.add(150);
		System.out.println(new CheckDataContent().rearrange(f));

	}
	

}
	
	
	/*Date now = new Date();
	   
    // Use Date.toString()
    System.out.println(now);
    String dateStr = formatter.format(now);
    System.out.println(dateStr);
    System.out.println(dateOnly.format(now));
    System.out.println(timeOnly.format(now));
    try {
		Date thisday = getDateOnly.parse(dateOnly.format(now));
		//System.out.println("thisday:" +thisday);
	} catch (ParseException e) {
		e.printStackTrace();
	}
    // Use locale
    formatter = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.FRANCE);
    System.out.println(formatter.format(now));

    // Use SimpleDateFormat
    SimpleDateFormat simpleFormatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
    System.out.println(simpleFormatter.format(now));*/
