package espTest1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ProgressDialog extends JDialog {
	private JProgressBar progressB;
	private JTextArea progressInfo;
	private JPopupMenu popupMenu;
	private JMenuItem mntmCloseWait;
	private JMenuItem mntmFreezeProblems;
	private JLabel lblWorking;
	public ProgressDialog(Window parent, String type) {
		super(parent, "Please wait", ModalityType.APPLICATION_MODAL);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProgressDialog.class.getResource("/espTest1/4.png")));
		setSize(300, 200);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(getParent());
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		getContentPane().add(panel);
		
		lblWorking = new JLabel("");
		lblWorking.setIcon(new ImageIcon(ProgressDialog.class.getResource("/espTest1/4.png")));
		panel.add(lblWorking);
		progressB = new JProgressBar();
		progressB.setMaximum(4);
		progressB.setStringPainted(true);
		panel.add(progressB);
		
		progressInfo = new JTextArea();
		Dimension size = progressInfo.getPreferredSize();
		size.width = 400;
		progressB.setPreferredSize(size);
		progressInfo.setPreferredSize(new Dimension(50, 22));
		progressInfo.setColumns(10);
		progressInfo.setRows(1);
		progressInfo.setWrapStyleWord(true);
		progressInfo.setLineWrap(true);
		progressInfo.setBackground(new Color(100, 149, 237));
		progressInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		progressInfo.setText("Hello...");
		getContentPane().add(progressInfo, BorderLayout.SOUTH);
		
		pack();
		
		popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);
		
		mntmCloseWait = new JMenuItem("Exit");
		popupMenu.add(mntmCloseWait);
		
		mntmFreezeProblems = new JMenuItem("Freeze Problems");
		popupMenu.add(mntmFreezeProblems);
	}

	public void setMaximum(int value){
		progressB.setMaximum(value);
	}
	
	public void setValue(int value){
		progressB.setValue(value);
	}
	
	public void setMessage(String message){
		progressInfo.setText(message);
	}
	
	public void setVisible(final boolean visible){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				ProgressDialog.super.setVisible(visible);
			}
		});
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
