package registration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clients.CityClient;

public class parameterEntry extends JDialog {
	private JLabel desLabel = new JLabel("Select Applicable designations");
	private final JPanel contentPanel = new JPanel();
	private JLabel textLabel;
	private JTextArea text;
	private JLabel text2Label;
	private JLabel text1Label;
	private JTextField text1;
	private JTextField text2;
	private designationPanel desigPanel;
	private JComboBox combo2;
	private JButton desigButt;
	private JLabel text3Label;
	private JComboBox combo3;
	private JScrollPane scrollPane;
	private String outcome = "";
	private Vector<String> out = new Vector<String>();
	private ArrayList depts = new ArrayList<String>();
	private ArrayList desig = new ArrayList<String>();
	private String pa;
	private CityClient getData = new CityClient();
	private ArrayList<Object> strong = new ArrayList<Object>();
	private JSplitPane pane = new JSplitPane();
	private JScrollPane croll = new JScrollPane();
	private InformationEntry entry;
	private JPopupMenu popupMenu;
	private JMenuItem ImportText;
	private StringInterface selection;
	private StringInterface selectMain;
	private JFileChooser descFile;
	public static void main(String[] args) {
		try {
			parameterEntry dialog = new parameterEntry(null, null, null, null, null, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public parameterEntry(final String bigpara1, final String para2, final String para3, final String para4, final String title,
			final String depart, final GeneralInfo sorter) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(parameterEntry.class.getResource("/registration/city.gif")));
		setTitle(title);
		setDefaultCloseOperation(parameterEntry.DISPOSE_ON_CLOSE);
		//setAlwaysOnTop(true);
		setVisible(true);
		setBounds(100, 100, 540, 333);
		getContentPane().setLayout(new BorderLayout());
		pa = para3;
		contentPanel.setBackground(new Color(216, 191, 216));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{71, -41};
		gbl_contentPanel.rowHeights = new int[]{92, 38, 38, 48, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			textLabel = new JLabel();
			textLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			textLabel.setText(bigpara1);
			GridBagConstraints gbc_textLabel = new GridBagConstraints();
			gbc_textLabel.fill = GridBagConstraints.BOTH;
			gbc_textLabel.insets = new Insets(0, 0, 5, 5);
			gbc_textLabel.gridx = 0;
			gbc_textLabel.gridy = 0;
			contentPanel.add(textLabel, gbc_textLabel);
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 0;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				text = new JTextArea();
				text.setToolTipText("Right-Click to import information");
				text.setBackground(new Color(245, 222, 179));
				text.setFont(new Font("Times New Roman", Font.PLAIN, 13));
				scrollPane.setViewportView(text);
				{
					popupMenu = new JPopupMenu();
					addPopup(text, popupMenu);
					{
						ImportText = new JMenuItem("Import your text");
						popupMenu.add(ImportText);
					}
				}
			}
		}
		{
			text1Label = new JLabel();
			text1Label.setHorizontalAlignment(SwingConstants.RIGHT);
			text1Label.setText(para2);
			GridBagConstraints gbc_text1Label = new GridBagConstraints();
			gbc_text1Label.fill = GridBagConstraints.BOTH;
			gbc_text1Label.insets = new Insets(0, 0, 5, 5);
			gbc_text1Label.gridx = 0;
			gbc_text1Label.gridy = 1;
			contentPanel.add(text1Label, gbc_text1Label);
		}
		{
			text1 = new JTextField();
			GridBagConstraints gbc_text1 = new GridBagConstraints();
			gbc_text1.fill = GridBagConstraints.HORIZONTAL;
			gbc_text1.insets = new Insets(0, 0, 5, 0);
			gbc_text1.gridx = 1;
			gbc_text1.gridy = 1;
			contentPanel.add(text1, gbc_text1);
			text1.setColumns(10);
		}
		{
			text2Label = new JLabel();
			text2Label.setHorizontalAlignment(SwingConstants.RIGHT);
			text2Label.setText(para3);
			GridBagConstraints gbc_text2Label = new GridBagConstraints();
			gbc_text2Label.fill = GridBagConstraints.BOTH;
			gbc_text2Label.insets = new Insets(0, 0, 5, 5);
			gbc_text2Label.gridx = 0;
			gbc_text2Label.gridy = 2;
			contentPanel.add(text2Label, gbc_text2Label);
		}
		{
			
			if(pa != null){
			if(pa.equalsIgnoreCase("Department") || pa.equalsIgnoreCase("Designation")){
				combo2 = new JComboBox();
				if(pa.equalsIgnoreCase("Department")){
					depts = sorter.getAllDepts();
				}
				if(pa.equalsIgnoreCase("Designation")){
					depts = designationBreaker(sorter.getAllDesig());
				}
				if(depts != null){
				if(depts.isEmpty()){
					depts.add("No department registered");
				}}
				if(depts == null)depts = new ArrayList<String>();
				combo2.setModel(new DefaultComboBoxModel(depts.toArray()));
				GridBagConstraints gbc_combo2 = new GridBagConstraints();
				gbc_combo2.insets = new Insets(0, 0, 5, 0);
				gbc_combo2.fill = GridBagConstraints.HORIZONTAL;
				gbc_combo2.gridx = 1;
				gbc_combo2.gridy = 2;
				contentPanel.add(combo2, gbc_combo2);
				combo2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(pa.equalsIgnoreCase("Department")){
							String combo2Select = combo2.getSelectedItem().toString();
							desig = designationBreaker(sorter.getAllDesig());
							if(combo2Select != null){
								combo3.removeAllItems();
								desig = getDesignates(desig, combo2Select);
								for(int c=0; c<desig.size(); c++){
									String[] ray = desig.get(c).toString().split(">");
									combo3.addItem(ray[1]);
								}
								
								if(desig.size()<1){
									combo3.addItem("No registered designation");
								}
								
								revalidate();
								System.out.println("For combo3 desig:"+desig);
							}else{
								JOptionPane.showMessageDialog(parameterEntry.this, "Please choose the Department of external appraiser!");
							}
							}
					}
				});
				
			}else{
				text2 = new JTextField();
				GridBagConstraints gbc_text2 = new GridBagConstraints();
				gbc_text2.insets = new Insets(0, 0, 5, 0);
				gbc_text2.fill = GridBagConstraints.HORIZONTAL;
				gbc_text2.gridx = 1;
				gbc_text2.gridy = 2;
				contentPanel.add(text2, gbc_text2);
				text2.setColumns(10);
				}
		}
		}
		
		{
			text3Label = new JLabel();
			text3Label.setText(para4);
			text3Label.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_text3Label = new GridBagConstraints();
			gbc_text3Label.anchor = GridBagConstraints.EAST;
			gbc_text3Label.insets = new Insets(0, 0, 0, 5);
			gbc_text3Label.gridx = 0;
			gbc_text3Label.gridy = 3;
			contentPanel.add(text3Label, gbc_text3Label);
		}
		{
			combo3 = new JComboBox();
			if(desig.isEmpty()){
				desig.add("Choose Department");
				}
			if(desig != null){
			combo3.setModel(new DefaultComboBoxModel(desig.toArray()));
			}
			GridBagConstraints gbc_text3 = new GridBagConstraints();
			gbc_text3.fill = GridBagConstraints.HORIZONTAL;
			gbc_text3.gridx = 1;
			gbc_text3.gridy = 3;
			contentPanel.add(combo3, gbc_text3);
		}
		
		if(para2.equals("Designation")){
			text2Label.setVisible(false);
			text1.setVisible(false);
			combo3.setVisible(false);
			text1Label.setVisible(false);
			this.remove(text2Label);
			this.remove(text1);
			this.remove(text1Label);
			this.remove(combo3);
			reload(sorter, depart);
		}
		
		if(bigpara1.equals("Internal Parameter")){
			text2Label.setVisible(false);
			combo2.setVisible(false);
			combo3.setVisible(false);
			this.remove(text2Label);
			this.remove(combo3);
			this.remove(combo2);
			reload(sorter, depart);
		}
		
		if(bigpara1.equals("External Parameter")){
			reload2(sorter, depart);
		}
		
		if(para3 == null){
			text2Label.setVisible(false);
			revalidate();
		}
		
		if(para4 == null){
			text3Label.setVisible(false);
			combo3.setVisible(false);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(216, 191, 216));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						outcome=depart+">"+text.getText().trim();
						if(para2.equals("Designation")){
							String apply = "";
							Vector<String> like = desigPanel.getApplicable();
							if(entry != null){
								Vector<String> desc = entry.getTransVec();
								outcome += ">";
								 for(int c=0; c<like.size(); c++){
									 apply+= desigPanel.getApplicable().get(c)+"`- ";
								 }
								for(String description: desc){
									out.add(description+"`- "+apply);
								}
							}else{
								String description = text.getText();
								for(int c=0; c<like.size(); c++){
									 apply+= desigPanel.getApplicable().get(c)+"`- ";
								 }
								out.add(description+"`- "+apply);
							}
							selectMain.StringBack("+", out);
							if(para3==null)outcome +=apply;
							System.out.println("3rd outcome:"+outcome);
						}else{
						outcome += ">"+text1.getText().trim();
						}
						
						if(para2.equalsIgnoreCase("score")){
							try{
							Integer.parseInt(text1.getText().trim());
							String apply = "";	Vector<String> like = null; String description=null;
							if(desigPanel == null){
								Vector<String> desc = null;
								if(entry != null){
								desc = entry.getTransVec();
								}
								if(desc != null){
									for(String descript: desc){
										outcome = depart+">"+descript+"> "+text1.getText();
										if(para3!=null)outcome += ">"+combo2.getSelectedItem().toString().trim();
										if(para4!=null)outcome += ">"+combo3.getSelectedItem().toString().trim();
										out.add(outcome);
									}
								}else{
									outcome=depart+">"+text.getText().trim()+">"+text1.getText().trim();
									if(para3!=null)outcome += ">"+combo2.getSelectedItem().toString().trim();
									if(para4!=null)outcome += ">"+combo3.getSelectedItem().toString().trim();
									out.add(outcome);
								}
								
								System.out.println("OUTTT:"+out);
								setVisible(false);
								return;
							}else{
								System.out.println("desigpanel is not null"+out);
							if(entry != null){
								Vector<String> desc = entry.getTransVec();
								System.out.println("DESC:"+desc);
								like = desigPanel.getApplicable();
								
								outcome += ">";
								 for(int c=0; c<like.size(); c++){
									 apply+= like.get(c)+", ";
								 }
								for(String descript: desc){
									out.add(descript+"> "+text1.getText()+"> "+apply);
								}
							}else{
								description = text.getText();
								like = desigPanel.getApplicable();
								for(int c=0; c<like.size(); c++){
									 apply+= like.get(c)+", ";
								 }
								out.add(description+"> "+text1.getText()+"> "+apply);
							}
							}
							System.out.println("out:"+out);
							selectMain.StringBack("+", out);
							if(para4==null)outcome +=apply;
							System.out.println("3rd outcome:"+outcome);
							}catch(Exception f){
								f.printStackTrace();
								JOptionPane.showMessageDialog(parameterEntry.this, "Enter digits only for score");
								text1.setBackground(Color.pink);
								outcome = "";
							}
						}
						
						if(pa != null){
						if(pa.equalsIgnoreCase("Department") || pa.equalsIgnoreCase("Designation")){
							if(para3!=null)outcome += ">"+combo2.getSelectedItem().toString().trim();
						}else{
						if(para3!=null)outcome += ">"+text2.getText().trim();
						}
						}
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
		
	}

	private void reload(GeneralInfo sorter, String depart) {
		ImportText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				descFile = new JFileChooser();
				int result = descFile.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION){
					entry = new InformationEntry(descFile.getSelectedFile());
					entry.setLocationRelativeTo(getParent());
					entry.setVisible(true);
					entry.setAlwaysOnTop(true);
					
					entry.setStringInterface(new StringInterface() {
						public void StringBack(String result, Vector transVec) {
						text.setText(1+". "+transVec.get(0)+"\n");
						for(int aa=1; aa<transVec.size(); aa++){
							text.append((aa+1)+". "+transVec.get(aa)+"\n");
							}
						}
					});
				}
			}
		});
		
		depts = getDesignates(designationBreaker(sorter.getAllDesig()), depart);
		if(depts != null){
			if(depts.isEmpty()){
				depts.add("No department registered");
			}}
			if(depts == null)depts = new ArrayList<String>();
		GridBagConstraints gbc_combo2 = new GridBagConstraints();
		gbc_combo2.insets = new Insets(0, 0, 5, 0);
		gbc_combo2.fill = GridBagConstraints.HORIZONTAL;
		gbc_combo2.gridx = 1;
		gbc_combo2.gridy = 1;
		desLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		desLabel.setBackground(new Color(216, 191, 216));
		contentPanel.add(desLabel, gbc_combo2);
				desigPanel = new designationPanel(depts);
				desigPanel.setBackground(getBackground());
				croll.setViewportView(desigPanel);
				GridBagConstraints gbc_desig = new GridBagConstraints();
				gbc_desig.insets = new Insets(0, 0, 5, 0);
				gbc_desig.gridheight = 2;
				gbc_desig.fill = GridBagConstraints.BOTH;
				gbc_desig.gridwidth = 3;
				gbc_desig.gridx = 1;
				gbc_desig.gridy = 2;
				contentPanel.add(croll, gbc_desig);
				revalidate();
		this.revalidate();
		
	}
	
	private void reload2(GeneralInfo sorter, String depart) {
		ImportText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				descFile = new JFileChooser();
				int result = descFile.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION){
					entry = new InformationEntry(descFile.getSelectedFile());
					entry.setLocationRelativeTo(getParent());
					entry.setVisible(true);
					entry.setAlwaysOnTop(true);
					
					entry.setStringInterface(new StringInterface() {
						public void StringBack(String result, Vector transVec) {
						text.setText(1+". "+transVec.get(0)+"\n");
						for(int aa=1; aa<transVec.size(); aa++){
							text.append((aa+1)+". "+transVec.get(aa)+"\n");
							}
						}
					});
				}
			}
		});
	}

	private ArrayList designationBreaker(ArrayList allDesig) {
		strong.clear();
		for (int a = 0; a<allDesig.size(); a++){
			Vector input = (Vector) allDesig.get(a);
			for(int b = 0; b<input.size(); b++){
				strong.add(input.get(b));
			}
		}
		return strong;
	}

	private ArrayList getDesignates(ArrayList depts2, String combo2Select) {
		ArrayList vect = new ArrayList();
		for(int y = 0; y<depts2.size(); y++){
			if(depts2.get(y).toString().contains(combo2Select)){
				vect.add(depts2.get(y));
			}
		}
		return vect;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public void setStringInterface(StringInterface string){
		this.selectMain = string;
	}
}
