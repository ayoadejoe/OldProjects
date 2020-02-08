package Administration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class Finance extends JFrame{
	private AccountClient up = new AccountClient();
	private JPanel bigPanel = new JPanel();
	private JPanel StudentAccount = new JPanel();
	private JPanel IncomeAccount = new JPanel();
	private JPanel ExpenseAccount = new JPanel();
	private JPanel TeacherAccount = new JPanel();
	private JPanel BalanceSheet = new JPanel();
	private Font buttFont = new Font("serif", Font.BOLD, 20);
	private Font buttFont2 = new Font("serif", Font.BOLD, 16);
	public Finance() {
		super("FINANCE");
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setUndecorated(false);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		setVisible(true);
		setResizable(true);
		setSize(1000, 650);
		setLocation(170, 50);
		setLayout(new BorderLayout());
		
		Dimension dim1 = bigPanel.getPreferredSize();
		dim1.height = 990;
		dim1.width = 640;
		bigPanel.setPreferredSize(dim1);
		bigPanel.setBackground(new Color(50, 100, 200));
		add(bigPanel, BorderLayout.CENTER);
		
		bigPanel.setLayout(new GridBagLayout());
		GridBagConstraints align = new GridBagConstraints();
		
		Dimension dim = ExpenseAccount.getPreferredSize();
		dim.height = 130;
		dim.width = 220;
		ExpenseAccount.setPreferredSize(dim);
		ExpenseAccount.setBackground(new Color(200, 100, 50));
		Border innerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		Border outerBorder = BorderFactory.createRaisedBevelBorder();
		AbstractButton createCompoundBorder;
		ExpenseAccount.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		JLabel exp = new JLabel(" EXPENSE ACCOUNTS"); exp.setFont(buttFont);
		ExpenseAccount.setLayout(new BorderLayout());
		ExpenseAccount.add(exp, BorderLayout.CENTER);
		
		IncomeAccount.getPreferredSize();
		IncomeAccount.setPreferredSize(dim);
		IncomeAccount.setBackground(new Color(50, 100, 230));
		IncomeAccount.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		JLabel exp2 = new JLabel(" INCOME ACCOUNTS"); exp2.setFont(buttFont);
		IncomeAccount.setLayout(new BorderLayout());
		IncomeAccount.add(exp2, BorderLayout.CENTER);
		
		BalanceSheet.getPreferredSize();
		BalanceSheet.setPreferredSize(dim);
		BalanceSheet.setBackground(Color.GRAY);
		BalanceSheet.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		JLabel exp3 = new JLabel("    BALANCE SHEET"); exp3.setFont(buttFont);
		BalanceSheet.setLayout(new BorderLayout());
		BalanceSheet.add(exp3, BorderLayout.CENTER);
		
		Dimension dim2 = StudentAccount.getPreferredSize();
		dim2.height = 100;
		dim2.width = 200;
		StudentAccount.setPreferredSize(dim2);
		StudentAccount.setBackground(new Color(50, 250, 50));
		StudentAccount.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		JLabel exp4 = new JLabel("    STUDENT ACCOUNT"); exp4.setFont(buttFont2);
		StudentAccount.setLayout(new BorderLayout());
		StudentAccount.add(exp4, BorderLayout.CENTER);
		
		TeacherAccount.getPreferredSize();
		TeacherAccount.setPreferredSize(dim2);
		TeacherAccount.setBackground(new Color(200, 200, 50));
		TeacherAccount.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		JLabel exp5 = new JLabel("   TEACHER ACCOUNT"); exp5.setFont(buttFont2);
		TeacherAccount.setLayout(new BorderLayout());
		TeacherAccount.add(exp5, BorderLayout.CENTER);
		
		align.insets = new Insets(100, 0, 0, 500);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		bigPanel.add(IncomeAccount, align);
		
		align.insets = new Insets(0, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		bigPanel.add(BalanceSheet, align);
		
		align.insets = new Insets(100, 500, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		bigPanel.add(ExpenseAccount, align);
		
		align.insets = new Insets(300, 250, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		bigPanel.add(StudentAccount, align);
		
		align.insets = new Insets(300, 0, 0, 250);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		bigPanel.add(TeacherAccount, align);
		
		up.Account("ArrangeStudent");
		up.Account("Balancing");
		
		ExpenseAccount.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				new ExpenseAccount();
			}
		});
		
		IncomeAccount.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				new IncomeAccount();
			}
		});
		
		BalanceSheet.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				new BalanceSheet();
			}
		});
		
		StudentAccount.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				new StudentAccount();
			}
		});
		
		TeacherAccount.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
	}

}
