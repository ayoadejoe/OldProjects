package espTest1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import FumsifolEsp.GraphLines;
import FumsifolEsp.GraphPanel;

public class EspMainUpdate extends JFrame {
	private JPanel panel = new JPanel();
	private JScrollPane pane = new JScrollPane();
	private JScrollPane textPane = new JScrollPane();
	private DataTable statusTable;
	private EspClient esp;
	private EspDerby derby = new EspDerby();
	private JMenu mnServer = new JMenu("Server");
	private JMenuBar menuBar = new JMenuBar();
	private ArrayList servers = new ArrayList();
	private String request = "MqueryX MqueryX MqueryX MqueryX MqueryX MqueryX",
			ip = "";
	private JTextArea espResponse = new JTextArea();
	private JButton btnRefresh = new JButton("Refresh");
	private Timer t;
	private Timer w;
	private ProgressDisplay progressBar = new ProgressDisplay(null, "Initializing");
	private boolean filling = false;
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
	private boolean ref = true, fill75 = false, fill100 = false,
			calibdone = false, relaunch = true;
	private JButton calib100 = new JButton("Calibrate 100kVA");
	private JButton calib75 = new JButton("Calibrate 75kVA");
	private SimpleDateFormat generalFormat = new SimpleDateFormat(
			"dd-MM-yyyy HH:mm:ss");
	private SimpleDateFormat getDateOnly = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat getTimeOnly = new SimpleDateFormat("HH:mm");
	private JPanel View = new JPanel();
	private String rec100 = "d", rec75 = "d";
	private JRadioButton rdbtnRawDataEntry = new JRadioButton("Raw Data Entry");
	private JRadioButton rdbtnProcessedData = new JRadioButton("Processed Data");
	private JRadioButton fuelTrend = new JRadioButton("Fuel Trend");
	private JScrollPane tabPanel = new JScrollPane();
	private JPanel ControlView = new JPanel();
	private ArrayList<Integer> realTimeData = new ArrayList<Integer>();
	private Timer mm;
	private GraphLines Graph = null;
	private GraphPanel dataGraph = null;
	private List<Object> bigData = new ArrayList();
	private List<Object> smallData = new ArrayList();
	private List<Object> prevBigData = new ArrayList();
	private List<Object> prevSmallData = new ArrayList();
	private Vector graphData = new Vector();
	private boolean on100 = false;
	private boolean on75 = false;
	private File daily100 = new File("100kVA Fuel Log");
	private File daily75 = new File("75kVA Fuel Log");
	private FileSerializer files = new FileSerializer();
	private CheckDataContent check = new CheckDataContent();
	private JLabel deleteData = new JLabel("");
	private JLabel prevData = new JLabel("");
	private JLabel nextData = new JLabel("");
	private JLabel lblCharts = new JLabel("Charts {fx}");
	private int count = 0;
	private JLabel chart75 = new JLabel("[75]    ");
	private JLabel chart100 = new JLabel("[100]     ");
	private GeneratorAngel trends;
	private JRadioButton g75View = new JRadioButton("75kVA Gen");
	private GeneratorAngel control;
	private ChartExpress charts;
	private JRadioButton g100View = new JRadioButton("100kVA Gen");
	private JLabel blueChart = new JLabel("[Blue]      ");
	private JLabel grayChart = new JLabel("[Gray]      ");
	
	public EspMainUpdate() {

		try {

			prevBigData = files.loadFromFile(daily100);
			if (!check.todayEntry(prevBigData)) {
				prevBigData.add(new Date());
				files.savetoFile(prevBigData, daily100);
			} else {
			}

			prevSmallData = files.loadFromFile(daily75);
			if (!check.todayEntry(prevSmallData)) {
				prevSmallData.add(new Date());
				files.savetoFile(prevSmallData, daily75);
			} else {
				// .out.println("Todays 75 date already added");
			}
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				EspMainUpdate.class.getResource("/espTest1/4.png")));
		setSize(833, 502);
		pane.setPreferredSize(new Dimension(800, 500));
		panel.setPreferredSize(new Dimension(800, 400));
		pane.setViewportView(panel);
		getContentPane().add(pane, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel tankPanel = new JPanel();
		tankPanel.setBackground(new Color(102, 102, 204));
		tankPanel.setBorder(new CompoundBorder(new EtchedBorder(
				EtchedBorder.RAISED, null, null), new BevelBorder(
				BevelBorder.RAISED, null, null, null, null)));
		tankPanel.setPreferredSize(new Dimension(300, 1000));
		panel.add(tankPanel, BorderLayout.WEST);
		GridBagLayout gbl_tankPanel = new GridBagLayout();
		gbl_tankPanel.columnWidths = new int[] { 40, 100, 29, 100, 0 };
		gbl_tankPanel.rowHeights = new int[] { 25, 61, 22, 200, 14, 71, 0, 0,
				0, 0, 65, 0, 0, 0, 0, 45, 0, 0, 0, 0 };
		gbl_tankPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_tankPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		tankPanel.setLayout(gbl_tankPanel);

		JLabel lblCityEngineeringDept = new JLabel("CITY ENGINEERING DEPT.");
		lblCityEngineeringDept.setFont(new Font("Times New Roman", Font.BOLD,
				17));
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
		Control.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		Control.setPreferredSize(new Dimension(300, 400));
		ControlView.add(Control, BorderLayout.WEST);
		Control.setLayout(new GridLayout(0, 2, 0, 0));

		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRefresh.setEnabled(false);
				ref = true;
				request = "MqueryX MqueryX MqueryX MqueryX MqueryX MqueryX";
				if (servers.size() > 2) {
					String resp = queryServer("refresh",
							"Just a moment please. Connecting to your THING...");
					btnRefresh.setEnabled(true);
					if (on100)
						if (bigData.size() > 0) {
							try {
								if (!daily100.exists())
									daily100.createNewFile();
								files.savetoFile(prevBigData, daily100);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

					if (on75)
						if (smallData.size() > 0) {
							try {
								if (!daily75.exists())
									daily75.createNewFile();
								files.savetoFile(prevSmallData, daily75);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
				} else {
					btnRefresh.setEnabled(true);
				}
				if (!save.exists()) {
					try {
						save.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
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
				request = "SHUTDNX SHUTDNX SHUTDNX SHUTDNX SHUTDNX SHUTDNX";
				if (servers.size() > 2) {
					String resp = queryServer("stopArduino",
							"Initiating Controller Shutdown. Connecting to your THING...");
					stopArduino.setEnabled(true);
				} else {
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
				if (servers.size() > 2) {

					if (refill100.getActionCommand().equals("START100")) {
						request = "STT100X STT100X STT100X STT100X STT100X STT100X";
						String resp = queryServer("100kva",
								"Refuelling of 100kVA initiated... connecting");
					} else if (refill100.getActionCommand().equals("STOP100")) {
						request = "STP100X STP100X STP100X STP100X STP100X STP100X";
						String resp = queryServer("100kva",
								"100kVA Fuelling will be cancelled... connecting");
					}

					refill100.setEnabled(true);
				} else {
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
				if (servers.size() > 2) {

					if (refill75.getActionCommand().equals("START75")) {
						request = "STT075X STT075X STT075X STT075X STT075X STT075X";
						String resp = queryServer("75kva",
								"Refuelling of 75kVA initiated... connecting");

					} else if (refill75.getActionCommand().equals("STOP75")) {
						request = "STP075X STP075X STP075X STP075X STP075X STP075X";
						String resp = queryServer("75kva",
								"75kVA Fuelling will be cancelled... connecting");
					}

					refill75.setEnabled(true);
				} else {
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
					String maxi;
					String mini;

					public void StringBack(int max, int min, String resp) {
						calibdone = false;
						if (max < 10 && max > -1)
							maxi = "00000" + max + "X";
						if (max < 100 && max > 9)
							maxi = "0000" + max + "X";
						if (max < 1000 && max > 99)
							maxi = "000" + max + "X";
						if (max < 10000 && max > 999)
							maxi = "00" + max + "X";
						if (max < 100000 && max > 9999)
							maxi = "0" + max + "X";

						if (min < 10 && min > -1)
							mini = "00000" + min + "X";
						if (min < 100 && min > 9)
							mini = "0000" + min + "X";
						if (min < 1000 && min > 99)
							mini = "000" + min + "X";
						if (min < 10000 && min > 999)
							mini = "00" + min + "X";
						if (min < 100000 && min > 9999)
							mini = "0" + min + "X";
						if (min <= 50000 || min >= 0 || max <= 50000
								|| max >= 0) {
							request = "CAL100X CAL100X CAL100X CAL100X " + maxi
									+ " " + mini;
							queryServer("Calibration",
									"Calibration of 100kVA... connecting");
							// .out.println("Calibration");
							calibrate.dispose();
						} else {
							JOptionPane.showMessageDialog(EspMainUpdate.this,
									"Please enter values between 0 - 50000");
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
					String maxi;
					String mini;

					public void StringBack(int max, int min, String resp) {
						calibdone = false;
						if (max < 10 && max > -1)
							maxi = "00000" + max + "X";
						if (max < 100 && max > 9)
							maxi = "0000" + max + "X";
						if (max < 1000 && max > 99)
							maxi = "000" + max + "X";
						if (max < 10000 && max > 999)
							maxi = "00" + max + "X";
						if (max < 100000 && max > 9999)
							maxi = "0" + max + "X";

						if (min < 10 && min > -1)
							mini = "00000" + min + "X";
						if (min < 100 && min > 9)
							mini = "0000" + min + "X";
						if (min < 1000 && min > 99)
							mini = "000" + min + "X";
						if (min < 10000 && min > 999)
							mini = "00" + min + "X";
						if (min < 100000 && min > 9999)
							mini = "0" + min + "X";
						if (min <= 50000 || min >= 0 || max <= 50000
								|| max >= 0) {
							request = "CAL075X CAL075X CAL075X CAL075X " + maxi
									+ " " + mini;
							// .out.println(" 75 Calibration request:"+request);
							queryServer("Calibration",
									"Calibration of 75kVA... connecting");
							// .out.println("Calibration");
							calibrate.dispose();

						} else {
							JOptionPane.showMessageDialog(EspMainUpdate.this,
									"Please enter values between 0 - 50000");
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
		tankView.setBorder(new CompoundBorder(
				new LineBorder(new Color(0, 0, 0)), new BevelBorder(
						BevelBorder.RAISED, null, null, null, null)));
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
		ViewGen.setBorder(new CompoundBorder(
				new LineBorder(new Color(0, 0, 0)), new BevelBorder(
						BevelBorder.RAISED, null, null, null, null)));
		Control.add(ViewGen);
		ViewGen.setLayout(new GridLayout(3, 0, 0, 0));

		JLabel lblViewGenerators = new JLabel("View Generators");
		lblViewGenerators.setHorizontalAlignment(SwingConstants.CENTER);
		ViewGen.add(lblViewGenerators);

		g75View.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(g75View.isSelected()){
					if(textPane != null)ControlView.remove(textPane);
					ControlView.revalidate();
					if(control != null){
						control.setVisible(false);
						ControlView.remove(control);
						ControlView.revalidate();
					}
					
					control =   new GeneratorAngel("g75", derby);
					ControlView.add(control, BorderLayout.CENTER);
					control.setVisible(true);
					ControlView.revalidate();
				}
			}
		});
		buttonGroup_2.add(g75View);
		ViewGen.add(g75View);

		g100View.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(g100View.isSelected()){
					if(textPane != null)ControlView.remove(textPane);
					ControlView.revalidate();
					if(control != null){
						control.setVisible(false);
						ControlView.remove(control);
						ControlView.revalidate();
					}
					
					control =   new GeneratorAngel("g100", derby);
					ControlView.add(control, BorderLayout.CENTER);
					control.setVisible(true);
					ControlView.revalidate();
				}
			}
		});
		buttonGroup_2.add(g100View);
		ViewGen.add(g100View);

		JPanel viewOthers = new JPanel();
		viewOthers.setBackground(SystemColor.controlHighlight);
		viewOthers.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0,
				0)),
				new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		Control.add(viewOthers);
		viewOthers.setLayout(new GridLayout(3, 0, 0, 0));

		JLabel lblVi = new JLabel("View Status");
		lblVi.setHorizontalAlignment(SwingConstants.CENTER);
		viewOthers.add(lblVi);

		buttonGroup_1.add(fuelTrend);
		viewOthers.add(fuelTrend);

		fuelTrend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fuelTrend.isSelected()) {
					// trends.setVisible(true);
				} else {
					// trends.setVisible(false);
				}
			}
		});

		JRadioButton rdbtnServerProbe = new JRadioButton("Server Probe");
		buttonGroup_1.add(rdbtnServerProbe);
		viewOthers.add(rdbtnServerProbe);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(
				new LineBorder(new Color(0, 0, 0)), new BevelBorder(
						BevelBorder.RAISED, null, null, null, null)));
		panel_1.setBackground(SystemColor.controlHighlight);
		Control.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 0, 0, 0));

		JLabel viewTables = new JLabel("View Tables");
		viewTables.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(viewTables);
		buttonGroup.add(rdbtnRawDataEntry);

		rdbtnRawDataEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent w) {
				if (rdbtnRawDataEntry.isSelected()) {
					// .out.println("Raw Data Table Loading");
					if (statusTable != null) {
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

		espResponse.setPreferredSize(new Dimension(200, 400));
		textPane.setViewportView(espResponse);
		ControlView.add(textPane, BorderLayout.CENTER);
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);

		setJMenuBar(menuBar);

		menuBar.add(mnServer);

		JMenuItem mntmNewAddress = new JMenuItem("New Address");
		mnServer.add(mntmNewAddress);

		JLabel label_10 = new JLabel("            ||       ");
		menuBar.add(label_10);
		menuBar.add(time);

		JLabel label_9 = new JLabel("          ||          ");
		menuBar.add(label_9);

		JLabel lblOverallView = new JLabel("Overview:");
		menuBar.add(lblOverallView);
		menuBar.add(prevData);
		prevData.setAlignmentX(Component.CENTER_ALIGNMENT);

		prevData.setToolTipText("Previous");
		prevData.setBackground(new Color(255, 204, 255));
		prevData.setIcon(new ImageIcon(EspMainUpdate.class
				.getResource("/espTest1/chevron_left-32.png")));
		menuBar.add(nextData);
		nextData.setAlignmentX(Component.CENTER_ALIGNMENT);

		nextData.addMouseListener(new MouseAdapter() {
			int z = 0, q = 0;

			public void mouseClicked(MouseEvent arg0) {
				switch ("info") {
				case "100kVA Cons.":
					List<Object> dates = new ArrayList<Object>();
					List<Object> prevDatat = prevBigData;
					int s = prevBigData.size();
					for (int y = (s - 1); y > 0; y--) {
						if (prevBigData.get(y).getClass()
								.isInstance(new Date())) {
							dates.add(prevBigData.get(y));
							prevDatat.remove(y);
						}
					}

					int pages = prevDatat.size() / 50; // for 25 readings and 25
														// time lines
					int remain = prevDatat.size() % 50;
					if (z < pages && z > -1) {
						prevDatat = check.next(prevDatat, z);
						// .out.println("");
						// .out.println("page:"+z+" > "+prevDatat);
					} else if (z == (pages)) {
						prevDatat = check.next(prevDatat, -1);
						// .out.println(" Last page:"+z+" > "+prevDatat);
						z = -1;
						// .out.println(dates);
						// .out.println(prevBigData);
						// .out.println("no of pages:"+pages+" remaining:"+remain);
					}
					z++;
					break;
				case "75 kVA Cons.":
					List<Object> dates2 = new ArrayList<Object>();
					List<Object> prevDatat2 = prevSmallData;
					int g = prevSmallData.size();
					for (int y = (g - 1); y > 0; y--) {
						if (prevSmallData.get(y).getClass()
								.isInstance(new Date())) {
							dates2.add(prevSmallData.get(y));
							prevDatat2.remove(y);
						}
					}

					int pages2 = prevDatat2.size() / 50; // for 25 readings and
															// 25 time lines
					int remain2 = prevDatat2.size() % 50;
					if (q < pages2 && q > -1) {
						prevDatat2 = check.next(prevDatat2, q);
						// .out.println("");
						// .out.println("page:"+q+" > "+prevDatat2);
					} else if (q == (pages2)) {
						prevDatat2 = check.next(prevDatat2, -1);
						// .out.println(" Last page:"+q+" > "+prevDatat2);
						q = -1;
						// .out.println(dates2);
						// .out.println(prevSmallData);
						// .out.println("no of pages:"+pages2+" remaining:"+remain2);
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
		nextData.setIcon(new ImageIcon(EspMainUpdate.class
				.getResource("/espTest1/chevron_right-32.png")));
		menuBar.add(deleteData);
		deleteData.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteData.setBackground(new Color(153, 102, 102));

		deleteData.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				DeleteTable delete = null;
				switch ("info") {
				case "100kVA Cons.":
					delete = new DeleteTable(prevBigData);
					delete.setvectorInterface(new VectorInterface() {
						public void vectorBack(Vector value1, Vector value2) {
							prevBigData = check.dataToDelete(prevBigData,
									value1);
							try {
								files.savetoFile(prevBigData, daily100);
							} catch (IOException e) {
								e.printStackTrace();
							}
							// .out.println("Edited big value received"+prevBigData);
						}
					});
					break;
				case "75 kVA Cons.":
					delete = new DeleteTable(prevSmallData);
					delete.setvectorInterface(new VectorInterface() {
						public void vectorBack(Vector value1, Vector value2) {
							prevSmallData = check.dataToDelete(prevSmallData,
									value1);
							try {
								files.savetoFile(prevSmallData, daily75);
							} catch (IOException e) {
								e.printStackTrace();
							}
							// .out.println("Edited small value received"+prevSmallData);
						}
					});
					break;

				default:
					break;
				}

			}
		});
		deleteData.setToolTipText("delete data");
		deleteData.setIcon(new ImageIcon(EspMainUpdate.class
				.getResource("/espTest1/delete_row-32.png")));

		JLabel label_8 = new JLabel("         |");
		menuBar.add(label_8);

		JLabel label_4 = new JLabel("|        ");
		menuBar.add(label_4);

		chart75.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				trends = new GeneratorAngel("g75", derby);
				charts = new ChartExpress("75kVA Fuel Drop Chart", trends);
				charts.setVisible(true);
			}
		});

		chart75.setToolTipText("click to check analysis for 75kVA Gen");
		menuBar.add(chart75);
		chart75.setBackground(new Color(173, 255, 47));

		chart100.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				trends = new GeneratorAngel("g100", derby);
				charts = new ChartExpress("100kVA Fuel Drop Chart", trends);
				charts.setVisible(true);
			}
		});

		chart100.setToolTipText("click to check analysis for 100kVA Gen");
		menuBar.add(chart100);
		chart100.setBackground(new Color(138, 43, 226));

		
		blueChart.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							TankAngel getTanks = new TankAngel(derby, "blue");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		blueChart.setToolTipText("click to check analysis for Blue Gen");
		menuBar.add(blueChart);
		blueChart.setBackground(new Color(100, 149, 237));
		grayChart.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							TankAngel getTanks = new TankAngel(derby, "gray");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		
		grayChart.setToolTipText("click to check analysis for Gray Tank");
		menuBar.add(grayChart);
		grayChart.setBackground(Color.GRAY);

		JLabel label_7 = new JLabel("||  ");
		menuBar.add(label_7);

		lblCharts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// trends.setVisible(true);
			}
		});
		menuBar.add(lblCharts);
		
		menuBar.add(label_6);
		lblEditGdata.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GenDataEditor frame = new GenDataEditor();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		menuBar.add(lblEditGdata);
		
		menuBar.add(lblConnectionStatus);
		progressBar.setBorderPainted(false);
		progressBar.setBackground(new Color(0, 51, 0));
		progressBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		menuBar.add(progressBar);
		
		menuBar.add(label_5);

		w = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				y++;
				z++;
				time.setText("  >>" + z + " secs");
				EspMainUpdate.this.revalidate();
				if (y == 60) {
					btnRefresh.setEnabled(false);
					ref = true;
					request = "MqueryX MqueryX MqueryX MqueryX MqueryX MqueryX";
					if (servers.size() > 2) {
						String resp = queryServer("refresh",
								"Just a moment please. Refreshing...");
						btnRefresh.setEnabled(true);
						if (on100)
							if (bigData.size() > 0) {
								try {
									if (!daily100.exists())
										daily100.createNewFile();
									files.savetoFile(prevBigData, daily100);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

						if (on75)
							if (smallData.size() > 0) {
								try {
									if (!daily75.exists())
										daily75.createNewFile();
									files.savetoFile(prevSmallData, daily75);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

					} else {
						btnRefresh.setEnabled(true);
					}

					if (!save.exists()) {
						try {
							save.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					try {
						PrintStream saveFile = new PrintStream(save);
						saveFile.println(realTimeData);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					y = 0;
				}
			}
		});

		w.start();

	}

	int y = 0, z = 0;

	boolean loadServers() {
		try {
			ResultSet sett = derby.accessDerby("AddressTable");
			ResultSetMetaData meta = sett.getMetaData();
			while (sett.next()) {
				for (int x = meta.getColumnCount(); x > 0; x--) {
					Object obj = sett.getObject(x);
					servers.add(obj);
				}
			}
			// load default server
			if (servers.size() > 3) {
				ip = (String) servers.get(3);
				// .out.println("list: "+servers);
			}

			derby.closeDerby();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private String response = null;
	private String processed = null;

	String queryServer(final String who, final String message) {

		progressBar.setMaximum(3);
		progressBar.setVisible(true);
		progressBar.setValue(1);
		progressBar.setString(message);
		t = new Timer(3000, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				esp = new EspClient(ip, request);
				synchronized (esp) {
					try {
						response = esp.connect();
						//.println(response);
					} catch (Exception c) {
						// .out.println(c.getMessage());
						progressBar.setString(c.getMessage());
						progressBar.setValue(3);
						t.stop();
						queryServer(who, c.getMessage());
					}

					// .out.println("response:"+response);
					if (response == null || response.equals("null")) {
						progressBar.setString("Connected... now acquiring data");
						progressBar.setValue(3);
						t.stop();
						queryServer(who, "Connected... now acquiring data");
					} else {

						if (!who.equals("Calibration"))
							processResponse(who);

						if (who.equals("refresh")) {
							espResponse.setBackground(Color.BLACK);
							espResponse.setForeground(new Color(142, 202, 254));
							int bleu = processStorageLevel("blue");
							if (bleu == 0) {
								blueBar.setValue(2);
								espResponse.setForeground(new Color(152, 222,
										254));
								espResponse
										.append("Check Blue tank sensor/connection \n");
							} else {
								blueBar.setValue(bleu);
								espResponse.append("Blue Tank has " + bleu
										+ " Litres \n");
							}

							int noir = processStorageLevel("gray");
							if (noir == 0) {
								grayBar.setValue(2);
								espResponse.setForeground(new Color(252, 252,154));
								espResponse.append("Check Gray tank sensor/connection \n");
							} else {
								grayBar.setValue(noir);
								espResponse.append("Gray Tank has "+noir+ " Litres \n");
							}

							String g100 = genFuelLevel("100", gen75);
							level100.setText(g100);
							espResponse
									.append("100kVA Tank unit "
											+ gen100
											+ " Litres \n"
											+ "100 low at gen = high at server work in reverse [120-250 370-450]\n");

							String g75 = genFuelLevel("75", gen100); 
							level75.setText(g75);
							espResponse
									.append("75kVA Tank unit "
											+ gen75
											+ " Litres \n"
											+ "75 low at gen75 = high at server too [375-470 	546-590]\n");

							espResponse.append("Big Generator is " + stateOf100
									+ " and was last refuelled at "
									+ fillTime100 + "\n");
							status100.setText(processState("100"));
							espResponse.append("Small Generator is "
									+ stateOf75 + " and was last refuelled at "
									+ fillTime75 + "\n");
							status75.setText(processState("75"));
							espResponse.append("\n");
							progressBar.setValue(3);
							t.stop();
						} else {
							espResponse.append(" Raw=" + response);
							espResponse.append("\n");
							progressBar.setValue(3);
							t.stop();
							// .out.println("For other commands, filling is:"+filling+" ref is:"+ref);
							if (ref && filling == false)
								queryServer(who,
										"Connected... now acquiring data");
							if (filling == true)
								queryServer(who,
										"Connected... now acquiring data");
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
		try {
			if (who.equals("100")) {
				st = stateOf100.split(":");
				if (st[0].contains("100")) {
					on100 = true;
					return st[0].substring(3);
				} else {
					on100 = false;
					return "OFF";
				}
			} else {
				st = stateOf75.split(":");
				if (st[0].contains("75")) {
					on75 = true;
					return st[0].substring(2);
				} else {
					on75 = false;
					return "OFF";
				}
			}
		} catch (Exception a) {
			// .out.println("No status:"+who);
			return a.getMessage();
		}
	}

	// 0]7]506]293] ]75ON:20.10.2016_12:08:02] ] ]00.00.2000_00:00:00
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
	private final JLabel lblConnectionStatus = new JLabel("              Connection Status:");
	private final JLabel label_5 = new JLabel("      ");

	protected boolean processResponse(String who) {
		Date today = new Date();
		String time = getTimeOnly.format(today);
		progressBar.setString("Processing received Data");
		System.out.println(response);
		switch (who) {
		case "refresh":
			try {
				String[] rep = response.split("]");
				blueLevel = Integer.parseInt(rep[0]);
				grayLevel = Integer.parseInt(rep[1]);

				gen100 = Integer.parseInt(rep[2]);
				gen75 = Integer.parseInt(rep[3]);

				stateOf100 = rep[4];
				stateOf75 = rep[5];
				fillTime100 = rep[6];
				fillTime100 = rep[7];
				ref = false;

				realTimeData.add(blueLevel);
				realTimeData.add(grayLevel);
				realTimeData.add(gen100);
				realTimeData.add(gen75);

				// when a generator is On, then activate it's saving and
				// plotting

				if (stateOf75.equals("75ON:")) {
					//.println("75 count:"+count);
					if (count == 2 || count == 0) {
					//.println("adding up time");
					
					smallData.add(gen75);
					smallData.add(time);
					prevSmallData.add(gen75);
					prevSmallData.add(time);
					count = 0;
					}
					count++;
				}
				

				if (stateOf100.equals("100ON:")) {
					//.println("100 count:"+count);
					if (count == 2 || count == 0) {
					//.println("adding up time");
						
					bigData.add(gen100);
					bigData.add(time);
					prevBigData.add(gen100);
					prevBigData.add(time);
					count =0;
					//.println("big:"+bigData);
					}
					count++;
				}
				
				

				//out.println("Big:"+bigData);
				//out.println("Small:"+smallData);
				if (saveRawData()) {
					// .out.println("Raw data saved");
					espResponse.append("Raw Data Saved");
					espResponse.append("\n");
				} else {
					// .out.println("Raw data NOT saved");
				}
			} catch (Exception c) {
				filling = false;
				return false;
			}
			return true;

		case "100kva":
			// .out.println(refill100.getActionCommand()+" - response received:"+response);
			switch (refill100.getActionCommand()) {
			case ("START100"):
				if (response.contains("100kVA") || response.contains("]")
						|| response.contains("75kVA")) { // means message
															// delivered and
															// processed anyway
					// .out.println("ok start equal");
					response = "START 100kVA";
					resetButton();
					filling = false;
				} else {
					// .out.println("Appropriate response unavailable yet for start");
					filling = true;
				}
				break;

			case ("STOP100"):
				if (response.contains("100kVA") || response.contains("]")
						|| response.contains("75kVA")) {
					response = "STOPPED 100kVA";
					resetButton();
					// .out.println("ok stop equal");
					filling = false;
				} else {
					// .out.println("Appropriate response unavailable yet for stop");
					filling = true;
				}
				break;

			default:
				break;
			}
			return true;

		case "75kva":
			// .out.println(refill75.getActionCommand()+" - response received:"+response);
			switch (refill75.getActionCommand()) {
			case ("START75"):
				if (response.contains("100kVA") || response.contains("]")
						|| response.contains("75kVA")) {
					// .out.println("ok start equal");
					response = "START 75kVA";
					resetButton();
					filling = false;
				} else {
					// .out.println("Appropriate response unavailable yet for start");
					filling = true;
				}
				break;

			case ("STOP75"):
				if (response.contains("100kVA") || response.contains("]")
						|| response.contains("75kVA")) {
					response = "STOPPED 75kVA"; // RESPONSE override due to
												// arduino-esp lag
					resetButton();
					// .out.println("ok stop equal 75");
					filling = false;
				} else {
					// .out.println("Appropriate response unavailable yet for stop 75");
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

	int bl = 0;
	int gl = 0;
	private final JLabel label_6 = new JLabel("   ");
	private final JLabel lblEditGdata = new JLabel("Edit GData");
	boolean saveRawData() {
		try {
			data = derby.inicialRaw();
			// :"+data.getID());
		} catch (Exception c) {
			c.printStackTrace();
			derby.enterRawData(blueLevel, grayLevel, bigData, smallData,stateOf100, stateOf75, fillTime100, fillTime75, 0, 0, serverTime);
		}

		if (data != null) {
			// .out.println("Last Computer Time:"+data.getComputerTime());
			// .out.println("Last Thing Time:"+data.getThingTime());
		}

		if (data.getID() == 0) { // fresh rawDataTable
			derby.enterRawData(blueLevel, grayLevel, bigData, smallData,stateOf100, stateOf75, fillTime100, fillTime75, 0, 0,
					serverTime);
		} else {
			// check date, if same day, extract and save fuel rate change in
			// tanks, then update info
			Date lastDate = null, newDate = null;

			try {
				lastDate = getDateOnly.parse(data.getComputerTime());
				String rt = getDateOnly.format(new Date());
				newDate = getDateOnly.parse(rt);
			} catch (Exception a) {
				a.printStackTrace();
				return false;
			}
			if (newDate.after(lastDate)) { // insert data
				//.println("New date after last");
				bigData.clear();
				smallData.clear();
				derby.enterRawData(blueLevel, grayLevel, bigData, smallData,stateOf100, stateOf75, fillTime100, fillTime75, 0, 0,
						serverTime);
				// bigData = derby.get
			} else if (newDate.equals(lastDate)) { // update data
				// compare allows to stop appending second records after the
				// first in case of a relaunch or change in generator state
				String state100 = compare100LastState(stateOf100,
						data.getBigOffOn());
				String state75 = compare75LastState(stateOf75,
						data.getSmallOffOn());

				if (relaunch) { // if it is a relaunch, the old list (since it
								// is same date) is appended
					relaunch = false;
					appendNewToOldList();
				}

				if(blueLevel>280){
					if(bl>0)blueLevel = bl;
					if(bl<=0)blueLevel = 240;		//if it does not see it, there is a likelihood that level is very low
				}
				
				if(grayLevel>180){
					if(gl>0)grayLevel = gl;
					if(gl<=0)grayLevel = 180;		//if it does not see it, there is a likelihood that level is very low
				}
				
				System.out.println("big:"+bigData);
				System.out.println("small:"+smallData);
					derby.updateRawData(blueLevel, grayLevel, bigData,smallData, state100, state75, fillTime100,
							fillTime75, 0, 0, serverTime, data.getID());
					
					bl=blueLevel;
					gl = grayLevel; 		//to hold down sensor catastrophy

				
			} else {
				// .out.println("Is it the future?");
				// .out.println("New day difference:"+(lastDate.getTime()-newDate.getTime()));
				return false;
			}
			// if yesterday, insert new data the new data
		}
		progressBar.setString("Connected");
		return true;
	}

	private void appendNewToOldList() {
		List<Object> smallDoor = data.getSmallLevel();
		//.println("B bigData:" + bigData);

		if (smallDoor.size() > 0) {
			for (int u = 0; u < smallData.size(); u++) {
				smallDoor.add(smallData.get(u));
			}

			smallData = smallDoor;
		}

		List<Object> bigDoor = data.getBigLevel();
		if (bigDoor.size() > 0) {
			for (int u = 0; u < bigData.size(); u++) {
				bigDoor.add(bigData.get(u));
			}

			bigData = bigDoor;
		}
	}

	private String compare75LastState(String stateOf75, String lastEntry) {
		String st75 = "";
		try {
			String[] rep = lastEntry.split(":>");
			int l = rep.length;
			st75 = rep[l - 1];
			rep = st75.split(",");
			st75 = rep[rep.length - 1];
		} catch (Exception c) {
			c.printStackTrace();
		}
		if (!stateOf75.trim().equals(st75 + ":")) { // if generator state
													// changes
			lastEntry += new Date().toLocaleString() + "," + stateOf75 + ">";
		} else {
		}
		return lastEntry;
	}

	private String compare100LastState(String stateOf100, String lastEntry) {
		String st100 = "";
		try {
			String[] rep = lastEntry.split(":>");
			int l = rep.length;
			st100 = rep[l - 1];
			rep = st100.split(",");
			st100 = rep[rep.length - 1];
		} catch (Exception c) {
			c.printStackTrace();
		}

		if (!stateOf100.trim().equals(st100 + ":")) { // if generator state
														// changes
			lastEntry += new Date().toLocaleString() + "," + stateOf100 + ">";
		} else {
		}
		return lastEntry;
	}

	private void resetButton() {
		switch (response) {

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

	protected int processStorageLevel(String tank) {
		switch (tank) {
		case ("blue"):
			double val1 = (double) ((240 - blueLevel));
			if (val1 == 240)
				return 0; // issues
			val1 = val1 / 240;
			double g1 = val1 * 5000;
			int blue = (int) g1;
			return blue;
		case ("gray"):
			double val = (double) ((180 - grayLevel));
			val = val / 180;
			double g = val * 2070;
			int gray = (int) g;
			return gray;
		case ("100"):
			double r = (double) gen100 / 1024;
			r = r * 250;
			int g100 = (int) r;
			return g100;
		case ("75"):
			double v75 = (double) 1024 - gen75;
			v75 = v75 / 1024;
			v75 = v75 * 220;
			int g75 = (int) v75;
			return g75;
		default:
			break;
		}

		return 0;
	}

	protected String genFuelLevel(String gen, int value) {
		switch (gen) {

		case "75":
			if (value <= 470) {
				return "HIGH";
			} else if (value <= 546 && value > 470) {
				return "MID";
			} else if (value > 546) {
				return "LOW";
			} else {
				return "CHECK GAUGE";
			}

		case "100":
			if (value <= 250 && value > 10) {
				return "HIGH";
			} else if (value <= 370 && value > 250) {
				return "MID";
			} else if (value > 370 && value < 1000) {
				return "LOW";
			} else {
				return "CHECK GAUGE";
			}
		default:
			break;
		}
		return "NO VALUE";
	}

	/*
	 * int graphWidth; private void graphPlotter(int noOfPlots) { if(Graph !=
	 * null){Graph.setVisible(false); textPane.remove(Graph); }
	 * 
	 * if(espResponse != null){espResponse.setVisible(false);
	 * textPane.remove(espResponse); }
	 * 
	 * //Graph = new GraphLines(); if(noOfPlots<20){ graphWidth = 1000; }else{
	 * graphWidth = 1000+(noOfPlots*5); }
	 * 
	 * Graph.setPreferredSize(new Dimension(graphWidth, 400));
	 * textPane.setViewportView(Graph); textPane.revalidate(); mm = new
	 * Timer(100, new ActionListener(){ public void actionPerformed(ActionEvent
	 * arg0) { //if(on100)Graph.update(bigData, graphWidth);
	 * //if(on75)Graph.update(smallData, graphWidth); } }); mm.start(); }
	 */

	public static void main(String[] args) {
		EspMainUpdate main = new EspMainUpdate();
		main.setVisible(true);
		if (main.loadServers()) {
			// main.queryServer("refresh",
			// "Welcome. Establishing Connection to your THING");
		}

		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// EspClient line = new EspClient();
	}
}

/*
 * 75 low at gen75 = high at server too 375-470 546-590
 * 
 * 
 * 100 low at gen = high at server work in reverse
 * 
 * 120-250 370-450
 */