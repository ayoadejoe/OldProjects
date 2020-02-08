package Administration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import TeacherPage.DiaryEssayClient;

public class DiaryViewTableModel extends AbstractTableModel{
	private String[] Headings = new String[]{ "WEEK", "CLASS", "SUBJECT", "TOPIC", "L.PLAN" };
	private static File pwd = new File("diary.evl");
	private Vector data;
    private Vector columns; private int week = 0; Object plan = null;
    private String line; Object topic = null, term = null, subject = null, Class = null, subjt = null, Cls = null, tm = null;
    private DiaryEssayClient change = new DiaryEssayClient(); private int IDno;
	public DiaryViewTableModel(String term){
		Vector Diary = change.getAllDiaries(term);
		data = new Vector();
		data = Diary;
		//("Data: "+data);
	}	
 
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0:
			return Object.class;
		case 1:
			return Object.class;
		case 2:
			return Object.class;
		case 3:
			return Object.class;
		case 4:
			return Boolean.class;
		default:
			return null;
		}
	}

	public String getColumnName(int column) {
		return Headings[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
			switch(columnIndex){
			case 0:
				int ki = ((rowIndex * getColumnCount())+ columnIndex);
				week = Integer.parseInt((String) data.elementAt(ki)+"");
				return false;
			case 1:
				return false;
			case 2:
				return false;
			case 3:
				int k = ((rowIndex * getColumnCount())+ columnIndex);
				topic = (String) data.elementAt(k)+"";
				return true;
			case 4:
				int p = ((rowIndex * getColumnCount())+ columnIndex);
				plan = (Object) data.elementAt(p)+"";
				return true;
			default:
				return false;
			}
	}
	
	

	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		int q3 = (rowIndex * getColumnCount());
		int wik = Integer.parseInt((String) data.elementAt(q3)+"");
		
		int qe = (rowIndex * getColumnCount())+ 1;
		String tpic = (String) data.elementAt(qe);
		tpic = tpic.trim();
		//JOptionPane.showMessageDialog(null, "Week = "+wik+" Topic"+tpic);
		switch(columnIndex){
		case 0:
			int k = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(k);
			data.add(k, newValue);
			change.rewrite(rowIndex, columnIndex, week, newValue, subjt, Cls, tm, wik);
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			int d = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(d);
			data.add(d, newValue);
			change.rewrite(rowIndex, columnIndex, topic, newValue, subjt, Cls, tm, wik);
			break;
		case 4:
			int u = ((rowIndex * getColumnCount())+ columnIndex); int resp = 10;
			if (newValue.toString().trim().equals("false")){
				resp = JOptionPane.showConfirmDialog(null, "Unchecking this box will delete week "+wik+"'s Lesson Plan. " +
						"Are you sure you want to delete it? ");
			}
			if (newValue.toString().trim().equals("true")){
				data.remove(u);
				data.add(u, newValue);
				change.rewrite(rowIndex, columnIndex, plan, newValue, subjt, Cls, tm, wik);
			}
			if (resp==0){
				data.remove(u);
				data.add(u, newValue);
				change.rewrite(rowIndex, columnIndex, plan, newValue, subjt, Cls, tm, wik);
			}
			
			break;
		default:
			return;
		}
	}

	public int getColumnCount() {
		return 5;
	}

	public int getRowCount() {
		return data.size() / getColumnCount();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		 if (columnIndex==4) {
			 try{
			    return Boolean.valueOf((String) data.elementAt((rowIndex * getColumnCount())
			            + columnIndex));
			 }catch(ClassCastException e){
				 return Boolean.valueOf((Boolean) data.elementAt((rowIndex * getColumnCount())
				            + columnIndex));
			 }
			    }else{
		return (String) data.elementAt((rowIndex * getColumnCount())
                + columnIndex);}
	}
}
