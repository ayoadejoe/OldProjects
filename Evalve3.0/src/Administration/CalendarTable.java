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

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import baseGui.TchChatBox;

public class CalendarTable extends JPanel{
	private JTable Table;
	private CalTableModel TableModel;
	private Font font = new Font("SERIF", Font.BOLD, 13);
	private int increase, d, e;
	private JPopupMenu popup;
	private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	private BasicInfoClient deleter;
	public CalendarTable() {
		Color GroundColor = new Color(100, 250, 100);
		Border innerBorder2 = BorderFactory.createEtchedBorder(20, Color.ORANGE, Color.GRAY);
		Border outerBorder2 = BorderFactory.createRaisedSoftBevelBorder();
		AbstractButton createCompoundBorder2;
		setBorder(BorderFactory.createCompoundBorder(innerBorder2, outerBorder2));
		setBackground(Color.white);
		TableModel = new CalTableModel();
		Table = new JTable(TableModel);
		Table.setRowHeight(30);
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    Table.getColumnModel().getColumn(0).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(1).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(2).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(3).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(4).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(5).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(6).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(7).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(8).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(9).setPreferredWidth(100);
	    Table.getColumnModel().getColumn(10).setPreferredWidth(100);
	    Table.setBackground(Color.pink);
		setLayout(new BorderLayout());
		JScrollPane tesT1 = new JScrollPane(Table);
		tesT1.getPreferredSize();
	    Dimension dim2 = tesT1.getPreferredSize();
		dim2.height = 340;
		dim2.width = 870;
		tesT1.setPreferredSize(dim2);
		tesT1.setForeground(Color.magenta);
		tesT1.setAutoscrolls(true);
		tesT1.getViewport().add(Table, BorderLayout.CENTER);
		tesT1.setViewportView(Table);
	    this.add(tesT1, BorderLayout.CENTER); 
	    
	    Table.addMouseListener(new MouseAdapter(){
	    	Object cellValue;
			public void mousePressed(MouseEvent r) {
				Point point = Table.getMousePosition();
			    d = Table.rowAtPoint(point);
				e = Table.columnAtPoint(point);
				if(r.getButton()==MouseEvent.BUTTON3){
					 popup = new JPopupMenu();
					    cellValue = Table.getModel().getValueAt(Table.rowAtPoint(r.getPoint()), 
					    		0);
					    menuItem1 = new JMenuItem("Destroy this plan");
					    menuItem4 = new JMenuItem("Print Table");
					    popup.add(menuItem1);popup.add(menuItem4); 
					popup.show(Table, r.getX(), r.getY());
					
					menuItem1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent w) {
							deleter = new BasicInfoClient();
							deleter.termCheck("cellvalue, "+cellValue.toString());
							JOptionPane.showMessageDialog(null, "Row "+cellValue+" deleted. You " +
							"will have to refresh this page to view changes. ");
						}
				    });
					menuItem4.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(null, "Table will be printed");
						}
						
					});
				}
			}
	    });
	}
	public void refresh(){
		TableModel.fireTableDataChanged();
	}

}
