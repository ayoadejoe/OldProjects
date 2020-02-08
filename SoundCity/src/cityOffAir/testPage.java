package cityOffAir;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

public class testPage extends JFrame{
	static Calendar one;
	static Timer t;
	static int z=0;
	static Random x;
	public testPage(){
		setVisible(true);
		x = new Random();
		JPasswordField pass = new JPasswordField("password");
		JOptionPane pane = new JOptionPane();
		String user = pane.showInputDialog(this,pass, "No existing User found. Please enter a Username.", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(user+" pass:"+pass.getText());
	
	}
	public static void main(String[] args) {
		t = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {z++;
				one = Calendar.getInstance();
				//System.out.println(one.getTimeInMillis());
				//System.out.println(one.getTime());
				int g = x.nextInt(7);
				System.out.println(g);
				if(z==10)t.stop();
			}
		});
		//System.out.println(t.getDelay());
		t.start();
		new testPage();
	}
}
