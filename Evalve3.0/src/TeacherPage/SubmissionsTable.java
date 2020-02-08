package TeacherPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
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

public class SubmissionsTable extends JFrame{
	private JTable Table;
	private SubmissionsTableModel model;
	private int increase, d, f, week;
	private Vector <Object> Headings =new Vector <Object>();
	private Vector <Object> Data = new Vector <Object> ();
	private Vector <Object> Extract = new Vector <Object> ();
	private JPopupMenu go = new JPopupMenu(); int s = 0;
	private JMenuItem menuItem1, menuItem2;
	private List<String> Ansrs = new ArrayList<String>();;
	public SubmissionsTable(final String subject, final String claxx, final String evalType, Vector readyAssignments, Vector headings, String cl) {
		super(claxx.toUpperCase()+" "+subject.toUpperCase()+" "+evalType.toUpperCase()+" RESULT");
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
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		Headings = headings;
		Data = readyAssignments;
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		setVisible(true);
		setSize(950, 400);
		model = new SubmissionsTableModel(Headings, 0, readyAssignments);
		menuItem1 = new JMenuItem("");
		menuItem2 = new JMenuItem(""); 
		go.add(menuItem1);go.add(menuItem2);
		Table = new JTable(model);
		Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Table.setRowHeight(25);
		int extent = Table.getColumnCount();
		int e = 0, w = 0; 
		while(w<(extent-4)){
		int space = 5+(e);
	    Table.getColumnModel().getColumn(space).setPreferredWidth(130);
	    //("space = "+space);
	    e = e+4;	w = space;
		}
	    Table.setBackground(Color.white);
		setLayout(new BorderLayout());
		JScrollPane tesT1 = new JScrollPane(Table);
		tesT1.getPreferredSize();
	    Dimension dim2 = tesT1.getPreferredSize();
		dim2.height = Table.getRowHeight();
		dim2.width = 900;
		tesT1.setPreferredSize(dim2);
		tesT1.setAutoscrolls(true);
		tesT1.getViewport().add(Table, BorderLayout.CENTER);
		tesT1.setViewportView(Table);
	    this.add(tesT1, BorderLayout.CENTER); 
	    int DataSize = Data.size(); int colum = Headings.size();
	    //(DataSize+", "+colum);
	    if(colum <= 0){
	    	colum = 1;
	    	JOptionPane.showMessageDialog(null, "No submissions yet.");
	    	this.dispose();
	    	}
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
					menuItem1.setText("Evaluate "+cellValue.toString());
					menuItem2.setText("Print Table");
					go.show(Table, e.getX(), e.getY());
					
					menuItem1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent w) { String Condition = null; int resp = 0;
							Object No = (Object) Table.getModel().getValueAt(d, 
						    		0);
							Object IDNO = (Object) Table.getModel().getValueAt(d, 
						    		1);
							Condition = (String) (Table.getModel().getValueAt(d, 
						    		f+1));
							switch(Condition.trim()){
							case "UnsignedFocus":
								resp = JOptionPane.showConfirmDialog(null, "There was either a connection/power problem while this" +
										" assessment was going on, hence this work is incomplete. Would you assess it still?" +
										" There may be errors. ");
								break;
							case "UnsignedTimeUp":
								resp = JOptionPane.showConfirmDialog(null, "This work could not be completed because time was out.");
								break;
							default:
								break;
								
							}
							if(resp == 0){
							String Answers = null; String Ans = null; String weight = null; 
							try{
							Answers = (String) (Table.getModel().getValueAt(d, 
						    		f));
							weight = (String) (Table.getModel().getValueAt(d, 
						    		(f-1)));
							String Answ = Answers.replace("[", "");
							String Anse =  Answ.replace("]", "");
							Ans = Anse.replace("|,", "`");
							//("IDNO: "+IDNO+" Answers: "+Ans);
							try{
							String[] parts = Ans.split("`"); int x = 0;
							while(x > -1){
								String tim = parts[x].toString().trim();
								Ansrs.add(tim);
								x++;
							}
							}catch(Exception g){}
							int week = Integer.parseInt(cellValue.toString().substring(5).trim());
							//("Week: "+week+" Vector: "+Ansrs);
							Vector<Integer> scorev = null;
							new StudentEssay(IDNO, subject, claxx, evalType, week, Ansrs, 0, 0, 0, weight, scorev);
							dispose();
							}catch(Exception t){
								JOptionPane.showMessageDialog(null, "You should right-click on the Essay Week that you want " +
										"to evaluate.");
							}
						}else{
							//("Closing Pages");
							dispose();
						}
							}
				    });
					
					 menuItem2.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent w) {
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
