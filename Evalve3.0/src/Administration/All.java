package Administration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class All extends JPanel{
	private JTable Table;
	private  AllResultsTableModel TableModel;
	private Font font = new Font("SERIF", Font.BOLD, 13);
	private File file1 = new File("allresult.evl");
	private File file2;
	private PrintWriter eachLine = null;
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	private Scanner scanClass;
	public All(String term) {
		Color GroundColor = new Color(100, 0, 250);
		Dimension dim3 = getPreferredSize();
		dim3.height = 600;
		dim3.width = 700;
		setPreferredSize(dim3);
		Border innerBorder2 = BorderFactory.createEtchedBorder(20, Color.ORANGE, Color.GRAY);
		Border outerBorder2 = BorderFactory.createRaisedSoftBevelBorder();
		AbstractButton createCompoundBorder2;
		setBorder(BorderFactory.createCompoundBorder(innerBorder2, outerBorder2));
		setBackground(Color.RED);
		classSorter(term);
		TableModel = new  AllResultsTableModel(file2, term);
		Table = new JTable(TableModel);
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Table.setRowHeight(25);
	    Table.getColumnModel().getColumn(0).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(1).setPreferredWidth(200);
	    Table.getColumnModel().getColumn(2).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(3).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(4).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(5).setPreferredWidth(130);
	    Table.getColumnModel().getColumn(6).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(7).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(8).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(9).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(10).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(11).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(12).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(13).setPreferredWidth(80);
	    Table.getColumnModel().getColumn(14).setPreferredWidth(80);
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

	private void classSorter(String term) {
		try{
		if(term.equals(null)){return;}
		}catch(Exception e){return;}
		if(term.equals("First") || term.equals("Second") ||term.equals("Third")){
			term = (term+"termresult").toLowerCase();
		}
		file2 = new File(term+"result.evl");
		try{
			PrintStream clear = new PrintStream(file2);
			clear.println("ID-No"+" "+ "Name"+" "+ "Class"+" "+ "1st Assgn"+" "+ "2nd Assgn"+" "+ "1st Test"+" "+"2nd Test"+" "+
					 "MCQ"+" "+ "Essay"+" "+ "Exam"+" "+ "Total"+" "+ "Grade-Point"+" "+"Class-Ave"+"Subject");
			clear.close();
			 
			scanClass = new Scanner(file1);
		while( scanClass.hasNextLine()){
			String line = scanClass.nextLine();
			if(line.contains("*")){
				 fw = new FileWriter(file2, true);
			      bw = new BufferedWriter(fw);
			      eachLine = new PrintWriter(bw);
			      eachLine.append(line+"\n");
			      eachLine.flush();
			      bw.flush();
			      fw.flush();
			}
			if(line.contains((term))){
				 fw = new FileWriter(file2, true);
			      bw = new BufferedWriter(fw);
			      eachLine = new PrintWriter(bw);
			      eachLine.append(line+"\n");
			      eachLine.flush();
			      bw.flush();
			      fw.flush();
						}
			}
		if(eachLine != null)eachLine.close();
		if(bw != null)bw.close();
		if(fw != null)fw.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Buffer file not found.");
				e.printStackTrace();
			} catch (NoSuchElementException e4) {
				JOptionPane.showMessageDialog(null, e4.getMessage());
				e4.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}		
	
	}
}
