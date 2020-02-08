package baseGui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressDisplay extends JDialog {

	private JButton cancelButton;
	private JProgressBar progressB;
	public ProgressDisplay(Window parent, String type) {
		super(parent, "Wait, "+type+" your file...", ModalityType.APPLICATION_MODAL);
		setSize(300, 200);
		
		cancelButton = new JButton("cancel");
		progressB = new JProgressBar();
		
		setLayout(new FlowLayout());
		
		Dimension size = cancelButton.getPreferredSize();
		size.width = 400;
		progressB.setPreferredSize(size);
		
		add(progressB);
		add(cancelButton);
		
		pack();
	}

	public void setMaximum(int value){
		progressB.setMaximum(value);
	}
	
	public void setValue(int value){
		progressB.setValue(value);
	}
	
	public void setVisible(final boolean visible){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				ProgressDisplay.super.setVisible(visible);
			}
		});
	}
}
