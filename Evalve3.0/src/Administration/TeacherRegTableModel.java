package Administration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TeacherRegTableModel extends AbstractTableModel{
	private String[] Headings = new String[]{ "ID-No", "NAME", "SUBJECT-CLASS-CREDIT", "GENDER", "BIRTHDAY", "PHONE-1","EMAIL",
			 "ADDRESS", "USERNAME", "PASSWORD", "EMPLOYED", "PHONE-2", "COMMENTS", "PASSPORT" };
	private static File pwd = new File("Tregister.evl");
	private Vector data;
    private Vector columns;
    private String line, name = null, subclasscl = null, subz = null, gendr = null, agez = null, phne1 = null, eml = null, addr = null,
    		usnam = null, pw = null, admt = null, phne2 = null, commnt = null, ppot = null;
    private RegisterRewrite change = new RegisterRewrite("teacher"); private int IDno;
    private RegisterClient u = new RegisterClient("teacher");
	public TeacherRegTableModel(){
		data = new Vector();
		Vector Tch = u.Registration();
		data = Tch;
	}	
 
	public String getColumnName(int column) {
		return Headings[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
			switch(columnIndex){
			case 0:
				int ki = ((rowIndex * getColumnCount())+ columnIndex);
				IDno = Integer.parseInt((String) data.elementAt(ki));
				//("IDNo = "+IDno);
				return true;
			case 1:
				int k = ((rowIndex * getColumnCount())+ columnIndex);
				name = (String) data.elementAt(k);
				return true;
			case 2:
				int p = ((rowIndex * getColumnCount())+ columnIndex);
				subclasscl = (String) data.elementAt(p);
				return true;
			case 3:
				int w = ((rowIndex * getColumnCount())+ columnIndex);
				subz = (String) data.elementAt(w);
				return true;
			case 4:
				int w3 = ((rowIndex * getColumnCount())+ columnIndex);
				gendr = (String) data.elementAt(w3);
				return true;
			case 5:
				int q = ((rowIndex * getColumnCount())+ columnIndex);
				agez = (String) data.elementAt(q);
				return true;
			case 6:
				int e = ((rowIndex * getColumnCount())+ columnIndex);
				phne1 = (String) data.elementAt(e);
				return true;
			case 7:
				int a = ((rowIndex * getColumnCount())+ columnIndex);
				eml = (String) data.elementAt(a);
				return true;
			case 8:
				int aa = ((rowIndex * getColumnCount())+ columnIndex);
				addr = (String) data.elementAt(aa);
				return true;
			case 9:
				int ar = ((rowIndex * getColumnCount())+ columnIndex);
				usnam = (String) data.elementAt(ar);
				return true;
			case 10:
				int ag = ((rowIndex * getColumnCount())+ columnIndex);
				pw = (String) data.elementAt(ag);
				return true;
			case 11:
				int z = ((rowIndex * getColumnCount())+ columnIndex);
				admt = (String) data.elementAt(z);
				return true;
			case 12:
				int er = ((rowIndex * getColumnCount())+ columnIndex);
				phne2 = (String) data.elementAt(er);
				return true;
			case 13:
				int tt = ((rowIndex * getColumnCount())+ columnIndex);
				commnt = (String) data.elementAt(tt);
				return true;
			case 14:
				int o = ((rowIndex * getColumnCount())+ columnIndex);
				ppot = (String) data.elementAt(o);
				return true;
			default:
				return false;
			}
	}

	
	
	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 1:
			int ki = ((rowIndex * getColumnCount())+ columnIndex)-1;
			//("IDNo = "+data.elementAt(ki));
			IDno = Integer.parseInt((String) data.elementAt(ki));
			int k = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(k);
			data.add(k, newValue);
			change.rewrite(rowIndex, columnIndex, name, newValue, IDno, "teachers");
			break;
		case 2:
			int ke = ((rowIndex * getColumnCount())+ columnIndex)-2;
			IDno = Integer.parseInt((String) data.elementAt(ke));
			//("IDNo = "+IDno);
			int u = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(u);
			data.add(u, newValue);
			change.rewrite(rowIndex, columnIndex, subclasscl, newValue, IDno, "teachers");
			break;
		case 3:
			int op = ((rowIndex * getColumnCount())+ columnIndex)-4;
			IDno = Integer.parseInt((String) data.elementAt(op));
			//("IDNo = "+IDno);
			int ku = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(ku);
			data.add(ku, newValue);
			change.rewrite(rowIndex, columnIndex, gendr, newValue, IDno, "teachers");
			break;
		case 4:
			int oo = ((rowIndex * getColumnCount())+ columnIndex)-5;
			IDno = Integer.parseInt((String) data.elementAt(oo));
			//("IDNo = "+IDno);
			int kt = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(kt);
			data.add(kt, newValue);
			change.rewrite(rowIndex, columnIndex, agez, newValue, IDno, "teachers");
			break;
		case 5:
			int qw = ((rowIndex * getColumnCount())+ columnIndex)-6;
			IDno = Integer.parseInt((String) data.elementAt(qw));
			//("IDNo = "+IDno);
			int kf = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(kf);
			data.add(kf, newValue);
			change.rewrite(rowIndex, columnIndex, phne1, newValue, IDno, "teachers");
			break;
		case 6:
			int qq = ((rowIndex * getColumnCount())+ columnIndex)-7;
			JOptionPane.showMessageDialog(null, qq+" -"+data.elementAt(qq));
			IDno = Integer.parseInt((String) data.elementAt(qq));
			//("IDNo = "+IDno);
			int fr = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(fr);
			data.add(fr, newValue);
			change.rewrite(rowIndex, columnIndex, eml, newValue, IDno, "teachers");
			break;
		case 7:
			int aa = ((rowIndex * getColumnCount())+ columnIndex)-8;
			IDno = Integer.parseInt((String) data.elementAt(aa));
			//("IDNo = "+IDno);
			int gf = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(gf);
			data.add(gf, newValue);
			change.rewrite(rowIndex, columnIndex, addr, newValue, IDno, "teachers");
			break;
		case 8:
			int az = ((rowIndex * getColumnCount())+ columnIndex)-9;
			IDno = Integer.parseInt((String) data.elementAt(az));
			//("IDNo = "+IDno);
			int hj = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(hj);
			data.add(hj, newValue);
			change.rewrite(rowIndex, columnIndex, usnam, newValue, IDno, "teachers");
			break;
		case 9:
			int ao = ((rowIndex * getColumnCount())+ columnIndex)-10;
			IDno = Integer.parseInt((String) data.elementAt(ao));
			//("IDNo = "+IDno);
			int hc = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(hc);
			data.add(hc, newValue);
			change.rewrite(rowIndex, columnIndex, pw, newValue, IDno, "teachers");
			break;
		case 10:
			int ad = ((rowIndex * getColumnCount())+ columnIndex)-11;
			IDno = Integer.parseInt((String) data.elementAt(ad));
			//("IDNo = "+IDno);
			int v = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(v);
			data.add(v, newValue);
			change.rewrite(rowIndex, columnIndex, admt, newValue, IDno, "teachers");
			break;
		case 11:
			int yt = ((rowIndex * getColumnCount())+ columnIndex)-12;
			IDno = Integer.parseInt((String) data.elementAt(yt));
			int b = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(b);
			JOptionPane.showMessageDialog(null, newValue+" --- "+ phne2);
			data.add(b, newValue);
			change.rewrite(rowIndex, columnIndex, phne2, newValue, IDno, "teachers");
			break;
		case 12:
			int yi = ((rowIndex * getColumnCount())+ columnIndex)-13;
			IDno = Integer.parseInt((String) data.elementAt(yi));
			//("IDNo = "+IDno);
			int ty = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(ty);
			data.add(ty, newValue);
			change.rewrite(rowIndex, columnIndex, commnt, newValue, IDno, "teachers");
			break;
		case 13:
			int yy = ((rowIndex * getColumnCount())+ columnIndex)-14;
			IDno = Integer.parseInt((String) data.elementAt(yy));
			//("IDNo = "+IDno);
			int fd = ((rowIndex * getColumnCount())+ columnIndex);
			data.remove(fd);
			data.add(fd, newValue);
			change.rewrite(rowIndex, columnIndex, ppot, newValue, IDno, "teachers");
			break;
		default:
			return;
		}
	}

	public int getColumnCount() {
		return 14;
	}

	public int getRowCount() {
		return data.size() / getColumnCount();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		////(((rowIndex * getColumnCount())+ columnIndex)+">"+data.elementAt((rowIndex * getColumnCount())+ columnIndex));
		return (String) data.elementAt((rowIndex * getColumnCount())
                + columnIndex);
	}

}
