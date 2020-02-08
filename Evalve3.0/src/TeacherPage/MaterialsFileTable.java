package TeacherPage;

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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import Administration.AccountClient;
import Administration.BalanceTableModel;

public class MaterialsFileTable extends JFrame{
	private JTable Table;
	private DefaultTableModel model;
	private int increase, d, f;
	private Vector <Object> Data = new Vector <Object> ();
	private Vector <Object> Extract = new Vector <Object> ();
	private DiaryEssayClient acquire = new DiaryEssayClient();
	private String[] Headings = new String[]{"No", "Subject", "Class", "File Name", "File Type", "File Size", "Date Uploaded"};
	private JPopupMenu go = new JPopupMenu(); int s = 0;
	private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;
	public MaterialsFileTable() {
		super("FILES ON THE SERVER");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
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
		setUndecorated(false);
		Data = acquire.UploadGet();
		getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
		setVisible(true);
		setSize(650, 400);
		model = new DefaultTableModel(Headings, 0);
		menuItem1 = new JMenuItem("");
		menuItem2 = new JMenuItem(""); 
		menuItem3 = new JMenuItem("");
		menuItem4 = new JMenuItem("");
		menuItem5 = new JMenuItem("");
		go.add(menuItem1);go.add(menuItem5); go.add(menuItem2); go.add(menuItem3); go.add(menuItem4); 
		Table = new JTable(model);
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Table.setRowHeight(25);
	    Table.getColumnModel().getColumn(1).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(3).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(4).setPreferredWidth(70);
	    Table.getColumnModel().getColumn(6).setPreferredWidth(100);
	    Table.setBackground(new Color(150, 150, 250));
		setLayout(new BorderLayout());
		setLocationRelativeTo(getParent());
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
	    int DataSize = Data.size(); int colum = 7;
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
				Object rowVal = (Object) Table.getValueAt(d, f);
				if(e.getButton() == 3){
					cellValue = Table.getModel().getColumnName(f);
					menuItem1.setText("Download and View "+rowVal.toString());
					go.show(Table, e.getX(), e.getY());
					
					menuItem1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent w) {
							Object no = (Object) Table.getValueAt(d, 
						    		0);
							Object subject = (Object) Table.getValueAt(d, 
						    		1);
							Object clasx = (Object) Table.getValueAt(d, 
						    		2);
							Object filetype = (Object) Table.getValueAt(d, 
						    		4);
							Object filename = (Object) Table.getValueAt(d, 
						    		3);
							String ServerDirectory = "C:\\NewEvalve\\UploadedFiles\\"+clasx.toString().trim()+"\\"+subject.toString().trim();
							String FileName = filename.toString().trim();
							String LocalDirectory = "C:\\UploadedFiles\\"+clasx+"\\"+subject;
							new ViewerThread(ServerDirectory,filetype.toString(), FileName, LocalDirectory);
						}
				    });
				}
			}
		  });
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
