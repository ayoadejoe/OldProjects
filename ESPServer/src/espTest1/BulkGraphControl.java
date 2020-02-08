package espTest1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BulkGraphControl extends JFrame{
	private JLabel refresh = new JLabel("Refresh"); 
	private JLabel nxt = new JLabel("Next"); 
	private JLabel prv = new JLabel("Previous"); 
	private JSlider hours = new JSlider();
	private JPanel controlPanel = new JPanel();
	private GeneratorGraph graph;
	private static FileSerializer info = new FileSerializer();
	private static  List<Object> data = new ArrayList<>();
	private static List<Object> defaulT = new ArrayList<>();
	private static  File daily100 = new File("100kVA Fuel Log");
	private File daily75 = new File("75kVA Fuel Log");
	private boolean compress;
	private int mins = 30, hrs;
	private final JLabel lblTimeFramehrs = new JLabel("Time Frame (hrs):");
	private final JLabel lblOpenReadings = new JLabel("Open Readings");
	private GenPlotEnumerator enumerate;
	int lowerLimit, upperLimit;
	private final JComboBox dateSet = new JComboBox();
	public BulkGraphControl(String toPlot, final List<Object> Plot) {
		
		this.setSize(900,600);
		getContentPane().setLayout(new BorderLayout());
		
		enumerate = new GenPlotEnumerator(Plot);
		graph = new GeneratorGraph(enumerate.plotDefault(2), null, null);
		getContentPane().add(graph, BorderLayout.CENTER);
		controlPanel.setPreferredSize(new Dimension(300, 60));
		
		getContentPane().add(controlPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_controlPanel = new GridBagLayout();
		gbl_controlPanel.columnWidths = new int[]{38, 41, 32, 85, 300, 61, 77, 0, 0, 0, 0, 0, 0, 0};
		gbl_controlPanel.rowHeights = new int[]{55, 0};
		gbl_controlPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		controlPanel.setLayout(gbl_controlPanel);
		dateSet.setPreferredSize(new Dimension(170, 20));
		dateSet.setAutoscrolls(true);
		dateSet.setToolTipText("select date to display");
		
		dateSet.setFont(new Font("Tahoma", Font.BOLD, 12)); ;
		
		Object[] infoDate = enumerate.GetPlotDates().toArray();
		dateSet.setModel(new DefaultComboBoxModel(infoDate));
		dateSet.setSelectedIndex(infoDate.length-1);
		
		nxt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				lowerLimit = enumerate.getLowerLimit();
				upperLimit = enumerate.getUpperLimit();
				
				graph.setVisible(false);
				BulkGraphControl.this.remove(graph);
				hrs = hours.getValue();
				System.out.println("Now Prev:"+hrs+" LowerLimit="+lowerLimit+", upperLimit="+upperLimit);
				graph = new GeneratorGraph(enumerate.plotPace(true, hrs, upperLimit, lowerLimit), null, null);
				graph.setVisible(true);
				getContentPane().add(graph);
				
			}
			
			public void mouseEntered(MouseEvent arg0) {
				nxt.setText("Go Forward");
			}
			
			public void mouseExited(MouseEvent arg0) {
				nxt.setText("Next");
			}
		});
		
		refresh.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				compress = true;
				graph.setVisible(false);
				BulkGraphControl.this.remove(graph);
				graph = new GeneratorGraph(enumerate.plotDefault(2), null, null);
				graph.setVisible(true);
				getContentPane().add(graph);
			}
			
			public void mouseEntered(MouseEvent arg0) {
				refresh.setText("Default");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				refresh.setText("Refresh");
			}
		});
		
		
		refresh.setHorizontalTextPosition(SwingConstants.CENTER);
		refresh.setVerticalTextPosition(SwingConstants.BOTTOM);
		refresh.setToolTipText("Sets graph back to last readings");
		refresh.setIcon(new ImageIcon(BulkGraphControl.class.getResource("/espTest1/refresh-32.png")));
		GridBagConstraints gbc_refresh = new GridBagConstraints();
		gbc_refresh.fill = GridBagConstraints.VERTICAL;
		gbc_refresh.insets = new Insets(0, 0, 0, 5);
		gbc_refresh.gridx = 0;
		gbc_refresh.gridy = 0;
		controlPanel.add(refresh, gbc_refresh);
		prv.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				lowerLimit = enumerate.getLowerLimit();
				upperLimit = enumerate.getUpperLimit();
				
				graph.setVisible(false);
				BulkGraphControl.this.remove(graph);
				hrs = hours.getValue();
				System.out.println("Now Prev:"+hrs+" LowerLimit="+lowerLimit+", upperLimit="+upperLimit);
				graph = new GeneratorGraph(enumerate.plotPace(false, hrs, upperLimit, lowerLimit), null, null);
				graph.setVisible(true);
				getContentPane().add(graph);
				
			}
			
			public void mouseEntered(MouseEvent arg0) {
				prv.setText("Scroll Back");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				prv.setText("Previous");
			}
		});
		
		
		prv.setHorizontalTextPosition(SwingConstants.CENTER);
		prv.setVerticalTextPosition(SwingConstants.BOTTOM);
		prv.setToolTipText("Read graph backwards");
		prv.setIcon(new ImageIcon(BulkGraphControl.class.getResource("/espTest1/chevron_left-32.png")));
		GridBagConstraints gbc_prv = new GridBagConstraints();
		gbc_prv.anchor = GridBagConstraints.EAST;
		gbc_prv.fill = GridBagConstraints.VERTICAL;
		gbc_prv.insets = new Insets(0, 0, 0, 5);
		gbc_prv.gridx = 1;
		gbc_prv.gridy = 0;
		controlPanel.add(prv, gbc_prv);
		
		
		nxt.setVerticalTextPosition(SwingConstants.BOTTOM);
		nxt.setHorizontalTextPosition(SwingConstants.CENTER);
		nxt.setToolTipText("Read graph forward");
		nxt.setIcon(new ImageIcon(BulkGraphControl.class.getResource("/espTest1/chevron_right-32.png")));
		GridBagConstraints gbc_nxt = new GridBagConstraints();
		gbc_nxt.anchor = GridBagConstraints.WEST;
		gbc_nxt.fill = GridBagConstraints.VERTICAL;
		gbc_nxt.insets = new Insets(0, 0, 0, 5);
		gbc_nxt.gridx = 2;
		gbc_nxt.gridy = 0;
		controlPanel.add(nxt, gbc_nxt);
		lblTimeFramehrs.setIcon(new ImageIcon(BulkGraphControl.class.getResource("/espTest1/property_time-32.png")));
		lblTimeFramehrs.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTimeFramehrs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeFramehrs.setVerticalTextPosition(SwingConstants.BOTTOM);
		GridBagConstraints gbc_lblTimeFramehrs = new GridBagConstraints();
		gbc_lblTimeFramehrs.fill = GridBagConstraints.BOTH;
		gbc_lblTimeFramehrs.insets = new Insets(0, 0, 0, 5);
		gbc_lblTimeFramehrs.gridx = 3;
		gbc_lblTimeFramehrs.gridy = 0;
		controlPanel.add(lblTimeFramehrs, gbc_lblTimeFramehrs);
		hours.setSnapToTicks(true);
		hours.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				hrs = hours.getValue();		
				compress = true;
				graph.setVisible(false);
				BulkGraphControl.this.remove(graph);
				graph = new GeneratorGraph(enumerate.plotDefault(hrs), null, null);
				graph.setVisible(true);
				getContentPane().add(graph);
			}
		});
		
		
		hours.setToolTipText("Adjust to get required time frame");
		hours.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		hours.setBorder(new LineBorder(new Color(173, 216, 230), 1, true));
		hours.setBackground(new Color(230, 230, 250));
		
		hours.setMaximum(24);
		hours.setMinimum(0);
		hours.setMajorTickSpacing(3);
		hours.setMinorTickSpacing(1);
		hours.setPaintLabels(true);
		hours.setPaintTicks(true);
		hours.setPaintTrack(true);
		hours.setPreferredSize(new Dimension(300, 40));
		hours.setValue(1);
		hours.setName("Time Frame");
		GridBagConstraints gbc_hours = new GridBagConstraints();
		gbc_hours.gridwidth = 2;
		gbc_hours.fill = GridBagConstraints.BOTH;
		gbc_hours.insets = new Insets(0, 0, 0, 5);
		gbc_hours.gridx = 4;
		gbc_hours.gridy = 0;
		controlPanel.add(hours, gbc_hours);
		
		lblOpenReadings.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});
		
		
		dateSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date choice = (Date) dateSet.getSelectedItem();
				graph.setVisible(false);
				BulkGraphControl.this.remove(graph);
				graph = new GeneratorGraph(enumerate.specifiedDatePlot(choice), null,null);
				graph.setVisible(true);
				getContentPane().add(graph);
			}
		});
		
		//dateSet.addItem(enumerate.GetPlotDates());
		GridBagConstraints gbc_dateSet = new GridBagConstraints();
		gbc_dateSet.gridwidth = 4;
		gbc_dateSet.insets = new Insets(0, 0, 0, 5);
		gbc_dateSet.gridx = 6;
		gbc_dateSet.gridy = 0;
		controlPanel.add(dateSet, gbc_dateSet);
		
		
		lblOpenReadings.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblOpenReadings.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOpenReadings.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblOpenReadings.setIcon(new ImageIcon(BulkGraphControl.class.getResource("/espTest1/insert_table-32.png")));
		GridBagConstraints gbc_lblOpenReadings = new GridBagConstraints();
		gbc_lblOpenReadings.gridwidth = 2;
		gbc_lblOpenReadings.fill = GridBagConstraints.BOTH;
		gbc_lblOpenReadings.gridx = 10;
		gbc_lblOpenReadings.gridy = 0;
		controlPanel.add(lblOpenReadings, gbc_lblOpenReadings);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			data = info.loadFromFile(daily100);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BulkGraphControl("g100", data);
			}
		});

	}

}
