package baseGui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TchChatTableModel extends AbstractTableModel{
	private String[] Headings = new String[]{ "NO", "ALL CONTACTS", "COLLEAGUES", "STUDENTS"};
	//private static File pwd = new File("contList.evl");
	private Vector data;
    private Vector columns;
    private String line;
    private TchChatClient settings = new TchChatClient();
    private int IDno;
	public TchChatTableModel(int iD, String username, String password){
		data = new Vector();
		data = settings.getContacts(iD, username, password) ;
	}	
 
	public String getColumnName(int column) {
		return Headings[column];
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return data.size() / getColumnCount();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return (String) data.elementAt((rowIndex * getColumnCount())
                + columnIndex);
	}

}
