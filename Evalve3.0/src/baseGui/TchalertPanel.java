package baseGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

public class TchalertPanel extends JPanel{
	private TchChatClient getDrops = new TchChatClient();
	private JLabel ds = null; 		
	private String rec = "";
	public TchalertPanel(final int iD, final String username, final String password) {
		setBackground(new Color(100, 100, 230));
		setLayout(new FlowLayout());
		Dimension dim = getPreferredSize();
		dim.width = 450;
		dim.height = 220;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		Border outerBorder = BorderFactory.createRaisedBevelBorder();
		
		AbstractButton createCompoundBorder;
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		
				Vector drops = getDrops.Drops(iD, username, password);
				if(drops.size()>0){
				if(!drops.get(0).equals("Empty")){
				int noOfDrops = drops.size();
				while(noOfDrops>0){noOfDrops--;
					ds = new JLabel("| "+drops.get(noOfDrops)+ " dropped a message |          ");
					ds.setFont(new Font("Pristina", Font.BOLD, 18)); 		//gigi, forte
					ds.setForeground(new Color(250, 100, 120));
					add(ds);
				}
				}else{
					ds = new JLabel("| Nobody dropped a message |          ");
					ds.setFont(new Font("comic sans", Font.BOLD, 15)); 		//gigi, forte
					ds.setForeground(new Color(200, 100, 100));
					remove(ds);
					add(ds);
				}}else{
					ds = new JLabel("| Nobody dropped a message |          ");
					ds.setFont(new Font("comic sans", Font.BOLD, 15)); 		//gigi, forte
					ds.setForeground(new Color(200, 100, 100));
					remove(ds);
					add(ds);
				}
	}

}
