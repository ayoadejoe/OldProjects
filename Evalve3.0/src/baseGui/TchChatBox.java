package baseGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TchChatBox extends JDialog implements Runnable{
	private JTextArea received = new JTextArea();
	private JTextArea typed = new JTextArea();
	private JButton b1, b2, b3;
	private JLabel l1, chatter, chattee;
	private Writer pen = new Writer();
	private Date date; int x = 0, y = 0, z= 0, a = 1, p = 0, opnr = 0, cnt = 0;
	private String realDate, realTime, checkOld = null, me = " ", me3 = " ";
	private List<Integer> extract; private String you = " ";
	private Color AdminC2, headC;
	private File file = new File("infoC.txt");
	private int k = 0, exit = 0; 		private Timer tt = null;
	private String threadName = null; private Thread t = null;
	private DataOutputStream output; // output stream to server
	private DataInputStream input; // input stream from server
	private  DataOutputStream out;
	private String message = ""; // message from server
	private String chatServer; // host server for this application
	private Socket client; // socket to communicate with server
	//ports
	private int ChatPort2 = 7378; private int ChatPort1 = 7379; private int ChatPort3 = 7380;
	
	//IPs
	private String IP = "192.168.0.101";
	private String Friend = null, Self = null;
	
	private ImageIcon iconLoad = new ImageIcon("IconCon.png"); 
	private JLabel connected = new JLabel(iconLoad);
	private ImageIcon iconLoadI = new ImageIcon("IconDiscon.png"); 
	private JLabel disconnected = new JLabel(iconLoadI);
	private ImageIcon iconLoadI2 = new ImageIcon("OnIcon.png"); 
	private JLabel online = new JLabel(iconLoadI2);
	private ImageIcon iconLoadI3 = new ImageIcon("OffIcon.png"); 
	private JLabel offline = new JLabel(iconLoadI3);
	
	public TchChatBox(JFrame parent, final String self, final String friend, String name) {
		super(parent, "CHAT-EVALVE", false);
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Friend = friend;
		Self = self;
		threadName = name;
		date = new Date();
		realDate = date.toLocaleString().substring(0, 12);
		realTime = date.toLocaleString().substring(12);
		
	    setLayout(new FlowLayout());
	    
	    JMenu access = new JMenu("Menu");
		access.setMnemonic('M');
		JMenu GenServ = new JMenu("Connection");
		GenServ.setMnemonic('C');
		GenServ.setToolTipText( "Click on this to connect a port that may be faster." );

		JMenuItem port1 = new JMenuItem("Faith");
		JMenuItem port2 = new JMenuItem("Great");
		JMenuItem port3 = new JMenuItem("Praise");
		
		GenServ.add(port1);
		port1.setToolTipText( "Click on this to connect with the Server and obtain the latest information." );
		GenServ.addSeparator();
		GenServ.add(port2);
		GenServ.addSeparator();
		GenServ.add(port3);
		
		
		JMenuItem history = new JMenuItem("Load History");
		JMenuItem Clear = new JMenuItem("Clear History");
		JMenuItem sendB = new JMenuItem("Send Book");		
		JMenuItem sendA = new JMenuItem("Send Audio");
		JMenuItem Exit = new JMenuItem("Exit");
		
		access.add(history);
		access.add(Clear);
		access.add(sendB);
		access.add(sendA);
		access.addSeparator();
		access.add(Exit);
		
		JMenuBar bar = new JMenuBar(); // create menu bar 
		setJMenuBar( bar ); // add menu bar to application
		bar.add(access);
		bar.add(GenServ);
		
	    b1=new JButton("SEND MESSAGE");
	    // Just for refresh :) Not optional!
	    Color GroundColor = new Color(150, 100, 200);
		setBackground(GroundColor);
	    setSize(340,500);
	    //setAlwaysOnTop(true);
	    setLocationRelativeTo(getParent());
	    setVisible(true);
	    
	    chatter = new JLabel(self.toLowerCase());
	    chattee = new JLabel("|"+Friend.toLowerCase());
	    l1=new JLabel("");
	    l1.setText("CHAT-EVALVE");
	    l1.setFont(new Font("SERIF", Font.BOLD, 27));
	    setLayout(new GridBagLayout());
		GridBagConstraints align = new GridBagConstraints();
		
		align.insets = new Insets(0, 0, 0, 10);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(disconnected, align);
		
		align.insets = new Insets(0, 0, 0, 10);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(connected, align);
		connected.setVisible(false);
		
		align.insets = new Insets(0, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		chattee.setFont(new Font("SERIF", Font.BOLD, 22));
		add(chattee, align);
		
		align.insets = new Insets(280, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		chatter.setFont(new Font("SERIF", Font.BOLD, 22));
		//add(chatter, align);
		
		align.insets = new Insets(30, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		JScrollPane jst1 = new JScrollPane(received);
		Dimension dim1 = jst1.getPreferredSize();
		dim1.height = 320;
		dim1.width = 300;
		jst1.setPreferredSize(dim1);
		received.setLineWrap(true);
		received.setWrapStyleWord(true);
		received.setEditable(false);
		received.setText(realDate+":    ");
		received.append("\n");
		received.setBackground(Color.LIGHT_GRAY);
		received.setForeground(Color.BLUE);
		add(jst1, align);
		
		align.insets = new Insets(360, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		JScrollPane jst2 = new JScrollPane(typed);
		Dimension dim2 = jst2.getPreferredSize();
		dim2.height = 70;
		dim2.width = 300;
		jst2.setPreferredSize(dim2);
		typed.setLineWrap(true);
		typed.setWrapStyleWord(true);
		typed.setEditable(true);
		typed.setText("Type here. Click ENTER to send message.");
		add(jst2, align);
		typed.setFocusable(true);
		typed.setCursor(getCursor());
		
		align.insets = new Insets(430, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		//add(b1, align);
		
		typed.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent g) {
				if(g.getKeyCode() == KeyEvent.VK_ENTER){
					date = new Date();
					realDate = date.toLocaleString().substring(0, 12);
					realTime = date.toLocaleString().substring(12);
					String type = typed.getText();
					if(type.contains("<") || type.contains(" < ")|| type.contains("`")){
						Toolkit.getDefaultToolkit().beep();
						type = type.replace("<", "-less than-");
						type = type.replace("`", "'");
					}
					if(type.equals("")||type.equals("\n")||type.equals("  ")){
						typed.setText("");
						Toolkit.getDefaultToolkit().beep();
					}else{
						String Statement = "("+realTime+")|"+Self+"> "+type+"  \n|";
						TchChatClient pack = new TchChatClient();
						pack.sendMsg(Self, Friend, Statement);
						received.append("("+realTime+")|"+Self+"> "+type+"\n");
						typed.setText("");
					}
				}
			}
		});
		
		typed.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				typed.setText("");
			}
		});
		
		 addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent arg0) {
					TchChatClient getIt = new TchChatClient();
					exit = 1;
					String newMsg = getIt.Messages(Self, Friend, exit);
					t.stop();
					tt.stop();
				}
		    	
		    });
	}

	
	
	public void run() {
		tt = new Timer(5000, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				TchChatClient getIt = new TchChatClient();
				String newMsg = getIt.Messages(Self, Friend, exit);
				if(newMsg != null && !newMsg.equals("") && !newMsg.equals("here``")){
				received.append(newMsg+"\n");
				connected.setVisible(true);
				disconnected.setVisible(false);
				revalidate();
				}
				if(newMsg.equals("here``")){
					connected.setVisible(false);
					disconnected.setVisible(true);
					revalidate();
				}
			}
	    });
		
	    tt.start();
	}
	
	public void start (){      
		////("Starting " +  threadName );      
		if (t == null){        
			t = new Thread (this, threadName);         
			t.start ();      
			}   
		}
	
	//Date today = new Date();
	//String Today = today.toLocaleString();
	//received.setText(Today);
}
