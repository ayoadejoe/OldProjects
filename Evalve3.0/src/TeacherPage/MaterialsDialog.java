package TeacherPage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Administration.ImageFileView;
import Administration.ImageFilter;
import Administration.ImagePreview;

public class MaterialsDialog extends JDialog{
	private SubClassDialog subC;
	private JLabel Username, pw, headr, space;
	private JRadioButton idd;
	private JRadioButton ppw;
	private JButton ent;
	private ButtonGroup group;
	
	public MaterialsDialog(JFrame parent, final String SubClazz) {
		super(parent, "MATERIALS", false);
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Color AdminC2 = new Color(20, 0, 130);
		getContentPane().setBackground(AdminC2);
		setLocationRelativeTo(getParent());
		Username = new JLabel("View Class Materials");
		idd = new JRadioButton();
		idd.setActionCommand("View");
		pw = new JLabel("Load Materials");
		ppw = new JRadioButton();
		ppw.setActionCommand("Load");
		headr = new JLabel("LIBRARIES");
		space = new JLabel("____________________________________________");
		ent = new JButton("Enter");
		headr.setFont(new Font("georgia", Font.BOLD, 16));
		Username.setFont(new Font("georgia", Font.BOLD, 12));
		pw.setFont(new Font("georgia", Font.BOLD, 12));
		add(headr);
		add(space);
		add(Username); add(idd); add(pw); add(ppw);
		space = new JLabel("______________________________________________");
		add(space);
		add(ent);
		
		group = new ButtonGroup();
		group.add(idd);
		group.add(ppw);
		setSize(250, 220);
		setLayout(new FlowLayout());
		
		ent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String Username = idd.getText();
				String pw = ppw.getText();
				String what = group.getSelection().getActionCommand();
				
				if(what.equals("Load")){
					subC = new SubClassDialog(null, SubClazz);
					setVisible(false);
					subC.setVisible(true);
				}else if (what.equals("View")){
					new MaterialsFileTable();
				}
				setVisible(false);
			}

		});
	}
}
