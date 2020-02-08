package espTest1;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;

public class ProgressDisplay extends JProgressBar{
	private JProgressBar progressB;
	private JTextArea progressInfo;
	private JPopupMenu popupMenu;
	private JMenuItem mntmCloseWait;
	private JMenuItem mntmFreezeProblems;
	private JLabel lblWorking;
	
	
	
	@Override
	public void addChangeListener(ChangeListener arg0) {
		// TODO Auto-generated method stub
		super.addChangeListener(arg0);
	}

	@Override
	public void setMinimum(int arg0) {
		// TODO Auto-generated method stub
		super.setMinimum(0);
	}

	@Override
	public void setString(String arg0) {
		super.setString(arg0);
	}

	public void setStringPainted(boolean arg0) {
		super.setStringPainted(true);
	}

	public ProgressDisplay(Window parent, String type) {
		this.setMaximum(4);
		this.setStringPainted(true);
		
		popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);
		
		mntmCloseWait = new JMenuItem("Exit");
		popupMenu.add(mntmCloseWait);
		
		mntmFreezeProblems = new JMenuItem("Freeze Problems");
		popupMenu.add(mntmFreezeProblems);
	}

	public void setMaximum(int value){
		super.setMaximum(value);
	}
	
	public void setValue(int value){
		super.setValue(value);
	}
	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
