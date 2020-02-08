package registration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

public class InformationEntry extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private File DescFile = null;	
	private ArrayList<String> data = null;
	private Scanner line = null;
	private JCheckBox check;
	private JTextPane text = new JTextPane();
	private JScrollPane pane = new JScrollPane();
	private ButtonGroup meet= new ButtonGroup();
	private JButton cancelButton = new JButton("Done");
	private StringInterface selection;
	private Vector<String> transVec = new Vector<String>();
	public static void main(String[] args) {
		try {
			InformationEntry dialog = new InformationEntry(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InformationEntry(File DescFile) {
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformationEntry.class.getResource("/registration/city.gif")));
		setBounds(100, 100, 358, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(216, 191, 216));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			text.setBackground(new Color(216, 191, 216));
			pane.setViewportView(text);
			contentPanel.add(pane);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(216, 191, 216));
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if(DescFile != null){
			try {
				line = new Scanner(DescFile);
				data = new ArrayList();
				while(line.hasNextLine()){
					data.add(line.nextLine());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        for (String dat : data ){
            try {
            check = new JCheckBox(dat);
	        text.insertComponent(check);
	        check.addActionListener(this);
	        //meet.add(check);
			//doc.insertString(doc.getLength(), dat, attr );   Important for setting exam questions
            //text.setCaretPosition(text.getDocument().getLength());
            doc.insertString(doc.getLength(), "\n", attr );
            } catch (BadLocationException e) {
				e.printStackTrace();
			}
        }
	}

	public void actionPerformed(ActionEvent c) {
		JCheckBox checkBox = (JCheckBox) c.getSource();
		if(checkBox.isSelected()){
			System.out.println(checkBox.getActionCommand());
			transVec.add(checkBox.getActionCommand());
			selection.StringBack("+", transVec);
		}else{
			for(int a=0; a<transVec.size(); a++){
				if(checkBox.getActionCommand().equals(transVec.get(a).toString())){
					transVec.remove(a);
					selection.StringBack("-", transVec);
				}
			}
		}
		
	}
	
	public void setStringInterface(StringInterface string){
		this.selection = string;
	}

	public Vector getTransVec() {
		return transVec;
	}

	public void setTransVec(Vector transVec) {
		this.transVec = transVec;
	}
	
}
