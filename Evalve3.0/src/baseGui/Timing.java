package baseGui;

public class Timing {
	private String ExamDay;
	private String ExamTime;
	private String Duration;
	private String Diagram;
	private String term;
	
	private int day, mon, year;
	private int hr, min, dhr, dmin;
	
	public Timing(){
		
	}
	
	public void setTerm(String term) {
		//("In Timing "+term);
		this.term = term;
	}
	public String getTerm() {
		return term;
	}

	public String getExamDay() {
		return ExamDay;
	}
	public void setExamDay(int day, int mon, int year) {
		String examDay = day+"/"+mon+"/"+year;
		ExamDay = examDay;
	}
	
	public String getExamTime() {
		return ExamTime;
	}
	public void setExamTime(int hr, String min, String Ped) {
		String examTime = hr+":"+min;
		ExamTime = examTime;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(int dhr, int dmin) {
		int hrM = dhr*60;
		int Minutes = hrM + dmin;
		String duration = Minutes+"' Minutes";
		Duration = duration;
	}
	
	

}
