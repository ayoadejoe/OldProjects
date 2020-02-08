package baseGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PictureLoader extends JDialog{
	private JLabel Good = new JLabel("This is good");
	private JPanel pic = new JPanel();
	private JLabel image = new JLabel("");
	private JScrollPane panelW ;
	private JSlider x; 		private int y = 400;
	public PictureLoader(JFrame parent, String No, String Question, final Image passp) {
		super(parent, "QUESTION "+No+" DIAGRAM", true );
		setSize(800, 600);
		setLocationRelativeTo(parent);
		pic.setBackground(Color.black);
		panelW = new JScrollPane(pic);
		setLayout(new BorderLayout());
		//("In Picture: "+Question);
		String labelText = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 670, ""+Question+"" );
		Good.setText(labelText);
		add(Good, BorderLayout.NORTH);
		 x = new JSlider(SwingConstants.HORIZONTAL, 0, 500, 250 );
		 x.setMajorTickSpacing( 10 ); // create tick every 10
		 x.setPaintTicks( true ); // paint ticks on slider   
		 
		 x.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				y = x.getValue()+200;
				//(y);
				if(passp != null){
					Image scaled = passp.getScaledInstance(y+100, y, BufferedImage.SCALE_SMOOTH);
					image.setIcon(new ImageIcon(scaled));
					image.setVisible(true);
					
					Dimension dim = panelW.getPreferredSize();
					dim.width = 500;
					dim.height = 500;
					panelW.setPreferredSize(dim);
					Border innerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
					Border outerBorder = BorderFactory.createRaisedBevelBorder();
					
					AbstractButton createCompoundBorder;
					panelW.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
					add(panelW, BorderLayout.CENTER);
					pic.add(image);
					}else{
						add(new JLabel("No Image to Display!"), BorderLayout.CENTER);
					}
				revalidate();
			}
		 });
		if(passp != null){
		Image scaled = passp.getScaledInstance(y+100, y, BufferedImage.SCALE_SMOOTH);
		image.setIcon(new ImageIcon(scaled));
		image.setVisible(true);
		
		Dimension dim = panelW.getPreferredSize();
		dim.width = 500;
		dim.height = 500;
		panelW.setPreferredSize(dim);
		Border innerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		Border outerBorder = BorderFactory.createRaisedBevelBorder();
		
		AbstractButton createCompoundBorder;
		panelW.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		add(panelW, BorderLayout.CENTER);
		pic.add(image);
		}else{
			add(new JLabel("No Image to Display!"), BorderLayout.CENTER);
		}
		add(x, BorderLayout.SOUTH);
		setVisible(true);
	}

}
