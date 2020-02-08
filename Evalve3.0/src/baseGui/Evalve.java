package baseGui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Evalve{
	private JFrame frame = new JFrame("EVALVE"); 
	private JLabel MainHead, subject, Class, duration, term, date, Q, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10;
	private JButton nxt, ext;
	Font newFont = new Font("SERIF", Font.BOLD, 23);
	Font newFont2 = new Font("CAMBRIA", Font.BOLD, 18);
	Font newFont3 = new Font("CAMBRIA", Font.ITALIC, 13);
	private JPanel panelX = new JPanel() ;
	int z;int scores = 0, s= 0, n = 0;
	private JPanel panelE = new JPanel() ;
	int linenumber = 0, NoQ = 0, linenumb = 0; private String Subject = null, Classx = null, ExamType = null;
	 String datex; String timex; String Duration; String Typex;String examType; String Term;
	public Evalve(final String filename, final int scores2, final String Subject, final String Classx, 
			final String ExamType, final String weight, final String week, final String topic, 
			final String username, final String evalType) throws IOException {
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBackground(Color.BLACK);
		File toPreview = new File(filename);
		
		this.Subject = Subject;
        this.Classx = Classx;
        this.ExamType = ExamType;
		Scanner oneLine = new Scanner(toPreview);
	      String OneLine = oneLine.nextLine();
	      if(ExamType.toLowerCase().contains("essay")){
	    	   datex = split3(OneLine, 4);
		       timex = split3(OneLine, 5);
		       NoQ = Integer.parseInt(split3(OneLine, 6));
		       Duration = split3(OneLine, 9);
		       Term = split3(OneLine, 10);
		       Typex = split3(OneLine, 2);
		       examType = split3(OneLine, 11);
	      }else{
	       datex = split(OneLine, 9);
	       timex = split(OneLine, 10);
	      NoQ = Integer.parseInt(split(OneLine, 11));
	       Duration = split(OneLine, 14);
	       Term = split(OneLine, 15);
	       Typex = split(OneLine, 16);
	       examType = split(OneLine, 17);
	      }
		Rectangle r = new Rectangle( );
		r = frame.getBounds(r);
		/*//("X      = " + frame.getX( ));
		//("Y      = " + frame.getY( ));
		//("Width  = " + frame.getWidth( ));
		//("Height = " + frame.getHeight( ));*/
		MainHead = new JLabel("KINGDOM CITIZENS COLLEGE JOS");
		MainHead.setFont(newFont);
		String NTerm = Term.toUpperCase();
		String NEv = Typex.toUpperCase();
		term = new JLabel("WEEK "+week+"- "+evalType.toUpperCase()+" - "+ NEv +" - "+" ("+weight+" marks)");
		term.setFont(newFont2);
		term.setForeground(Color.DARK_GRAY);
		subject = new JLabel("Subject: "+Subject.toUpperCase());
		subject.setFont(newFont2);
		subject.setForeground(Color.DARK_GRAY);
		Class = new JLabel("Class: "+Classx);
		Class.setFont(newFont2);
		Class.setForeground(Color.DARK_GRAY);
		duration = new JLabel("Time Allowed: "+Duration);
		duration.setFont(newFont2);
		duration.setForeground(Color.DARK_GRAY);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		frame.setLayout(new GridBagLayout());
		GridBagConstraints align = new GridBagConstraints();
		
		align.insets = new Insets(0, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		frame.add(MainHead, align);
		
		align.insets = new Insets(40, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		frame.add(term, align);
		
		align.insets = new Insets(30, 0, 0, 1050);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		frame.add(subject, align);
		
		ext = new JButton("EXIT");
		ext.setForeground(Color.RED);
		ext.setFont(new Font("System", Font.BOLD, 14));
		align.insets = new Insets(60, 0, 0, 1210);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		frame.add(ext, align);
		
		align.insets = new Insets(30, 1000, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		frame.add(duration, align);
		
		nxt = new JButton("NEXT >>");
		nxt.setForeground(Color.BLUE);
		nxt.setFont(new Font("Arial", Font.BOLD, 14));
		align.insets = new Insets(60, 1250, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		frame.add(nxt, align);
		
		File file2 = new File(filename);
		if(!file2.exists()){
			JOptionPane.showConfirmDialog(null, filename+" does not exists.");
		}
            try {
            	LineNumberReader lnr = new LineNumberReader(new FileReader(file2));
				while (lnr.readLine() != null){
				linenumber++;
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
            if(linenumber==0){
            	JOptionPane.showConfirmDialog(null, filename+" is empty.");
            }
		Q = new JLabel("No of Questions: "+linenumber);
		Q.setFont(newFont3);
		Q.setForeground(Color.DARK_GRAY);
		align.insets = new Insets(70, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		frame.add(Q, align);
		
		//PanelX section for mcq
		
		Dimension dim2 = panelX.getPreferredSize();
		dim2.height = 610;
		dim2.width = 1370;
		panelX.setPreferredSize(dim2);
		panelX.setBackground(new Color(255, 250, 230));
		Border innerBorder2 = BorderFactory.createEtchedBorder(10, Color.GREEN, Color.GRAY);
		Border outerBorder2 = BorderFactory.createRaisedSoftBevelBorder();
		AbstractButton createCompoundBorder2;
		panelX.setBorder(BorderFactory.createCompoundBorder(innerBorder2, outerBorder2));
		panelX.setLayout(new GridBagLayout());
		GridBagConstraints align2 = new GridBagConstraints();
		
		//PanelX section for essay
		Dimension dim4 = panelE.getPreferredSize();
		dim4.height = 610;
		dim4.width = 1370;
		panelE.setPreferredSize(dim4);
		panelE.setBackground(new Color(255, 250, 230));
		Border innerBorder4 = BorderFactory.createEtchedBorder(10, Color.GREEN, Color.GRAY);
		Border outerBorder4 = BorderFactory.createRaisedSoftBevelBorder();
		AbstractButton createCompoundBorder4;
		panelE.setBorder(BorderFactory.createCompoundBorder(innerBorder4, outerBorder4));
		panelE.setLayout(new GridBagLayout());
		GridBagConstraints align3 = new GridBagConstraints();
		
	final File nxtPage = new File("numbering");
	if(!nxtPage.exists()){
		nxtPage.createNewFile();
		PrintStream zeroKick = new PrintStream(nxtPage);
		zeroKick.println(0);
		zeroKick.close();
	}
	final Scanner cont = new Scanner(nxtPage);
	z = cont.nextInt()+1;
	int live = z;
	cont.close();
	
	if (examType.equals("M.C.Q")){
		
    final String [] Ans = new String [z+10];
    int count = z;
    try {
     	LineNumberReader lnr = new LineNumberReader(new FileReader(toPreview));
			while (lnr.readLine() != null){
			linenumb++;
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	 
    while(count<=(z+9)){
		try {
		
			String k = getQuestion(count, file2);
			String string = k; 
			int N = 7;
			String part = split(string, N);
			//(part+" -"+string);
			Ans[count] = part;
			count++;
		
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    final Scanner cont3 = new Scanner(nxtPage);
    int fireLive = cont3.nextInt();
	cont.close(); 
   
	int s = 0; int n = 200;
	////("While "+z+" < "+(z+9));
	////(live);
	final Scanner cont2 = new Scanner(nxtPage);
	for (z = cont2.nextInt()+1; z<=(fireLive+10); z++){
		////("z = "+z);
	try {
		String k = getQuestion(z, file2);
		String string = k; 
		int N = 1;
		String part = split(string, N);

		JLabel no = new JLabel(z+". ");
		no.setFont(new Font("SERIF", Font.BOLD, 14));
		
		String labelText = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 470, ""+part+"" );
		JLabel Question = new JLabel(labelText);
		Question.setFont(new Font("SERIF", Font.BOLD, 14));
		final ButtonGroup group = new ButtonGroup();
		int f = n; int g = s+50;
		String [] OptX  = new String[] {"A","B","C","D","X"};
		 for (String Option  : OptX) {
			
			 if(f>1190){
				 f =n; g =g+40;
		 }
			 
			 if(f>520 && f<870 ){
				 f =n; g =g+40;
			 }
			 switch (z){
				case 1:case 11:case 21:case 31:case 41:case 51:case 61:case 71:case 81:case 91:case 101:case 111:
					final int Q = z;
					final JRadioButton Opt = new JRadioButton(Option);
					group.add(Opt);
					align2.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
					align2.anchor = GridBagConstraints.WEST;
					align2.gridx = 0;
					align2.gridy = 0;
					align2.weightx = 0;
					align2.weighty = 0;
					align2.fill = GridBagConstraints.NONE;
					panelX.add(Opt, align2);
					Opt.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
								String c = Opt.getActionCommand();
								JOptionPane.showMessageDialog(null, " Q = "+(Q)+" Array Ans[Q]: "+(Ans[Q]));
								if (c.equals(Ans[Q])){
									scores++;
									JOptionPane.showMessageDialog(null, " Correct! "+": Score: "+scores);
								}
						}
						
					});
					break;
				case 2:case 12:case 22:case 32:case 42:case 52:case 62:case 72:case 82:case 92:case 102:case 112:
					final int Q2 = z;
					final JRadioButton Opt2 = new JRadioButton(Option);
					group.add(Opt2);
					align2.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
					align2.anchor = GridBagConstraints.WEST;
					align2.gridx = 0;
					align2.gridy = 0;
					align2.weightx = 0;
					align2.weighty = 0;
					align2.fill = GridBagConstraints.NONE;
					panelX.add(Opt2, align2);
					Opt2.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
								String c = Opt2.getActionCommand();
								JOptionPane.showMessageDialog(null, " Q = "+(Q2)+" Array Ans[Q2]: "+(Ans[Q2]));
								if (c.equals(Ans[Q2])){
									scores++;
									JOptionPane.showMessageDialog(null, " Correct! "+": Score: "+scores);
								}
						}
						
					});
					break;
				case 3: case 13:
					case 23:case 33:case 43:case 53:case 63:
						case 73:case 83:case 93:case 103:case 113:
					final int Q3 = z;
					final JRadioButton Opt3 = new JRadioButton(Option);
					group.add(Opt3);
					align2.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
					align2.anchor = GridBagConstraints.WEST;
					align2.gridx = 0;
					align2.gridy = 0;
					align2.weightx = 0;
					align2.weighty = 0;
					align2.fill = GridBagConstraints.NONE;
					panelX.add(Opt3, align2);
					Opt3.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
								String c = Opt3.getActionCommand();
								JOptionPane.showMessageDialog(null, " Q3 = "+(Q3)+" Array Ans[Q3]: "+(Ans[Q3]));
								if (c.equals(Ans[Q3])){
									scores++;
									JOptionPane.showMessageDialog(null, " Correct! "+": Score: "+scores);
								}
						}
						
					});
				break;
				case 4:case 14:
				case 24:case 34:case 44:case 54:case 64:
				case 74:case 84:case 94:case 104:case 114:
					final int Q4 = z;
					final JRadioButton Opt4 = new JRadioButton(Option);
					group.add(Opt4);
					align2.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
					align2.anchor = GridBagConstraints.WEST;
					align2.gridx = 0;
					align2.gridy = 0;
					align2.weightx = 0;
					align2.weighty = 0;
					align2.fill = GridBagConstraints.NONE;
					panelX.add(Opt4, align2);
					Opt4.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
								String c = Opt4.getActionCommand();
								JOptionPane.showMessageDialog(null, " Q4 = "+(Q4)+" Array Ans[Q4]: "+(Ans[Q4]));
								if (c.equals(Ans[Q4])){
									scores++;
								}
						}
						
					});
					break;
				case 5:case 15:
				case 25:case 35:case 45:case 55:case 65:
				case 75:case 85:case 95:case 105:case 115:
					final int Q5 = z;
					final JRadioButton Opt5 = new JRadioButton(Option);
					group.add(Opt5);
					align2.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
					align2.anchor = GridBagConstraints.WEST;
					align2.gridx = 0;
					align2.gridy = 0;
					align2.weightx = 0;
					align2.weighty = 0;
					align2.fill = GridBagConstraints.NONE;
					panelX.add(Opt5, align2);
					Opt5.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
								String c = Opt5.getActionCommand();
								JOptionPane.showMessageDialog(null, " Q5 = "+Q5+" Array Ans[Q5]: "+(Ans[Q5]));
								if (c.equals(Ans[Q5])){
									scores++;
								}
						}
						
					});
					break;
				case 6:case 16:
				case 26:case 36:case 46:case 56:case 66:
				case 76:case 86:case 96:case 106:case 116:
					final int Q6 = z;
					final JRadioButton Opt6 = new JRadioButton(Option);
					group.add(Opt6);
					align2.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
					align2.anchor = GridBagConstraints.WEST;
					align2.gridx = 0;
					align2.gridy = 0;
					align2.weightx = 0;
					align2.weighty = 0;
					align2.fill = GridBagConstraints.NONE;
					panelX.add(Opt6, align2);
					Opt6.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
								String c = Opt6.getActionCommand();
								JOptionPane.showMessageDialog(null, " Q6 = "+Q6+" Array Ans[Q6]: "+(Ans[Q6]));
								if (c.equals(Ans[Q6])){
									scores++;
								}
						}
						
					});
					break;
				case 7:case 17:
				case 27:case 37:case 47:case 57:case 67:
				case 77:case 87:case 97:case 107:case 117:
					final int Q7 = z;
					final JRadioButton Opt7 = new JRadioButton(Option);
					group.add(Opt7);
					align2.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
					align2.anchor = GridBagConstraints.WEST;
					align2.gridx = 0;
					align2.gridy = 0;
					align2.weightx = 0;
					align2.weighty = 0;
					align2.fill = GridBagConstraints.NONE;
					panelX.add(Opt7, align2);
					Opt7.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
								String c = Opt7.getActionCommand();
								JOptionPane.showMessageDialog(null, " Q7 = "+Q7+" Array Ans[Q7]: "+(Ans[Q7]));
								if (c.equals(Ans[Q7])){
									scores++;
								}
						}
						
					});
					break;
				case 8:case 18:
				case 28:case 38:case 48:case 58:case 68:
				case 78:case 88:case 98:case 108:case 118:
					final int Q8 = z;
					final JRadioButton Opt8 = new JRadioButton(Option);
					group.add(Opt8);
					align2.insets = new Insets(g, f, 500,200);		// format: push(DOWN, right, UP, left)
					align2.anchor = GridBagConstraints.WEST;
					align2.gridx = 0;
					align2.gridy = 0;
					align2.weightx = 0;
					align2.weighty = 0;
					align2.fill = GridBagConstraints.NONE;
					panelX.add(Opt8, align2);
					Opt8.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
								String c = Opt8.getActionCommand();
								JOptionPane.showMessageDialog(null, " Q8 = "+Q8+" Array Ans[Q8]: "+(Ans[Q8]));
								if (c.equals(Ans[Q8])){
									scores++;
								}
						}
						
					});
					break;
				case 9:case 19:
				case 29:case 39:case 49:case 59:case 69:
				case 79:case 89:case 99:case 109:case 119:
					final int Q9 = z;
					final JRadioButton Opt9 = new JRadioButton(Option);
					group.add(Opt9);
					align2.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
					align2.anchor = GridBagConstraints.WEST;
					align2.gridx = 0;
					align2.gridy = 0;
					align2.weightx = 0;
					align2.weighty = 0;
					align2.fill = GridBagConstraints.NONE;
					panelX.add(Opt9, align2);
					Opt9.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
								String c = Opt9.getActionCommand();
								JOptionPane.showMessageDialog(null, " Q9 = "+Q9+" Array Ans[Q9]: "+(Ans[Q9]));
								if (c.equals(Ans[Q9])){
									scores++;
								}
						}
						
					});
					break;
				case 0:case 10:
				case 20:case 30:case 40:case 50:case 60:
				case 70:case 80:case 90:case 100:case 110:
					final int Q10 = z;
					final JRadioButton Opt10 = new JRadioButton(Option);
					group.add(Opt10);
					align2.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
					align2.anchor = GridBagConstraints.WEST;
					align2.gridx = 0;
					align2.gridy = 0;
					align2.weightx = 0;
					align2.weighty = 0;
					align2.fill = GridBagConstraints.NONE;
					panelX.add(Opt10, align2);
					Opt10.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
								String c = Opt10.getActionCommand();
								JOptionPane.showMessageDialog(null, " Q10 = "+Q10+" Array Ans[z]: "+(Ans[Q10]));
								if (c.equals(Ans[Q10])){
									scores++;
								}
						}
						
					});
					break;
				default:
					break;
			}
		
		 f = f +320;
		 }
		
		align2.insets = new Insets(s, n-30, 500, 200);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.WEST;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		panelX.add(no, align2);
		
		align2.insets = new Insets(s, n, 530, 150);		// format: push(DOWN, right, UP, left)
		align2.anchor = GridBagConstraints.WEST;
		align2.gridx = 0;
		align2.gridy = 0;
		align2.weightx = 0;
		align2.weighty = 0;
		align2.fill = GridBagConstraints.NONE;
		panelX.add(Question, align2);
		
		s = s + 235;		// top-down spacing
		if (s>940){
			s = 0;
			n = 870;  //left- right centre spacing
			
		}
		
	} catch (IOException e2) {
		e2.printStackTrace();
	}
	cont2.close();
	}
			align.insets = new Insets(90, 0, 0, 0);		// format: push(DOWN, right, UP, left)
			align.anchor = GridBagConstraints.NORTH;
			align.gridx = 0;
			align.gridy = 0;
			align.weightx = 0;
			align.weighty = 0;
			align.fill = GridBagConstraints.NONE;
			frame.add(panelX, align);
			JOptionPane.showMessageDialog(null, "MCQ Questions loaded."+"Present Score: "+scores2);
			
		}else{
			
		File file3 = new File(filename);
		z = 0; s = 0; n = 200;
			final Scanner cont3 = new Scanner(nxtPage);
		    int fireLive = cont3.nextInt();
			cont.close(); 
			final Scanner cont2 = new Scanner(nxtPage);
		for (z = cont2.nextInt()+1; z<=(fireLive+10); z++){
			//(z+" -firelive ="+fireLive);
		try {
			String k = getQuestion(z, file3);
			String string = k; 
			int N = 1;
			String part = split3(string, N);
			
			
			JLabel no = new JLabel(z+". ");
			no.setFont(new Font("SERIF", Font.BOLD, 14));
			
			String labelText = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 470, ""+part+"" );
			JLabel Question = new JLabel(labelText);
			Question.setFont(new Font("SERIF", Font.BOLD, 14));
			int f = n; int g = s+78;
				 switch (z){
				 case 1:case 11:case 21:case 31:case 41:case 51:case 61:case 71:case 81:case 91:case 101:case 111:
						final int Q = z;
						final JTextArea Opt1 = new JTextArea(3, 55);
						JScrollPane opt1 = new JScrollPane(Opt1);
						opt1.setMinimumSize(opt1.getPreferredSize());
						Opt1.setBackground(Color.PINK);
						Opt1.setLineWrap(true);
						Opt1.setWrapStyleWord(true);
						Opt1.setEditable(true);
						align3.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
						align3.anchor = GridBagConstraints.WEST;
						align3.gridx = 0;
						align3.gridy = 0;
						align3.weightx = 0;
						align3.weighty = 0;
						align3.fill = GridBagConstraints.NONE;
						panelE.add(opt1, align3);
						Opt1.addFocusListener(new FocusListener(){
							public void focusGained(FocusEvent arg0) {
							}
							public void focusLost(FocusEvent arg0) {
								//("Textbox 1 saved");
							}
						});
						break;
				 case 2:case 12:case 22:case 32:case 42:case 52:case 62:case 72:case 82:case 92:case 102:case 112:
						final int Q2 = z;
						final JTextArea Opt2 = new JTextArea(3, 55);
						JScrollPane opt2 = new JScrollPane(Opt2);
						opt2.setMinimumSize(opt2.getPreferredSize());
						Opt2.setBackground(Color.PINK);
						Opt2.setLineWrap(true);
						Opt2.setWrapStyleWord(true);
						Opt2.setEditable(true);
						align3.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
						align3.anchor = GridBagConstraints.WEST;
						align3.gridx = 0;
						align3.gridy = 0;
						align3.weightx = 0;
						align3.weighty = 0;
						align3.fill = GridBagConstraints.NONE;
						panelE.add(opt2, align3);
						Opt2.addFocusListener(new FocusListener(){
							public void focusGained(FocusEvent arg0) {
							}
							public void focusLost(FocusEvent arg0) {
								String Ans2 = Opt2.getText();
								JOptionPane.showMessageDialog(null, Ans2+" saved");
							}
						});
						break;
						
				 case 3: case 13:
					case 23:case 33:case 43:case 53:case 63:
						case 73:case 83:case 93:case 103:case 113:
						final int Q3 = z;
						final JTextArea Opt3 = new JTextArea(3, 55);
						JScrollPane opt3 = new JScrollPane(Opt3);
						opt3.setMinimumSize(opt3.getPreferredSize());
						Opt3.setBackground(Color.PINK);
						Opt3.setLineWrap(true);
						Opt3.setWrapStyleWord(true);
						Opt3.setEditable(true);
						align3.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
						align3.anchor = GridBagConstraints.WEST;
						align3.gridx = 0;
						align3.gridy = 0;
						align3.weightx = 0;
						align3.weighty = 0;
						align3.fill = GridBagConstraints.NONE;
						panelE.add(opt3, align3);
					break;
					
						case 4:case 14:
						case 24:case 34:case 44:case 54:case 64:
						case 74:case 84:case 94:case 104:case 114:
						final int Q4 = z;
						final JTextArea Opt4 = new JTextArea(3, 55);
						JScrollPane opt4 = new JScrollPane(Opt4);
						opt4.setMinimumSize(opt4.getPreferredSize());
						Opt4.setBackground(Color.PINK);
						Opt4.setLineWrap(true);
						Opt4.setWrapStyleWord(true);
						Opt4.setEditable(true);
						align3.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
						align3.anchor = GridBagConstraints.WEST;
						align3.gridx = 0;
						align3.gridy = 0;
						align3.weightx = 0;
						align3.weighty = 0;
						align3.fill = GridBagConstraints.NONE;
						panelE.add(opt4, align3);
						break;
						
						case 5:case 15:
						case 25:case 35:case 45:case 55:case 65:
						case 75:case 85:case 95:case 105:case 115:
						final int Q5 = z;
						final JTextArea Opt5 = new JTextArea(3, 55);
						JScrollPane opt5 = new JScrollPane(Opt5);
						opt5.setMinimumSize(opt5.getPreferredSize());
						Opt5.setBackground(Color.PINK);
						Opt5.setLineWrap(true);
						Opt5.setWrapStyleWord(true);
						Opt5.setEditable(true);
						align3.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
						align3.anchor = GridBagConstraints.WEST;
						align3.gridx = 0;
						align3.gridy = 0;
						align3.weightx = 0;
						align3.weighty = 0;
						align3.fill = GridBagConstraints.NONE;
						panelE.add(opt5, align3);
						break;
						
						case 6:case 16:
						case 26:case 36:case 46:case 56:case 66:
						case 76:case 86:case 96:case 106:case 116:
						final int Q6 = z;
						final JTextArea Opt6 = new JTextArea(3, 55);
						JScrollPane opt6 = new JScrollPane(Opt6);
						opt6.setMinimumSize(opt6.getPreferredSize());
						Opt6.setBackground(Color.PINK);
						Opt6.setLineWrap(true);
						Opt6.setWrapStyleWord(true);
						Opt6.setEditable(true);
						align3.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
						align3.anchor = GridBagConstraints.WEST;
						align3.gridx = 0;
						align3.gridy = 0;
						align3.weightx = 0;
						align3.weighty = 0;
						align3.fill = GridBagConstraints.NONE;
						panelE.add(opt6, align3);
						break;
						

				case 7:case 17:
				case 27:case 37:case 47:case 57:case 67:
				case 77:case 87:case 97:case 107:case 117:
						final int Q7 = z;
						final JTextArea Opt7 = new JTextArea(3, 55);
						JScrollPane opt7 = new JScrollPane(Opt7);
						opt7.setMinimumSize(opt7.getPreferredSize());
						Opt7.setBackground(Color.PINK);
						Opt7.setLineWrap(true);
						Opt7.setWrapStyleWord(true);
						Opt7.setEditable(true);
						align3.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
						align3.anchor = GridBagConstraints.WEST;
						align3.gridx = 0;
						align3.gridy = 0;
						align3.weightx = 0;
						align3.weighty = 0;
						align3.fill = GridBagConstraints.NONE;
						panelE.add(opt7, align3);
						break;
						
				case 8:case 18:
				case 28:case 38:case 48:case 58:case 68:
				case 78:case 88:case 98:case 108:case 118:
						final int Q8 = z;
						final JTextArea Opt8 = new JTextArea(3, 55);
						JScrollPane opt8 = new JScrollPane(Opt8);
						opt8.setMinimumSize(opt8.getPreferredSize());
						Opt8.setBackground(Color.PINK);
						Opt8.setLineWrap(true);
						Opt8.setWrapStyleWord(true);
						Opt8.setEditable(true);
						align3.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
						align3.anchor = GridBagConstraints.WEST;
						align3.gridx = 0;
						align3.gridy = 0;
						align3.weightx = 0;
						align3.weighty = 0;
						align3.fill = GridBagConstraints.NONE;
						panelE.add(opt8, align3);
						break;

				case 9:case 19:
				case 29:case 39:case 49:case 59:case 69:
				case 79:case 89:case 99:case 109:case 119:
						final int Q9 = z;
						final JTextArea Opt9 = new JTextArea(3, 55);
						JScrollPane opt9 = new JScrollPane(Opt9);
						opt9.setMinimumSize(opt9.getPreferredSize());
						Opt9.setBackground(Color.PINK);
						Opt9.setLineWrap(true);
						Opt9.setWrapStyleWord(true);
						Opt9.setEditable(true);
						align3.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
						align3.anchor = GridBagConstraints.WEST;
						align3.gridx = 0;
						align3.gridy = 0;
						align3.weightx = 0;
						align3.weighty = 0;
						align3.fill = GridBagConstraints.NONE;
						panelE.add(opt9, align3);
						break;

				case 0:case 10:
				case 20:case 30:case 40:case 50:case 60:
				case 70:case 80:case 90:case 100:case 110:
						final int Q10 = z;
						final JTextArea Opt10 = new JTextArea(3, 55);
						JScrollPane opt10 = new JScrollPane(Opt10);
						opt10.setMinimumSize(opt10.getPreferredSize());
						Opt10.setBackground(Color.PINK);
						Opt10.setLineWrap(true);
						Opt10.setWrapStyleWord(true);
						Opt10.setEditable(true);
						align3.insets = new Insets(g, f, 500, 200);		// format: push(DOWN, right, UP, left)
						align3.anchor = GridBagConstraints.WEST;
						align3.gridx = 0;
						align3.gridy = 0;
						align3.weightx = 0;
						align3.weighty = 0;
						align3.fill = GridBagConstraints.NONE;
						panelE.add(opt10, align3);
						break;
					default:
						break;
				}
			
			
			align3.insets = new Insets(s, n-30, 500, 200);		// format: push(DOWN, right, UP, left)
			align3.anchor = GridBagConstraints.WEST;
			align3.gridx = 0;
			align3.gridy = 0;
			align3.weightx = 0;
			align3.weighty = 0;
			align3.fill = GridBagConstraints.NONE;
			panelE.add(no, align3);
			
			align3.insets = new Insets(s, n, 530, 150);		// format: push(DOWN, right, UP, left)
			align3.anchor = GridBagConstraints.WEST;
			align3.gridx = 0;
			align3.gridy = 0;
			align3.weightx = 0;
			align3.weighty = 0;
			align3.fill = GridBagConstraints.NONE;
			panelE.add(Question, align3);
			
			s = s + 235;		// top-down spacing
			if (s>940){
				s = 0;
				n = 870;  //left- right centre spacing
			}
			} catch (IOException e) {
			e.printStackTrace();
		  }
		cont2.close();
		}
		align.insets = new Insets(90, 0, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		frame.add(panelE, align);
		JOptionPane.showMessageDialog(null, "Essay Questions loaded.");
		}
	
		ext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					File clr = new File("numbering");
					PrintStream clear = new PrintStream(clr);
					clear.println(0);
					clear.close();
					System.gc();
					clr.delete();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				frame.setVisible(false);
			}
			
		});
		nxt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(linenumber>10){
						PrintStream stops = new PrintStream(nxtPage);
						stops.println(z-1);
						stops.close();
						JOptionPane.showMessageDialog(null, linenumber+" "+NoQ+"  "+(z-1));
						if(linenumber==(z-1)){
							//System.gc();
							nxtPage.delete();
							int finalScore = scores2+scores;
							JOptionPane.showMessageDialog(null, "You have come to the end of this Evaluation." +
									" Your Total score is: "+finalScore);
							return;
							//frame.setVisible(false);
						}else{
							int latestScore = scores2+scores;
							JOptionPane.showMessageDialog(null, "Your score in this section is: "+scores+ "Total Score: "+latestScore);
							frame.setVisible(false);
							new Evalve(filename, latestScore, Subject, Classx, ExamType, weight, week, topic, username,
									evalType);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	public static String getQuestion(int numLine, File file2) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(file2));
	    String line = "";
	    for(int i = 0; i < numLine; i++) {
	        line = br.readLine();
	    }
	    return line; }
	
	public static String split(String f, int d){
		String[] parts = f.split("`");
		String part1 = parts[0]; // 004
		String part2 = parts[1]; // 034556
		String part3 = parts[2];
		String part4 = parts[3];
		String part5 = parts[4];
		String part6 = parts[5];
		String part7 = parts[6];
		String part8 = parts[7];
		String part9 = parts[8]; // 004
		String part10 = parts[9]; // 034556
		String part11 = parts[10];
		String part12 = parts[11];
		String part13 = parts[12];
		String part14 = parts[13];
		String part15 = parts[14];
		String part16 = parts[15];
		String part17 = parts[16];
		switch (d){
		case 1:
			return part1;
		case 2:
			return part2;
		case 3:
			return part3;
		case 4:
			return part4;
		case 5:
			return part5;
		case 6:
			return part6;
		case 7:
			return part7;
		case 8:
			return part8;
		case 9:
			return part9;
		case 10:
			return part10;
		case 11:
			return part11;
		case 12:
			return part12;
		case 13:
			return part13;
		case 14:
			return part14;
		case 15:
			return part15;
		case 16:
			return part16;
		case 17:
			return part17;
		}
		return null;
	}
	
	public static String split2(String f, int d){
		String[] parts = f.split("`");
		String part1 = parts[0]; // 004
		String part2 = parts[1]; // 034556
		String part3 = parts[2];
		switch (d){
		case 1:
			return part1;
		case 2:
			return part2;
		case 3:
			return part3;
		}
		return null;
	}
	
	private String split3(String f, int i) {
		try{
		String[] parts = f.split("`");
		String part1 = parts[0]; // 004
		String part2 = parts[1]; // 034556
		String part3 = parts[2];
		String part4 = parts[3];
		String part5 = parts[4];
		String part6 = parts[5];
		String part7 = parts[6];
		String part8 = parts[7];
		String part9 = parts[8]; // 004
		String part10 = parts[9]; // 034556
		String part11 = parts[10];
		switch (i){
		case 1:
			return part1;
		case 2:
			return part2;
		case 3:
			return part3;
		case 4:
			return part4;
		case 5:
			return part5;
		case 6:
			return part6;
		case 7:
			return part7;
		case 8:
			return part8;
		case 9:
			return part9;
		case 10:
			return part10;
		case 11:
			return part11;
		}
		}catch(Exception q){
			//("There was a problem in Evalve split3");
		}
		return null;
	}
}
