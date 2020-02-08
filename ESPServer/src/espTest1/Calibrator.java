package espTest1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JProgressBar;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;

public class Calibrator extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Calibrator dialog = new Calibrator("75kVA GENERATOR");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSlider slider = new JSlider();
	private JProgressBar tankIndicator = new JProgressBar();
	private JSpinner maxim = new JSpinner();
	private JSpinner minim = new JSpinner();
	private IntInterface calib;
	private int max, min;
	private String resp;
	private final JCheckBox inversion = new JCheckBox("Invert Values");
	private final JLabel label = new JLabel("                                                               ");
	Calibrator(String who) {
		
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Calibrator.class.getResource("/espTest1/5.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBackground(new Color(255, 255, 204));
		setBounds(100, 100, 450, 300);
		setTitle("CALIBRATE "+who);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(204, 255, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 41, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			tankIndicator.setFont(new Font("Cambria Math", Font.PLAIN, 13));
			tankIndicator.setStringPainted(true);
			tankIndicator.setOrientation(SwingConstants.VERTICAL);
			GridBagConstraints gbc_tankIndicator = new GridBagConstraints();
			gbc_tankIndicator.insets = new Insets(0, 0, 0, 5);
			gbc_tankIndicator.fill = GridBagConstraints.BOTH;
			gbc_tankIndicator.gridheight = 6;
			gbc_tankIndicator.gridwidth = 2;
			gbc_tankIndicator.gridx = 11;
			gbc_tankIndicator.gridy = 1;
			contentPanel.add(tankIndicator, gbc_tankIndicator);
		}
		{
			JLabel lblEnterMaximum = new JLabel("Enter Maximum:");
			lblEnterMaximum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			GridBagConstraints gbc_lblEnterMaximum = new GridBagConstraints();
			gbc_lblEnterMaximum.anchor = GridBagConstraints.EAST;
			gbc_lblEnterMaximum.gridwidth = 3;
			gbc_lblEnterMaximum.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterMaximum.gridx = 1;
			gbc_lblEnterMaximum.gridy = 2;
			contentPanel.add(lblEnterMaximum, gbc_lblEnterMaximum);
		}
		{
			maxim.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					max = (int) maxim.getValue();
					slider.setMaximum(max);
					tankIndicator.setMaximum(max);
				}
			});
			maxim.setBackground(new Color(204, 255, 204));
			maxim.setForeground(new Color(0, 153, 51));
			maxim.setModel(new SpinnerNumberModel(0, 0, 50000, 100));
			maxim.setFont(new Font("Cambria Math", Font.BOLD, 17));
			GridBagConstraints gbc_maxim = new GridBagConstraints();
			gbc_maxim.fill = GridBagConstraints.HORIZONTAL;
			gbc_maxim.gridwidth = 4;
			gbc_maxim.insets = new Insets(0, 0, 5, 5);
			gbc_maxim.gridx = 4;
			gbc_maxim.gridy = 2;
			contentPanel.add(maxim, gbc_maxim);
		}
		{
			JLabel lblEnterMinimum = new JLabel("Enter Minimum:");
			lblEnterMinimum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			GridBagConstraints gbc_lblEnterMinimum = new GridBagConstraints();
			gbc_lblEnterMinimum.anchor = GridBagConstraints.EAST;
			gbc_lblEnterMinimum.fill = GridBagConstraints.VERTICAL;
			gbc_lblEnterMinimum.gridwidth = 3;
			gbc_lblEnterMinimum.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterMinimum.gridx = 1;
			gbc_lblEnterMinimum.gridy = 4;
			contentPanel.add(lblEnterMinimum, gbc_lblEnterMinimum);
		}
		{
			minim.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					min = (int) minim.getValue();
					slider.setMinimum(min);
					tankIndicator.setMinimum(min);
				}
			});
			minim.setBackground(new Color(204, 153, 255));
			minim.setForeground(new Color(153, 0, 51));
			minim.setModel(new SpinnerNumberModel(0, 0, 50000, 100));
			minim.setFont(new Font("Cambria Math", Font.BOLD, 17));
			GridBagConstraints gbc_minim = new GridBagConstraints();
			gbc_minim.fill = GridBagConstraints.HORIZONTAL;
			gbc_minim.gridwidth = 4;
			gbc_minim.insets = new Insets(0, 0, 5, 5);
			gbc_minim.gridx = 4;
			gbc_minim.gridy = 4;
			contentPanel.add(minim, gbc_minim);
		}
		{
			GridBagConstraints gbc_slider = new GridBagConstraints();
			gbc_slider.fill = GridBagConstraints.HORIZONTAL;
			gbc_slider.gridwidth = 8;
			gbc_slider.insets = new Insets(0, 0, 5, 5);
			gbc_slider.gridx = 2;
			gbc_slider.gridy = 6;
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					System.out.println(slider.getValue());
					tankIndicator.setValue(slider.getValue());
					tankIndicator.setString(slider.getValue()+" L");
					Calibrator.this.revalidate();
				}
			});
			slider.setBackground(new Color(204, 255, 204));
			slider.setValue(0);
			slider.setPaintLabels(true);
			slider.setPaintTicks(true);
			slider.setMinorTickSpacing(25);
			slider.setForeground(new Color(51, 102, 153));
			slider.setMajorTickSpacing(50);
			contentPanel.add(slider, gbc_slider);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(204, 255, 204));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(inversion.isSelected())calib.StringBack(min, max, resp);		//interchange values because this is actually inverse in resistor
						if(!inversion.isSelected())calib.StringBack(max, min, resp);		
					}
				});
				{
					inversion.setBackground(new Color(204, 255, 204));
					inversion.setHorizontalAlignment(SwingConstants.LEFT);
					buttonPane.add(inversion);
				}
				{
					buttonPane.add(label);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Calibrator.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}
	
	public void setIntInterface(IntInterface intInterface) {
		this.calib = intInterface;
	}

}
