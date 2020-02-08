package espTest1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.GridLayout;

class DeleteTable extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	public static void main(String[] args) {
		try {
			Vector rat = new Vector(); rat.add("20:11");rat.add("123");rat.add("10:11");
			DeleteTable dialog = new DeleteTable(rat);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JCheckBox loog;
	private Vector<String> toDelete = new Vector <String>();
	private VectorInterface calib;
	private JScrollPane pane = new JScrollPane();
	DeleteTable(List<Object> prevBigData) {
		setBounds(100, 100, 650, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPanel.setPreferredSize(new Dimension(800, 600));
		int logNo = prevBigData.size();
		int grid = logNo/100;
		pane.setViewportView(contentPanel);
		contentPanel.setLayout(new GridLayout(100, grid, 2, 2));
		getContentPane().add(pane, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		
		for(int y =0; y<logNo; y++){
			loog = new JCheckBox(""+prevBigData.get(y));
			loog.setName(""+y);
			loog.addActionListener(this);
			contentPanel.add(loog);
			System.out.println(prevBigData.get(y));
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Vector <Integer> intAdd = new Vector<Integer>();
						for(int t=0; t<toDelete.size(); t++){
							int address = Integer.parseInt((String)toDelete.get(t));
							intAdd.add(address);
						}
						calib.vectorBack(intAdd, null);
						DeleteTable.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						DeleteTable.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	public void actionPerformed(ActionEvent f) {
		JCheckBox checked = (JCheckBox)f.getSource();
				if(checked.isSelected()){
					toDelete.add(checked.getName());
					System.out.println(toDelete);
				}else{
					for(int r =0; r<toDelete.size(); r++){
						if(toDelete.get(r).equals(checked.getName())){
							toDelete.remove(r);
						}
						System.out.println(toDelete);
					}
				}
	}
	
	public void setvectorInterface(VectorInterface vecInterface) {
		this.calib = vecInterface;
	}

}
