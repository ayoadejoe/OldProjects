

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

public class AppraisalControl extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JButton jobDescRemove = new JButton("Remove");;
	private JButton jobDescription = new JButton("Add Job Description");
	private Vector desc = new Vector();
	private Vector desig = new Vector();
	private Vector paramet = new Vector();
	private Vector linkpara = new Vector();
	private Vector conditions = new Vector();
	private String depart = "";
	private JTextArea description;
	private JTextArea designations;
	private JComboBox department = new JComboBox();
	private int xx = 0, c = 0, q= 0, l=0, m=0, n=0;
	private JButton desigButton = new JButton("Add Designations");
	private JButton removeDesig = new JButton("Remove");
	private JButton defineParameters = new JButton("Define Parameters");
	private JButton paraRemove = new JButton("Remove");
	private JTextArea parameters = new JTextArea();
	private parameterEntry getPara;
	private JButton defineSynergy = new JButton("External Parameters");
	private JButton linkParaRemove = new JButton("Remove");
	private JTextArea linkParameters = new JTextArea();
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	public AppraisalControl() {
		setSize(630, 457);
		setDefaultCloseOperation(AppraisalControl.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{121, 77, 64, 93, 105, 81, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 76, 0, 76, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblDepartment = new JLabel("Department");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.gridwidth = 2;
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.anchor = GridBagConstraints.EAST;
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 0;
		getContentPane().add(lblDepartment, gbc_lblDepartment);
		
		GridBagConstraints gbc_department = new GridBagConstraints();
		gbc_department.gridwidth = 3;
		gbc_department.insets = new Insets(0, 0, 5, 5);
		gbc_department.fill = GridBagConstraints.HORIZONTAL;
		gbc_department.gridx = 2;
		gbc_department.gridy = 0;
		department.setModel(new DefaultComboBoxModel(new String[] {"Choose Department"}));
		getContentPane().add(department, gbc_department);
		
		department.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				depart = department.getSelectedItem().toString();
			}
		});
		
		JLabel lblRoleModel = new JLabel("Role Model:");
		GridBagConstraints gbc_lblRoleModel = new GridBagConstraints();
		gbc_lblRoleModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoleModel.anchor = GridBagConstraints.EAST;
		gbc_lblRoleModel.gridx = 0;
		gbc_lblRoleModel.gridy = 1;
		getContentPane().add(lblRoleModel, gbc_lblRoleModel);
		
		textField = new JTextField();
		textField.setText("90-100");
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		getContentPane().add(textField, gbc_textField);
		
		JLabel label_2 = new JLabel("Outstanding:");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 2;
		gbc_label_2.gridy = 1;
		getContentPane().add(label_2, gbc_label_2);
		
		textField_1 = new JTextField();
		textField_1.setText("70-89");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 1;
		getContentPane().add(textField_1, gbc_textField_1);
		
		JLabel label_3 = new JLabel("Appraiser:");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.gridx = 4;
		gbc_label_3.gridy = 1;
		getContentPane().add(label_3, gbc_label_3);
		
		textField_2 = new JComboBox();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 5;
		gbc_textField_2.gridy = 1;
		getContentPane().add(textField_2, gbc_textField_2);
		
		JLabel label_4 = new JLabel("Above Average:");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.EAST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 2;
		getContentPane().add(label_4, gbc_label_4);
		
		textField_3 = new JTextField();
		textField_3.setText("56-69");
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 2;
		getContentPane().add(textField_3, gbc_textField_3);
		
		JLabel label_5 = new JLabel("Average:");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 2;
		gbc_label_5.gridy = 2;
		getContentPane().add(label_5, gbc_label_5);
		
		textField_4 = new JTextField();
		textField_4.setText("50-55");
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 2;
		getContentPane().add(textField_4, gbc_textField_4);
		
		JCheckBox checkBox = new JCheckBox("Use Conditions");
		checkBox.setToolTipText("checking this box will activate conditions for this department");
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox.gridx = 5;
		gbc_checkBox.gridy = 2;
		getContentPane().add(checkBox, gbc_checkBox);
		
		JLabel label_6 = new JLabel("Below Average:");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.EAST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 3;
		getContentPane().add(label_6, gbc_label_6);
		
		textField_5 = new JTextField();
		textField_5.setText("40-49");
		textField_5.setColumns(10);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 3;
		getContentPane().add(textField_5, gbc_textField_5);
		
		JLabel label_7 = new JLabel("Observation:");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.EAST;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 2;
		gbc_label_7.gridy = 3;
		getContentPane().add(label_7, gbc_label_7);
		
		textField_6 = new JTextField();
		textField_6.setText("39-0");
		textField_6.setColumns(10);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 3;
		gbc_textField_6.gridy = 3;
		getContentPane().add(textField_6, gbc_textField_6);
		
		JButton button = new JButton("Explanation");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(AppraisalControl.this, "PARAMETERS\n\nWhen you define a parameter, you" +
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
		button.setForeground(Color.MAGENTA);
		button.setFont(new Font("Arial Nova", Font.BOLD, 12));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 5;
		gbc_button.gridy = 3;
		getContentPane().add(button, gbc_button);
		
		jobDescRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { int no=-1;
				String noe = JOptionPane.showInputDialog(AppraisalControl.this, "Which number of description do you want to remove?");
				try{
					if(c<=0)jobDescRemove.setEnabled(false);
					no = Integer.parseInt(noe);
					System.out.println("Before Desc:"+desc);
					System.out.println("Remove "+(no-1)+":"+desc.get(no-1));
					desc.remove(no-1);
					System.out.println("After Desc:"+desc);
					description.setText("");c--;
					for(int u = 0; desc.size()>u; u++){
						description.append("\n"+(u+1)+". "+desc.get(u));
					}
				}catch(Exception v){JOptionPane.showMessageDialog(AppraisalControl.this, "Enter digits only.");}
			}
		});
		if(c<=0)jobDescRemove.setEnabled(false);
		jobDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(c==0)description.setText("");
				if(depart.length()<3){
					department.setBackground(Color.pink);
					JOptionPane.showMessageDialog(AppraisalControl.this, "Please enter department first, at least 3 Characters. Abbreviations are not allowed.");
				}else{
				getPara = new parameterEntry("Description", "Designation", null, null, "Add Description "+(c+1), depart, null );
				getPara.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) {c++;
					if(c>=1)jobDescRemove.setEnabled(true);
						String outcome  = getPara.getOutcome();
						System.out.println("Gotten:"+outcome);
						String [] dec = outcome.split(">");
						description.append("\n"+c+". "+dec[0]+">"+dec[1]+">"+dec[2]);
						if(getPara != null)getPara.dispose();
						desc.add(outcome);
					}
				});
				}
			}
		});
		
		GridBagConstraints gbc_jobDescription = new GridBagConstraints();
		gbc_jobDescription.insets = new Insets(0, 0, 5, 5);
		gbc_jobDescription.gridx = 0;
		gbc_jobDescription.gridy = 4;
		getContentPane().add(jobDescription, gbc_jobDescription);
		
		GridBagConstraints gbc_jobDescRemove = new GridBagConstraints();
		gbc_jobDescRemove.insets = new Insets(0, 0, 5, 5);
		gbc_jobDescRemove.gridx = 2;
		gbc_jobDescRemove.gridy = 4;
		getContentPane().add(jobDescRemove, gbc_jobDescRemove);
		
		removeDesig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { int no=-1;
				String noe = JOptionPane.showInputDialog(AppraisalControl.this, "Remove designation number:");
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
				}catch(Exception v){JOptionPane.showMessageDialog(AppraisalControl.this, "Enter digits only.");}
			}
		});
		if(c<=0)removeDesig.setEnabled(false);
		
		desigButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(q==0)designations.setText("");
				if(depart.length()<3){
					department.setBackground(Color.pink);
					JOptionPane.showMessageDialog(AppraisalControl.this, "Please enter the Department first, at least 3 Characters. Abbreviations are not allowed.");
				}else{
				getPara = new parameterEntry("Designation", "Level", "Company Code", null, "Add Designation "+(q+1), depart);
				getPara.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) {q++;
					if(q>=1)removeDesig.setEnabled(true);
						String outcome  = getPara.getOutcome();
						System.out.println("Gotten:"+outcome);
						String [] dec = outcome.split(">");
						designations.append("\n"+q+". "+dec[0]+">"+dec[1]+">"+dec[2]);
						if(getPara != null)getPara.dispose();
						desig.add(outcome);
					}
				});
				}
			}
		});
		
		
		GridBagConstraints gbc_desigButton = new GridBagConstraints();
		gbc_desigButton.gridwidth = 2;
		gbc_desigButton.insets = new Insets(0, 0, 5, 5);
		gbc_desigButton.gridx = 3;
		gbc_desigButton.gridy = 4;
		getContentPane().add(desigButton, gbc_desigButton);
		
		GridBagConstraints gbc_removeDesig = new GridBagConstraints();
		gbc_removeDesig.insets = new Insets(0, 0, 5, 0);
		gbc_removeDesig.gridx = 5;
		gbc_removeDesig.gridy = 4;
		getContentPane().add(removeDesig, gbc_removeDesig);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 5;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		description = new JTextArea();
		scrollPane.setViewportView(description);
		description.setWrapStyleWord(true);
		description.setText("1. To manage and carry out maintenance on Transmission and Studio Equipment>Technician\r\n2. Create and design operational modus operandi for the department>Head,Engineering");
		description.setRows(5);
		description.setPreferredSize(new Dimension(200, 100));
		description.setLineWrap(true);
		description.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		description.setEditable(false);
		description.setColumns(10);
		description.setBackground(new Color(255, 255, 153));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.gridx = 3;
		gbc_scrollPane_1.gridy = 5;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		designations = new JTextArea();
		scrollPane_1.setViewportView(designations);
		designations.setWrapStyleWord(true);
		designations.setRows(5);
		designations.setPreferredSize(new Dimension(200, 100));
		designations.setLineWrap(true);
		designations.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		designations.setEditable(false);
		designations.setColumns(10);
		designations.setBackground(new Color(255, 255, 153));
		
		GridBagConstraints gbc_defineParameters = new GridBagConstraints();
		gbc_defineParameters.gridwidth = 2;
		gbc_defineParameters.insets = new Insets(0, 0, 5, 5);
		gbc_defineParameters.gridx = 0;
		gbc_defineParameters.gridy = 6;
		getContentPane().add(defineParameters, gbc_defineParameters);
		
		GridBagConstraints gbc_paraRemove = new GridBagConstraints();
		gbc_paraRemove.insets = new Insets(0, 0, 5, 5);
		gbc_paraRemove.gridx = 2;
		gbc_paraRemove.gridy = 6;
		getContentPane().add(paraRemove, gbc_paraRemove);
		
		paraRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {int no=-1;
				String noe = JOptionPane.showInputDialog(AppraisalControl.this, "Remove parameter number:");
				try{
					if(l<=0)paraRemove.setEnabled(false);
					no = Integer.parseInt(noe);
					System.out.println("Before paramet:"+paramet);
					System.out.println("Remove "+(no-1)+":"+paramet.get(no-1));
					paramet.remove(no-1);
					System.out.println("After Parameters:"+paramet);
					parameters.setText("");l--;
					for(int u = 0; paramet.size()>u; u++){
						parameters.append("\n"+(u+1)+". "+paramet.get(u));
					}
				}catch(Exception v){JOptionPane.showMessageDialog(AppraisalControl.this, "Enter digits only.");}
			}
		});
		if(l<=0)paraRemove.setEnabled(false);
		defineParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(l==0)parameters.setText("");
				if(depart.length()<3){
					department.setBackground(Color.pink);
					JOptionPane.showMessageDialog(AppraisalControl.this, "Please enter the Department first, at least 3 Characters. Abbreviations are not allowed.");
				}else{
				getPara = new parameterEntry("Parameter", "Score", "Designation", null, "Add Parameter "+(l+1), depart);
				getPara.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) {l++;
					if(l>=1)paraRemove.setEnabled(true);
						String outcome  = getPara.getOutcome();
						System.out.println("Gotten:"+outcome);
						String [] dec = outcome.split(">");
						parameters.append("\n"+l+". "+dec[0]+">"+dec[1]+">"+dec[2]+">"+dec[3]);
						if(getPara != null)getPara.dispose();
						paramet.add(outcome);
					}
				});
				}
			}
		});
		
		GridBagConstraints gbc_defineSynergy = new GridBagConstraints();
		gbc_defineSynergy.gridwidth = 2;
		gbc_defineSynergy.insets = new Insets(0, 0, 5, 5);
		gbc_defineSynergy.gridx = 3;
		gbc_defineSynergy.gridy = 6;
		getContentPane().add(defineSynergy, gbc_defineSynergy);
		
		GridBagConstraints gbc_linkParaRemove = new GridBagConstraints();
		gbc_linkParaRemove.insets = new Insets(0, 0, 5, 0);
		gbc_linkParaRemove.gridx = 5;
		gbc_linkParaRemove.gridy = 6;
		getContentPane().add(linkParaRemove, gbc_linkParaRemove);
		
		if(m<=0)linkParaRemove.setEnabled(false);
		linkParaRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int no=-1;
				String noe = JOptionPane.showInputDialog(AppraisalControl.this, "Remove link-parameter number:");
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
				}catch(Exception v){JOptionPane.showMessageDialog(AppraisalControl.this, "Enter digits only.");}
			}
		});
		
		defineSynergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(m==0)linkParameters.setText("");
				if(depart.length()<3){
					department.setBackground(Color.pink);
					JOptionPane.showMessageDialog(AppraisalControl.this, "Please enter the Department first, at least 3 Characters. Abbreviations are not allowed.");
				}else{
				getPara = new parameterEntry("External Parameter", "Score",  "Department", "External Designation", "Add External Parameter "+(m+1), depart);
				getPara.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent arg0) {m++;
					if(m>=1)linkParaRemove.setEnabled(true);
						String outcome  = getPara.getOutcome();
						System.out.println("Gotten:"+outcome);
						String [] dec = outcome.split(">");
						linkParameters.append("\n"+m+". "+dec[0]+">"+dec[1]+">"+dec[2]+">"+dec[3]+">"+dec[4]);
						if(getPara != null)getPara.dispose();
						linkpara.add(outcome);
					}
				});
				}
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridwidth = 3;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 7;
		getContentPane().add(scrollPane_2, gbc_scrollPane_2);
		
		scrollPane_2.setViewportView(parameters);
		parameters.setWrapStyleWord(true);
		parameters.setText("In the order:\r\nParameter>Score>Designation");
		parameters.setLineWrap(true);
		parameters.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		parameters.setEditable(false);
		parameters.setBackground(new Color(255, 255, 153));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridwidth = 3;
		gbc_scrollPane_3.gridx = 3;
		gbc_scrollPane_3.gridy = 7;
		getContentPane().add(scrollPane_3, gbc_scrollPane_3);
		
		scrollPane_3.setViewportView(linkParameters);
		linkParameters.setWrapStyleWord(true);
		linkParameters.setText("In the order:\r\nParameter>Score>Another Department Designation");
		linkParameters.setLineWrap(true);
		linkParameters.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		linkParameters.setEditable(false);
		linkParameters.setBorder(null);
		linkParameters.setBackground(new Color(255, 255, 153));
		
		JLabel label_12 = new JLabel("Define Conditions for Staff Operation/Control/Contract");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.gridwidth = 6;
		gbc_label_12.insets = new Insets(0, 0, 5, 0);
		gbc_label_12.gridx = 0;
		gbc_label_12.gridy = 8;
		getContentPane().add(label_12, gbc_label_12);
		
		JLabel label = new JLabel("Promotion Advice:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 9;
		getContentPane().add(label, gbc_label);
		
		textField_7 = new JTextField();
		textField_7.setText("RM>OT>AA>AV>BA>OB");
		textField_7.setColumns(10);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 9;
		getContentPane().add(textField_7, gbc_textField_7);
		
		JLabel label_8 = new JLabel("Disengage Advice:");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.EAST;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 2;
		gbc_label_8.gridy = 9;
		getContentPane().add(label_8, gbc_label_8);
		
		textField_9 = new JTextField();
		textField_9.setText("RM>OT>AA>AV>BA>OB");
		textField_9.setColumns(10);
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 3;
		gbc_textField_9.gridy = 9;
		getContentPane().add(textField_9, gbc_textField_9);
		
		JLabel label_10 = new JLabel("Issue Warning On:");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.anchor = GridBagConstraints.EAST;
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 4;
		gbc_label_10.gridy = 9;
		getContentPane().add(label_10, gbc_label_10);
		
		textField_11 = new JTextField();
		textField_11.setText("RM>OT>AA>AV>BA>OB");
		textField_11.setColumns(10);
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.insets = new Insets(0, 0, 5, 0);
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridx = 5;
		gbc_textField_11.gridy = 9;
		getContentPane().add(textField_11, gbc_textField_11);
		
		JLabel label_1 = new JLabel("Increment Advice:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 10;
		getContentPane().add(label_1, gbc_label_1);
		
		textField_8 = new JTextField();
		textField_8.setText("RM>OT>AA>AV>BA>OB");
		textField_8.setColumns(10);
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 1;
		gbc_textField_8.gridy = 10;
		getContentPane().add(textField_8, gbc_textField_8);
		
		JLabel label_9 = new JLabel("MVP Advice:");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.anchor = GridBagConstraints.EAST;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 2;
		gbc_label_9.gridy = 10;
		getContentPane().add(label_9, gbc_label_9);
		
		textField_10 = new JTextField();
		textField_10.setText("RM>OT>AA>AV>BA>OB");
		textField_10.setColumns(10);
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.gridx = 3;
		gbc_textField_10.gridy = 10;
		getContentPane().add(textField_10, gbc_textField_10);
		
		JLabel label_11 = new JLabel("Support Advice:");
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.anchor = GridBagConstraints.EAST;
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 4;
		gbc_label_11.gridy = 10;
		getContentPane().add(label_11, gbc_label_11);
		
		textField_12 = new JTextField();
		textField_12.setText("RM>OT>AA>AV>BA>OB");
		textField_12.setColumns(10);
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.insets = new Insets(0, 0, 5, 0);
		gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_12.gridx = 5;
		gbc_textField_12.gridy = 10;
		getContentPane().add(textField_12, gbc_textField_12);
		
		JLabel label_13 = new JLabel("Apply Condition Every:");
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.insets = new Insets(0, 0, 0, 5);
		gbc_label_13.gridx = 0;
		gbc_label_13.gridy = 11;
		getContentPane().add(label_13, gbc_label_13);
		
		JCheckBox checkBox_1 = new JCheckBox("Trimester");
		GridBagConstraints gbc_checkBox_1 = new GridBagConstraints();
		gbc_checkBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_1.gridx = 1;
		gbc_checkBox_1.gridy = 11;
		getContentPane().add(checkBox_1, gbc_checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Quarter");
		GridBagConstraints gbc_checkBox_2 = new GridBagConstraints();
		gbc_checkBox_2.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_2.gridx = 2;
		gbc_checkBox_2.gridy = 11;
		getContentPane().add(checkBox_2, gbc_checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("Semester");
		GridBagConstraints gbc_checkBox_3 = new GridBagConstraints();
		gbc_checkBox_3.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_3.gridx = 3;
		gbc_checkBox_3.gridy = 11;
		getContentPane().add(checkBox_3, gbc_checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("1 Year");
		GridBagConstraints gbc_checkBox_4 = new GridBagConstraints();
		gbc_checkBox_4.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_4.gridx = 4;
		gbc_checkBox_4.gridy = 11;
		getContentPane().add(checkBox_4, gbc_checkBox_4);
		
		JCheckBox checkBox_5 = new JCheckBox("2 Years");
		GridBagConstraints gbc_checkBox_5 = new GridBagConstraints();
		gbc_checkBox_5.gridx = 5;
		gbc_checkBox_5.gridy = 11;
		getContentPane().add(checkBox_5, gbc_checkBox_5);


		setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new AppraisalControl();
	}

}
