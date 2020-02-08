package baseGui;

import java.awt.BorderLayout;
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
import java.awt.event.FocusListener;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Administration.ImageFileView;
import Administration.ImageFilter;
import Administration.ImagePreview;

public class mcqEngine extends JFrame implements FocusListener {
	private static int SW;
	private static int SH;
	private double inicialW, finalH, inicialH, finalW;
	private JFileChooser fc = null; private File file = null; private String imageName = null, DT = null;
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
	private QuestionClient Serve = new QuestionClient();
	private CaseLoader brief = new CaseLoader();
	private JLabel Heading = new JLabel("");
	private JLabel HeadLow = new JLabel("");
	private JTextArea Instruction;
	private JScrollPane IWrap;
	private JTextArea Question1, Question2, Question3, Question4, Question5;
	private JScrollPane Q1Pane, Q2Pane, Q3Pane, Q4Pane, Q5Pane;
	private int TextCol, TextRow, bookmark, g, No, clear, co;
	private JLabel no = new JLabel("");
	
	private ArrayList <Object> QArray = new ArrayList <Object> ();
	private ImageIcon iconLoad1 = new ImageIcon("savesub.png"); 
	private JLabel save = new JLabel(iconLoad1);
	
	private GridBagConstraints align = new GridBagConstraints();
	private FileSerializer saveMCQ = new FileSerializer();
	private JTextField[] options;
	private JTextField[] answers;
	private JTextArea[] questions;
	private JButton[] Diagrams;
	private JLabel[] images;
	private JPanel form = new JPanel();
	private JScrollPane formWrap;
	private JScrollPane Wrap;
	public mcqEngine(final int IDNo, final int NoQ, final int pointing, final int weighting, final int BookMark, 
			final String Work, final String Username, final String Clazz, final String Subject, final String Examday, 
			final String Examtime, final String Duration, final String Term, final String Examiner, final String Topic, 
			final ArrayList qArray, final int Week) {
		super(Subject.toUpperCase()+" WEEK "+Week);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		getContentPane().setBackground(new Color(100, 100, 250));
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		inicialW = 683; 
		inicialH = 460;	//size for screen with taskbar for height
		//for MCQEngine we will use
		SW = size.width;	finalW= SW;
		SH = size.height;	finalH= SH;
		//(SW+", "+SH);
		No = NoQ;
		setSize(683, 460); 		
		TextCol = (int) (SW/15);
		TextRow = (int) (SH/140);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		form.setPreferredSize(new Dimension(SW-50, resizerH((No*140)+300)));
		//("Old "+(SW-50)+", "+((No*140)+300));
		int val = (No*140)+300;
		form.setLayout(new GridBagLayout());
		//("Resized "+(resizerW(SW-50)+", "+resizerH(((No*140)+300))));
		
		save.setText("Save Questions");			
		save.setIcon(iconLoad1);
		save.setHorizontalTextPosition( SwingConstants.CENTER );
		save.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		if(qArray != null){			// initiate ArrayList to lift out Array 
			QArray = qArray;
			}else{
			int rollno = 0;
			while(rollno <=((No*5)+(No*3))){		//No is for questions, diagrams, answers
				QArray.add(rollno);
				rollno++;
			}
			QArray.add(IDNo); QArray.add(No);  QArray.add(pointing); QArray.add(weighting);  QArray.add(Work);
			QArray.add(Username); QArray.add(Clazz);  QArray.add(Subject); QArray.add(Examday);  QArray.add(Examtime);
			QArray.add(Duration); QArray.add(Term);  QArray.add(Examiner); QArray.add(Topic);
			//("Active");
			}
		
		int marker = 1; int k = 0; int y = resizerH(((No*140))); int x = 0;
		//("y = "+y);
		
		Heading.setText(Subject.toUpperCase()+" "+Work.toUpperCase()+" : "+Topic.toUpperCase());
		if(Work.trim().equalsIgnoreCase("assignment")){
			HeadLow.setText("To be submitted before: "+Examday.toLowerCase()+" by "+Examtime);
			}else{
			HeadLow.setText("Schedule: "+Examday+" Time: "+Examtime);
			}
		Heading.setFont(new Font("serif", Font.BOLD, resizerH(22)-10));
		HeadLow.setFont(new Font("serif", Font.BOLD, resizerH(16)-10));
		//("y= "+y);
		align.insets = new Insets(resizerH(0), resizerW(0), y+350, resizerW(0));		// format: push(DOWN, right, UP, left)
		align.anchor = GridBagConstraints.CENTER;
		align.gridx = 0;
		align.gridy = 0;
		align.weightx = 0;
		align.weighty = 0;
		align.fill = GridBagConstraints.NONE;
	    form.add( Heading, align);
	    align.insets = new Insets(resizerH(0), resizerW(560), y+280, resizerW(0));
		form.add(save, align);
	    align.insets = new Insets(resizerH(0), resizerW(0), y+280, resizerW(0));
		form.add(HeadLow, align);
		form.setBackground(new Color(100, 100, 250));
		align.insets = new Insets(resizerH(0), resizerW(0), y+200, resizerW(0));
		Instruction = new JTextArea(TextRow-3, TextCol-6);
		Instruction.setLineWrap(true);
		Instruction.setWrapStyleWord(true);
		Instruction.setText(QArray.get(0).toString());
		IWrap = new JScrollPane(Instruction);
		form.add(IWrap, align);
		JLabel inst = new JLabel("Instructions:");
		align.insets = new Insets(resizerH(0), resizerW(0), y+200, resizerW(530));
		form.add(inst, align);
		
		Instruction.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent arg0) {
				QArray.remove(0);
				QArray.add(0, Instruction.getText());
			}
		});
		
		questions = new JTextArea[No];
		answers = new JTextField[No];
		options = new JTextField[(No*5)+No];
		Diagrams = new JButton[No];
		images = new JLabel[No];
		for (int i = 0; i < ((No*5)+No); i++) { 
		   if(marker == 1){
			   answers[k] = new JTextField("A", resizerW(1)); g = k;
			   answers[k].addFocusListener(this);
			   questions[k] = new JTextArea("Question "+(k+1), resizerH(4), resizerW(40));
			   questions[k].addFocusListener(this); 
			   questions[k].addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent y) {
					Object who = y.getSource();
					if(y.getClickCount() == 2){
							((JTextArea) who).setText("");
					}
				}
			   });
			   questions[k].setLineWrap(true);
			   questions[k].setWrapStyleWord(true);
			   Wrap = new JScrollPane(questions[k]);
			   Diagrams[k] = new JButton((k+1)+"");  g = k;
			   Diagrams[k].setPreferredSize(new Dimension(resizerW(50), resizerH(10)));
			   images[k] = new JLabel("Fig. "+(k+1)); 
			   images[k].setPreferredSize(new Dimension(resizerW(50), resizerH(50)));
			   no.setText(g+".");
			   Diagrams[k].addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e) {
					//(e.getActionCommand() +" clicked");
					if(!e.getActionCommand().contains("Remove")){
					getImage(Integer.parseInt(e.getActionCommand()));
					}else{
						int re = Integer.parseInt(e.getActionCommand().substring(7));
						images[re-1].setVisible(false);
						Diagrams[re-1].setText(re+"");
					}
				}

				private void getImage(int figure) {
					fc = new JFileChooser();
					 fc.addChoosableFileFilter(new ImageFilter());
			            fc.setAcceptAllFileFilterUsed(true);
			            fc.setFileView(new ImageFileView());
			            fc.setAccessory(new ImagePreview(fc));
					int result = fc.showOpenDialog(null);
					DT = format.format( new Date());
					if (result == JFileChooser.APPROVE_OPTION){
						file = fc.getSelectedFile();
						try{
							imageName = figure+"_"+Week+"_"+Work+"_"+"MCQ_"+Clazz+"_"+DT;
							//("No of Questions= "+No);
							int ArrayLoc = figure+((No*5)+(No));
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
							BufferedImage passp = ImageIO.read(source);
							Image scaled = passp.getScaledInstance(resizerW(50), resizerH(50), BufferedImage.SCALE_SMOOTH);
							images[figure-1].setIcon(new ImageIcon(scaled));
							images[figure-1].setVisible(true);
						}catch(IOException u){
							ImageFilter f = new ImageFilter();
							JOptionPane.showMessageDialog(null, f.getDescription());
							u.printStackTrace();
						}catch( NullPointerException e1){
							ImageFilter f = new ImageFilter();
							JOptionPane.showMessageDialog(null, f.getDescription());
							e1.printStackTrace();
						}
						Diagrams[figure-1].setText("Remove "+figure);
				}}
			   });
			 
			   align.insets = new Insets(resizerH(0), resizerW(0), y, resizerW(150));		// format: push(DOWN, right, UP, left)
				align.anchor = GridBagConstraints.CENTER;
				align.gridx = 0;
				align.gridy = 0;
				align.weightx = 0;
				align.weighty = 0;
				align.fill = GridBagConstraints.NONE;
				form.add( Wrap, align);
				align.insets = new Insets( resizerH(0), resizerW(600), y-70, resizerW(0));
				form.add(answers[k], align);
				align.insets = new Insets( resizerH(0), resizerW(400), y-70, resizerW(0));
				form.add(Diagrams[k], align);
				align.insets = new Insets( resizerH(0), resizerW(400), y+10, resizerW(0));
			    form.add(images[k], align);
			    align.insets = new Insets( resizerH(0), resizerW(0), y+10, resizerW(620));
			    form.add(new JLabel((k+1)+""), align);
			   k++; x=0; y-=280; i = i+1;
		   }
		   options[i] = new JTextField("Option "+(k), resizerW(10));
		   options[i].addFocusListener(this); co = k;
		   options[i].addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent y) {
					Object who = y.getSource();
					if(y.getClickCount() == 2){
							((JTextField) who).setText("");
					}
				}
			   });
		   //("options["+i+"]");
		   align.insets = new Insets(resizerH(0), resizerW(0), y+150, resizerW(480-x));
		   form.add(options[i], align);
		   x+=250;
		   if(marker ==5)marker = 0;
		   marker++;
		}
		formWrap = new JScrollPane(form);
		formWrap.setVerticalScrollBarPolicy(
                 JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		formWrap.setPreferredSize(new Dimension(resizerW(250), 250));
		formWrap.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
                         BorderFactory.createTitledBorder(Work.toUpperCase()+" FOR WEEK "+Week+"  - "+No+" Questions"),
                         BorderFactory.createEmptyBorder(5,5,5,5)),
                         formWrap.getBorder()));
		this.add(formWrap, BorderLayout.CENTER);
		setVisible(true);
		
		save.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				int c = 1;int q = 1; int k = 1; int x = 0; int f = 0;
				QArray.remove(0);
				QArray.add(0, Instruction.getText());
				while (k<=((No*5)+No)){
					if(q==1){
					//("k= "+k+" and x="+x);
					QArray.remove(k);
					QArray.add(k, questions[x].getText());
					int ArrayLoc = (x+1)+((No*5)+(No*2));
					QArray.remove(ArrayLoc);
					QArray.add(ArrayLoc, answers[x].getText());
					//(QArray.toString()+", k="+k);
					f = c+1;
					x++; k++;
					}
					QArray.remove(k);
					try{
					QArray.add(k, options[c].getText());
					}catch(Exception z){
						c = c+1;
						QArray.add(k, options[c].getText());
					}
					if(q == 5) q = 0;
					q++; k++;  c++;
					//(QArray.toString());
				}
				
						String thisfile = Week+"+"+Work+"+"+"MCQ"+"+"+Clazz;
						 File file4 = new File("C:\\LocalSubjects\\"+Subject+"\\"+Work);
						 	if (!file4.exists()) {
						 		new File("C:\\LocalSubjects\\"+Subject+"\\"+Work).mkdirs();
						 		//(file4+ " now exists.");
						 		}
						File file5 = new File("C:\\LocalSubjects\\"+Subject+"\\"+Work+"\\"+thisfile);
						
						try {
							saveMCQ.savetoFile(QArray, file5);
						} catch (IOException e) {
							e.printStackTrace();
						}
							
						boolean network = Serve.Work(IDNo, Work, Username, Clazz, Subject, Examday, Examtime,
								Duration, Term, No, pointing,"MCQ", thisfile, Topic, Week, weighting); //Inform server that this person has questions
						Serve.DiagramStream(QArray, No*5, Subject);
						//("Back here");
						if(network == false){
							Object mcqparameters[] = {IDNo, Work, Username, Clazz, Subject, Examday, Examtime,
									Duration, Term, No, pointing,"MCQ", file5, Topic, Week, weighting};
							brief.mcqcaseLoader(mcqparameters, thisfile);
						}
						//("completed");
			
		}
			
			public void mouseEntered(MouseEvent arg0) {
				save.setText("Submit");
			}
			public void mouseExited(MouseEvent arg0) {
				save.setText("Save Questions");
			}
		});
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				int c = 1;int q = 1; int k = 1; int x = 0; int f = 0;int bj = 0;
				int b = QArray.size()-20;
				QArray.remove(0);
				QArray.add(0, Instruction.getText());
				while (k<=((No*5)+No)){
					if(q==1){
					QArray.remove(k);
					QArray.add(k, questions[x].getText());
					int ArrayLoc = (x+1)+((No*5)+(No*2));
					QArray.remove(ArrayLoc);
					QArray.add(ArrayLoc, answers[x].getText());
					//(QArray.toString()+", k="+k);
					f = c+1;
					//("k= "+k+" and x="+x);
					x++; k++;
					}
					QArray.remove(k);
					try{
					QArray.add(k, options[c].getText());
					}catch(Exception z){
						c = c+1;
						QArray.add(k, options[c].getText());
					}
					if(q == 5) q = 0;
					q++; k++;  c++;
					//(QArray.toString());
				}
				
					int resp = JOptionPane.showConfirmDialog(null, "Are you done?");
					if(resp == 0){
						String thisfile = Week+"+"+Work+"+"+"MCQ"+"+"+Clazz;
						 File file4 = new File("C:\\LocalSubjects\\"+Subject+"\\"+Work);
						 	if (!file4.exists()) {
						 		new File("C:\\LocalSubjects\\"+Subject+"\\"+Work).mkdirs();
						 		//(file4+ " now exists.");
						 		}
						File file5 = new File("C:\\LocalSubjects\\"+Subject+"\\"+Work+"\\"+thisfile);
						
						try {
							saveMCQ.savetoFile(QArray, file5);
						} catch (IOException e) {
							e.printStackTrace();
						}
							
						boolean network = Serve.Work(IDNo, Work, Username, Clazz, Subject, Examday, Examtime,
								Duration, Term, No, pointing,"MCQ", thisfile, Topic, Week, weighting); //Inform server that this person has questions
						if(network == false)JOptionPane.showMessageDialog(null, "You are offline. Your work will be saved.");
						Serve.DiagramStream(QArray, No*5, Subject);
						if(network == false){
							Object mcqparameters[] = {IDNo, Work, Username, Clazz, Subject, Examday, Examtime,
									Duration, Term, No, pointing,"MCQ", file5, Topic, Week, weighting};
							brief.mcqcaseLoader(mcqparameters, thisfile);
						}
						dispose();
					}else{
					}
					
			}
		});
	}
	
	
	public static void main(String[] args) {
		int IDNo = 5;
		String Work = "Test",  Username = "joseph",  Clazz = "SSS3", Subject = "English", Examday ="12/11/2014", 
				Examtime = "10:15 AM", Duration = "105 Minutes", Term = "first", Examiner = "", Topic = " - ";
		int NoQ = 30, pointing = 2, weighting = pointing*NoQ, BookMark = 0, Week = 4;
		ArrayList QList = null;
		new mcqEngine(IDNo, NoQ, pointing, weighting, BookMark, 
				Work, Username, Clazz, Subject, Examday, Examtime, Duration, Term, Examiner, Topic, QList, Week);
	}



	public void focusGained(FocusEvent t) {
	}

	public void focusLost(FocusEvent r) {
		int c = 1;int q = 1; int k = 1; int x = 0; int f = 0; 
			while (k<=((No*5)+No)){
				if(q==1){
				QArray.remove(k);
				QArray.add(k, questions[x].getText());
				int ArrayLoc = (x+1)+((No*5)+(No*2));
				QArray.remove(ArrayLoc);
				QArray.add(ArrayLoc, answers[x].getText());
				//(QArray.toString()+", k="+k);
				f = c+1;
				x++; k++;	
				}
				//("k= "+k+" and x="+x);
				QArray.remove(k);
				try{
				QArray.add(k, options[c].getText());
				}catch(Exception z){
					c = c+1;
					QArray.add(k, options[c].getText());
				}
				if(q == 5) q = 0;
				q++; k++;  c++;
				//(QArray.toString());
		}
			
	}
	
	private int resizerW(int inicialposition){
		int newposition = (int)(inicialposition*(finalW/inicialW));
		return newposition;
	}
	
	private int resizerH(int inicialposition){
		int newposition = (int)(inicialposition*(finalH/inicialH));
		return newposition;
	}
}
