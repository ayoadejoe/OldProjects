package TeacherPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import baseGui.TchChatBox;
import baseGui.DiaryPreviewer;
import baseGui.LessonPlan;
import baseGui.LessonPlanEdit;

public class DiaryTable extends JPanel{
	private JTable DiaryTable; int q=0, IDNo = 0, wk = 0;
	private DiaryTableModel DiaryTableModel;
	private Font font = new Font("SERIF", Font.BOLD, 13);
	int increase, d, e;
	private JPopupMenu popup;
	private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	private String Subject = null,  Clasz = null,  Username = null, term = null, topic = null;
	public DiaryTable(int iDNo, int sub, String username, String Class, String subject, String Term, int w) {
		Color GroundColor = new Color(100, 250, 100);
		Border innerBorder2 = BorderFactory.createEtchedBorder(20, Color.GREEN, Color.GRAY);
		Border outerBorder2 = BorderFactory.createRaisedSoftBevelBorder();
		AbstractButton createCompoundBorder2;
		setBorder(BorderFactory.createCompoundBorder(innerBorder2, outerBorder2));
		setBackground(Color.GREEN);
		Subject = subject; Clasz = Class; Username = username; term = Term; IDNo = iDNo;
		DiaryTableModel = new DiaryTableModel(iDNo,   sub,   username,   Class,   subject, Term, w);
		DiaryTable = new JTable(DiaryTableModel);
		DiaryTable.setRowHeight(30);
		DiaryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    DiaryTable.getColumnModel().getColumn(0).setPreferredWidth(50);
	    DiaryTable.getColumnModel().getColumn(1).setPreferredWidth(730);
	    DiaryTable.getColumnModel().getColumn(2).setPreferredWidth(60);
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
					    menuItem1 = new JMenuItem("Create Topic Lesson Plan for: "+ cellValue.toString());
					    menuItem2 = new JMenuItem("Preview Lesson Plan for: "+ cellValue.toString());
					    popup.add(menuItem1);popup.add(menuItem2);
					popup.show(DiaryTable, r.getX(), r.getY());
					
					menuItem1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent w) {
							String Topic = (String) DiaryTable.getModel().getValueAt(d, 
						    		1);
							int Week = Integer.parseInt((DiaryTable.getModel().getValueAt(d, 
						    		0)).toString().trim());
							topic = Topic;
							wk = Week;
							new LessonPlan( null, IDNo, Subject,  Clasz,  Topic, Week,  Username, term);
						}
				    });
					menuItem2.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
							String Topic = (String) DiaryTable.getModel().getValueAt(d, 
						    		1);
							int Week = Integer.parseInt((DiaryTable.getModel().getValueAt(d, 
						    		0)).toString().trim());
							topic = Topic;
							wk = Week;
							 //("Lesson : "+Subject+", "+ Clasz+", "+topic+", "+wk+", "+term);
							new LessonPlanEdit(null, IDNo, Subject,  Clasz,  Topic, Week,  Username, term);
						}
					});
				}
			}
	    	
	    });
	    
		
	}

}
