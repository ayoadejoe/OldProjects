package registration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import clients.CityClient;
import javax.swing.event.ChangeListener;

public class UpdateDepartment extends JFrame {
	private JTextField department;
	private JTextArea description;
	private JTextArea designations;
	private JButton picButt = new JButton("Company Logo");
	private JButton deptLogoButt = new JButton("Add Dept Logo");
	private JButton thumButt = new JButton("Change Logo");
	private JPanel thumbPanel = new JPanel();
	private JButton btnEnter = new JButton("UPDATE DEPARTMENT DETAILS");
	private UtilDateModel model = new UtilDateModel();
	private JPanel picPanel = new JPanel();
	private JDatePanelImpl datePanel = new JDatePanelImpl(model);
    private UtilDateModel model2 = new UtilDateModel();
	private JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
    private SimpleDateFormat generalFormat = new SimpleDateFormat("dd-MM-yyyy");
	private Date birthdate, employmentdate; private String bdate = ""; String empdate = "";
	private final JTextField DeptNo = new JTextField("00");
	private CityClient staffData = new CityClient(); 
	private Object dob = null;
	private String surnam = null, othernam = null, homadd = null,   Phone1 = null, Phone2 = null,
			state = null, designate = null, NOK = null, NOKAdd = null, pic = null, dept = null, user = null
			, pwd = null, gend = null;
	private String IDcheck = "non";
	private ArrayList deptVector = new ArrayList();
	private ArrayList messenger = new ArrayList();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private File file; 
	private String imageName = null;
	private JFileChooser fc = null;
	private JFileChooser tc = null;
	private JFileChooser dc = null;
	private JLabel image = new JLabel();
	private JLabel deptLogoImage = new JLabel();
	private Image passp=null;
	private Image thumb=null;
	private JButton doc1 = new JButton("Add Files");
	private JTextArea deptFiles = new JTextArea();
	private Vector <File>documents = new Vector<File>();
	private int xx = 0, c = 0, q= 0, l=0, m=0, n=0, aa=0;;
	private float next = 0;
	private JButton fileRemove = new JButton("Remove");
	private static Vector <byte[]> DocumentsData = new Vector<byte[]>();
	private static byte[] passpdata;
	private static byte[] thumbdata;
	private static byte[] docdata;
	private JTextField head;
	private JTextField top;
	private JPanel framePanel = new JPanel();
	private JScrollPane framePane = new JScrollPane(framePanel);
	private JTextField promotionAdv;
	private JTextField incrementAdv;
	private JTextField disengageAdv;
	private JTextField mvpAdv;
	private JTextField warningAdv;
	private JTextArea numbers = new JTextArea();
	private parameterEntry getPara;
	private JButton jobDescRemove = new JButton("Remove");;
	private JButton removeDesig = new JButton("Remove");
	private JButton jobDescription = new JButton("Add Job Description");
	private JButton desigButton = new JButton("Add Designations");
	private Vector<String> desc = new Vector<String> ();
	private Vector<String>  desig = new Vector<String> ();
	private Vector<String>  paramet = new Vector<String> ();
	private Vector<String> linkpara = new Vector<String> ();
	private Vector<String>  conditions = new Vector<String> ();
	private Vector  promote = new Vector ();
	private String depart = "";
	private JButton defineParameters = new JButton("Define Parameters");
	private JTextArea parameters = new JTextArea();
	private JButton paraRemove = new JButton("Remove");
	private JButton defineSynergy = new JButton("Define Link Para.");
	private JButton linkParaRemove = new JButton("Remove");
	private JTextArea linkParameters = new JTextArea();
	private JButton lblPromotionChain = new JButton("Define Promotion Chain:");
	private JTextArea promotionChain = new JTextArea();
	private JTextArea email;
	private GeneralInfo sorter;
	private JComboBox departmentFiles = new JComboBox();
	private String[] fileNames = null;
	private JComboBox appraiser = new JComboBox();
	private JScrollPane scrollPane = new JScrollPane();
	private JCheckBox useConditions = new JCheckBox("Don't use Conditions");
	private final JSpinner exitSupport = new JSpinner();
	private final JSpinner needsImp = new JSpinner();
	private final JSpinner average = new JSpinner();
	private final JSpinner roleModel = new JSpinner();
	private JSpinner outstanding = new JSpinner();
	private JSpinner aboveAverage = new JSpinner();
	private JCheckBox chckbxTrimester = new JCheckBox("Trimester");
	private JCheckBox chckbxQuarter = new JCheckBox("Quarter");
	private JCheckBox chckbxSemester = new JCheckBox("Semester");
	private JCheckBox chckbxYear = new JCheckBox("Annual");
	private JSpinner daysCondition = new JSpinner();
	private final JLabel lastUpdate = new JLabel("Last Updated on ");
	private JCheckBox useDays = new JCheckBox("Count Days");
	private final ButtonGroup conditionRange = new ButtonGroup();
	public UpdateDepartment(JFrame parent) {
		setTitle("UPDATE DEPARTMENT");
		getContentPane().setLayout(new BorderLayout());
		framePane.setPreferredSize(new Dimension(500, 800));
		getContentPane().add(framePane, BorderLayout.CENTER);
		framePanel.setBackground(new Color(255, 255, 153));
		setSize(1150, 537);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(UpdateDepartment.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon(UpdateDepartment.class.getResource("city.gif"));
		setIconImage(img.getImage());
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 79, 90, 137, 80, 0, 55, 90, 108, 90, 72};
		gridBagLayout.rowHeights = new int[]{32, 32, 0, 0, 74, 65, 55, 81, 23, 58, 66, 44, 49, 45, 36, 0, 62, 0, 0, 58, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		framePanel.setLayout(gridBagLayout);
		
		GridBagConstraints gbc_lastUpdate = new GridBagConstraints();
		gbc_lastUpdate.gridwidth = 4;
		gbc_lastUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_lastUpdate.gridx = 4;
		gbc_lastUpdate.gridy = 0;
		lastUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		framePanel.add(lastUpdate, gbc_lastUpdate);
		
		JLabel label_1 = new JLabel("            ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 10;
		gbc_label_1.gridy = 0;
		framePanel.add(label_1, gbc_label_1);
		
		JLabel lblPhoneNos = new JLabel("Phone Nos:");
		GridBagConstraints gbc_lblPhoneNos = new GridBagConstraints();
		gbc_lblPhoneNos.anchor = GridBagConstraints.EAST;
		gbc_lblPhoneNos.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNos.gridx = 1;
		gbc_lblPhoneNos.gridy = 1;
		framePanel.add(lblPhoneNos, gbc_lblPhoneNos);
		
		numbers.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				String nos = numbers.getText();
				if(nos.length()>3){
					deptVector.remove(6);
					deptVector.add(6, nos);
				}
			}
		});
		
		numbers.setLineWrap(true);
		numbers.setWrapStyleWord(true);
		GridBagConstraints gbc_numbers = new GridBagConstraints();
		gbc_numbers.insets = new Insets(0, 0, 5, 5);
		gbc_numbers.fill = GridBagConstraints.BOTH;
		gbc_numbers.gridx = 2;
		gbc_numbers.gridy = 1;
		framePanel.add(numbers, gbc_numbers);
		
		JLabel lblSuper = new JLabel("Super>");
		GridBagConstraints gbc_lblSuper = new GridBagConstraints();
		gbc_lblSuper.anchor = GridBagConstraints.EAST;
		gbc_lblSuper.insets = new Insets(0, 0, 5, 5);
		gbc_lblSuper.gridx = 4;
		gbc_lblSuper.gridy = 1;
		framePanel.add(lblSuper, gbc_lblSuper);
		
		top = new JTextField();
		top.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String high = top.getText();
				if(high.length()>3){
					deptVector.remove(1);
					deptVector.add(1, high);
				}else{
					JOptionPane.showMessageDialog(UpdateDepartment.this,
							"Please enter Department Overseer. (No abbreviations)");
				}
			}
		});
		GridBagConstraints gbc_top = new GridBagConstraints();
		gbc_top.insets = new Insets(0, 0, 5, 5);
		gbc_top.fill = GridBagConstraints.HORIZONTAL;
		gbc_top.gridx = 5;
		gbc_top.gridy = 1;
		framePanel.add(top, gbc_top);
		top.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 6;
		gbc_lblEmail.gridy = 1;
		framePanel.add(lblEmail, gbc_lblEmail);
		
		email = new JTextArea();
		email.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String mail = email.getText();
				if(mail.length()>5 & mail.contains("@")){
					deptVector.remove(5);
					deptVector.add(5, mail);
				}else{
					JOptionPane.showMessageDialog(UpdateDepartment.this, "Please enter valid email address/addresses.");
				}
			}
		});
		email.setWrapStyleWord(true);
		email.setLineWrap(true);
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.gridwidth = 3;
		gbc_email.insets = new Insets(0, 0, 5, 5);
		gbc_email.fill = GridBagConstraints.BOTH;
		gbc_email.gridx = 7;
		gbc_email.gridy = 1;
		framePanel.add(email, gbc_email);
		email.setColumns(10);
		
		JButton btnExplanation = new JButton("HELP");
		btnExplanation.setBackground(new Color(102, 255, 0));
		btnExplanation.setPreferredSize(new Dimension(70, 43));
		btnExplanation.setForeground(Color.MAGENTA);
		btnExplanation.setFont(new Font("Arial Nova", Font.BOLD, 12));
		GridBagConstraints gbc_btnExplanation = new GridBagConstraints();
		gbc_btnExplanation.insets = new Insets(0, 0, 5, 0);
		gbc_btnExplanation.gridx = 10;
		gbc_btnExplanation.gridy = 1;
		framePanel.add(btnExplanation, gbc_btnExplanation);
		btnExplanation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(UpdateDepartment.this, "PARAMETERS\n\nWhen you define a parameter, you" +
						" should remember to state the designation and the score.\nFor example, to define a general parameter for an Engineering Officer, you type:" +
						"\n'State challenges encountered and resolved this month>10>Technician'\nOn the other hand, to define a link parameter:\n" +
						"If link person is the HR, we will write 'How would you rate your punctuality this month?>5>HR'\n\n" +
						"CONDITIONS(Scroll down)\n\nEach condition is determined by the number of times within a " +
						"range that a grade is scored.\nFor example, if a trimester is selected, and the condition for Promotion Advice\n" +
						"is 2 Role-Model(RM), 1 Outstanding(OT), then you enter the condition this way '2>1>0>0>0>0'.\nThis means that " +
						"anyone in the department that gets 2 Role Models and 1 Outstanding within the 3 months\nevaluation period" +
						" will be eligible for promotion.\nOn the other hand, if you specify a '0>0>0>1>2>3' as condition for Disengage Advice" +
						" for a Semester range.\nThis means, the staff would be eligible for retrenchment or resignation.\nYou can specify" +
						" different ranges for individual condition depending on the gravity.");
			}
		});
		
		JLabel lblIdentityNo = new JLabel("Enter Dept No:");
		GridBagConstraints gbc_lblIdentityNo = new GridBagConstraints();
		gbc_lblIdentityNo.anchor = GridBagConstraints.EAST;
		gbc_lblIdentityNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdentityNo.gridx = 1;
		gbc_lblIdentityNo.gridy = 2;
		framePanel.add(lblIdentityNo, gbc_lblIdentityNo);
		
		GridBagConstraints gbc_DeptNo = new GridBagConstraints();
		gbc_DeptNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_DeptNo.insets = new Insets(0, 0, 5, 5);
		gbc_DeptNo.gridx = 2;
		gbc_DeptNo.gridy = 2;
		
		DeptNo.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				String noID = DeptNo.getText().trim();
				if(noID != null & noID.length()>3){
					deptVector.remove(0);
					deptVector.add(0, noID);
				}else{
					JOptionPane.showMessageDialog(UpdateDepartment.this, "Please enter valid dept ID.");
				}
			}
		});
		framePanel.add(DeptNo, gbc_DeptNo);
		
		JLabel lblHeadOfDept = new JLabel("Head:");
		GridBagConstraints gbc_lblHeadOfDept = new GridBagConstraints();
		gbc_lblHeadOfDept.anchor = GridBagConstraints.EAST;
		gbc_lblHeadOfDept.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeadOfDept.gridx = 6;
		gbc_lblHeadOfDept.gridy = 2;
		framePanel.add(lblHeadOfDept, gbc_lblHeadOfDept);
		
		head = new JTextField();
		head.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				String hd = head.getText();
				if(hd.length()>3){
					deptVector.remove(4);
					deptVector.add(4, hd);
				}else{
					JOptionPane.showMessageDialog(UpdateDepartment.this, "Do not use abbreviations. Please write full title!");
				}
			}
		});
		GridBagConstraints gbc_head = new GridBagConstraints();
		gbc_head.gridwidth = 2;
		gbc_head.insets = new Insets(0, 0, 5, 5);
		gbc_head.fill = GridBagConstraints.HORIZONTAL;
		gbc_head.gridx = 7;
		gbc_head.gridy = 2;
		framePanel.add(head, gbc_head);
		head.setColumns(10);
		
		picPanel.setBackground(new Color(0, 0, 51));
		picPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), new Color(0, 0, 0), null, null));
		GridBagConstraints gbc_picPanel = new GridBagConstraints();
		gbc_picPanel.gridwidth = 2;
		gbc_picPanel.gridheight = 3;
		gbc_picPanel.insets = new Insets(0, 0, 5, 5);
		gbc_picPanel.fill = GridBagConstraints.BOTH;
		gbc_picPanel.gridx = 1;
		gbc_picPanel.gridy = 3;
		framePanel.add(picPanel, gbc_picPanel);
		
		Dimension dim2 = picPanel.getPreferredSize();
		dim2.height = 180;
		dim2.width = 100;
		picPanel.setPreferredSize(new Dimension(200, 200));
		picPanel.setLayout(new BorderLayout());
		image.setHorizontalTextPosition(SwingConstants.CENTER);
		image.setHorizontalAlignment(SwingConstants.CENTER);
		picPanel.add(image, BorderLayout.CENTER);
		
		department = new JTextField();
		department.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				if(department.getText().length()>3){
					depart = department.getText();
					deptVector.remove(2);
					deptVector.add(2, depart);	
				}else{
					JOptionPane.showMessageDialog(UpdateDepartment.this, "Please enter at least 3 Characters. Abbreviations are not allowed.");
				}
			}
		});
		GridBagConstraints gbc_department = new GridBagConstraints();
		gbc_department.gridwidth = 2;
		gbc_department.insets = new Insets(0, 0, 5, 5);
		gbc_department.fill = GridBagConstraints.HORIZONTAL;
		gbc_department.gridx = 4;
		gbc_department.gridy = 2;
		framePanel.add(department, gbc_department);
		department.setColumns(10);
		jobDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(desc.size()<0){
					jobDescRemove.setEnabled(false);
					description.setText("");
				}else{
					jobDescRemove.setEnabled(true);
				}
				if(depart.length()<3){
					department.setBackground(Color.pink);
					JOptionPane.showMessageDialog(UpdateDepartment.this, "Please enter department first, at least 3 Characters. Abbreviations are not allowed.");
				}else{
				getPara = new parameterEntry("Description", "Designation", null, null, "Add Description "+(aa+1), depart, sorter );
				if(getPara != null){
					getPara.setStringInterface(new StringInterface() {
						public void StringBack(String result, Vector transVec) {
							int qp=0;
							if(desc.isEmpty()){
								description.setText((desc.size()+(qp+1))+". "+transVec.get(0)+"\n\n");
								qp=1;
								}
							for(qp=qp; qp<transVec.size(); qp++){
								description.append("\n"+(desc.size()+(qp+1))+". "+transVec.get(qp)+"\n\n");
							}
							desc.addAll(transVec);
							
							deptVector.remove(9);
							deptVector.add(9, desc);		//add to page array
							
							System.out.println("DESC:"+desc);
							if(desc.size()<0){
								jobDescRemove.setEnabled(false);
								description.setText("");
							}else{
								jobDescRemove.setEnabled(true);
							}
						}
					});
					}
				}
			}
		});
		
		GridBagConstraints gbc_jobDescription = new GridBagConstraints();
		gbc_jobDescription.gridwidth = 2;
		gbc_jobDescription.insets = new Insets(0, 0, 5, 5);
		gbc_jobDescription.gridx = 3;
		gbc_jobDescription.gridy = 3;
		framePanel.add(jobDescription, gbc_jobDescription);
		
		
		
		jobDescRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { int no=-1;
				String noe = JOptionPane.showInputDialog(UpdateDepartment.this, "Type number of description do you want to remove:");
				try{
					if(desc.size()<1){
						jobDescRemove.setEnabled(false);
					}else{
						jobDescRemove.setEnabled(true);
					}
					no = Integer.parseInt(noe);
					System.out.println("Before Desc:"+desc);
					System.out.println("Remove "+(no-1)+":"+desc.get(no-1));
					desc.remove(no-1);
					System.out.println("After Desc:"+desc);
					description.setText("");c--;
					for(int u = 0; desc.size()>u; u++){
						description.append("\n\n"+(u+1)+". "+desc.get(u));
					}
					deptVector.remove(9);
					deptVector.add(9, desc);		//add to page array
				}catch(Exception v){JOptionPane.showMessageDialog(UpdateDepartment.this, "Enter digits only.");}
			}
		});
		
		GridBagConstraints gbc_jobDescRemove = new GridBagConstraints();
		gbc_jobDescRemove.insets = new Insets(0, 0, 5, 5);
		gbc_jobDescRemove.gridx = 5;
		gbc_jobDescRemove.gridy = 3;
		framePanel.add(jobDescRemove, gbc_jobDescRemove);
		
		desigButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(q==0)designations.setText("");
				if(depart.length()<3){
					department.setBackground(Color.pink);
					JOptionPane.showMessageDialog(UpdateDepartment.this, "Please enter the Department first, at least 3 Characters. Abbreviations are not allowed.");
				}else{
				getPara = new parameterEntry("Designation", "Level", "Company Code", null, "Add Designation "+(q+1), depart, sorter);
				getPara.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) {q++;
					if(q>=1)removeDesig.setEnabled(true);
						String outcome  = getPara.getOutcome();
						System.out.println("Gotten:"+outcome);
						String [] dec = outcome.split(">");
						designations.append("\n"+q+". "+dec[0]+">"+dec[1]+">"+dec[2]+">"+dec[3]);
						if(getPara != null)getPara.dispose();
						desig.add(outcome);
						deptVector.remove(3);
						deptVector.add(3, desig);		//add to page array
						System.out.println("New Desig:"+desig);
					}
				});
				}
			}
		});
		
		GridBagConstraints gbc_desigButton = new GridBagConstraints();
		gbc_desigButton.gridwidth = 3;
		gbc_desigButton.insets = new Insets(0, 0, 5, 5);
		gbc_desigButton.gridx = 6;
		gbc_desigButton.gridy = 3;
		framePanel.add(desigButton, gbc_desigButton);
		
		if(c<=0)jobDescRemove.setEnabled(false);
		if(c<=0)removeDesig.setEnabled(false);
		
		removeDesig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { int no=-1;
				String noe = JOptionPane.showInputDialog(UpdateDepartment.this, "Remove designation number:");
				try{
					if(q<=0)removeDesig.setEnabled(false);
					no = Integer.parseInt(noe);
					System.out.println("Before Desc:"+desig);
					System.out.println("Remove "+(no-1)+":"+desig.get(no-1));
					desig.remove(no-1);
					System.out.println("After Desig:"+desig);
					designations.setText("");q--;
					for(int u = 0; desig.size()>u; u++){
						designations.append("\n"+(u+1)+". "+desig.get(u));
					}
					deptVector.remove(3);
					deptVector.add(3, desig);		//add to page array
				}catch(Exception v){JOptionPane.showMessageDialog(UpdateDepartment.this, "Enter digits only.");}
			}
		});
		
		GridBagConstraints gbc_removeDesig = new GridBagConstraints();
		gbc_removeDesig.insets = new Insets(0, 0, 5, 5);
		gbc_removeDesig.gridx = 9;
		gbc_removeDesig.gridy = 3;
		framePanel.add(removeDesig, gbc_removeDesig);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setViewportBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane_6.setPreferredSize(new Dimension(200, 100));
		scrollPane_6.setSize(new Dimension(200, 100));
		scrollPane_6.setAutoscrolls(true);
		GridBagConstraints gbc_scrollPane_6 = new GridBagConstraints();
		gbc_scrollPane_6.gridheight = 2;
		gbc_scrollPane_6.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_6.gridwidth = 3;
		gbc_scrollPane_6.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_6.gridx = 3;
		gbc_scrollPane_6.gridy = 4;
		framePanel.add(scrollPane_6, gbc_scrollPane_6);
		
		description = new JTextArea();
		scrollPane_6.setViewportView(description);
		description.setMaximumSize(new Dimension(300, 150));
		description.setTabSize(10);
		description.setBackground(new Color(255, 255, 153));
		description.setText("1. To manage and carry out maintenance on Transmission and Studio Equipment>Technician\r\n2. Create and design operational modus operandi for the department>Head,Engineering");
		description.setFont(new Font("Arial Nova", Font.PLAIN, 13));
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		
		JLabel lblAppraiser = new JLabel("Appraiser:");
		GridBagConstraints gbc_lblAppraiser = new GridBagConstraints();
		gbc_lblAppraiser.anchor = GridBagConstraints.EAST;
		gbc_lblAppraiser.insets = new Insets(0, 0, 5, 5);
		gbc_lblAppraiser.gridx = 6;
		gbc_lblAppraiser.gridy = 5;
		framePanel.add(lblAppraiser, gbc_lblAppraiser);
		
		GridBagConstraints gbc_appraiser = new GridBagConstraints();
		gbc_appraiser.gridwidth = 3;
		gbc_appraiser.insets = new Insets(0, 0, 5, 5);
		gbc_appraiser.fill = GridBagConstraints.HORIZONTAL;
		gbc_appraiser.gridx = 7;
		gbc_appraiser.gridy = 5;
		appraiser.setSelectedIndex(-1);
		appraiser.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				deptVector.remove(13);
				deptVector.add(13, appraiser.getSelectedItem());
			}
		});
		framePanel.add(appraiser, gbc_appraiser);
		
		GridBagConstraints gbc_picButt = new GridBagConstraints();
		gbc_picButt.gridwidth = 2;
		gbc_picButt.insets = new Insets(0, 0, 5, 5);
		gbc_picButt.gridx = 1;
		gbc_picButt.gridy = 6;
		framePanel.add(picButt, gbc_picButt);
		
		
		Dimension dim = thumbPanel.getPreferredSize();
		dim.height = 110;
		dim.width = 110;
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane_1.setPreferredSize(new Dimension(200, 50));
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.gridx = 6;
		gbc_scrollPane_1.gridy = 4;
		framePanel.add(scrollPane_1, gbc_scrollPane_1);
		
		designations = new JTextArea();
		designations.setBackground(new Color(255, 255, 153));
		designations.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		designations.setWrapStyleWord(true);
		designations.setLineWrap(true);
		designations.setRows(5);
		scrollPane_1.setViewportView(designations);
		designations.setColumns(10);
		
		GridBagConstraints gbc_defineParameters = new GridBagConstraints();
		gbc_defineParameters.gridwidth = 2;
		gbc_defineParameters.insets = new Insets(0, 0, 5, 5);
		gbc_defineParameters.gridx = 3;
		gbc_defineParameters.gridy = 6;
		defineParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(paramet.size()<=0)parameters.setText("");
				if(depart.length()<3){
					department.setBackground(Color.pink);
					JOptionPane.showMessageDialog(UpdateDepartment.this, "Please enter the Department first, at least 3 Characters. Abbreviations are not allowed.");
				}else{
				getPara = new parameterEntry("Internal Parameter", "Score", "Designation", null, "Add Parameter "+(l+1), depart, sorter);
				if(getPara != null){
					getPara.setStringInterface(new StringInterface() {
						public void StringBack(String result, Vector transVec) {
							int qp=0;
							if(paramet.isEmpty()){
								parameters.setText((paramet.size()+(qp+1))+". "+transVec.get(0)+"\n\n");
								qp=1;
								}
							for(qp=qp; qp<transVec.size(); qp++){
								parameters.append((paramet.size()+(qp+1))+". "+transVec.get(qp)+"\n\n");
							}
							paramet.addAll(transVec);
							deptVector.remove(10);
							deptVector.add(10, paramet);		//add to page array
							System.out.println("PARAMET:"+paramet);
							if(paramet.size()<0){
								paraRemove.setEnabled(false);
								parameters.setText("");
							}else{
								paraRemove.setEnabled(true);
							}
						}
					});
					}
				}}
		});
		framePanel.add(defineParameters, gbc_defineParameters);
		
		paraRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {int no=-1;
				String noe = JOptionPane.showInputDialog(UpdateDepartment.this, "Remove parameter number:");
				try{
					if(paramet.size()<1){
						paraRemove.setEnabled(false);
					}else{
						paraRemove.setEnabled(true);
					}
					no = Integer.parseInt(noe);
					System.out.println("Before paramet:"+paramet);
					System.out.println("Remove "+(no-1)+":"+paramet.get(no-1));
					paramet.remove(no-1);
					System.out.println("After Parameters:"+paramet);
					parameters.setText("");l--;
					for(int u = 0; paramet.size()>u; u++){
						parameters.append("\n\n"+(u+1)+". "+paramet.get(u));
					}
					deptVector.remove(10);
					deptVector.add(10, paramet);		//add to page array
				}catch(Exception v){JOptionPane.showMessageDialog(UpdateDepartment.this, "Enter digits only.");}
			}
		});
		
		GridBagConstraints gbc_paraRemove = new GridBagConstraints();
		gbc_paraRemove.insets = new Insets(0, 0, 5, 5);
		gbc_paraRemove.gridx = 5;
		gbc_paraRemove.gridy = 6;
		framePanel.add(paraRemove, gbc_paraRemove);
		
		GridBagConstraints gbc_defineSynergy = new GridBagConstraints();
		gbc_defineSynergy.gridwidth = 3;
		gbc_defineSynergy.insets = new Insets(0, 0, 5, 5);
		gbc_defineSynergy.gridx = 6;
		gbc_defineSynergy.gridy = 6;
		
		defineSynergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(linkpara.size()<=0)linkParameters.setText("");
				if(depart.length()<3){
					department.setBackground(Color.pink);
					JOptionPane.showMessageDialog(UpdateDepartment.this, "Please enter the Department first, at least 3 Characters. Abbreviations are not allowed.");
				}else{
				getPara = new parameterEntry("External Parameter", "Score",  "Department", "External Designation", "Add External Parameter "+(m+1), depart, sorter);
				getPara.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) {m++;
					if(m>=1)linkParaRemove.setEnabled(true);
						String outcome  = getPara.getOutcome();
						System.out.println("Gotten:"+outcome);
						String [] dec = outcome.split(">");
						linkParameters.append("\n"+m+". "+dec[0]+">"+dec[1]+">"+dec[2]+">"+dec[3]+">"+dec[4]);
						if(getPara != null)getPara.dispose();
						linkpara.add(outcome);
						deptVector.remove(11);
						deptVector.add(11, linkpara);		//add to page array
					}
				});
				}
			}
		});
		
		framePanel.add(defineSynergy, gbc_defineSynergy);
		linkParaRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int no=-1;
				String noe = JOptionPane.showInputDialog(UpdateDepartment.this, "Remove link-parameter number:");
				try{
					if(m<=0)linkParaRemove.setEnabled(false);
					no = Integer.parseInt(noe);
					System.out.println("Before linkparamet:"+linkpara);
					System.out.println("Remove "+(no-1)+":"+linkpara.get(no-1));
					linkpara.remove(no-1);
					System.out.println("After Linkpara:"+linkpara);
					linkParameters.setText("");m--;
					for(int u = 0; linkpara.size()>u; u++){
						linkParameters.append("\n"+(u+1)+". "+linkpara.get(u));
					}
					deptVector.remove(11);
					deptVector.add(11, linkpara);		//add to page array
				}catch(Exception v){JOptionPane.showMessageDialog(UpdateDepartment.this, "Enter digits only.");}
			}
		});
		
		GridBagConstraints gbc_linkParaRemove = new GridBagConstraints();
		gbc_linkParaRemove.insets = new Insets(0, 0, 5, 5);
		gbc_linkParaRemove.gridx = 9;
		gbc_linkParaRemove.gridy = 6;
		framePanel.add(linkParaRemove, gbc_linkParaRemove);
		thumbPanel.setPreferredSize(new Dimension(100, 100));
		thumbPanel.setLayout(new BorderLayout());
		deptLogoImage.setHorizontalAlignment(SwingConstants.CENTER);
		thumbPanel.add(deptLogoImage, BorderLayout.CENTER);
		thumbPanel.setToolTipText("Employee's thumbprint or signature should be added here. \r\nFor Signature: This can be done on plane white paper and picture taken.\r\nFor Thumb: Finger print is best on glass and picture taken.");
		thumbPanel.setBackground(new Color(102, 153, 204));
		thumbPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), new Color(192, 192, 192), null, null));
		GridBagConstraints gbc_thumbPanel = new GridBagConstraints();
		gbc_thumbPanel.insets = new Insets(0, 0, 5, 5);
		gbc_thumbPanel.fill = GridBagConstraints.BOTH;
		gbc_thumbPanel.gridx = 1;
		gbc_thumbPanel.gridy = 7;
		framePanel.add(thumbPanel, gbc_thumbPanel);
		if(l<=0)paraRemove.setEnabled(false);
		
		if(m<=0)linkParaRemove.setEnabled(false);
		
		GridBagConstraints gbc_deptLogoButt = new GridBagConstraints();
		gbc_deptLogoButt.fill = GridBagConstraints.BOTH;
		gbc_deptLogoButt.insets = new Insets(0, 0, 5, 5);
		gbc_deptLogoButt.gridx = 1;
		gbc_deptLogoButt.gridy = 8;
		deptLogoButt.setMinimumSize(new Dimension(10, 23));
		deptLogoButt.setPreferredSize(new Dimension(70, 23));
		deptLogoButt.setHorizontalTextPosition(SwingConstants.CENTER);
		framePanel.add(deptLogoButt, gbc_deptLogoButt);
		
		
		deptLogoButt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				tc = new JFileChooser();
				 tc.addChoosableFileFilter(new ImageFilter());
		          tc.setAcceptAllFileFilterUsed(true);
			    //Add custom icons for file types.
		            tc.setFileView(new ImageFileView());

			    //Add the preview pane.
		            tc.setAccessory(new ImagePreview(tc));
		            
				int result = tc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION){
					file = tc.getSelectedFile();
					try{
						ImageIcon icon = new ImageIcon(ImageIO.read(file));
						thumb = icon.getImage().getScaledInstance(175, 110, Image.SCALE_DEFAULT);
						deptLogoImage.setIcon(new ImageIcon(thumb));
						if(file.exists()){
							System.out.println(file+" exists.");
							BufferedImage buffImage = ImageIO.read(file); 
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							boolean foundWriter = ImageIO.write(buffImage, "png", baos);
							assert foundWriter; // Not sure about this... with jpg it may work but other formats ?
							thumbdata = baos.toByteArray();
							deptVector.remove(8);
							deptVector.add(8, thumbdata);
							//ImageData.add(imagedata);
					        //JOptionPane.showMessageDialog( null, new JLabel(new ImageIcon(buffImage.getScaledInstance(buffImage.getWidth(null)/2, buffImage.getHeight(null)/2, Image.SCALE_SMOOTH ))));
							}else{
								JOptionPane.showMessageDialog(UpdateDepartment.this, "The image does not exists.");
							}
					}catch(Exception u){
						JOptionPane.showMessageDialog(null, u.getMessage());
						u.printStackTrace();
					}
				}
			}
		});
		
		JLabel label = new JLabel("               ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridheight = 8;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 9;
		framePanel.add(label, gbc_label);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridheight = 2;
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 9;
		framePanel.add(scrollPane_2, gbc_scrollPane_2);
		
		deptFiles.setEditable(false);
		deptFiles.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		scrollPane_2.setViewportView(deptFiles);
		deptFiles.setLineWrap(true);
		deptFiles.setWrapStyleWord(true);
		deptFiles.setText("click 'Add Files' to add dept. files");
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setViewportBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane_3.setPreferredSize(new Dimension(200, 100));
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridheight = 2;
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridwidth = 3;
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_3.gridx = 3;
		gbc_scrollPane_3.gridy = 7;
		framePanel.add(scrollPane_3, gbc_scrollPane_3);
		parameters.setBackground(new Color(255, 255, 153));
		
		parameters.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		parameters.setEditable(false);
		parameters.setWrapStyleWord(true);
		parameters.setLineWrap(true);
		parameters.setText("In the order:\r\nParameter>Score>Designation");
		scrollPane_3.setViewportView(parameters);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setViewportBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane_4.setPreferredSize(new Dimension(200, 100));
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.gridheight = 2;
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.gridwidth = 4;
		gbc_scrollPane_4.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_4.gridx = 6;
		gbc_scrollPane_4.gridy = 7;
		framePanel.add(scrollPane_4, gbc_scrollPane_4);
		linkParameters.setBackground(new Color(255, 255, 153));
		
		linkParameters.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		linkParameters.setLineWrap(true);
		linkParameters.setWrapStyleWord(true);
		linkParameters.setText("In the order:\r\nParameter>Score>Another Department Designation");
		scrollPane_4.setViewportView(linkParameters);
		
		GridBagConstraints gbc_lblPromotionChain = new GridBagConstraints();
		gbc_lblPromotionChain.gridwidth = 2;
		gbc_lblPromotionChain.insets = new Insets(0, 0, 5, 5);
		gbc_lblPromotionChain.gridx = 3;
		gbc_lblPromotionChain.gridy = 9;
		lblPromotionChain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final Chains make = new Chains(0, promote, "Define Promotion Grades from Highest to Lowest");
				make.addComponentListener(new ComponentAdapter() {int c = 0;String prom= "";
					public void componentHidden(ComponentEvent arg0) {
						promote = make.getRake();
						prom=promote.get(c).toString();c++;
						while(c<promote.size()){
							prom+=" > "+promote.get(c).toString();
							c++;
						}
						promotionChain.setText(prom);
						deptVector.remove(14);
						deptVector.add(14, prom);
						promote.clear();
					}
					
				});
			}
		});
		framePanel.add(lblPromotionChain, gbc_lblPromotionChain);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_5 = new GridBagConstraints();
		gbc_scrollPane_5.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_5.gridwidth = 5;
		gbc_scrollPane_5.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_5.gridx = 5;
		gbc_scrollPane_5.gridy = 9;
		framePanel.add(scrollPane_5, gbc_scrollPane_5);
		promotionChain.setEditable(false);
		promotionChain.setWrapStyleWord(true);
		promotionChain.setLineWrap(true);
		promotionChain.setFont(new Font("Times New Roman", Font.BOLD, 13));
		scrollPane_5.setViewportView(promotionChain);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				promote.clear();
				promotionChain.setText("");
				deptVector.remove(14);
				deptVector.add(14, "");
			}
		});
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 5, 0);
		gbc_btnClear.gridx = 10;
		gbc_btnClear.gridy = 9;
		framePanel.add(btnClear, gbc_btnClear);
		
		JLabel lineCondition = new JLabel("Condition Definition Section (For Control and Appraisal)");
		lineCondition.setFont(new Font("Tahoma", Font.BOLD, 14));
		lineCondition.setBackground(Color.PINK);
		GridBagConstraints glineCondition = new GridBagConstraints();
		glineCondition.anchor = GridBagConstraints.CENTER;
		glineCondition.gridwidth = 6;
		glineCondition.fill = GridBagConstraints.CENTER;
		glineCondition.insets = new Insets(0, 0, 5, 5);
		glineCondition.gridx = 3;
		glineCondition.gridy = 10;
		framePanel.add(lineCondition, glineCondition);
		
		doc1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 dc = new JFileChooser();
				 dc.addChoosableFileFilter(new DocFilter());
		          dc.setAcceptAllFileFilterUsed(true);
			    //Add custom icons for file types.
		            dc.setFileView(new DocFileView());

			    //Add the preview pane.
		            dc.setAccessory(new DocPreview(dc));
				 dc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );      
				 int result = dc.showOpenDialog( UpdateDepartment.this );
				 
				 // if user clicked Cancel button on dialog, return
				 if ( result == JFileChooser.CANCEL_OPTION )
					 System.exit( 1 );
				 File fileN = dc.getSelectedFile(); // get selected file
				// display error if invalid
				 if ( ( fileN == null ) || ( fileN.getName().equals( "" ) ) ){
					 JOptionPane.showMessageDialog( UpdateDepartment.this, "Invalid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE );
					 System.exit( 1 );
				 } // end if
				 System.out.println("file selected:"+fileN);
				 
				 if (result == JFileChooser.APPROVE_OPTION){xx++;
						File newdoc = dc.getSelectedFile();
						if(newdoc.exists()){
							System.out.println(file+" exists.");
							try {
							docdata = new byte[(int) newdoc.length()];
							
							FileInputStream filetoByte = new FileInputStream(newdoc);
							filetoByte.read(docdata);
							DocumentsData.add(docdata);
							deptVector.remove(12);
							deptVector.add(12, DocumentsData);
							} catch (IOException e) {
								e.printStackTrace();
							}
							}else{
								JOptionPane.showMessageDialog(UpdateDepartment.this, "The image does not exists.");
							}
						documents.add(newdoc);
						String docName="";
						for (int w=0; w<documents.size(); w++){
						docName+=documents.get(w);
						}
						deptVector.remove(33);
						deptVector.add(33, docName);
						next+=newdoc.length();
						double remaining = Math.round(newdoc.length()/1000000);
						String rem = remaining+"MB";
						if(remaining<1){
							remaining = Math.round(newdoc.length()/1000);
							rem = remaining+"KB";
						}
						
						if(remaining<1){
							remaining = Math.round(newdoc.length());
							rem = remaining+"Bytes";
						}
						
						double all = Math.round(next/1000000); String allofit= all+"MB";
						if(all<1){
							all = Math.round(next/1000);allofit= all+"KB";
						}
						fileRemove.setVisible(true);
						deptFiles.append(xx+". "+newdoc.getName()+" added.         "+rem+"\n");
						deptFiles.append("Total Document Size now: "+allofit+"\n");
						System.out.println(documents);
						if(next/1000000 >50){
							JOptionPane.showMessageDialog(UpdateDepartment.this, "Documents size is getting quite large!" +
									"\nAre you uploading a book or something?\nLarge size means lots of patience for this registration to complete.");
						}
					}
			}
		});
		conditionRange.add(useConditions);
		useConditions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(useConditions.isSelected()){
					daysCondition.setEnabled(false);
					deptVector.remove(30);
					deptVector.add(30, useConditions.getActionCommand());
					deptVector.remove(26);
					deptVector.add(26, "true");
				}else{
					deptVector.remove(26);
					deptVector.add(26, "false");
					daysCondition.setEnabled(true);
				}
			}
		});
		
		useConditions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		useConditions.setBackground(Color.PINK);
		GridBagConstraints gbc_useConditions = new GridBagConstraints();
		gbc_useConditions.gridwidth = 2;
		gbc_useConditions.anchor = GridBagConstraints.WEST;
		gbc_useConditions.insets = new Insets(0, 0, 5, 0);
		gbc_useConditions.gridx = 9;
		gbc_useConditions.gridy = 10;
		framePanel.add(useConditions, gbc_useConditions);
		
		GridBagConstraints gbc_doc1 = new GridBagConstraints();
		gbc_doc1.insets = new Insets(0, 0, 5, 5);
		gbc_doc1.gridx = 1;
		gbc_doc1.gridy = 11;
		framePanel.add(doc1, gbc_doc1);
		
		fileRemove.setVisible(false);
		fileRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int y = documents.size();
				if(y==0){
					//textArea.setText("");
					fileRemove.setVisible(false);
				}
				if(y>0){xx--;
					//textArea.append("Removed "+documents.get(y-1)+"\n");
					next-= documents.get(y-1).length();
					double all = Math.round(next/1000000); String allofit= all+"MB";
					if(all<1){
						all = Math.round(next/1000);allofit= all+"KB";
					}
					documents.remove(y-1);
					DocumentsData.remove(y-1);
					deptVector.remove(12);
					deptVector.add(12, DocumentsData);
					deptVector.remove(33);
					deptVector.add(33, documents);
					System.out.println(documents);
				}
			}
		});
		
		GridBagConstraints gbc_fileRemove = new GridBagConstraints();
		gbc_fileRemove.insets = new Insets(0, 0, 5, 5);
		gbc_fileRemove.gridx = 2;
		gbc_fileRemove.gridy = 11;
		framePanel.add(fileRemove, gbc_fileRemove);
		
		JLabel lblApplyConditionEvery = new JLabel("Apply Condition Every:");
		GridBagConstraints gbc_lblApplyConditionEvery = new GridBagConstraints();
		gbc_lblApplyConditionEvery.insets = new Insets(0, 0, 5, 5);
		gbc_lblApplyConditionEvery.gridx = 3;
		gbc_lblApplyConditionEvery.gridy = 11;
		framePanel.add(lblApplyConditionEvery, gbc_lblApplyConditionEvery);
		
		GridBagConstraints gbc_chckbxTrimester = new GridBagConstraints();
		gbc_chckbxTrimester.fill = GridBagConstraints.BOTH;
		gbc_chckbxTrimester.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTrimester.gridx = 4;
		gbc_chckbxTrimester.gridy = 11;
		conditionRange.add(chckbxTrimester);
		chckbxTrimester.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxTrimester.isSelected()){
					daysCondition.setEnabled(false);
					deptVector.remove(30);
					deptVector.add(30, chckbxTrimester.getActionCommand());
					deptVector.remove(26);
					deptVector.add(26, "false");
				}else{
					daysCondition.setEnabled(true);
				}
			}
		});
		framePanel.add(chckbxTrimester, gbc_chckbxTrimester);
		
		GridBagConstraints gbc_chckbxQuarter = new GridBagConstraints();
		gbc_chckbxQuarter.fill = GridBagConstraints.BOTH;
		gbc_chckbxQuarter.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxQuarter.gridx = 5;
		gbc_chckbxQuarter.gridy = 11;
		conditionRange.add(chckbxQuarter);
		chckbxQuarter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxQuarter.isSelected()){
					daysCondition.setEnabled(false);
					deptVector.remove(30);
					deptVector.add(30, chckbxQuarter.getActionCommand());
					deptVector.remove(26);
					deptVector.add(26, "false");
				}else{
					daysCondition.setEnabled(true);
				}
			}
		});
		framePanel.add(chckbxQuarter, gbc_chckbxQuarter);
	
		GridBagConstraints gbc_chckbxSemester = new GridBagConstraints();
		gbc_chckbxSemester.fill = GridBagConstraints.BOTH;
		gbc_chckbxSemester.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSemester.gridx = 6;
		gbc_chckbxSemester.gridy = 11;
		conditionRange.add(chckbxSemester);
		chckbxSemester.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxSemester.isSelected()){
					daysCondition.setEnabled(false);
					deptVector.remove(30);
					deptVector.add(30, chckbxSemester.getActionCommand());
					deptVector.remove(26);
					deptVector.add(26, "false");
				}else{
					daysCondition.setEnabled(true);
				}
			}
		});
		framePanel.add(chckbxSemester, gbc_chckbxSemester);
		
		GridBagConstraints gbc_chckbxYear = new GridBagConstraints();
		gbc_chckbxYear.fill = GridBagConstraints.BOTH;
		gbc_chckbxYear.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxYear.gridx = 7;
		gbc_chckbxYear.gridy = 11;
		conditionRange.add(chckbxYear);
		chckbxYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxYear.isSelected()){
					daysCondition.setEnabled(false);
					deptVector.remove(30);
					deptVector.add(30, chckbxYear.getActionCommand());
					deptVector.remove(26);
					deptVector.add(26, "false");
				}else{
					daysCondition.setEnabled(true);
				}
			}
		});
		framePanel.add(chckbxYear, gbc_chckbxYear);
		
		GridBagConstraints gbc_daysCondition = new GridBagConstraints();
		gbc_daysCondition.fill = GridBagConstraints.BOTH;
		gbc_daysCondition.insets = new Insets(0, 0, 5, 5);
		gbc_daysCondition.gridx = 8;
		gbc_daysCondition.gridy = 11;
		daysCondition.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				deptVector.remove(30);
				deptVector.add(30, daysCondition.getValue()+"");
			}
		});
		framePanel.add(daysCondition, gbc_daysCondition);
		
		GridBagConstraints gbc_departmentFiles = new GridBagConstraints();
		gbc_departmentFiles.fill = GridBagConstraints.BOTH;
		gbc_departmentFiles.gridwidth = 2;
		gbc_departmentFiles.insets = new Insets(0, 0, 5, 5);
		gbc_departmentFiles.gridx = 1;
		gbc_departmentFiles.gridy = 13;
		departmentFiles.setModel(new DefaultComboBoxModel(new String[] {"View Department Files here"}));
		
		departmentFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!departmentFiles.getSelectedItem().toString().equals("View Department Files here")){
				File doc = new File("C://CITY1051FM//"+fileNames[(departmentFiles.getSelectedIndex()-1)]);
				System.out.println(doc);
				try {
					Process P = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+doc);
	    		    P.waitFor();
	    		   
	    		    BufferedReader reader = new BufferedReader(new InputStreamReader(P.getInputStream()));
	    		    System.out.println(reader.readLine());
	    		    String line = reader.readLine();
	    		    
	    		    while(reader.ready()){
	    		    	System.out.println(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			}
		});
		
		useDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(useDays.isSelected()){
				daysCondition.setEnabled(true);
				JOptionPane.showMessageDialog(useDays, "Enter the number of days for the application of conditions in the box.");
				conditionRange.clearSelection();
				}
			}
		});
		
		conditionRange.add(useDays);
		GridBagConstraints gbc_useDays = new GridBagConstraints();
		gbc_useDays.insets = new Insets(0, 0, 5, 5);
		gbc_useDays.anchor = GridBagConstraints.WEST;
		gbc_useDays.gridx = 9;
		gbc_useDays.gridy = 11;
		framePanel.add(useDays, gbc_useDays);
		
		JLabel lblDefineAppraisalGrade = new JLabel("Define Appraisal Grade:");
		lblDefineAppraisalGrade.setForeground(new Color(153, 0, 153));
		lblDefineAppraisalGrade.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblDefineAppraisalGrade = new GridBagConstraints();
		gbc_lblDefineAppraisalGrade.gridwidth = 2;
		gbc_lblDefineAppraisalGrade.anchor = GridBagConstraints.SOUTH;
		gbc_lblDefineAppraisalGrade.insets = new Insets(0, 0, 5, 5);
		gbc_lblDefineAppraisalGrade.gridx = 3;
		gbc_lblDefineAppraisalGrade.gridy = 12;
		framePanel.add(lblDefineAppraisalGrade, gbc_lblDefineAppraisalGrade);
		
		framePanel.add(departmentFiles, gbc_departmentFiles);
		
		JLabel lblDefineScoreRange = new JLabel("Upper Score Range>");
		lblDefineScoreRange.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblDefineScoreRange = new GridBagConstraints();
		gbc_lblDefineScoreRange.anchor = GridBagConstraints.EAST;
		gbc_lblDefineScoreRange.insets = new Insets(0, 0, 5, 5);
		gbc_lblDefineScoreRange.gridx = 3;
		gbc_lblDefineScoreRange.gridy = 13;
		framePanel.add(lblDefineScoreRange, gbc_lblDefineScoreRange);
		
		JLabel lblLowScoreRange = new JLabel("Lower Score Range>");
		lblLowScoreRange.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblLowScoreRange = new GridBagConstraints();
		gbc_lblLowScoreRange.anchor = GridBagConstraints.EAST;
		gbc_lblLowScoreRange.insets = new Insets(0, 0, 5, 5);
		gbc_lblLowScoreRange.gridx = 3;
		gbc_lblLowScoreRange.gridy = 14;
		framePanel.add(lblLowScoreRange, gbc_lblLowScoreRange);
		
		JLabel lblRolemodel = new JLabel("RoleModel:");
		GridBagConstraints gbc_lblRolemodel = new GridBagConstraints();
		gbc_lblRolemodel.anchor = GridBagConstraints.EAST;
		gbc_lblRolemodel.insets = new Insets(0, 0, 5, 5);
		gbc_lblRolemodel.gridx = 4;
		gbc_lblRolemodel.gridy = 13;
		framePanel.add(lblRolemodel, gbc_lblRolemodel);
		roleModel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				deptVector.remove(22);
				deptVector.add(22, roleModel.getValue());
			}
		});
		
		roleModel.setModel(new SpinnerNumberModel(0, 0, 100, 5));
		GridBagConstraints gbc_roleModel = new GridBagConstraints();
		gbc_roleModel.anchor = GridBagConstraints.WEST;
		gbc_roleModel.insets = new Insets(0, 0, 5, 5);
		gbc_roleModel.gridx = 5;
		gbc_roleModel.gridy = 13;
		framePanel.add(roleModel, gbc_roleModel);
		
		JLabel lblOutstanding = new JLabel("Outstanding:");
		GridBagConstraints gbc_lblOutstanding = new GridBagConstraints();
		gbc_lblOutstanding.anchor = GridBagConstraints.EAST;
		gbc_lblOutstanding.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutstanding.gridx = 6;
		gbc_lblOutstanding.gridy = 13;
		framePanel.add(lblOutstanding, gbc_lblOutstanding);
		outstanding.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				deptVector.remove(23);
				deptVector.add(23, outstanding.getValue());
			}
		});
		
		outstanding.setModel(new SpinnerNumberModel(0, 0, 100, 5));
		GridBagConstraints gbc_outstanding = new GridBagConstraints();
		gbc_outstanding.anchor = GridBagConstraints.WEST;
		gbc_outstanding.insets = new Insets(0, 0, 5, 5);
		gbc_outstanding.gridx = 7;
		gbc_outstanding.gridy = 13;
		framePanel.add(outstanding, gbc_outstanding);
		
		JLabel lblAboveaverage = new JLabel("Above-Average:");
		GridBagConstraints gbc_lblAboveaverage = new GridBagConstraints();
		gbc_lblAboveaverage.anchor = GridBagConstraints.EAST;
		gbc_lblAboveaverage.insets = new Insets(0, 0, 5, 5);
		gbc_lblAboveaverage.gridx = 8;
		gbc_lblAboveaverage.gridy = 13;
		framePanel.add(lblAboveaverage, gbc_lblAboveaverage);
		aboveAverage.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				deptVector.remove(24);
				deptVector.add(24, aboveAverage.getValue());
			}
		});
		
		aboveAverage.setModel(new SpinnerNumberModel(0, 0, 100, 5));
		GridBagConstraints gbc_aboveAverage = new GridBagConstraints();
		gbc_aboveAverage.anchor = GridBagConstraints.WEST;
		gbc_aboveAverage.insets = new Insets(0, 0, 5, 5);
		gbc_aboveAverage.gridx = 9;
		gbc_aboveAverage.gridy = 13;
		framePanel.add(aboveAverage, gbc_aboveAverage);
		
		JLabel lblAverage = new JLabel("Average:");
		GridBagConstraints gbc_lblAverage = new GridBagConstraints();
		gbc_lblAverage.anchor = GridBagConstraints.EAST;
		gbc_lblAverage.insets = new Insets(0, 0, 5, 5);
		gbc_lblAverage.gridx = 4;
		gbc_lblAverage.gridy = 14;
		framePanel.add(lblAverage, gbc_lblAverage);
		
		average.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				System.out.println("average before:"+deptVector.get(21));
				deptVector.remove(21);
				deptVector.add(21, average.getValue());
				System.out.println("average:"+deptVector.get(21));
			}
		});
		
		average.setModel(new SpinnerNumberModel(0, 0, 100, 5));
		GridBagConstraints gbc_average = new GridBagConstraints();
		gbc_average.anchor = GridBagConstraints.WEST;
		gbc_average.insets = new Insets(0, 0, 5, 5);
		gbc_average.gridx = 5;
		gbc_average.gridy = 14;
		framePanel.add(average, gbc_average);
		
		JLabel lblNeedsimprovement = new JLabel("Needs Improvement:");
		GridBagConstraints gbc_lblNeedsimprovement = new GridBagConstraints();
		gbc_lblNeedsimprovement.insets = new Insets(0, 0, 5, 5);
		gbc_lblNeedsimprovement.gridx = 6;
		gbc_lblNeedsimprovement.gridy = 14;
		framePanel.add(lblNeedsimprovement, gbc_lblNeedsimprovement);
		needsImp.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				deptVector.remove(20);
				deptVector.add(20, needsImp.getValue());
			}
		});
		
		needsImp.setModel(new SpinnerNumberModel(0, 0, 100, 5));
		GridBagConstraints gbc_needsImp = new GridBagConstraints();
		gbc_needsImp.anchor = GridBagConstraints.WEST;
		gbc_needsImp.insets = new Insets(0, 0, 5, 5);
		gbc_needsImp.gridx = 7;
		gbc_needsImp.gridy = 14;
		framePanel.add(needsImp, gbc_needsImp);
		
		JLabel lblS = new JLabel("Exit Support:");
		GridBagConstraints gbc_lblS = new GridBagConstraints();
		gbc_lblS.anchor = GridBagConstraints.EAST;
		gbc_lblS.insets = new Insets(0, 0, 5, 5);
		gbc_lblS.gridx = 8;
		gbc_lblS.gridy = 14;
		framePanel.add(lblS, gbc_lblS);
		exitSupport.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				deptVector.remove(19);
				deptVector.add(19, exitSupport.getValue());
			}
		});
		
		exitSupport.setModel(new SpinnerNumberModel(0, 0, 100, 5));
		GridBagConstraints gbc_exitSupport = new GridBagConstraints();
		gbc_exitSupport.anchor = GridBagConstraints.WEST;
		gbc_exitSupport.insets = new Insets(0, 0, 5, 5);
		gbc_exitSupport.gridx = 9;
		gbc_exitSupport.gridy = 14;
		framePanel.add(exitSupport, gbc_exitSupport);
		
		JLabel controlLine = new JLabel("Staff Performance Control:");
		controlLine.setForeground(new Color(204, 0, 153));
		controlLine.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints controlLineg = new GridBagConstraints();
		controlLineg.gridwidth = 2;
		controlLineg.anchor = GridBagConstraints.SOUTH;
		controlLineg.insets = new Insets(0, 0, 5, 5);
		controlLineg.gridx = 3;
		controlLineg.gridy = 16;
		framePanel.add(controlLine, controlLineg);
		
		JLabel lblPromotionCondition = new JLabel("Promotion Advice:");
		lblPromotionCondition.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblPromotionCondition = new GridBagConstraints();
		gbc_lblPromotionCondition.anchor = GridBagConstraints.EAST;
		gbc_lblPromotionCondition.insets = new Insets(0, 0, 5, 5);
		gbc_lblPromotionCondition.gridx = 3;
		gbc_lblPromotionCondition.gridy = 17;
		framePanel.add(lblPromotionCondition, gbc_lblPromotionCondition);
		
		promotionAdv = new JTextField();
		promotionAdv.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				promote.add("Role-Model"); promote.add("Outstanding");	promote.add("Above-Average");
				promote.add("Average");	promote.add("Below-Average");	promote.add("Observation");
				final Chains make = new Chains(6, promote, "Enter Maximum no of grades to be obtained for Promotion Advice:");
				make.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) { int u = 0; String prom = "";
						promote = make.getRake();
						prom=promote.get(u)+"";		u++;
						while(u<promote.size()){
							prom+=" > "+promote.get(u).toString();
							u++;
						}
						deptVector.remove(28);
						deptVector.add(28, prom);
						promotionAdv.setText(prom);
						promote.clear();
					}
				});
			}
		});
		promotionAdv.setEditable(false);
		promotionAdv.setText("RM>OT>AA>AV>BA>OB");
		GridBagConstraints gbc_promotionAdv = new GridBagConstraints();
		gbc_promotionAdv.insets = new Insets(0, 0, 5, 5);
		gbc_promotionAdv.fill = GridBagConstraints.HORIZONTAL;
		gbc_promotionAdv.gridx = 4;
		gbc_promotionAdv.gridy = 17;
		framePanel.add(promotionAdv, gbc_promotionAdv);
		promotionAdv.setColumns(10);
		
		JLabel lblDisengageAdvice = new JLabel("Disengage Advice:");
		lblDisengageAdvice.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblDisengageAdvice = new GridBagConstraints();
		gbc_lblDisengageAdvice.anchor = GridBagConstraints.EAST;
		gbc_lblDisengageAdvice.insets = new Insets(0, 0, 5, 5);
		gbc_lblDisengageAdvice.gridx = 5;
		gbc_lblDisengageAdvice.gridy = 17;
		framePanel.add(lblDisengageAdvice, gbc_lblDisengageAdvice);
		
		disengageAdv = new JTextField();
		disengageAdv.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				promote.add("Role-Model"); promote.add("Outstanding");	promote.add("Above-Average");
				promote.add("Average");	promote.add("Below-Average");	promote.add("Observation");
				final Chains make = new Chains(6, promote, "Enter Maximum no of grades to be obtained for Disengage Advice:");
				make.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) { int u = 0; String prom = "";
						promote = make.getRake();
						prom=promote.get(u).toString();u++;
						while(u<promote.size()){
							prom+=" > "+promote.get(u).toString();
							u++;
						}
						disengageAdv.setText(prom);
						deptVector.remove(29);
						deptVector.add(29, prom);
						promote.clear();
					}
				});
			}
		});
		disengageAdv.setEditable(false);
		disengageAdv.setText("RM>OT>AA>AV>BA>OB");
		disengageAdv.setColumns(10);
		GridBagConstraints gbc_disengageAdv = new GridBagConstraints();
		gbc_disengageAdv.insets = new Insets(0, 0, 5, 5);
		gbc_disengageAdv.fill = GridBagConstraints.HORIZONTAL;
		gbc_disengageAdv.gridx = 6;
		gbc_disengageAdv.gridy = 17;
		framePanel.add(disengageAdv, gbc_disengageAdv);
		
		JLabel lblIssueWarningOn = new JLabel("Issue Warning On:");
		lblIssueWarningOn.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblIssueWarningOn = new GridBagConstraints();
		gbc_lblIssueWarningOn.anchor = GridBagConstraints.EAST;
		gbc_lblIssueWarningOn.insets = new Insets(0, 0, 5, 5);
		gbc_lblIssueWarningOn.gridx = 7;
		gbc_lblIssueWarningOn.gridy = 17;
		framePanel.add(lblIssueWarningOn, gbc_lblIssueWarningOn);
		
		warningAdv = new JTextField();
		warningAdv.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				promote.add("Role-Model"); promote.add("Outstanding");	promote.add("Above-Average");
				promote.add("Average");	promote.add("Below-Average");	promote.add("Observation");
				final Chains make = new Chains(6, promote, "Enter Maximum no of grades to be obtained for Warning Advice:");
				make.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) { int u = 0; String prom = "";
						promote = make.getRake();
						prom=promote.get(u).toString();u++;
						while(u<promote.size()){
							prom+=" > "+promote.get(u).toString();
							u++;
						}
						warningAdv.setText(prom);
						deptVector.remove(25);
						deptVector.add(25, prom);
						promote.clear();
					}
				});
			}
		});
		warningAdv.setEditable(false);
		warningAdv.setText("RM>OT>AA>AV>BA>OB");
		warningAdv.setColumns(10);
		GridBagConstraints gbc_warningAdv = new GridBagConstraints();
		gbc_warningAdv.insets = new Insets(0, 0, 5, 5);
		gbc_warningAdv.fill = GridBagConstraints.HORIZONTAL;
		gbc_warningAdv.gridx = 8;
		gbc_warningAdv.gridy = 17;
		framePanel.add(warningAdv, gbc_warningAdv);
		
		JLabel lblIncrementAdvice = new JLabel("Increment Advice:");
		lblIncrementAdvice.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblIncrementAdvice = new GridBagConstraints();
		gbc_lblIncrementAdvice.anchor = GridBagConstraints.EAST;
		gbc_lblIncrementAdvice.insets = new Insets(0, 0, 5, 5);
		gbc_lblIncrementAdvice.gridx = 3;
		gbc_lblIncrementAdvice.gridy = 18;
		framePanel.add(lblIncrementAdvice, gbc_lblIncrementAdvice);
		
		incrementAdv = new JTextField();
		incrementAdv.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				promote.add("Role-Model"); promote.add("Outstanding");	promote.add("Above-Average");
				promote.add("Average");	promote.add("Below-Average");	promote.add("Observation");
				final Chains make = new Chains(6, promote, "Enter Maximum no of grades to be obtained for Salary Increment Advice:");
				make.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) { int u = 0; String prom = "";
						promote = make.getRake();
						prom=promote.get(u).toString();u++;
						while(u<promote.size()){
							prom+=" > "+promote.get(u).toString();
							u++;
						}
						incrementAdv.setText(prom);
						deptVector.remove(27);
						deptVector.add(27, prom);
						promote.clear();
					}
				});
			}
		});
		incrementAdv.setEditable(false);
		incrementAdv.setText("RM>OT>AA>AV>BA>OB");
		incrementAdv.setColumns(10);
		GridBagConstraints gbc_incrementAdv = new GridBagConstraints();
		gbc_incrementAdv.insets = new Insets(0, 0, 5, 5);
		gbc_incrementAdv.fill = GridBagConstraints.HORIZONTAL;
		gbc_incrementAdv.gridx = 4;
		gbc_incrementAdv.gridy = 18;
		framePanel.add(incrementAdv, gbc_incrementAdv);
		
		JLabel lblMvpAdvice = new JLabel("MVP Advice:");
		lblMvpAdvice.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblMvpAdvice = new GridBagConstraints();
		gbc_lblMvpAdvice.anchor = GridBagConstraints.EAST;
		gbc_lblMvpAdvice.insets = new Insets(0, 0, 5, 5);
		gbc_lblMvpAdvice.gridx = 5;
		gbc_lblMvpAdvice.gridy = 18;
		framePanel.add(lblMvpAdvice, gbc_lblMvpAdvice);
		
		mvpAdv = new JTextField();
		mvpAdv.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				promote.add("Role-Model"); promote.add("Outstanding");	promote.add("Above-Average");
				promote.add("Average");	promote.add("Below-Average");	promote.add("Observation");
				final Chains make = new Chains(6, promote, "Enter Maximum no of grades to be obtained for Most Valuable Player Advice:");
				make.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) { int u = 0; String prom = "";
						promote = make.getRake();
						prom=promote.get(u).toString();u++;
						while(u<promote.size()){
							prom+=" > "+promote.get(u).toString();
							u++;
						}
						mvpAdv.setText(prom);
						deptVector.remove(31);
						deptVector.add(31, prom);
						promote.clear();
					}
				});
			}
		});
		mvpAdv.setEditable(false);
		mvpAdv.setText("RM>OT>AA>AV>BA>OB");
		mvpAdv.setColumns(10);
		GridBagConstraints gbc_mvpAdv = new GridBagConstraints();
		gbc_mvpAdv.insets = new Insets(0, 0, 5, 5);
		gbc_mvpAdv.fill = GridBagConstraints.HORIZONTAL;
		gbc_mvpAdv.gridx = 6;
		gbc_mvpAdv.gridy = 18;
		framePanel.add(mvpAdv, gbc_mvpAdv);
		
		btnEnter.setBackground(new Color(178, 34, 34));
		btnEnter.setForeground(new Color(250, 250, 210));
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.fill = GridBagConstraints.BOTH;
		gbc_btnEnter.gridwidth = 9;
		gbc_btnEnter.insets = new Insets(0, 0, 0, 5);
		gbc_btnEnter.gridx = 1;
		gbc_btnEnter.gridy = 20;
		framePanel.add(btnEnter, gbc_btnEnter);
		
		btnEnter.addActionListener(new ActionListener() { boolean check = false;
			public void actionPerformed(ActionEvent arg0) {
				if(!messenger.isEmpty())messenger.clear();
				messenger.add(0, "^updatestandards");
				for(int y=0; deptVector.size()>y; y++){
					messenger.add(deptVector.get(y));
				}
				System.out.println("Messenger:"+messenger);
				System.out.println("deptVector:"+deptVector);
				messenger = staffData.getIt(messenger);
				JOptionPane.showMessageDialog(UpdateDepartment.this, messenger.get(0));
				
			}
		});
		
		picButt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
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
						ImageIcon icon = new ImageIcon(ImageIO.read(file));
						passp = icon.getImage().getScaledInstance(175, 180, Image.SCALE_DEFAULT);
						image.setIcon(new ImageIcon(passp));

						if(file.exists()){
							System.out.println(file+" exists.");
							BufferedImage buffImage = ImageIO.read(file); 
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							boolean foundWriter = ImageIO.write(buffImage, "png", baos);
							assert foundWriter; // Not sure about this... with jpg it may work but other formats ?
							passpdata = baos.toByteArray();
							deptVector.remove(7);
							deptVector.add(7, thumbdata);
							//ImageData.add(imagedata);
					        //JOptionPane.showMessageDialog( null, new JLabel(new ImageIcon(buffImage.getScaledInstance(buffImage.getWidth(null)/2, buffImage.getHeight(null)/2, Image.SCALE_SMOOTH ))));
							}else{
								JOptionPane.showMessageDialog(UpdateDepartment.this, "The image does not exists.");
							}
					}catch(Exception u){
						JOptionPane.showMessageDialog(null, u.getMessage());
						u.printStackTrace();
					}
				}
			
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.out.println("Windows closed");
				dispose();
			}

			public void windowOpened(WindowEvent arg0) {
				String [] DeptIDs = {"CITY/ENG", "CITY/MKT", "CITY/HR"};
				//get departments in database
				deptVector.add("^getstandards$"); deptVector.add("standards"); deptVector.add("Department"); deptVector.add("null");
				System.out.println("Sending out:"+deptVector);
				
				deptVector = staffData.getIt(deptVector);
				System.out.println("Sending out:"+deptVector);
				
				JComboBox rugged = new JComboBox(deptVector.toArray());
				rugged.setEditable(true);
				JOptionPane.showMessageDialog(UpdateDepartment.this, rugged, "SELECT DEPARTMENT",  JOptionPane.QUESTION_MESSAGE);
				String dept = (String) rugged.getSelectedItem();
				preLoad(dept);
			}
		});
		
		setVisible(true);
	}

	protected void preLoad(String dept) {
		sorter = new GeneralInfo(UpdateDepartment.this, dept);
		if(sorter != null)deptVector = sorter.getDeptVector();
		
		if(dept.equals(sorter.getDept())){
		DeptNo.setText(sorter.getDeptID());
		DeptNo.setFont(new Font("serif", Font.BOLD, 13));
		DeptNo.setForeground(Color.GREEN);
		btnEnter.setEnabled(true);
		
		department.setText(sorter.getDept());
		depart = sorter.getDept();
		department.setFont(new Font("serif", Font.BOLD, 13));
		department.setForeground(Color.GREEN);
		
		top.setText(sorter.getSuperTop());
		
		head.setText(sorter.getHead());
		email.setText(sorter.getEmail());
		numbers.setText(sorter.getNumbers());
		
		desig = sorter.getDesignation();
		System.out.println("desig"+desig);
		designations.setText("");
		
		if(!desig.get(0).toString().equals("empty field")){
		q = desig.size();
		if(q>=1)removeDesig.setEnabled(true);
		for(int u = 0; desig.size()>u; u++){
			String val = desig.get(u).toString();
			String [] dec = val.split(">");
			appraiser.addItem(desig.get(u));
			designations.append("\n"+(u+1)+". "+dec[0]+">"+dec[1]+">"+dec[2]+">"+dec[3]);
		}
		}
		
		desc = sorter.getDescription();
		System.out.println("description:"+desc);
		description.setText("");
		if(!desc.isEmpty()){ 
		if( !desc.get(0).toString().equals("empty field")){
		c = desc.size();
		if(c>=1)jobDescRemove.setEnabled(true);
		for(int u = 0; desc.size()>u; u++){
			String val = desc.get(u).toString();
			String [] dec = val.split("`-");
			description.append("\n"+(u+1)+". "+dec[0]+"`- "+dec[1]+"`- "+dec[2]+"\n");
		}}
		}else{
			desc.clear();
		}
		
		paramet = sorter.getParameters();
		parameters.setText("");
		System.out.println("parameter:"+paramet);
		if(!paramet.isEmpty()){
		if(!paramet.get(0).toString().equals("empty field")){
		c = paramet.size();
		if(c>=1)paraRemove.setEnabled(true);
		for(int u = 0; paramet.size()>u; u++){
			String val = paramet.get(u).toString();
			String [] dec = val.split(">");
			parameters.append("\n"+(u+1)+". "+dec[0]+">"+dec[1]+">"+dec[2]+"\n");
		}
		}}else{
			paramet.clear();
		}
		
		linkpara = sorter.getLinkparameters();
		linkParameters.setText("");
		System.out.println("Size:"+linkpara.size()+" link parameter:"+linkpara);
		if(!linkpara.isEmpty()){
		if(!linkpara.get(0).toString().equals("empty field")){
		c = linkpara.size();
		if(c>=1)linkParaRemove.setEnabled(true);
		for(int c = 0; linkpara.size()>c; c++){
			String val = linkpara.get(c).toString();
			String [] dec = val.split(">");
			linkParameters.append("\n"+(c+1)+". "+dec[0]+">"+dec[1]+">"+dec[2]+">"+dec[3]+"\n");
		}
		}}
		
		DocumentsData = sorter.getCredentials();
		deptFiles.setText("");   String mimeType = "";
		System.out.println("Dept Files:"+DocumentsData);
		if(DocumentsData.size()>0){
		if(!DocumentsData.get(0).toString().equals("empty field")){
		c = DocumentsData.size();
		File homeDir = new File("C://CITY1051FM");
		if(!homeDir.exists()){
			homeDir.mkdirs();
		}
		fileNames = sorter.getFileNames().split("`'");
		if(c>=1)fileRemove.setEnabled(true);
		for(int u = 0; DocumentsData.size()>u; u++){
			try {
				Files.write(Paths.get("C://CITY1051FM//"+fileNames[u]), DocumentsData.get(u));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(fileNames[u].length()>22){
			departmentFiles.addItem(fileNames[u].substring(0, 22)+"...");
			}else{
				departmentFiles.addItem(fileNames[u]);
			}
			}
		}}
		
		try {
		InputStream in = new ByteArrayInputStream(sorter.getComplogo());
		BufferedImage filepic = ImageIO.read(in);
		ImageIcon icon = new ImageIcon(filepic);
		passp = icon.getImage().getScaledInstance(175, 180, Image.SCALE_DEFAULT);
		image.setIcon(new ImageIcon(passp));
		picButt.setText("Change Logo");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			InputStream in = new ByteArrayInputStream(sorter.getDeptlogo());
			BufferedImage filepic = ImageIO.read(in);
			ImageIcon icon = new ImageIcon(filepic);
			thumb = icon.getImage().getScaledInstance(175, 110, Image.SCALE_DEFAULT);
			deptLogoImage.setIcon(new ImageIcon(thumb));
			deptLogoButt.setText("Change");
			revalidate();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		appraiser.setSelectedItem(sorter.getAppraiser());
		promotionChain.setText(sorter.getPromotionChain());
		
		exitSupport.setValue(sorter.getExitSupportScore());
		needsImp.setValue(sorter.getNeedsImproveScore());
		average.setValue(sorter.getAverageScore());
		roleModel.setValue(sorter.getRoleModelScore());
		outstanding.setValue(sorter.getOutstandingScore());
		aboveAverage.setValue(sorter.getAboveAverageScore());
		
		warningAdv.setText(sorter.getWarningAdv());
		incrementAdv.setText(sorter.getIncrementAdv());
		promotionAdv.setText(sorter.getPromotionAdv());
		disengageAdv.setText(sorter.getDisengageAdv());
		mvpAdv.setText(sorter.getMvpAdv());
		
		boolean conditions = sorter.isUseCondition();
		
		String sel = sorter.getPeriodDays();
		if(!conditions){
			int days = 0;
			try{
				days = Integer.parseInt(sel);
			}catch(Exception d){}
			//not number of days, but checkbox selected
			if(days==0){
			JCheckBox[] period = {chckbxSemester, chckbxQuarter,
					  chckbxTrimester, chckbxYear};
			for(JCheckBox selection: period){
				String game = selection.getActionCommand();
				if(sel !=null && sel.equals(game)){
					selection.setSelected(true);
					daysCondition.setEnabled(false);
					break;
				}else{
					//test
					//System.out.println("SELECTION:"+selection.getActionCommand());
					//selection.setSelected(true);
				}
			}}else{
				daysCondition.setValue(days);
			}
		}else{
			useConditions.setSelected(true);
			daysCondition.setEnabled(false);
		}
		
		lastUpdate.setText("Last updated on "+sorter.getLastUpdate());

		//sanctions = sorter.getSanctions();
		//commendations = sorter.getCommendations();
		//strengths = sorter.getStrengths();
		//weaknesses = sorter.getWeaknesses();
		}else{
			JOptionPane.showMessageDialog(UpdateDepartment.this, "This Department:"+dept+" can't be found in your database.");
			dispose();
		}
		
	}

	public static void main(String[] args){
		new UpdateDepartment(null);
	}
}
