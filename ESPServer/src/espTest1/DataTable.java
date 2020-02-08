package espTest1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class DataTable extends JPanel {
	private JTable Table;
	private DefaultTableModel model;
	private int increase, d, f, stops;
	private Vector <Object> Data = new Vector <Object> ();
	private Vector <Object> Extract = new Vector <Object> ();
	private String[] Headings;
	private JPopupMenu go = new JPopupMenu(); int s = 0;
	private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;
	private EspDerby derby = new EspDerby();
	private ResultSet sett;
	private ResultSetMetaData meta;
	private int colum;
	public DataTable() {
		setLayout(new BorderLayout(0, 0));
		
		Table = new JTable();

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		sett =derby.accessDerby("RawDataTable");
		
		 try {
			    meta = sett.getMetaData();
			    colum = meta.getColumnCount();
			    Headings = new String[colum];
			    int t=1;
			    while(t<=colum){
			    	 Headings[t-1]=meta.getColumnName(t);
			    	t++;
			    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			    
	    
	    try {
			while(sett.next()){
				for(int q = 1; q<=colum; q++){
					Data.add(sett.getObject(q));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
		model = new DefaultTableModel(Headings, 0);
		menuItem1 = new JMenuItem("");
		menuItem2 = new JMenuItem(""); 
		menuItem3 = new JMenuItem("");
		menuItem4 = new JMenuItem("");
		menuItem5 = new JMenuItem("");
		go.add(menuItem1);go.add(menuItem5); go.add(menuItem2); go.add(menuItem3); go.add(menuItem4); 
		Table = new JTable(model);
		Table.setRowHeight(25);
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Table.getColumnModel().getColumn(0).setPreferredWidth(10);
	    Table.getColumnModel().getColumn(1).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(2).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(3).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(4).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(5).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(6).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(7).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(8).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(9).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(10).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(11).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(12).setPreferredWidth(150);
	    
	    Table.setBackground(new Color(150, 150, 250));
	    
		JScrollPane tesT1 = new JScrollPane(Table);
		tesT1.getPreferredSize();
	    Dimension dim2 = tesT1.getPreferredSize();
		dim2.height = Table.getRowHeight();
		dim2.width = 550;
		tesT1.setPreferredSize(dim2);
		tesT1.setAutoscrolls(true);
		tesT1.getViewport().add(Table, BorderLayout.CENTER);
		tesT1.setViewportView(Table);
	    this.add(tesT1, BorderLayout.CENTER); 
	    int DataSize = Data.size(); 
	    //(DataSize+", "+colum);
	    int round = DataSize/colum; int cover = DataSize; int t =1;
	    while(t<=round){
	    	Extract = getVector(Data, colum);
	    model.addRow(Extract); 
	    t++;
	    }
	    
	}
	
	 private Vector<Object> getVector(Vector<Object> data, int colum) {
		  Vector vector = new Vector(); int h = data.size(); int r = 0;
		while(h>s){
			r++; 
			vector.add(data.get(s));
			s++;
		if(r==colum){
			//("vector: "+vector);
			return vector;
		}
		}
		return null;
	}

}
