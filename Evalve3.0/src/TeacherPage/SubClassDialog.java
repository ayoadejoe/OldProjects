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

public class SubClassDialog extends JDialog {
	private JRadioButton b;
	private ButtonGroup group = new ButtonGroup();
	private FlowLayout pint = new FlowLayout();
	private JButton ok = new JButton("OK");
	private JButton ex = new JButton("EXIT");
	private JFileChooser fc; private File file;
	private Upload upload = new Upload(null, null);
	public SubClassDialog(JFrame parent, final String SubClassCl) {
		super(parent, "ENTER SUBJECT AND CLASS", false);
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		//(SubClassCl);
		final Vector <String> major = new Vector <String>();
		int q = 1; int u = 1;
		try{
		while(q>0){
		String[] parts = SubClassCl.split(",");
		String subject = parts[q].trim().toUpperCase();q=q+1;
		String clazz = parts[q].trim();q= q+1;
		String cl = parts[q].trim();
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
					if(JOptionPane.showConfirmDialog(getContentPane(), "You want to load for "+selected) == JOptionPane.YES_OPTION){
						//(selected);
						setVisible(false);
						fc = new JFileChooser();
						 fc.addChoosableFileFilter(new fileFilter());
				            fc.setAcceptAllFileFilterUsed(true);
					    //Add custom icons for file types.
				            fc.setFileView(new fileFileView());

					    //Add the preview pane.
				            fc.setAccessory(new filePreview(fc));
				            
						int result = fc.showOpenDialog(null);
						if (result == JFileChooser.APPROVE_OPTION){
							file = fc.getSelectedFile();
							try{
								String fileName = file.getName();
								String fileLoc = file.getAbsolutePath();
								File source = new File(fileLoc);
								
								fileFilter w = new fileFilter();
								String whichfile = w.getNature(file);
								if(whichfile.equals("Image")){
									upload.Image(source, selected, fileName, whichfile);
								}else if (whichfile.equals("Audio")||(whichfile.equals("Video"))||(whichfile.equals("Document"))){
									//(fileLoc+" : File Name:"+fileName+", Nature:"+whichfile+" file");
									new uploadThreader(fileName, selected, fileLoc, whichfile);
								}else{
									
								}
							}catch( NullPointerException e1){
								fileFilter w = new fileFilter();
								JOptionPane.showMessageDialog(null, w.getDescription2());
								e1.printStackTrace();
							}catch( Exception e1){
								fileFilter w = new fileFilter();
								JOptionPane.showMessageDialog(null, w.getDescription2());
								e1.printStackTrace();
							}}
					}else{}
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
		setSize(270, Size*45);
		setLocationRelativeTo(getParent());
		setAlwaysOnTop(true);
	}

}
