package TeacherPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StudentEssay extends JFrame{
	private JTextArea Big1 = new JTextArea(4, 45);
	private JTextField small1 = new JTextField(2);
	private JScrollPane BigCover1 = new JScrollPane(Big1);
	
	private JTextArea Big2 = new JTextArea(4, 45);
	private JTextField small2 = new JTextField(2);
	private JScrollPane BigCover2 = new JScrollPane(Big2);
	
	private JTextArea Big3 = new JTextArea(4, 45);
	private JTextField small3 = new JTextField(2);
	private JScrollPane BigCover3 = new JScrollPane(Big3);
	
	private JTextArea Big4 = new JTextArea(4, 45);
	private JTextField small4 = new JTextField(2);
	private JScrollPane BigCover4 = new JScrollPane(Big4);
	
	private JTextArea Big5 = new JTextArea(4, 45);
	private JTextField small5 = new JTextField(2);
	private JScrollPane BigCover5 = new JScrollPane(Big5);
	
	private ImageIcon iconLoad1 = new ImageIcon("forward.png"); 
	private JLabel next = new JLabel(iconLoad1);
	
	private ImageIcon iconLoad2 = new ImageIcon("back.png"); 
	private JLabel prev = new JLabel(iconLoad2);
	
	private JTextField weight = new JTextField(2);
	private JLabel Heading = new JLabel("");
	private JLabel Instructions = new JLabel("");
	private JLabel total = new JLabel("Weight/Total-Score:");
	private GridBagConstraints align = null;
	private JLabel sco = new JLabel("");
	private JButton enter = new JButton("ENTER");
	private int TAns = 0, anti = 0, score = 0, wgt = 0, val = 0;;
	private boolean sma1 = false, sma2 = false, sma3 = false, sma4 = false, sma5 = false;
	private Vector <Integer> ScoreV = new Vector <Integer> ();
	public StudentEssay(final Object IDNO,final String subject,final String claxx,final String evalType,
			final int week, final List<String> ansrs, final int ant, final int Score, final int wg, 
			final String weigh, Vector<Integer> scorev) {
		super("ID-"+IDNO+"   CLASS:"+claxx);
		setLocation(300, 10);
		setSize(600, 650);
		setVisible(true);
		
		score = Score;
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		setLayout(new GridBagLayout());
		align = new GridBagConstraints();
		
		wgt = Integer.parseInt(weigh.trim());
		if (wgt != 0){
			weight.setText(wgt+"");
		}
		
		TAns = ansrs.size(); 
		anti = ant;
		
		if(anti == 0 && score == 0){
		JOptionPane.showMessageDialog(null, "Please make sure you confirm the weight you are using.", "IMPORTANT NOTICE", JOptionPane.INFORMATION_MESSAGE);
		int g = 0;
		while(g < TAns){
			ScoreV.add(g, 0);
			g++;
		}
		}else{
			ScoreV = scorev;
		}
		if(anti >= TAns){
			int p = 0;  int ScoSize = ScoreV.size();
			while(p<ScoSize){
				val = val+ScoreV.get(p);
				p++;
			}
			if(val<((wgt/2)-1)){
				sco.setForeground(Color.red);
			}else{
				sco.setForeground(Color.blue);
			}
			sco.setText("*"+val+"/"+wgt+"*");
			sco.setFont(new Font("elephant", Font.BOLD, 60));
			align.insets = new Insets(0, 0, 0, 0);		// format: push(DOWN, right, UP, left)
			align.anchor = GridBagConstraints.CENTER;
			align.gridx = 0;
			align.gridy = 0;
			align.weightx = 0;
			align.weighty = 0;
			align.fill = GridBagConstraints.NONE;
			add(sco, align);
			
			align.insets = new Insets(400, 0, 0, 0);		// format: push(DOWN, right, UP, left)
			align.anchor = GridBagConstraints.CENTER;
			align.gridx = 0;
			align.gridy = 0;
			align.weightx = 0;
			align.weighty = 0;
			align.fill = GridBagConstraints.NONE;
			add(enter, align);
		}else if(anti<=TAns && anti>=0){
		try{
		align.insets = new Insets(0, 0, 560, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Heading, align);
		
		Heading.setText(subject.toUpperCase()+" "+evalType.toUpperCase()+" FOR WEEK "+week);
		Heading.setFont(new Font("book antiqua", Font.BOLD, 21));
		
		align.insets = new Insets(0, 0, 520, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Instructions, align);
		
		align.insets = new Insets(0, 50, 470, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(weight, align);
		
		align.insets = new Insets(0, 0, 470, 80);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(total, align);
		
		Instructions.setText("Enter the score for each question in the small right box.");
		Instructions.setFont(new Font("book antiqua", Font.PLAIN, 15));
		
		align.insets = new Insets(0, 0, 470, 480);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(prev, align);
		
		align.insets = new Insets(0, 480, 470, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(next, align);
		
		BigCover1.setMinimumSize(BigCover1.getPreferredSize());
		Big1.setLineWrap(true);
		Big1.setWrapStyleWord(true);
		Big1.setEditable(false);
		Big1.append((anti+1)+".  " +ansrs.get(anti)); 
		small1.setText(ScoreV.get(anti).toString());
		anti++;
		
		small1.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				sma1 = true;
				if(small1.getText() != null || !weight.getText().trim().equals("") ){
					try{
						ScoreV.remove((anti-5));ScoreV.add((anti-5), Integer.parseInt(small1.getText()));
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Please enter Whole Numbers only. Decimals and Fractions are allowed.");
						sma1 = false;
					}
					}else{
						JOptionPane.showMessageDialog(null, "Please enter a score.");
					}
			}
		});
		
		align.insets = new Insets(100, 0, 0, 50);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(BigCover1, align);
		
		align.insets = new Insets(100, 500, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(small1, align);
		
		BigCover2.setMinimumSize(BigCover2.getPreferredSize());
		Big2.setLineWrap(true);
		Big2.setWrapStyleWord(true);
		Big2.setEditable(false);
		Big2.append((anti+1)+".  " +ansrs.get(anti));
		small2.setText(ScoreV.get(anti).toString());
		anti++;
		
		small2.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				sma2 = true;
				if(small2.getText() != null || !weight.getText().trim().equals("") ){
					try{
						ScoreV.remove((anti-4));ScoreV.add((anti-4), Integer.parseInt(small2.getText()));
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Please enter Whole Numbers only. Decimals and Fractions are allowed.");
						sma2 = false;
					}
					}else{
						JOptionPane.showMessageDialog(null, "Please enter a score.");
					}
			}
		});
		
		align.insets = new Insets(200, 0, 0, 50);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(BigCover2, align);
		
		align.insets = new Insets(200, 500, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(small2, align);
		
		BigCover3.setMinimumSize(BigCover3.getPreferredSize());
		Big3.setLineWrap(true);
		Big3.setWrapStyleWord(true);
		Big3.setEditable(false);
		Big3.append((anti+1)+".  " +ansrs.get(anti));
		small3.setText(ScoreV.get(anti).toString());
		anti++;
		
		small3.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				sma3 = true;
				if(small3.getText() != null || !weight.getText().trim().equals("") ){
					try{
						ScoreV.remove((anti-3));ScoreV.add((anti-3), Integer.parseInt(small3.getText()));
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Please enter Whole Numbers only. Decimals and Fractions are allowed.");
						sma3 = false;
					}
					}else{
						JOptionPane.showMessageDialog(null, "Please enter a score.");
					}
			}
		});
		
		align.insets = new Insets(300, 0, 0, 50);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(BigCover3, align);
		
		align.insets = new Insets(300, 500, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(small3, align);
		
		BigCover4.setMinimumSize(BigCover4.getPreferredSize());
		Big4.setLineWrap(true);
		Big4.setWrapStyleWord(true);
		Big4.setEditable(false);
		Big4.append((anti+1)+".  " +ansrs.get(anti)); 
		small4.setText(ScoreV.get(anti).toString());
		anti++;
		
		small4.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				sma4 = true;
				if(small4.getText() != null || !weight.getText().trim().equals("") ){
					try{
						ScoreV.remove((anti-2));ScoreV.add((anti-2), Integer.parseInt(small4.getText()));
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Please enter Whole Numbers only. Decimals and Fractions are allowed.");
						sma4 = false;
					}
					}else{
						JOptionPane.showMessageDialog(null, "Please enter a score.");
					}
			}
		});
		
		align.insets = new Insets(400, 0, 0, 50);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(BigCover4, align);
		
		align.insets = new Insets(400, 500, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(small4, align);
		
		BigCover5.setMinimumSize(BigCover5.getPreferredSize());
		Big5.setLineWrap(true);
		Big5.setWrapStyleWord(true);
		Big5.setEditable(false);
		Big5.append((anti+1)+".  " +ansrs.get(anti)); 
		small5.setText(ScoreV.get(anti).toString());
		anti++;
		
		small5.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				sma5 = true;
				if(small5.getText() != null || !weight.getText().trim().equals("") ){
					try{
						ScoreV.remove((anti-1));ScoreV.add((anti-1), Integer.parseInt(small5.getText()));
						//("In Focus Score: "+ScoreV);
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Please enter Whole Numbers only. Decimals and Fractions are allowed.");
						sma5 = false;
					}
					}else{
						JOptionPane.showMessageDialog(null, "Please enter a score.");
					}
			}
		});
		
		align.insets = new Insets(500, 0, 0, 50);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(BigCover5, align);
		
		align.insets = new Insets(500, 500, 0, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.NORTH;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(small5, align);
		}catch(Exception r){
		}
		}
		next.setText("");
		next.setIcon(iconLoad1);
		next.setHorizontalTextPosition( SwingConstants.CENTER );
		next.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		prev.setText("");
		prev.setIcon(iconLoad2);
		prev.setHorizontalTextPosition( SwingConstants.CENTER );
		prev.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		next.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				sma1 = true;
				if(small1.getText() != null || !weight.getText().trim().equals("") ){
					try{
						Integer.parseInt(small1.getText());}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Please enter Whole Numbers only. Decimals and Fractions are allowed.");
						sma1 = false;
					}}else{JOptionPane.showMessageDialog(null, "Please enter a score.");}
				
				sma2 = true;
				if(small2.getText() != null || !weight.getText().trim().equals("") ){
					try{Integer.parseInt(small2.getText());}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Please enter Whole Numbers only. Decimals and Fractions are allowed.");
						sma2 = false;
					}}else{JOptionPane.showMessageDialog(null, "Please enter a score.");}
				
				sma3 = true;
				if(small3.getText() != null || !weight.getText().trim().equals("") ){
					try{Integer.parseInt(small3.getText());}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Please enter Whole Numbers only. Decimals and Fractions are allowed.");
						sma3 = false;
					}}else{JOptionPane.showMessageDialog(null, "Please enter a score.");}
				
				sma4 = true;
				if(small4.getText() != null || !weight.getText().trim().equals("") ){
					try{Integer.parseInt(small4.getText());}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Please enter Whole Numbers only. Decimals and Fractions are allowed.");
						sma4 = false;
					}}else{JOptionPane.showMessageDialog(null, "Please enter a score.");}
				
				sma5 = true;
				if(small5.getText() != null || !weight.getText().trim().equals("") ){
					try{Integer.parseInt(small5.getText());}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Please enter Whole Numbers only. Decimals and Fractions are allowed.");
						sma5 = false;
					}}else{JOptionPane.showMessageDialog(null, "Please enter a score.");}
				
				//(sma1+", "+sma2+", "+sma3+", "+sma4+", "+sma5);
				if (sma1 == false){JOptionPane.showMessageDialog(null, "Please enter score in box 1.");}
				if (sma2 == false){JOptionPane.showMessageDialog(null, "Please enter score in box 2.");}
				if (sma3 == false){JOptionPane.showMessageDialog(null, "Please enter score in box 3.");}
				if (sma4 == false){JOptionPane.showMessageDialog(null, "Please enter score in box 4.");}
				if (sma5 == false){JOptionPane.showMessageDialog(null, "Please enter score in box 5.");}
				if(sma1 == false || sma2 == false || sma3 == false || sma4 == false || sma5 == false){
				}else{
				if(weight.getText().trim().equals("") || weight.getText().trim() == null){
				JOptionPane.showMessageDialog(null, "Please Enter this assessment weight int the box above.");
				}else{
				int score1 = Integer.parseInt(small1.getText().trim());	int score2 = Integer.parseInt(small2.getText().trim());
				int score3 = Integer.parseInt(small3.getText().trim());	int score4 = Integer.parseInt(small4.getText().trim());
				int score5 = Integer.parseInt(small5.getText().trim());	
				ScoreV.remove((anti-5));ScoreV.add((anti-5), score1);	ScoreV.remove((anti-4));ScoreV.add((anti- 4), score2);	
				ScoreV.remove((anti-3));ScoreV.add((anti-3), score3);	ScoreV.remove((anti-2));ScoreV.add((anti-2), score4);
				ScoreV.remove((anti-1));ScoreV.add((anti-1), score5);
				wgt =Integer.parseInt(weight.getText().trim());	
				int p = 0; int val = 0; int ScoSize = ScoreV.size();
				while(p<ScoSize){
					val = val+ScoreV.get(p);
					p++;
				}
				score = score1+score2+score3+score4+score5+Score;
				//("This is present Score: "+ScoreV);
				JOptionPane.showMessageDialog(null, "This is present Score: "+val);
				wgt = Integer.parseInt(weight.getText().trim());
				dispose();
				//if(anti >= TAns){anti = anti - 10;}
				new StudentEssay(IDNO, subject, claxx, evalType, week, ansrs, anti, score, wgt, wgt+"", ScoreV);
				}
				}
			}
			public void mouseEntered(MouseEvent arg0) {
				next.setText("To Next Page");
			}
			public void mouseExited(MouseEvent arg0) {
				next.setText("");
			}
		});
		
		prev.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				//("Before: "+anti);
				dispose(); 
				if(anti > 5){ anti = anti-10;}
				//("After: "+anti);
				new StudentEssay(IDNO, subject, claxx, evalType, week, ansrs, anti, Score, wgt, wgt+"", ScoreV);
			}
			public void mouseEntered(MouseEvent arg0) {
				prev.setText("To Previous Page");
			}
			public void mouseExited(MouseEvent arg0) {
				prev.setText("");
			}
		});
		
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				SubmissionsClient submit = new SubmissionsClient();
				submit.submitEssayScore(IDNO, subject, claxx, evalType, week, val, wgt);
				JOptionPane.showMessageDialog(null, "Student ID No.: "+IDNO+ " -: "+val+" marks. Please relaunch table to view update.");
			}
			
		});
		
	}

}
