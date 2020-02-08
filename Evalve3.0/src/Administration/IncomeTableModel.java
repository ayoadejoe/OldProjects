package Administration;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class IncomeTableModel extends DefaultTableModel{
	private Vector Headings ;
	private Vector <Object> Data = new Vector <Object> ();
	private AccountClient acquire = new AccountClient();
	public IncomeTableModel(Vector Heading, int space) {
		super(Heading, space);
		Data = acquire.Account("IncomeData");
	}
	
	public void setValueAt(Object aValue, int row, int column) {
		int ki = ((row * getColumnCount())+ 0);			//No
		Object Row0 = Data.elementAt(ki);
		int kf = ((row * getColumnCount())+ 1);			//Session
		Object Row1 = Data.elementAt(kf);
		int ka = ((row * getColumnCount())+ 2);			//Outstanding
		Object Row2 = Data.elementAt(ka);
		int kb = ((row * getColumnCount())+ 3);			//Tuition
		Object Row3 = Data.elementAt(kb);
		//("This is AValue:"+aValue);
		
		int field = (row * getColumnCount())+column;
		//("This is field clicked:"+Data.get(field));
		Data.remove((row * getColumnCount())+column);
		Data.add(field, aValue);
		String ColName = getColumnName(column);
		acquire.change(ColName, aValue, Row0, "incomeaccount");
		super.setValueAt(aValue, row, column);
	}
}
