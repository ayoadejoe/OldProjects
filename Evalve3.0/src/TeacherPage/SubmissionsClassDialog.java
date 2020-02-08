package TeacherPage;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class SubmissionsClassDialog extends JDialog {
	private JRadioButton b;
	private ButtonGroup group = new ButtonGroup();
	private FlowLayout pint = new FlowLayout();
	private JButton ok = new JButton("OK");
	private JButton ex = new JButton("EXIT");
	private JFileChooser fc; private File file;
	private Upload upload = new Upload(null, null);
	private String subject = null, clazz = null, cl = null;
	public SubmissionsClassDialog(JFrame parent, final String SubClassCl) {
		super(parent, "ENTER SUBJECT AND CLASS", false);
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		//(SubClassCl);
		final Vector <String> major = new Vector <String>();
		int q = 1; int u = 1;
		try{
		while(q>0){
		String[] parts = SubClassCl.split(",");
		subject = parts[q].trim().toUpperCase();q=q+1;
		clazz = parts[q].trim();q= q+1;
		cl = parts[q].trim();
		major.add(subject+", "+clazz);
		q = q+1;u++;
		}
		}catch(ArrayIndexOutOfBoundsException f){}
		int Size = major.size(); int d = 0;
		setLayout(pint);
		while(d<Size){
			b = new JRadioButton(major.get(d));
			b.setActionCommand(major.get(d));
			group.add(b);
			add(b, pint.LEFT);
			d++;
		}
		
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String selected = group.getSelection().getActionCommand();
				if(group.getSelection().getActionCommand() != null){
					String[] parts = selected.split(",");
					subject = parts[0].trim().toUpperCase();
					clazz = parts[1].trim().toUpperCase();
						setVisible(false);
						new Submissions(null, subject, clazz, cl);
				}
				
			}
			
		});
		
		ex.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
						dispose();
			}
		});
		
		add(new JLabel("     "));
		add(ok);
		add(ex);
		setVisible(true);
		setSize(270, Size*45);
		setLocationRelativeTo(getParent());
		setAlwaysOnTop(true);
	}

}
