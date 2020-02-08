package baseGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

public class LessonPlan extends JFrame{
	private JLabel anstag;
	private JLabel  ics;
	private LessonPlanBar  collectLeft;
	private JLabel claz, subject,  username;
	private JTextField duration, perwk;
	private JTextArea Intro, PK, Obj, Summ, TM, CW, HW, Ans, Chall, Conclu,  AnsHw;
	private JScrollPane intro, pk, obj, summ, tm, cw, hw, ans, chall, conclu, anshw;
	private JLabel duratn, ppw, introduct, preknow, object, summry, teachaid, classw, homew, answer, challenge, conc;
	private JPanel LessonPlanLeft = new JPanel();
	private JPanel LessonPlanRight = new JPanel();
	private JPanel Toolbar = new JPanel();
	private JButton submit = new JButton("Submit");
	private JButton save = new JButton("Save");
	private JLabel time = new JLabel("");
	private Timer t ; private int r, s, x, u, v , w, y, z, a, b, c, d, e;
	private JLabel top;
	private JLabel week;
	private Vector <String> Giant = new Vector <String> ();
	private DiaryClient GiantLoader = new DiaryClient();
	private SelectionTest selectionTest;

	public LessonPlan(JFrame parent, final int iDNo, final String Subject, final String Clasz, final String Topic, 
			final int Week, final String username, final String term) {
		super( username.toUpperCase()+"'s "+Subject.toUpperCase()+" LESSON PLAN");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		setSize(1060, 690);
		setBackground(Color.white);
		setLocationRelativeTo(getParent());
		setVisible(true);
		setLayout(new BorderLayout());
		
		submit.setToolTipText("This will save and make the diary visible to the Admin and part of the Lesson note visible to Students.");
		save.setToolTipText("This will make the diary visible to All, but the note will be saved in your System");
		LessonPlanLeft.setSize(400, 610);
		Border innerBorder = BorderFactory.createRaisedSoftBevelBorder();
		Border outerBorder = BorderFactory.createEtchedBorder(10, Color.DARK_GRAY, Color.GRAY);
		AbstractButton createCompoundBorder;
		LessonPlanLeft.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		LessonPlanLeft.setLayout(new GridBagLayout());
		GridBagConstraints align1 = new GridBagConstraints();
		
		LessonPlanRight.setSize(550, 610);
		Border innerBorder2 = BorderFactory.createRaisedSoftBevelBorder();
		Border outerBorder2 = BorderFactory.createEtchedBorder(10, Color.DARK_GRAY, Color.GRAY);
		AbstractButton createCompoundBorder2;
		LessonPlanRight.setBorder(BorderFactory.createCompoundBorder(innerBorder2, outerBorder2));

		LessonPlanRight.setLayout(new GridBagLayout());
		GridBagConstraints align2 = new GridBagConstraints();
		
		String Top = " ";
		String ic = " ";
		int f = Topic.length();
		if(f<50){
			Top = Topic;
			ic = "*";
		}
		if(Topic.length()>50){
			if (f<=80){
			Top = Topic.substring(0, 40);
			ic = Topic.substring(40, f);
			}else if(f>80){
				Top = Topic.substring(0, 40);
				ic = Topic.substring(40, 78);
				ic = ic+"...";
			}
				
		}
		
		claz = new JLabel("CLASS: "+Clasz);		top = new JLabel(" *"+Top.toUpperCase()+" ");	
		ics = new JLabel("-"+ic.toUpperCase()+"*");	week = new JLabel("WEEK: "+Week+" ");
		claz.setFont(new Font("calibri", Font.BOLD, 20));
		top.setFont(new Font("calibri", Font.BOLD, 20));
		week.setFont(new Font("calibri", Font.BOLD, 20));
		ics.setFont(new Font("calibri", Font.BOLD, 20));
		
		duratn = new JLabel("Age: ");		ppw = new JLabel("Unit: ");
		duration = new JTextField("-", 10);  		perwk = new JTextField("-",6);
		duratn.setFont(new Font("calibri", Font.BOLD, 16));
		ppw.setFont(new Font("calibri", Font.BOLD, 16));
		duration.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
					duration.setText("");
			}
		});
		perwk.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
					perwk.setText("");
			}
		});
		align1.insets = new Insets(0, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(top, align1);
		
		align1.insets = new Insets(25, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(ics, align1);
		
		align1.insets = new Insets(50, 0, 0, 315);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(claz, align1);
		
		align1.insets = new Insets(50, 340, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(week, align1);
		
		align1.insets = new Insets(90, 120, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(duratn, align1);
		
		align1.insets = new Insets(90, 300, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(duration, align1);
		
		align1.insets = new Insets(90, 0, 0, 310);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(ppw, align1);
		
		align1.insets = new Insets(90, 0, 0, 140);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(perwk, align1);
		
		Intro = new JTextArea("-"); 					intro = new JScrollPane(Intro);			introduct = new JLabel("Introduction");
		Dimension dim11 = intro.getPreferredSize();  dim11.width = 200; 						dim11.height = 100;
		intro.setPreferredSize(dim11);				Intro.setBackground(Color.white);		Intro.setLineWrap(true);
		intro.setAutoscrolls(true);					Intro.setEditable(true);				Intro.setWrapStyleWord(true);
		Intro.setText("Write the introduction to the new topic here.");
		Intro.setFont(new Font("calibri", Font.PLAIN, 13));
		introduct.setFont(new Font("calibri", Font.BOLD, 16));
		Intro.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				s++;
				if(s==1){
				Intro.setText("");
				}
			}
			public void focusLost(FocusEvent arg0) {
				if(Intro.getText().length()>1000){
					JOptionPane.showMessageDialog(Intro, "Only 1000 Characters allowed. Please " +
							"summarize your work or your work will not be saved.", "("+Intro.getText().length()+" Characters) >>TOO MUCH WORDS IN INTRODUCTION", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		align1.insets = new Insets(140, 0, 0, 330);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(introduct, align1);
		
		align1.insets = new Insets(160, 0, 0, 220);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(intro, align1);
		
		
		PK = new JTextArea("-"); 					pk = new JScrollPane(PK);			preknow = new JLabel("Pre-Knowledge");
		Dimension dim21 = pk.getPreferredSize(); dim21.width = 200; 					dim21.height = 100;
		pk.setPreferredSize(dim21);				PK.setBackground(Color.white);		PK.setLineWrap(true);
		pk.setAutoscrolls(true);				PK.setEditable(true);				PK.setWrapStyleWord(true);
		PK.setText("Write the previous/basal knowledge about the new topic here.");
		PK.setFont(new Font("calibri", Font.PLAIN, 13));
		preknow.setFont(new Font("calibri", Font.BOLD, 16));
		PK.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				x++;
				if (x==1){
				PK.setText("");
				}
			}
			public void focusLost(FocusEvent arg0) {
				if(PK.getText().length()>1000){
					JOptionPane.showMessageDialog(PK, "Only 1000 Characters allowed. Please " +
							"summarize your work or your work will not be saved.", "("+PK.getText().length()+" Characters) >>TOO MUCH WORDS IN PREKNOWLEDGE", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		align1.insets = new Insets(140, 315, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(preknow, align1);
		
		align1.insets = new Insets(160, 220, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(pk, align1);
		
		Obj = new JTextArea(); 					obj = new JScrollPane(Obj);				object = new JLabel("Objectives");
		Dimension dim3 = obj.getPreferredSize();dim3.width = 425; 						dim3.height = 120;
		obj.setPreferredSize(dim3);				Obj.setBackground(Color.white);	Obj.setLineWrap(true);
		obj.setAutoscrolls(true);				Obj.setEditable(true);					Obj.setWrapStyleWord(true);
		Obj.setText("State the objectives of the topic here.");
		Obj.setFont(new Font("calibri", Font.PLAIN, 13));
		object.setFont(new Font("calibri", Font.BOLD, 16));
		Obj.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				u++;
				if(u == 1){
				Obj.setText("");
				}
			}
			public void focusLost(FocusEvent arg0) {
				if(Obj.getText().length()>1000){
					JOptionPane.showMessageDialog(Obj, "Only 1000 Characters allowed. Please " +
							"summarize your work or your work will not be saved.", "("+Obj.getText().length()+" Characters) >>TOO MUCH WORDS IN OBJECTIVES", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		align1.insets = new Insets(270, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(object, align1);
		
		align1.insets = new Insets(290, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(obj, align1);
		
		TM = new JTextArea("-"); 					tm = new JScrollPane(TM);				teachaid = new JLabel("Teaching Aids");
		Dimension dim4 = tm.getPreferredSize();	dim4.width = 150; 						dim4.height = 50;
		tm.setPreferredSize(dim4);				TM.setBackground(Color.LIGHT_GRAY);		TM.setLineWrap(true);
		tm.setAutoscrolls(true);				TM.setEditable(true);					TM.setWrapStyleWord(true);
		TM.setFont(new Font("calibri", Font.PLAIN, 13));
		teachaid.setFont(new Font("calibri", Font.BOLD, 16));
		TM.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				z++;
				if(z == 1){
				TM.setText("");
				}
			}
			public void focusLost(FocusEvent arg0) {
				if(TM.getText().length()>1000){
					JOptionPane.showMessageDialog(TM, "Only 1000 Characters allowed. Please " +
							"summarize your work or your work will not be saved.", "("+TM.getText().length()+" Characters) >>TOO MUCH WORDS IN TEACHING MAT.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		align1.insets = new Insets(410, 0, 0, 275);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(teachaid, align1);
		
		align1.insets = new Insets(430, 0, 0, 270);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(tm, align1);
		
		Chall = new JTextArea("-"); 				chall = new JScrollPane(Chall);		challenge = new JLabel("Character Pillar");
		Dimension dim5 = chall.getPreferredSize();dim5.width = 250; 						dim5.height = 50;
		chall.setPreferredSize(dim5);				Chall.setBackground(Color.LIGHT_GRAY);	Chall.setLineWrap(true);
		chall.setAutoscrolls(true);					Chall.setEditable(true);				Chall.setWrapStyleWord(true);
		Chall.setFont(new Font("calibri", Font.PLAIN, 13));
		challenge.setFont(new Font("calibri", Font.BOLD, 16));
		Chall.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				v++;
				if(v == 1){
				Chall.setText("");
				}
			}
			public void focusLost(FocusEvent arg0) {
				if(Chall.getText().length()>1000){
					JOptionPane.showMessageDialog(Chall, "Only 1000 Characters allowed. Please " +
							"summarize your work or your work will not be saved.", "("+Chall.getText().length()+" Characters) >>TOO MUCH WORDS IN CHALLENGES", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		align1.insets = new Insets(410, 310, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(challenge, align1);
		
		align1.insets = new Insets(430, 180, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(chall, align1);
		
		Conclu = new JTextArea("-"); 				conclu = new JScrollPane(Conclu);		conc = new JLabel("Conclusion:");
		Dimension dim6 = conclu.getPreferredSize();	dim6.width = 425; 						dim6.height = 50;
		conclu.setPreferredSize(dim6);				Conclu.setBackground(Color.yellow);		Conclu.setLineWrap(true);
		conclu.setAutoscrolls(true);				Conclu.setEditable(true);				Conclu.setWrapStyleWord(true);
		Conclu.setFont(new Font("calibri", Font.PLAIN, 13));
		conc.setFont(new Font("calibri", Font.BOLD, 16));
		Conclu.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				a++;
				if(a == 1){
				Conclu.setText("");
				}
			}
			public void focusLost(FocusEvent arg0) {
				if(Conclu.getText().length()>1000){
					JOptionPane.showMessageDialog(Conclu, "Only 1000 Characters allowed. Please " +
							"summarize your work or your work will not be saved.", "("+Conclu.getText().length()+" Characters) >>TOO MUCH WORDS IN CONCLUSION", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		align1.insets = new Insets(480, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(conc, align1);
		
		align1.insets = new Insets(500, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align1.anchor = GridBagConstraints.NORTH;
		align1.gridx = 0;
		align1.gridy = 0;
		align1.weightx = 0;
		align1.weighty = 0;
		align1.fill = GridBagConstraints.NONE;
		LessonPlanLeft.add(conclu, align1);
		
		// LessonPlanright
		
		Summ = new JTextArea("-"); 					summ = new JScrollPane(Summ);			summry = new JLabel("Summary");
		Dimension dim7 = summ.getPreferredSize();	dim7.width = 500; 						dim7.height = 300;
		summ.setPreferredSize(dim7);				Summ.setBackground(Color.white);	Summ.setLineWrap(true);
		summ.setAutoscrolls(true);					Summ.setEditable(true);					Summ.setWrapStyleWord(true);
		Summ.setFont(new Font("calibri", Font.PLAIN, 13));
		summry.setFont(new Font("calibri", Font.BOLD, 16));
		Summ.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				a++;
				if(a == 1){
				Summ.setText("");
				}
			}
		});
		
		align2.insets = new Insets(0, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.NORTH;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		LessonPlanRight.add(summry, align2);
		
		align2.insets = new Insets(25, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.NORTH;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		LessonPlanRight.add(summ, align2);
		
		CW = new JTextArea("-"); 						cw = new JScrollPane(CW);				classw = new JLabel("  Evaluation:");
		Dimension dim8 = cw.getPreferredSize();		dim8.width = 230; 						dim8.height = 120;
		cw.setPreferredSize(dim8);					CW.setBackground(Color.LIGHT_GRAY);		CW.setLineWrap(true);
		cw.setAutoscrolls(true);					CW.setEditable(true);					CW.setWrapStyleWord(true);
		CW.setFont(new Font("calibri", Font.PLAIN, 13));
		classw.setFont(new Font("calibri", Font.BOLD, 16));
		CW.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				b++;
				if(b == 1){
				CW.setText("");
				}
			}
			public void focusLost(FocusEvent arg0) {
				if(CW.getText().length()>1000){
					JOptionPane.showMessageDialog(CW, "("+CW.getText().length()+" Characters) >>Only 1000 Characters allowed. Please " +
							"summarize your work or your work will not be saved.", "CLASSWORK", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		align2.insets = new Insets(325, 0, 0, 440);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.NORTH;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		LessonPlanRight.add(classw, align2);
		
		align2.insets = new Insets(345, 0, 0, 270);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.NORTH;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		LessonPlanRight.add(cw, align2);
		
		HW = new JTextArea("-"); 						hw = new JScrollPane(HW);			homew = new JLabel("Presentation:");
		Dimension dim9 = hw.getPreferredSize();		dim9.width = 230; 						dim9.height = 120;
		hw.setPreferredSize(dim9);					HW.setBackground(Color.LIGHT_GRAY);		HW.setLineWrap(true);
		hw.setAutoscrolls(true);					HW.setEditable(true);					HW.setWrapStyleWord(true);
		HW.setFont(new Font("calibri", Font.PLAIN, 13));
		homew.setFont(new Font("calibri", Font.BOLD, 16));
		HW.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				c++;
				if(c == 1){
				HW.setText("");
				}
			}
			public void focusLost(FocusEvent arg0) {
				if(HW.getText().length()>1000){
					JOptionPane.showMessageDialog(HW, "Only 1000 Characters allowed. Please " +
							"summarize your work or your work will not be saved.", "("+HW.getText().length()+" Characters) >>HOMEWORK", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		align2.insets = new Insets(325, 420, 0, 0);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.NORTH;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		LessonPlanRight.add(homew, align2);
		
		align2.insets = new Insets(345, 265, 0, 0);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.NORTH;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		LessonPlanRight.add(hw, align2);
		
		Ans = new JTextArea("Type references:"); 						
		ans = new JScrollPane(Ans);				answer = new JLabel("Click here to load assignments");	answer.setForeground(Color.red);
		Dimension dim10 = ans.getPreferredSize();	dim10.width = 250; 							dim10.height = 50;
		ans.setPreferredSize(dim10);				Ans.setBackground(Color.white);				Ans.setLineWrap(true);
		ans.setAutoscrolls(true);					Ans.setEditable(true);						Ans.setWrapStyleWord(true);
		Ans.setFont(new Font("calibri", Font.PLAIN, 13));
		answer.setFont(new Font("calibri", Font.BOLD, 16));
		Ans.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				w++;
				if(w == 1){
				Ans.setText("");
				}
			}
				public void focusLost(FocusEvent arg0) {
					if(Ans.getText().length()>1000){
						JOptionPane.showMessageDialog(Ans, "Only 1000 Characters allowed for ClassWork. Please " +
								"summarize your work or your work will not be saved.", "("+Ans.getText().length()+" Characters) >>TOO MUCH WORDS", JOptionPane.ERROR_MESSAGE);
					}
				}
		});
		
		answer.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "You will now load the Questions.");
				selectionTest = new SelectionTest(null, iDNo, "Assignment", username, Topic,  Subject,  Week, Clasz, term);
				selectionTest.setVisible(true);
			}
			public void mouseEntered(MouseEvent arg0) {
				answer.setForeground(Color.blue);
				answer.setText("Assignments - (click once)");
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				answer.setText("Click here to load assignments");
				answer.setForeground(Color.red);
				revalidate();
			}
			
		});
		
		align2.insets = new Insets(475, 0, 0, 290);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.NORTH;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		LessonPlanRight.add(answer, align2);
		
		align2.insets = new Insets(495, 0, 0, 245);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.NORTH;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		LessonPlanRight.add(ans, align2);
		
		AnsHw = new JTextArea("Type Unrecorded Work here."); 						
		anshw = new JScrollPane(AnsHw);				anstag = new JLabel("Click here to load Test");		anstag.setForeground(Color.red);
		Dimension dim101 = anshw.getPreferredSize();	dim101.width = 250; 			dim101.height = 50;
		anshw.setPreferredSize(dim101);				AnsHw.setBackground(Color.white);	AnsHw.setLineWrap(true);
		anshw.setAutoscrolls(true);					AnsHw.setEditable(true);			AnsHw.setWrapStyleWord(true);
		AnsHw.setFont(new Font("calibri", Font.PLAIN, 13));
		anstag.setFont(new Font("calibri", Font.BOLD, 16));
		AnsHw.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				y++;
				if(y==1){
				AnsHw.setText("");
				}
			}
		});
		
		anstag.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "You will now load the Questions.");
				selectionTest = new SelectionTest(null, iDNo, "Test", username, Topic,  Subject,  Week, Clasz, term);
				selectionTest.setVisible(true);
			}
			public void mouseEntered(MouseEvent arg0) {
				anstag.setText("Tests - (click once)");
				anstag.setForeground(Color.blue);
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				anstag.setText("Click here to load Test");
				anstag.setForeground(Color.red);
				revalidate();
			}
			
		});
		
		align2.insets = new Insets(475, 330, 0, 0);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.NORTH;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		LessonPlanRight.add(anstag, align2);
		
		align2.insets = new Insets(495, 270, 0, 0);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.NORTH;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		LessonPlanRight.add(anshw, align2);
		
		
		Toolbar.add(submit);
		Toolbar.add(save);
		Toolbar.add(time);
		t = new Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Date today = new Date();
				String Today = today.toLocaleString();
				time.setText(Today);
			}
	    });
		
	    t.start();
		
		add(Toolbar, BorderLayout.NORTH);
		add(LessonPlanLeft, BorderLayout.WEST);
		add(LessonPlanRight,BorderLayout.EAST);
		
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				String Introductn = Intro.getText(); int IntL = Introductn.length();
				String preKnow = PK.getText(); int pkL = preKnow.length();
				String Objec = Obj.getText(); int Objc = Objec.length();
				Giant.add(0, duration.getText());		Giant.add(1, perwk.getText());
				Giant.add(2, Intro.getText());  		Giant.add(3,  PK.getText());
				Giant.add(4,  Obj.getText());			Giant.add(5, TM.getText());	
				Giant.add(6, Chall.getText());			Giant.add(7, Conclu.getText());	
				Giant.add(8, Summ.getText());			Giant.add(9, CW.getText());	
				Giant.add(10, HW.getText());			Giant.add(11, Ans.getText());
				Giant.add(12,  AnsHw.getText());
				// Parameter : String Subject, String Clasz, String Topic, int Week, String username
				Giant.add(13, Subject);			Giant.add(14, Clasz);
				Giant.add(15, Topic);			Giant.add(16,  Week+"");
				Giant.add(17,  username);		Giant.add(18,  iDNo+"");
				Giant.add(19,  term);
				boolean latent = GiantLoader.lessonSaver(Giant);
				if(latent = true){
					int resp = JOptionPane.showConfirmDialog(null, "Do you want to exit this page now?");
					if (resp == 0){
					setVisible(false);
					}
				}
			}
			
		});
		
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Giant.add(0, duration.getText());		Giant.add(1, perwk.getText());
				Giant.add(2, Intro.getText());  		Giant.add(3,  PK.getText());
				Giant.add(4,  Obj.getText());			Giant.add(5, TM.getText());	
				Giant.add(6, Chall.getText());			Giant.add(7, Conclu.getText());	
				Giant.add(8, Summ.getText());			Giant.add(9, CW.getText());	
				Giant.add(10, HW.getText());			Giant.add(11, Ans.getText());
				Giant.add(12,  AnsHw.getText());
				// Parameter : String Subject, String Clasz, String Topic, int Week, String username
				Giant.add(13, Subject);			Giant.add(14, Clasz);
				Giant.add(15, Topic);			Giant.add(16,  Week+"");
				Giant.add(17,  username);		Giant.add(18,  iDNo+"");
				Giant.add(19,  term);
				boolean latent = GiantLoader.lessonSubmit(Giant);	
				if(latent = true){
					int resp = JOptionPane.showConfirmDialog(null, "Do you want to exit this page now?");
					if (resp == 0){
					setVisible(false);
					}
				}
			}
			
		});
	}
}
