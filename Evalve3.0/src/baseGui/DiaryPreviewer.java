package baseGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import TeacherPage.DiaryEssayClient;

public class DiaryPreviewer extends JFrame{
	private JTextArea LessonHeading = new JTextArea();
	private JTextArea LessonBody = new JTextArea();
	private JTextArea LessonConclude = new JTextArea();
	private JScrollPane LessonScroll, LessonScroll2, LessonScroll3;
	private DiaryEssayClient Parts = new DiaryEssayClient();
	private JButton edit = new JButton("EDIT WORK");
	
	public DiaryPreviewer(final String subject, final String clasz, final String topic, final int wk, final String term) {
		super(subject.toUpperCase()+" LESSON PLAN - "+term+" term");
		setSize(800, 700);
		setBackground(Color.white);
		setLocationRelativeTo(getParent());
		setVisible(true);
		setLayout(new BorderLayout());
		final Vector Giant = Parts.PartSubject(subject, clasz, topic, wk, term);
		//(Giant);
		LessonScroll = new JScrollPane(LessonHeading);
		Dimension dim101 = LessonScroll.getPreferredSize();	dim101.width = 780; 					dim101.height = 100;
		LessonScroll.setPreferredSize(dim101);				LessonHeading.setBackground(Color.white);		LessonHeading.setLineWrap(true);
		LessonScroll.setAutoscrolls(true);					LessonHeading.setEditable(false);				LessonHeading.setWrapStyleWord(true);
		Color LPColor = new Color(120, 120, 200);
		LessonHeading.setBackground(LPColor);
		LessonHeading.setLayout(new FlowLayout());
		LessonHeading.setFont(new Font("serif", Font.PLAIN, 18));
		LessonHeading.append("Topic: "+topic+" \n");
		//LessonHeading.setFont(new Font("serif", Font.PLAIN, 15));
		LessonHeading.append("Duration: "+Giant.get(0)+"\n");
		LessonHeading.append("Period/Week: "+Giant.get(1)+" \n");
		LessonHeading.append("Class: "+clasz+" \n");
		LessonHeading.append("Week: "+wk+" \n");
		add(LessonScroll, BorderLayout.NORTH);
		
		LessonScroll2 = new JScrollPane(LessonBody);
		Dimension dim2 = LessonScroll.getPreferredSize();	dim2.width = 780; 					dim2.height = 280;
		LessonScroll2.setPreferredSize(dim2);				LessonBody.setBackground(Color.white);		LessonBody.setLineWrap(true);
		LessonScroll2.setAutoscrolls(true);					LessonBody.setEditable(false);				LessonBody.setWrapStyleWord(true);
		Color LPColor2 = new Color(120, 120, 170);
		LessonBody.setBackground(LPColor2);
		LessonBody.setLayout(new FlowLayout());
		LessonBody.setFont(new Font("serif", Font.PLAIN, 15));
		LessonBody.append("Introduction: "+Giant.get(2)+"\n");
		LessonBody.append("Pre-Knowledge: "+Giant.get(3)+"\n");
		LessonBody.append("Objective: "+Giant.get(4)+"\n");
		LessonBody.append("Teaching-Materials: "+Giant.get(5)+"\n");
		LessonBody.append("Challenges: "+Giant.get(6)+"\n");
		LessonBody.append("Notes: "+Giant.get(8)+"\n");
		LessonBody.append("Conclusion: "+Giant.get(7)+"\n");
		LessonBody.append("Class-Work: "+Giant.get(9)+"\n");
		LessonBody.append("Home-Work: "+Giant.get(3)+"\n");
		LessonBody.append("Answers: "+Giant.get(11)+"\n");
		add(LessonScroll2, BorderLayout.CENTER);
		add(edit, BorderLayout.SOUTH);
		
		edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int IDNo = Integer.parseInt(Giant.get(13).toString().trim());
				String Username = Giant.get(13).toString().trim();
				new LessonPlanEdit( null, IDNo, subject,  clasz,  topic, wk,  Username, term);
			}
			
		});
		/*Giant.get(0, duration.getText());		Giant.get(1, perwk.getText());
		Giant.get(2, Intro.getText());  		Giant.get(3,  PK.getText());
		Giant.get(4,  Obj.getText());			Giant.get(5, TM.getText());	
		Giant.get(6, Chall.getText());			Giant.get(7, Conclu.getText());	
		Giant.get(8, Summ.getText());			Giant.get(9, CW.getText());	
		Giant.get(10, HW.getText());			Giant.get(11, Ans.getText());
		Giant.get(12,  AnsHw.getText());
		// Parameter : String Subject, String Clasz, String Topic, int Week, String username
		Giant.get(13, Subject);			Giant.get(14, Clasz);
		Giant.get(15, Topic);			Giant.get(16,  Week+"");
		Giant.get(17,  username);		Giant.get(18,  iDNo+"");
		Giant.get(19,  term);*/
	}

}
