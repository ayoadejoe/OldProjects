package baseGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Caller2 extends JFrame{
	private ImageIcon iconLoad1 = new ImageIcon("kcc.png"); 
	private JLabel kcc = new JLabel(iconLoad1); 
	private ImageIcon iconLoad2 = new ImageIcon("evalve.png"); 
	private JLabel evalve = new JLabel(iconLoad2); 
	//private LoadQuestions Questions = new LoadQuestions();
	private TeacherChecks2 Teachers = new TeacherChecks2();
	private JLabel school = new JLabel("KINGDOM CITIZENS COLLEGE");
	private JLabel ware = new JLabel("Evalve School Manager");
	private JLabel welcome = new JLabel("Welcome! What do you want to do?");
	private JPanel packer = new JPanel();
	ImageIcon img = new ImageIcon("frame2.png");
	
	Font newFont = new Font("SERIF", Font.BOLD, 27);
	Font newFont2 = new Font("CAMBRIA", Font.BOLD, 22);
	AdminCheck Admin = new AdminCheck();
	Color GroundColor;
	
	
	public Caller2() {
		new FileDeleteer();
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
		setContentPane(new JLabel(new ImageIcon("tendril")));
		setIconImage(img.getImage());
		GroundColor = new Color(100, 100, 200);
		school.setFont(newFont);
		ware.setFont(newFont);
		school.setForeground(Color.MAGENTA);
		welcome.setFont(newFont2);
		//getContentPane().setBackground(GroundColor);
		
		Dimension dim2 = packer.getPreferredSize();
		dim2.height = 50;
		dim2.width = 800;
		packer.setPreferredSize(dim2);
		packer.setBackground(GroundColor);
		
		setLayout(new GridBagLayout());
		GridBagConstraints align = new GridBagConstraints();
		
		align.insets = new Insets(0, 0, 450, 800);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(kcc, align);
		
		align.insets = new Insets(30, 0, 420, 0);		// format: push(down, right, up, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(school, align);
		
		align.insets = new Insets(0, 800, 450, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(evalve, align);
		
		align.insets = new Insets(70, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(ware, align);
		
		align.insets = new Insets(150, 0, 0, 850);		// format: push(down, right, up, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(welcome, align);
		
		align.insets = new Insets(250, 0, 0, 800);		// format: push(down, right, up, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 1;
		align.fill = GridBagConstraints.NONE;
		//add(Questions, align);
		
		align.insets = new Insets(250, 0, 0, 600);		// format: push(down, right, up, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 1;
		align.fill = GridBagConstraints.NONE;
		add(Teachers, align);
		
		align.insets = new Insets(250, 600, 0, 0);		// format: push(up, right, down, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 1;
		align.fill = GridBagConstraints.NONE;
		add(Admin, align);
		
		setSize(1150, 700);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		}
	}
