package Administration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class Master extends JFrame{
	private JLabel heading;
	private JTable Table;
	private MasterTableModel TableModel;
	private ResultClient msc = new ResultClient("master");
	private Font BigBoldBlack = new Font("SERIF", Font.BOLD, 24);
	private Color GroundColor = new Color(100, 200, 100);
	public Master() {
		super("MASTER SHEET");
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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(true);
		setBackground(GroundColor);
		setLayout(new BorderLayout());
		Color GroundColor = new Color(100, 250, 100);
		Border innerBorder2 = BorderFactory.createEtchedBorder(20, Color.ORANGE, Color.GRAY);
		Border outerBorder2 = BorderFactory.createRaisedSoftBevelBorder();
		AbstractButton createCompoundBorder2;
		setBackground(Color.white);
		TableModel = new MasterTableModel();
		Table = new JTable(TableModel);
		Table.setRowHeight(30);
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int count =  Table.getColumnModel().getColumnCount();
		int f = 3; int ded = count-4;
		for (int g = 2; count>f; count --){g++;
	    Table.getColumnModel().getColumn(g).setPreferredWidth(350);
	    if(count == ded){break;}
		}
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
	}

}
