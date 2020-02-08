package Administration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class StudentRegTable extends JPanel{
	private JTable Table;
	private StudentRegTableModel TableModel;
	private Font font = new Font("SERIF", Font.BOLD, 13);
	public StudentRegTable(Vector stud) {
		Color GroundColor = new Color(100, 250, 100);
		Dimension dim3 = getPreferredSize();
		dim3.height = 600;
		dim3.width = 700;
		setPreferredSize(dim3);
		Border innerBorder2 = BorderFactory.createEtchedBorder(20, Color.ORANGE, Color.GRAY);
		Border outerBorder2 = BorderFactory.createRaisedSoftBevelBorder();
		AbstractButton createCompoundBorder2;
		setBorder(BorderFactory.createCompoundBorder(innerBorder2, outerBorder2));
		setBackground(Color.RED);
		TableModel = new StudentRegTableModel(stud, null);
		Table = new JTable(TableModel);
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    Table.getColumnModel().getColumn(0).setPreferredWidth(30);
	    Table.getColumnModel().getColumn(1).setPreferredWidth(200);
	    Table.getColumnModel().getColumn(3).setPreferredWidth(50);
	    Table.getColumnModel().getColumn(4).setPreferredWidth(30);
	    Table.getColumnModel().getColumn(5).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(6).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(7).setPreferredWidth(300);
	    Table.getColumnModel().getColumn(11).setPreferredWidth(150);
	    Table.getColumnModel().getColumn(12).setPreferredWidth(40);
	    Table.getColumnModel().getColumn(13).setPreferredWidth(200);
	    Table.setBackground(Color.ORANGE);
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
	}

}
