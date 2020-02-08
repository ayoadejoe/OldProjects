package registration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.Toolkit;

public class Chains extends JDialog implements FocusListener, ChangeListener{

	private final JPanel contentPanel = new JPanel();
	private int x = 0, y=0, a=200;
	private JSpinner steps = new JSpinner();
	private JSpinner score = new JSpinner();
	private JTextField zenith;
	private JTextField went;
	private JLabel seep;
	private ArrayList <JSpinner> spin = new ArrayList<JSpinner>();
	private ArrayList <JTextField> rolls = new ArrayList<JTextField>();
	private ArrayList <JLabel> label = new ArrayList<JLabel>();
	private JTextField zenithBox;
	private JScrollPane Pane = new JScrollPane();
	private static Vector rake = new Vector();
	private JLabel lblZenith = new JLabel("Zenith:");
	private JLabel lblNoOfSteps = new JLabel("Grade Levels:");
	
	public static Vector getRake() {
		return rake;
	}

	public static void setRake(Vector rake) {
		Chains.rake = rake;
	}

	public static void main(String[] args) {
		try {
			rake.add("Role-Model"); 			rake.add("Outstanding");
			rake.add("Above-Ave");
			rake.add("Average");
			rake.add("Below-Average");
			rake.add("Observation");

			Chains dialog = new Chains(0, rake, "Testing");
			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int rows = 0;
	public Chains(final int row, final Vector rowNames, String heading) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Chains.class.getResource("/registration/city.gif")));
		setBounds(100, 100, 450, 282); rows=row;
		getContentPane().setLayout(new BorderLayout());
		setTitle(heading);
		setVisible(true);
		setAlwaysOnTop(true);
		contentPanel.setPreferredSize(new Dimension(400, a));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Pane.setViewportView(contentPanel);
		getContentPane().add(Pane, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			lblZenith.setHorizontalAlignment(SwingConstants.CENTER);
			lblZenith.setFont(new Font("Tahoma", Font.BOLD, 13));
			contentPanel.add(lblZenith);
		}
		{
			zenithBox = new JTextField();
			zenithBox.setHorizontalAlignment(SwingConstants.CENTER);
			zenithBox.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			zenithBox.setPreferredSize(new Dimension(200, 25));
			contentPanel.add(zenithBox);
			zenithBox.setColumns(35);
			zenithBox.addFocusListener(this);
			zenithBox.setName("0");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				lblNoOfSteps.setHorizontalAlignment(SwingConstants.RIGHT);
				buttonPane.add(lblNoOfSteps);
			}
			if(rows>3){
				contentPanel.setPreferredSize(new Dimension(800, 100));
				setSize(850, 150);
				steps.setVisible(false);
				lblNoOfSteps.setText("Enter maximum no of times a grade would be obtained to be considered. i.e. Role-Model: 2, Observation: 1");
				lblNoOfSteps.setForeground(new Color(252, 55, 50));
			while(x<rows){
				contentPanel.remove(lblZenith);
				contentPanel.remove(zenithBox);
				score = new JSpinner();
				score.addChangeListener(this);
				score.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				score.setPreferredSize(new Dimension(50, 25));
				score.setModel(new SpinnerNumberModel(0, 0, 10, 1));
				
				seep = new JLabel(rowNames.get(x)+":");
				seep.setHorizontalAlignment(SwingConstants.RIGHT);
				seep.setFont(new Font( "Arial", Font.BOLD, 11)); 
				score.setName(x+"");
				spin.add(score);
				contentPanel.add(seep);
				contentPanel.add(spin.get(x));
				x++;
			}
			revalidate();
			rake.clear(); 
			while(rake.size()<6){
				rake.add(0);
				System.out.println(rake);
			}
			}else{
				rake.clear();
				rake.add(0);
			}
			
			{
				steps.setPreferredSize(new Dimension(29, 40));
				steps.setFont(new Font("Cambria Math", Font.PLAIN, 15));
				steps.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				
				steps.addChangeListener(new ChangeListener() {int z =0;
					public void stateChanged(ChangeEvent arg0) {
						System.out.println("prev:"+y+" now:"+steps.getValue());
						if(y<(int)steps.getValue()){
							System.out.println("increasing");
						a = 40*(y);
						contentPanel.setPreferredSize(new Dimension(400, a));
						y = (int) steps.getValue();
						rake.add(y);
						went = new JTextField();
						went.setFont(new Font("Times New Roman", Font.PLAIN, 13));
						went.setPreferredSize(new Dimension(200, 25));
						went.setColumns(35);
						went.setHorizontalAlignment(SwingConstants.CENTER);
						seep = new JLabel("Grade "+y+": ");
						seep.setHorizontalAlignment(SwingConstants.RIGHT);
						seep.setFont(new Font( "Arial", Font.BOLD, 13)); 
						went.setName(""+(y-1));
						went.addFocusListener(Chains.this);
						rolls.add(went);
						label.add(seep);
						contentPanel.add(label.get(y-2));
						contentPanel.add(rolls.get(y-2));
						System.out.println("rake after addition:"+rake);
						}else{
							System.out.println("decreasing");
							System.out.println("rolls before:"+rolls.size());
							y = (int) steps.getValue();
							rake.remove(y);
							System.out.println("removing position:"+(y-1));
							rolls.get(y-1).setVisible(false);
							label.get(y-1).setVisible(false);
							contentPanel.remove(rolls.get(y-1));
							rolls.remove(y-1);
							label.remove(y-1);
							System.out.println("rolls after removal:"+rolls.size());
							System.out.println(rake);
						}
						revalidate();
					}
				});
				steps.setModel(new SpinnerNumberModel(1, 1, 20, 1));
				buttonPane.add(steps);
			}
			{
				JLabel label = new JLabel("<<Navigate with the arrow   ");
				buttonPane.add(label);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void focusGained(FocusEvent g) {
		JTextField what = (JTextField)g.getSource();
		what.setBackground(new Color(120, 200, 250));
	}

	public void focusLost(FocusEvent g) {
			JTextField what = (JTextField)g.getSource();
			int pos = Integer.parseInt(what.getName());
			rake.remove(pos);	rake.add(pos, what.getText().trim());
			System.out.println(rake);
	}

	public void stateChanged(ChangeEvent c) {
		JSpinner what = (JSpinner)c.getSource();
		int pos = Integer.parseInt(what.getName());
		rake.remove(pos);	rake.add(pos, what.getValue());
		System.out.println(rake);
	}

}
