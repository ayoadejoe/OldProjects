package baseGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class LoadQuestions extends JPanel{
	private ImageIcon iconLoad = new ImageIcon("loadQF.png"); 
	private JLabel QuestionLabel = new JLabel(iconLoad); 
	ImageIcon icon2 = new ImageIcon("loadQM.jpg");
	private SelectionExam selectionExam;
	JButton QuestionBut = new JButton("Click Here To Load Questions");
	Color Q;
	
	public LoadQuestions() {
		selectionExam = new SelectionExam(null, 0, null, null, null, null);
		Q = new Color(100, 100, 200);
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout());
		
		Dimension dim = getPreferredSize();
		dim.width = 310;
		dim.height = 310;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		Border outerBorder = BorderFactory.createRaisedBevelBorder();
		
		AbstractButton createCompoundBorder;
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));

		add(QuestionLabel, BorderLayout.CENTER);
		
		
		QuestionLabel.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent event1) {
				//String b = JOptionPane.showInputDialog("Enter your value here.");
				selectionExam.setVisible(true);
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
