package cityOffAir;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;

public class Simple360 extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private File config = new File("C://CITY1051FM//Config.ini");
	private File Home = new File("C://CITY1051FM//CitySound//");
	private JLabel folders = new JLabel("DEFAULT");
	private JLabel addUser = new JLabel("+New");
	private JScrollPane pane = new JScrollPane();
	private JosephFrame joseph = null;
	private JSplitPane partition = new JSplitPane();
	private int e=400;
	private File dirDelFolder = null;
	private Timer t;
	private ArrayList<Object> configurations = new ArrayList<Object>();
	private CrypterDec crypter = new CrypterDec();
	private CrypterEnc encrypt = new CrypterEnc();
	private final JPopupMenu popupMenu = new JPopupMenu();
	private final JMenuItem mntmOpenOperations = new JMenuItem("Open Operations");
	public Simple360() {
		setTitle("Simple360");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Simple360.class.getResource("/cityOffAir/LOG.png")));
		setBounds(100, 100, 1000, 500);
		setDefaultCloseOperation(Simple360.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(120, e));
		contentPanel.setAutoscrolls(true);
		contentPanel.setForeground(new Color(0, 139, 139));
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		pane.setViewportView(contentPanel);
		getContentPane().add(pane, BorderLayout.CENTER);
		
		folders = new JLabel("DEFAULT");
		folders.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				joseph = new JosephFrame(configurations);
				joseph.folder("Default");
				joseph.Joseph();
				getContentPane().add(pane, BorderLayout.WEST);
				getContentPane().add(joseph, BorderLayout.CENTER);
				revalidate();
				
				joseph.setStringInterface(new StringInterface() {
					public void response(String folder, int s) {
						dispose();
						System.gc();
						System.out.println("reloading");
						
						new Simple360().setVisible(true);
					};
				});
			}
		});
		
		folders.setBorder(new LineBorder(new Color(0, 0, 51), 2));
		folders.setHorizontalTextPosition(SwingConstants.CENTER);
		folders.setVerticalTextPosition(SwingConstants.BOTTOM);
		folders.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
		folders.setIcon(new ImageIcon(Simple360.class.getResource("/cityOffAir/"+1+".png")));
		folders.setForeground(new Color(128, 128, 0));
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 0, 0));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
			{
				addUser.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						String user = JOptionPane.showInputDialog("Please the new User.");
						if(user != null && !user.equals("")&& !user.contains("%")){
							File firstuse = new File("C://CITY1051FM//CitySound//"+user.toUpperCase());
							if (!firstuse.exists())firstuse.mkdir();
							dispose();
							new Simple360().setVisible(true);
							System.gc();
						}
					}
				});
				addUser.setForeground(new Color(100, 149, 237));
				buttonPane.add(addUser);
				addUser.setPreferredSize(new Dimension(61, 40));
				addUser.setMaximumSize(new Dimension(100, 100));
				addUser.setFont(new Font("Cambria Math", Font.PLAIN, 18));
			}
		}
		{
			addPopup(this, popupMenu);
		}
		{
			mntmOpenOperations.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			popupMenu.add(mntmOpenOperations);
		}
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {
				if(!Home.exists())Home.mkdir();
				if(!config.exists()){
					try {
						config.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}}
				try {
					configurations = crypter.CrypterDec(config.getAbsolutePath());
				} catch (Exception e1) {
					configurations.add("def2.png"); 
					configurations.add("Folder to Clear"); 
					configurations.add("reset"); 
					configurations.add("InMixer"); 
					configurations.add("OutMixer"); 
					e1.printStackTrace();
				}
				
				if(Home.list().length<1){
					String user = JOptionPane.showInputDialog("No existing User found. Please enter a Username.");
					if(user != null && !user.equals("")&& !user.contains("%")){
						File firstuse = new File("C://CITY1051FM//CitySound//"+user.toUpperCase());
						if (!firstuse.exists())firstuse.mkdir();
					}
				}
				
				String[] homeFolders = Home.list();
				contentPanel.setPreferredSize(new Dimension(120, (Home.list().length*e)));
				//contentPanel.revalidate();
				System.out.println(homeFolders.length);
				for(int u = 0; homeFolders.length>u; u++){
					System.out.println(homeFolders[u]);
					folders = new JLabel(homeFolders[u]);
					folders.setHorizontalTextPosition(SwingConstants.CENTER);
					folders.setVerticalTextPosition(SwingConstants.BOTTOM);
					folders.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
					folders.setIcon(new ImageIcon(Simple360.class.getResource("/cityOffAir/"+u+".png")));
					folders.setForeground(new Color(128, 128, 0));
					folders.setBorder(new LineBorder(new Color(0, 0, 51), 5));
					contentPanel.add(folders);
					folders.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent x) {
							System.out.println(joseph);
							if(joseph != null){
								partition.setVisible(false);
								partition.remove(joseph);
								joseph = null;
								getContentPane().remove(partition);
								partition = null;
								revalidate();
								
								joseph = new JosephFrame(configurations);
								JLabel fold = (JLabel) x.getSource();
								System.out.println(fold.getText());
								joseph.folder(fold.getText());
								joseph.Joseph();
								partition = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pane,joseph);
								partition.setDividerLocation(250);
								getContentPane().add(partition, BorderLayout.CENTER);
								partition.setOneTouchExpandable(true);
								partition.setVisible(true);
								
								joseph.setStringInterface(new StringInterface() {
									public void response(String folder, int s) {
										dispose();
										System.gc();
										System.out.println("reloading");
										
										new Simple360().setVisible(true);
									};
								});
								revalidate();
							}else{
							joseph = new JosephFrame(configurations);
							JLabel fold = (JLabel) x.getSource();
							System.out.println(fold.getText());
							joseph.folder(fold.getText());
							joseph.Joseph();
							partition = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pane,joseph);
							partition.setDividerLocation(250);
							getContentPane().add(partition, BorderLayout.CENTER);
							partition.setOneTouchExpandable(true);
							
							joseph.setStringInterface(new StringInterface() {
								public void response(String folder, int s) {
									dispose();
									System.gc();
									System.out.println("reloading");
									
									new Simple360().setVisible(true);
								};
							});
							revalidate();
							}
						}
					});
					revalidate();
				}
			}
			
			
			public void windowClosing(WindowEvent arg0) {
				System.out.println("Final Configuration:"+configurations);
				try {
					encrypt.CrypterEnc(config.getAbsolutePath(), configurations);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(joseph != null)joseph.stopThreads();
				System.out.println("setting window hidden");
				setVisible(false);
			}
		});
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
}
