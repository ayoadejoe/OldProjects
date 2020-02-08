package Administration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;

public class Register extends JFrame{
	private StudentRegTable table;
	private TeacherRegTable teachtable;
	private RegisterClient t = new RegisterClient("student");
	private ClassesRegTable jss1, jss2, jss3, sss1, sss2, sss3;
	private JLabel heading;
	private Font BigBoldBlack = new Font("SERIF", Font.BOLD, 24);
	private Color GroundColor = new Color(100, 200, 100);
	private JTabbedPane studentP;
	public Register() {
		super("REGISTER");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		getRootPane().setWindowDecorationStyle(JRootPane.WARNING_DIALOG);
		setSize(1000, 640);
		setVisible(true);
		setResizable(true);
		setBackground(GroundColor);
		setLayout(new BorderLayout());
		studentP = new JTabbedPane();
		//("Sending to student registration table.");
		Vector Stud =  t.Registration();
		table = new StudentRegTable(Stud);
		//("Sending to teacher registration table.");
		teachtable = new TeacherRegTable();
		//("Sending to class registration table.");
		jss1 = new ClassesRegTable("JSS1", Stud); jss2 = new ClassesRegTable("JSS2", Stud);
		jss3 = new ClassesRegTable("JSS3", Stud); sss1 = new ClassesRegTable("SSS1", Stud);
		sss2 = new ClassesRegTable("SSS2", Stud); sss3 = new ClassesRegTable("SSS3", Stud);
		heading = new JLabel("                                                         " +
				"                           GENERAL STUDENT REGISTER");
		heading.setFont(BigBoldBlack);
		heading.setForeground(Color.RED);
		//add(heading, BorderLayout.NORTH);
		add(studentP, BorderLayout.CENTER);
		studentP.add("All Students", table);
		studentP.add("All Teachers", teachtable);
		studentP.add("JSS1", jss1);	studentP.add("JSS2", jss2);
		studentP.add("JSS3", jss3); studentP.add("SSS1", sss1);
		studentP.add("SSS2", sss2);	studentP.add("SSS3", sss3);
	}

}
