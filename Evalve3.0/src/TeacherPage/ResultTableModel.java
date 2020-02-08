package TeacherPage;

import java.io.File;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import baseGui.QuestionClient;

public class ResultTableModel extends AbstractTableModel{
	private String[] Headings = new String[]{ "Std. ID", "Name", "TAW", "TAS", 
			"Assg.Score", "TTW", "TTS", "TestScore", "TEW", "TES",  "ExamScore",
			 "TotalScore", "GradePoint"};
	private Vector data = new Vector();
    private Vector columns;
	public ResultTableModel(Vector classIDs) {
		data = classIDs;
	}

	public int getColumnCount() {
		return 13;
	}

	public String getColumnName(int column) {
		return Headings[column];
	}

	public int getRowCount() {
			return data.size();
	}

	public Object getValueAt(int row, int column) {
		Vector rol = new Vector();
		int c = (data.size()*13) - data.size();
		while(c>0){c--;
			rol.add(column);
		}
		System.out.println(row+", "+column);
		if(column == 0){
		System.out.println(column+", "+data.elementAt(column));
		return (String) data.elementAt(column);
		
		}else{
			return (String) rol.elementAt((row * getColumnCount())
	                + column);
		}
	}

}
