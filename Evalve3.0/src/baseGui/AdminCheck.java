package baseGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import Administration.AdminFront;
import Administration.BasicInfoClient;

public class AdminCheck extends JPanel{
	private ImageIcon iconLoad = new ImageIcon("admin.png"); 
	private JLabel QuestionLabel = new JLabel(iconLoad); 
	private JLabel l = new JLabel("Administration");
	private Color Q; int r = 200;
	private AdminFront admin;
	private Timer t;
	private BasicInfoClient g = new BasicInfoClient();
	public AdminCheck() {
		admin = new AdminFront();
		Q = new Color(100, 100, 200);
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout());
		Dimension dim = getPreferredSize();
		dim.width = 320;
		dim.height = 320;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		Border outerBorder = BorderFactory.createRaisedBevelBorder();
		
		AbstractButton createCompoundBorder;
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		add(QuestionLabel, BorderLayout.CENTER);
		
		QuestionLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				String which = JOptionPane.showInputDialog("Please enter your password.").trim();
				boolean confirm  = g.AdminCheck("admin ,"+which);
				if(confirm == true){
				admin.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "The password is incorrect.");
				}
			}
		});
		
	}

}
