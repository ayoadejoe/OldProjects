package servers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class CityServe extends JFrame{
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String USERNAME = null;
	private static String PASSWORD = null;
    private static String DATABASE_URL = null;
    private static String IP = null;
    private static int PORT = 0;
    private static Connection connection = null;
    private static DatabaseCred credentials = new DatabaseCred();
    private static boolean ready = false;
    private JPanel backPanel = new JPanel();
    private GridBagConstraints align = null;
    private final JProgressBar progressBar = new JProgressBar();
    private final Structure rocks;
    private static Timer t;
    private JTextArea updates = new JTextArea("UPDATES");
    private JScrollPane upPane = new JScrollPane();
    private CityServerProcess data;
	public static void main(String[] args) {
		
			try {
				Class.forName(JDBC_DRIVER);
				USERNAME = credentials.getUSERNAME();
				PASSWORD = credentials.getPASSWORD();
				IP = credentials.getIP();
				PORT = credentials.getPORT();
				DATABASE_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+"information_schema"; 
				connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				ready = true;
			} catch (SQLException e) {
				if(e.getMessage().contains("Communications link failure"))JOptionPane.showMessageDialog(null, 
						"Attempt to reach your database was unsucessful. Please start MYSQL Server");
				e.printStackTrace();
				System.exit(0);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "A very important driver is missing. Please contact support.");
				e.printStackTrace();
				System.exit(0);
			}
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					new CityServe();
				}
			});
	}
	
	public CityServe() {
		setTitle("CITY LOCAL SERVER");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CityServe.class.getResource("/servers/city.gif")));
		setSize(650, 500);
		setLocationRelativeTo(getParent());
		setContentPane(new JLabel(new ImageIcon(CityServe.class.getResource("forCt.PNG"))));
		setVisible(true);
		setDefaultCloseOperation(CityServe.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		backPanel.setOpaque(false);
		backPanel.setPreferredSize(new Dimension(650, 500));
		getContentPane().add(backPanel, BorderLayout.CENTER);
		
		backPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.insets = new Insets(200, 0, 0, 0);
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 12;
		progressBar.setBackground(new Color(0, 0, 0));
		progressBar.setForeground(new Color(210, 105, 30));
		progressBar.setOpaque(true);
		backPanel.add(progressBar, gbc_progressBar);
		progressBar.setValue(5);
		if(ready)progressBar.setString("Database Server is running...");
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(500, 30));
		rocks = new Structure(IP, PORT, USERNAME, PASSWORD);
		
		t = new Timer(1000, new ActionListener() {
			int x = 0; boolean check = false;
			public void actionPerformed(ActionEvent arg0) {
				x++;
				switch(x){
				case 1:
					if(!rocks.checkExistDatabase()){
						try{
						rocks.createDatabase();
						progressBar.setValue(10);
						progressBar.setString("Database creation process started...");
						}catch(Exception c){
							if(c.getMessage().contains("already")){
								progressBar.setValue(10);
								progressBar.setString("Database looks good!");
								check = true;
							}
						}
					}
					break;
					
				case 2:
					if(!rocks.checkExistStaffTable()){
					try{
						rocks.createStaffTable();
						progressBar.setValue(20);
						progressBar.setString("Setting up your Staff Boxes");
					}catch(Exception c){
						if(c.getMessage().contains("already")){
							progressBar.setValue(20);
							progressBar.setString("Staff Boxes in operation...");
							check = true;
						}
					}
					}
					break;
					
				case 3:
					if(!rocks.checkExistMemoTable()){
						try{
						rocks.createMemoTable();
						progressBar.setValue(40);
						progressBar.setString("Creating Internal Memo Record Slots");
						}catch(Exception c){
							if(c.getMessage().contains("already")){
								progressBar.setValue(40);
								progressBar.setString("Memos OK");
								check = true;
							}
						}
					}
					break;
					
				case 4:
					if(!rocks.checkExistNotifyTable()){
						try{
						rocks.createNotifyTable();
						progressBar.setValue(50);
						progressBar.setString("Building notification framework");
					}catch(Exception c){
						if(c.getMessage().contains("already")){
							progressBar.setValue(50);
							progressBar.setString("Notification blocks active");
							check = true;
						}
					}
					}
					break;

				case 5:
					if(!rocks.checkExistEngineerTable()){
						try{
						rocks.createEngineerTable();
						progressBar.setValue(60);
						progressBar.setString("Setting up engineer's Log");
					}catch(Exception c){
						if(c.getMessage().contains("already")){
							progressBar.setValue(60);
							progressBar.setString("Engineer logs is ok");
							check = true;
						}
					}
					}
					break;
					
				case 6:
					if(!rocks.checkExistAppraisalTable()){
						try{
						rocks.createAppraisalTable();
						progressBar.setValue(80);
						progressBar.setString("Setting up appraisals");
					}catch(Exception c){
						if(c.getMessage().contains("already")){
							progressBar.setValue(80);
							progressBar.setString("Appraisal set up done");
							check = true;
						}
					}
					}
					break;
					
				case 7:
					if(!rocks.checkExistStandardTable()){
						try{
						rocks.createStandardTable();
						progressBar.setValue(90);
						progressBar.setString("Setting up Standards");
					}catch(Exception c){
						if(c.getMessage().contains("already")){
							progressBar.setValue(90);
							progressBar.setString("Standards created");
							check = true;
						}
					}
					}
					break;
					
				case 8:
						if(!check){
						progressBar.setValue(100);
						progressBar.setString("All Structures Completed. Now let's launch the Server");
						t.stop();
						progressBar.setVisible(false);
						CityServe.this.revalidate();
						makeItRain();
						}else{
							progressBar.setValue(100);
							progressBar.setString("There may be some problems. If Server does not launch, contact support");
							t.stop();
							progressBar.setVisible(false);
							CityServe.this.revalidate();
							makeItRain();
						}
						//CityServe.this.dispose();
					break;
				
				}
			}
		});
		t.start();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				if(data !=null)data.stop();
			}
		});
	}

	protected void makeItRain() {
		backPanel.setLayout(new BorderLayout());
		CityServe.this.setSize(550, 450);
		CityServe.this.revalidate();
		updates.setBackground(new Color(222, 220, 220));
		updates.setForeground(new Color(10, 10, 130));
		updates.setFont(new Font("georgia", Font.BOLD, 16));
		updates.setWrapStyleWord(true);
		upPane.setViewportView(updates);
		backPanel.add(upPane, BorderLayout.CENTER);
		CityServe.this.revalidate();
		data = new CityServerProcess(IP, PORT, USERNAME, PASSWORD, updates, "serverprocess");
		data.start();
	}
	
	
}
