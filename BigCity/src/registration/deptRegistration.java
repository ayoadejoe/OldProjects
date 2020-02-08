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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import clients.CityClient;
import javax.swing.SwingConstants;

public class deptRegistration extends JFrame {
	
	private JTextField department;
	private JSpinner DateBirth;
	private JSpinner empDate;
	private JTextArea designations;
	private JButton picButt = new JButton("Company Logo");
	private JButton thumbButt = new JButton("Add Dept Logo");
	private JPanel thumbPanel = new JPanel();
	private JButton btnEnter = new JButton("REGISTER");
	private UtilDateModel model = new UtilDateModel();
	private JPanel picPanel = new JPanel();
	private JDatePanelImpl datePanel = new JDatePanelImpl(model);
    private UtilDateModel model2 = new UtilDateModel();
	private JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
    private SimpleDateFormat generalFormat = new SimpleDateFormat("dd-MM-yyyy");
	private Date birthdate, employmentdate; private String bdate = ""; String empdate = "";
	private final JTextField DeptNo = new JTextField("00");
	private CityClient staffData = new CityClient(); private Object dob = null;
	private String surnam = null, othernam = null, homadd = null,   Phone1 = null, Phone2 = null,
			state = null, designate = null, NOK = null, NOKAdd = null, pic = null, dept = null, user = null
			, pwd = null, gend = null;
	private String IDcheck = "non";
	private ArrayList deptVector = new ArrayList();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private File file; 
	private String imageName = null;
	private JFileChooser fc = null;
	private JFileChooser tc = null;
	private JFileChooser dc = null;
	private JLabel image = new JLabel();
	private JLabel thumbImage = new JLabel();
	private Image passp=null;
	private Image thumb=null;
	private JButton doc1 = new JButton("Add Department Files");
	private Vector <File>documents = new Vector<File>();
	private int xx = 0, c = 0, q= 0, l=0, m=0, n=0;
	private float next = 0;
	private JButton btnNewButton = new JButton("Remove");
	private static Vector <byte[]> DocumentsData = new Vector<byte[]>();
	private String fileNames = "";
	private static byte[] complogo;
	private static byte[] deptlogo;
	private static byte[] docdata;
	private JTextField head;
	private JTextField top;
	private JPanel framePanel = new JPanel();
	private JScrollPane framePane = new JScrollPane(framePanel);
	private parameterEntry getPara;;
	private JButton removeDesig = new JButton("Remove");
	private JButton desigButton = new JButton("Add Designations");
	private Vector desig = new Vector();
	private String depart = "";
	private final JLabel lblDeptEmail = new JLabel("Department Email:");
	private final JTextField deptEmail = new JTextField();
	private final JLabel lblDepartmentNumbers = new JLabel("Dept. Numbers:");
	private final JTextArea deptNumbers = new JTextArea();
	private final JScrollPane scrollPane_2 = new JScrollPane();
	private JTextArea deptFiles = new JTextArea();
	public deptRegistration(JFrame parent) {
		setTitle("REGISTER DEPARTMENT");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(framePane, BorderLayout.CENTER);
		framePanel.setBackground(new Color(255, 255, 153));
		setSize(902, 530);
		setLocationRelativeTo(getParent());
		//setDefaultCloseOperation(adminRegistration.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon(deptRegistration.class.getResource("city.gif"));
		setIconImage(img.getImage());
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 68, 98, 81, 80, 0, 55, 43, 139, 0};
		gridBagLayout.rowHeights = new int[]{32, 32, 0, 0, 0, 55, 100, 23, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		framePanel.setLayout(gridBagLayout);
		
		JLabel label_1 = new JLabel("            ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 9;
		gbc_label_1.gridy = 0;
		framePanel.add(label_1, gbc_label_1);
		
		JLabel lblIdentityNo = new JLabel("Enter Dept ID:");
		GridBagConstraints gbc_lblIdentityNo = new GridBagConstraints();
		gbc_lblIdentityNo.gridwidth = 2;
		gbc_lblIdentityNo.anchor = GridBagConstraints.EAST;
		gbc_lblIdentityNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdentityNo.gridx = 0;
		gbc_lblIdentityNo.gridy = 1;
		framePanel.add(lblIdentityNo, gbc_lblIdentityNo);
		
		GridBagConstraints gbc_DeptNo = new GridBagConstraints();
		gbc_DeptNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_DeptNo.insets = new Insets(0, 0, 5, 5);
		gbc_DeptNo.gridx = 2;
		gbc_DeptNo.gridy = 1;
		DeptNo.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				if(!deptVector.isEmpty())deptVector.clear();
				deptVector.add("^checkstandards"); deptVector.add("DeptID"); deptVector.add(DeptNo.getText().trim());deptVector.add("standards");
				System.out.println("Sending out:"+deptVector);
				deptVector = staffData.getIt(deptVector);
				System.out.println("Staff gotten:"+deptVector);
				if(deptVector.get(0).toString().equals("Clash")) {
					JOptionPane.showMessageDialog(deptRegistration.this, "The staff ID you are attempting to enter already exists in the database " +
							"for \n>>>>     " +deptVector.get(1)+
							"\nIf this is a new registration, you will have to enter a new staffID.");
					DeptNo.setFont(new Font("serif", Font.BOLD, 13));
					DeptNo.setForeground(Color.RED);
					DeptNo.setText("Clash");
					btnEnter.setEnabled(false);
				}else{
				System.out.println("received "+deptVector.get(0));
				DeptNo.setFont(new Font("serif", Font.BOLD, 13));
				DeptNo.setForeground(Color.GREEN);
				btnEnter.setEnabled(true);
				}
			}
		});
		framePanel.add(DeptNo, gbc_DeptNo);
		
		JLabel lblSuper = new JLabel("Super>");
		GridBagConstraints gbc_lblSuper = new GridBagConstraints();
		gbc_lblSuper.anchor = GridBagConstraints.EAST;
		gbc_lblSuper.insets = new Insets(0, 0, 5, 5);
		gbc_lblSuper.gridx = 3;
		gbc_lblSuper.gridy = 1;
		framePanel.add(lblSuper, gbc_lblSuper);
		
		top = new JTextField();
		top.setText("Managing Director");
		GridBagConstraints gbc_top = new GridBagConstraints();
		gbc_top.gridwidth = 5;
		gbc_top.insets = new Insets(0, 0, 5, 5);
		gbc_top.fill = GridBagConstraints.HORIZONTAL;
		gbc_top.gridx = 4;
		gbc_top.gridy = 1;
		framePanel.add(top, gbc_top);
		top.setColumns(10);
		
		JLabel lblHeadOfDept = new JLabel("Head:");
		GridBagConstraints gbc_lblHeadOfDept = new GridBagConstraints();
		gbc_lblHeadOfDept.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeadOfDept.gridx = 6;
		gbc_lblHeadOfDept.gridy = 2;
		framePanel.add(lblHeadOfDept, gbc_lblHeadOfDept);
		
		head = new JTextField();
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
		gbc_picPanel.gridheight = 2;
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
		//picPanel.add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Department:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		framePanel.add(lblNewLabel, gbc_lblNewLabel);
		
		department = new JTextField();
		department.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				if(department.getText().length()<3)JOptionPane.showMessageDialog(deptRegistration.this, "Please enter at least 3 Characters. Abbreviations are not allowed.");
				depart = department.getText().toUpperCase();
				department.setText(depart);
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
		if(c<=0)removeDesig.setEnabled(false);
		
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
				 int result = dc.showOpenDialog( deptRegistration.this );
				 
				 // if user clicked Cancel button on dialog, return
				 if ( result == JFileChooser.CANCEL_OPTION )
					 System.exit( 1 );
				 File fileN = dc.getSelectedFile(); // get selected file
				// display error if invalid
				 if ( ( fileN == null ) || ( fileN.getName().equals( "" ) ) ){
					 JOptionPane.showMessageDialog( deptRegistration.this, "Invalid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE );
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
								JOptionPane.showMessageDialog(deptRegistration.this, "The image does not exists.");
							}
						documents.add(newdoc);
						fileNames+=newdoc.getName()+"`'";
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
						btnNewButton.setEnabled(true);
						deptFiles.append(xx+". "+newdoc.getName()+" added.         "+rem+"\n");
						deptFiles.append("Total Document Size now: "+allofit+"\n");
						System.out.println(documents);
						if(next/1000000 >50){
							JOptionPane.showMessageDialog(deptRegistration.this, "Documents size is getting quite large!" +
									"\nAre you uploading a book or something?\nLarge size means lots of patience for this registration to complete.");
						}
					}
			}
		});
		
		GridBagConstraints gbc_doc1 = new GridBagConstraints();
		gbc_doc1.gridwidth = 2;
		gbc_doc1.insets = new Insets(0, 0, 5, 5);
		gbc_doc1.gridx = 3;
		gbc_doc1.gridy = 3;
		framePanel.add(doc1, gbc_doc1);
		
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int y = documents.size();
				if(y==0){
					//textArea.setText("");
					btnNewButton.setEnabled(false);
				}
				if(y>0){xx--;
					//textArea.append("Removed "+documents.get(y-1)+"\n");
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
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 3;
		framePanel.add(btnNewButton, gbc_btnNewButton);
		
		desigButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(q==0)designations.setText("");
				if(depart.length()<3){
					department.setBackground(Color.pink);
					JOptionPane.showMessageDialog(deptRegistration.this, "Please enter the Department first, at least 3 Characters. Abbreviations are not allowed.");
				}else{
				getPara = new parameterEntry("Designation", "Level", "Company Code", null, "Add Designation "+(q+1), depart, null);
				getPara.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) {q++;
					if(q>=1)removeDesig.setEnabled(true);
						String outcome  = getPara.getOutcome();
						System.out.println("Gotten:"+outcome);
						String [] dec = outcome.split(">");
						designations.append("\n"+q+". "+dec[0]+">"+dec[1]+">"+dec[2]+">"+dec[3]);
						if(getPara != null)getPara.dispose();
						desig.add(outcome);
					}
				});
				}
			}
		});
		
		GridBagConstraints gbc_desigButton = new GridBagConstraints();
		gbc_desigButton.insets = new Insets(0, 0, 5, 5);
		gbc_desigButton.gridx = 7;
		gbc_desigButton.gridy = 3;
		framePanel.add(desigButton, gbc_desigButton);
		
		
		
		removeDesig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { int no=-1;
				String noe = JOptionPane.showInputDialog(deptRegistration.this, "Remove designation number:");
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
				}catch(Exception v){JOptionPane.showMessageDialog(deptRegistration.this, "Enter digits only.");}
			}
		});
		
		GridBagConstraints gbc_removeDesig = new GridBagConstraints();
		gbc_removeDesig.insets = new Insets(0, 0, 5, 5);
		gbc_removeDesig.gridx = 8;
		gbc_removeDesig.gridy = 3;
		framePanel.add(removeDesig, gbc_removeDesig);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 4;
		framePanel.add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(deptFiles);
		deptFiles.setEditable(false);
		deptFiles.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		deptFiles.setLineWrap(true);
		deptFiles.setWrapStyleWord(true);
		deptFiles.setText("Add Department Files Here");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.gridx = 7;
		gbc_scrollPane_1.gridy = 4;
		framePanel.add(scrollPane_1, gbc_scrollPane_1);
		
		designations = new JTextArea();
		scrollPane_1.setViewportView(designations);
		designations.setBackground(new Color(255, 255, 255));
		designations.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		designations.setEditable(false);
		designations.setWrapStyleWord(true);
		designations.setLineWrap(true);
		designations.setRows(5);
		designations.setPreferredSize(new Dimension(200, 100));
		designations.setColumns(10);
		
		GridBagConstraints gbc_picButt = new GridBagConstraints();
		gbc_picButt.gridwidth = 2;
		gbc_picButt.insets = new Insets(0, 0, 5, 5);
		gbc_picButt.gridx = 1;
		gbc_picButt.gridy = 5;
		framePanel.add(picButt, gbc_picButt);
		
		thumbPanel.setPreferredSize(new Dimension(100, 100));
		thumbPanel.setLayout(new BorderLayout());
		thumbPanel.setToolTipText("Add Dept logo if any");
		thumbPanel.setBackground(new Color(102, 153, 204));
		thumbPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), new Color(192, 192, 192), null, null));
		GridBagConstraints gbc_thumbPanel = new GridBagConstraints();
		gbc_thumbPanel.gridheight = 2;
		gbc_thumbPanel.gridwidth = 2;
		gbc_thumbPanel.insets = new Insets(0, 0, 5, 5);
		gbc_thumbPanel.fill = GridBagConstraints.BOTH;
		gbc_thumbPanel.gridx = 3;
		gbc_thumbPanel.gridy = 5;
		thumbImage.setHorizontalAlignment(SwingConstants.CENTER);
		thumbImage.setHorizontalTextPosition(SwingConstants.CENTER);
		thumbPanel.add(thumbImage, BorderLayout.CENTER);
		framePanel.add(thumbPanel, gbc_thumbPanel);
		
		GridBagConstraints gbc_lblDeptEmail = new GridBagConstraints();
		gbc_lblDeptEmail.anchor = GridBagConstraints.EAST;
		gbc_lblDeptEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeptEmail.gridx = 5;
		gbc_lblDeptEmail.gridy = 5;
		framePanel.add(lblDeptEmail, gbc_lblDeptEmail);
		
		GridBagConstraints gbc_deptEmail = new GridBagConstraints();
		gbc_deptEmail.gridwidth = 3;
		gbc_deptEmail.insets = new Insets(0, 0, 5, 5);
		gbc_deptEmail.fill = GridBagConstraints.BOTH;
		gbc_deptEmail.gridx = 6;
		gbc_deptEmail.gridy = 5;
		deptEmail.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {n++;
				if(n<=1)deptEmail.setText("");
			}
		});
		deptEmail.setText("Seperate emails with commas");
		framePanel.add(deptEmail, gbc_deptEmail);
		
		GridBagConstraints gbc_lblDepartmentNumbers = new GridBagConstraints();
		gbc_lblDepartmentNumbers.anchor = GridBagConstraints.EAST;
		gbc_lblDepartmentNumbers.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartmentNumbers.gridx = 5;
		gbc_lblDepartmentNumbers.gridy = 6;
		framePanel.add(lblDepartmentNumbers, gbc_lblDepartmentNumbers);
		
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridwidth = 3;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.gridx = 6;
		gbc_scrollPane_2.gridy = 6;
		framePanel.add(scrollPane_2, gbc_scrollPane_2);
		deptNumbers.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {m++;
				if(m<=1)deptNumbers.setText("");
			}
		});
		deptNumbers.setLineWrap(true);
		deptNumbers.setWrapStyleWord(true);
		deptNumbers.setText("Seperate numbers with commas");
		scrollPane_2.setViewportView(deptNumbers);
		
		
		GridBagConstraints gbc_thumbButt = new GridBagConstraints();
		gbc_thumbButt.gridwidth = 2;
		gbc_thumbButt.insets = new Insets(0, 0, 0, 5);
		gbc_thumbButt.gridx = 3;
		gbc_thumbButt.gridy = 7;
		framePanel.add(thumbButt, gbc_thumbButt);
		
		
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
							deptlogo = baos.toByteArray();
							//ImageData.add(imagedata);
					        //JOptionPane.showMessageDialog( null, new JLabel(new ImageIcon(buffImage.getScaledInstance(buffImage.getWidth(null)/2, buffImage.getHeight(null)/2, Image.SCALE_SMOOTH ))));
							}else{
								JOptionPane.showMessageDialog(deptRegistration.this, "The image does not exists.");
							}
					}catch(Exception u){
						JOptionPane.showMessageDialog(null, u.getMessage());
						u.printStackTrace();
					}
				}
			}
		});
		
		btnEnter.setBackground(new Color(178, 34, 34));
		btnEnter.setForeground(new Color(250, 250, 210));
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.insets = new Insets(0, 0, 0, 5);
		gbc_btnEnter.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEnter.gridwidth = 3;
		gbc_btnEnter.gridx = 6;
		gbc_btnEnter.gridy = 7;
		framePanel.add(btnEnter, gbc_btnEnter);
		
		btnEnter.addActionListener(new ActionListener() { boolean check = true;
			public void actionPerformed(ActionEvent arg0) {
				if(!deptVector.isEmpty())deptVector.clear();
				deptVector.add("^enterdept&");
				deptVector.add("standards");

				String depNo =DeptNo.getText().trim();
				if(depNo.length()>2){
					deptVector.add(depNo); check=true;
				}else{
					DeptNo.setBackground(Color.cyan);
					check = false;
					JOptionPane.showMessageDialog(deptRegistration.this, "Please enter Dept ID. At least 3 characters.");
				}
				
				String topp =top.getText().trim();
				if(topp.length()>2){
					deptVector.add(topp); check=true;
				}else{
					top.setBackground(Color.cyan);
					check = false;
					JOptionPane.showMessageDialog(deptRegistration.this, "Please enter Overseeing Dept/Post. At least 3 characters.");
				}

				String dip =department.getText().trim();
				if(dip.length()>2){
					deptVector.add(dip); check=true;
				}else{
					check = false;
					department.setBackground(Color.cyan);
					JOptionPane.showMessageDialog(deptRegistration.this, "Please enter Department. No abbreviations.");
				}
				
				String hd =head.getText().trim();
				if(hd.length()>2){
					deptVector.add(hd); check=true;
				}else{
					check = false;
					head.setBackground(Color.cyan);
					JOptionPane.showMessageDialog(deptRegistration.this, "Please enter Supervisor Designation. No abbreviations.");
				}

				if(complogo != null){
					deptVector.add(complogo); check=true;
				}else{
					check = false;
					image.setBackground(Color.red);
					JOptionPane.showMessageDialog(deptRegistration.this, "Please add Company Logo. No abbreviations.");
				}				
				
				if(!desig.isEmpty()){
					deptVector.add(desig); check=true;
				}else{
					check = false;
					desigButton.setBackground(Color.pink);
					designations.setBackground(Color.pink);
					JOptionPane.showMessageDialog(deptRegistration.this, "Please add Designations within the department. No abbreviations.");
				}				
				
				deptVector.add(DocumentsData);
				deptVector.add(deptlogo);
				
				String email =deptEmail.getText().trim();
				if(email.length()>2){
					deptVector.add(email); check=true;
				}else{
					check = false;
					deptEmail.setBackground(Color.cyan);
					JOptionPane.showMessageDialog(deptRegistration.this, "Please enter at least one departmental Email.");
				}

				String numbers =deptNumbers.getText().trim();
				if(numbers.length()>2){
					deptVector.add(numbers); check=true;
				}else{
					check = false;
					deptNumbers.setBackground(Color.cyan);
					JOptionPane.showMessageDialog(deptRegistration.this, "Please enter at least one number. Preferably mobile no of the Dept. Head");
				}
				
				deptVector.add(fileNames);
				System.out.println("Filenames>"+fileNames);
				if(check){
					deptVector = staffData.getIt(deptVector);
					JOptionPane.showMessageDialog(deptRegistration.this, deptVector.get(0));
					check=true;
					dispose();
				}else{
					JOptionPane.showMessageDialog(deptRegistration.this, "Entry not done, check highlighted fields");
				}
				
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
							complogo = baos.toByteArray();
							//ImageData.add(imagedata);
					        //JOptionPane.showMessageDialog( null, new JLabel(new ImageIcon(buffImage.getScaledInstance(buffImage.getWidth(null)/2, buffImage.getHeight(null)/2, Image.SCALE_SMOOTH ))));
							}else{
								JOptionPane.showMessageDialog(deptRegistration.this, "The image does not exists.");
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
			
		});
		
		setVisible(true);
	}

	public static void main(String[] args){
		new deptRegistration(null);
	}
}
