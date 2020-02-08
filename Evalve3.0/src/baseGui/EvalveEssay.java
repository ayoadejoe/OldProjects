package baseGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class EvalveEssay extends JFrame{
	private StdSubmissionsClient subclient = new StdSubmissionsClient();
	private static FileSerializer getQuestions = new FileSerializer();
	private String Filename = "";
	private static ArrayList <Object> QArray = new ArrayList <Object>();
	private static Vector <Object> AnswerArray = new Vector <Object>();
	private static int SW; double inicialS, finalS;
	private static int SH; private int t1, t2, t3, t4;
	private JLabel Lane = new JLabel("Questions                                                  " +
			"                                        Type your answers in these boxes.");
	private JButton closePic = new JButton("CLOSE");
	private JFileChooser fc = null; private File file = null; private String imageName = null, DT = null;
	private JLabel image = new JLabel(""), image2 = new JLabel(""), image3 = new JLabel(""), image4 = new JLabel(""), image5 = new JLabel("");
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
	private CaseLoader brief = new CaseLoader();
	private JLabel Heading = new JLabel("");
	private JLabel HeadLow = new JLabel("");
	private JLabel Instruction;
	private JTextArea Question1, Question2, Question3, Question4, Question5;
	private JScrollPane Q1Pane, Q2Pane, Q3Pane, Q4Pane, Q5Pane;
	private JTextArea Answer1, Answer2, Answer3, Answer4, Answer5;
	private JScrollPane A1Pane, A2Pane, A3Pane, A4Pane, A5Pane;
	private int TextCol, TextRow, bookmark;
	private JLabel no = new JLabel("");
	private JButton Diagram1 = new JButton("1"), Diagram2 = new JButton("2"), Diagram3 = new JButton("3"),
			Diagram4 = new JButton("4"), Diagram5 = new JButton("5");
	private String topic, question = "", answer = "";
	private ImageIcon iconLoad1 = new ImageIcon("forward.png"); 
	private JLabel next = new JLabel(iconLoad1);
	
	private ImageIcon iconLoad2 = new ImageIcon("back.png"); 
	private JLabel prev = new JLabel(iconLoad2);
	
	private GridBagConstraints align = new GridBagConstraints();
	public EvalveEssay(final int IDNo, final int NoQ, final int pointing, final int weighting, final int BookMark, 
			final String Work, final String Username, final String Clazz, final String Subject, final String Examday, 
			final String Examtime, final String Duration, final String Term, final String Topic, 
			final ArrayList qArray, final int Week, final String Examiner, final Vector answerArray, 
			final int baseno, final String filenam) {
		 super(Subject.toUpperCase()+" "+Work.toUpperCase()+" FOR WEEK: "+Week);
		 
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		getContentPane().setBackground(new Color(120, 120, 250));
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		inicialS = 956.0;
		SW = size.width;  finalS= SW;
		SH = size.height; 
		TextCol = (int) (SW/50);
		TextRow = (int) (SH/120);
		setSize(SW, SH);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(BookMark == 0){
			bookmark = 0;
			prev.setVisible(false);
		}else{bookmark = BookMark;}
		
		if(bookmark < NoQ ){  					// initialize page if number is within range
		int tLen = Topic.length();				//Header
		if(tLen > 25) {
			topic = Topic.substring(0, 23)+"...";
		}else{topic = Topic;}
		
		if( answerArray != null){			// initiate ArrayList to lift out Array 
		QArray = qArray;
		AnswerArray =  answerArray;
		//("Not nil");
		}else{
			//(" nil");
			int rollno = 0;
			while(rollno <(NoQ)){		//+5 is for diagram
				 AnswerArray.add(rollno);
				rollno++;
			}
		}
		
		next.setText("");			
		next.setIcon(iconLoad1);
		next.setHorizontalTextPosition( SwingConstants.CENTER );
		next.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		prev.setText("");
		prev.setIcon(iconLoad2);
		prev.setHorizontalTextPosition( SwingConstants.CENTER );
		prev.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		Heading.setText(Subject.toUpperCase()+" "+Work.toUpperCase()+" : "+Topic.toUpperCase());
		if(Work.trim().equalsIgnoreCase("assignment")){
		HeadLow.setText("To be submitted before: "+Examday.toLowerCase()+" by "+Examtime);
		}else{
		HeadLow.setText("Schedule: "+Examday+" Time: "+Examtime);
		}
		Heading.setFont(new Font("serif", Font.BOLD, 22));
		HeadLow.setFont(new Font("serif", Font.BOLD, 16));
		Instruction = new JLabel("<>");
		//1366, 768
		t3 = resizer(600);
		align.insets = new Insets(0, 0, 600, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Instruction, align);
		
		t3 = resizer(650);
		align.insets = new Insets(0, 0,650, 0);
		add(HeadLow, align);
	
		t2 = resizer(620);	t4 = resizer(800);
		align.insets = new Insets(0, 0, 620, (int)t4);
		add(prev, align);
		align.insets = new Insets(0, (int)t2, 620, 0);
		add(next, align);
		question = "";
		if(!QArray.get(0).toString().trim().equals("0")){
			Instruction.setText(QArray.get(0).toString());
			QArray.remove(0);
			question = Instruction.getText(); 
			QArray.add(0, question);
		}else{
			QArray.remove(0); 
			Instruction.setText("Type your instructions here.");
			question = Instruction.getText(); 
			QArray.add(0, question);
		}
		
		bookmark = bookmark+1; 
		no.setText(bookmark+".");
		Diagram1.setText(bookmark+"");
		Image passp;
		try {
			passp = geticon(bookmark, NoQ, Subject);
			Image scaled = passp.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
			image.setIcon(new ImageIcon(scaled));
			image.setVisible(true);
			Diagram1.setText("View"+bookmark);
		} catch (IOException e) {}
		Diagram1.setBackground(Color.ORANGE);
		
		Question1 = new JTextArea(TextRow, TextCol);
		Question1.setLineWrap(true);
		Question1.setWrapStyleWord(true);
		Question1.setEditable(false);
		Q1Pane = new JScrollPane(Question1);
		Question1.setText(QArray.get(bookmark)+"");
		Question1.setBackground(new Color(120, 120, 250));
		Answer1 = new JTextArea(TextRow, TextCol+25);
		Answer1.setLineWrap(true);
		Answer1.setWrapStyleWord(true);
		A1Pane = new JScrollPane(Answer1);
		Answer1.setBackground(Color.white);
		Answer1.setText(AnswerArray.get(bookmark-1)+"");
		Answer1.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				Answer1.setBackground(Color.white);
			}
			public void focusLost(FocusEvent arg0) {
				int spot = bookmark-5; 
				answer = Answer1.getText();
				AnswerArray.remove(spot);
				AnswerArray.add(spot, answer);
				subclient.essaysubmissions(filenam, Clazz, IDNo, Subject, "Essay"
							, Examtime, Examday, Duration, baseno, AnswerArray, "UnsignedFocus", weighting+"");
			}
		});
		
		t1 = resizer(100);  t4 = resizer(640);
		align.insets = new Insets(0, 0, 430, (int)t4);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Q1Pane, align);
		//(t1+", t4="+t3+", inicial="+inicialS+", final="+finalS);
		align.insets = new Insets(0, (int)t1, 430, 0);
		add(A1Pane, align);
		
		t3 = resizer(560); t4 = resizer(300);
		align.insets = new Insets(0, 0, 560, (int)t4);
		Lane.setForeground(Color.white);
		add(Lane, align);
		
		t2 = resizer(800); t3 = resizer(480); t4 = resizer(640);
		align.insets = new Insets(0, (int)t2, 480, 0);
		add(Diagram1, align);
		t2 = resizer(660);
		align.insets = new Insets(0, (int)t2, 480, 0);
		add(image, align);
		t4 = resizer(900);
		align.insets = new Insets(0, 0, 480, (int)t4);
		add(new JLabel(bookmark+"."), align);
		
		bookmark = bookmark+1;  
		no.setText(bookmark+".");
		Diagram2.setText(bookmark+"");
		Image passp2;
		try {
			passp2 = geticon(bookmark, NoQ, Subject);
			Image scaled = passp2.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
			image2.setIcon(new ImageIcon(scaled));
			image2.setVisible(true);
			Diagram2.setText("View"+bookmark);
		} catch (IOException e) {}
		Diagram2.setBackground(Color.blue);
		Question2 = new JTextArea(TextRow, TextCol);
		Question2.setLineWrap(true);
		Question2.setEditable(false);
		Question2.setWrapStyleWord(true);
		Q2Pane = new JScrollPane(Question2);
		Question2.setText(QArray.get(bookmark)+"");
		Question2.setBackground(new Color(120, 120, 250));
		Answer2 = new JTextArea(TextRow, TextCol+25);
		Answer2.setLineWrap(true);
		Answer2.setWrapStyleWord(true);
		A2Pane = new JScrollPane(Answer2);
		Answer2.setBackground(Color.white);
		Answer2.setText(AnswerArray.get(bookmark-1)+"");
		Answer2.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				Answer2.setBackground(Color.white);
			}
			public void focusLost(FocusEvent arg0) {
				int spot = bookmark-4;
				answer = Answer2.getText();
				AnswerArray.remove(spot);
				AnswerArray.add(spot, answer);
				subclient.essaysubmissions(filenam, Clazz, IDNo, Subject, "Essay"
							, Examtime, Examday, Duration, baseno, AnswerArray, "UnsignedFocus", weighting+"");
			}
		});
		t4 = resizer(640);
		align.insets = new Insets(0, 0, 180, t4);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Q2Pane, align);
		
		t2 = resizer(100);
		align.insets = new Insets(0, (int)t2, 180, 0);
		add(A2Pane, align);
		
		t2 = resizer(800);
		align.insets = new Insets(0, (int) t2, 200, 0);
		add(Diagram2, align);
		t2 = resizer(660);
		align.insets = new Insets(0, (int)t2, 200, 0);
		add(image2, align);
		t4 = resizer(900);
		align.insets = new Insets(0, 0, 200, (int)t4);
		add(new JLabel(bookmark+"."), align);
		
		
		bookmark = bookmark+1;  
		no.setText(bookmark+".");
		Diagram3.setText(bookmark+"");
		Image passp3;
		try {
			passp3 = geticon(bookmark, NoQ, Subject);
			Image scaled = passp3.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
			image3.setIcon(new ImageIcon(scaled));
			image3.setVisible(true);
			Diagram3.setText("View"+bookmark);
		} catch (IOException e) {}
		Diagram3.setBackground(Color.GREEN);
		Question3 = new JTextArea(TextRow, TextCol);
		Question3.setLineWrap(true);
		Question3.setEditable(false);
		Question3.setWrapStyleWord(true);
		Q3Pane = new JScrollPane(Question3);
		Question3.setText(QArray.get(bookmark)+"");
		Question3.setBackground(new Color(120, 120, 250));
		Answer3 = new JTextArea(TextRow, TextCol+25);
		Answer3.setLineWrap(true);
		Answer3.setWrapStyleWord(true);
		A3Pane = new JScrollPane(Answer3);
		Answer3.setBackground(Color.white);
		Answer3.setText(AnswerArray.get(bookmark-1)+"");
		Answer3.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				Answer3.setBackground(Color.white);
			}
			public void focusLost(FocusEvent arg0) {
				int spot = bookmark - 3;
				answer = Answer3.getText();
				AnswerArray.remove(spot);
				AnswerArray.add(spot, answer);
				subclient.essaysubmissions(filenam, Clazz, IDNo, Subject, "Essay"
							, Examtime, Examday, Duration, baseno, AnswerArray, "UnsignedFocus", weighting+"");
			}
		});
		align.insets = new Insets(70, 0, 0, resizer(640));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Q3Pane, align);
		
		align.insets = new Insets(70, resizer(100), 0, 0);
		add(A3Pane, align);
		
		align.insets = new Insets(50, resizer(800), 0, 0);
		add(Diagram3, align);
		align.insets = new Insets(50, resizer(660), 0, 0);
		add(image3, align);
		align.insets = new Insets(50, 0, 0, resizer(900));
		add(new JLabel(bookmark+"."), align);
		
		
		bookmark = bookmark+1;   
		no.setText(bookmark+".");
		Diagram4.setText(bookmark+"");
		Image passp4;
		try {
			passp4 = geticon(bookmark, NoQ, Subject);
			Image scaled = passp4.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
			image4.setIcon(new ImageIcon(scaled));
			image4.setVisible(true);
			Diagram4.setText("View"+bookmark);
		} catch (IOException e) {}
		Diagram4.setBackground(Color.RED);
		Question4 = new JTextArea(TextRow, TextCol);
		Question4.setLineWrap(true);
		Question4.setEditable(false);
		Question4.setWrapStyleWord(true);
		Q4Pane = new JScrollPane(Question4);
		Question4.setText(QArray.get(bookmark)+"");
		Question4.setBackground(new Color(120, 120, 250));
		Answer4 = new JTextArea(TextRow, TextCol+25);
		Answer4.setLineWrap(true);
		Answer4.setWrapStyleWord(true);
		A4Pane = new JScrollPane(Answer4);
		Answer4.setBackground(Color.white);
		Answer4.setText(AnswerArray.get(bookmark-1)+"");
		Answer4.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				Answer4.setBackground(Color.white);
			}
			public void focusLost(FocusEvent arg0) {
				int spot = bookmark - 2;
				answer = Answer4.getText();
				AnswerArray.remove(spot);
				AnswerArray.add(spot, answer);
				subclient.essaysubmissions(filenam, Clazz, IDNo, Subject, "Essay"
							, Examtime, Examday, Duration, baseno, AnswerArray, "UnsignedFocus", weighting+"");
			}
		});
		align.insets = new Insets(310, 0, 0, resizer(640));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Q4Pane, align);
		
		align.insets = new Insets(310, resizer(100), 0, 0);
		add(A4Pane, align);
		
		align.insets = new Insets(290, resizer(800), 0, 0);
		add(Diagram4, align);
		align.insets = new Insets(290, resizer(660), 0, 0);
		add(image4, align);
		align.insets = new Insets(290, 0, 0, resizer(900));
		add(new JLabel(bookmark+"."), align);
		
		bookmark = bookmark+1;  
		no.setText(bookmark+".");
		Diagram5.setText(bookmark+"");
		Image passp5;
		try {
			passp5 = geticon(bookmark, NoQ, Subject);
			Image scaled = passp5.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
			image5.setIcon(new ImageIcon(scaled));
			image5.setVisible(true);
			Diagram5.setText("View"+bookmark);
		} catch (IOException e) {}
		Diagram5.setBackground(Color.CYAN);
		Question5 = new JTextArea(TextRow, TextCol);
		Question5.setLineWrap(true);
		Question5.setEditable(false);
		Question5.setWrapStyleWord(true);
		Q5Pane = new JScrollPane(Question5);
		Question5.setText(QArray.get(bookmark)+"");
		Question5.setBackground(new Color(120, 120, 250));
		Answer5 = new JTextArea(TextRow, TextCol+25);
		Answer5.setLineWrap(true);
		Answer5.setWrapStyleWord(true);
		A5Pane = new JScrollPane(Answer5);
		Answer5.setBackground(Color.white);
		Answer5.setText(AnswerArray.get(bookmark-1)+"");
		Answer5.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				Answer5.setBackground(Color.white);
			}
			public void focusLost(FocusEvent arg0) {
				int spot = bookmark - 1;
				answer = Answer5.getText();
				AnswerArray.remove(bookmark);
				AnswerArray.add(bookmark, answer);
				subclient.essaysubmissions(filenam, Clazz, IDNo, Subject, "Essay"
							, Examtime, Examday, Duration, baseno, AnswerArray, "UnsignedFocus", weighting+"");
			}
		});
		align.insets = new Insets(560, 0, 0, resizer(640));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Q5Pane, align);
		
		align.insets = new Insets(560, resizer(100), 0, 0);
		add(A5Pane, align);
		
		align.insets = new Insets(540, resizer(800), 0, 0);
		add(Diagram5, align);
		align.insets = new Insets(540, resizer(660), 0, 0);
		add(image5, align);
		align.insets = new Insets(540, 0, 0, resizer(900));
		add(new JLabel(bookmark+"."), align);
		////(bookmark+", end "+QArray);
		}else{
			setLayout(new GridBagLayout());
			int resp = JOptionPane.showConfirmDialog(null, "Are you done?");
			if(resp == 0){
				JLabel end = new JLabel("Your work has been saved. Goodbye!");
				align.insets = new Insets(0, resizer(0), 0, resizer(0));
				align.anchor = GridBagConstraints.CENTER;
				align.gridx = 0;
				align.gridy = 0;
				align.weightx = 0;
				align.weighty = 0;
				align.fill = GridBagConstraints.NONE;
				add(end, align);
				
				////(qArray);
			}else{
				prev.setText("");
				prev.setIcon(iconLoad2);
				prev.setHorizontalTextPosition( SwingConstants.CENTER );
				prev.setVerticalTextPosition( SwingConstants.BOTTOM );
				
				
				align.insets = new Insets(0, 0, 100, 0);		// format: push(DOWN, right, UP, left)
				align.anchor = GridBagConstraints.CENTER;
				align.gridx = 0;
				align.gridy = 0;
				align.weightx = 0;
				align.weighty = 0;
				align.fill = GridBagConstraints.NONE;
				add(prev, align);
			}
			
		}
		setVisible(true);
		
		
//__________________________________________________________________________________________________________________
		
		next.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				//(bookmark+" -- "+AnswerArray);
				answer = Answer1.getText();
				AnswerArray.remove(bookmark-5);
				AnswerArray.add(bookmark-5, answer);
				answer = Answer2.getText();
				AnswerArray.remove(bookmark-4);
				AnswerArray.add(bookmark-4, answer);
				answer = Answer3.getText();
				AnswerArray.remove(bookmark-3);
				AnswerArray.add(bookmark-3, answer);
				answer = Answer4.getText();
				AnswerArray.remove(bookmark-2);
				AnswerArray.add(bookmark-2, answer);
				answer = Answer5.getText();
				AnswerArray.remove(bookmark-1);
				AnswerArray.add(bookmark-1, answer);
				subclient.essaysubmissions(filenam, Clazz, IDNo, Subject, "Essay"
							, Examtime, Examday, Duration, baseno, AnswerArray, "UnsignedFocus", weighting+"");
				dispose();
				new EvalveEssay(IDNo, NoQ,   pointing,   weighting,   bookmark, Work,   Username,   Clazz,   Subject,   Examday, 
						  Examtime,   Duration,   Term,   Topic, QArray,   Week,   Examiner,  AnswerArray, baseno, filenam);
			}
			public void mouseEntered(MouseEvent arg0) {
				next.setText("Next Page");
			}
			public void mouseExited(MouseEvent arg0) {
				next.setText("");
		}
	});
		
		
		prev.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				dispose();
					QArray = qArray;
					AnswerArray = answerArray;
					new EvalveEssay(IDNo, NoQ,   pointing,   weighting,   bookmark-10, Work,   Username,   Clazz,   Subject,   Examday, 
							  Examtime,   Duration,   Term,   Topic, QArray,   Week,   Examiner,  AnswerArray, baseno, filenam);
			}
			public void mouseEntered(MouseEvent arg0) {
				prev.setText("Previous");
			}
			public void mouseExited(MouseEvent arg0) {
				prev.setText("");
		}
	});
		
		Diagram1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Image passp = null;
				try {
					passp = geticon(bookmark-4, NoQ, Subject);
				} catch (IOException e) {}
				new PictureLoader(null, (bookmark-4)+"", QArray.get(bookmark-4).toString(), passp );
			}
		});
		
		Diagram2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Image passp = null;
				try {
					passp = geticon(bookmark-3, NoQ, Subject);
				} catch (IOException e) {}
				new PictureLoader(null, (bookmark-3)+"", QArray.get(bookmark-3).toString(), passp );
			}
		});
		
		Diagram3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Image passp = null;
				try {
					passp = geticon(bookmark-2, NoQ, Subject);
				} catch (IOException e) {}
				new PictureLoader(null, (bookmark-2)+"", QArray.get(bookmark-2).toString(), passp );
			}
		});
		
		Diagram4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Image passp = null;
				try {
					passp = geticon(bookmark-1, NoQ, Subject);
				} catch (IOException e) {}
				new PictureLoader(null, (bookmark-1)+"", QArray.get(bookmark-1).toString(), passp );
			}
		});
		
		Diagram5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Image passp = null;
				try {
					passp = geticon(bookmark, NoQ, Subject);
				} catch (IOException e) {}
				new PictureLoader(null, (bookmark)+"", QArray.get(bookmark).toString(), passp );
			}
		});
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				String thisfile = Week+"+"+Work+"+"+"Essay"+"+"+Clazz;
				//("in delete");
				File file5 = new File("C:\\LocalSubjects\\"+Subject+"\\"+Work+"\\"+thisfile);
				//("Trying to delete "+file5.getAbsolutePath());
				if(file5.exists()){
					System.gc();
					file5.deleteOnExit();
				}
			}
		});
	}
	
	
	private static void clear(File toPreview) {
		 if(toPreview.exists()){
			  File gry = new File ((toPreview.getAbsolutePath()+"unlocked.evl"));
			  System.gc();
			  gry.delete();
			  //("deleted "+gry.getAbsolutePath());
			}
	}

	private static File Q() {
		String thisfile = "4+Test+Essay+SSS3";
		File toPreview = new File("C:\\LocalSubjects\\English\\Test\\"+thisfile+"+locked");
        if(!toPreview.exists()){
        	//("File for Evalve does not exist: "+toPreview);
        	thisfile = "4+Test+Essay+SSS3.evl";
        }else{
        	//("File for Evalve exist: "+toPreview);
        }
		return toPreview;
	}

	private Image geticon(int bookmark, int NoQ, String Subject) throws IOException {
		String imageName = QArray.get(bookmark+NoQ).toString().trim();
		File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName);
		BufferedImage passp = null;
		passp = ImageIO.read(dest); 
		return passp;
	}
	
	private int resizer(int inicialposition){
		int newposition = (int)(inicialposition*(finalS/inicialS));
		//(newposition);
		return newposition;
	}
	
	public static void main(String[] args) {
		String thisfile = "4+Test+Essay+SSS3";
		File parametr = new File("C:\\Teachers\\Briefcase\\Essays\\"+thisfile+".evl+locked");
		ArrayList parameters = new ArrayList();
		try {
			parameters = getQuestions.loadFromFile(parametr);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		//(parameters.toString());
		
		
		
		 int IDNo = Integer.parseInt(parameters.get(0).toString().trim());
		 int pointing = Integer.parseInt(parameters.get(10).toString().trim());
		 int weighting = Integer.parseInt(parameters.get(15).toString().trim());
		 int Week = Integer.parseInt(parameters.get(14).toString().trim());
		 int NoQ = Integer.parseInt(parameters.get(9).toString().trim());
		 int BookMark = 0;
		 String Work = parameters.get(1).toString().trim();
		 String Username = parameters.get(2).toString().trim();
		 String Clazz = parameters.get(3).toString().trim();
		 String Subject = parameters.get(4).toString().trim();
		 String Examday = parameters.get(5).toString().trim();
		 String Examtime = parameters.get(6).toString().trim();
		 String Duration = parameters.get(7).toString().trim();
		 String Term = parameters.get(8).toString().trim();
		 String Topic = parameters.get(13).toString().trim();
		 String Examiner = "";
		 Vector  AnswerArray = null;
		 String filename = "";
		 int baseno = 0;
		 File toPreview = Q();
			try {
				QArray = getQuestions.loadFromFile(toPreview);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			//("Launching Essay");
			clear(toPreview);
			
		new EvalveEssay(IDNo, NoQ,   pointing,   weighting,   BookMark, Work,   Username,   Clazz,   Subject,   Examday, 
				  Examtime,   Duration,   Term,   Topic, QArray,   Week,   Examiner,  AnswerArray, baseno, filename);
	}
}
