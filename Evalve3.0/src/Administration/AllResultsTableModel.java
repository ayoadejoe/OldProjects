package Administration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class AllResultsTableModel extends AbstractTableModel{
	private String[] Headings = new String[]{ "R-No", "Name", "Class", "1st Assgn", "2nd Assgn", "1st Test","2nd Test",
			 "Essay", "MCQ", "Exam", "Total", "Grade-Point", "Grade", "Class-Ave", "Term", "Subject" };
	private static File pwd = new File("allresult.evl");
	private Vector data;
   private Vector columns; String line = null;
   private Object name = null, classxx = null, FstAssgn = null, SecAssgn = null, FstTest = null, SecTest = null, subj = null, 
   		mcq = null, ess = null, exm = null, tot = null, gp = null, cAv = null, grd = null, Tem = null, currentterm = null;
   private int IDno;
   private WriteChanges change = new WriteChanges();
	public AllResultsTableModel(File file2, String term) {
		  data = new Vector();
		  try {
			  if(term != null){
				  pwd = file2;
			  }
	          FileInputStream fis = new FileInputStream(pwd);
	          BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	          StringTokenizer st1 = new StringTokenizer(br.readLine(), "<>");
	          /*while (st1.hasMoreTokens())
	                  columns.addElement(st1.nextToken());*/
	          while ((line = br.readLine()) != null) {
	                  StringTokenizer st2 = new StringTokenizer(line, "<>");
	                  while (st2.hasMoreTokens())
	                          data.addElement(st2.nextToken());
	          }
	          br.close();
	          fis.close();
	          System.gc();
	          //pwd.delete();
	  } catch (Exception e) {
	          e.printStackTrace();
	  }

	}
	public String getColumnName(int column) {
		return Headings[column];
	}
	@Override
	public int getColumnCount() {
		return 16;
	}

	@Override
	public int getRowCount() {
		return data.size() / getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		////.println(((rowIndex * getColumnCount())+ columnIndex)+">"+data.elementAt((rowIndex * getColumnCount())+ columnIndex));
		return (String) data.elementAt((rowIndex * getColumnCount())
                + columnIndex);
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0:
			int ki = ((rowIndex * getColumnCount())+ columnIndex);
			IDno = Integer.parseInt((String) data.elementAt(ki));
			//.println("IDNo = "+IDno);
			return true;
		case 1:
			int k = ((rowIndex * getColumnCount())+ columnIndex);
			name =  data.elementAt(k);
			return true;
		case 2:
			int p = ((rowIndex * getColumnCount())+ columnIndex);
			classxx =  data.elementAt(p);
			return true;
		case 3:
			int w = ((rowIndex * getColumnCount())+ columnIndex);
			FstAssgn =  data.elementAt(w);
			return true;
		case 4:
			int q = ((rowIndex * getColumnCount())+ columnIndex);
			SecAssgn =  data.elementAt(q);
			return true;
		case 5:
			int e = ((rowIndex * getColumnCount())+ columnIndex);
			FstTest =  data.elementAt(e);
			return true;
		case 6:
			int a = ((rowIndex * getColumnCount())+ columnIndex);
			SecTest =  data.elementAt(a);
			return true;
		case 7:
			int aa = ((rowIndex * getColumnCount())+ columnIndex);
			ess =  data.elementAt(aa);
			return true;
		case 8:
			int ar = ((rowIndex * getColumnCount())+ columnIndex);
			mcq =  data.elementAt(ar);
			return true;
		case 9:
			int ag = ((rowIndex * getColumnCount())+ columnIndex);
			exm =  data.elementAt(ag);
			return true;
		case 10:
			int z = ((rowIndex * getColumnCount())+ columnIndex);
			tot =  data.elementAt(z);
			return true;
		case 11:
			int er = ((rowIndex * getColumnCount())+ columnIndex);
			gp =  data.elementAt(er);
			return true;
		case 12:
			int r = ((rowIndex * getColumnCount())+ columnIndex);
			grd =  data.elementAt(r);
			return true;
		case 13:
			int rt = ((rowIndex * getColumnCount())+ columnIndex);
			cAv =  data.elementAt(rt);
			return true;
		case 14:
			int tm = ((rowIndex * getColumnCount())+ columnIndex);
			Tem =  data.elementAt(tm);
			return true;
		case 15:
			int re = ((rowIndex * getColumnCount())+ columnIndex);
			subj =  data.elementAt(re);
			return true;
		default:
			return false;
		}
	}

	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		int tm = ((rowIndex * getColumnCount())+ 14);
		currentterm =  data.elementAt(tm);
		int re = ((rowIndex * getColumnCount())+ 15);
		subj =  data.elementAt(re);
		int ki = ((rowIndex * getColumnCount())+ 0);
		IDno = Integer.parseInt( (data.elementAt(ki).toString().trim()));
		switch(columnIndex){
		/*case 0:
			int kr = ((rowIndex * getColumnCount())+ columnIndex)-1;
			//.println("IDNo = "+data.elementAt(kr));
			IDno = Integer.parseInt((String) data.elementAt(kr));
			int kw = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(kw);
			data.add(kw, newValue);
			break;
		case 1:
			int ki = ((rowIndex * getColumnCount())+ columnIndex)-1;
			IDno = Integer.parseInt((String) data.elementAt(ki));
			int k = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(k);
			data.add(k, newValue);
			change.rewrite(rowIndex, columnIndex, name, newValue, IDno, currentterm, subj);
			break;
		case 2:
			int ke = ((rowIndex * getColumnCount())+ columnIndex)-2;
			//.println("IDNo = "+data.elementAt(ke));
			IDno = Integer.parseInt((String) data.elementAt(ke));
			//.println("IDNo = "+IDno);
			int u = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(u);
			data.add(u, newValue);
			change.rewrite(rowIndex, columnIndex, classxx, newValue, IDno, currentterm, subj);
			break;*/
		case 3:
			int ku = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(ku);
			data.add(ku, newValue);
			change.rewrite(rowIndex, columnIndex, FstAssgn, newValue, IDno, currentterm, subj);
			break;
		case 4:
			int kt = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(kt);
			data.add(kt, newValue);
			change.rewrite(rowIndex, columnIndex, SecAssgn, newValue, IDno, currentterm, subj);
			break;
		case 5:
			int kf = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(kf);
			data.add(kf, newValue);
			change.rewrite(rowIndex, columnIndex, FstTest, newValue, IDno, currentterm, subj);
			break;
		case 6:
			int fr = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(fr);
			data.add(fr, newValue);
			change.rewrite(rowIndex, columnIndex, SecTest, newValue, IDno, currentterm, subj);
			break;
		case 7:
			int gf = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(gf);
			data.add(gf, newValue);
			change.rewrite(rowIndex, columnIndex, ess, newValue, IDno, currentterm, subj);
			break;
		case 8:
			int hj = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(hj);
			data.add(hj, newValue);
			change.rewrite(rowIndex, columnIndex, mcq, newValue, IDno, currentterm, subj);
			break;
		case 9:
			int hc = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(hc);
			data.add(hc, newValue);
			change.rewrite(rowIndex, columnIndex, exm, newValue, IDno, currentterm, subj);
			break;
		case 10:
			int v = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(v);
			data.add(v, newValue);
			change.rewrite(rowIndex, columnIndex, tot, newValue, IDno, currentterm, subj);
			break;
		case 11:
			int b = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(b);
			data.add(b, newValue);
			change.rewrite(rowIndex, columnIndex, gp, newValue, IDno, currentterm, subj);
			break;
		case 12:
			int vv = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(vv);
			data.add(vv, newValue);
			change.rewrite(rowIndex, columnIndex, grd, newValue, IDno, currentterm, subj);
			break;
		case 13:
			int vc = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(vc);
			data.add(vc, newValue);
			change.rewrite(rowIndex, columnIndex, cAv, newValue, IDno, currentterm, subj);
			break;
		case 14:
			int vt = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(vt);
			data.add(vt, newValue);
			change.rewrite(rowIndex, columnIndex, Tem, newValue, IDno, currentterm, subj);
			break;
		default:
			return;
		}
	}
}
