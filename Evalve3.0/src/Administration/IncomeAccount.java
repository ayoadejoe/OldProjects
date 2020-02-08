package Administration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IncomeAccount extends JFrame{
	private JTable Table;
	private IncomeTableModel model;
	private int increase, d, f;
	private Vector <Object> Headings =new Vector <Object>();
	private Vector <Object> Data = new Vector <Object> ();
	private Vector <Object> Extract = new Vector <Object> ();
	private AccountClient acquire = new AccountClient();
	private JPopupMenu go = new JPopupMenu(); int s = 0;
	private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;
	public IncomeAccount() {
		super("INCOME ACCOUNTS");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		setUndecorated(true);
		Headings = acquire.Account("IncomeHeader");
		Data = acquire.Account("IncomeData");
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		setVisible(true);
		setSize(850, 400);
		model = new IncomeTableModel(Headings, 0);
		menuItem1 = new JMenuItem("");
		menuItem2 = new JMenuItem(""); 
		menuItem3 = new JMenuItem("");
		menuItem4 = new JMenuItem("");
		menuItem5 = new JMenuItem("");
		go.add(menuItem1);go.add(menuItem5); go.add(menuItem2); go.add(menuItem3); go.add(menuItem4); 
		Table = new JTable(model);
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Table.setRowHeight(25);
	    //Table.getColumnModel().getColumn(0).setPreferredWidth(30);
	    Table.setBackground(new Color(150, 150, 250));
		setLayout(new BorderLayout());
		JScrollPane tesT1 = new JScrollPane(Table);
		tesT1.getPreferredSize();
	    Dimension dim2 = tesT1.getPreferredSize();
		dim2.height = Table.getRowHeight();
		dim2.width = 700;
		tesT1.setPreferredSize(dim2);
		tesT1.setAutoscrolls(true);
		tesT1.getViewport().add(Table, BorderLayout.CENTER);
		tesT1.setViewportView(Table);
	    this.add(tesT1, BorderLayout.CENTER); 
	    int DataSize = Data.size(); int colum = Headings.size();
	    //(DataSize+", "+colum);
	    int round = DataSize/colum; int cover = DataSize; int t =1;
	    while(t<=round){
	    	Extract = getVector(Data, colum);
	    model.addRow(Extract); 
	    t++;
	    }
	    
	    
	    final Vector <String> yes = new Vector <String> ();
	    
	    Table.addMouseListener(new MouseAdapter(){
	    	Object cellValue;
			public void mouseClicked(MouseEvent e) {
				Point point = Table.getMousePosition();
			    d = Table.rowAtPoint(point);
				f = Table.columnAtPoint(point);
				//String rowVal = (String) Table.getValueAt(d, f);
				if(e.getButton() == 3){
					cellValue = Table.getModel().getColumnName(f);
					menuItem1.setText("Add New Column");
					menuItem3.setText("Add New Row");
					menuItem2.setText("Delete this Row ");
					menuItem4.setText("Print this Table");
					menuItem5.setText("Delete this Column: "+cellValue.toString());
					go.show(Table, e.getX(), e.getY());
					
					menuItem1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent w) {
							String d = JOptionPane.showInputDialog("Enter the name of the Column?").trim();
							model.addColumn(d);
							acquire.alterTable("incomeaccount", "column", d);
							//positionColumn(Table,f);
						}
				    });
					
					 menuItem3.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent w) {
								model.addRow(yes);
								String d = "row";
								acquire.alterTable("incomeaccount", "row", d);
							}
					    });
					 
					 menuItem5.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent w) {
								Table.removeColumn(Table.getColumnModel().getColumn(f));
								//removeColumn(TableColumn);
							}
					    });

					 menuItem2.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent w) {
								model.removeRow(d);
							}
					    });
				}
			}
		  });
	    
	}
	
	  public void positionColumn(JTable table,int col_Index) {
		  table.moveColumn(table.getColumnCount()-1, col_Index);
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
