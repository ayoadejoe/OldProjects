package Administration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import baseGui.LessonPlanEdit;
import baseGui.Termer;

public class DiaryViewrTable extends JFrame{
	private JTable DiaryTable; int q=0, IDNo = 0, wk = 0;
	private DiaryViewTableModel DiaryTableModel;
	private Font font = new Font("SERIF", Font.BOLD, 13);
	private Termer tem = new Termer();
	int increase, d, e;
	private JPopupMenu popup;
	private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	private String Subject = null,  Clasz = null,  Username = null, term = null, topic = null;
	public DiaryViewrTable() {
		super("SUBMITTED DIARIES");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		Color GroundColor = new Color(100, 250, 100);
		setBackground(Color.GREEN);
		setSize(850, 600);
		setVisible(true);
		setLocationRelativeTo(getParent());
		term = tem.chckTerm().toLowerCase();
		try{
		DiaryTableModel = new DiaryViewTableModel(term);
		DiaryTable = new JTable(DiaryTableModel);
		DiaryTable.setRowHeight(30);
		DiaryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    DiaryTable.getColumnModel().getColumn(0).setPreferredWidth(50);
	    DiaryTable.getColumnModel().getColumn(2).setPreferredWidth(200);
	    DiaryTable.getColumnModel().getColumn(3).setPreferredWidth(440);
	    DiaryTable.getColumnModel().getColumn(4).setPreferredWidth(50);
	    DiaryTable.setBackground(Color.ORANGE);
	    DiaryTable.setFont(new Font("arial", Font.PLAIN, 13));
	    int yt = DiaryTable.getColumnModel().getTotalColumnWidth();
	    DiaryTable.setBackground(Color.ORANGE);
		setLayout(new BorderLayout());
		JScrollPane tesT1 = new JScrollPane(DiaryTable);
		tesT1.getPreferredSize();
	    Dimension dim2 = tesT1.getPreferredSize();
	    double ht = DiaryTable.getRowCount()*(1.4);
		dim2.height = 550;
		dim2.width = 800;
		tesT1.setPreferredSize(dim2);
		tesT1.setAutoscrolls(true);
		tesT1.getViewport().add(DiaryTable, BorderLayout.CENTER);
		tesT1.setViewportView(DiaryTable);
	    this.add(tesT1, BorderLayout.CENTER); 
	    //("Diary ColumnCount: "+DiaryTable.getColumnCount());
		}catch(Exception g){
			JOptionPane.showMessageDialog(null, "There is a problem here >"+g.getMessage());
		}
	    DiaryTable.addMouseListener(new MouseAdapter(){
	    	Object cellValue = null;
			public void mousePressed(MouseEvent r) {
				Point point = DiaryTable.getMousePosition();
			    d = DiaryTable.rowAtPoint(point);
				e = DiaryTable.columnAtPoint(point);
				if(r.getButton()==MouseEvent.BUTTON3){
					 popup = new JPopupMenu();
					    cellValue = DiaryTable.getModel().getValueAt(DiaryTable.rowAtPoint(r.getPoint()), 
					    		DiaryTable.columnAtPoint(r.getPoint()));
					    menuItem1 = new JMenuItem("Print Diary");
					    menuItem2 = new JMenuItem("Preview Lesson Plan for: "+ cellValue.toString());
					    popup.add(menuItem1);popup.add(menuItem2);
					popup.show(DiaryTable, r.getX(), r.getY());
					
					menuItem1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent w) {
						//Print out instructions
						}
				    });
					menuItem2.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
							String Claz = (String) DiaryTable.getModel().getValueAt(d, 
						    		1);
							String Subjct = (String) DiaryTable.getModel().getValueAt(d, 
						    		2);
							String Topic = (String) DiaryTable.getModel().getValueAt(d, 
						    		3);
							int Week = Integer.parseInt((DiaryTable.getModel().getValueAt(d, 
						    		0)).toString().trim());
							topic = Topic;		Clasz = Claz;
							wk = Week;
							Subject = Subjct; Username = "Admin View"; IDNo = 0;
							 //("Lesson : "+Subject+", "+ Clasz+", "+topic+", "+wk+", "+term);
							new LessonPlanEdit( null, IDNo, Subject,  Clasz,  Topic, wk,  Username, term);
						}
						
					});
				}
			}
	    	
	    });
	    
		
	}

}
