package Administration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MasterTableModel extends AbstractTableModel{
	private static File pwd = new File("master.evl");
	private Vector data;
	private List<String> gather = new LinkedList<String>();
	private Vector columns;
	private int IDno;	private String line = null;
	private Object[] Headings = null;
	public MasterTableModel() {
		data = new Vector();
		columns = new Vector();
		  try {
	          FileInputStream fis = new FileInputStream(pwd);
	          BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	          StringTokenizer st1 = new StringTokenizer(br.readLine(), "<>");
	          while (st1.hasMoreTokens())
	                  columns.addElement(st1.nextToken());
	          while ((line = br.readLine()) != null) {
	                  StringTokenizer st2 = new StringTokenizer(line, "<>");
	                  while (st2.hasMoreTokens())
	                          data.addElement(st2.nextToken());
	          }
	          Headings = columns.toArray();
	          br.close();
	          fis.close();
	          System.gc();
	          //pwd.delete();
	  } catch (Exception e) {
	          e.printStackTrace();
	  }

	}

	public String getColumnName(int column) {
		return (String) Headings[column];
	}
	@Override
	public int getColumnCount() {
		int count = columns.size();
		//("No of Columns = "+count);
		return count;
	}

	@Override
	public int getRowCount() {
		return data.size() / getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return (String) data.elementAt((rowIndex * getColumnCount())
                + columnIndex);
	}

}
