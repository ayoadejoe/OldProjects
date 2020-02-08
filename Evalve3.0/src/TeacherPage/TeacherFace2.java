package TeacherPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import Administration.BasicInfoClient;
import baseGui.SelectionExam;

public class TeacherFace2 extends JFrame{
	private BasicInfoClient p = new BasicInfoClient(); // to get passport
	private SelectionExam f;
	private Color bakG2, bakG;
	private ImageIcon iconLoad = new ImageIcon("iconBW1.png"); 
	private JLabel lineUp = new JLabel(iconLoad);
	private ImageIcon iconLoadI = new ImageIcon("lineUp.png"); 
	private JLabel iconBW1 = new JLabel(iconLoadI);
	private ImageIcon iconLoadI2 = new ImageIcon("iconBW2.png"); 
	private JLabel iconBW2 = new JLabel(iconLoadI2);
	
	private ImageIcon iconpass = new ImageIcon("evpass.png"); 
	private JLabel iconlabel = new JLabel(iconpass);
	
	private ImageIcon iconLoad2 = new ImageIcon("linelow.png"); 
	private JLabel lineLow = new JLabel(iconLoad2);
	private JLabel nameHeader;
	private ImageIcon passpot;
	private File pass;
	private JLabel passP, Pal, Pally, pol;
	private JLabel sign = new JLabel("SIGN IN");
	private JLabel signNote = new JLabel("You have to sign in to use your tools and access your Information.");
	private JLabel signLine = new JLabel("____________________________________________________________________");
	private JPanel pic = new JPanel();
	private JPanel signIn = new JPanel();
	private JTextField userfield;
	private JPasswordField passwordfield;
	private boolean verify = false; private String term = null;
	private String name = null, userName = null, psw = null, SubClassCl = null, passport = null, namehead = null; 
	private int IDNo, d; private String detect = null;
	private TeacherClient client; private GridBagConstraints align = null;
	private JButton exam = new JButton("SET EXAM");
	private JButton test = new JButton("SET TEST");
	private JButton assg = new JButton("SET ASSN");
	private JButton logout = new JButton("SIGN OUT");
	private static int SW; double inicialW, finalW, inicialH, finalH;
	private static int SH; private int t1, t2, t3, t4, TextCol, TextRow;
	public TeacherFace2() {
		super("WELCOME");
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
		bakG2 = new Color(100, 100, 250);
		bakG = new Color(70, 80, 250);
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		signNote.setForeground(Color.RED);
		signLine.setForeground(Color.RED);
		signNote.setFont(new Font("Book Antiqua", Font.HANGING_BASELINE, 16));
		getContentPane().setBackground(bakG2);
		setLocationRelativeTo(getParent());
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		getContentPane().setBackground(new Color(120, 120, 250));
		
		inicialW = 900.0; inicialH = 550;
		SW = (int) (size.width*(0.85));  finalW= SW;
		SH = (int) (size.height*(0.85)); finalH = SH;
		userfield = new JTextField("",resizerW(5));
		userfield.setSize(20, 10);
		passwordfield = new JPasswordField("", resizerW(5));
		setSize(SW, SH);
		setLocationRelativeTo(getParent());
		
		setLayout(new GridBagLayout());
		align = new GridBagConstraints();
		setVisible(true);
		
		Dimension dim2 = pic.getPreferredSize();
		dim2.height = resizerH(240);
		dim2.width = resizerW(210);
		pic.setPreferredSize(dim2);
		Border innerBorder = BorderFactory.createRaisedSoftBevelBorder();
		Border outerBorder = BorderFactory.createEtchedBorder(10, bakG2, Color.GRAY);
		AbstractButton createCompoundBorder;
		pic.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		pic.setLayout(new BorderLayout());
		pic.add(iconlabel, BorderLayout.CENTER);
		
		
		Dimension dim = signIn.getPreferredSize();
		dim.height = resizerH(200);
		dim.width = resizerH(210);
		signIn.setBackground(bakG);
		signIn.setPreferredSize(dim);
		Border innerBorder1 = BorderFactory.createRaisedSoftBevelBorder();
		Border outerBorder1 = BorderFactory.createEtchedBorder(10, bakG, Color.GRAY);
		AbstractButton createCompoundBorder1;
		signIn.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		sign.setFont(new Font("serif", Font.BOLD, 20));
		signIn.setLayout(new FlowLayout());
		signIn.add(iconBW2);
		signIn.add(sign);
		
		userfield.setFont(new Font("calibri", Font.ITALIC, resizerH(13)-1));
		passwordfield.setFont(new Font("calibri", Font.ITALIC, resizerH(13)-1));
		signIn.setFont(new Font("calibri", Font.ITALIC, resizerH(13)-1));
		userfield.setText("joseph is just brilliant");
		passwordfield.setText("joseph");
		signIn.add(new JLabel("                                                                                     "));
		signIn.add(userfield);
		signIn.add(new JLabel("                                                                                     "));
		signIn.add(passwordfield);
		signIn.add(new JLabel("                                                                                     "));
		signIn.add(new JLabel("Please enter your Info "));
		
		align.insets = new Insets(resizerH(0), resizerW(780), resizerH(400), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(lineUp, align);
		
		align.insets = new Insets(resizerH(8), resizerW(550)+50, resizerH(400), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(iconBW1, align);
		
		nameHeader = new JLabel("");
		nameHeader.setText("");
		nameHeader.setFont(new Font("arial black", Font.BOLD, resizerW(24)));
		align.insets = new Insets(resizerH(0), resizerW(0), resizerH(400), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(nameHeader, align);
		
		align.insets = new Insets(resizerH(0), resizerW(235), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(signNote, align);
		
		align.insets = new Insets(resizerH(2), resizerW(235), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(signLine, align);
		
		align.insets = new Insets(resizerH(0), resizerW(0), resizerH(0), resizerW(650));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(pic, align);
		
		align.insets = new Insets(resizerH(250), resizerW(0), resizerH(0), resizerW(650));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(signIn, align);
		
		align.insets = new Insets(resizerH(450), resizerW(0), resizerH(0), resizerW(100)+300);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		//add(lineLow, align);
		
		exam.setSize(resizerW(50), resizerH(50));
		exam.setBackground(Color.white);
		exam.setForeground(Color.blue);
		exam.setFont(new Font("serif", Font.BOLD, 13));
		Dimension dim3 = exam.getPreferredSize();
		dim3.width = resizerW(100);
		dim3.height = resizerH(20);
		exam.setPreferredSize(dim3);
		align.insets = new Insets(resizerH(350), resizerW(0), resizerH(0), resizerW(650));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(exam, align);
		exam.setVisible(false);
		
		test.setSize(resizerW(50), resizerH(50));
		test.setBackground(Color.white);
		test.setForeground(Color.blue);
		test.setFont(new Font("serif", Font.BOLD, 13));
		test.setPreferredSize(dim3);
		align.insets = new Insets(resizerH(300), resizerW(0), resizerH(0), resizerW(650));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(test, align);
		test.setVisible(false);
		
		assg.setSize(resizerW(50), resizerH(50));
		assg.setBackground(Color.white);
		assg.setForeground(Color.blue);
		assg.setFont(new Font("serif", Font.BOLD, 13));
		assg.setPreferredSize(dim3);
		align.insets = new Insets(resizerH(250), resizerW(0), resizerH(0), resizerW(650));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(assg, align);
		assg.setVisible(false);
		
		logout.setSize(resizerW(50), resizerH(50));
		logout.setBackground(Color.white);
		logout.setForeground(Color.red);
		logout.setFont(new Font("serif", Font.BOLD, 13));
		Dimension dim4 = logout.getPreferredSize();
		dim4.width = resizerW(100);
		dim4.height = resizerH(20);
		logout.setPreferredSize(dim4);
		align.insets = new Insets(resizerH(400), resizerW(0), resizerH(0), resizerW(650));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(logout, align);
		logout.setVisible(false);
		
		logout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
			
		});
		
		exam.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String Work = "Exam";
				f = new SelectionExam(null, IDNo,   Work,   userName,   SubClassCl,  term );
				f.setVisible(true);
			}
			
		});
		
		userfield.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				userfield.setFont(new Font("calibri", Font.BOLD, 14));
				passwordfield.setFont(new Font("calibri", Font.BOLD, 14));
				userfield.setText("");
				passwordfield.setText("");
			}
		});
		
		userfield.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
				String user = userfield.getText();
				String password = passwordfield.getText(); d++;
				client = new TeacherClient();
				//confirm = "true"+"`"+IDNo+"`"+name+"`"+userName+"`"+password+"`"+SubClassCl+"`"+passport;
				if(!user.equals("")  && !password.equals("") ){
					String veri = client.signIn(user, password);
					String[] parts = veri.split("`");
					verify = Boolean.parseBoolean((parts[0]).trim());
					if(verify == true){
					IDNo = Integer.parseInt((parts[1]).trim());
					name =(parts[2]).trim();		userName =(parts[3]).trim();		psw =(parts[4]).trim();
					SubClassCl =(parts[5]).trim();	passport =(parts[6]).trim(); 	term =(parts[7]).trim();
					//(veri+", Term: "+term+", verify: "+verify+", "+name+", "+passport);
					}else{
						JOptionPane.showMessageDialog(null, "The username or password is incorrect. Pl" +
								"ease contact the Admin.",  "INVALID LOGIN DATA", JOptionPane.ERROR_MESSAGE);
						//(veri+", Term: "+term+", verify: "+verify+", "+name+", "+passport);
					}
					}else{
						JOptionPane.showMessageDialog(null, "Please enter your username and password");
						verify = false;
					}
				
				TTopface top = new TTopface( IDNo, name, userName, psw, SubClassCl, passport, term);
				
				Dimension dim3 = top.getPreferredSize();
				dim3.height = resizerH(350);
				dim3.width = resizerW(600);
				top.setPreferredSize(dim3);
				Border innerBorder3 = BorderFactory.createRaisedSoftBevelBorder();
				Border outerBorder3 = BorderFactory.createEtchedBorder(10, bakG2, Color.GRAY);
				AbstractButton createCompoundBorder3;
				top.setBorder(BorderFactory.createCompoundBorder(innerBorder3, outerBorder3));
				
				File file = new File("C:\\LocalPassports\\");
			 	if (!file.exists()) {
			 		new File("C:\\LocalPassports\\").mkdirs();
			 		//(file+ " now exists.");
			 		}
			 	File outputfile = new File("C:\\LocalPassports\\"+passport.trim());
			 	p.getPassport(passport.trim());
			 	if(outputfile.exists()){
			 	//("Passport gotten: "+outputfile);
					String add = outputfile.getAbsolutePath();
					BufferedImage passp = null;
					try {
						passp = ImageIO.read(outputfile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					Image scaled = passp.getScaledInstance(resizerW(210), resizerH(240), BufferedImage.SCALE_SMOOTH);
					//("Passport Exists in "+add);
					passpot = new ImageIcon(scaled);
					passP = new JLabel(passpot);
					pic.remove(iconlabel);
					pic.add(passP, BorderLayout.NORTH);
					pic.setBackground(bakG2);
					pic.revalidate();
					
					if(name.length() > 22) name = name.substring(0, 20)+"...";
					nameHeader.setText(name.trim().toUpperCase());
					//(name.trim().toUpperCase() + " now added");
					revalidate();
					//add(passP);
				}else{
					//("Passport does not Exists.");
				}
				
				if(verify == false){
					top.setVisible(false);
					JOptionPane.showMessageDialog(null, "Username or Password does not exist");
				}else if(verify == true){
					signNote.setVisible(false);
					signLine.setVisible(false);
					align.insets = new Insets(resizerH(90), resizerW(200), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
					align.anchor = GridBagConstraints.NORTH;
					align.gridx = 0;
					align.gridy = 0;
					align.weightx = 0;
					align.weighty = 0;
					align.fill = GridBagConstraints.NONE;
					signIn.setVisible(false);
					add(top, align);
					assg.setVisible(true);
					test.setVisible(true);
					exam.setVisible(true);
					logout.setVisible(true);
					top.setVisible(true);
				}
				}catch(Exception h){}
			}
			
		});
		
		passwordfield.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
				String user = userfield.getText();
				String password = passwordfield.getText();  d++;
				client = new TeacherClient();
				if(!user.equals("")  && !password.equals("") ){
					String veri = client.signIn(user, password);
					//(veri);
					String[] parts = veri.split("`");
					verify = Boolean.parseBoolean((parts[0]).trim());
					if(verify == true){
					IDNo = Integer.parseInt((parts[1]).trim());
					name =(parts[2]).trim();		userName =(parts[3]).trim();		psw =(parts[4]).trim();
					name =(parts[2]).trim();		userName =(parts[3]).trim();		psw =(parts[4]).trim();
					SubClassCl =(parts[5]).trim();	passport =(parts[6]).trim(); 	term =(parts[7]).trim();
					}else{
						JOptionPane.showMessageDialog(null, "The username or password is incorrect. Pl" +
								"ease contact the Admin.",  "INVALID LOGIN DATA", JOptionPane.ERROR_MESSAGE);
						//(veri+", Term: "+term+", verify: "+verify+", "+name+", "+passport);
					}
					//(veri+", Term: "+term+", verify: "+verify+", "+name+", "+passport);
					}else{
						JOptionPane.showMessageDialog(null, "Please enter your username and password");
						verify = false;
					}
				TTopface top = new TTopface( IDNo, name, userName, psw, SubClassCl, passport, term);
				Dimension dim3 = top.getPreferredSize(); 
				dim3.height = resizerH(420);
				dim3.width = resizerW(730);
				top.setPreferredSize(dim3);
				Border innerBorder3 = BorderFactory.createRaisedSoftBevelBorder();
				Border outerBorder3 = BorderFactory.createEtchedBorder(10, bakG2, Color.GRAY);
				AbstractButton createCompoundBorder3;
				top.setBorder(BorderFactory.createCompoundBorder(innerBorder3, outerBorder3));
				
				File file = new File("C:\\LocalPassports\\");
			 	if (!file.exists()) {
			 		new File("C:\\LocalPassports\\").mkdirs();
			 		//(file+ " now exists.");
			 		}
			 	File outputfile = new File("C:\\LocalPassports\\"+passport.trim());
			 	p.getPassport(passport.trim());
			 	if(outputfile.exists()){
			 		//("Passport gotten: "+outputfile);
					String add = outputfile.getAbsolutePath();
					BufferedImage passp = null;
					try {
						passp = ImageIO.read(outputfile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					Image scaled = passp.getScaledInstance(resizerW(210), resizerH(240), BufferedImage.SCALE_SMOOTH);
					passpot = new ImageIcon(scaled);
					passP = new JLabel(passpot);
					pic.remove(iconlabel);
					pic.add(passP, BorderLayout.NORTH);
					pic.setBackground(bakG2);
					pic.revalidate();
					
					if(name.length() > 22) name = name.substring(0, 20)+"...";
					nameHeader.setText(name.trim().toUpperCase());
					revalidate();
				}
			 	
				if(verify == false){
					top.setVisible(false);
					JOptionPane.showMessageDialog(null, "Username or Password does not exist");
				}else if(verify == true){
					signNote.setVisible(false);
					signLine.setVisible(false);
					align.insets = new Insets(resizerH(90), resizerW(200), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
					align.anchor = GridBagConstraints.NORTH;
					align.gridx = 0;
					align.gridy = 0;
					align.weightx = 0;
					align.weighty = 0;
					align.fill = GridBagConstraints.NONE;
					signIn.setVisible(false);
					add(top, align);
					assg.setVisible(true);
					test.setVisible(true);
					exam.setVisible(true);
					logout.setVisible(true);
					top.setVisible(true);
				}
				}catch(Exception h){}
			}
			
		});
		//(verify+", Term "+term);
	}
	
	private int resizerW(int inicialposition){
		int newposition = (int)(inicialposition*(finalW/inicialW));
		//(newposition);
		return newposition;
	}
	
	private int resizerH(int inicialposition){
		int newposition = (int)(inicialposition*(finalH/inicialH));
		//(newposition);
		return newposition;
	}
}
