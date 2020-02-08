package espTest1;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class ChartExpress extends JDialog{

	public ChartExpress(String title, GeneratorAngel trends) {
		setTitle(title);
		setResizable(true);
		setSize(900, 600);
		setLocationRelativeTo(getParent());
		setLayout(new BorderLayout());
		this.add(trends, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
