package baseGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Administration.ImageFileView;
import Administration.ImageFilter;
import Administration.ImagePreview;

public class EssayEngine extends JFrame {
	private static int SW;
	private static int SH;
	double inicialS, finalS;
	private JFileChooser fc = null; private File file = null; private String imageName = null, DT = null;
	private JLabel image = new JLabel(""), image2 = new JLabel(""), image3 = new JLabel(""), image4 = new JLabel(""), image5 = new JLabel("");
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
	private QuestionClient Serve = new QuestionClient();
	private CaseLoader brief = new CaseLoader();
	private JLabel Heading = new JLabel("");
	private JLabel HeadLow = new JLabel("");
	private JTextArea Instruction;
	private JScrollPane IWrap;
	private JTextArea Question1, Question2, Question3, Question4, Question5;
	private JScrollPane Q1Pane, Q2Pane, Q3Pane, Q4Pane, Q5Pane;
	private int TextCol, TextRow, bookmark;
	private JLabel no = new JLabel("");
	private JButton Diagram1 = new JButton("1"), Diagram2 = new JButton("2"), Diagram3 = new JButton("3"),
			Diagram4 = new JButton("4"), Diagram5 = new JButton("5");
	private String topic, question = "";
	private ArrayList <Object> QArray = new ArrayList <Object> ();
	private ImageIcon iconLoad1 = new ImageIcon("forward.png"); 
	private JLabel next = new JLabel(iconLoad1);
	
	private ImageIcon iconLoad2 = new ImageIcon("back.png"); 
	private JLabel prev = new JLabel(iconLoad2);
	
	private GridBagConstraints align = new GridBagConstraints();
	private FileSerializer saveEssay = new FileSerializer();
	
	
	public EssayEngine(final int IDNo, final int NoQ, final int pointing, final int weighting, final int BookMark, 
			final String Work, final String Username, final String Clazz, final String Subject, final String Examday, 
			final String Examtime, final String Duration, final String Term, final String Examiner, final String Topic, 
			final ArrayList qArray, final int Week) {
		super(Subject.toUpperCase()+" WEEK "+Week);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		getContentPane().setBackground(new Color(100, 100, 250));
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		SW = size.width;
		SH = size.height;
		//(SW+", "+SH);
		SW = (int) (0.7*SW);
		SH = (int) (0.95*SH);		//size for screen with taskbar for height
		//for EssayEngine we will use
		setSize(SW, SH); 		
		TextCol = (int) (SW/15);
		TextRow = (int) (SH/140);
		setLayout(new GridBagLayout());
		//(SW+", "+SH);
		//(TextRow+", "+TextCol);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		////(BookMark+", begin "+qArray);
												//numbering
		if(BookMark == 0){
			bookmark = 0;
			prev.setVisible(false);
		}else{bookmark = BookMark;}
		
		if(bookmark < NoQ ){  					// initialize page if number is within range
		int tLen = Topic.length();				//Header
		if(tLen > 25) {
			topic = Topic.substring(0, 23)+"...";
		}else{topic = Topic;}
		
		if(qArray != null){			// initiate ArrayList to lift out Array 
		QArray = qArray;
		}else{
		int rollno = 0;
		while(rollno <(NoQ+NoQ)){		//+5 is for diagram
			QArray.add(rollno);
			rollno++;
		}
		
		QArray.add(IDNo); QArray.add(NoQ);  QArray.add(pointing); QArray.add(weighting);  QArray.add(Work);
		QArray.add(Username); QArray.add(Clazz);  QArray.add(Subject); QArray.add(Examday);  QArray.add(Examtime);
		QArray.add(Duration); QArray.add(Term);  QArray.add(Examiner); QArray.add(Topic);
		//("Active");
		}
		
		next.setText("");			
		next.setIcon(iconLoad1);
		next.setHorizontalTextPosition( SwingConstants.CENTER );
		next.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		prev.setText("");
		prev.setIcon(iconLoad2);
		prev.setHorizontalTextPosition( SwingConstants.CENTER );
		prev.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		Heading.setText(Subject.toUpperCase()+" "+Work.toUpperCase()+" : "+Topic.toUpperCase());
		if(Work.trim().equalsIgnoreCase("assignment")){
		HeadLow.setText("To be submitted before: "+Examday.toLowerCase()+" by "+Examtime);
		}else{
		HeadLow.setText("Schedule: "+Examday+" Time: "+Examtime);
		}
		Heading.setFont(new Font("serif", Font.BOLD, 22));
		HeadLow.setFont(new Font("serif", Font.BOLD, 16));
		Instruction = new JTextArea(TextRow-3, TextCol-10);
		IWrap = new JScrollPane(Instruction);
		align.insets = new Insets(0, 0, 500, 0);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(IWrap, align);
		
		align.insets = new Insets(0, 0, 630, 0);
		add(Heading, align);
		
		align.insets = new Insets(0, 0, 570, 0);
		add(HeadLow, align);
		
		align.insets = new Insets(0, 0, 560, 850);
		add(prev, align);
		align.insets = new Insets(0, 850, 560, 0);
		add(next, align);
		question = "";
		JLabel inst = new JLabel("Instructions:");
		align.insets = new Insets(0, 0, 500, 670);
		add(inst, align);
		if(!QArray.get(0).toString().trim().equals("0")){
			Instruction.setText(QArray.get(0).toString());
			QArray.remove(0);
			question = Instruction.getText(); 
			QArray.add(0, question);
		}else{
			QArray.remove(0); 
			Instruction.setText("Type your instructions here.");
			question = Instruction.getText(); 
			QArray.add(0, question);
		}
		
		Instruction.setBackground(Color.lightGray);
		Instruction.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				Instruction.selectAll();
				Instruction.setBackground(Color.white);
			}
			public void focusLost(FocusEvent arg0) {
				question = Instruction.getText();
				QArray.remove(0);
				QArray.add(0, question);
			}
		});
		
		bookmark = bookmark+1; 
		no.setText(bookmark+".");
		Diagram1.setText(bookmark+"");
		Image passp;
		try {
			passp = geticon(bookmark, NoQ, Subject);
			Image scaled = passp.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
			image.setIcon(new ImageIcon(scaled));
			image.setVisible(true);
			Diagram1.setText("Remove "+bookmark);
		} catch (IOException e) {}
		Diagram1.setBackground(Color.ORANGE);
		
		Question1 = new JTextArea(TextRow, TextCol);
		Question1.setLineWrap(true);
		Question1.setWrapStyleWord(true);
		Q1Pane = new JScrollPane(Question1);
		Question1.setText(QArray.get(bookmark)+"");
		Question1.setBackground(Color.lightGray);
		Question1.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				Question1.setBackground(Color.white);
			}
			public void focusLost(FocusEvent arg0) {
				question = Question1.getText();
				QArray.remove(bookmark);
				QArray.add(bookmark, question);
			}
		});
		align.insets = new Insets(0, 0, 350, 100);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Q1Pane, align);
		align.insets = new Insets(0, 800, 350, 0);
		add(Diagram1, align);
		align.insets = new Insets(0, 660, 350, 0);
		add(image, align);
		align.insets = new Insets(0, 0, 350, 900);
		add(new JLabel(bookmark+"."), align);
		
		bookmark = bookmark+1;  
		no.setText(bookmark+".");
		Diagram2.setText(bookmark+"");
		Image passp2;
		try {
			passp2 = geticon(bookmark, NoQ, Subject);
			Image scaled = passp2.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
			image2.setIcon(new ImageIcon(scaled));
			image2.setVisible(true);
			Diagram2.setText("Remove "+bookmark);
		} catch (IOException e) {}
		Diagram2.setBackground(Color.blue);
		Question2 = new JTextArea(TextRow, TextCol);
		Question2.setLineWrap(true);
		Question2.setWrapStyleWord(true);
		Q2Pane = new JScrollPane(Question2);
		Question2.setText(QArray.get(bookmark)+"");
		Question2.setBackground(Color.lightGray);
		Question2.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				Question2.setBackground(Color.white);
			}
			public void focusLost(FocusEvent arg0) {
				question = Question2.getText();
				QArray.remove(bookmark);
				QArray.add(bookmark, question);
			}
		});
		align.insets = new Insets(0, 0, 150, 100);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Q2Pane, align);
		align.insets = new Insets(0, 800, 150, 0);
		add(Diagram2, align);
		align.insets = new Insets(0, 660, 150, 0);
		add(image2, align);
		align.insets = new Insets(0, 0, 150, 900);
		add(new JLabel(bookmark+"."), align);
		
		
		bookmark = bookmark+1;  
		no.setText(bookmark+".");
		Diagram3.setText(bookmark+"");
		Image passp3;
		try {
			passp3 = geticon(bookmark, NoQ, Subject);
			Image scaled = passp3.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
			image3.setIcon(new ImageIcon(scaled));
			image3.setVisible(true);
			Diagram3.setText("Remove "+bookmark);
		} catch (IOException e) {}
		Diagram3.setBackground(Color.GREEN);
		Question3 = new JTextArea(TextRow, TextCol);
		Question3.setLineWrap(true);
		Question3.setWrapStyleWord(true);
		Q3Pane = new JScrollPane(Question3);
		Question3.setText(QArray.get(bookmark)+"");
		Question3.setBackground(Color.lightGray);
		Question3.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				Question3.setBackground(Color.white);
			}
			public void focusLost(FocusEvent arg0) {
				question = Question3.getText();
				QArray.remove(bookmark);
				QArray.add(bookmark, question);
			}
		});
		align.insets = new Insets(50, 0, 0, 100);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Q3Pane, align);
		align.insets = new Insets(50, 800, 0, 0);
		add(Diagram3, align);
		align.insets = new Insets(50, 660, 0, 0);
		add(image3, align);
		align.insets = new Insets(50, 0, 0, 900);
		add(new JLabel(bookmark+"."), align);
		
		
		bookmark = bookmark+1;   
		no.setText(bookmark+".");
		Diagram4.setText(bookmark+"");
		Image passp4;
		try {
			passp4 = geticon(bookmark, NoQ, Subject);
			Image scaled = passp4.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
			image4.setIcon(new ImageIcon(scaled));
			image4.setVisible(true);
			Diagram4.setText("Remove "+bookmark);
		} catch (IOException e) {}
		Diagram4.setBackground(Color.RED);
		Question4 = new JTextArea(TextRow, TextCol);
		Question4.setLineWrap(true);
		Question4.setWrapStyleWord(true);
		Q4Pane = new JScrollPane(Question4);
		Question4.setText(QArray.get(bookmark)+"");
		Question4.setBackground(Color.lightGray);
		Question4.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				Question4.setBackground(Color.white);
			}
			public void focusLost(FocusEvent arg0) {
				question = Question4.getText();
				QArray.remove(bookmark);
				QArray.add(bookmark, question);
			}
		});
		align.insets = new Insets(250, 0, 0, 100);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Q4Pane, align);
		align.insets = new Insets(250, 800, 0, 0);
		add(Diagram4, align);
		align.insets = new Insets(250, 660, 0, 0);
		add(image4, align);
		align.insets = new Insets(250, 0, 0, 900);
		add(new JLabel(bookmark+"."), align);
		
		bookmark = bookmark+1;  
		no.setText(bookmark+".");
		Diagram5.setText(bookmark+"");
		Image passp5;
		try {
			passp5 = geticon(bookmark, NoQ, Subject);
			Image scaled = passp5.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
			image5.setIcon(new ImageIcon(scaled));
			image5.setVisible(true);
			Diagram5.setText("Remove "+bookmark);
		} catch (IOException e) {}
		Diagram5.setBackground(Color.CYAN);
		Question5 = new JTextArea(TextRow, TextCol);
		Question5.setLineWrap(true);
		Question5.setWrapStyleWord(true);
		Q5Pane = new JScrollPane(Question5);
		Question5.setText(QArray.get(bookmark)+"");
		Question5.setBackground(Color.lightGray);
		Question5.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {
				Question5.setBackground(Color.white);
			}
			public void focusLost(FocusEvent arg0) {
				question = Question5.getText();
				QArray.remove(bookmark);
				QArray.add(bookmark, question);
			}
		});
		align.insets = new Insets(500, 0, 0, 100);		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
		add(Q5Pane, align);
		align.insets = new Insets(500, 800, 0, 0);
		add(Diagram5, align);
		align.insets = new Insets(500, 660, 0, 0);
		add(image5, align);
		align.insets = new Insets(500, 0, 0, 900);
		add(new JLabel(bookmark+"."), align);
		//(bookmark+", end "+QArray);
		}else{
			setLayout(new GridBagLayout());
			int resp = JOptionPane.showConfirmDialog(null, "Are you done?");
			if(resp == 0){
				JLabel end = new JLabel("Your work has been saved. Goodbye!");
				align.insets = new Insets(0, 0, 0, 0);
				align.anchor = GridBagConstraints.CENTER;
				align.gridx = 0;
				align.gridy = 0;
				align.weightx = 0;
				align.weighty = 0;
				align.fill = GridBagConstraints.NONE;
				add(end, align);
				
				//(qArray);
				
				String thisfile = Week+"+"+Work+"+"+"Essay"+"+"+Clazz;
				 File file4 = new File("C:\\LocalSubjects\\"+Subject+"\\"+Work);
				 	if (!file4.exists()) {
				 		new File("C:\\LocalSubjects\\"+Subject+"\\"+Work).mkdirs();
				 		//(file4+ " now exists.");
				 		}
				File file5 = new File("C:\\LocalSubjects\\"+Subject+"\\"+Work+"\\"+thisfile);
				
				try {
					saveEssay.savetoFile(qArray, file5);
				} catch (IOException e) {
					e.printStackTrace();
				}
					
				boolean network = Serve.Work(IDNo, Work, Username, Clazz, Subject, Examday, Examtime,
						Duration, Term, NoQ, pointing,"Essay", thisfile, Topic, Week, weighting); //Inform server that this person has questions
				if(network == false)JOptionPane.showMessageDialog(null, "You are offline. Your work will be saved.");
				Serve.DiagramStream(qArray, NoQ, Subject);
				if(network == false){
					Object essayparameters[] = {IDNo, Work, Username, Clazz, Subject, Examday, Examtime,
							Duration, Term, NoQ, pointing,"Essay", file5, Topic, Week, weighting};
					brief.EssaycaseLoader(essayparameters, thisfile);
				}
				
			}else{
				prev.setText("");
				prev.setIcon(iconLoad2);
				prev.setHorizontalTextPosition( SwingConstants.CENTER );
				prev.setVerticalTextPosition( SwingConstants.BOTTOM );
				
				
				align.insets = new Insets(0, 0, 100, 0);		// format: push(DOWN, right, UP, left)
				align.anchor = GridBagConstraints.CENTER;
				align.gridx = 0;
				align.gridy = 0;
				align.weightx = 0;
				align.weighty = 0;
				align.fill = GridBagConstraints.NONE;
				add(prev, align);
			}
			
		}
		setVisible(true);
		
		
//__________________________________________________________________________________________________________________
		
		next.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				
				//(bookmark+",i "+QArray);
				
				QArray.remove(bookmark-4); QArray.add(bookmark-4, Question1.getText()); //(QArray.get(bookmark-4));
				QArray.remove(bookmark-3); QArray.add(bookmark-3, Question2.getText());  //(QArray.get(bookmark-3));
				QArray.remove(bookmark-2); QArray.add(bookmark-2, Question3.getText());  //(QArray.get(bookmark-2));
				QArray.remove(bookmark-1); QArray.add(bookmark-1, Question4.getText());  //(QArray.get(bookmark-1));
				QArray.remove(bookmark); QArray.add(bookmark, Question5.getText());		 //(QArray.get(bookmark));
				QArray.remove(bookmark-bookmark); QArray.add(0, Instruction.getText());
				//(bookmark+",ii "+QArray);
				dispose();
				new EssayEngine(IDNo, NoQ, pointing, weighting, bookmark, 
						Work, Username, Clazz, Subject, Examday, Examtime, Duration, Term, Examiner, Topic, QArray, Week);
			}
			public void mouseEntered(MouseEvent arg0) {
				next.setText("Next Page");
			}
			public void mouseExited(MouseEvent arg0) {
				next.setText("");
		}
	});
		
		
		prev.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				if(bookmark < NoQ){
					QArray.remove(bookmark-bookmark); QArray.add(0, Instruction.getText());
					QArray.remove(bookmark-4); QArray.add(bookmark-4, Question1.getText());
					QArray.remove(bookmark-3); QArray.add(bookmark-3, Question2.getText());
					QArray.remove(bookmark-2); QArray.add(bookmark-2, Question3.getText());
					QArray.remove(bookmark-1); QArray.add(bookmark-1, Question4.getText());
					QArray.remove(bookmark); QArray.add(bookmark, Question5.getText());
				}else{
					QArray = qArray;
				}
				
				new EssayEngine(IDNo, NoQ, pointing, weighting, bookmark-10, 
						Work, Username, Clazz, Subject, Examday, Examtime, Duration, Term, Examiner, Topic, QArray, Week);
			}
			public void mouseEntered(MouseEvent arg0) {
				prev.setText("Previous");
			}
			public void mouseExited(MouseEvent arg0) {
				prev.setText("");
		}
	});
		
		//____________________________________diagram chooser_____________________________________________________________
		
		Diagram1.addActionListener(new ActionListener(){
			int ND = 0;
			public void actionPerformed(ActionEvent arg0) {
				try{
				ND = Integer.parseInt(Diagram1.getText().trim());
				}catch(Exception g){
					String Q = Diagram1.getText().trim().substring(7);
					//(Q);
					ND = Integer.parseInt(Q);
					imageName = QArray.get(ND+NoQ).toString().trim();
					File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
					if(dest.exists()){
						dest.delete();
						//(dest+ " deleted.");
						Diagram1.setText(ND+"");
						question = Question1.getText();
						question = question.replace(">(Please Click the icon to your right to view larger Diagram.)", "");
						QArray.remove(ND);
						QArray.add(ND, question);
						Question1.setText(QArray.get(ND)+"");
						image.setVisible(false);
					}
					return;
				}
				question = Question1.getText();
				if(question.length()>2){
					QArray.remove(ND);
					QArray.add(ND, question+" >(Please Click the picture icon to your right to view larger Diagram.)");
					//(ND+", diagram "+question);
					Question1.setText(QArray.get(ND)+"");
				}else{
					JOptionPane.showMessageDialog(null, "Please type the question first before loading Diagram.");
					return;
				}
				
				fc = new JFileChooser();
				 fc.addChoosableFileFilter(new ImageFilter());
		            fc.setAcceptAllFileFilterUsed(true);
			    //Add custom icons for file types.
		            fc.setFileView(new ImageFileView());
			    //Add the preview pane.
		            fc.setAccessory(new ImagePreview(fc));
				int result = fc.showOpenDialog(null);
				DT = format.format( new Date());
				if (result == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					try{
						imageName = ND+"_"+Week+"_"+Work+"_"+"Essay_"+Clazz+"_"+DT;
						int ArrayLoc = ND+NoQ;
						QArray.remove(ArrayLoc);
						QArray.add(ArrayLoc, imageName);
						//(ArrayLoc+"---"+imageName);
						String ImageLoc = file.getAbsolutePath();
						File source = new File(ImageLoc);
						 File file4 = new File("C:\\Teachers\\Diagrams\\"+Subject);
						 	if (!file4.exists()) {
						 		new File("C:\\Teachers\\Diagrams\\"+Subject).mkdirs();
						 		//(file4+ " now exists.");
						 		}
						File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
						FileChannel inputChannel = null;
						FileChannel outputChannel = null;

						inputChannel = new FileInputStream(source).getChannel();
						outputChannel = new FileOutputStream(dest).getChannel();
						outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
						try {
							inputChannel.close();
							outputChannel.close();
						} catch (IOException l) {
							l.printStackTrace();
						}
						File outputfile = new File(imageName);
						BufferedImage passp = ImageIO.read(file);
						Image scaled = passp.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
						image.setIcon(new ImageIcon(scaled));
						image.setVisible(true);
					}catch(IOException u){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						u.printStackTrace();
					}catch( NullPointerException e1){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						e1.printStackTrace();
					}
					Diagram1.setText("Remove "+ND);
				}
			}
		});
		
		Diagram2.addActionListener(new ActionListener(){
			int ND = 0;
			public void actionPerformed(ActionEvent arg0) {
				try{
					ND = Integer.parseInt(Diagram2.getText().trim());
					}catch(Exception g){
						String Q = Diagram2.getText().trim().substring(7);
						//(Q);
						ND = Integer.parseInt(Q);
						//("Here "+(ND+NoQ));
						imageName = QArray.get(ND+NoQ).toString().trim();
						File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
						//(dest);
						if(dest.exists()){
							dest.delete();
							//(dest+ " deleted.");
							Diagram2.setText(ND+"");
							question = Question2.getText();
							question = question.replace(">(Please Click the picture icon to your right to view larger Diagram.)", "");
							QArray.remove(ND);
							QArray.add(ND, question);
							Question2.setText(QArray.get(ND)+"");
							image2.setVisible(false);
						}
						return;
					}
					question = Question2.getText();
					if(question.length()>2){
						QArray.remove(ND);
						QArray.add(ND, question+" >(Please Click the picture icon to your right to view larger Diagram.)");
						//(ND+", diagram "+question);
						Question2.setText(QArray.get(ND)+"");
					}else{
						JOptionPane.showMessageDialog(null, "Please type the question first before loading Diagram.");
						return;
					}
				fc = new JFileChooser();
				 fc.addChoosableFileFilter(new ImageFilter());
		            fc.setAcceptAllFileFilterUsed(true);
			    //Add custom icons for file types.
		            fc.setFileView(new ImageFileView());
			    //Add the preview pane.
		            fc.setAccessory(new ImagePreview(fc));
				int result = fc.showOpenDialog(null);
				DT = format.format( new Date());
				if (result == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					try{
						imageName = ND+"_"+Week+"_"+Work+"_"+"Essay_"+Clazz+"_"+DT;
						int ArrayLoc = ND+NoQ;
						QArray.remove(ArrayLoc);
						QArray.add(ArrayLoc, imageName);
						//(ArrayLoc+"---"+imageName);
						String ImageLoc = file.getAbsolutePath();
						File source = new File(ImageLoc);
						 File file4 = new File("C:\\Teachers\\Diagrams\\"+Subject);
						 	if (!file4.exists()) {
						 		new File("C:\\Teachers\\Diagrams\\"+Subject).mkdirs();
						 		//(file4+ " now exists.");
						 		}
						File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
						FileChannel inputChannel = null;
						FileChannel outputChannel = null;

						inputChannel = new FileInputStream(source).getChannel();
						outputChannel = new FileOutputStream(dest).getChannel();
						outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
						try {
							inputChannel.close();
							outputChannel.close();
						} catch (IOException l) {
							l.printStackTrace();
						}
						File outputfile = new File(imageName);
						BufferedImage passp = ImageIO.read(file);
						Image scaled = passp.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
						image2.setIcon(new ImageIcon(scaled));
						image2.setVisible(true);
					}catch(IOException u){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						u.printStackTrace();
					}catch( NullPointerException e1){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						e1.printStackTrace();
					}
					Diagram2.setText("Remove "+ND);
				}
			}
		});
		
		Diagram3.addActionListener(new ActionListener(){
			int ND = 0;
			public void actionPerformed(ActionEvent arg0) {
				try{
				ND = Integer.parseInt(Diagram3.getText().trim());
				}catch(Exception g){
					String Q = Diagram3.getText().trim().substring(7);
					//(Q);
					ND = Integer.parseInt(Q);
					imageName = QArray.get(ND+NoQ).toString().trim();
					File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
					if(dest.exists()){
						dest.delete();
						//(dest+ " deleted.");
						Diagram3.setText(ND+"");
						question = Question3.getText();
						question = question.replace(">(Please Click the picture icon to your right to view larger Diagram.)", "");
						QArray.remove(ND);
						QArray.add(ND, question);
						Question3.setText(QArray.get(ND)+"");
						image3.setVisible(false);
					}
					return;
				}
				question = Question3.getText();
				if(question.length()>2){
					QArray.remove(ND);
					QArray.add(ND, question+" >(Please Click the picture icon to your right to view larger Diagram.)");
					//(ND+", diagram "+question);
					Question3.setText(QArray.get(ND)+"");
				}else{
					JOptionPane.showMessageDialog(null, "Please type the question first before loading Diagram.");
					return;
				}
				
				fc = new JFileChooser();
				 fc.addChoosableFileFilter(new ImageFilter());
		            fc.setAcceptAllFileFilterUsed(true);
			    //Add custom icons for file types.
		            fc.setFileView(new ImageFileView());
			    //Add the preview pane.
		            fc.setAccessory(new ImagePreview(fc));
				int result = fc.showOpenDialog(null);
				DT = format.format( new Date());
				if (result == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					try{
						imageName = ND+"_"+Week+"_"+Work+"_"+"Essay_"+Clazz+"_"+DT;
						int ArrayLoc = ND+NoQ;
						QArray.remove(ArrayLoc);
						QArray.add(ArrayLoc, imageName);
						//(ArrayLoc+"---"+imageName);
						String ImageLoc = file.getAbsolutePath();
						File source = new File(ImageLoc);
						 File file4 = new File("C:\\Teachers\\Diagrams\\"+Subject);
						 	if (!file4.exists()) {
						 		new File("C:\\Teachers\\Diagrams\\"+Subject).mkdirs();
						 		//(file4+ " now exists.");
						 		}
						File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
						FileChannel inputChannel = null;
						FileChannel outputChannel = null;

						inputChannel = new FileInputStream(source).getChannel();
						outputChannel = new FileOutputStream(dest).getChannel();
						outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
						try {
							inputChannel.close();
							outputChannel.close();
						} catch (IOException l) {
							l.printStackTrace();
						}
						File outputfile = new File(imageName);
						BufferedImage passp = ImageIO.read(file);
						Image scaled = passp.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
						image3.setIcon(new ImageIcon(scaled));
						image3.setVisible(true);
					}catch(IOException u){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						u.printStackTrace();
					}catch( NullPointerException e1){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						e1.printStackTrace();
					}
					Diagram3.setText("Remove "+ND);
				}
			}
		});
		
		Diagram4.addActionListener(new ActionListener(){
			int ND = 0;
			public void actionPerformed(ActionEvent arg0) {
				try{
				ND = Integer.parseInt(Diagram4.getText().trim());
				}catch(Exception g){
					String Q = Diagram4.getText().trim().substring(7);
					//(Q);
					ND = Integer.parseInt(Q);
					imageName = QArray.get(ND+NoQ).toString().trim();
					File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
					if(dest.exists()){
						dest.delete();
						//(dest+ " deleted.");
						Diagram4.setText(ND+"");
						question = Question4.getText();
						question = question.replace(">(Please Click the picture icon to your right to view larger Diagram.)", "");
						QArray.remove(ND);
						QArray.add(ND, question);
						Question4.setText(QArray.get(ND)+"");
						image4.setVisible(false);
					}
					return;
				}
				question = Question4.getText();
				if(question.length()>2){
					QArray.remove(ND);
					QArray.add(ND, question+" >(Please Click the picture icon to your right to view larger Diagram.)");
					//(ND+", diagram "+question);
					Question4.setText(QArray.get(ND)+"");
				}else{
					JOptionPane.showMessageDialog(null, "Please type the question first before loading Diagram.");
					return;
				}
				fc = new JFileChooser();
				 fc.addChoosableFileFilter(new ImageFilter());
		            fc.setAcceptAllFileFilterUsed(true);
			    //Add custom icons for file types.
		            fc.setFileView(new ImageFileView());
			    //Add the preview pane.
		            fc.setAccessory(new ImagePreview(fc));
				int result = fc.showOpenDialog(null);
				DT = format.format( new Date());
				if (result == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					try{
						imageName = ND+"_"+Week+"_"+Work+"_"+"Essay_"+Clazz+"_"+DT;
						int ArrayLoc = ND+NoQ;
						QArray.remove(ArrayLoc);
						QArray.add(ArrayLoc, imageName);
						//(ArrayLoc+"---"+imageName);
						String ImageLoc = file.getAbsolutePath();
						File source = new File(ImageLoc);
						 File file4 = new File("C:\\Teachers\\Diagrams\\"+Subject);
						 	if (!file4.exists()) {
						 		new File("C:\\Teachers\\Diagrams\\"+Subject).mkdirs();
						 		//(file4+ " now exists.");
						 		}
						File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
						FileChannel inputChannel = null;
						FileChannel outputChannel = null;

						inputChannel = new FileInputStream(source).getChannel();
						outputChannel = new FileOutputStream(dest).getChannel();
						outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
						try {
							inputChannel.close();
							outputChannel.close();
						} catch (IOException l) {
							l.printStackTrace();
						}
						File outputfile = new File(imageName);
						BufferedImage passp = ImageIO.read(file);
						Image scaled = passp.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
						image4.setIcon(new ImageIcon(scaled));
						image4.setVisible(true);
					}catch(IOException u){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						u.printStackTrace();
					}catch( NullPointerException e1){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						e1.printStackTrace();
					}
					Diagram4.setText("Remove "+ND);
				}
			}
		});
		
		Diagram5.addActionListener(new ActionListener(){
			int ND = 0;
			public void actionPerformed(ActionEvent arg0) {
				try{
				ND = Integer.parseInt(Diagram5.getText().trim());
				}catch(Exception g){
					String Q = Diagram5.getText().trim().substring(7);
					//(Q);
					ND = Integer.parseInt(Q);
					imageName = QArray.get(ND+NoQ).toString().trim();
					File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
					if(dest.exists()){
						dest.delete();
						//(dest+ " deleted.");
						Diagram5.setText(ND+"");
						question = Question5.getText();
						question = question.replace(">(Please Click the picture icon to your right to view larger Diagram.)", "");
						QArray.remove(ND);
						QArray.add(ND, question);
						Question5.setText(QArray.get(ND)+"");
						image5.setVisible(false);
					}
					return;
				}
				question = Question5.getText();
				if(question.length()>2){
					QArray.remove(ND);
					QArray.add(ND, question+" >(Please Click the picture icon to your right to view larger Diagram.)");
					//(ND+", diagram "+question);
					Question5.setText(QArray.get(ND)+"");
				}else{
					JOptionPane.showMessageDialog(null, "Please type the question first before loading Diagram.");
					return;
				}
				fc = new JFileChooser();
				 fc.addChoosableFileFilter(new ImageFilter());
		            fc.setAcceptAllFileFilterUsed(true);
			    //Add custom icons for file types.
		            fc.setFileView(new ImageFileView());
			    //Add the preview pane.
		            fc.setAccessory(new ImagePreview(fc));
				int result = fc.showOpenDialog(null);
				DT = format.format( new Date());
				if (result == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					try{
						imageName = ND+"_"+Week+"_"+Work+"_"+"Essay_"+Clazz+"_"+DT;
						int ArrayLoc = ND+NoQ;
						QArray.remove(ArrayLoc);
						QArray.add(ArrayLoc, imageName);
						//(ArrayLoc+"---"+imageName);
						String ImageLoc = file.getAbsolutePath();
						File source = new File(ImageLoc);
						 File file4 = new File("C:\\Teachers\\Diagrams\\"+Subject);
						 	if (!file4.exists()) {
						 		new File("C:\\Teachers\\Diagrams\\"+Subject).mkdirs();
						 		//(file4+ " now exists.");
						 		}
						File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
						FileChannel inputChannel = null;
						FileChannel outputChannel = null;

						inputChannel = new FileInputStream(source).getChannel();
						outputChannel = new FileOutputStream(dest).getChannel();
						outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
						try {
							inputChannel.close();
							outputChannel.close();
						} catch (IOException l) {
							l.printStackTrace();
						}
						File outputfile = new File(imageName);
						BufferedImage passp = ImageIO.read(file);
						Image scaled = passp.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
						image5.setIcon(new ImageIcon(scaled));
						image5.setVisible(true);
					}catch(IOException u){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						u.printStackTrace();
					}catch( NullPointerException e1){
						ImageFilter f = new ImageFilter();
						JOptionPane.showMessageDialog(null, f.getDescription());
						e1.printStackTrace();
					}
					Diagram5.setText("Remove "+ND);
				}
			}
		});
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				String thisfile = Week+"+"+Work+"+"+"Essay"+"+"+Clazz;
				//("in delete");
				File file5 = new File("C:\\LocalSubjects\\"+Subject+"\\"+Work+"\\"+thisfile);
				//("Trying to delete "+file5.getAbsolutePath());
				if(file5.exists()){
					System.gc();
					file5.deleteOnExit();
				}
				dispose();
			}
		});
	}
	

	private Image geticon(int bookmark, int NoQ, String Subject) throws IOException {
		//("Here "+(bookmark+NoQ));
		String imageName = QArray.get(bookmark+NoQ).toString().trim();
		File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
		//("Here "+dest);
		BufferedImage passp = null;
		passp = ImageIO.read(dest); 
		return passp;
	}
	
	private int resizer(int inicialposition){
		int newposition = (int)(inicialposition*(finalS/inicialS));
		//(newposition);
		return newposition;
	}

	public static void main(String[] args) {
		int IDNo = 5;
		String Work = "Test",  Username = "joseph",  Clazz = "SSS3", Subject = "English", Examday ="12/11/2014", 
				Examtime = "10:15 AM", Duration = "105 Minutes", Term = "first", Examiner = "", Topic = " - ";
		int NoQ = 30, pointing = 2, weighting = pointing*NoQ, BookMark = 0, Week = 4;
		ArrayList QList = null;
		new EssayEngine(IDNo, NoQ, pointing, weighting, BookMark, 
				Work, Username, Clazz, Subject, Examday, Examtime, Duration, Term, Examiner, Topic, QList, Week);
	}
}
