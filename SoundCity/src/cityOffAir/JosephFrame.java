package cityOffAir;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class JosephFrame extends JPanel {
	public JosephFrame(ArrayList<Object> configurations) {
		this.configurations = configurations;
		//Joseph();
	}
	
	private JavaSoundRecorder recorder;
	private PlayRecord play;
	
	private ArrayList<Object> configurations;
	private JButton btnRecord = new JButton("");
	private JProgressBar progressBar = new JProgressBar();
	private JPanel buttonsPanel = new JPanel();
	private final JButton btnpause = new JButton("Pause");
	private int z = 0;
	private final JLabel duration = new JLabel("00:00");
	private JLabel contentPane;
	private JPanel controlPanel2 = new JPanel();
	private String background = "def2.png";
	private final JPopupMenu popupMenu = new JPopupMenu();
	private final JPopupMenu buttonPopup = new JPopupMenu();
	private final JMenuItem changeB = new JMenuItem("Change Background");
	private final JMenuItem changeB2 = new JMenuItem("Clean up!");
	private final JMenuItem popButDelete = new JMenuItem();
	private final JMenuItem popButEdit = new JMenuItem();
	private Image thumb=null;
	private JButton call = new JButton("Voice Call: 0");
	private String Folder = "", fileTag = "";
	private JLabel nameIt = new JLabel();
	private JButton butt = null;
	private JPanel mainControl = new JPanel();
	private JScrollPane controlPane = new JScrollPane();
	private JScrollPane wrapBack = new JScrollPane();
	final JButton btnStop = new JButton("Stop");
	private StringInterface levels;
	private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel panel = new JPanel();
	private JComboBox inpChannel;
	private JLabel inputChannel = new JLabel("                      Input Channels:");
	private JComboBox OutChannel = new JComboBox();
	private JLabel outputChannel = new JLabel("           Output Channels:");
	public void Joseph() {
		setPreferredSize(new Dimension(600, 300));
		setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		contentPane = new JLabel();
		contentPane.setAutoscrolls(true);
		System.out.println(configurations);
		nameIt.setText(Folder+"             ");
		
		play = new PlayRecord(configurations);
		recorder = new JavaSoundRecorder(configurations);
		
		try{
			background = (String) configurations.get(0);
			File img = new File(background);
			ImageIcon icon = new ImageIcon(ImageIO.read(img));
			thumb = icon.getImage().getScaledInstance(size.width-250, size.height, Image.SCALE_SMOOTH);
			contentPane.setIcon((new ImageIcon(thumb)));
		}catch(Exception c){
			System.out.println(background);
			contentPane.setIcon((new ImageIcon(JosephFrame.class.getResource(background))));
		}
		contentPane.setLayout(new BorderLayout());
		wrapBack.setViewportView(contentPane);
		add(wrapBack, BorderLayout.CENTER);
		FlowLayout flowLayout = (FlowLayout) buttonsPanel.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(10);
		buttonsPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3){
					System.out.println("right clicked");
					popupMenu.show(buttonsPanel, e.getX(), e.getY());
				}
			}
		});
		
		buttonsPanel.setOpaque(false);
		contentPane.add(buttonsPanel, BorderLayout.CENTER);
		controlPanel2.setOpaque(false);
		controlPane.setViewportView(mainControl);
		mainControl.setLayout(new BorderLayout());
		mainControl.add(controlPanel2, BorderLayout.CENTER);
		controlPanel2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Recorder", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		mainControl.setBackground(new Color(240, 220, 200));
		add(controlPane, BorderLayout.SOUTH);
		GridBagLayout gbl_controlPanel2 = new GridBagLayout();
		gbl_controlPanel2.columnWidths = new int[]{69, 68, 66, 32, 0, 33, 0, 0, 0, 0};
		gbl_controlPanel2.rowHeights = new int[]{50, 0};
		gbl_controlPanel2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_controlPanel2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		controlPanel2.setLayout(gbl_controlPanel2);
		GridBagConstraints gbc_btnRecord = new GridBagConstraints();
		gbc_btnRecord.insets = new Insets(0, 0, 0, 0);
		gbc_btnRecord.fill = GridBagConstraints.BOTH;
		gbc_btnRecord.gridx = 0;
		gbc_btnRecord.gridy = 0;
		controlPanel2.add(btnRecord, gbc_btnRecord);
		
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(play != null){
					if(play.logic.getPlayStatus()){
						play.logic.stopPlay();
					}
				}
				if(inpChannel.isEnabled())inpChannel.setEnabled(false);
				recorder.trueRunner();
				recorder.captureAudio();
				btnRecord.setEnabled(false);
				btnStop.setEnabled(true);
			}
		});
		btnRecord.setText("Record");
		btnRecord.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null))));
		btnRecord.setBackground(new Color(204, 0, 0));
		//btnRecord.setVerticalTextPosition(SwingConstants.BOTTOM);
		//btnRecord.setIcon(new ImageIcon(JosephFrame.class.getResource("/cityOffAir/as.png")));
		
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 0, 0);
		gbc_btnStop.fill = GridBagConstraints.BOTH;
		gbc_btnStop.gridx = 1;
		gbc_btnStop.gridy = 0;
		controlPanel2.add(btnStop, gbc_btnStop);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRecord.setEnabled(true);
				recorder.falseRunner();
				try {
					recorder.save(Folder);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(!inpChannel.isEnabled())inpChannel.setEnabled(true);
				recorder.finish();
				btnStop.setEnabled(false);
			}
		});
		btnStop.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		btnStop.setBackground(new Color(0, 128, 0));
		btnStop.setEnabled(false);
		GridBagConstraints gbc_btnpause = new GridBagConstraints();
		gbc_btnpause.insets = new Insets(0, 0, 0, 0);
		gbc_btnpause.fill = GridBagConstraints.BOTH;
		gbc_btnpause.gridx = 2;
		gbc_btnpause.gridy = 0;
		controlPanel2.add(btnpause, gbc_btnpause);
		
		btnpause.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		btnpause.setBackground(new Color(255, 255, 102));
		btnpause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnpause.getText().equals("Resume")){
					recorder.resumeThread();
					btnpause.setText("Pause");
				}else{
				try {
					recorder.pauseThread();
					btnpause.setText("Resume");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			}
		});
		GridBagConstraints gbc_duration = new GridBagConstraints();
		gbc_duration.insets = new Insets(0, 0, 0, 0);
		gbc_duration.fill = GridBagConstraints.BOTH;
		gbc_duration.gridx = 3;
		gbc_duration.gridy = 0;
		controlPanel2.add(duration, gbc_duration);
		duration.setForeground(new Color(0, 51, 102));
		duration.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		duration.setFont(new Font("Cambria Math", Font.BOLD, 21));
		duration.setHorizontalAlignment(SwingConstants.CENTER);
		duration.setBackground(new Color(153, 204, 255));
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 5;
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridx = 4;
		gbc_progressBar.gridy = 0;
		progressBar.setOpaque(true);
		controlPanel2.add(progressBar, gbc_progressBar);
		progressBar.setForeground(new Color(255, 102, 51));
		
		buttonsPanel.setBackground(new Color(233, 150, 122));
		
		buttonsPanel.add(popupMenu);
		
		changeB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File clearContents = new File("C://CITY1051FM//CitySound//"+Folder+"//");
				if(clearContents.exists()){
					String[] list = clearContents.list();
					for(int r = 0; list.length>r; r++){
						System.gc();
						del("C://CITY1051FM//CitySound//"+Folder+"//"+list[r]);
					}
					levels.response(null, 0);
				}
			}
		});
		
		
		changeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser tc = new JFileChooser();
				 tc.addChoosableFileFilter(new ImageFilter());
		          tc.setAcceptAllFileFilterUsed(true);
		            tc.setFileView(new ImageFileView());
		            tc.setAccessory(new ImagePreview(tc));
		            
				int result = tc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION){
					File file = tc.getSelectedFile();
					String nameFile = file.getAbsolutePath();
					configurations.remove(0);
					configurations.add(0, nameFile);
					try{
						ImageIcon icon = new ImageIcon(ImageIO.read(file));
						thumb = icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
						contentPane.setIcon((new ImageIcon(thumb)));
					}catch(Exception u){
						JOptionPane.showMessageDialog(null, u.getMessage());
						u.printStackTrace();
					}
				}
				revalidate();
			}
		});
		
		popupMenu.add(changeB);	popupMenu.add(changeB2);
		call.setToolTipText("Call made at:");
		call.setFont(new Font("Georgia Pro Light", Font.BOLD, 15));
		call.setBackground(new Color(47, 79, 79));
		call.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new LineBorder(new Color(0, 0, 0))));
		call.setPreferredSize(new Dimension(200, 100));
		call.setMaximumSize(new Dimension(267, 267));
		
		buttonsPanel.add(call);
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel .setBackground(new Color(0, 0, 0));
		
		wrapBack.setColumnHeaderView(panel);
		nameIt.setForeground(new Color(153, 153, 0));
		nameIt.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		panel.add(nameIt);
		inputChannel.setForeground(new Color(153, 255, 0));
		
		panel.add(inputChannel);
		String[] mix = availableMixerLines("inputs");
		
		inpChannel = new JComboBox(mix);
		inpChannel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = (String) inpChannel.getSelectedItem();
				configurations.remove(3); configurations.add(3, input);
				inpChannel.setSelectedIndex(inpChannel.getSelectedIndex());
				System.out.println("new input selected");
			}
		});
		
		for(int x=0; mix.length>x; x++){
			System.out.println("mix x-"+mix[x]);
			System.out.println(configurations.get(3));
			if(mix[x].contains(configurations.get(3).toString())){
				inpChannel.setSelectedIndex(x);
				break;
			}
		}
		inpChannel.setPreferredSize(new Dimension(250, 20));
		inpChannel.setForeground(Color.BLACK);
		
		panel.add(inpChannel);
		outputChannel.setForeground(new Color(153, 204, 255));
		
		panel.add(outputChannel);
		OutChannel.setForeground(new Color(102, 255, 51));
		
		String[] mix2 = availableMixerLines("outputs");
		OutChannel = new JComboBox(mix2);
		OutChannel.setPreferredSize(new Dimension(250, 20));
		panel.add(OutChannel);
		
		OutChannel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = (String) OutChannel.getSelectedItem();
				configurations.remove(4); configurations.add(4, input);
				OutChannel.setSelectedIndex(OutChannel.getSelectedIndex());
				System.out.println("new output selected");
			}
		});
		
		for(int x=0; mix2.length>x; x++){
			System.out.println("mix x-"+mix2[x]);
			System.out.println(configurations.get(4));
			if(mix2[x].contains(configurations.get(4).toString())){
				OutChannel.setSelectedIndex(x);
				break;
			}
		}
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 0;
		
		recorder.setLevelInterface(new LevelInterface() {
			public void response(int level) {
				progressBar.setValue(level);
			}
		});
		
		play.setStringInterface(new StringInterface() {
			public void response(String string, int s) {
				//Player status report
				if(!OutChannel.isEnabled())OutChannel.setEnabled(true);
			}
		});
		
		recorder.setStringInterface(new StringInterface() {
			public void response(final String value, int s) {
				if(value != null){
					System.out.println(value);
					String [] name = value.split("%");
					call = new JButton(Folder+" "+name[0]);
					call.setToolTipText("Call made at "+name[1].replace("_", ":"));
					call.setFont(new Font("Georgia Pro Light", Font.BOLD, 15));
					call.setBackground(new Color(47, 79, 79));
					call.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new LineBorder(new Color(0, 0, 0))));
					call.setPreferredSize(new Dimension(200, 100));
					call.setMaximumSize(new Dimension(267, 267));
					buttonsPanel.add(call);
					
					call.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent j) {
							if(j.getButton() == MouseEvent.BUTTON3){
								butt = (JButton) j.getSource();
								popButDelete.setText("DELETE "+butt.getText());
								popButEdit.setText("EDIT "+butt.getText());
								fileTag = butt.getName();
								butt.add(buttonPopup); buttonPopup.add(popButDelete);buttonPopup.add(popButEdit);
								buttonPopup.show(butt, j.getX(), j.getY());
							}
						}
					});
					
					call.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							mainControl.add(play, BorderLayout.EAST);
							play.setfileToPlay("C://CITY1051FM//CitySound//"+Folder+"//"+value);
							if(OutChannel.isEnabled())OutChannel.setEnabled(false);
							play.setVisible(true);
							revalidate();
						}
					});
					revalidate();
				}else{
					String zx = s+"", df = z+"", ts = ""; 
					if(s==0)z++;
					if(s<10){
						zx = "0"+s;
					}
					if(z<10){
						df = "0"+z;
					}
					
					ts = df+":"+zx;
					duration.setText(ts);
				}
				
			}
		});
		
		
		File inDir = new File("C://CITY1051FM//CitySound//"+Folder+"//");
		if(inDir.exists()){
			String [] recordings = inDir.list();  
			for(int e=0; recordings.length>e; e++){
				String [] name = recordings[e].split("%");
				final String rec=recordings[e];
				System.out.println("rec:"+rec);
				call = new JButton(Folder+" "+name[0]);
				call.setToolTipText("Call made at "+name[1].replace("_", ":"));
				call.setFont(new Font("Georgia Pro Light", Font.BOLD, 15));
				call.setBackground(new Color(47, 79, 79));
				call.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new LineBorder(new Color(0, 0, 0))));
				call.setPreferredSize(new Dimension(200, 100));
				call.setMaximumSize(new Dimension(267, 267));
				call.setName(recordings[e]);
				buttonsPanel.add(call);
				
				call.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						mainControl.add(play, BorderLayout.EAST);
						play.setfileToPlay("C://CITY1051FM//CitySound//"+Folder+"//"+rec);
						if(OutChannel.isEnabled())OutChannel.setEnabled(false);
						play.setVisible(true);
						revalidate();
					}
				});
				
				call.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent j) {
						if(j.getButton() == MouseEvent.BUTTON3){
							butt = (JButton) j.getSource();
							popButDelete.setText("DELETE "+butt.getText());
							popButEdit.setText("EDIT "+butt.getText());
							fileTag = butt.getName();
							butt.add(buttonPopup); buttonPopup.add(popButDelete);buttonPopup.add(popButEdit);
							buttonPopup.show(butt, j.getX(), j.getY());
						}
					}
				});
			}
		}
		
		
		popButDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(fileTag);
				File fileToDelete = new File("C://CITY1051FM//CitySound//"+Folder+"//"+fileTag);
				if(fileToDelete.delete()){
					JOptionPane.showMessageDialog(JosephFrame.this, fileTag+" deleted.");
					buttonsPanel.remove(butt);
					butt.setVisible(false);
					revalidate();
				}else{
					fileToDelete.deleteOnExit();
					buttonsPanel.remove(butt);
					butt.setVisible(false);
					revalidate();
				}
			}
		});
	}
	
	public void folder(String folder){
		Folder = folder;
	}

	public void stopThreads() {
		if(recorder != null)recorder.falseRunner();
		if(play.logic != null)play.logic.stopPlay();
	}
	
	public void setStringInterface(StringInterface value){
		this.levels = value;
	}
	
	public void del(String address){
				File toDelete = new File(address);
				toDelete.delete();
				if(toDelete.delete()){
					System.out.println(toDelete+" now deleted");
				}else{
					System.out.println(toDelete+" could not be deleted");
				}
			}
	
	public String[] availableMixerLines(String type){
		Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo(); 
		Vector <String>mixers = new Vector();
		 if(type.equals("inputs")){
		 for(int z = 0; mixerInfo.length>z; z++){
			 if(!mixerInfo[z].toString().contains("Speakers")&& !mixerInfo[z].toString().contains("Port")){
				 mixers.add(mixerInfo[z].toString());
			 } }
		 } 
		 
		 if(type.equals("outputs")){
			 for(int z = 0; mixerInfo.length>z; z++){
				 if(mixerInfo[z].toString().contains("Speakers")&& !mixerInfo[z].toString().contains("Port")){
					 mixers.add(mixerInfo[z].toString());
				 } }
			 } 
		 String[] Lines = new String[mixers.size()];
		 for(int c = 0; mixers.size()>c; c++){
			 Lines[c]= mixers.get(c);
		 }
	    	
		return Lines;
	}
}
