package TeacherPage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import baseGui.QuestionClient;

public class TTableModel extends AbstractTableModel{
	private String[] Headings = new String[]{ "No.", "SET", "SUBJECT", "CLASS", "EVALUATION", 
			"QUESTION TYPE", "DATE", "TIME","QUESTIONS", "POINTING", "DURATION", "FILENAME", "WEIGHT",  "WEEK",
			"TOPIC", "USERNAME"};
	private QuestionClient Serve = new QuestionClient();
	private static File pwd = new File("colSource.txt");
	private Vector data = new Vector();
    private Vector columns;
    private File setList = new File("setList");
    private Object clas = null, sub = null, evaltype = null, exmtype = null, tim = null, dat = null,
    		point = null, term = null, noQ, filename = null, duration = null;
	private String set = null;
   private TeacherClient change = new TeacherClient(); private int IDno;
	public TTableModel(Vector readyQuestions){
		data = readyQuestions;

	}	
 
	public String getColumnName(int column) {
		return Headings[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
			switch(columnIndex){
			case 0:
				int k = ((rowIndex * getColumnCount())+ columnIndex);
				//set =  data.elementAt(k);
				return false;
			case 1:
				int p = ((rowIndex * getColumnCount())+ columnIndex);
				//sub =  data.elementAt(p);
				return true;
			case 2:
				int w = ((rowIndex * getColumnCount())+ columnIndex);
				clas =  data.elementAt(w);
				return false;
			case 3:
				int q = ((rowIndex * getColumnCount())+ columnIndex);
				evaltype =  data.elementAt(q);
				return false;
			case 4:
				int e = ((rowIndex * getColumnCount())+ columnIndex);
				exmtype =  data.elementAt(e);
				return true;
			case 5:
				int a = ((rowIndex * getColumnCount())+ columnIndex);
				dat =  data.elementAt(a);
				return true;
			case 6:
				int aa = ((rowIndex * getColumnCount())+ columnIndex);
				tim =  data.elementAt(aa);
				return true;
			case 7:
				int ar = ((rowIndex * getColumnCount())+ columnIndex);
				noQ =  data.elementAt(ar);
				return true;
			case 8:
				int ag = ((rowIndex * getColumnCount())+ columnIndex);
				point =  data.elementAt(ag);
				return true;
			case 9:
				int aw = ((rowIndex * getColumnCount())+ columnIndex);
				duration =  data.elementAt(aw);
				return true;
			case 10:
				int po = ((rowIndex * getColumnCount())+ columnIndex);
				filename =  data.elementAt(po);
				return true;
				
			default:
				return false;
			}
	}

	
	
	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		int rx = (rowIndex * getColumnCount());
		Object NO = ((Object) data.elementAt(rx));
		
		int qq = (rowIndex * getColumnCount())+ 1;
		Object sett = (Object) data.elementAt(qq);

		int qk = (rowIndex * getColumnCount())+ 2;
		Object sub = (Object) data.elementAt(qk);

		int qh = (rowIndex * getColumnCount())+ 3;
		Object clas = (Object) data.elementAt(qh);

		int qx = (rowIndex * getColumnCount())+ 4;
		Object evaltype = (Object) data.elementAt(qx);

		int qi = (rowIndex * getColumnCount())+ 5;
		Object exmtype = (Object) data.elementAt(qi);

		int qu = (rowIndex * getColumnCount())+ 6;
		Object dat = (Object) data.elementAt(qu);

		int q2 = (rowIndex * getColumnCount())+ 7;
		Object tim = (Object) data.elementAt(q2);

		int q3 = (rowIndex * getColumnCount())+ 8;
		Object noQ = (Object) data.elementAt(q3);

		int fi = (rowIndex * getColumnCount())+ 11;
		Object filename = (Object) data.elementAt(fi);
		
		String ile = filename.toString().trim();
		String ServerDirectory = "C:\\Subjects\\"+sub+"\\"+evaltype;
		String localname = "C:\\LocalSubjects\\"+sub+"\\"+evaltype+"\\"+ile+"+locked";
		String filenam = ile+"+locked";
		
		switch(columnIndex){
		case 0:
			int k = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(k);
			data.add(k, newValue);
			break;
		case 1:
			int kd = ((rowIndex * getColumnCount())+ columnIndex);
			int resp = 10;
			if (newValue.toString().trim().equals("false")){
				resp = JOptionPane.showConfirmDialog(null, "Unchecking this box will make this Assessment inaccessible. " +
						"Are you sure you want to unset it? ");
			}
			if (newValue.toString().trim().equals("true")){
				JOptionPane.showMessageDialog(null, "Checking this box will make this Assessment accessible on the" +
						"date and time scheduled.  ");
				Serve.StreamFiles(ServerDirectory, localname, filenam);   // Files will be streamed when set is clicked
				data.remove(kd);
				data.add(kd, newValue);
				change.rewrite(columnIndex, sett, newValue, NO, sub, clas, evaltype);
			}
			if (resp==0){
				data.remove(kd);
				data.add(kd, newValue);
				change.rewrite(columnIndex, sett, newValue, NO, sub, clas, evaltype);
			}
			break;
		case 2:
			int u = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(u);
			data.add(u, newValue);
			change.rewrite(columnIndex, sub, newValue, NO, sub, clas, evaltype);
			break;
		case 3:
			IDno = 3;
			int ku = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(ku);
			data.add(ku, newValue);
			change.rewrite(columnIndex, clas, newValue, NO, sub, clas, evaltype);
			break;
		case 4:
			IDno = 4;
			int kt = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(kt);
			data.add(kt, newValue);
			change.rewrite(columnIndex, evaltype, newValue, NO, sub, clas, evaltype);
			break;
		case 5:
			IDno = 5;
			int kf = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(kf);
			data.add(kf, newValue);
			change.rewrite(columnIndex, exmtype, newValue, NO, sub, clas, evaltype);
			break;
		case 6:
			IDno = 6;
			int fr = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(fr);
			data.add(fr, newValue);
			change.rewrite(columnIndex, dat, newValue, NO, sub, clas, evaltype);
			break;
		case 7:
			IDno = 7;
			int gf = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(gf);
			data.add(gf, newValue);
			change.rewrite(columnIndex, tim, newValue, NO, sub, clas, evaltype);
			break;
			
		case 8:
			IDno = 8;
			int hj = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(hj);
			data.add(hj, newValue);
			change.rewrite(columnIndex, noQ, newValue, NO, sub, clas, evaltype);
			break;
		case 9:
			IDno = 8;
			int ho = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(ho);
			data.add(ho, newValue);
			change.rewrite(columnIndex, point, newValue, NO, sub, clas, evaltype);
			double newVal = Double.parseDouble(newValue.toString().trim());
			double noOfQ = Double.parseDouble(noQ.toString().trim());
			Object weight = newVal*noOfQ;
			change.rewrite(12, point, weight, NO, sub, clas, evaltype);
			break;
		case 10:
			IDno = 8;
			int hv = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(hv);
			data.add(hv, newValue);
			change.rewrite(columnIndex, duration, newValue, NO, sub, clas, evaltype);
			break;
		case 11:
			IDno = 8;
			int c = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(c);
			data.add(c, newValue);
			change.rewrite(columnIndex, noQ, newValue, NO, sub, clas, evaltype);
			break;
		default:
			return;
		}
	}

	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0:
			return String.class;
		case 1:
			return Boolean.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return String.class;
		case 6:
			return String.class;
		case 7:
			return String.class;
		case 8:
			return String.class;
		case 9:
			return String.class;
		case 10:
			return String.class;
		case 11:
			return String.class;
		case 12:
			return String.class;
		case 13:
			return String.class;
		case 14:
			return String.class;
		case 15:
			return String.class;
		default:
			return null;
		}
	}

	public int getColumnCount() {
		return 16;
	}

	public int getRowCount() {
		return data.size() / getColumnCount();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		////(((rowIndex * getColumnCount())+ columnIndex)+">"+data.elementAt((rowIndex * getColumnCount())+ columnIndex));
		 if (columnIndex==1) {
			 try{
			    return Boolean.valueOf((String) data.elementAt((rowIndex * getColumnCount())
			            + columnIndex));
			 }catch(ClassCastException e){
				 return Boolean.valueOf((Boolean) data.elementAt((rowIndex * getColumnCount())
				            + columnIndex));
			 }
			    }
		return (String) data.elementAt((rowIndex * getColumnCount())
                + columnIndex);
	}

}
