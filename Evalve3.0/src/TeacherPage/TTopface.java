package TeacherPage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Administration.SubjectIdentifier;
import baseGui.TchChatTable;

public class TTopface extends JPanel{
	
	private TeacherClient access = new TeacherClient();
	private TchChatTable chats;
	private ImageIcon iconLoad3 = new ImageIcon("questions.png"); 
	private JLabel q1 = new JLabel(iconLoad3);
	private ImageIcon iconLoad4 = new ImageIcon("question2.png"); 
	private JLabel q2 = new JLabel(iconLoad4);
	
	private ImageIcon iconLoad5 = new ImageIcon("briefcasesmall.png"); 
	private JLabel briefcase = new JLabel(iconLoad5);
	private ImageIcon iconLoad6 = new ImageIcon("briefcasebig.png"); 
	private JLabel briefcasebig = new JLabel(iconLoad6);
	
	private ImageIcon iconLoad7 = new ImageIcon("diary.png"); 
	private JLabel diary = new JLabel(iconLoad7);
	private ImageIcon iconLoad8 = new ImageIcon("diarybig.png"); 
	private JLabel diarybig = new JLabel(iconLoad8);
	
	private ImageIcon iconLoad9 = new ImageIcon("chat.png"); 
	private JLabel chat = new JLabel(iconLoad9);
	private ImageIcon iconLoad10 = new ImageIcon("bigchat.png"); 
	private JLabel bigchat = new JLabel(iconLoad10);
	
	private ImageIcon iconLoad11 = new ImageIcon("results.png"); 
	private JLabel result = new JLabel(iconLoad11);
	private ImageIcon iconLoad12 = new ImageIcon("resultbig.png"); 
	private JLabel resultbig = new JLabel(iconLoad12);
	
	private ImageIcon iconLoad13 = new ImageIcon("resourcesmall.png"); 
	private JLabel resources = new JLabel(iconLoad13);
	private ImageIcon iconLoad14 = new ImageIcon("resourcebig.png"); 
	private JLabel resourcesB = new JLabel(iconLoad14);
	
	private ImageIcon iconLoad15 = new ImageIcon("performance.png"); 
	private JLabel performance = new JLabel(iconLoad15);
	private ImageIcon iconLoad16 = new ImageIcon("performanceB.png"); 
	private JLabel performanceB = new JLabel(iconLoad16);
	private Color bakG;
	private String name = null, userName = null, psw = null, SubClassCl = null,  passport = null, term = null;
	
	private static int SW; double inicialW, finalW, inicialH, finalH;
	private static int SH; private int t1, t2, t3, t4, TextCol, TextRow;
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		bakG = new Color(70, 70, 250);
		g2.setColor(bakG);
		g2.fillRect(resizerW(5), resizerH(5), resizerW(720), resizerH(410));
	}

	public TTopface(final int IDNo, final String name, final String userName, final String password, final String SubClassCl, 
		final String passport, final String term) {
		this.name = name; this.userName = null; this.psw = password; this.SubClassCl = SubClassCl; 
		this.passport = passport; this.term = term; 
		setLayout(new GridBagLayout());
		GridBagConstraints align = new GridBagConstraints();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		inicialW = 980.0; inicialH = 590;
		SW = size.width;  finalW= SW;
		SH = size.height; finalH = SH;
		TextCol = (int) (SW/50);
		TextRow = (int) (SH/120);
		
		align.insets = new Insets(resizerH(20), resizerW(0), resizerH(0), resizerW(400));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(q1, align);
		
		align.insets = new Insets(resizerH(0), resizerW(0), resizerH(0), resizerW(400));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(q2, align);
		q2.setVisible(false);
		
		q1.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				q1.setVisible(false);
				q2.setVisible(true);
				//.println("Mouse entered.");
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				q1.setVisible(true);
				revalidate();
			}

			public void mouseClicked(MouseEvent arg0) {
				Vector ReadyQuestions = access.getSetQ(term, userName, SubClassCl);
				new TeachTable(ReadyQuestions, term);
			}
		});
		
		q2.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				q1.setVisible(false);
				q2.setVisible(true);
				//.println("Mouse entered.");
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				q1.setVisible(true);
				q2.setVisible(false);
				revalidate();
			}

			public void mouseClicked(MouseEvent arg0) {
				Vector ReadyQuestions = access.getSetQ(term, userName, SubClassCl);
				new TeachTable(ReadyQuestions, term);
			}
		});
		
		align.insets = new Insets(resizerH(180), resizerW(0), resizerH(0), resizerW(400));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		add(briefcase, align);
		
		align.insets = new Insets(resizerH(150), resizerW(0), resizerH(0), resizerW(380));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		add(briefcasebig, align);
		briefcasebig.setVisible(false);
		
		briefcase.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				briefcase.setVisible(false);
				briefcasebig.setVisible(true);
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				briefcase.setVisible(true);
				revalidate();
			}
			
		});
		
		briefcasebig.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				briefcase.setVisible(false);
				briefcasebig.setVisible(true);
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				briefcase.setVisible(true);
				briefcasebig.setVisible(false);
				revalidate();
			}
			
		});
		
		align.insets = new Insets(resizerH(20), resizerW(0), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		add(diary, align);
		
		align.insets = new Insets(resizerH(0), resizerW(0), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		add(diarybig, align);
		diarybig.setVisible(false);
		
		diary.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				diary.setVisible(false);
				diarybig.setVisible(true);
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				diary.setVisible(true);
				revalidate();
			}
			
			public void mouseClicked(MouseEvent arg0) {
				new Diary(null, IDNo,   name,   userName,   SubClassCl, term, 1);
			}
	
			
		});
		
		diarybig.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				diary.setVisible(false);
				diarybig.setVisible(true);
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				diary.setVisible(true);
				diarybig.setVisible(false);
				revalidate();
			}

			public void mouseClicked(MouseEvent arg0) {
						new Diary(null, IDNo,   name,   userName,   SubClassCl, term, 1);
					}
			
		});
		
		
		align.insets = new Insets(resizerH(20), resizerW(420), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(chat, align);
		
		align.insets = new Insets(resizerH(0), resizerW(390), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(bigchat, align);
		bigchat.setVisible(false);
		
		chat.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				chat.setVisible(false);
				bigchat.setVisible(true);
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				chat.setVisible(true);
				revalidate();
			}

			public void mouseClicked(MouseEvent arg0) {
				int iDNo = 0;
				//("Going to chat.");
				new TchChatTable(IDNo, userName, password);
			}
			
		});
		
		bigchat.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				chat.setVisible(false);
				bigchat.setVisible(true);
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				chat.setVisible(true);
				bigchat.setVisible(false);
				revalidate();
			}

			public void mouseClicked(MouseEvent arg0) {
				int iDNo = 0;
				//("Going to chat.");
				new TchChatTable(IDNo, userName, password);
			}
			
		});
		
		align.insets = new Insets(resizerH(180), resizerW(420), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(resources, align);
		
		align.insets = new Insets(resizerH(160), resizerW(390), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(resourcesB, align);
		resourcesB.setVisible(false);
		
		resources.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				resources.setVisible(false);
				resourcesB.setVisible(true);
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				resources.setVisible(true);
				revalidate();
			}
			
			public void mouseClicked(MouseEvent arg0) {
				MaterialsDialog teach = new MaterialsDialog(null, SubClassCl);
				teach.setVisible(true);
			}
			
		});
		
		resourcesB.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				resources.setVisible(false);
				resourcesB.setVisible(true);
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				resources.setVisible(true);
				resourcesB.setVisible(false);
				revalidate();
			}
			public void mouseClicked(MouseEvent arg0) {
				MaterialsDialog teach = new MaterialsDialog(null, SubClassCl);
				teach.setVisible(true);
			}
		});
		
		align.insets = new Insets(resizerH(180), resizerW(0), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		add(result, align);
		
		align.insets = new Insets(resizerH(150), resizerW(0), resizerH(0), resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		add(resultbig, align);
		resultbig.setVisible(false);
		
		result.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				result.setVisible(false);
				resultbig.setVisible(true);
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				result.setVisible(true);
				revalidate();
			}
			
			public void mouseClicked(MouseEvent arg0) {
				new SubmissionsClassDialog(null, SubClassCl);
			}
	
			
		});
		
		resultbig.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent arg0) {
				result.setVisible(false);
				resultbig.setVisible(true);
				revalidate();
			}

			public void mouseExited(MouseEvent arg0) {
				result.setVisible(true);
				resultbig.setVisible(false);
				revalidate();
			}
			
			public void mouseClicked(MouseEvent arg0) {
				new SubmissionsClassDialog(null,  SubClassCl);
			}
	
		});
	}
	private int resizerW(int inicialposition){
		int newposition = (int)(inicialposition*(finalW/inicialW));
		//(newposition);
		return newposition;
	}
	
	private int resizerH(int inicialposition){
		int newposition = (int)(inicialposition*(finalH/inicialH));
		//(newposition);
		return newposition;
	}
}
