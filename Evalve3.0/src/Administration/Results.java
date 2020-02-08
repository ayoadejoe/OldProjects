package Administration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Results extends JFrame{
	private All allresults, First, Second, Third;
	private ResultClient change = new ResultClient("result");
	private All jss1, jss2, jss3, sss1, sss2, sss3;  	//the current term
	private JLabel heading;
	private Font BigBoldBlack = new Font("SERIF", Font.BOLD, 24);
	private Color GroundColor = new Color(100, 200, 100);
	private JTabbedPane result;
	public Results() {
		super("RESULTS");
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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(true);
		setBackground(GroundColor);
		setLayout(new BorderLayout());
		result = new JTabbedPane();
		allresults = new All(null);
		First = new All("First");
		Second = new All("Second");
		Third = new All("Third");
		
		jss1 = new All("JSS1"); jss2 = new All("JSS2");
		jss3 = new All("JSS3"); sss1 = new All("SSS1");
		sss2 = new All("SSS2"); sss3 = new All("SSS3");
		heading = new JLabel("                                                         " +
				"                           GENERAL STUDENT REGISTER");
		heading.setFont(BigBoldBlack);
		heading.setForeground(Color.RED);
		//add(heading, BorderLayout.NORTH);
		add(result, BorderLayout.CENTER);
		result.add("ALL RESULTS", allresults);
		result.add("FIRST TERM", First);
		result.add("SECOND TERM", Second);
		result.add("THIRD TERM", Third);
		result.add("JSS1", jss1);	result.add("JSS2", jss2);
		result.add("JSS3", jss3); result.add("SSS1", sss1);
		result.add("SSS2", sss2);	result.add("SSS3", sss3);
	}

}
