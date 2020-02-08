package baseGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

public class TchChatTable extends JFrame{
	private JTable table;
	private TchalertPanel alert;
	private TchChatTableModel tableModel;
	private File nxtPage = new File("numbering");
	private TchChatBox catP;			private Timer tt = null;
	private int increase, d, e;
	private JPopupMenu popup;
	private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	public TchChatTable(final int iD, final String username, final String password) {
		super("CHAT-EVALVE");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		Color GroundColor = new Color(100, 100, 200);
		setBackground(GroundColor);
		alert = new TchalertPanel(iD, username, password);
		tableModel = new TchChatTableModel(iD, username, password);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.getColumnModel().getColumn(0).setPreferredWidth(40);
	    table.getColumnModel().getColumn(1).setPreferredWidth(120);
	    table.getColumnModel().getColumn(2).setPreferredWidth(120);
	    table.getColumnModel().getColumn(3).setPreferredWidth(120);
	    int yt = table.getColumnModel().getTotalColumnWidth();
	    table.setBackground(GroundColor);
		setLayout(new BorderLayout());
		final JScrollPane tesT1 = new JScrollPane(table);
		tesT1.getPreferredSize();
	    Dimension dim2 = tesT1.getPreferredSize();
	    double ht = table.getRowCount()*(2.5);
	    if(ht<3){
	    	ht = 4;
	    }
	    if(ht>28){ht = 28;}
		dim2.height = (int) (table.getRowHeight()*(ht));
		dim2.width = yt+80;
		tesT1.setPreferredSize(dim2);
		tesT1.setAutoscrolls(true);
		tesT1.getViewport().add(table, BorderLayout.CENTER);
		tesT1.setViewportView(table);
		tesT1.revalidate();
		tesT1.repaint();
	    this.add(tesT1, BorderLayout.CENTER); 
	    this.add(alert, BorderLayout.SOUTH);
	    setSize(yt+80, (int)(table.getRowHeight()*(ht)));
	    setLocationRelativeTo(getParent());
		setResizable(false);
	    setVisible(true);
	    
	    tt = new Timer(5000, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				remove(alert);
				revalidate();
					alert = new TchalertPanel(iD, username, password);
					add(alert, BorderLayout.SOUTH);
					revalidate();
			}
	    });
		
	    tt.start();
	    
	    table.addMouseListener(new MouseAdapter(){
	    	Object cellValue;
			public void mousePressed(MouseEvent r) {
				Point point = table.getMousePosition();
			    d = table.rowAtPoint(point);
				e = table.columnAtPoint(point);
				String rowVal = (String) table.getValueAt(d, e);
				if(r.getButton()==MouseEvent.BUTTON3){
					 popup = new JPopupMenu();
					    cellValue = table.getModel().getValueAt(table.rowAtPoint(r.getPoint()), 
					    		table.columnAtPoint(r.getPoint()));
					    menuItem1 = new JMenuItem("Chat with "+cellValue.toString());
					    menuItem4 = new JMenuItem("Print Table");
					    popup.add(menuItem1);popup.add(menuItem4); 
					popup.show(table, r.getX(), r.getY());
					
					menuItem1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent w) {
								String friend = cellValue.toString().trim();
								//(friend);
								String self = username.toUpperCase();
								new TchThreader(self, friend, "chat");
						}
				    });
					menuItem4.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(null, "Table will be printed");
						}
						
					});
				}
			}


			public void mouseClicked(MouseEvent r){
				if (r.getClickCount() == 2) {
					Point point = table.getMousePosition();
				    d = table.rowAtPoint(point);
					e = table.columnAtPoint(point);
					String rowVal = (String) table.getValueAt(d, e);
					cellValue = table.getModel().getValueAt(table.rowAtPoint(r.getPoint()), 
				    		table.columnAtPoint(r.getPoint()));
					
					String friend = cellValue.toString().trim();
					//(friend);
					String self = username.toUpperCase();
					if (friend.equalsIgnoreCase(self)){
						Toolkit.getDefaultToolkit().beep();
					}else{
					new TchThreader(self, friend, "chat");
					}
				}
			}


	    	
	    });
	    
	    addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				tt.stop();
			}
	    	
	    });
	}
	public void refresh(){
		tableModel.fireTableDataChanged();
	}
	
}
