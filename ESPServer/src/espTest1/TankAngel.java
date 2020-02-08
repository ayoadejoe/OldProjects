package espTest1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class TankAngel extends JPanel{
	private SimpleDateFormat getDateForm17 = new SimpleDateFormat("dd-MM-yyyy");
	private DateFormat dateOnly = DateFormat.getDateInstance(DateFormat.SHORT);
	private List<Object> LevelPact = new ArrayList<>();
	private List<Object> TankLevels = new ArrayList<>();
	private List<Integer> IntTank = new ArrayList<>();
	private List<Integer> IntGray = new ArrayList<>();
	private List<String> dates = new ArrayList<>();
	private UniversalDateFormatList convert = new UniversalDateFormatList();
	private JPanel panel = new JPanel();
	private JSlider slider = new JSlider();
	private JLabel label = new JLabel("<<");
	private JRadioButton rdbtnShowUnitView = new JRadioButton("Show Unit View");
	private JRadioButton rdbtnShowVolumeView = new JRadioButton("Show Volume View");
	private JLabel lblSelectViewBelow = new JLabel("Select View below");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private String dataTank, infoTank;
	
	public TankAngel(EspDerby derby, String tank) throws SQLException {
		setLayout(new BorderLayout(0, 0));
		
		add(panel, BorderLayout.SOUTH);
		
		label.setToolTipText("previous");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(label);
		
		JLabel label_2 = new JLabel("  ");
		panel.add(label_2);
		
		JLabel label_1 = new JLabel(">>");
		label_1.setToolTipText("next");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(label_1);
		
		JLabel label_3 = new JLabel("  ");
		panel.add(label_3);
		
		JLabel lblDays = new JLabel("Days:");
		panel.add(lblDays);
		slider.setValue(30);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(10);
		slider.setMaximum(60);
		
		panel.add(slider);
		
		buttonGroup.add(rdbtnShowUnitView);
		panel.add(rdbtnShowUnitView);
		
		buttonGroup.add(rdbtnShowVolumeView);
		panel.add(rdbtnShowVolumeView);
		
		lblSelectViewBelow.setBackground(new Color(0, 102, 153));
		lblSelectViewBelow.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSelectViewBelow, BorderLayout.CENTER);
		
		
		getWhos(tank);
		
		ResultSet stat = derby.accessDerby("RawDataTable");
		
		while(stat.next()){
			int Level = stat.getInt(dataTank);
			String date = stat.getString("COMPUTERTIME");
			IntTank.add(Level);
			
			try {
				dates.add(dateOnly.format(getDateForm17.parse(date)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			TankLevels.add(Level);
			TankLevels.add(date);
		}
		
		
		checkDates(IntTank, tank);
		
		LevelPact = processLevels(IntTank, tank);
		System.out.println(LevelPact);
	}
	

	
	private int checkDates(List<Integer> intTank, String tank) {
		int y=0;
		for (int q =0; q<dates.size(); q++){
			try{
			Date init = new SimpleDateFormat("dd/MM/yy").parse(dates.get(q));
			Date finit = new SimpleDateFormat("dd/MM/yy").parse(dates.get(q+1));
			long daysBtw = finit.getTime()-init.getTime();
			int  diff =(int) daysBtw/86400000;
			if(diff>1){
				y=augmentDates(init, finit,q, diff, intTank.get(q+1), intTank.get(q));
			}
		}catch(Exception a){System.out.println(a.getMessage());}
		}
		return y;
	}



	private int augmentDates(Date init,Date finit, int q, int diff, Integer spread, Integer build) throws ParseException {
		
		long sf = init.getTime();
		int ave = (int)Math.round(spread/diff);
		
		for(int r=1; r<diff; r++){
		build+=ave;
		if(build>spread)build = spread;
		IntTank.add(q+r, build);
		Date d = new Date(sf+=86400000);
		dates.add(q+r, dateOnly.format(d));
		}
		
		return q;
	}

	private List<Object> processLevels(List<Integer> toProcess, String tank) {
		List<Object> harmony = new ArrayList<>();
		minimumUnitRecorded = getMinValue(toProcess);
		maximumUnitRecorded = getMaxValue(toProcess);
		System.out.println("minimum="+minimumUnitRecorded);
		System.out.println("maximum="+maximumUnitRecorded);
		
		for (int q =0; q<toProcess.size(); q++){
			try{
				int fwd=0, prv=0;
				if(tank.equals("blue")){
					fwd = processBlueUnit(toProcess.get(q+1));
					prv = processBlueUnit(toProcess.get(q));
					minimumVolume = processBlueUnit(minimumUnitRecorded);
					maximumVolume = processBlueUnit(maximumUnitRecorded);
				}else{
					fwd = processGrayUnit(toProcess.get(q+1));
					prv = processGrayUnit(toProcess.get(q));
					minimumVolume = processBlueUnit(minimumUnitRecorded);
					maximumVolume = processBlueUnit(maximumUnitRecorded);
				}

				int aDayUse = prv-fwd;
				if(aDayUse<0)aDayUse=0;
				harmony.add(aDayUse);
				harmony.add(dates.get(q));
			}catch(Exception d){
				harmony = getDailyStorageAverage(harmony, toProcess);
				System.out.println("after>"+harmony);
			}
		}
		return harmony;
	}
	

	int minimumUnitRecorded = 0;									//to cater for if the entry did not start from full tank
	int minimumVolume = 0;
	
	int maximumUnitRecorded = 0;									//to cater for if the entry did not start from full tank
	int maximumVolume = 0;
	private List<Object> getDailyStorageAverage(List<Object> harmony, List<Integer> toProcess) {
		int totalVolume=0;
		System.out.println("minimumVolumeStarted="+minimumVolume);
		int firstLev = 5000-minimumVolume;
		System.out.println("Already Consumed="+firstLev);
		int totalRecConsumed = minimumVolume-maximumVolume;
		System.out.println("Logged Consumed="+totalRecConsumed);
		int remaining = 5000 - maximumVolume;
		for(int t =0; t<harmony.size(); t++){
			if(harmony.get(t) instanceof Integer){
				totalVolume+= (int)harmony.get(t);
				System.out.println(t+"="+harmony.get(t)+", totalVolume="+(totalVolume+firstLev));
			}
		}
		
		
		return null;
	}



	public List<Integer> plotVolume(List<Integer> plot, String tank){
		for(int c = 0; c<plot.size(); c++){
			if(tank.equals("blue")){
				int val = processBlueUnit(plot.get(c));
				plot.remove(c);
				plot.add(c, val);
			}else{
				int val = processGrayUnit(plot.get(c));
				plot.remove(c);
				plot.add(c, val);
			}
		}
		return plot;
	}

	int pastBl=0; 
	public int processBlueUnit(int unit){
		if(unit>283)unit = pastBl;
		if(unit<0)unit = pastBl;
		double h = 287.945 - unit;					//283 is the assigned height of blue tank, actual is 240 //V=xr2h
		double pi = Math.PI;
		double r = 75*75;
		double V_ = pi*r*h;
		V_ = V_/1000;
		int V = (int) Math.round(V_);
		pastBl = unit;						//manage the catastrophe
		return V;
	}
	
	int pastGr=0;
	public int processGrayUnit(int unit){
		if(unit>210)unit = pastGr;
		if(unit<0)unit = pastGr;
		int h = (212 - unit), l = 134;				//210 is the assigned height of gray tank, actual is 180 //V=xr2h
		double b=71.09;
		
		double V_ = l*b*h;
		V_ = V_/1000; 
		int V = (int) Math.round(V_);
		pastGr = unit;					//manage the catastrophe
		return V;
	}
	
	private int getMinValue(List<Integer> toProcess) {
		 List<Integer> values = new ArrayList<>();
	    	for(int c=0; c<toProcess.size(); c++){
	    	if(toProcess.get(c) instanceof Integer){
					values.add((int)toProcess.get(c));
				}
	    	}
	        int minValue = Collections.min(values);
	        return minValue;

	    }
	
	 private int getMaxValue(List<Integer> toProcess) {
	    	List<Integer> values = new ArrayList<>();
	    	for(int c=0; c<toProcess.size(); c++){
	    	if(toProcess.get(c) instanceof Integer){
					values.add((int)toProcess.get(c));
				}
	    	}
	    	
	        int maxValue = Collections.max(values);

	        return maxValue;
	    }
	 
private String getWhos(String toPlot) {
		
		switch(toPlot){
		case "blue":
			dataTank = "BLUETANKLEVEL";
			infoTank = "Blue Tank Fuel Usage";
			break;
		case "gray":
			dataTank = "GRAYTANKLEVEL";
			infoTank = "Gray Tank Fuel Usage";
			break;
			default:
				break;
		}
		
		return toPlot;
	}

	private static EspDerby derb = new EspDerby();
	public static void main(String[] args) {
		try {
			TankAngel getTanks = new TankAngel(derb, "blue");
			//getTanks.processTankUnit(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
