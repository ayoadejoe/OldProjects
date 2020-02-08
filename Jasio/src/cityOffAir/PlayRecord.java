package cityOffAir;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class PlayRecord extends JPanel {
	private JButton btnPause = new JButton("Pause");
	private JButton btnStop = new JButton("Stop");
	private JSlider slider = new JSlider();
	Player logic;
	private File file = null;
	private int location = 0;
	private final JLabel lblElapsed = new JLabel("Elapsed");
	private ArrayList configurations=null;
	
	
	public ArrayList getConfigurations() {
		return configurations;
	}

	public void setConfigurations(ArrayList configurations) {
		this.configurations = configurations;
	}

	public PlayRecord(ArrayList<Object> configurations) {
		logic = new Player(configurations);
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Playback", TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		setBackground(new Color(102, 204, 153));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{63, 62, 69, 69, 95, 95, 95, 0};
		gridBagLayout.rowHeights = new int[]{49, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnPause.getText().equals("Pause")){
					logic.pausePlay(true);
				btnPause.setText("Play");
				}else{
					 logic.pausePlay(false);
					btnPause.setText("Pause");
				}
				
			}
		});
		
		btnPause.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		btnPause.setBackground(new Color(255, 255, 102));
		GridBagConstraints gbc_btnPause = new GridBagConstraints();
		gbc_btnPause.insets = new Insets(0, 5, 0, 0);
		gbc_btnPause.fill = GridBagConstraints.BOTH;
		gbc_btnPause.gridx = 0;
		gbc_btnPause.gridy = 0;
		add(btnPause, gbc_btnPause);
		
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 0, 5);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logic.stopPlay();
			}
		});
		btnStop.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		btnStop.setBackground(new Color(0, 128, 0));
		gbc_btnStop.fill = GridBagConstraints.BOTH;
		gbc_btnStop.gridx = 1;
		gbc_btnStop.gridy = 0;
		add(btnStop, gbc_btnStop);
		
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.BOTH;
		gbc_slider.gridwidth = 4;
		gbc_slider.gridx = 3;
		gbc_slider.gridy = 0;
		slider.setBackground(new Color(102, 204, 153));
		slider.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent arg0) {
				location = slider.getValue();
				//System.out.println("slider point dragged to:"+location);
				logic.positionChange(location, true);
			}
		});
		
		GridBagConstraints gbc_lblElapsed = new GridBagConstraints();
		gbc_lblElapsed.insets = new Insets(0, 0, 0, 5);
		gbc_lblElapsed.gridx = 2;
		gbc_lblElapsed.gridy = 0;
		lblElapsed.setFont(new Font("Cambria Math", Font.PLAIN, 15));
		lblElapsed.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		add(lblElapsed, gbc_lblElapsed);
		
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		add(slider, gbc_slider);
		
		slider.setMajorTickSpacing(10);
		slider.setValue(0);
		logic.setPlayerInterface(new PlayerInterface() { int c=0, t = 0, z= 0;
			public void sendout(long framelength, int frameposition) {
				if(frameposition ==0){
				slider.setMaximum(100);
				slider.setMinimum(0);
				lblElapsed.setText("00:00");
				}else{
					double div = (double)frameposition/framelength;
							double per = div*100;
							int position = (int) Math.round(per);
							//System.out.println(position);
							//if(position>80)position+=5;
				
				int time = new Date().getSeconds();
				int elapse;
	         	if(time != c){
	         		t++;
	         		String zx = t+"", df = z+"", ts = ""; 
	         		if(t<10){
						zx = "0"+t;
					}
					if(z<10){
						df = "0"+z;
					}
					ts = df+":"+zx;
	         		if(t==59) {
	         			z++; t=0;
	         		}
	         		lblElapsed.setText(ts);
	         	}
	         	slider.setValue(position);
				if(frameposition== -1){
					c=0; t = 0; z= 0;
					slider.setValue(100);
				}
	         	c= time;
				}
			}
		});
		
	}
	
	public void setfileToPlay(String filetoPlay){
		if(logic.getPlayStatus()==true){
			logic.stopPlay();
			//logic.Player(filetoPlay);
		}else{
			logic.Player(filetoPlay);
		}
	}
	
	
	
}
