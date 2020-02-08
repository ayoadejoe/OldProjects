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
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import baseGui.Evalve;

public class TeachTable extends JFrame{
	private JTable table;
	private TTableModel tableModel;
	private TeacherClient teach = new TeacherClient();
	private File nxtPage = new File("numbering");
	int increase, d, e;
	private JPopupMenu popup;
	private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	public TeachTable(final Vector ReadyQuestions, final String term) {
		super("LOADED QUESTIONS");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		Color GroundColor = new Color(0, 80, 0);
		tableModel = new TTableModel(ReadyQuestions);
		table = new JTable(tableModel);
		table.setForeground(Color.black);
		table.setFont(new Font("arial", Font.BOLD, 13));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(25);
	    table.getColumnModel().getColumn(0).setPreferredWidth(30);
	    table.getColumnModel().getColumn(1).setPreferredWidth(30);
	    table.getColumnModel().getColumn(2).setPreferredWidth(150);
	    table.getColumnModel().getColumn(3).setPreferredWidth(40);
	    table.getColumnModel().getColumn(4).setPreferredWidth(130);
	    table.getColumnModel().getColumn(5).setPreferredWidth(100);
	    table.getColumnModel().getColumn(6).setPreferredWidth(80);
	    table.getColumnModel().getColumn(7).setPreferredWidth(80);
	    table.getColumnModel().getColumn(8).setPreferredWidth(70);
	    table.getColumnModel().getColumn(9).setPreferredWidth(70);
	    table.getColumnModel().getColumn(10).setPreferredWidth(150);
	    table.getColumnModel().getColumn(11).setPreferredWidth(40);
	    table.getColumnModel().getColumn(14).setPreferredWidth(200);
	    table.setBackground(GroundColor);
	    int yt = table.getColumnModel().getTotalColumnWidth();
	    table.setBackground(Color.white);
		setLayout(new BorderLayout());
		JScrollPane tesT1 = new JScrollPane(table);
		tesT1.getPreferredSize();
	    Dimension dim2 = tesT1.getPreferredSize();
	    double ht = table.getRowCount()*(1.3);
	    if(ht>15){ht = 15;}
		dim2.height = (int) (table.getRowHeight()*(ht));
		dim2.width = yt-200;
		tesT1.setPreferredSize(dim2);
		tesT1.setAutoscrolls(true);
		tesT1.getViewport().add(table, BorderLayout.CENTER);
		tesT1.setViewportView(table);
		tesT1.revalidate();
		tesT1.repaint();
	    this.add(tesT1, BorderLayout.CENTER); 
	    setSize(1050, 670);
	    setLocationRelativeTo(getParent());
		setResizable(false);
	    setVisible(true);
	    
	    table.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent r) {
				Point point = table.getMousePosition();
			    d = table.rowAtPoint(point);
				e = table.columnAtPoint(point);
				if(r.getButton()==MouseEvent.BUTTON3){
					 popup = new JPopupMenu();
					    Object cellValue = table.getModel().getValueAt(table.rowAtPoint(r.getPoint()), 
					    		table.columnAtPoint(r.getPoint()));
					    menuItem1 = new JMenuItem("Preview "+cellValue.toString());
					    menuItem2 = new JMenuItem("Delete "+cellValue.toString());
					    menuItem3 = new JMenuItem("Refresh Table");
					    menuItem4 = new JMenuItem("Print Table");
					    popup.add(menuItem1);popup.add(menuItem2);popup.add(menuItem3);popup.add(menuItem4); 
					popup.show(table, r.getX(), r.getY());
					
					menuItem1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent w) {
							int No = Integer.parseInt(table.getModel().getValueAt(d, 
						    		0).toString().trim());
							String Subject = (String) table.getModel().getValueAt(d, 
						    		2);
							String ClassS = (String) table.getModel().getValueAt(d, 
						    		3);
							String ExamType = (String) table.getModel().getValueAt(d, 
						    		5);
							String EvalType = (String) table.getModel().getValueAt(d, 
						    		4);
							String filenam = (String) table.getModel().getValueAt(d, 
						    		11);
							String weight = (String) table.getModel().getValueAt(d, 
						    		12);
							String week = (String) table.getModel().getValueAt(d, 
						    		13);
							String topic = (String) table.getModel().getValueAt(d, 
						    		14);
							String username = (String) table.getModel().getValueAt(d, 
						    		15);
							String filename = "C:\\LocalSubjects\\"+Subject.trim()+"\\"+EvalType.trim()+"\\"+filenam+".evl";
								//(filename);
								File toPreview = new File(filename);
								if(!toPreview.exists()){
									JOptionPane.showMessageDialog(null, "You have not loaded the question. It can't be found in the directory. Please set question again.");
									String ServerCopy = "C:\\Subjects\\"+Subject.trim()+"\\"+EvalType.trim()+"\\"+filenam+".evl";
									String delQuery = "DELETE FROM "+Subject.trim()+" WHERE No = '"+No+"' AND Week = '"+week+"';";
									teach.DeleteWork( delQuery, term, ServerCopy);
									JOptionPane.showMessageDialog(null, "Empty Week "+week+" work is deleted. Refresh to view changes.");
									return;
								}else{
								try {
									new Evalve(filename, 0, Subject, ClassS, ExamType, weight, week, topic, username, EvalType);
								} catch (IOException e1) {
									e1.printStackTrace();
								}}
						}
				    });
					menuItem2.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
							String No = (String) table.getModel().getValueAt(d, 
						    		0);
							String Subject = (String) table.getModel().getValueAt(d, 
						    		2);
							String week = (String) table.getModel().getValueAt(d, 
						    		13);
							String EvalType = (String) table.getModel().getValueAt(d, 
						    		4);
							String filenam = (String) table.getModel().getValueAt(d, 
						    		11);
							String filename = "C:\\LocalSubjects\\"+Subject.trim()+"\\"+EvalType.trim()+"\\"+filenam+".evl";
							File toDelete = new File(filename);
							if(toDelete.exists()){
								toDelete.delete();
							}
							String ServerCopy = "C:\\Subjects\\"+Subject.trim()+"\\"+EvalType.trim()+"\\"+filenam+".evl";
							String delQuery = "DELETE FROM "+Subject.trim()+" WHERE No = '"+No+"' AND Week = '"+week+"';";
							teach.DeleteWork( delQuery, term, ServerCopy);
							JOptionPane.showMessageDialog(null, "Week "+week+" is deleted. Refresh to view changes.");
						}
						
					});
				}
			}
	    	
	    });
	    if(nxtPage.exists()){
			nxtPage.delete();
		}
	    return;
		
	}
	public void refresh(){
		tableModel.fireTableDataChanged();
	}
	
}
