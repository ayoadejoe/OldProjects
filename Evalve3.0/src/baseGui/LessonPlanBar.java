package baseGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LessonPlanBar extends JPanel {
	private JButton submit = new JButton("Submit");
	private JButton save = new JButton("Save");
	private JLabel time = new JLabel("");
	private Timer t ; private int r;
	public LessonPlanBar() {
		add(submit);
		add(save);
		add(time);
		t = new Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Date today = new Date();
				String Today = today.toLocaleString();
				time.setText(Today);
			}
	    });
		
	    t.start();
	    
	    
	    
	    
	    submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				////("This is PK: "+PK);
		
			}
	    });
	    
	    save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
			}
	    });
	}
	public void arrange(String pk) {
		//this.PK = pk;
		
	}
	
}
