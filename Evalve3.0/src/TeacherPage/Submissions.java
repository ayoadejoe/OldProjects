package TeacherPage;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Submissions extends JFrame{
	private SubmissionsClient access = new SubmissionsClient();
	private ImageIcon iconLoad7 = new ImageIcon("assignment.png"); 
	private JLabel Assignments = new JLabel(iconLoad7);
	
	private ImageIcon iconLoad9 = new ImageIcon("test.png"); 
	private JLabel Tests = new JLabel(iconLoad9);
	
	private ImageIcon iconLoad11 = new ImageIcon("exam.png"); 
	private JLabel Exam = new JLabel(iconLoad11);
	
	private ImageIcon iconLoad13 = new ImageIcon("cumulate.png"); 
	private JLabel result = new JLabel(iconLoad13);
	
	private JLabel heading = new JLabel("");
	private JLabel heading2 = new JLabel("");
	
	private GridBagConstraints align = null;
	
	public Submissions(JFrame parent, final String subject, final String claxx, final String cl) {
		super("SUBMISSIONS");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		setContentPane(new JLabel(new ImageIcon("resultpic")));
		setLayout(new GridBagLayout());
		align = new GridBagConstraints();
		align.insets = new Insets(0, 0, 470, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(heading, align);
		
		align.insets = new Insets(0, 0, 420, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(heading2, align);
		
		align.insets = new Insets(100, 0, 0, 350);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Assignments, align);
		
		align.insets = new Insets(80, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Tests, align);
		
		Assignments.setText("Assignment");
		Assignments.setIcon(iconLoad7);
		Assignments.setHorizontalTextPosition( SwingConstants.CENTER );
		Assignments.setVerticalTextPosition( SwingConstants.TOP );
		
		Tests.setText("Test");
		Tests.setIcon(iconLoad9);
		Tests.setHorizontalTextPosition( SwingConstants.CENTER );
		Tests.setVerticalTextPosition( SwingConstants.TOP );
		
		Assignments.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				Assignments.setText("Evaluate "+subject+" Assignments");
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				Assignments.setText("Assignments");
				revalidate();
			}

			public void mouseClicked(MouseEvent arg0) {
				Vector ReadyAssignments = access.getSubmissionsTable(cl, subject, claxx, "assignment");
				Vector Headings = access.getSubmissionsColumns(cl, subject, claxx, "assignment");
				new SubmissionsTable(subject, claxx, "assignment", ReadyAssignments, Headings, cl);
				dispose();
			}
		});
		
		Tests.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				Tests.setText("Evaluate "+subject+" Tests");
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				Tests.setText("Tests");
				revalidate();
			}

			public void mouseClicked(MouseEvent arg0) {
				Vector ReadyTests = access.getSubmissionsTable(cl, subject, claxx, "test");
				Vector Headings = access.getSubmissionsColumns(cl, subject, claxx, "test");
				new SubmissionsTable(subject, claxx, "test", ReadyTests,Headings, cl);
				dispose();
			}
		});
		
		align.insets = new Insets(100, 350, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		add(Exam, align);
		
		align.insets = new Insets(280, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		add(result, align);
		
		Exam.setText("Examinations");
		Exam.setIcon(iconLoad11);
		Exam.setHorizontalTextPosition( SwingConstants.CENTER );
		Exam.setVerticalTextPosition( SwingConstants.TOP );
		
		result.setText("Results");
		result.setIcon(iconLoad13);
		result.setHorizontalTextPosition( SwingConstants.CENTER );
		result.setVerticalTextPosition( SwingConstants.TOP );
		
		Exam.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				Exam.setText("Evaluate "+subject+" Exam");
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				Exam.setText("Examinations");
				revalidate();
			}

			public void mouseClicked(MouseEvent arg0) {
				Vector ReadyExam = access.getSubmissionsTable(cl, subject, claxx, "examination");
				Vector Headings = access.getSubmissionsColumns(cl, subject, claxx, "examination");
				new SubmissionsTable(subject, claxx,"examination", ReadyExam, Headings, cl);
				dispose();
			}
		});
		
		result.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				result.setText("View "+subject+" results");
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				result.setText("Results");
				revalidate();
			}

			public void mouseClicked(MouseEvent arg0) {
				Vector ClassIDs = access.getClassIDs(claxx);
				new ResultCentre(subject, claxx,  ClassIDs);
				dispose();
			}
		});
		
		Assignments.setFont(new Font("monotype corsiva", Font.PLAIN, 18));
		Tests.setFont(new Font("monotype corsiva", Font.PLAIN, 18));
		Exam.setFont(new Font("monotype corsiva", Font.PLAIN, 18));
		result.setFont(new Font("monotype corsiva", Font.PLAIN, 16));
		
		heading.setText(subject+" EVALUATION");
		heading.setFont(new Font("book antiqua", Font.BOLD, 21));
		
		heading2.setText("* "+claxx.toUpperCase()+" *");
		heading2.setFont(new Font("book antiqua", Font.BOLD, 21));
		
		setLocation(300, 100);
		setSize(750, 550);
		setVisible(true);
	}

}
