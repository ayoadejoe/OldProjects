package Administration;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class AdminFront extends JFrame{
	private Color AdminC, AdminC2;
	private JPanel Reg = new JPanel();
	private JPanel Res = new JPanel();
	private JPanel view = new JPanel();
	private JPanel Ass = new JPanel();
	private GridLayout gridLayout1; // first gridlayout 
	private JLabel regis, res, viw, ass;
	private JButton stdR, Cal, TeaR, TeaU, Prog, gpa, Fin, att, result, ster, mster, sstat,
	mcq, ess, asg, cat;
	private Register List; String term;
	private StudentRegistration register;
	private TeacherRegistration register2;
	private CalendarUpdate calend;
	private TeacherUpdate register4;
	public AdminFront() {
		super("CONTROL BOARD");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		File pass = new File("passports.txt");
		if(pass.exists()){
			String passp = pass.getAbsolutePath();
			pass.delete(); //(passp);
		}
		AdminC2 = new Color(200, 200, 255);
		getContentPane().setBackground(AdminC2);
		setSize(800, 500);
		gridLayout1 = new GridLayout( 2, 2, 5, 5 );
		setLayout( gridLayout1 );
		
		AdminC = new Color(150, 200, 255);
		Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 0, 5);
		Border outerBorder = BorderFactory.createTitledBorder("REGISTRATION");
		AbstractButton createCompoundBorder;
		Reg.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		Reg.setBackground(AdminC);
		
		Border innerBorder2 = BorderFactory.createEmptyBorder(5, 5, 0, 5);
		Border outerBorder2 = BorderFactory.createTitledBorder("GENERAL STATUS");
		AbstractButton createCompoundBorder2;
		Res.setBorder(BorderFactory.createCompoundBorder(innerBorder2, outerBorder2));
		Res.setBackground(AdminC);
		
		Border innerBorder3 = BorderFactory.createEmptyBorder(5, 5, 0, 5);
		Border outerBorder3 = BorderFactory.createTitledBorder("EDITING CENTRE");
		AbstractButton createCompoundBorder3;
		view.setBorder(BorderFactory.createCompoundBorder(innerBorder3, outerBorder3));
		view.setBackground(AdminC);
		
		Border innerBorder4 = BorderFactory.createEmptyBorder(5, 5, 0, 5);
		Border outerBorder4 = BorderFactory.createTitledBorder("OVERVIEWS");
		AbstractButton createCompoundBorder4;
		Ass.setBorder(BorderFactory.createCompoundBorder(innerBorder4, outerBorder4));
		Ass.setBackground(AdminC);
		
		add(Reg);
		add(Res);
		add(view);
		add(Ass);
		
		Font stat = new Font("SERIF", Font.BOLD, 17);
		stdR = new JButton("New Student");
		stdR.setFont(stat);
		stdR.setBackground(Color.GREEN);
		Cal = new JButton("Calendar");
		Cal.setFont(stat);
		Cal.setVisible(true);
		TeaR = new JButton("New Teacher");
		TeaR.setFont(stat);
		TeaR.setBackground(Color.BLUE);
		TeaU = new JButton("Resource Centre");
		TeaU.setBackground(Color.CYAN);
		TeaU.setFont(stat);
		TeaU.setVisible(true);
		Reg.setLayout( gridLayout1 );
		Reg.add(stdR);
		Reg.add(Cal);
		Reg.add(TeaR);
		Reg.add(TeaU);
		
		Prog = new JButton("Progress Monitor");
		Prog.setFont(stat);
		Prog.setBackground(Color.CYAN);
		gpa = new JButton("Reports Cards");
		gpa.setFont(stat);
		gpa.setBackground(Color.PINK);
		Fin = new JButton("Finances");
		Fin.setFont(stat);
		att = new JButton("General Reports");
		att.setFont(stat);
		att.setBackground(Color.ORANGE);
		Res.setLayout( gridLayout1 );
		Res.add(Prog);
		Res.add(gpa);
		Res.add(Fin);
		Res.add(att);
		
		result = new JButton("Results");
		result.setFont(stat);
		result.setBackground(Color.CYAN);
		ster = new JButton("Register");
		ster.setFont(stat);
		ster.setBackground(Color.MAGENTA);
		mster = new JButton("Master Sheet");
		mster.setFont(stat);
		mster.setBackground(Color.DARK_GRAY);
		sstat = new JButton("Diaries & Plans");
		sstat.setFont(stat);
		sstat.setBackground(Color.ORANGE);
		view.setLayout( gridLayout1 );
		view.add(result);
		view.add(ster);
		view.add(mster);
		view.add(sstat);
		
		mcq = new JButton("School Room");
		mcq.setFont(stat);
		mcq.setBackground(Color.ORANGE);
		ess = new JButton("Projects");
		ess.setFont(stat);
		ess.setBackground(Color.CYAN);
		asg = new JButton("Black Book");
		asg.setFont(stat);
		asg.setBackground(Color.DARK_GRAY);
		cat = new JButton("Teachers");
		cat.setFont(stat);
		cat.setBackground(Color.MAGENTA);
		Ass.setLayout( gridLayout1 );
		Ass.add(mcq);
		Ass.add(ess);
		Ass.add(asg);
		Ass.add(cat);
		
		
		Fin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new Finance();
			}
			
		});
		
		
		sstat.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new DiaryViewrTable();
			}
			
		});
		
		
		mster.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new Master();
			}
			
		});
		
		result.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new Results();
			}
			
		});
		
		stdR.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					register = new StudentRegistration();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				register.setVisible(true);
			}
			
		});
		TeaR.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					register2 = new TeacherRegistration();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				register2.setVisible(true);
			}
			
		});
		Cal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				calend = new CalendarUpdate(null);
				calend.setVisible(true);
			}
			
		});
		
		TeaU.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				register4 = new TeacherUpdate();
				register4.setVisible(true);
			}
			
		});
		
		ster.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new Register();
			}
		});
	}
	public void closeFronts(){
		setVisible(false);
	}
}
