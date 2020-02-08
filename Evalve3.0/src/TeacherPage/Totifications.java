package TeacherPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Totifications extends JPanel{
	private JLabel notice = new JLabel("SEND A NOTE TO THE ROOM");
	public Totifications() {
		Dimension dim3 = getPreferredSize();
		dim3.height = 250;
		dim3.width = 180;
		setPreferredSize(dim3);
		Border innerBorder2 = BorderFactory.createEtchedBorder(10, Color.GREEN, Color.GRAY);
		Border outerBorder2 = BorderFactory.createRaisedSoftBevelBorder();
		AbstractButton createCompoundBorder2;
		setBorder(BorderFactory.createCompoundBorder(innerBorder2, outerBorder2));
		Color GroundColor = new Color(0, 80, 0);
		setBackground(GroundColor);
		add(notice, BorderLayout.NORTH);
	}

}
