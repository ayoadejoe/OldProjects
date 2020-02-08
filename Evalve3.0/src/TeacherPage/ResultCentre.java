package TeacherPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ResultCentre extends JFrame{
	private JTable table;
	private ResultTableModel tableModel;
	private JPopupMenu popup;
	private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	private JTextField RAW = new JTextField("--", 3);
	private JTextField RTW = new JTextField("--", 3);
	private JTextField REW = new JTextField("--", 3);
	private JLabel raw = new JLabel("Assignment Weight");
	private JLabel rtw = new JLabel("Test Weight");
	private JLabel rew = new JLabel("Exam Weight");
	private JLabel title = new JLabel("");
	private JPanel tablePanel = new JPanel();
	private JButton pop = new JButton("Populate");
	private JButton cum = new JButton("Cumulate");
	private JButton enter = new JButton("Submit");
	
	private GridBagConstraints lineup = new GridBagConstraints();
	public ResultCentre(String subject, String claxx, Vector classIDs) {
		super("RESULTS");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		setLayout(new GridBagLayout());
		tableModel = new ResultTableModel(classIDs);
		table = new JTable(tableModel);
		table.setForeground(Color.black);
		table.setFont(new Font("arial", Font.BOLD, 13));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(25);
		
		tablePanel.setLayout(new BorderLayout());
		Border innerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		Border outerBorder = BorderFactory.createRaisedBevelBorder();
		AbstractButton createCompoundBorder;
		tablePanel.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		tablePanel.add(new JLabel("¬                                                                                 TABLE                  " +
				"                                                                ¬"), BorderLayout.NORTH);
		
		table.setPreferredSize(new Dimension(500, 400));
		JScrollPane tesT1 = new JScrollPane(table);
		tesT1.setPreferredSize(new Dimension(550, 450));
		tesT1.setAutoscrolls(true);
		tesT1.getViewport().add(table, BorderLayout.CENTER);
		tesT1.setViewportView(table);
		tesT1.revalidate();
		tesT1.repaint();
		
		tablePanel.setPreferredSize(new Dimension(800, 500));
		tablePanel.add(tesT1, BorderLayout.CENTER);
		
		title.setText(subject.toUpperCase()+" RESULT FOR "+claxx);
		lineup.insets = new Insets(0, 0, 500, 0);		// format: push(DOWN, right, UP, left)
		lineup.anchor = GridBagConstraints.CENTER;
		lineup.gridx = 0;
		lineup.gridy = 0;
		lineup.weightx = 0;
		lineup.weighty = 0;
		lineup.fill = GridBagConstraints.NONE;
		title.setFont(new Font("serif", Font.BOLD, 20));
		add(title, lineup);
		
		lineup.insets = new Insets(0, 0, 400, 0);	
	    add(tablePanel, lineup); 
		 
	    Font bam = new Font("serif", Font.BOLD, 15);
	    lineup.insets = new Insets(500, 0, 0, 700);	
	    raw.setFont(bam);
	    add(raw, lineup);
	    lineup.insets = new Insets(500, 0, 0, 500);	
	    add(RAW, lineup);
	    lineup.insets = new Insets(500, 0, 0, 360);	
	    rtw.setFont(bam);
	    add(rtw, lineup);
	    lineup.insets = new Insets(500, 0, 0, 200);	
	    add(RTW, lineup);
	    lineup.insets = new Insets(500, 0, 0, 30);	
	    rew.setFont(bam);
	    add(rew, lineup);
	    lineup.insets = new Insets(500, 130, 0, 0);	
	    add(REW, lineup);
	    
	    lineup.insets = new Insets(500, 350, 0, 0);	
	    add(pop, lineup);
	    lineup.insets = new Insets(500, 530, 0, 0);	
	    add(cum, lineup);
	    lineup.insets = new Insets(500, 700, 0, 0);	
	    add(enter, lineup);
	    
	    setSize(850, 650);
	    setLocationRelativeTo(getParent());
	    setVisible(true);
	}

}
