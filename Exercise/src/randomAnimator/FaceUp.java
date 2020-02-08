package randomAnimator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class FaceUp extends JFrame{
	private final JPanel formPanel = new JPanel();
	private final JLabel lblName = new JLabel("Username:");
	private final JTextField textField = new JTextField();
	private final JLabel lblSignupEmail = new JLabel("  Sign-Up Email:");
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblPassword = new JLabel("Password:");
	private final JPasswordField passwordField = new JPasswordField();
	private final JLabel lblShowPassword = new JLabel("Show Password    ");
	final JEditorPane siteDisplay = new JEditorPane();
	private final JScrollPane sitePane = new JScrollPane();
	private final JPanel progressPanel = new JPanel();
	private final JProgressBar progressBar = new JProgressBar();
	private final JButton btnSaveInformation = new JButton("Save Information");
	private final JLabel label = new JLabel("NEW ENTRY");
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnView = new JMenu("View");
	private final JMenuItem mntmNewEntry = new JMenuItem("New Entry");
	private final JMenuItem mntmViewAllEntry = new JMenuItem("View all Entry");
	private final JLabel lblFindEntry = new JLabel("Find Entry");
	//The class is maintained at default because it's visibility is intended for this package and this package alone
	FaceUp() {
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(10);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FaceUp.class.getResource("/randomAnimator/5.png")));
		setSize(628, 400);
		setResizable(false);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formPanel.setPreferredSize(new Dimension(300, 10));
		
		getContentPane().add(formPanel, BorderLayout.WEST);
		GridBagLayout gbl_formPanel = new GridBagLayout();
		gbl_formPanel.columnWidths = new int[]{70, 86, 0};
		gbl_formPanel.rowHeights = new int[]{43, 34, 36, 35, 35, 35, 30, 20, 0};
		gbl_formPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_formPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		formPanel.setLayout(gbl_formPanel);
		
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(new Color(102, 102, 204));
		label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		formPanel.add(label, gbc_label);
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.BOTH;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		formPanel.add(lblName, gbc_lblName);
		textField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		textField.setColumns(10);
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		formPanel.add(textField, gbc_textField);
		lblSignupEmail.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblSignupEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		
		GridBagConstraints gbc_lblSignupEmail = new GridBagConstraints();
		gbc_lblSignupEmail.fill = GridBagConstraints.BOTH;
		gbc_lblSignupEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblSignupEmail.gridx = 0;
		gbc_lblSignupEmail.gridy = 3;
		formPanel.add(lblSignupEmail, gbc_lblSignupEmail);
		textField_1.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				String[] siteToFind = textField_1.getText().split("@");
				 try {
					  siteDisplay.setPage("http://"+siteToFind[1]);
					  progressBar.setString("Loading Preview");
					  progressBar.setBackground(new Color(102, 255, 153));
					  progressBar.setValue(1);
				  } catch (IOException e) {
					  try {
						siteDisplay.setPage("Could not find Site or Bad Site:"+siteToFind[1]);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				      System.err.println("Attempted to read a bad URL: " + siteToFind[1]);
				  }
			}
		});
		textField_1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		textField_1.setColumns(10);
		
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		formPanel.add(textField_1, gbc_textField_1);
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 5;
		formPanel.add(lblPassword, gbc_lblPassword);
		
		final GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 5;
		passwordField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		formPanel.add(passwordField, gbc_passwordField);
		
		lblShowPassword.addMouseListener(new MouseAdapter() {
			JTextField showText = new JTextField();
			public void mousePressed(MouseEvent arg0) {
				showText.setText(passwordField.getText());
				//formPanel.remove(passwordField);
				passwordField.setVisible(false);
				formPanel.add(showText, gbc_passwordField);
				showText.setVisible(true);
				formPanel.revalidate();
			}
			public void mouseReleased(MouseEvent arg0) {
				formPanel.add(passwordField, gbc_passwordField);
				showText.setVisible(false);
				passwordField.setVisible(true);
				formPanel.revalidate();
			}
		});
		lblShowPassword.setForeground(new Color(204, 0, 153));
		lblShowPassword.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblShowPassword.setHorizontalAlignment(SwingConstants.CENTER);
		
		GridBagConstraints gbc_lblShowPassword = new GridBagConstraints();
		gbc_lblShowPassword.anchor = GridBagConstraints.EAST;
		gbc_lblShowPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblShowPassword.fill = GridBagConstraints.VERTICAL;
		gbc_lblShowPassword.gridx = 1;
		gbc_lblShowPassword.gridy = 6;
		formPanel.add(lblShowPassword, gbc_lblShowPassword);
		
		GridBagConstraints gbc_btnSaveInformation = new GridBagConstraints();
		gbc_btnSaveInformation.fill = GridBagConstraints.VERTICAL;
		gbc_btnSaveInformation.gridx = 1;
		gbc_btnSaveInformation.gridy = 7;
		formPanel.add(btnSaveInformation, gbc_btnSaveInformation);
		siteDisplay.setPreferredSize(new Dimension(600, 900));
		
		siteDisplay.addPropertyChangeListener(new PropertyChangeListener() {
			int x=0;
			public void propertyChange(PropertyChangeEvent arg0) {
				if(FaceUp.this.isFocused()){
				progressBar.setValue(x);
				if(x>=3){x=0;
					progressBar.setString("Set!");
				}
				}
			}
		});
		siteDisplay.setBorder(new CompoundBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 102), null, new Color(0, 0, 0), null)));
		siteDisplay.setEditable(false);
		String url="http://google.com";    
		  try {
			  siteDisplay.setPage(url);
		  } catch (IOException e) {
		      System.err.println("Attempted to read a bad URL: " + url);
		  }
		sitePane.setColumnHeaderView(siteDisplay);
		getContentPane().add(sitePane, BorderLayout.CENTER);
		
		getContentPane().add(progressPanel, BorderLayout.SOUTH);
		progressBar.setBackground(Color.gray);
		progressBar.setStringPainted(true);
		progressBar.setMaximum(3);
		progressPanel.add(progressBar);
		
		setJMenuBar(menuBar);
		mnView.setIcon(new ImageIcon(FaceUp.class.getResource("/randomAnimator/check_file-32.png")));
		
		menuBar.add(mnView);
		
		mnView.add(mntmNewEntry);
		
		mnView.add(mntmViewAllEntry);
		lblFindEntry.setIcon(new ImageIcon(FaceUp.class.getResource("/randomAnimator/view_file-32.png")));
		
		menuBar.add(lblFindEntry);

		lias("123");
		lias(3);
	}
	
	public double lias(int d){
		
		return 0.0;
	}
	
	private double lias(String z){
		return 1.0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FaceUp faceIt = new FaceUp();
				AbstractLogic logic = new Logic();
				logic.randomize();
				
				logic.overloaded(new ArrayList());
				
				logic.overloaded(10);
				
				logic.overloaded(11.1);
				
				logic.overloaded("working");
				
				//logic.overloaded(21, 11);   //The method overloaded(double, int) is ambiguous for the type AbstractLogic
				
				//overloaded constructor
				
				OverConstructor construct = new OverConstructor();
				OverConstructor construct2 = new OverConstructor(faceIt);
				new OverConstructor(5, "yes");		//default access overload possible
				//new OverConstructor(5);		// not possible, it's a private - The constructor OverConstructor(int) is not visible
				new OverConstructor("yes");			//protected access overload possible
			}
		});
	}
}
