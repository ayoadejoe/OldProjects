package espTest1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

class GenPlotEnumerator {
	private List<Object> Plot = new ArrayList<Object>();
	private List<Date> plotDate = new ArrayList<Date>();
	private Date date = new Date();
	private int Minutes;
	private int Hours;
	private boolean pace;
	private int lowerLimit, upperLimit;
	
	
	GenPlotEnumerator(List<Object> Plot) {
		this.Plot = Plot;
		System.out.println("Received Plots:"+Plot);
	}
	
	List<Date> GetPlotDates() {
		plotDate.clear();
		int l = Plot.size();
		for(int c=0; c<l; c++){
			if(Plot.get(c).getClass().isInstance(date)){
				Date recDate = (Date) Plot.get(c);
				plotDate.add(recDate);
			}
		}
		
		return plotDate;
	}
	
	List<Object> specifiedDatePlot(Date toFind) {
		List<Object> plot = new ArrayList<Object>();
		int l = Plot.size();	boolean nextDate = false;
		int lowerLimit=0, upperLimit=0;
		for(int c=0; c<l; c++){
			if(Plot.get(c).getClass().isInstance(date)){
				Date recDate = (Date) Plot.get(c);
				if(nextDate){
					upperLimit = c;
					this.upperLimit = upperLimit;
					nextDate = false;
					break;
				}
				if(recDate.equals(toFind)){
					lowerLimit = c;
					this.lowerLimit = lowerLimit;
					nextDate = true;
				}
				
			}
		}
		
		if(nextDate){
			upperLimit = l;
			this.lowerLimit = upperLimit;
		}
		
		for(int c = lowerLimit; c<upperLimit; c++){
			plot.add(Plot.get(c));
		}
		
		return plot;
	}
	
	List<Object> plotDefault(int hours) {
		System.out.println("Received Plots:"+Plot);
		Minutes = hours*60;
		List<Object> plot = new ArrayList<Object>();
		int lowerLimit = Plot.size()- Minutes;
		int upperLimit = Plot.size()-1;
		System.out.println("lower:"+lowerLimit+", upper:"+upperLimit);
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		
		if(lowerLimit<0){
			lowerLimit = 0;
			Minutes = Plot.size();
			this.lowerLimit = lowerLimit;
		}
		
		for (int e=0; e<Minutes; e++){
			try{
				plot.add(Plot.get(lowerLimit++));
			}catch(ArrayIndexOutOfBoundsException d){
				//.out.println("BREAKS!!!"+d.getMessage());
				return plot;							//data not up to time frame, just send the time frame available
			}
		}
		System.out.println("Return Plots:"+Plot);
		return plot;
	}
	
	List<Object> plotPace( boolean pace, int hours, int upperLimit, int lowerLimit) {
		Minutes = hours*60;
		//.out.println("pace:"+pace+", hours:"+hours);
		List<Object> plot = new ArrayList<Object>();
		if(pace){
			lowerLimit = upperLimit;
			upperLimit = upperLimit+Minutes;
			this.lowerLimit = lowerLimit;
			this.upperLimit = upperLimit;
			//.out.println("plotPace:"+"low>"+lowerLimit+", upp>"+upperLimit);
			if(upperLimit > 0 ){	
				for (int e=0; e<Minutes; e++){
					try{
					plot.add(Plot.get(lowerLimit++));							//lowerLimit added because you want to start from where it last reading stopped
					}catch(ArrayIndexOutOfBoundsException d){
						//.out.println(d.getMessage());
						return plot;
					}
					}
			}
		}else{
			if((lowerLimit-Minutes)<0){					//means data is right past end, adjust, check upperlimit
				lowerLimit = 0;
				}else if((lowerLimit-Minutes)>=0 && upperLimit>0){
					//.out.println("Accessing Subefore:"+"low>"+lowerLimit);
					upperLimit = lowerLimit;
					lowerLimit = upperLimit - Minutes;
					//.out.println("Accessing SubAfter:"+"low>"+lowerLimit);
					}else{
						return plot;
					}
			
			this.lowerLimit = lowerLimit;
			this.upperLimit = upperLimit;
			//.out.println("plotPace 2:"+"low>"+lowerLimit+", upp>"+upperLimit);
			for (int e=0; e<Minutes; e++){
				try{
					plot.add(Plot.get(lowerLimit++));
				}catch(ArrayIndexOutOfBoundsException d){
					//.out.println(d.getMessage());
					return plot;
				}
			}
			//.out.println("Completed");
			
		}
		return plot;
	}
	
	public static void main(String[] args) {
		
	}

	int getLowerLimit() {
		//.out.println("Lower:"+lowerLimit);
		return lowerLimit;
	}

	void setLowerLimit(int lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	int getUpperLimit() {
		//.out.println("upper:"+upperLimit);
		return upperLimit;
	}

	void setUpperLimit(int upperLimit) {
		this.upperLimit = upperLimit;
	}

}
