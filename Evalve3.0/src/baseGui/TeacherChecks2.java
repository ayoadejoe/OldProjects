package baseGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import TeacherPage.MaterialsDialog;
import TeacherPage.TeacherFace2;

public class TeacherChecks2 extends JPanel{
	private ImageIcon iconLoad = new ImageIcon("teaL.png"); 
	private JLabel QuestionLabel = new JLabel(iconLoad); 
	private TeacherFace2 teachr;
	private MaterialsDialog teach;
	public TeacherChecks2() {
		//teach = new TeacherFront(null);
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout());
		Dimension dim = getPreferredSize();
		dim.width = 330;
		dim.height = 330;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		Border outerBorder = BorderFactory.createRaisedBevelBorder();
		
		AbstractButton createCompoundBorder;
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		add(QuestionLabel, BorderLayout.CENTER);
		
		QuestionLabel.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent event1) {
				teachr = new TeacherFace2();
				teachr.setVisible(true);
				//teach.setVisible(true);
			}
			public void mouseEntered(MouseEvent arg0) {
			       QuestionLabel.setForeground(Color.RED);
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
	}

}
