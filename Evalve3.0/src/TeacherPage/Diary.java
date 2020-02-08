package TeacherPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import baseGui.Termer;

public class Diary extends JFrame{
	private DiaryTable subjectD;
	private String Term = null;
	private JButton next = new JButton("NEXT"); 
	private JLabel header = new JLabel(""); private int D = 0;
	private String Name, Username, SubClassCL, Passport, Subject = null, Clasz = null; int ID = 0, Sub = 0, w= 0;
	public Diary(JFrame parent,int iDNo, String name, String username, String SubClassCl,  String term, final int d) {
		super("DIARY");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		this.Term = term;
		Term = Term.substring(0, 5);
		////("The Term is :" +Term);
		Name = name; Username = username; SubClassCL = SubClassCl; ID = iDNo; D = d; 
		int sub = 0;
		switch(Term){
		case "first": sub = 1; break;
		case "second": sub = 2; break;
		case "third": sub = 3; break;
		}Sub = sub; w++;
		try{
		String[] parts = SubClassCL.split(",");
		Subject = parts[d].trim();		Clasz = parts[d+1].trim();
		}catch(Exception w){
			D = 1 ;
			//(" button D = "+ D);
			String[] parts = SubClassCL.split(",");
			Subject = parts[D].trim();		Clasz = parts[D+1].trim();
		}
		header.setText("      CLASS: "+Clasz+"          SUBJECT: "+Subject+"\n       " +
				"   TERM: "+Term);
		header.setFont(new Font("serif", Font.BOLD, 25));
		add(header, BorderLayout.NORTH);
		subjectD = new DiaryTable(ID,  sub,  Username,  Clasz, Subject, Term, w);	
		add(subjectD, BorderLayout.CENTER);
		subjectD.setVisible(true);
		
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				D = D+3;
				setVisible(false);
				//(" button D = "+ D);
				new Diary(null, ID,  Name, Username, SubClassCL, Term, D);
			}
			
		});
		
		//int iDNo, int sub, String username, String Class, String subject, String Term, int w
		add(next, BorderLayout.SOUTH);
		setSize(850, 600);
		setVisible(true);
		setLocationRelativeTo(getParent());
		setResizable(false);
		
	}

}
