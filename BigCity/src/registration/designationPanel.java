package registration;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class designationPanel extends JPanel implements ActionListener{
	private JCheckBox chckbxDefault = new JCheckBox("Default");
	private JCheckBox desigs;
	private Vector applicable = new Vector();
	
	public designationPanel(ArrayList designations) {
		setBackground(getBackground());
		setLayout(new GridLayout(designations.size(), 1, 5, 5));
		
		chckbxDefault.setBackground(new Color(216, 191, 216));
		chckbxDefault.addActionListener(this);
		
		for (int a=0; a<designations.size(); a++){
			String des = designations.get(a).toString().split(">")[1]+"("+designations.get(a).toString().split(">")[2]+")";
			System.out.println(des);
			if(a==0){
				chckbxDefault.setText(des);
				chckbxDefault.setName(des);
				add(chckbxDefault);
			}else{
				desigs = new JCheckBox(des);
				desigs.setName(des);
				desigs.setBackground(new Color(204, 255, 0));
				add(desigs);
				desigs.addActionListener(this);
			}
			
		}
	}

	public void actionPerformed(ActionEvent w) {
		desigs = (JCheckBox) w.getSource();
		if(desigs.isSelected()){
			applicable.add(desigs.getName());
		}else{
			for(int e=0; e<applicable.size(); e++){
				if(applicable.get(e).toString().equals(desigs.getName())){
					applicable.remove(e);
				}}
		}
		System.out.println(applicable);
	}
	
	public Vector getApplicable() {
		return applicable;
	}

	public void setApplicable(Vector applicable) {
		this.applicable = applicable;
	}


}
