

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.ImageIcon;

public class EspMain extends JFrame{
	private JPanel panel = new JPanel();
	private JScrollPane pane = new JScrollPane();
	private JScrollPane textPane = new JScrollPane();
	private DataTable statusTable;
	private EspClient esp;
	private EspDerby derby = new EspDerby();
	private JMenu mnServer = new JMenu("Server");
	private JMenuBar menuBar = new JMenuBar();
	private ArrayList servers = new ArrayList();
	private String request="MqueryX MqueryX MqueryX MqueryX MqueryX MqueryX", ip = "";
	private JTextArea espResponse = new JTextArea();
	private JButton btnRefresh = new JButton("Refresh");
	private Timer t;
	private Timer w;
	private ProgressDisplay prog;
	private boolean filling=false;
	private JButton refill100 = new JButton("Refill 100kVA");
	private JButton refill75 = new JButton("Refill 75kVA");
	private JButton stopArduino = new JButton("Shutdown Control");
	private JLabel time = new JLabel("time");
	private JProgressBar blueBar = new JProgressBar();
	private JProgressBar grayBar = new JProgressBar();
	private JLabel level100 = new JLabel("100LEVEL");
	private JLabel status100 = new JLabel("ON/OFF");
	private JLabel hr100 = new JLabel("Hours");
	private JLabel label_1 = new JLabel("Fuel Level:");
	private JLabel level75 = new JLabel("75LEVEL");
	private JLabel status75 = new JLabel("ON/OFF");
	private JLabel hour75 = new JLabel("Hours");
	private boolean ref=true, fill75=false, fill100=false, calibdone=false;
	private JButton calib100 = new JButton("Calibrate 100kVA");
	private JButton calib75 = new JButton("Calibrate 75kVA");
	private SimpleDateFormat generalFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private SimpleDateFormat getDateOnly = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat getTimeOnly = new SimpleDateFormat("HH:mm");
	private JPanel View = new JPanel();
	private String rec100="d", rec75="d";
	private JRadioButton rdbtnRawDataEntry = new JRadioButton("Raw Data Entry");
	private JRadioButton rdbtnProcessedData = new JRadioButton("Processed Data");
	private JScrollPane tabPanel = new JScrollPane();
	private JPanel ControlView = new JPanel();
	private ArrayList<Integer> realTimeData = new ArrayList<Integer>();
	private Timer mm;
	private GraphLines Graph = null;
	private GraphPanel dataGraph = null;
	private Vector bigData = new Vector();
	private Vector smallData = new Vector();
	private Vector prevBigData = new Vector();
	private Vector prevSmallData = new Vector();
	private Vector graphData = new Vector();
	private boolean on100 = false;
	private boolean on75 = false;
	private File daily100 = new File("100kVA Fuel Log");
	private File daily75 = new File("75kVA Fuel Log");
	private FileSerializer files = new FileSerializer();
	private CheckDataContent check = new CheckDataContent();
	private JButton deleteData = new JButton("");
	private JComboBox dataToAnalyse = new JComboBox();
	private JButton prevData = new JButton("");
	private JButton nextData = new JButton("");
	public EspMain() {
		
		try {
			
			prevBigData = files.loadFromFile(daily100);
			if(!check.todayEntry(prevBigData)){
				System.out.println("Todays date will now be added");
				prevBigData.add(new Date());
				files.savetoFile(prevBigData, daily100);
			}else{
				System.out.println("Todays date already added");
			}
			
			prevSmallData = files.loadFromFile(daily75);
			if(!check.todayEntry(prevSmallData)){
				prevSmallData.add(new Date());
				files.savetoFile(prevSmallData, daily75);
			}else{
				System.out.println("Todays date already added");
			}
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(EspMain.class.getResource("/espTest1/4.png")));
		setSize(800,502);
		pane.setPreferredSize(new Dimension(800, 500));
		panel.setPreferredSize(new Dimension(800, 400));
		pane.setViewportView(panel);
		getContentPane().add(pane, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel tankPanel = new JPanel();
		tankPanel.setBackground(new Color(102, 102, 204));
		tankPanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.RAISED, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		tankPanel.setPreferredSize(new Dimension(300, 1000));
		panel.add(tankPanel, BorderLayout.WEST);
		GridBagLayout gbl_tankPanel = new GridBagLayout();
		gbl_tankPanel.columnWidths = new int[]{40, 100, 29, 100, 0};
		gbl_tankPanel.rowHeights = new int[]{25, 61, 22, 200, 14, 71, 0, 0, 0, 0, 65, 0, 0, 0, 0, 45, 0, 0, 0, 0};
		gbl_tankPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_tankPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		tankPanel.setLayout(gbl_tankPanel);
		
		JLabel lblCityEngineeringDept = new JLabel("CITY ENGINEERING DEPT.");
		lblCityEngineeringDept.setFont(new Font("Times New Roman", Font.BOLD, 17));
		GridBagConstraints gbc_lblCityEngineeringDept = new GridBagConstraints();
		gbc_lblCityEngineeringDept.gridwidth = 3;
		gbc_lblCityEngineeringDept.insets = new Insets(0, 0, 5, 5);
		gbc_lblCityEngineeringDept.gridx = 1;
		gbc_lblCityEngineeringDept.gridy = 1;
		tankPanel.add(lblCityEngineeringDept, gbc_lblCityEngineeringDept);
		blueBar.setForeground(new Color(51, 102, 153));
		
		blueBar.setStringPainted(true);
		blueBar.setMaximum(5000);
		blueBar.setMinimum(0);
		blueBar.setFont(new Font("TITUS Cyberbit Basic", Font.BOLD, 14));
		blueBar.setPreferredSize(new Dimension(100, 200));
		blueBar.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_blueBar = new GridBagConstraints();
		gbc_blueBar.fill = GridBagConstraints.BOTH;
		gbc_blueBar.insets = new Insets(0, 0, 5, 5);
		gbc_blueBar.gridx = 1;
		gbc_blueBar.gridy = 3;
		tankPanel.add(blueBar, gbc_blueBar);
		grayBar.setForeground(new Color(0, 102, 153));
		
		
		grayBar.setFont(new Font("TITUS Cyberbit Basic", Font.BOLD, 14));
		grayBar.setStringPainted(true);
		grayBar.setMaximum(2000);
		grayBar.setMinimum(0);
		grayBar.setPreferredSize(new Dimension(100, 200));
		grayBar.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_grayBar = new GridBagConstraints();
		gbc_grayBar.fill = GridBagConstraints.BOTH;
		gbc_grayBar.insets = new Insets(0, 0, 5, 0);
		gbc_grayBar.gridx = 3;
		gbc_grayBar.gridy = 3;
		tankPanel.add(grayBar, gbc_grayBar);
		
		JLabel label = new JLabel("Blue Tank");
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 4;
		tankPanel.add(label, gbc_label);
		
		JLabel lblGrayTank = new JLabel("Gray Tank");
		lblGrayTank.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblGrayTank = new GridBagConstraints();
		gbc_lblGrayTank.insets = new Insets(0, 0, 5, 0);
		gbc_lblGrayTank.anchor = GridBagConstraints.NORTH;
		gbc_lblGrayTank.gridx = 3;
		gbc_lblGrayTank.gridy = 4;
		tankPanel.add(lblGrayTank, gbc_lblGrayTank);
		
		JLabel lblvaGenerator = new JLabel("100VA GENERATOR");
		lblvaGenerator.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblvaGenerator = new GridBagConstraints();
		gbc_lblvaGenerator.gridwidth = 3;
		gbc_lblvaGenerator.insets = new Insets(0, 0, 5, 0);
		gbc_lblvaGenerator.gridx = 1;
		gbc_lblvaGenerator.gridy = 6;
		tankPanel.add(lblvaGenerator, gbc_lblvaGenerator);
		
		JLabel lblFuelLevel = new JLabel("Fuel Level:");
		lblFuelLevel.setFont(new Font("Calibri Light", Font.BOLD, 13));
		GridBagConstraints gbc_lblFuelLevel = new GridBagConstraints();
		gbc_lblFuelLevel.anchor = GridBagConstraints.EAST;
		gbc_lblFuelLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFuelLevel.gridx = 1;
		gbc_lblFuelLevel.gridy = 7;
		tankPanel.add(lblFuelLevel, gbc_lblFuelLevel);
		
		GridBagConstraints gbc_level100 = new GridBagConstraints();
		gbc_level100.anchor = GridBagConstraints.WEST;
		gbc_level100.insets = new Insets(0, 0, 5, 0);
		gbc_level100.gridx = 3;
		gbc_level100.gridy = 7;
		tankPanel.add(level100, gbc_level100);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Calibri Light", Font.BOLD, 13));
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 1;
		gbc_lblStatus.gridy = 8;
		tankPanel.add(lblStatus, gbc_lblStatus);
		
		GridBagConstraints gbc_status100 = new GridBagConstraints();
		gbc_status100.anchor = GridBagConstraints.WEST;
		gbc_status100.insets = new Insets(0, 0, 5, 0);
		gbc_status100.gridx = 3;
		gbc_status100.gridy = 8;
		tankPanel.add(status100, gbc_status100);
		
		JLabel lblHoursRan = new JLabel("Hours Ran:");
		lblHoursRan.setFont(new Font("Calibri Light", Font.BOLD, 13));
		GridBagConstraints gbc_lblHoursRan = new GridBagConstraints();
		gbc_lblHoursRan.anchor = GridBagConstraints.EAST;
		gbc_lblHoursRan.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoursRan.gridx = 1;
		gbc_lblHoursRan.gridy = 9;
		tankPanel.add(lblHoursRan, gbc_lblHoursRan);
		
		
		GridBagConstraints gbc_hr100 = new GridBagConstraints();
		gbc_hr100.anchor = GridBagConstraints.WEST;
		gbc_hr100.insets = new Insets(0, 0, 5, 0);
		gbc_hr100.gridx = 3;
		gbc_hr100.gridy = 9;
		tankPanel.add(hr100, gbc_hr100);
		
		JLabel lblkvaGenerator = new JLabel("75KVA GENERATOR");
		lblkvaGenerator.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GridBagConstraints gbc_lblkvaGenerator = new GridBagConstraints();
		gbc_lblkvaGenerator.insets = new Insets(0, 0, 5, 0);
		gbc_lblkvaGenerator.gridwidth = 3;
		gbc_lblkvaGenerator.gridx = 1;
		gbc_lblkvaGenerator.gridy = 11;
		tankPanel.add(lblkvaGenerator, gbc_lblkvaGenerator);
		
		
		label_1.setFont(new Font("Calibri Light", Font.BOLD, 12));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 12;
		tankPanel.add(label_1, gbc_label_1);
		
		
		GridBagConstraints gbc_level75 = new GridBagConstraints();
		gbc_level75.anchor = GridBagConstraints.WEST;
		gbc_level75.insets = new Insets(0, 0, 5, 0);
		gbc_level75.gridx = 3;
		gbc_level75.gridy = 12;
		tankPanel.add(level75, gbc_level75);
		
		JLabel label_2 = new JLabel("Status:");
		label_2.setFont(new Font("Calibri Light", Font.BOLD, 12));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 13;
		tankPanel.add(label_2, gbc_label_2);
		
		GridBagConstraints gbc_status75 = new GridBagConstraints();
		gbc_status75.anchor = GridBagConstraints.WEST;
		gbc_status75.insets = new Insets(0, 0, 5, 0);
		gbc_status75.gridx = 3;
		gbc_status75.gridy = 13;
		tankPanel.add(status75, gbc_status75);
		
		JLabel label_3 = new JLabel("Hours Ran:");
		label_3.setFont(new Font("Calibri Light", Font.BOLD, 12));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 14;
		tankPanel.add(label_3, gbc_label_3);
		
		
		GridBagConstraints gbc_hour75 = new GridBagConstraints();
		gbc_hour75.insets = new Insets(0, 0, 5, 0);
		gbc_hour75.anchor = GridBagConstraints.WEST;
		gbc_hour75.gridx = 3;
		gbc_hour75.gridy = 14;
		tankPanel.add(hour75, gbc_hour75);
		
		panel.add(ControlView, BorderLayout.CENTER);
		ControlView.setLayout(new BorderLayout(0, 0));
		
		JPanel Control = new JPanel();
		Control.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		Control.setPreferredSize(new Dimension(300, 400));
		ControlView.add(Control, BorderLayout.WEST);
		Control.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnRefresh.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				btnRefresh.setEnabled(false);
				ref=true;
				request="MqueryX MqueryX MqueryX MqueryX MqueryX MqueryX";
				if(servers.size()>2){
					String resp = queryServer("refresh", "Just a moment please. Connecting to your THING...");
					btnRefresh.setEnabled(true);
					if(on100)if(bigData.size()>0) {
						graphPlotter(bigData.size()/2);
						try {
							if(!daily100.exists())daily100.createNewFile();
							files.savetoFile(prevBigData, daily100);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					if(on75)if(smallData.size()>0){
						graphPlotter(smallData.size()/2);
						try {
							if(!daily75.exists())daily75.createNewFile();
							files.savetoFile(prevSmallData, daily75);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}else{
					btnRefresh.setEnabled(true);
				}
				if(!save.exists()){try {save.createNewFile();} catch (IOException e) {e.printStackTrace();}}
				try {
					PrintStream saveFile = new PrintStream(save);
					saveFile.println(realTimeData);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		btnRefresh.setBackground(SystemColor.activeCaption);
		Control.add(btnRefresh);
		
		JButton btnResetServer = new JButton("Reset THING");
		btnResetServer.setBorder(new LineBorder(new Color(0, 0, 0)));
		Control.add(btnResetServer);
		
		stopArduino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stopArduino.setEnabled(false);
				request="SHUTDNX SHUTDNX SHUTDNX SHUTDNX SHUTDNX SHUTDNX";
				if(servers.size()>2){
				String resp = queryServer("stopArduino", "Initiating Controller Shutdown. Connecting to your THING...");
				stopArduino.setEnabled(true);
				}else{
					stopArduino.setEnabled(true);
				}
			}
		});
		stopArduino.setBorder(new LineBorder(new Color(0, 0, 0)));
		Control.add(stopArduino);
		
		refill75.setActionCommand("START75");
		refill100.setActionCommand("START100");
		
		refill100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fill100 = true;
				refill100.setEnabled(false);
				if(servers.size()>2){
				
				if(refill100.getActionCommand().equals("START100")){
				request = "STT100X STT100X STT100X STT100X STT100X STT100X";
				String resp = queryServer("100kva", "Refuelling of 100kVA initiated... connecting");
				}else if(refill100.getActionCommand().equals("STOP100")){
					request = "STP100X STP100X STP100X STP100X STP100X STP100X";
					String resp = queryServer("100kva", "100kVA Fuelling will be cancelled... connecting");
				}
				
					refill100.setEnabled(true);
				}else{
					refill100.setEnabled(true);
				}
			}
		});
		refill100.setBorder(new LineBorder(new Color(0, 0, 0)));
		Control.add(refill100);
		
		refill75.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fill75 = true;
				refill75.setEnabled(false);
				if(servers.size()>2){
					
				if(refill75.getActionCommand().equals("START75")){
					request= "STT075X STT075X STT075X STT075X STT075X STT075X";
					String resp = queryServer("75kva", "Refuelling of 75kVA initiated... connecting");
					
					}else if(refill75.getActionCommand().equals("STOP75")) {
						request = "STP075X STP075X STP075X STP075X STP075X STP075X";
						String resp = queryServer("75kva", "75kVA Fuelling will be cancelled... connecting");
					}
				
					refill75.setEnabled(true);
				}else{
					refill75.setEnabled(true);
				}
			}
		});
		refill75.setBorder(new LineBorder(new Color(0, 0, 0)));
		Control.add(refill75);
		
		calib100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final Calibrator calibrate = new Calibrator("100kVA GENERATOR");
				
				calibrate.setIntInterface(new IntInterface() {
					String maxi; String mini;
					public void StringBack(int max, int min, String resp) {
						calibdone = false;
						if(max<10&& max>-1)maxi = "00000"+max+"X";
						if(max<100&& max>9) maxi = "0000"+max+"X";
						if(max<1000&& max>99) maxi = "000"+max+"X";
						if(max<10000&& max>999) maxi = "00"+max+"X";
						if(max<100000&& max>9999) maxi = "0"+max+"X";
						
						if(min<10&& min>-1)mini = "00000"+min+"X";
						if(min<100&& min>9) mini = "0000"+min+"X";
						if(min<1000&& min>99) mini = "000"+min+"X";
						if(min<10000&& min>999) mini = "00"+min+"X";
						if(min<100000&& min>9999) mini = "0"+min+"X";
						if(min<=50000 || min>=0 || max<=50000 || max>=0){
						request ="CAL100X CAL100X CAL100X CAL100X "+maxi+" "+mini;
						queryServer("Calibration", "Calibration of 100kVA... connecting");
						System.out.println("Calibration");
						calibrate.dispose();
						}else{
							JOptionPane.showMessageDialog(EspMain.this, "Please enter values between 0 - 50000");
						}
					}
				});
			}
		});
		calib100.setBorder(new LineBorder(new Color(0, 0, 0)));
		Control.add(calib100);
		
		
		
		calib75.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Calibrator calibrate = new Calibrator("75kVA GENERATOR");
				calibrate.setIntInterface(new IntInterface() {
					String maxi; String mini;
					public void StringBack(int max, int min, String resp) {
						calibdone = false;
						if(max<10&& max>-1)maxi = "00000"+max+"X";
						if(max<100&& max>9) maxi = "0000"+max+"X";
						if(max<1000&& max>99) maxi = "000"+max+"X";
						if(max<10000&& max>999) maxi = "00"+max+"X";
						if(max<100000&& max>9999) maxi = "0"+max+"X";
						
						if(min<10&& min>-1)mini = "00000"+min+"X";
						if(min<100&& min>9) mini = "0000"+min+"X";
						if(min<1000&& min>99) mini = "000"+min+"X";
						if(min<10000&& min>999) mini = "00"+min+"X";
						if(min<100000&& min>9999) mini = "0"+min+"X";
						if(min<=50000 || min>=0 || max<=50000 || max>=0){
						request ="CAL075X CAL075X CAL075X CAL075X "+maxi+" "+mini;
						System.out.println(" 75 Calibration request:"+request);
						queryServer("Calibration", "Calibration of 75kVA... connecting");
							System.out.println("Calibration");
							calibrate.dispose();
						
						}else{
							JOptionPane.showMessageDialog(EspMain.this, "Please enter values between 0 - 50000");
						}
					}
				});
			}
		});
		calib75.setBorder(new LineBorder(new Color(0, 0, 0)));
		Control.add(calib75);
		
		JButton btnSetTime = new JButton("Set Time");
		btnSetTime.setBorder(new LineBorder(new Color(0, 0, 0)));
		Control.add(btnSetTime);
		
		JPanel tankView = new JPanel();
		tankView.setBackground(SystemColor.controlHighlight);
		tankView.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		Control.add(tankView);
		tankView.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel rdbtnViewBlueTank = new JLabel("View Tank Details");
		rdbtnViewBlueTank.setHorizontalAlignment(SwingConstants.CENTER);
		tankView.add(rdbtnViewBlueTank);
		
		JRadioButton rdbtnGrayTank = new JRadioButton("Gray Tank");
		buttonGroup_3.add(rdbtnGrayTank);
		tankView.add(rdbtnGrayTank);
		
		JRadioButton rdbtnkvaGen = new JRadioButton("Blue Tank");
		buttonGroup_3.add(rdbtnkvaGen);
		tankView.add(rdbtnkvaGen);
		
		JPanel ViewGen = new JPanel();
		ViewGen.setBackground(SystemColor.controlHighlight);
		ViewGen.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		Control.add(ViewGen);
		ViewGen.setLayout(new GridLayout(3, 0, 0, 0));
		
		JLabel lblViewGenerators = new JLabel("View Generators");
		lblViewGenerators.setHorizontalAlignment(SwingConstants.CENTER);
		ViewGen.add(lblViewGenerators);
		
		JRadioButton rdbtnkvaGen_1 = new JRadioButton("75kVA Gen");
		buttonGroup_2.add(rdbtnkvaGen_1);
		ViewGen.add(rdbtnkvaGen_1);
		
		JRadioButton radioButton_1 = new JRadioButton("100kVA Gen");
		buttonGroup_2.add(radioButton_1);
		ViewGen.add(radioButton_1);
		
		JPanel viewOthers = new JPanel();
		viewOthers.setBackground(SystemColor.controlHighlight);
		viewOthers.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		Control.add(viewOthers);
		viewOthers.setLayout(new GridLayout(3, 0, 0, 0));
		
		JLabel lblVi = new JLabel("View Status");
		lblVi.setHorizontalAlignment(SwingConstants.CENTER);
		viewOthers.add(lblVi);
		
		JRadioButton rdbtnFuelUsageGraph = new JRadioButton("Fuel Usage Graph");
		buttonGroup_1.add(rdbtnFuelUsageGraph);
		viewOthers.add(rdbtnFuelUsageGraph);
		
		JRadioButton rdbtnServerProbe = new JRadioButton("Server Probe");
		buttonGroup_1.add(rdbtnServerProbe);
		viewOthers.add(rdbtnServerProbe);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		panel_1.setBackground(SystemColor.controlHighlight);
		Control.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 0, 0, 0));
		
		JLabel viewTables = new JLabel("View Tables");
		viewTables.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(viewTables);
		buttonGroup.add(rdbtnRawDataEntry);
		
		rdbtnRawDataEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent w) {
				if(rdbtnRawDataEntry.isSelected()){
					System.out.println("Raw Data Table Loading");
					if(statusTable != null){
						statusTable.setVisible(false);
						tabPanel.remove(statusTable);
						statusTable = new DataTable();
						statusTable.setPreferredSize(new Dimension(600, 200));
						tabPanel.setViewportView(statusTable);
						tabPanel.revalidate();
					}
				}
			}
		});
		panel_1.add(rdbtnRawDataEntry);
		buttonGroup.add(rdbtnProcessedData);
		
		panel_1.add(rdbtnProcessedData);
		
		tabPanel.setPreferredSize(new Dimension(600, 200));
		
		statusTable = new DataTable();
		statusTable.setPreferredSize(new Dimension(600, 200));
		tabPanel.setViewportView(statusTable);
		ControlView.add(tabPanel, BorderLayout.SOUTH);
		
		
		espResponse.setWrapStyleWord(true);
		espResponse.setLineWrap(true);
		espResponse.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		espResponse.setPreferredSize(new Dimension(200, 800));
		textPane.setViewportView(espResponse);
		ControlView.add(textPane, BorderLayout.CENTER);
		
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnServer);
		
		JMenuItem mntmNewAddress = new JMenuItem("New Address");
		mnServer.add(mntmNewAddress);
		
		JPanel toolPanel = new JPanel();
		menuBar.add(toolPanel);
		GridBagLayout gbl_toolPanel = new GridBagLayout();
		gbl_toolPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_toolPanel.rowHeights = new int[]{0, 0};
		gbl_toolPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_toolPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		toolPanel.setLayout(gbl_toolPanel);
		GridBagConstraints gbc_time = new GridBagConstraints();
		gbc_time.gridwidth = 2;
		gbc_time.insets = new Insets(0, 0, 0, 5);
		gbc_time.gridx = 0;
		gbc_time.gridy = 0;
		toolPanel.add(time, gbc_time);
		
		JLabel label_4 = new JLabel("          ");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.gridwidth = 5;
		gbc_label_4.insets = new Insets(0, 0, 0, 5);
		gbc_label_4.gridx = 7;
		gbc_label_4.gridy = 0;
		toolPanel.add(label_4, gbc_label_4);
		
		JLabel lblDataAnalysis = new JLabel("Data Analysis:");
		GridBagConstraints gbc_lblDataAnalysis = new GridBagConstraints();
		gbc_lblDataAnalysis.gridwidth = 3;
		gbc_lblDataAnalysis.insets = new Insets(0, 0, 0, 5);
		gbc_lblDataAnalysis.gridx = 12;
		gbc_lblDataAnalysis.gridy = 0;
		toolPanel.add(lblDataAnalysis, gbc_lblDataAnalysis);
		
		dataToAnalyse.setToolTipText("Select Data to analyse");
		dataToAnalyse.setModel(new DefaultComboBoxModel(new String[] {"100kVA Cons.", "75 kVA Cons.", "Blue Tank", "Gray Tank"}));
		dataToAnalyse.setSelectedIndex(0);
		GridBagConstraints gbc_dataToAnalyse = new GridBagConstraints();
		gbc_dataToAnalyse.fill = GridBagConstraints.VERTICAL;
		gbc_dataToAnalyse.anchor = GridBagConstraints.EAST;
		gbc_dataToAnalyse.gridwidth = 3;
		gbc_dataToAnalyse.insets = new Insets(0, 0, 0, 5);
		gbc_dataToAnalyse.gridx = 15;
		gbc_dataToAnalyse.gridy = 0;
		toolPanel.add(dataToAnalyse, gbc_dataToAnalyse);
		
		prevData.setToolTipText("Previous");
		prevData.setBackground(new Color(255, 204, 255));
		prevData.setIcon(new ImageIcon(EspMain.class.getResource("/espTest1/chevron_left-32.png")));
		GridBagConstraints gbc_prevData = new GridBagConstraints();
		gbc_prevData.anchor = GridBagConstraints.EAST;
		gbc_prevData.insets = new Insets(0, 0, 0, 5);
		gbc_prevData.gridx = 18;
		gbc_prevData.gridy = 0;
		toolPanel.add(prevData, gbc_prevData);
		
		nextData.addActionListener(new ActionListener() {
			int z =0, q=0;
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println((String)dataToAnalyse.getSelectedItem());
				switch((String)dataToAnalyse.getSelectedItem()){
				case "100kVA Cons.":
					Vector dates = new Vector();
					Vector prevDatat = prevBigData;
					int s = prevBigData.size();
					for(int y=(s-1); y>0; y--){
						if(prevBigData.get(y).getClass().isInstance(new Date())){
							dates.add(prevBigData.get(y));
							prevDatat.remove(y);
						}
					}
					
					int pages = prevDatat.size()/50;				//for 25 readings and 25 time lines
					int remain = prevDatat.size()%50;
					if(z<pages && z>-1){
						prevDatat = check.next(prevDatat, z);
						System.out.println("");
						System.out.println("page:"+z+" > "+prevDatat);
						}else if(z == (pages)){	
							prevDatat = check.next(prevDatat, -1);
							System.out.println(" Last page:"+z+" > "+prevDatat);
							z=-1;
							System.out.println(dates);
							System.out.println(prevBigData);
							System.out.println("no of pages:"+pages+" remaining:"+remain);
							}
					z++;
					break;
				case "75 kVA Cons.":
					Vector dates2 = new Vector();
					Vector prevDatat2 = prevSmallData;
					int g = prevSmallData.size();
					for(int y=(g-1); y>0; y--){
						if(prevSmallData.get(y).getClass().isInstance(new Date())){
							dates2.add(prevSmallData.get(y));
							prevDatat2.remove(y);
						}
					}
					
					int pages2 = prevDatat2.size()/50;				//for 25 readings and 25 time lines
					int remain2 = prevDatat2.size()%50;
					if(q<pages2 && q>-1){
						prevDatat2 = check.next(prevDatat2, q);
						System.out.println("");
						System.out.println("page:"+q+" > "+prevDatat2);
						}else if(q == (pages2)){	
							prevDatat2 = check.next(prevDatat2, -1);
							System.out.println(" Last page:"+q+" > "+prevDatat2);
							q=-1;
							System.out.println(dates2);
							System.out.println(prevSmallData);
							System.out.println("no of pages:"+pages2+" remaining:"+remain2);
							}
					q++;
					break;
					
				default:
					break;
				}
			}
		});
		nextData.setToolTipText("Next");
		nextData.setBackground(new Color(204, 204, 255));
		nextData.setIcon(new ImageIcon(EspMain.class.getResource("/espTest1/chevron_right-32.png")));
		GridBagConstraints gbc_nextData = new GridBagConstraints();
		gbc_nextData.insets = new Insets(0, 0, 0, 5);
		gbc_nextData.anchor = GridBagConstraints.EAST;
		gbc_nextData.gridx = 19;
		gbc_nextData.gridy = 0;
		toolPanel.add(nextData, gbc_nextData);
		deleteData.setBackground(new Color(153, 102, 102));
		
		deleteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteTable delete = null;
				switch((String)dataToAnalyse.getSelectedItem()){
				case "100kVA Cons.":
					delete = new DeleteTable(prevBigData);
					delete.setvectorInterface(new VectorInterface() {
						public void vectorBack(Vector value1, Vector value2) {
							prevBigData = check.dataToDelete(prevBigData, value1);
							try {
								files.savetoFile(prevBigData, daily100);
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.out.println("Edited big value received"+prevBigData);
						}
					});
					break;
				case "75 kVA Cons.":
					delete = new DeleteTable(prevSmallData);
					delete.setvectorInterface(new VectorInterface() {
						public void vectorBack(Vector value1, Vector value2) {
							prevSmallData = check.dataToDelete(prevSmallData, value1);
							try {
								files.savetoFile(prevSmallData, daily75);
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.out.println("Edited small value received"+prevSmallData);
						}
					});
					break;
					
				default:
					break;
				}
				
			}
		});
		deleteData.setToolTipText("delete data");
		deleteData.setIcon(new ImageIcon(EspMain.class.getResource("/espTest1/delete_row-32.png")));
		GridBagConstraints gbc_deleteData = new GridBagConstraints();
		gbc_deleteData.insets = new Insets(0, 0, 0, 5);
		gbc_deleteData.gridx = 20;
		gbc_deleteData.gridy = 0;
		toolPanel.add(deleteData, gbc_deleteData);
		
		w = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {y++; z++;
			time.setText("  >>"+z+" secs");
			EspMain.this.revalidate();
			if(y==60){
				btnRefresh.setEnabled(false);
				ref=true;
				request="MqueryX MqueryX MqueryX MqueryX MqueryX MqueryX";
				if(servers.size()>2){
					String resp = queryServer("refresh", "Just a moment please. Refreshing...");
					btnRefresh.setEnabled(true);
					if(on100)if(bigData.size()>0) {
						graphPlotter(bigData.size()/2);
						try {
							if(!daily100.exists())daily100.createNewFile();
							files.savetoFile(prevBigData, daily100);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					if(on75)if(smallData.size()>0){
						graphPlotter(smallData.size()/2);
						try {
							if(!daily75.exists())daily75.createNewFile();
							files.savetoFile(prevSmallData, daily75);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}else{
					btnRefresh.setEnabled(true);
				}

				if(!save.exists()){try {save.createNewFile();} catch (IOException e) {e.printStackTrace();}}
				try {
					PrintStream saveFile = new PrintStream(save);
					saveFile.println(realTimeData);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				y=0;
			}
			}
		});
		
		w.start();
			
	}
	int y=0,z=0;
	boolean loadServers(){
		try {
		ResultSet sett =derby.accessDerby("AddressTable");
		ResultSetMetaData meta = sett.getMetaData();
		while(sett.next()){
			for (int x = meta.getColumnCount(); x>0; x--){
				Object obj = sett.getObject(x);
				servers.add(obj);
			}
		}
		//load default server
		if(servers.size()>3){
		ip = (String) servers.get(3);
		System.out.println("list: "+servers);
		}
		
		derby.closeDerby();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private String response = null;
	private String processed=null;
	String queryServer(final String who, final String message){
		
		prog = new ProgressDisplay(null, "Please Wait");
		prog.setMaximum(3);
		prog.setVisible(true);
		prog.setValue(1);
		prog.setMessage(message);
		t = new Timer(3000, new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		esp = new EspClient(ip, request);
		synchronized(esp){
			try{
			response = esp.connect();
			}catch(Exception c){
				System.out.println(c.getMessage());
				prog.setMessage("Connection issues: "+c.getMessage());
				prog.setValue(3);
				prog.dispose();
				t.stop();
				queryServer(who, "Connection issues: "+c.getMessage());
				}
			
			System.out.println("response:"+response);
			if(response == null || response.equals("null")){
				prog.setMessage("Connected... now acquiring data");
				prog.setValue(3);
				prog.dispose();
				t.stop();
				queryServer(who, "Connected... now acquiring data");
			}else{
				
			if (!who.equals("Calibration"))processResponse(who);
			
			if(who.equals("refresh")){
				espResponse.setBackground(Color.BLACK);
				espResponse.setForeground(new Color(142, 202, 254));
				int bleu = processStorageLevel("blue");
				if(bleu == 0){
				blueBar.setValue(2);
				espResponse.setForeground(new Color(152, 222, 254));
				espResponse.append("Check Blue tank sensor/connection \n");
				}else{
				blueBar.setValue(bleu);
				espResponse.append("Blue Tank has "+bleu+" Litres \n");
				}
				
				int noir = processStorageLevel("gray");
				if(noir == 0){
				grayBar.setValue(2);
				espResponse.setForeground(new Color(252, 252, 154));
				espResponse.append("Check Gray tank sensor/connection \n");
				}else{
				grayBar.setValue(noir);
				espResponse.append("Gray Tank has "+noir+" Litres \n");
				}
				
				String g100 = genFuelLevel("100", gen75);
				level100.setText(g100);
				espResponse.append("100kVA Tank unit "+gen100+" Litres \n" +
						"100 low at gen = high at server work in reverse [120-250 370-450]\n");
				
				String g75 = genFuelLevel("75", gen100);   //doing this because there is a switch of gauge
				level75.setText(g75);
				espResponse.append("75kVA Tank unit "+gen75+" Litres \n" +
						"75 low at gen75 = high at server too [375-470 	546-590]\n");
				
				espResponse.append("Big Generator is "+stateOf100+" and was last refuelled at "+fillTime100+"\n");
				status100.setText(processState("100"));
				espResponse.append("Small Generator is "+stateOf75+" and was last refuelled at "+fillTime75+"\n");
				status75.setText(processState("75"));
				espResponse.append("\n");
				prog.setValue(3);
				prog.dispose();
				t.stop();
			}else{
				espResponse.append(" Raw="+response);
				espResponse.append("\n");
				prog.setValue(3);
				prog.dispose();
				t.stop();
				System.out.println("For other commands, filling is:"+filling+" ref is:"+ref);
				if(ref && filling==false)queryServer(who, "Connected... now acquiring data");
				if(filling == true)queryServer(who, "Connected... now acquiring data");
				calibdone = true;
				processed = "done";
			}
			}
		}
			}
		});
		t.start();
		
		return processed;
	}
	

	protected String processState(String who) {
		String[] st;
		try{
		if(who.equals("100")){
			st = stateOf100.split(":");
			if(st[0].contains("100")){
				on100 = true;
				return st[0].substring(3);
			}else{
				on100 = false;
				return "OFF";
			}
		}else{
			st = stateOf75.split(":");
			if(st[0].contains("75")){
				on75 = true;
				return st[0].substring(2);
			}else{
				on75 = false;
				return "OFF";
			}
		}
		}catch(Exception a){
			System.out.println("No status:"+who);
			return a.getMessage();}
	}
	
	//0]7]506]293] ]75ON:20.10.2016_12:08:02] ] ]00.00.2000_00:00:00
	File save = new File("overnight.txt");
	ProcessData data;
	int blueLevel = 0, grayLevel = 0;
	int gen100 = 0, gen75 = 0;
	String stateOf100, stateOf75;
	String fillTime100, fillTime75;
	String serverTime;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	protected boolean processResponse(String who) {
		Date today = new Date();
		String time = getTimeOnly.format(today);
		
		switch(who){
		case "refresh":
			try{
				String[] rep = response.split("]");
				blueLevel = Integer.parseInt(rep[0]);
				grayLevel = Integer.parseInt(rep[1]);
				
				gen75 = Integer.parseInt(rep[2]);
				smallData.add(gen75);
				smallData.add(time);
				prevSmallData.add(gen75);
				prevSmallData.add(time);
				
				gen100 = Integer.parseInt(rep[3]);
				bigData.add(gen100);
				bigData.add(time);
				prevBigData.add(gen100);
				prevBigData.add(time);
				
				stateOf100 = rep[4];
				stateOf75 = rep[5];
				fillTime100 = rep[6];
				fillTime100 = rep[7];
				serverTime = rep[8];
				ref=false;
				
				realTimeData.add(blueLevel);
				realTimeData.add(grayLevel);
				realTimeData.add(gen100);
				realTimeData.add(gen75);
				
				if(saveRawData()){
					espResponse.append("Raw Data Saved");
					espResponse.append("\n");
				}
				}catch(Exception c){
					filling = false;
					return false;
					}
			return true;
			
		case "100kva":
			System.out.println(refill100.getActionCommand()+" - response received:"+response);
			switch(refill100.getActionCommand()){
			case("START100"):
			if( response.contains("100kVA")||response.contains("]")||response.contains("75kVA")){	//means message delivered and processed anyway
				System.out.println("ok start equal");
				response = "START 100kVA";
				resetButton();
				filling = false;
			}else{
				System.out.println("Appropriate response unavailable yet for start");
				filling = true;
			}
			break;
			
			case ("STOP100"):
			if( response.contains("100kVA")||response.contains("]")||response.contains("75kVA")){	
				response = "STOPPED 100kVA";
				resetButton();
				System.out.println("ok stop equal");
				filling = false;
			}else{
				System.out.println("Appropriate response unavailable yet for stop");
				filling = true;
			}
				break;
				
			default:
				break;
			}
			return true;
			
		case "75kva":
			System.out.println(refill75.getActionCommand()+" - response received:"+response);
			switch(refill75.getActionCommand()){
			case("START75"):
			if( response.contains("100kVA")||response.contains("]")||response.contains("75kVA")){
				System.out.println("ok start equal");
				response = "START 75kVA";
				resetButton();
				filling = false;
			}else{
				System.out.println("Appropriate response unavailable yet for start");
				filling = true;
			}
			break;
			
			case ("STOP75"):
			if(response.contains("100kVA")||response.contains("]")||response.contains("75kVA")){
				response = "STOPPED 75kVA";				//RESPONSE override due to arduino-esp lag
				resetButton();
				System.out.println("ok stop equal 75");
				filling = false;
			}else{
				System.out.println("Appropriate response unavailable yet for stop 75");
				filling = true;
			}
			break;
			default:
				break;
			}
			return true;
			
		default:
			return false;
		}
		
	}
	
	boolean saveRawData(){
		try{
			data = derby.inicialRaw();
			//:"+data.getID());
			}catch(Exception c){
				derby.enterRawData(blueLevel, grayLevel, gen75, gen100, stateOf100, stateOf75, fillTime100, fillTime75, 0, 0, serverTime);
			}
			if(data != null){
				System.out.println("Last Computer Time:"+data.getComputerTime());
				System.out.println("Last Thing Time:"+data.getThingTime());
			}
			
			if(data.getID()==0){				//fresh rawDataTable
				derby.enterRawData(blueLevel, grayLevel, gen75, gen100, stateOf100, stateOf75, fillTime100, fillTime75, 0, 0, serverTime);
			}else{								
				//check date, if same day, extract and save fuel rate change in tanks, then update info
				Date lastDate = null, newDate=null;
				try{
				
				lastDate = getDateOnly.parse(data.getComputerTime());
				String rt = getDateOnly.format(new Date());
				newDate = getDateOnly.parse(rt);
				}catch(Exception a){
					a.printStackTrace();
					return false;
				}
				if(newDate.after(lastDate)){			//insert data
					derby.enterRawData(blueLevel, grayLevel, gen75, gen100, stateOf100, stateOf75, fillTime100, fillTime75, 0, 0, serverTime);
				}else if(newDate.equals(lastDate)){		//update data
					String state100 = compare100LastState(stateOf100, data.getBigOffOn());
					String state75 = compare75LastState(stateOf75, data.getSmallOffOn());
					derby.updateRawData(blueLevel, grayLevel, gen75, gen100, state100, state75, fillTime100, fillTime75, 0, 0, serverTime, data.getID());
				}else{
					System.out.println("Is it the future?");
					System.out.println("New day difference:"+(lastDate.getTime()-newDate.getTime()));
					return false;
				}
				//if yesterday, insert new data the new data
			}
			
			return true;
	}

	
	private String compare75LastState(String stateOf75, String lastEntry) {
		String st75="";
		if(!rec75.trim().equals(stateOf75.trim())&& !rec100.equals("d")){
			st75 = lastEntry+">"+stateOf75;
		}else{
			st75 = lastEntry;
		}
		rec75 = stateOf75;
		return st75;
	}


	private String compare100LastState(String stateOf100, String lastEntry) {
		String st100 = "";
		
		if(!rec100.trim().equals(stateOf100.trim()) && !rec100.equals("d")){
			st100 = lastEntry+">"+stateOf100;
		}else{
			st100 = lastEntry;
		}
		rec100 = stateOf100;
		return st100;
	}


	private void resetButton() {
		switch(response){
		
		case "STOPPED 75kVA":
			refill75.setText("Refill 75kVA");
			refill75.setActionCommand("START75");
			break;
		case "STOPPED 100kVA":
			refill100.setText("Refill 100kVA");
			refill100.setActionCommand("START100");
			break;
		case "START 75kVA":
			refill75.setText("Stop Fill 75");
			refill75.setActionCommand("STOP75");
			break;
		case "START 100kVA":
			refill100.setText("Stop Fill 100");
			refill100.setActionCommand("STOP100");
			break;
		default:
			break;
		}
	}

	protected int processStorageLevel(String tank){
		switch(tank){
		case("blue"):
			double val1 = (double)((240-blueLevel));
			if(val1 == 240)return 0; 			//issues
			val1 = val1/240;
			double g1 = val1*5000;
			int blue = (int)g1;
			return blue;
		case("gray"):
			double val = (double)((180-grayLevel));
			val = val/180;
			double g = val*2070;
			int gray = (int)g;
			return gray;
		case("100"):
			double r = (double) gen100/1024;
			r = r*250;
			int g100 = (int)r;
			return g100;
		case("75"):
			double v75 = (double) 1024 - gen75;
			v75 = v75/1024;
			v75 = v75*220;
			int g75 = (int)v75;
		return g75;
		default:
			break;
		}
		
		return 0;
	}
	
	protected String genFuelLevel(String gen, int value){
		switch(gen){
		
		case "75":
			if(value<=470){
				return "HIGH";
			}else if(value<= 546 && value>470){
				return "MID";
			}else if(value>546){
				return "LOW";
			}else{
				return "CHECK GAUGE";
			}
			
		case "100":
			if(value<=250 && value>10){
				return "HIGH";
			}else if(value<=370 && value>250){
				return "MID";
			}else if(value>370 && value<1000){
				return "LOW";
			}else{
				return "CHECK GAUGE";
			}
			default:
				break;
		}
		return "NO VALUE";
	}
	
	int graphWidth;
	private void graphPlotter(int noOfPlots) {
		if(Graph != null){Graph.setVisible(false);
		textPane.remove(Graph);
		}
		
		if(espResponse != null){espResponse.setVisible(false);
		textPane.remove(espResponse);
		}
		
		Graph = new GraphLines();
		if(noOfPlots<20){
			graphWidth = 1000;
		}else{
			graphWidth = 1000+(noOfPlots*5);
		}
		
		Graph.setPreferredSize(new Dimension(graphWidth, 400));
		textPane.setViewportView(Graph);
		textPane.revalidate();
		mm = new Timer(100, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(on100)Graph.update(bigData, graphWidth);
				if(on75)Graph.update(smallData, graphWidth);
			}
        });
		mm.start();
	}
	

	public static void main(String[] args) {
		EspMain main = new EspMain();
		main.setVisible(true);
		if(main.loadServers()){
			//main.queryServer("refresh", "Welcome. Establishing Connection to your THING");
		}
		
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//EspClient line = new EspClient();
	}
}


/*75 low at gen75 = high at server too
375-470 	546-590


100 low at gen = high at server work in reverse

120-250         370-450*/