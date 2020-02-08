package Administration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class CalTableModel extends AbstractTableModel{
	private String[] Headings = new String[]{" No ", "Date", "Term", "Session", "	Weeks", "Resumption", "First-Test","Mid_Term",
			 "Second-Test", "Revision", "Examination", "Vacation" };
	private static File pwd = new File("calendar.evl");
	private Vector data;
    private Vector columns; private Object Term = 0, Weeks = 0;
    private String line; private Object No = null, Date = null, Session = null, Resume = null, FirstT = null, Mid = null, SecondT = null,
    		Revis = null, Exam = null, Vacay = null;
    private BasicInfoClient change = new BasicInfoClient(); 
	public CalTableModel(){
		data = new Vector();
	  try {
		  data = change.DataCheck("Calendar");
  } catch (Exception e) {
          e.printStackTrace();
  }

	}	
 
	public String getColumnName(int column) {
		return Headings[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
			switch(columnIndex){
			case 0:
				int t = ((rowIndex * getColumnCount())+ columnIndex);
				No = (Object) data.elementAt(t);
				return true;
			case 1:
				int k = ((rowIndex * getColumnCount())+ columnIndex);
				Date = (Object) data.elementAt(k);
				return true;
			case 2:
				int ki = ((rowIndex * getColumnCount())+ columnIndex);
				Term = ((Object) data.elementAt(ki));
				return true;
			case 3:
				int p = ((rowIndex * getColumnCount())+ columnIndex);
				Session = (Object) data.elementAt(p);
				return true;
			case 4:
				int w = ((rowIndex * getColumnCount())+ columnIndex);
				Weeks = (Object) data.elementAt(w);
				return true;
			case 5:
				int q = ((rowIndex * getColumnCount())+ columnIndex);
				Resume = (Object) data.elementAt(q);
				return true;
			case 6:
				int e = ((rowIndex * getColumnCount())+ columnIndex);
				FirstT = (Object) data.elementAt(e);
				return true;
			case 7:
				int a = ((rowIndex * getColumnCount())+ columnIndex);
				Mid = (Object) data.elementAt(a);
				return true;
			case 8:
				int aa = ((rowIndex * getColumnCount())+ columnIndex);
				SecondT = (Object) data.elementAt(aa);
				return true;
			case 9:
				int ar = ((rowIndex * getColumnCount())+ columnIndex);
				Revis = (Object) data.elementAt(ar);
				return true;
			case 10:
				int ag = ((rowIndex * getColumnCount())+ columnIndex);
				Exam = (Object) data.elementAt(ag);
				return true;
			case 11:
				int z = ((rowIndex * getColumnCount())+ columnIndex);
				Vacay = (Object) data.elementAt(z);
				return true;
			default:
				return false;
			}
	}

	
	
	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		int ki = ((rowIndex * getColumnCount()));
		int No = Integer.parseInt((String) data.elementAt(ki).toString().trim());
		switch(columnIndex){
		case 0:
			break;
		case 1:
			int u = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(u);
			data.add(u, newValue);
			change.rewrite(rowIndex, columnIndex, Date, newValue, No);
			break;
		case 2:
			int k = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(k);
			data.add(k, newValue);
			change.rewrite(rowIndex, columnIndex, Term, newValue, No);
			break;
		case 3:
			int ku = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(ku);
			data.add(ku, newValue);
			change.rewrite(rowIndex, columnIndex, Session, newValue, No);
			break;
		case 4:
			int kt = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(kt);
			data.add(kt, newValue);
			change.rewrite(rowIndex, columnIndex, Weeks, newValue, No);
			break;
		case 5:
			int kf = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(kf);
			data.add(kf, newValue);
			change.rewrite(rowIndex, columnIndex, Resume, newValue, No);
			break;
		case 6:
			int fr = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(fr);
			data.add(fr, newValue);
			change.rewrite(rowIndex, columnIndex, FirstT, newValue, No);
			break;
		case 7:
			int gf = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(gf);
			data.add(gf, newValue);
			change.rewrite(rowIndex, columnIndex, Mid, newValue, No);
			break;
		case 8:
			int hj = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(hj);
			data.add(hj, newValue);
			change.rewrite(rowIndex, columnIndex, SecondT, newValue, No);
			break;
		case 9:
			int hc = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(hc);
			data.add(hc, newValue);
			change.rewrite(rowIndex, columnIndex, Revis, newValue, No);
			break;
		case 10:
			int v = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(v);
			data.add(v, newValue);
			change.rewrite(rowIndex, columnIndex, Exam, newValue, No);
			break;
		case 11:
			int b = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(b);
			data.add(b, newValue);
			change.rewrite(rowIndex, columnIndex, Vacay, newValue, No);
			break;
		default:
			return;
		}
	}

	public int getColumnCount() {
		return 12;
	}

	public int getRowCount() {
		return data.size() / getColumnCount();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		////(((rowIndex * getColumnCount())+ columnIndex)+">"+data.elementAt((rowIndex * getColumnCount())+ columnIndex));
		return (Object) data.elementAt((rowIndex * getColumnCount())
                + columnIndex);
	}

}
