package baseGui;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class SelectionTest extends JDialog{
	private Color bakG2;
	private PrintStream stream;
	private JSpinner day, point, Wgt;
	private SpinnerNumberModel dayMod, pointMod, wgt;
	private JSpinner month, loadNo;
	private SpinnerNumberModel monthMod, loadMod;
	private JSpinner year, hr, min, duraMin, duraHr;
	private SpinnerNumberModel yearMod, hrMod, minMod, dMinMod, dHrMod;
	
	private JTextField examiner;
	private JRadioButton mcq, essay;
	
	private JLabel type, date, time, dura, noload,  weight,
	examr, header, schd,  TimNote, DuNote, PointC;
	private JButton entr = new JButton("ENTER");
	private ButtonGroup group;
	
	public SelectionTest(LoadQuestions parent, final int IDNo, final String Work, final String Username, final String Topic,
			final String Subjct, final int Week, final String Clzz, final String Term) {
		super();
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setSize(600, 400);
		bakG2 = new Color(100, 100, 200);
		getContentPane().setBackground(bakG2);
		setLayout(new GridBagLayout());
		GridBagConstraints align = new GridBagConstraints();
		setLocationRelativeTo(getParent());
		
		String Tpic;
		int T = Topic.length();
		if(T>25){
			Tpic = Topic.substring(0, 23)+"...";
		}else{
			Tpic = Topic;
		}
		
		Font but = new Font("SERIF", Font.BOLD, 16);
		entr.setFont(but);
		entr.setForeground(Color.RED);
		header = new JLabel("SCHEDULE FOR "+Tpic.toUpperCase());
		Font newFont = new Font("SERIF", Font.BOLD, 23);
		header.setFont(newFont);
		align.insets = new Insets(0, 0, 30, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(header, align);
		
		Font font = new Font("SERIF", Font.BOLD, 17);
		
		schd = new JLabel("Work Schedule");
		schd.setFont(font);
		align.insets = new Insets(0, 0, 0, 220);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 3;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(schd, align);
		
		type = new JLabel("Type of Paper");
		type.setFont(font);
		align.insets = new Insets(0, 380, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 3;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(type, align);
		
		date = new JLabel("Date:");
		date.setFont(font);
		align.insets = new Insets(0, 0, 0, 300);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(date, align);
		
		dayMod = new SpinnerNumberModel(1, 1, 30, 1); //default, minimum, maximum, increment
		day = new JSpinner(dayMod);
		align.insets = new Insets(0, 0, 0, 200);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(day, align);
		
		monthMod = new SpinnerNumberModel(1, 1, 12, 1);//default, minimum, maximum, increment
		month = new JSpinner(monthMod);
		align.insets = new Insets(0, 0, 0, 120);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(month, align);
		
		yearMod = new SpinnerNumberModel(2014, 2014, 2050, 1);//default, minimum, maximum, increment
		year = new JSpinner(yearMod);
		align.insets = new Insets(0, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(year, align);
		
		mcq = new JRadioButton("M.C.Q");
		mcq.setActionCommand("M.C.Q");
		align.insets = new Insets(10, 380, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(mcq, align);
		
		essay = new JRadioButton("Essay");
		essay.setActionCommand("Essay");
		align.insets = new Insets(40, 380, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(essay, align);
		
		PointC = new JLabel("Pointing|");
		Font fontP = new Font("SERIF", Font.ITALIC, 16);
		PointC.setFont(fontP);
		align.insets = new Insets(80, 330, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(PointC, align);
		
		weight = new JLabel("Weight|");
		Font fontW = new Font("SERIF", Font.ITALIC, 16);
		weight.setFont(fontW);
		align.insets = new Insets(110, 330, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(weight, align);
		
		align.insets = new Insets(150, 450, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(entr, align);
		
		pointMod = new SpinnerNumberModel(1, 1, 50, 1);
		point = new JSpinner(pointMod);
		align.insets = new Insets(80, 440, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(point, align);
		
		wgt = new SpinnerNumberModel(1, 1, 50, 1);
		Wgt = new JSpinner(wgt);
		align.insets = new Insets(110, 440, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Wgt, align);
		
		
		
		time = new JLabel("Time:");
		time.setFont(font);
		align.insets = new Insets(30, 0, 0, 296);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(time, align);
		
		hrMod = new SpinnerNumberModel(8, 0, 23, 1); //default, minimum, maximum, increment
		hr = new JSpinner(hrMod);
		align.insets = new Insets(30, 0, 0, 200);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(hr, align);
		
		minMod = new SpinnerNumberModel(0, 0, 59, 1);//default, minimum, maximum, increment
		min = new JSpinner(minMod);
		align.insets = new Insets(30, 0, 0, 110);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(min, align);
		
		TimNote = new JLabel("| hour | min|");
		align.insets = new Insets(30, 0, 0, 5);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(TimNote, align);
		
		dura = new JLabel("Duration:");
		dura.setFont(font);
		align.insets = new Insets(90, 0, 0, 265);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(dura, align);
		
		dHrMod = new SpinnerNumberModel(1, 0, 23, 1);
		duraHr = new JSpinner(dHrMod);
		align.insets = new Insets(90, 0, 0, 140);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(duraHr, align);
		
		dMinMod = new SpinnerNumberModel(1, 0, 59, 1);
		duraMin = new JSpinner(dMinMod);
		align.insets = new Insets(90, 0, 0, 50);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(duraMin, align);
		
		DuNote = new JLabel("|hr|mm");
		align.insets = new Insets(90, 50, 0, 20);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(DuNote, align);
		
		
		noload = new JLabel("Questions:");
		noload.setFont(font);
		align.insets = new Insets(120, 0, 0, 264);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(noload, align);
		
		loadMod = new SpinnerNumberModel(10, 5, 500, 10);
		loadNo = new JSpinner(loadMod);
		align.insets = new Insets(120, 0, 0, 130);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(loadNo, align);
		
		examr = new JLabel("Supervisor:");
		examr.setFont(font);
		align.insets = new Insets(150, 0, 0,264);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(examr, align);
		
		examiner = new JTextField("Surname First");
		align.insets = new Insets(150, 0, 0, 100);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 4;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(examiner, align);
		
		group = new ButtonGroup();
		group.add(mcq);
		group.add(essay);
		
		entr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev1) {
				try{
				Timing time = new Timing();
				int datday = (Integer)day.getValue();
				int datmont = (Integer)month.getValue();
				int datyear = (Integer)year.getValue();
				time.setExamDay(datday, datmont, datyear);
				String Examday = time.getExamDay();
				
				int datHr = (Integer)hr.getValue();
				int datmin = (Integer)min.getValue();
				if (datmin<10 && datHr < 12){
					String datMin = "0"+datmin;
						String Ped = "AM";
						time.setExamTime(datHr, datMin, Ped);
					}
				if(datmin<10 && datHr >= 12){
						String Ped = "PM";
						String datMin = "0"+datmin;
						time.setExamTime(datHr, datMin, Ped);
					}
				if(datmin>=10 && datHr < 12){
					String Ped = "AM";
					String datMin = datmin+"";
					time.setExamTime(datHr, datMin, Ped);
				}
				if(datmin>=10 && datHr >= 12){
					String Ped = "PM";
					String datMin = datmin+"";
					time.setExamTime(datHr, datMin, Ped);
				}
				
				
					String Examtime = time.getExamTime();
				
				int datDhr = (Integer)duraHr.getValue();
				int datDmin = (Integer)duraMin.getValue();
				time.setDuration(datDhr, datDmin);
				String Duration = time.getDuration();
				////(Examday+" at "+Examtime+" for "+Duration);
				
				int NoQ = (Integer)loadNo.getValue();
				int Alt = NoQ;
				int pointing = (Integer)point.getValue();
				
				int weighting = (Integer)Wgt.getValue();
				
				String Terms = Term;
				////(Terms);
				String Examiner = examiner.getText();
				
				String ExamType = group.getSelection().getActionCommand();
				
				String ClassS = Clzz;
				String Subject = Subjct;
				
				//(ClassS+" "+Subject);
				try {
					stream = new PrintStream("numbering");
					stream.print(NoQ);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				int pt = 1;
				
				if(Work.equals("Test")){
				if(ExamType.equals("M.C.Q")){
					new mcqEngine(IDNo, NoQ, pointing, weighting, 0, 
							Work, Username, ClassS, Subject, Examday, Examtime, Duration, Term, Examiner, Topic, null, Week);
				}else if(ExamType.equals("Essay")){
					new EssayEngine(IDNo, NoQ, pointing, weighting, 0, 
							Work, Username, ClassS, Subject, Examday, Examtime, Duration, Term, Examiner, Topic, null, Week);
				}
				}else if (Work.equals("Assignment")){
					if(ExamType.equals("M.C.Q")){
						new mcqEngine(IDNo, NoQ, pointing, weighting, 0, 
								Work, Username, ClassS, Subject, Examday, Examtime, Duration, Term, Examiner, Topic, null, Week);
					}else if(ExamType.equals("Essay")){
						new EssayEngine(IDNo, NoQ, pointing, weighting, 0, 
								Work, Username, ClassS, Subject, Examday, Examtime, Duration, Term, Examiner, Topic, null, Week);
					}
				}
				setVisible(false);
				}catch(Exception e1){
					JOptionPane.showMessageDialog( null, "You have not selected either a class, subject " +
							"or type of exam.", "Parameter Ignore.",
		 					JOptionPane.ERROR_MESSAGE);		
					e1.printStackTrace();
				}
			}
			
		});
		
	}

}
