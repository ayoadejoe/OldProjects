package Administration;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CalendarUpdate extends JDialog{
	private JButton b1, b2;
	private Color all;
	private JLabel heading = new JLabel("CALENDAR PLAN");
	private BasicInfoClient test = new BasicInfoClient();
	private Date today; private String termStatus;
	public CalendarUpdate(AdminFront parent){
		super(parent, "CALENDAR SECTION", true);
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		heading.setFont(new Font("serif", Font.BOLD, 24));
		//add(heading);
		today = new Date();
		termStatus = test.termCheck("Present, "+today.toLocaleString());
		switch(termStatus){
		case "empty":
			int work = JOptionPane.showConfirmDialog(null, "Would you like to plan your Calendar now?");
			////(work);
			if(work ==0){
				////("Opening Calendar for first use.");
				test.termCheck("enterNull, "+today.toLocaleString());
			}else{
				JOptionPane.showMessageDialog(null, "Calendar Page will be blank");
			}
			break;
		case 1+"":
			JOptionPane.showMessageDialog(null, today+" is a day in the First Term of this session. ");
			break;
		case 2+"":
			JOptionPane.showMessageDialog(null, today+" is a day in the Second Term of this session. ");
			break;
		case 3+"":
			JOptionPane.showMessageDialog(null, today+" is a day in the Third Term of this session. ");
			break;
		case "false":
			break;
					}
		b1 = new JButton("NEW PLAN");
		b2 = new JButton("REFRESH");
		CalendarTable CalT = new CalendarTable();
		all = new Color(200, 50, 255);
		getContentPane().setBackground(all);
		setSize(900, 430);
		setLayout(new FlowLayout() );
		add(CalT);add(b1); add(b2);
		
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
					String editdate = "1-Jan-2014"; 
					test.termCheck("enterNull, "+editdate);
					dispose();
					new CalendarUpdate(null).setVisible(true);
					
			}
		});
		
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				dispose();
					new CalendarUpdate(null).setVisible(true);
			}
		});
	}

}
