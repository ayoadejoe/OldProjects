package FumsifolEsp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Cursor;

public class MainFace extends JFrame {

	private JPanel contentPane;
	private JButton openDoor = new JButton("OPEN");
	private JButton freeDoor = new JButton("FREE");
	private EspClient control = new EspClient();
	private JButton onBeep = new JButton("BEEP ON");
	private JButton offBeep = new JButton("BEEP OFF");
	private JMenuItem mntmChangeIp = new JMenuItem("Change IP");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFace frame = new MainFace();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String IP;
	private File file = new File("IPStore.ini");
	private JTextField result = new JTextField();
	private Scanner fileRead;
	private PrintStream filePrint;

	public MainFace() {
		setTitle("AUTODOOR");
		setLocationRelativeTo(getParent());
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainFace.class.getResource("/FumsifolEsp/logout.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 297, 300);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(100, 149, 237));
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmChangeIp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String outcome = IPAddress();
				result.setText("new add: " + outcome);
			}
		});
		mnFile.add(mntmChangeIp);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (file.exists()) {
			IP = IPAddress();
			result.setText("Connection on: " + IP);
		}
		result.setHorizontalAlignment(SwingConstants.CENTER);
		result.setForeground(new Color(153, 0, 153));
		result.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		result.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		result.setEditable(false);
		result.setBorder(null);

		result.setBackground(new Color(100, 149, 237));
		menuBar.add(result);
		result.setColumns(10);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 2, 2));

		openDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String outcome = control.connect(IPAddress(), "open1500");
				result.setText(outcome);
			}
		});

		openDoor.setVerticalTextPosition(SwingConstants.BOTTOM);
		openDoor.setHorizontalTextPosition(SwingConstants.CENTER);
		openDoor.setIcon(new ImageIcon(MainFace.class
				.getResource("/FumsifolEsp/door_closed-48.png")));
		openDoor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		openDoor.setBackground(new Color(102, 102, 255));
		openDoor.setBorder(new CompoundBorder(new EtchedBorder(
				EtchedBorder.RAISED, new Color(100, 149, 237), new Color(0, 0,
						255)), new BevelBorder(BevelBorder.RAISED, null, null,
				null, null)));
		openDoor.setPreferredSize(new Dimension(59, 57));
		contentPane.add(openDoor);

		freeDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String outcome = control.connect(IPAddress(), "close400");
				result.setText(outcome);
			}
		});

		freeDoor.setVerticalTextPosition(SwingConstants.BOTTOM);
		freeDoor.setHorizontalTextPosition(SwingConstants.CENTER);
		freeDoor.setIcon(new ImageIcon(MainFace.class
				.getResource("/FumsifolEsp/open_window-48.png")));
		freeDoor.setBackground(new Color(102, 102, 255));
		freeDoor.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		freeDoor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		freeDoor.setPreferredSize(new Dimension(57, 57));
		contentPane.add(freeDoor);

		onBeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String outcome = control.connect(IPAddress(), "beepON10");
				result.setText(outcome);
			}
		});

		onBeep.setHorizontalTextPosition(SwingConstants.CENTER);
		onBeep.setIcon(new ImageIcon(MainFace.class
				.getResource("/FumsifolEsp/speaker-48.png")));
		onBeep.setVerticalTextPosition(SwingConstants.BOTTOM);
		onBeep.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		onBeep.setBackground(new Color(51, 102, 255));
		contentPane.add(onBeep);
		offBeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String outcome = control.connect(IPAddress(), "beepOFF1");
				result.setText(outcome);
			}
		});

		offBeep.setVerticalTextPosition(SwingConstants.BOTTOM);
		offBeep.setHorizontalTextPosition(SwingConstants.CENTER);
		offBeep.setIcon(new ImageIcon(MainFace.class
				.getResource("/FumsifolEsp/mute-48.png")));
		offBeep.setBackground(new Color(51, 102, 255));
		offBeep.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null,
				null, null));
		contentPane.add(offBeep);
	}

	private String IPAddress() {
		if (file.exists()) {
			try {
				fileRead = new Scanner(file);
				if (fileRead.hasNextLine()) {
					IP = fileRead.nextLine();
					System.out.println(IP);
					if(IP==null)acquire();
				} else {
					acquire();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				IP = "localhost";
			}
		} else {
			JOptionPane.showMessageDialog(MainFace.this, "Error!",
					"Address is not in database", JOptionPane.ERROR_MESSAGE);
			IP = "localhost";
		}
		return IP;
	}

	private void acquire() {
		String entry = JOptionPane.showInputDialog("Enter Connection Address");
		try {
			filePrint = new PrintStream(file);
			filePrint.println(entry);
			IP = entry;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
