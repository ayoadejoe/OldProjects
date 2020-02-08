package espTest1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

public class GenDataEditor extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	JTextPane GenEditor = new JTextPane();
	JRadioButton Data100 = new JRadioButton("100kVA Data");
	JPanel panel = new JPanel();
	JRadioButton Data75 = new JRadioButton("75kVA Data");
	JButton enterData = new JButton("Enter Data");
	JTextPane dataBackUp = new JTextPane();
	
	private List<String> plotDates = new ArrayList();
	private List<Object> Plotx = new ArrayList();
	private String dataGen, infoGen, plotDateTime;
	private EspDerby derby = new EspDerby();
	private final JComboBox dateSet = new JComboBox();
	private FileSerializer files = new FileSerializer();
	private List<Object> BackUpData = new ArrayList();
	private DateComparer compare = new DateComparer();
	private File theDay;
	private final JScrollPane GenPane = new JScrollPane();
	private final JScrollPane BackUpPane = new JScrollPane();
	private final JSplitPane splitPane = new JSplitPane();
	
	public static void main(String[] args) {
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

	
	public GenDataEditor() throws SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		
		GenEditor.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		
		GenEditor.setBorder(new TitledBorder(null, "DATABASE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GenEditor.setBackground(new Color(204, 102, 204));
		GenEditor.setText("Generator Data");
		GenPane.setViewportView(GenEditor);
		
		contentPane.add(panel, BorderLayout.SOUTH);
		
		buttonGroup.add(Data100);
		Data100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Data100.isSelected()){
					getWhos("g100");
					GenDataEditor.this.setTitle(infoGen);
				}
			}
		});
		Data100.setSelected(true);
		panel.add(Data100);
		
		buttonGroup.add(Data75);
		Data75.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Data75.isSelected()){
					getWhos("g75");
					GenDataEditor.this.setTitle(infoGen);
				}
			}
		});
		panel.add(Data75);
		
		panel.add(dateSet);
		
		panel.add(enterData);
		
		
		
		dataBackUp.setBorder(new TitledBorder(null, "BACKUP", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		dataBackUp.setBackground(new Color(153, 204, 153));
		dataBackUp.setText("Corresponding Backup");
		BackUpPane.setViewportView(dataBackUp);
		
		splitPane.setLeftComponent(GenPane);
		splitPane.setRightComponent(BackUpPane);
		splitPane.setDividerLocation((this.getWidth()/2));
		
		if(Data100.isSelected()){
			getWhos("g100");
			GenDataEditor.this.setTitle(infoGen);
		}
		
		ResultSet stat = derby.accessDerby("RawDataTable");
		
		while(stat.next()){
				String compTime = stat.getString("COMPUTERTIME");
				Object plo = getStringtoList(stat.getString(dataGen));
				plotDates.add(compTime);
				Plotx.add(plo);
		}
		
		
		Object[] infoDate = plotDates.toArray();
		dateSet.setModel(new DefaultComboBoxModel(infoDate));
		dateSet.setSelectedIndex(infoDate.length-1);
		
		try {
			BackUpData = files.loadFromFile(theDay);
			dataBackUp.setText(BackUpData.toString());
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		
		dateSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String day = (((String) dateSet.getSelectedItem()).trim());
				GenEditor.setBorder(new TitledBorder(null, "DATABASE: "+day, TitledBorder.LEADING, TitledBorder.TOP, null, null));
				
				Date init=null;
				try {
					init = new SimpleDateFormat("dd-MM-yy").parse(day);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				plotDateTime = day;
				List<Object> plot=  (List<Object>) Plotx.get(dateSet.getSelectedIndex());
				GenEditor.setText(plot.toString());
				GenDataEditor.this.revalidate();
				
				try {
					BackUpData = files.loadFromFile(theDay);
					List<Object> daySelected = compare.getDataOnDay(BackUpData, init);
					String backUpDate = daySelected.get(0).toString();
					daySelected.remove(0);
					dataBackUp.setBorder(new TitledBorder(null, "BACKUP: "+backUpDate, TitledBorder.LEADING, TitledBorder.TOP, null, null));
					dataBackUp.setText(daySelected.toString());
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				splitPane.setDividerLocation((GenDataEditor.this.getWidth()/2));
				GenDataEditor.this.revalidate();
			}
		});
		
		this.addWindowFocusListener(new WindowAdapter() {

			public void windowStateChanged(WindowEvent arg0) {
				super.windowStateChanged(arg0);
				System.out.println(arg0.COMPONENT_RESIZED+" ....");
			}
			
			
		});
		
	}

	


	private List<Object> getStringtoList(String string) {
		List<String> info = Arrays.asList(string.split(">"));
		List<Object> vals = new ArrayList<Object>();
		for(int d=0; d<info.size(); d++){
			try{
			vals.add(Integer.parseInt(info.get(d)));
			}catch(NumberFormatException g){
				vals.add(info.get(d));
			}
		}
		return vals;
	}
private String getWhos(String toPlot) {
		
		switch(toPlot){
		case "g75":
			dataGen = "SMALLGENFUEL";
			infoGen = "Gen 75kVA Fuel Drop (Data)";
			theDay = new File("75kVA Fuel Log");
			break;
		case "g100":
			dataGen = "BIGGENFUEL";
			infoGen = "Gen 100kVA Fuel Drop (Data)";
			theDay = new File("100kVA Fuel Log");
			break;
			default:
				break;
		}
		
		return toPlot;
	}
}
