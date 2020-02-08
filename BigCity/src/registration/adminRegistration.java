package registration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import clients.CityClient;

public class adminRegistration extends JDialog {
	
	private JTextField surname;
	private JTextField othernames;
	private JTextField homeAdd;
	private JSpinner DateBirth;
	private JSpinner empDate;
	private JTextField phone1;
	private JTextField phone2;
	private JTextField State;
	private JTextField designation;
	private JTextField nok;
	private JTextField nokAdd;
	private JButton picButt = new JButton("Add Picture");
	private JButton thumbButt = new JButton("Thumbprint/Sign");
	private JRadioButton male = new JRadioButton("Male");
	private JRadioButton female = new JRadioButton("Female");
	private JPanel thumbPanel = new JPanel();
	private JButton btnEnter = new JButton("REGISTER");
	private UtilDateModel model = new UtilDateModel();
	private JPanel picPanel = new JPanel();
	private JDatePanelImpl datePanel = new JDatePanelImpl(model);
    private JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
    private UtilDateModel model2 = new UtilDateModel();
	private JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
    private JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
    private SimpleDateFormat generalFormat = new SimpleDateFormat("dd-MM-yyyy");
	private Date birthdate, employmentdate; private String bdate = ""; String empdate = "";
	private final JTextField staffNo = new JTextField("00");
	private CityClient staffData = new CityClient(); private Object dob = null;
	private String surnam = null, othernam = null, homadd = null,   Phone1 = null, Phone2 = null,
			state = null, designate = null, NOK = null, NOKAdd = null, pic = null, dept = null, user = null
			, pwd = null, gend = null;
	private String IDcheck = "non";
	private ArrayList staff = new ArrayList();
	private JComboBox Department;
	private JTextField username;
	private JPasswordField password;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField email;
	private File file; 
	private String imageName = null;
	private JFileChooser fc = null;
	private JFileChooser tc = null;
	private JFileChooser dc = null;
	private JLabel image = new JLabel();
	private JLabel thumbImage = new JLabel();
	private Image passp=null;
	private Image thumb=null;
	private JButton doc1 = new JButton("Add Credentials");
	private Vector <File>documents = new Vector<File>();
	private JTextArea textArea = new JTextArea();
	private JScrollPane textPane = new JScrollPane();
	private int xx = 0;
	private float next = 0;
	private JButton btnNewButton = new JButton("Remove Doc");
	private static Vector <byte[]> DocumentsData = new Vector<byte[]>();
	private static byte[] passpdata;
	private static byte[] thumbdata;
	private static byte[] docdata;
	public adminRegistration(JFrame parent) {
		super(parent, "REGISTER NEW ADMINISTRATOR", true);
		setTitle("REGISTER NEW STAFF");
		getContentPane().setBackground(new Color(255, 255, 153));
		setSize(700, 500);
		setLocationRelativeTo(getParent());
		//setDefaultCloseOperation(adminRegistration.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon(adminRegistration.class.getResource("city.gif"));
		setIconImage(img.getImage());
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 81, 80, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{32, 32, 0, 0, 0, 53, 0, 0, 0, 0, 0, 0, 23, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel label_1 = new JLabel("            ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.gridwidth = 2;
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 13;
		gbc_label_1.gridy = 0;
		getContentPane().add(label_1, gbc_label_1);
		
		JLabel lblIdentityNo = new JLabel("Enter Staff No:");
		GridBagConstraints gbc_lblIdentityNo = new GridBagConstraints();
		gbc_lblIdentityNo.gridwidth = 2;
		gbc_lblIdentityNo.anchor = GridBagConstraints.EAST;
		gbc_lblIdentityNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdentityNo.gridx = 0;
		gbc_lblIdentityNo.gridy = 1;
		getContentPane().add(lblIdentityNo, gbc_lblIdentityNo);
		
		GridBagConstraints gbc_staffNo = new GridBagConstraints();
		gbc_staffNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_staffNo.gridwidth = 2;
		gbc_staffNo.insets = new Insets(0, 0, 5, 5);
		gbc_staffNo.gridx = 2;
		gbc_staffNo.gridy = 1;
		staffNo.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				if(!staff.isEmpty())staff.clear();
				staff.add("^checkregister^"); staff.add("StaffNo"); staff.add(staffNo.getText().trim());staff.add("stafftable");
				System.out.println("Sending out:"+staff);
				staff = staffData.getIt(staff); 
				System.out.println("Staff gotten:"+staff);
				if(staff.get(0).toString().equals("Clash")) {
					JOptionPane.showMessageDialog(adminRegistration.this, "The staff ID you are attempting to enter already exists in the database " +
							"for \n>>>>     " +staff.get(1)+
							"\nIf this is a new registration, you will have to enter a new staffID.");
					staffNo.setFont(new Font("serif", Font.BOLD, 13));
					staffNo.setForeground(Color.RED);
					staffNo.setText("Clash");
					btnEnter.setEnabled(false);
				}else{
				System.out.println("received "+staff.get(0));
				staffNo.setFont(new Font("serif", Font.BOLD, 13));
				staffNo.setForeground(Color.GREEN);
				btnEnter.setEnabled(true);
				}
			}
		});
		getContentPane().add(staffNo, gbc_staffNo);
		
		btnEnter.setBackground(new Color(178, 34, 34));
		btnEnter.setForeground(new Color(250, 250, 210));
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.gridwidth = 2;
		gbc_btnEnter.insets = new Insets(0, 0, 5, 5);
		gbc_btnEnter.gridx = 9;
		gbc_btnEnter.gridy = 1;
		getContentPane().add(btnEnter, gbc_btnEnter);
		
		picPanel.setBackground(new Color(0, 0, 51));
		picPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), new Color(0, 0, 0), null, null));
		GridBagConstraints gbc_picPanel = new GridBagConstraints();
		gbc_picPanel.gridwidth = 3;
		gbc_picPanel.gridheight = 5;
		gbc_picPanel.insets = new Insets(0, 0, 5, 5);
		gbc_picPanel.fill = GridBagConstraints.BOTH;
		gbc_picPanel.gridx = 1;
		gbc_picPanel.gridy = 2;
		getContentPane().add(picPanel, gbc_picPanel);
		
		Dimension dim2 = picPanel.getPreferredSize();
		dim2.height = 130;
		dim2.width = 120;
		picPanel.setPreferredSize(dim2);
		picPanel.setLayout(new BorderLayout());
		picPanel.add(image, BorderLayout.CENTER);
		//picPanel.add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Surname:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 2;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		surname = new JTextField();
		GridBagConstraints gbc_surname = new GridBagConstraints();
		gbc_surname.gridwidth = 2;
		gbc_surname.insets = new Insets(0, 0, 5, 5);
		gbc_surname.fill = GridBagConstraints.HORIZONTAL;
		gbc_surname.gridx = 5;
		gbc_surname.gridy = 2;
		getContentPane().add(surname, gbc_surname);
		surname.setColumns(10);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSalary.setForeground(new Color(0, 0, 51));
		GridBagConstraints gbc_lblSalary = new GridBagConstraints();
		gbc_lblSalary.anchor = GridBagConstraints.WEST;
		gbc_lblSalary.gridwidth = 7;
		gbc_lblSalary.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalary.gridx = 7;
		gbc_lblSalary.gridy = 2;
		getContentPane().add(lblSalary, gbc_lblSalary);
		
		JLabel lblName = new JLabel("Other Names:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 4;
		gbc_lblName.gridy = 3;
		getContentPane().add(lblName, gbc_lblName);
		
		othernames = new JTextField();
		GridBagConstraints gbc_othernames = new GridBagConstraints();
		gbc_othernames.gridwidth = 2;
		gbc_othernames.insets = new Insets(0, 0, 5, 5);
		gbc_othernames.fill = GridBagConstraints.HORIZONTAL;
		gbc_othernames.gridx = 5;
		gbc_othernames.gridy = 3;
		getContentPane().add(othernames, gbc_othernames);
		othernames.setColumns(10);
		
		final JSpinner salary = new JSpinner();
		salary.setModel(new SpinnerNumberModel(new Integer(10000), new Integer(100), null, new Integer(500)));
		GridBagConstraints gbc_salary = new GridBagConstraints();
		gbc_salary.fill = GridBagConstraints.HORIZONTAL;
		gbc_salary.gridwidth = 4;
		gbc_salary.insets = new Insets(0, 0, 5, 5);
		gbc_salary.gridx = 7;
		gbc_salary.gridy = 3;
		getContentPane().add(salary, gbc_salary);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.ITALIC, 12));
		username.setText("username");
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.gridwidth = 2;
		gbc_username.insets = new Insets(0, 0, 5, 5);
		gbc_username.fill = GridBagConstraints.HORIZONTAL;
		gbc_username.gridx = 5;
		gbc_username.gridy = 4;
		getContentPane().add(username, gbc_username);
		username.setColumns(10);
		username.addFocusListener(new FocusAdapter() { int g = 0;
			public void focusGained(FocusEvent arg0) {
				username.setText("");
			}
			
			public void focusLost(FocusEvent arg0) {
				if(!staff.isEmpty())staff.clear();
				staff.add("^checkregister^"); staff.add("username"); staff.add(username.getText().trim());staff.add("stafftable");
				staff = staffData.getIt(staff); 
				if(staff.get(0).toString().equals("Clash")) {
					JOptionPane.showMessageDialog(adminRegistration.this, "The Username you are attempting to enter already exists in the database " +
							"for \n" +staff.get(1)+
							"\nIf this is a new registration, you will have to enter a new Username.");
					username.setFont(new Font("serif", Font.BOLD, 13));
					username.setForeground(Color.RED);
					username.setText("Clash");
					btnEnter.setEnabled(false);
				}else{
				System.out.println("received "+staff.get(0));
				username.setFont(new Font("serif", Font.BOLD, 13));
				username.setForeground(Color.GREEN);
				btnEnter.setEnabled(true);
				}
			}
		});
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.ITALIC, 12));
		password.setText("password");
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.gridwidth = 4;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 7;
		gbc_txtPassword.gridy = 4;
		getContentPane().add(password, gbc_txtPassword);
		password.setColumns(10);
		password.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				password.setText("");
			}
			public void focusLost(FocusEvent arg0) {
				if(!staff.isEmpty())staff.clear();
				staff.add("^checkregister^"); staff.add("password"); staff.add(password.getText().trim());staff.add("stafftable");
				staff = staffData.getIt(staff); 
				if(staff.get(0).toString().equals("Clash")) {
					JOptionPane.showMessageDialog(adminRegistration.this, "The Password you are attempting to enter already exists in the database " +
							"for \n" +staff.get(1)+
							"\nIf this is a new registration, you will have to enter a new Password.");
					password.setFont(new Font("serif", Font.BOLD, 13));
					password.setForeground(Color.RED);
					password.setText("");
					btnEnter.setEnabled(false);
				}else{
				System.out.println("received "+staff.get(0));
				password.setFont(new Font("serif", Font.BOLD, 13));
				password.setForeground(Color.GREEN);
				btnEnter.setEnabled(true);
				}
			}
		});
		
		JLabel lblAddresses = new JLabel("Address:");
		GridBagConstraints gbc_lblAddresses = new GridBagConstraints();
		gbc_lblAddresses.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddresses.anchor = GridBagConstraints.EAST;
		gbc_lblAddresses.gridx = 4;
		gbc_lblAddresses.gridy = 5;
		getContentPane().add(lblAddresses, gbc_lblAddresses);
		
		homeAdd = new JTextField();
		homeAdd.setText("Enter Home Address");
		GridBagConstraints gbc_homeAdd = new GridBagConstraints();
		gbc_homeAdd.gridwidth = 2;
		gbc_homeAdd.insets = new Insets(0, 0, 5, 5);
		gbc_homeAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_homeAdd.gridx = 5;
		gbc_homeAdd.gridy = 5;
		getContentPane().add(homeAdd, gbc_homeAdd);
		homeAdd.setColumns(10);
		
		email = new JTextField();
		email.setText("Enter Email");
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.gridwidth = 4;
		gbc_email.insets = new Insets(0, 0, 5, 5);
		gbc_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_email.gridx = 7;
		gbc_email.gridy = 5;
		getContentPane().add(email, gbc_email);
		email.setColumns(10);
		
		JLabel lblPhoneNo = new JLabel("Phone 1:");
		GridBagConstraints gbc_lblPhoneNo = new GridBagConstraints();
		gbc_lblPhoneNo.anchor = GridBagConstraints.EAST;
		gbc_lblPhoneNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNo.gridx = 4;
		gbc_lblPhoneNo.gridy = 6;
		getContentPane().add(lblPhoneNo, gbc_lblPhoneNo);
		
		phone1 = new JTextField();
		GridBagConstraints gbc_phone1 = new GridBagConstraints();
		gbc_phone1.insets = new Insets(0, 0, 5, 5);
		gbc_phone1.fill = GridBagConstraints.HORIZONTAL;
		gbc_phone1.gridx = 5;
		gbc_phone1.gridy = 6;
		getContentPane().add(phone1, gbc_phone1);
		phone1.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone 2:");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.gridwidth = 2;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.anchor = GridBagConstraints.EAST;
		gbc_lblPhone.gridx = 6;
		gbc_lblPhone.gridy = 6;
		getContentPane().add(lblPhone, gbc_lblPhone);
		
		phone2 = new JTextField();
		GridBagConstraints gbc_phone2 = new GridBagConstraints();
		gbc_phone2.gridwidth = 3;
		gbc_phone2.insets = new Insets(0, 0, 5, 5);
		gbc_phone2.fill = GridBagConstraints.HORIZONTAL;
		gbc_phone2.gridx = 8;
		gbc_phone2.gridy = 6;
		getContentPane().add(phone2, gbc_phone2);
		phone2.setColumns(10);
		
		GridBagConstraints gbc_picButt = new GridBagConstraints();
		gbc_picButt.gridwidth = 3;
		gbc_picButt.insets = new Insets(0, 0, 5, 5);
		gbc_picButt.gridx = 1;
		gbc_picButt.gridy = 7;
		getContentPane().add(picButt, gbc_picButt);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth:");
		GridBagConstraints gbc_lblDateOfBirth = new GridBagConstraints();
		gbc_lblDateOfBirth.anchor = GridBagConstraints.EAST;
		gbc_lblDateOfBirth.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOfBirth.gridx = 4;
		gbc_lblDateOfBirth.gridy = 7;
		getContentPane().add(lblDateOfBirth, gbc_lblDateOfBirth);
		
		model.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try{
					birthdate = (Date) datePicker.getModel().getValue();
					bdate = generalFormat.format(birthdate);
				}catch(Exception a){}
				System.out.println(bdate);
			}
		});
		
		model2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try{
					employmentdate = (Date) datePicker2.getModel().getValue();
					empdate = generalFormat.format(employmentdate);
				}catch(Exception a){}
				System.out.println(empdate);
			}
		});
		datePicker.setMinimumSize(new Dimension(23, 23));
		datePicker.setPreferredSize(new Dimension(100, 23));
		datePicker.setToolTipText("click to select date");
		datePicker.setShowYearButtons(true);
		
		datePicker2.setToolTipText("click to select date");
		datePicker2.setShowYearButtons(true);
		
		datePicker.setForeground(getBackground());
		datePicker.setBackground(getBackground());
		datePicker.setFont(new Font("Tahoma", Font.ITALIC, 11));
		datePicker.setDoubleClickAction(true);
		GridBagConstraints gbc_DateBirth = new GridBagConstraints();
		gbc_DateBirth.insets = new Insets(0, 0, 5, 5);
		gbc_DateBirth.fill = GridBagConstraints.HORIZONTAL;
		gbc_DateBirth.gridx = 5;
		gbc_DateBirth.gridy = 7;
		getContentPane().add(datePicker, gbc_DateBirth);
		
		JLabel lblGender = new JLabel("Gender:");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.EAST;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 6;
		gbc_lblGender.gridy = 7;
		getContentPane().add(lblGender, gbc_lblGender);
		
		male.setActionCommand("Male");
		buttonGroup.add(male);
		GridBagConstraints gbc_male = new GridBagConstraints();
		gbc_male.gridwidth = 2;
		gbc_male.insets = new Insets(0, 0, 5, 5);
		gbc_male.gridx = 7;
		gbc_male.gridy = 7;
		getContentPane().add(male, gbc_male);
		
		female.setActionCommand("Female");
		buttonGroup.add(female);
		GridBagConstraints gbc_female = new GridBagConstraints();
		gbc_female.gridwidth = 2;
		gbc_female.insets = new Insets(0, 0, 5, 5);
		gbc_female.gridx = 9;
		gbc_female.gridy = 7;
		getContentPane().add(female, gbc_female);
		
		JLabel lblEmploymentDate = new JLabel("Employment Date:");
		GridBagConstraints gbc_lblEmploymentDate = new GridBagConstraints();
		gbc_lblEmploymentDate.anchor = GridBagConstraints.EAST;
		gbc_lblEmploymentDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmploymentDate.gridx = 4;
		gbc_lblEmploymentDate.gridy = 8;
		getContentPane().add(lblEmploymentDate, gbc_lblEmploymentDate);
		datePicker2.setPreferredSize(new Dimension(100, 23));
		datePicker2.setMinimumSize(new Dimension(23, 23));
		
		datePicker2.setForeground(getBackground());
		datePicker2.setBackground(getBackground());
		datePicker2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		datePicker2.setDoubleClickAction(true);
		GridBagConstraints gbc_empDate = new GridBagConstraints();
		gbc_empDate.insets = new Insets(0, 0, 5, 5);
		gbc_empDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_empDate.gridx = 5;
		gbc_empDate.gridy = 8;
		getContentPane().add(datePicker2, gbc_empDate);
		
		Dimension dim = thumbPanel.getPreferredSize();
		dim.height = 110;
		dim.width = 110;
		thumbPanel.setPreferredSize(dim2);
		thumbPanel.setLayout(new BorderLayout());
		thumbPanel.add(thumbImage, BorderLayout.CENTER);
		thumbPanel.setToolTipText("Employee's thumbprint or signature should be added here. \r\nFor Signature: This can be done on plane white paper and picture taken.\r\nFor Thumb: Finger print is best on glass and picture taken.");
		thumbPanel.setBackground(new Color(102, 153, 204));
		thumbPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), new Color(192, 192, 192), null, null));
		GridBagConstraints gbc_thumbPanel = new GridBagConstraints();
		gbc_thumbPanel.gridwidth = 3;
		gbc_thumbPanel.gridheight = 4;
		gbc_thumbPanel.insets = new Insets(0, 0, 5, 5);
		gbc_thumbPanel.fill = GridBagConstraints.BOTH;
		gbc_thumbPanel.gridx = 1;
		gbc_thumbPanel.gridy = 8;
		getContentPane().add(thumbPanel, gbc_thumbPanel);
		
		JLabel DeptLabel = new JLabel("Department:");
		GridBagConstraints gbc_DeptLabel = new GridBagConstraints();
		gbc_DeptLabel.anchor = GridBagConstraints.EAST;
		gbc_DeptLabel.insets = new Insets(0, 0, 5, 5);
		gbc_DeptLabel.gridx = 6;
		gbc_DeptLabel.gridy = 8;
		getContentPane().add(DeptLabel, gbc_DeptLabel);
		
		Department = new JComboBox();
		Department.setModel(new DefaultComboBoxModel(new String[] {"Engineering", "IT", "Administration", "Human Resources", "Accounts", "Audit/Traffic", "Programmes", "General Manager", "Managing Director", "Others"}));
		GridBagConstraints gbc_Department = new GridBagConstraints();
		gbc_Department.gridwidth = 4;
		gbc_Department.insets = new Insets(0, 0, 5, 5);
		gbc_Department.fill = GridBagConstraints.HORIZONTAL;
		gbc_Department.gridx = 7;
		gbc_Department.gridy = 8;
		getContentPane().add(Department, gbc_Department);
		
		Department.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				String who = Department.getSelectedItem().toString();
				dept = who;
				if(who.equalsIgnoreCase("Others")){
					String pos = JOptionPane.showInputDialog("Please enter new department of employee.");
					Department.setEditable(true);
					Department.setSelectedItem(pos);
					dept = pos;
				}
			}
		});
		
		JLabel lblStateOfOrigin = new JLabel("State Of Origin:");
		GridBagConstraints gbc_lblStateOfOrigin = new GridBagConstraints();
		gbc_lblStateOfOrigin.anchor = GridBagConstraints.EAST;
		gbc_lblStateOfOrigin.insets = new Insets(0, 0, 5, 5);
		gbc_lblStateOfOrigin.gridx = 4;
		gbc_lblStateOfOrigin.gridy = 9;
		getContentPane().add(lblStateOfOrigin, gbc_lblStateOfOrigin);
		
		State = new JTextField();
		GridBagConstraints gbc_State = new GridBagConstraints();
		gbc_State.insets = new Insets(0, 0, 5, 5);
		gbc_State.fill = GridBagConstraints.HORIZONTAL;
		gbc_State.gridx = 5;
		gbc_State.gridy = 9;
		getContentPane().add(State, gbc_State);
		State.setColumns(10);
		
		JLabel lblStateAddress = new JLabel("Designation:");
		GridBagConstraints gbc_lblStateAddress = new GridBagConstraints();
		gbc_lblStateAddress.anchor = GridBagConstraints.EAST;
		gbc_lblStateAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblStateAddress.gridx = 6;
		gbc_lblStateAddress.gridy = 9;
		getContentPane().add(lblStateAddress, gbc_lblStateAddress);
		
		designation = new JTextField();
		GridBagConstraints gbc_designation = new GridBagConstraints();
		gbc_designation.gridwidth = 4;
		gbc_designation.insets = new Insets(0, 0, 5, 5);
		gbc_designation.fill = GridBagConstraints.HORIZONTAL;
		gbc_designation.gridx = 7;
		gbc_designation.gridy = 9;
		getContentPane().add(designation, gbc_designation);
		designation.setColumns(10);
		
		JLabel lblNextOfKin = new JLabel("Next Of Kin:");
		GridBagConstraints gbc_lblNextOfKin = new GridBagConstraints();
		gbc_lblNextOfKin.anchor = GridBagConstraints.EAST;
		gbc_lblNextOfKin.insets = new Insets(0, 0, 5, 5);
		gbc_lblNextOfKin.gridx = 4;
		gbc_lblNextOfKin.gridy = 10;
		getContentPane().add(lblNextOfKin, gbc_lblNextOfKin);
		
		nok = new JTextField();
		GridBagConstraints gbc_nok = new GridBagConstraints();
		gbc_nok.gridwidth = 4;
		gbc_nok.insets = new Insets(0, 0, 5, 5);
		gbc_nok.fill = GridBagConstraints.HORIZONTAL;
		gbc_nok.gridx = 5;
		gbc_nok.gridy = 10;
		getContentPane().add(nok, gbc_nok);
		nok.setColumns(10);
		
		JLabel lblNokAddress = new JLabel("NOK Address:");
		GridBagConstraints gbc_lblNokAddress = new GridBagConstraints();
		gbc_lblNokAddress.anchor = GridBagConstraints.EAST;
		gbc_lblNokAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblNokAddress.gridx = 4;
		gbc_lblNokAddress.gridy = 11;
		getContentPane().add(lblNokAddress, gbc_lblNokAddress);
		
		nokAdd = new JTextField();
		GridBagConstraints gbc_nokAdd = new GridBagConstraints();
		gbc_nokAdd.gridwidth = 6;
		gbc_nokAdd.insets = new Insets(0, 0, 5, 5);
		gbc_nokAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_nokAdd.gridx = 5;
		gbc_nokAdd.gridy = 11;
		getContentPane().add(nokAdd, gbc_nokAdd);
		nokAdd.setColumns(10);
		
		GridBagConstraints gbc_thumbButt = new GridBagConstraints();
		gbc_thumbButt.gridwidth = 3;
		gbc_thumbButt.insets = new Insets(0, 0, 5, 5);
		gbc_thumbButt.gridx = 1;
		gbc_thumbButt.gridy = 12;
		getContentPane().add(thumbButt, gbc_thumbButt);
		
		JLabel label = new JLabel("               ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 13;
		getContentPane().add(label, gbc_label);
		
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
				 int result = dc.showOpenDialog( adminRegistration.this );
				 
				 // if user clicked Cancel button on dialog, return
				 if ( result == JFileChooser.CANCEL_OPTION )
					 System.exit( 1 );
				 File fileN = dc.getSelectedFile(); // get selected file
				// display error if invalid
				 if ( ( fileN == null ) || ( fileN.getName().equals( "" ) ) ){
					 JOptionPane.showMessageDialog( adminRegistration.this, "Invalid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE );
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
							} catch (IOException e) {
								e.printStackTrace();
							}
							}else{
								JOptionPane.showMessageDialog(adminRegistration.this, "The image does not exists.");
							}
						documents.add(newdoc);
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
						btnNewButton.setVisible(true);
						textArea.append(xx+". "+newdoc.getName()+" added.         "+rem+"\n");
						textArea.append("Total Document Size now: "+allofit+"\n");
						System.out.println(documents);
						if(next/1000000 >50){
							JOptionPane.showMessageDialog(adminRegistration.this, "Documents size is getting quite large!" +
									"\nAre you uploading a book or something?\nLarge size means lots of patience for this registration to complete.");
						}
					}
			}
		});
		
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int y = documents.size();
				if(y==0){
					textArea.setText("");
					btnNewButton.setVisible(false);
				}
				if(y>0){xx--;
					textArea.append("Removed "+documents.get(y-1)+"\n");
					next-= documents.get(y-1).length();
					double all = Math.round(next/1000000); String allofit= all+"MB";
					if(all<1){
						all = Math.round(next/1000);allofit= all+"KB";
					}
					documents.remove(y-1);
					System.out.println(documents);
				}
			}
		});
		
		GridBagConstraints gbc_doc1 = new GridBagConstraints();
		gbc_doc1.fill = GridBagConstraints.HORIZONTAL;
		gbc_doc1.gridwidth = 3;
		gbc_doc1.insets = new Insets(0, 0, 0, 5);
		gbc_doc1.gridx = 1;
		gbc_doc1.gridy = 13;
		getContentPane().add(doc1, gbc_doc1);
		
		textArea.setBackground(new Color(255, 255, 153));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 2;
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.gridwidth = 5;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 4;
		gbc_textArea.gridy = 12;
		textPane.setViewportView(textArea);
		getContentPane().add(textPane, gbc_textArea);
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 9;
		gbc_btnNewButton.gridy = 13;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
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
							//ImageData.add(imagedata);
					        //JOptionPane.showMessageDialog( null, new JLabel(new ImageIcon(buffImage.getScaledInstance(buffImage.getWidth(null)/2, buffImage.getHeight(null)/2, Image.SCALE_SMOOTH ))));
							}else{
								JOptionPane.showMessageDialog(adminRegistration.this, "The image does not exists.");
							}
					}catch(Exception u){
						JOptionPane.showMessageDialog(null, u.getMessage());
						u.printStackTrace();
					}
				}
			
			}
		});
		
		
		thumbButt.addActionListener(new ActionListener(){
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
						thumbImage.setIcon(new ImageIcon(thumb));
						if(file.exists()){
							System.out.println(file+" exists.");
							BufferedImage buffImage = ImageIO.read(file); 
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							boolean foundWriter = ImageIO.write(buffImage, "png", baos);
							assert foundWriter; // Not sure about this... with jpg it may work but other formats ?
							thumbdata = baos.toByteArray();
							//ImageData.add(imagedata);
					        //JOptionPane.showMessageDialog( null, new JLabel(new ImageIcon(buffImage.getScaledInstance(buffImage.getWidth(null)/2, buffImage.getHeight(null)/2, Image.SCALE_SMOOTH ))));
							}else{
								JOptionPane.showMessageDialog(adminRegistration.this, "The image does not exists.");
							}
					}catch(Exception u){
						JOptionPane.showMessageDialog(null, u.getMessage());
						u.printStackTrace();
					}
				}
			}
		});
		
		btnEnter.addActionListener(new ActionListener() { boolean check = false;
			public void actionPerformed(ActionEvent arg0) {
				try{
				 System.out.println(dept);
				 if(!staff.isEmpty())staff.clear();
				 staff.add("%registernew%");	staff.add("stafftable");	
				 IDcheck = staffNo.getText().trim()+"";
				 surnam = surname.getText().trim()+""; 		homadd = homeAdd.getText().trim()+"";
				 othernam = othernames.getText().trim()+"";	user = username.getText().trim()+"";
				 pwd = password.getText().trim()+"";			dob = bdate+"";
				 Phone2 = phone2.getText().trim()+"";			state = State.getText().trim()+"";
				 designate = designation.getText().trim()+"";	NOK = nok.getText().trim()+"";
				 NOKAdd = nokAdd.getText().trim()+"";
				 Phone1 = phone1.getText().trim()+"";	
				 gend = buttonGroup.getSelection().getActionCommand(); int Sal = (int) salary.getValue();
				 
				 staff.add(IDcheck); staff.add(surnam);  staff.add(othernam);   staff.add(dept);
				 staff.add(user);	 staff.add(pwd);	 staff.add(homadd);  staff.add(Phone1);  staff.add(Phone2);
				 staff.add(dob); 	 staff.add(empdate); staff.add(gend);
				 staff.add(state);   staff.add(NOK); staff.add(designation.getText().trim()+"");
				 staff.add(NOKAdd);  staff.add(thumbdata);  	staff.add(passpdata);	staff.add(Sal); staff.add(DocumentsData);
				 staff.add(email.getText().trim()+"");
				 for(int u=0; staff.size()>u; u++){
					 try{
					 Object value = staff.get(u);
					 System.out.println(value.toString());
					 if(value.toString().length()<=0|| value ==null){
						 check=false;
						 getHighlight(u);
						 JOptionPane.showMessageDialog(adminRegistration.this, "For this form to be submitted, you have to enter information in the box shaded pink");
						 break;
					 }else{
						 check = true;
					 }
					 }catch(Exception c){
						 check = false;
						 getHighlight(u);
						 JOptionPane.showMessageDialog(adminRegistration.this, "For this form to be submitted, you have to input information in the box shaded red");
						 break;
					 }
				 }
				 if(check){
					 System.out.println("StaffData:"+staff);
					 staff = staffData.getIt(staff);
					 JOptionPane.showMessageDialog(null, staff.get(0));
					 dispose();
				 }
				}catch(Exception f){
					JOptionPane.showMessageDialog(null, "Enter all fields expected.");
					f.printStackTrace();
					System.out.println(f.getMessage());
				}
			}

			private void getHighlight(int u) {
				 if(u==2) staffNo.setBackground(Color.pink); if(u==3) surname.setBackground(Color.pink);
					if(u==4) othernames.setBackground(Color.pink); if(u==5) Department.setBackground(Color.pink);
					if(u==6) username.setBackground(Color.pink); if(u==7) password.setBackground(Color.pink);
					if(u==8) homeAdd.setBackground(Color.pink); if(u==9) phone1.setBackground(Color.pink);
					if(u==10) phone2.setBackground(Color.pink); if(u==11) datePicker.setBackground(Color.pink);
					if(u==12) datePicker2.setBackground(Color.pink); if(u==13) male.setBackground(Color.pink);
					
					if(u==14) State.setBackground(Color.pink); 
					if(u==15) nok.setBackground(Color.pink); if(u==16) nokAdd.setBackground(Color.pink);
					if(u==17) thumbButt.setBackground(Color.red); if(u==18) picButt.setBackground(Color.red);
					
					if(u==19) salary.setBackground(Color.pink); if(u==20) email.setBackground(Color.pink);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.out.println("Windows closed");
				dispose();
			}
			
		});
		
		setVisible(true);
	}

	public static void main(String[] args){
		new adminRegistration(null);
	}
}
