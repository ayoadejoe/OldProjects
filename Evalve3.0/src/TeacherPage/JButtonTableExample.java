package TeacherPage;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventObject;
import java.util.Hashtable;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class JButtonTableExample extends JFrame{

	public JButtonTableExample() {
		 super("JButtonTable Example");    
		 DefaultTableModel dm = new DefaultTableModel();    
		 dm.setDataVector(new Object[][] { { "button 1", "food is ready" },{ "button 2", "bar is for lawyers" },
				 { "button 3", " is empty" } },  
				 new Object[] {"Button", "Heading", "Component" });    
		 JTable table = new JTable(dm);    
		 table.getColumn("Button").setCellRenderer(new ButtonRenderer());    
		 table.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));
		 CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();    
		 EachRowRenderer rowRenderer = new EachRowRenderer();    
		 rowRenderer.add(0, checkBoxRenderer);    
		 rowRenderer.add(1, checkBoxRenderer); 
		 rowRenderer.add(2, checkBoxRenderer);
		 JCheckBox checkBox = new JCheckBox();   
		 checkBox.setHorizontalAlignment(JLabel.CENTER); 
		 DefaultCellEditor checkBoxEditor = new DefaultCellEditor(checkBox);
		 
		 EachRowEditor rowEditor = new EachRowEditor(table);    
			rowEditor.setEditorAt(0, checkBoxEditor);    
			rowEditor.setEditorAt(1, checkBoxEditor); 
			rowEditor.setEditorAt(2, checkBoxEditor);
		 table.getColumn("Component").setCellRenderer(rowRenderer);    
		 table.getColumn("Component").setCellEditor(rowEditor);
		 JScrollPane scroll = new JScrollPane(table);    
		 getContentPane().add(scroll);    
		 setSize(400, 300);    
		 setVisible(true);  
		 }  
	
	public static void main(String[] args) {         
		JButtonTableExample frame = new JButtonTableExample();   
		frame.addWindowListener(new WindowAdapter() {      
			public void windowClosing(WindowEvent e) {        
				System.exit(0);      
				}    
			});  
		}
	
	class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {  
		CheckBoxRenderer() {    
			setHorizontalAlignment(JLabel.CENTER);  
			}  
		public Component getTableCellRendererComponent(JTable table, Object value,      
				boolean isSelected, boolean hasFocus, int row, int column) {    
			if (isSelected) {      
				setForeground(table.getSelectionForeground());     
			super.setBackground(table.getSelectionBackground());      
			setBackground(table.getSelectionBackground());    
			} else {      
				setForeground(table.getForeground());      
				setBackground(table.getBackground());    
				}   
			setSelected((value != null && ((Boolean) value).booleanValue()));    
			return this;  
			} 
		} 
	
	class ButtonRenderer extends JButton implements TableCellRenderer {  
		public ButtonRenderer() {    
			setOpaque(true);  
			}  
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {    
			if (isSelected) {      
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());    
				} 
			else {     
				setForeground(table.getForeground());      
				setBackground(UIManager.getColor("Button.background"));    
				}
			  setText((value == null) ? "" : value.toString());    
			  return this;  
			  } 
		}
	
	class EachRowRenderer implements TableCellRenderer {  
		protected Hashtable renderers;  
		protected TableCellRenderer renderer, 
		defaultRenderer;  
		public EachRowRenderer() {    
			renderers = new Hashtable();    
			defaultRenderer = new DefaultTableCellRenderer();  
			}  
		public void add(int row, TableCellRenderer renderer) {    
			renderers.put(new Integer(row), renderer);  
			}  
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			renderer = (TableCellRenderer) renderers.get(new Integer(row));    
			if (renderer == null) {      
				renderer = defaultRenderer;    
				}    
			return renderer.getTableCellRendererComponent(table, value, isSelected, 
					hasFocus, row, column);  
			} 
		}
	class ButtonEditor extends DefaultCellEditor {  
		protected JButton button;  
		private String label;  
		private boolean isPushed;  
		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);    
			button = new JButton();    
			button.setOpaque(true);    
			button.addActionListener(new ActionListener() {      
				public void actionPerformed(ActionEvent e) {        
					fireEditingStopped();      
					}    
				});  
			}  
		public Component getTableCellEditorComponent(JTable table, Object value,      
				boolean isSelected, int row, int column) {    
			if (isSelected) {      
				button.setForeground(table.getSelectionForeground());     
				button.setBackground(table.getSelectionBackground());    
				} else {      
					button.setForeground(table.getForeground());      
					button.setBackground(table.getBackground());    
					}    
			label = (value == null) ? "" : value.toString();    
					button.setText(label);    
					isPushed = true;    
					return button;  
					}  
		public Object getCellEditorValue() {    
			if (isPushed) {     
				JOptionPane.showMessageDialog(button, label + ": Ouch!");      
				//(label + ": Ouch!");   
				}    isPushed = false;    
				return new String(label);  
				}  public boolean stopCellEditing() {
					isPushed = false;    
					return super.stopCellEditing();  
					}  protected void fireEditingStopped() {    
						super.fireEditingStopped();  
						} 
					}
	class EachRowEditor implements TableCellEditor {  
		protected Hashtable editors;  
		protected TableCellEditor editor, defaultEditor;  
		JTable table;
		 public EachRowEditor(JTable table) {    
			 this.table = table;    
			 editors = new Hashtable();    
			 }
		 public void setEditorAt(int row, TableCellEditor editor) {    
			 editors.put(new Integer(row), editor);  
			 }  
		 
		 public Component getTableCellEditorComponent(JTable table, Object value, 
				 boolean isSelected, int row, int column) {   
			 editor = (TableCellEditor)editors.get(new Integer(row));    
			 ////(editor.toString());
			 if (isSelected) {     
					JOptionPane.showMessageDialog(null, value + ": Ouch!");      
					//(row + ": Ouch!");   
					}  
			 if (editor == null) {    //  editor = defaultEditor;   
			 }    
			 return editor.getTableCellEditorComponent(table, value, isSelected, row, column);  
			 }  
		 public Object getCellEditorValue() {    
			 return editor.getCellEditorValue();  
			 }  
		 public boolean stopCellEditing() {    
			 return editor.stopCellEditing();  
			 }  
		 public void cancelCellEditing() {    
			 editor.cancelCellEditing();  
			 }  
		 public boolean isCellEditable(EventObject anEvent) {    
			 selectEditor((MouseEvent) anEvent);    
			 return editor.isCellEditable(anEvent);  
			 }  
		 public void addCellEditorListener(CellEditorListener l) {   
			 editor.addCellEditorListener(l);  
			 }  
		 public void removeCellEditorListener(CellEditorListener l) {    
			 editor.removeCellEditorListener(l);  
			 }  
		 public boolean shouldSelectCell(EventObject anEvent) {    
			 selectEditor((MouseEvent) anEvent);    
			 return editor.shouldSelectCell(anEvent);  
			 }
		 protected void selectEditor(MouseEvent e) {    
			 int row;    
			 if (e == null) {      
				 row = table.getSelectionModel().getAnchorSelectionIndex();    
				 } else {      
					 row = table.rowAtPoint(e.getPoint());    
					 }    
			 editor = (TableCellEditor) editors.get(new Integer(row));    
			 if (editor == null) {      
				 editor = defaultEditor;    
				 }  
			 } 
		 }
}
