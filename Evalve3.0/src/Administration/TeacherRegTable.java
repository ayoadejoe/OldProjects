package Administration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class TeacherRegTable extends JPanel{
	private JTable TeacherTable;
	private TeacherRegTableModel TeacherTableModel;
	private Font font = new Font("SERIF", Font.BOLD, 13);
	public TeacherRegTable() {
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
		TeacherTableModel = new TeacherRegTableModel();
		TeacherTable = new JTable(TeacherTableModel);
		TeacherTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    TeacherTable.getColumnModel().getColumn(0).setPreferredWidth(30);
	    TeacherTable.getColumnModel().getColumn(1).setPreferredWidth(200);
	    TeacherTable.getColumnModel().getColumn(2).setPreferredWidth(250);
	    TeacherTable.getColumnModel().getColumn(3).setPreferredWidth(210);
	    TeacherTable.getColumnModel().getColumn(4).setPreferredWidth(60);
	    TeacherTable.getColumnModel().getColumn(5).setPreferredWidth(70);
	    TeacherTable.getColumnModel().getColumn(6).setPreferredWidth(100);
	    TeacherTable.getColumnModel().getColumn(7).setPreferredWidth(150);
	    TeacherTable.getColumnModel().getColumn(8).setPreferredWidth(200);
	    TeacherTable.getColumnModel().getColumn(9).setPreferredWidth(100);
	    TeacherTable.getColumnModel().getColumn(11).setPreferredWidth(100);
	    TeacherTable.getColumnModel().getColumn(12).setPreferredWidth(150);
	    TeacherTable.getColumnModel().getColumn(13).setPreferredWidth(200);
	    TeacherTable.setBackground(Color.ORANGE);
		setLayout(new BorderLayout());
		JScrollPane tesT1 = new JScrollPane(TeacherTable);
		tesT1.getPreferredSize();
	    Dimension dim2 = tesT1.getPreferredSize();
		dim2.height = TeacherTable.getRowHeight();
		dim2.width = 700;
		tesT1.setPreferredSize(dim2);
		tesT1.setAutoscrolls(true);
		tesT1.getViewport().add(TeacherTable, BorderLayout.CENTER);
		tesT1.setViewportView(TeacherTable);
	    this.add(tesT1, BorderLayout.CENTER); 
	}

}
