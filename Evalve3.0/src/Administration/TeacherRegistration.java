
package Administration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class TeacherRegistration extends JFrame{
	private SubjectIdentifier checkSub = new SubjectIdentifier();
	private AdminFront closing = new AdminFront();
	private BasicInfoClient comp = new BasicInfoClient();
	private JTextArea comm;		private File file = null; private String imageName = null;
	private JLabel headin, picpan, nam, addres, id, passW,cl, phne, phne2, sx, com, panel, 
	space, space1, space2, email, cont, sec, sta, birt, image, subj, subj2, subj3, clas1, clas2, clas3;
	private JPanel pic = new JPanel();
	private JPanel paN = new JPanel();
	private JPanel paO = new JPanel();
	
	private JFileChooser fc = null;
	private String Password = null, Username = null, Email = null, ppp = null, Q1Content = "", Name = null, Address = null,
			Phone1 = null, Phone2 = null, AllEntered = null, Birthday = null,  Admission = null, Gender = null, Comment = null;
	private int compPass = 0, Salary = 0;
	private boolean pwd = true, usr = true, emil = true, psp = true, nm = true, ph = true, ad = true, em = true;
	private JButton loadPic, Regist;
	private JTextField name, add, phoneNo, phoneNo2,level, eml, user;
	private JPasswordField pass;
	private JSpinner birthY, birthM, birthD, admitY, admitM, admitD, sal;
	private SpinnerNumberModel BModY, BModM, BModD, AdModY, AdModM, AdModD, salMod;
	private JComboBox sex;
	private String sexe[] = {"Male", "Female"};
	private JLabel EnterSubj = new JLabel("");
	private Vector <String> Sibjects = new Vector<String> ();
	private Vector compare = null;
	private static File passpot = new File("passports.txt");
	private PrintStream passP;
	private Scanner passp;
	
	public TeacherRegistration() throws FileNotFoundException {
		super("Register Teacher");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
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
		setSize(800, 400);
		setLayout(new FlowLayout());
		headin = new JLabel("________________________TEACHER'S REGISTRATION SECTION_______________________");
		picpan = new JLabel("Student's Picture");
		panel = new JLabel("(Click to Upload Size:150 X 180)");
		panel.setFont(new Font("SERIF", Font.BOLD, 10));
		headin.setFont(new Font("SERIF", Font.BOLD, 20));
		add(headin);
		space = new JLabel(" ");
		add(space);
		
		Dimension dim2 = pic.getPreferredSize();
		dim2.height = 190;
		dim2.width = 160;
		pic.setPreferredSize(dim2);
		Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border outerBorder = BorderFactory.createRaisedBevelBorder();
		AbstractButton createCompoundBorder;
		pic.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		pic.setBackground(Color.GRAY);
		image = new JLabel("");
		pic.setLayout(new BorderLayout());
		pic.add(image, BorderLayout.BEFORE_FIRST_LINE);
		pic.add(new JLabel("Click to Upload"), BorderLayout.NORTH );
		pic.add(panel, BorderLayout.CENTER);
		add(pic);
		
		Dimension dim = paN.getPreferredSize();
		dim.height = 190;
		dim.width = 600;
		paN.setPreferredSize(dim);
		Border innerBorder1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border outerBorder1 = BorderFactory.createTitledBorder("Personal Info");
		AbstractButton createCompoundBorder1;
		paN.setBorder(BorderFactory.createCompoundBorder(innerBorder1, outerBorder1));
		paN.setBackground(Color.WHITE);
		add(paN);
		
	//name, add, idno, phoneNo, sex, level, comm, eml;
		Font font = new Font("SERIF", Font.BOLD, 15);
		
		pass = new JPasswordField();
		
		paN.add(nam = new JLabel("Name:   "));
		paN.add(name = new JTextField("Surname First",30));
		
		paN.add(phne = new JLabel(" Phone #1"));
		paN.add(phoneNo = new JTextField(11));
		phoneNo.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				Phone1 = phoneNo.getText().toUpperCase();
				int P1 = Phone1.length(); ph = true;
				if(P1 > 11 || P1 < 11){
				int resp = JOptionPane.showConfirmDialog(null, "Is this an International No or LandLine No?", "" +
						"PHONE NO DIGITS > 11", JOptionPane.INFORMATION_MESSAGE);
				if(resp == 0){
					ph = true;
				}else{
					JOptionPane.showMessageDialog(null, "Please enter a valid Phone No."); ph = true;
				}
				}
			}
		});
		
		paN.add(addres = new JLabel("Address"));
		paN.add(add = new JTextField(30));
		add.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				Address = add.getText();
				int N1 = Address.length();
				ad = true;
				if(N1 > 199){
					JOptionPane.showMessageDialog(null, "The address entered is quite elaborate. Please manage the field to " +
							"150 Characters");
					ad = false;
				}
			}
		});
		
		paN.add(phne2 = new JLabel("Phone #2"));
		paN.add(phoneNo2 = new JTextField(11));
		phoneNo2.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				Phone2 = phoneNo2.getText();
				int N1 = Phone2.length();
				if(N1 > 11){
					JOptionPane.showMessageDialog(null, "The Number entered in Phone 2 has more than 11 digits");
				}
			}
		});
		
		
		paN.add(email = new JLabel("Email:     "));
		paN.add(eml = new JTextField(20));
		
		paN.add(space1 = new JLabel("                                            "));
		paN.add(cl = new JLabel("Salary:  "));
		salMod = new SpinnerNumberModel(10000, 1000, 100000, 5000); //default, minimum, maximum, increment
		paN.add(sal = new JSpinner(salMod));
		paN.add(id = new JLabel("Username: "));
		paN.add(user = new JTextField(10));
		paN.add(passW = new JLabel("Password: "));
		paN.add(pass = new JPasswordField(10));
		
		paN.add(space1 = new JLabel("                       "));
		paN.add(sx = new JLabel("Gender: "));
		paN.add(sex = new JComboBox(sexe));
		sex.setMaximumRowCount(0);
		paN.add(birt = new JLabel("Date Of Birth"));
		BModD = new SpinnerNumberModel(1, 1, 31, 1); //default, minimum, maximum, increment
		paN.add(birthD = new JSpinner(BModD));
		BModM = new SpinnerNumberModel(1, 1, 12, 1); //default, minimum, maximum, increment
		paN.add(birthM = new JSpinner(BModM));
		BModY = new SpinnerNumberModel(1990, 1979, 2100, 1); //default, minimum, maximum, increment
		paN.add(birthY = new JSpinner(BModY));
		paN.add(space1 = new JLabel("           "));
		paN.add(sta = new JLabel("Employment Date: "));
		AdModD = new SpinnerNumberModel(1, 1, 31, 1); //default, minimum, maximum, increment
		paN.add(admitD = new JSpinner(AdModD));
		AdModM = new SpinnerNumberModel(1, 1, 12, 1); //default, minimum, maximum, increment
		paN.add(admitM = new JSpinner(AdModM));
		AdModY = new SpinnerNumberModel(1990, 1979, 2100, 1); //default, minimum, maximum, increment
		paN.add(admitY = new JSpinner(AdModY));
		
		paN.add(space1 = new JLabel("|>>> "));
		name.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				name.setText("");
			}
		});
		name.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				//
				String nam = name.getText();
				EnterSubj.setText("Enter Subject For: "+nam.toUpperCase());
				EnterSubj.setForeground(Color.black);
			}
			public void focusLost(FocusEvent arg0) {
				String nam = name.getText(); if(nam.length()>25){nam = nam.substring(0, 23)+"...";}
				EnterSubj.setText("Enter Subject For: "+nam.toUpperCase()+" Here");
				EnterSubj.setForeground(Color.red);
				EnterSubj.setFont(new Font("elephant", Font.PLAIN, 15));
				
				Name = name.getText().toUpperCase();
				int N1 = Name.length();
				nm = true;
				if(N1 > 100){
					JOptionPane.showMessageDialog(null, "The name entered is quite elaborate. Please manage the field to " +
							"100 Characters");
					nm = true;
				}
			}
		});
		
		paN.add(EnterSubj);
		paN.revalidate();
		
		paN.add(space1 = new JLabel(" <<<|"));
		
		pass.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				char[] Pd = pass.getPassword();
				int Pw1 = pass.getPassword().length;
				Password = new String(Pd).trim();
				//("Comparing Passwords."+ Password);
				compare = comp.DataCheck("password");
				if (compare == null){
					JOptionPane.showMessageDialog(null, "Cannot connect to Server. Please check your network connection.", "" +
							"SERVER COMMUNICATION ERROR", JOptionPane.ERROR_MESSAGE);
				}else{
					compPass = compare.size();
					//("Returned Passwords."+ compare);
				}
				if (Pw1<6){
					JOptionPane.showMessageDialog(null, "Your password is too short, it must be at least 6 Characters.");
				}
				int e = 0;
			while (compPass>e){
				String baseP = compare.get(e).toString().trim();
				if(Password.equals(baseP)){
				JOptionPane.showMessageDialog(null, "Sorry, this password already exists before, please choose another one.", "" +
						"ILLEGAL PASSWORD HIT", JOptionPane.ERROR_MESSAGE);
				pwd = true;
				break;
				}
				e++;
			}
			}
			
		});
		
		user.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				Username = user.getText().trim();
				int U1 = Username.length();
				//("Comparing Usernames."+ Username);
				compare = comp.DataCheck("username");
				if (compare == null){
					JOptionPane.showMessageDialog(null, "Cannot connect to Server. Please check your network connection.", "" +
							"SERVER COMMUNICATION ERROR", JOptionPane.ERROR_MESSAGE);
				}else{
					compPass = compare.size();
					//("Returned Usernames."+ compare);
				}
				if (U1<6){
					JOptionPane.showMessageDialog(null, "Your Username is too short, it must be at least 6 Characters.");
				}
				int e = 0;
			while (compPass>e){
				String base = compare.get(e).toString().trim();
				if(Username.equals(base)){
				//(Username+ " = "+base);
				JOptionPane.showMessageDialog(null, "Sorry, this username already exists before, please choose another one.", "" +
						"ILLEGAL USERNAME HIT", JOptionPane.ERROR_MESSAGE);
				usr = true;
				break;
				}
				e++;
			}
			}
			
		});
		
		eml.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				Email = eml.getText().trim();
				int U1 = Email.length();
				em = true;
				compare = comp.DataCheck("email");
				if (compare == null){
					JOptionPane.showMessageDialog(null, "Cannot connect to Server. Please check your network connection.", "" +
							"SERVER COMMUNICATION ERROR", JOptionPane.ERROR_MESSAGE); em = true;
				}else{
					compPass = compare.size();
					//("Returned Emails."+ compare);
				}
				if (U1<6){
					JOptionPane.showMessageDialog(null, "Your Email is too short, it must be at least 6 Characters.");
					em = true;
				}
				int e = 0;
			while (compPass>e){
				String base = compare.get(e).toString().trim();
				if(Email.equals(base)){
				JOptionPane.showMessageDialog(null, "Sorry, this Email already exists before, please choose another one.", "" +
						"ILLEGAL EMAIL HIT", JOptionPane.ERROR_MESSAGE);
				em = true;
				break;
				}
				e++;
				em = true;
			}
			if(U1 > 50){
				JOptionPane.showMessageDialog(null, "The Email entered is quite too long for the field");
				em = true;
			}
			if(!Email.contains("@")){
				JOptionPane.showMessageDialog(null, "You have not entered a valid Email Address");
				em = true;
			}
			}
			
		});
		
		
		loadPic = new JButton("Add Picture");
		Regist = new JButton("Employ");
		add(loadPic);
		add(space1 = new JLabel("                                                       " +
				"                                                                       " +
				"                                                            "));
		add(Regist);
		sec = new JLabel("Enter Relevant Information Below:");
		add(sec);
		final JTextArea Q1 = new JTextArea();
		Dimension dim1 = Q1.getPreferredSize();
		dim1.height = 70;
		dim1.width = 750;
		Q1.setPreferredSize(dim1);
		Q1.setLineWrap(true);
		Q1.setWrapStyleWord(true);
		Q1.setText("Good Day!");
		Q1.setFont(new Font("SERIF", Font.PLAIN, 12));
		add(Q1);
		Q1.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				String Comment = Q1.getText();
				int ComMax = Comment.length();
				if (ComMax>250){
					JOptionPane.showMessageDialog(null, "You have typed more than 250 words," +
							" please edit your comment. ", "MAXIMUM WORDS EXCEEDED",
						JOptionPane.ERROR_MESSAGE	);
				}
			}
		});
		
		addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        Q1.requestFocus();
		    }
		}); 
		
		EnterSubj.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				try{
				String noOfSub = JOptionPane.showInputDialog("How many Subjects will this teacher be handling?");
				String noOfCla = JOptionPane.showInputDialog("How many Classes will this teacher be handling?");
				int suj = Integer.parseInt(noOfSub); int cls = Integer.parseInt(noOfCla);
				if(suj>=cls){ int d = 0;
				String QCont = "";
				while(suj>d){
					d++;
					String populate = JOptionPane.showInputDialog("Enter Subject "+d+", Class"+d+", Credit-Load in the format (Subject, Class, CL)");
					String[] parts = populate.split(",");
					String Sbt = parts[0].trim();
					String Clz = parts[1].trim();
					String cl = parts[2].trim();
					//(Sbt+", "+Clz+", "+cl);
					String subct = checkSub.DBSubjectList(Sbt.toLowerCase());
					if(subct == null){
					int resp =	JOptionPane.showConfirmDialog(null, "This subject does not exist in the standard curriculum." +
								" Are you creating a new Subject?" );
					if (resp == 0){subct = Sbt;}
					if (resp == 1){JOptionPane.showMessageDialog(null, "Please check your spellings");
					if (resp == 2){setVisible(true);}
					}
					}
					String clz = checkSub.ClassList(Clz.toUpperCase());
					if(clz == null){
						JOptionPane.showMessageDialog(null, "This Class does not exist in the standard curriculum. Please spell it right" );
					}
					populate = subct+","+clz+", "+cl;
					QCont = populate;
					Q1Content = Q1Content+", "+QCont;
					Q1.setText(Q1Content);
				}
				}
				if(cls>suj){ int d = 0;
				String QCont = "";
				while(cls>d){
					d++;
					String populate = JOptionPane.showInputDialog("Enter Subject "+d+", Class"+d+", Credit-Load in the format (Subject, Class, CL)");
					String[] parts = populate.split(", ");
					String Sbt = parts[0].trim();
					String Clz = parts[1].trim();
					String cl = parts[2].trim();
					String subct = checkSub.DBSubjectList(Sbt.toLowerCase());
					String clz = checkSub.ClassList(Clz.toUpperCase());
					if(subct == null){
						int resp = JOptionPane.showConfirmDialog(null, "This subject does not exist in the standard curriculum." +
								" Are you creating a new Subject?" );
						if (resp == 0){subct = Sbt;}
						if (resp == 1){JOptionPane.showMessageDialog(null, "Please check your spellings");}
						if (resp == 2){setVisible(true);}
					}
					if(clz == null){
						JOptionPane.showMessageDialog(null, "This Class does not exist in the standard curriculum. Please spell it right" );
					}
					populate = subct+","+clz+", "+cl;
					QCont = populate;
					Q1Content = Q1Content+", "+QCont;
					if(Q1Content.contains("null")){
						JOptionPane.showMessageDialog(null, "One or more subject or class that you entered does not exist." +
								" Your work will not be saved. You will have to enter your data again.", "CLASS/SUBJECT ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					Q1.setText(Q1Content);
				}
				}
				Q1.setText("No Comments");
				}catch(Exception w){
					Q1Content = "";
				}
			}
			
		});
		
		loadPic.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ppp = JOptionPane.showInputDialog("Please Enter your passport name");
				int U8 = ppp.length();
				compare = comp.DataCheck("passport");
				if (compare == null){
					JOptionPane.showMessageDialog(null, "Cannot connect to Server. Please check your network connection.", "" +
							"SERVER COMMUNICATION ERROR", JOptionPane.ERROR_MESSAGE);
				}else{
					compPass = compare.size();
					//("Returned passports."+ compare);
				}
				if (U8<6){
					JOptionPane.showMessageDialog(null, "Your passport name is too short, it must be at least 6 Characters.");
				}
				int e = 0;
			while (compPass>e){
				String base = compare.get(e).toString().trim();
				if(ppp.equals(base)){
				//(ppp+ " = "+base);
				JOptionPane.showMessageDialog(null, "Sorry, this passport name already exists before, please choose another one.", "" +
						"ILLEGAL NAME HIT", JOptionPane.ERROR_MESSAGE);
				psp = true;
				break;
				}
				e++;
			}
			//("psp = "+psp);
			if(psp == true){
				fc = new JFileChooser();
				 fc.addChoosableFileFilter(new ImageFilter());
		            fc.setAcceptAllFileFilterUsed(true);
			    //Add custom icons for file types.
		            fc.setFileView(new ImageFileView());

			    //Add the preview pane.
		            fc.setAccessory(new ImagePreview(fc));
		            
				int result = fc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					try{
						imageName = file.getName();
						String ImageLoc = file.getAbsolutePath();
						File source = new File(ImageLoc);
						File dest = new File("Passports\\"+imageName);
						FileChannel inputChannel = null;
						FileChannel outputChannel = null;

						inputChannel = new FileInputStream(source).getChannel();
						outputChannel = new FileOutputStream(dest).getChannel();
						outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
						try {
							inputChannel.close();
							outputChannel.close();
						} catch (IOException l) {
							l.printStackTrace();
						}
						ImageIcon icon = new ImageIcon(ImageIO.read(file));
						Image ScaleImage = icon.getImage().getScaledInstance(150, 175, Image.SCALE_DEFAULT);
						image.setIcon(new ImageIcon(ScaleImage));
						File outputfile = new File(imageName);
						BufferedImage passp = ImageIO.read(file);
						Image scaled = passp.getScaledInstance(150, 175, BufferedImage.SCALE_SMOOTH);

					}catch(IOException u){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						u.printStackTrace();
					}catch( NullPointerException e1){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						e1.printStackTrace();
					}
				}
			
			}
			psp = true;
			}
		});
		
		Regist.addActionListener(new ActionListener(){
			String picture=null;
			public void actionPerformed(ActionEvent arg0) {
				try{
					picture = ppp;
					comp.DataPaspp(ImageIO.read(file), ppp);
					}catch(IOException u){
						JOptionPane.showMessageDialog(null, u.getMessage());
						u.printStackTrace();
					}catch( NullPointerException e1){
						JOptionPane.showMessageDialog(null, e1.getMessage());
						e1.printStackTrace();
					}
				
				int BY = (Integer)birthY.getValue();
				int BM = (Integer)birthM.getValue();
				int BD = (Integer)birthD.getValue();
				Birthday = BD+"/"+BM+"/"+BY;
				
				int AY = (Integer)admitY.getValue();
				int AM = (Integer)admitM.getValue();
				int AD = (Integer)admitD.getValue();
				Admission = AD+"/"+AM+"/"+AY;
				Salary = (Integer)sal.getValue();
				Gender = (String) sex.getSelectedItem().toString();
				
				if(pwd == false){JOptionPane.showMessageDialog(null, "There is a problem with your password. Resolve it. ");}
				if(usr == false){JOptionPane.showMessageDialog(null, "There is a problem with your username. Resolve it. ");}
				if(nm == false){JOptionPane.showMessageDialog(null, "There is a problem with your Name. Resolve it. ");}
				if(ph == false){JOptionPane.showMessageDialog(null, "There is a problem with your Phone No. Resolve it. ");}
				if(ad == false){JOptionPane.showMessageDialog(null, "There is a problem with your address. Resolve it. ");}
				if(em == false){JOptionPane.showMessageDialog(null, "There is a problem with your Email. Resolve it. ");}
				if(picture == null){
					JOptionPane.showMessageDialog(null, "You did not upload a picture.");
				}
				if(Q1Content.equals("")){
					JOptionPane.showMessageDialog(null, "You did not enter the Class and Subject to be taken by teacher.");
				}
				if(pwd == true && usr == true && nm == true && ph == true && ad == true && em == true && picture != null &&
						!Q1Content.equals("")){
				JOptionPane.showMessageDialog(null, "All data Entered.");
				comp.loadTeacher(Name,Address,Phone1,Phone2,Email,Password,Username,Birthday,Admission,Salary,Gender,Comment,
						Q1Content, picture);
						setVisible(false);
					}else{
						setVisible(true);
						JOptionPane.showMessageDialog(null, "There occurred one or more errors, please check " +
								"the information you entered and correct any info entered wrongly.");
					}
			}

		});
	}

}
