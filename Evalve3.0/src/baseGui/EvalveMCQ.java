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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class EvalveMCQ extends JFrame implements MouseListener {
	private StdSubmissionsClient subclient = new StdSubmissionsClient();
	private static FileSerializer getQuestions = new FileSerializer();
	private ScheduledExecutorService service = null;
	private ScheduledExecutorService service3 = null;
	private TimeChecker timer = new TimeChecker();
	private static Calendar cal; private static JLabel Vo;
	private String Filename = "",  timx = null, countdown = null;
	private int iDNo = 0, point = 0, baseNo = 0, Wk = 0, scores =0, weight = 0;
	private String evalType = null, class1 = null, Subjct = null, date2 = null, Tem = null, 
			time2 = null,   duration2 = null,   Term = null, Examinr = null, filenam = null;
	private Image pap = null;
	private static Vector <Object> AnswerArray = new Vector <Object>();
	private ArrayList Marked = new ArrayList();
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
	private int TextCol, TextRow, bookmark, g, No, clear, co, score;
	private JLabel no = new JLabel("");
	
	private static ArrayList <Object> QArray = new ArrayList <Object> ();
	private ImageIcon iconLoad1 = new ImageIcon("savesub.png"); 
	private JLabel submit = new JLabel(iconLoad1);
	
	private GridBagConstraints align = new GridBagConstraints();
	private FileSerializer saveMCQ = new FileSerializer();
	private JRadioButton[] options;
	private ButtonGroup[] group;
	private JTextArea[] questions;
	private JButton[] Diagrams;
	private JLabel[] images;
	private JPanel form = new JPanel();
	private JScrollPane formWrap;
	private JScrollPane Wrap;
	public EvalveMCQ(final int IDNo, final int NoQ, final int pointing, final int weighting, final int BookMark, 
			final String Work, final String Username, final String Clazz, final String Subject, final String Examday, 
			final String Examtime, final String Duration, final String Term, final String Examiner, final String Topic, 
			final ArrayList qArray, final int Week, Vector answerArray, int baseno, String filename) {
		super(Subject.toUpperCase()+" WEEK "+Week);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		getContentPane().setBackground(new Color(255, 255, 160));
		ImageIcon img = new ImageIcon("frame2.png");
		setIconImage(img.getImage());
		inicialW = 683; 
		inicialH = 460;	//size for screen with taskbar for height
		//for MCQEngine we will use
		SW = size.width;	finalW= SW;
		SH = size.height;	finalH= SH;
		//(SW+", "+SH);
		filenam = filename;	 class1 = Clazz;  iDNo = IDNo;  Subjct = Subject; evalType = Work;
		time2 = Examtime; date2 = Examday; duration2 = Duration; baseNo = baseno;  weight = weighting; point = pointing;
		Wk = Week; Examinr = Examiner; Tem = Term; 
		No = NoQ;
		Vo = new JLabel("BELIEVE");
		Vo.setFont(new Font("serif", Font.BOLD, resizerH(15)-3));
	    Vo.setForeground(Color.BLUE);
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
		
		submit.setText("Submit");			
		submit.setIcon(iconLoad1);
		submit.setHorizontalTextPosition( SwingConstants.CENTER );
		submit.setVerticalTextPosition( SwingConstants.BOTTOM );
		
		if(qArray != null){			// initiate ArrayList to lift out Array 
			//("qArray received: "+qArray);
			QArray = qArray;
			int AnsNo = 0;
			while(AnsNo <= No){
				AnswerArray.add(0);
				AnsNo++;
			}
			}else{
			int rollno = 0;
			while(rollno <=((No*5)+(No*2))){		//No is for diagram
				QArray.add(rollno);
				rollno++;
			}
			int AnsNo = 0;
			while(AnsNo <= No){
				AnswerArray.add(0);
				AnsNo++;
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
		form.add(submit, align);
		align.insets = new Insets(resizerH(0), resizerW(0), y+280, resizerW(500));
		form.add(Vo, align);
	    align.insets = new Insets(resizerH(0), resizerW(0), y+280, resizerW(0));
		form.add(HeadLow, align);
		form.setBackground(new Color(255, 255, 160));
		align.insets = new Insets(resizerH(0), resizerW(0), y+200, resizerW(0));
		Instruction = new JTextArea(TextRow-3, TextCol-6);
		Instruction.setLineWrap(true);
		Instruction.setWrapStyleWord(true);
		Instruction.setEditable(false);
		Instruction.setBackground(new Color(255, 255, 100));
		Instruction.setText(QArray.get(0).toString());
		IWrap = new JScrollPane(Instruction);
		form.add(IWrap, align);
		JLabel inst = new JLabel("Instructions:");
		align.insets = new Insets(resizerH(0), resizerW(0), y+200, resizerW(530));
		form.add(inst, align);
		
		
		questions = new JTextArea[No];
		options = new JRadioButton[(No*5)+No];
		group = new ButtonGroup[No+1];
		Diagrams = new JButton[No];
		images = new JLabel[No];
		for (int i = 0; i < ((No*5)+No); i++) { 
		   if(marker == 1){
			   questions[k] = new JTextArea(QArray.get(i+1).toString(), resizerH(4), resizerW(20));
			   group[k] = new ButtonGroup();
			   questions[k].setLineWrap(true);
			   questions[k].setWrapStyleWord(true);
			   questions[k].setEditable(false);
			   questions[k].setBackground(new Color(255, 255, 160));
			   Wrap = new JScrollPane(questions[k]);
			   Diagrams[k] = new JButton((k+1)+"");  g = k;
			   Diagrams[k].setPreferredSize(new Dimension(resizerW(50), resizerH(10)));
			   images[k] = new JLabel("Fig. "+(k+1)); 
			  // images[k].setPreferredSize(new Dimension(resizerW(50), resizerH(50)));
			   Image passp = null;
				try {
					passp = geticon(k+1, No, Subject);
					pap = passp;
					Image scaled = passp.getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
					images[k].setIcon(new ImageIcon(scaled));
					images[k].setVisible(true);
					Diagrams[k].setText("View "+(k+1));
				} catch (IOException e) {}
			   Diagrams[k].addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e) {
					//(e.getActionCommand() +" clicked"); 	
					int re = 0;
					try{
						re = Integer.parseInt(e.getActionCommand().substring(5).trim());
					}catch(Exception r){
						re = Integer.parseInt(e.getActionCommand().trim());
					}
						//images[re-1].setVisible(false);
						Diagrams[re-1].setText(re+"");
						try {
							pap = geticon(re, No, Subject);
						} catch (IOException e1) {
							pap = null;
						}
							re = ((re-1)*6)+1;
							new PictureLoader(null, (re)+"", QArray.get(re).toString(), pap );
					}
			   });
			 
			   align.insets = new Insets(resizerH(0), resizerW(0), y, resizerW(400));		// format: push(DOWN, right, UP, left)
				align.anchor = GridBagConstraints.CENTER;
				align.gridx = 0;
				align.gridy = 0;
				align.weightx = 0;
				align.weighty = 0;
				align.fill = GridBagConstraints.NONE;
				form.add( Wrap, align);
				align.insets = new Insets( resizerH(0), resizerW(600), y-70, resizerW(0));
				form.add(Diagrams[k], align);
				align.insets = new Insets( resizerH(0), resizerW(600), y+10, resizerW(0));
			    form.add(images[k], align);
			    align.insets = new Insets( resizerH(0), resizerW(0), y+10, resizerW(640));
			    form.add(new JLabel((k+1)+""), align);
			   k++; x=0; y-=280; i = i+1;
		   }
		   options[i] = new JRadioButton("("+optCol(marker)+k+")-"+QArray.get(i+1).toString());
		   options[i].setPreferredSize(new Dimension (700, 20));
		   options[i].setBackground(new Color(255, 255, 160));
		   group[k-1].add(options[i]);
		   options[i].addMouseListener(this);
		   align.insets = new Insets(resizerH(0), resizerW(180), (y+360)+x, resizerW(0));
		   form.add(options[i], align);
		   x-=50;
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
		
		submit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				subclient.mcqSubmissions(filenam, class1, iDNo, Subjct, evalType
						, time2, date2, duration2, baseNo, AnswerArray, "Signed", weight, point, Wk, Examinr, Tem);
				int resp = JOptionPane.showConfirmDialog(null, "Are you done?");
				if(resp == 0){
					dispose();
				}else{}
			}
			
			public void mouseEntered(MouseEvent arg0) {
				submit.setText("Done!");
			}
			public void mouseExited(MouseEvent arg0) {
				submit.setText("Submit");
			}
		});
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				subclient.mcqSubmissions(filenam, class1, iDNo, Subjct, evalType
						, time2, date2, duration2, baseNo, AnswerArray, "Signed", weight, point, Wk, Examinr, Tem);
				int resp = JOptionPane.showConfirmDialog(null, "Are you done?");
				if(resp == 0){
					service.shutdown();
		       		service3.shutdown();
					dispose();
					System.exit(0);
				}else{}
			}
		});
		
		service3 = Executors.newSingleThreadScheduledExecutor();
		 service3.scheduleWithFixedDelay(new Runnable()
	    {
	      public void run(){
	   	 TimeUpdaterClient  ti = new TimeUpdaterClient();
	   	 timx = ti.UpdaterClient(Subject, baseNo, class1);
	      }
	    }, 5, 65, TimeUnit.SECONDS);
		
		service = Executors.newSingleThreadScheduledExecutor();
		 service.scheduleWithFixedDelay(new Runnable()
	     {
	       public void run(){
	    		try {
	    			countdown = timer.Checker(timx, date2, duration2);
	    		 	//(timx+", set = "+countdown);
	    		} catch (ParseException e) {
	    			e.printStackTrace();
	    		}
	    		Object[] parts = countdown.split("'");
				String tim = parts[1].toString().trim();
				double HowLong = Double.parseDouble(tim);
	       	 DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss"); 
	       	 cal = Calendar.getInstance(); 
	       	 String date = dateFormat.format(cal.getTime());
	       	 int sec = cal.getTime().getSeconds();
	       	 int k = 60 - sec;
	       	 Vo.setText("You have "+(int)HowLong+"."+ k+" minutes");
	       	 revalidate();
	       	 
	        	if(HowLong <= 0){
		       		System.gc();	
						int finalScore = scores;
						subclient.mcqSubmissions(filenam, class1, iDNo, Subjct, evalType
								, time2, date2, duration2, baseNo, AnswerArray, "Signed", weight, point, Wk, Examinr, Tem);
						JOptionPane.showMessageDialog(null, "Time Up! You have come to the end of this Evaluation. Your work has been saved" );
						
		       		service.shutdown();
		       		service3.shutdown();
							System.exit(0);
		       	 }
	       }
	     }, 10, 1, TimeUnit.SECONDS);
	}
	
	
	private String optCol(int marker) {
		String Opt = null;
		switch (marker){
		case(1):
			Opt = "A";
		break;
		case(2):
			Opt = "B";
		break;
		case(3):
			Opt = "C";
		break;
		case(4):
			Opt = "D";
		break;
		case(5):
			Opt = "E";
		break;
		default:
			break;
		}
		return Opt;
	}

	private static void clear(File toPreview) {
		 if(toPreview.exists()){
			  File gry = new File ((toPreview.getAbsolutePath()+"unlocked.evl"));
			  System.gc();
			  gry.delete();
			  //("deleted "+gry.getAbsolutePath());
			}
	}

	private static File Q() {
		String thisfile = "4+Test+MCQ+SSS3";
		File toPreview = new File("C:\\LocalSubjects\\English\\Test\\"+thisfile+"+locked");
       if(!toPreview.exists()){
       	//("File for Evalve does not exist: "+toPreview);
       	thisfile = "4+Test+MCQ+SSS3.evl";
       }else{
       	//("File for Evalve exist: "+toPreview);
       }
		return toPreview;
	}

	private Image geticon(int k, int NoQ, String Subject) throws IOException {
		String imageName = QArray.get(k+(No*5)+(No)).toString().trim();
		File dest = new File("C:\\Teachers\\Diagrams\\"+Subject+"\\"+imageName+".evl");
		BufferedImage passp = null;
		passp = ImageIO.read(dest); 
		return passp;
	}

	public static void main(String[] args) {
		
		String thisfile = "4+Test+MCQ+SSS3";
		File parametr = new File("C:\\Teachers\\Briefcase\\MCQ\\"+thisfile+".evl+locked");
		ArrayList parameters = new ArrayList();
		try {
			parameters = getQuestions.loadFromFile(parametr);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		//(parameters.toString());
		
		
		
		 int IDNo = Integer.parseInt(parameters.get(0).toString().trim());
		 int pointing = Integer.parseInt(parameters.get(10).toString().trim());
		 int weighting = Integer.parseInt(parameters.get(15).toString().trim());
		 int Week = Integer.parseInt(parameters.get(14).toString().trim());
		 int NoQ = Integer.parseInt(parameters.get(9).toString().trim());
		 int BookMark = 0;
		 String Work = parameters.get(1).toString().trim();
		 String Username = parameters.get(2).toString().trim();
		 String Clazz = parameters.get(3).toString().trim();
		 String Subject = parameters.get(4).toString().trim();
		 String Examday = parameters.get(5).toString().trim();
		 String Examtime = parameters.get(6).toString().trim();
		 String Duration = parameters.get(7).toString().trim();
		 String Term = parameters.get(8).toString().trim();
		 String Topic = parameters.get(13).toString().trim();
		 String Examiner = "";
		 Duration = "10' Minutes";
		 Examtime = "00:10";
		 Examday = "17/11/2014";
		 Vector  AnswerArray = null;
		 String filename = "";
		 int baseno = 0;
		 File toPreview = Q();
			try {
				QArray = getQuestions.loadFromFile(toPreview);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			//("Launching MCQ");
			clear(toPreview);
			
		new EvalveMCQ(IDNo, NoQ, pointing, weighting, BookMark, Work, Username, Clazz, Subject, Examday, 
				Examtime, Duration, Term, Examiner, Topic, QArray, Week,  AnswerArray, baseno, filename);
	}
	
	private int resizerW(int inicialposition){
		int newposition = (int)(inicialposition*(finalW/inicialW));
		return newposition;
	}
	
	private int resizerH(int inicialposition){
		int newposition = (int)(inicialposition*(finalH/inicialH));
		return newposition;
	}


	public void mouseClicked(MouseEvent r) {
		Object who = r.getSource();
		String yes = ((JRadioButton) who).getText();
		String StudAns = yes.substring(1, 2);
		int StdAns = 0;
		try{
		StdAns = Integer.parseInt(yes.substring(2, 5));
		}catch(Exception w){
			try{
			StdAns = Integer.parseInt(yes.substring(2, 4));
			}catch(Exception g){
				try{
				StdAns = Integer.parseInt(yes.substring(2, 3));
				}catch(Exception h){
					//("Unresolvable Student Option.");
				}
			}
		}
		
		int ArrayLoc = (StdAns)+((No*5)+(No*2));
		String TchAns = QArray.get(ArrayLoc).toString();
		if (StudAns.equalsIgnoreCase(TchAns)){
			score++;
			AnswerArray.remove(StdAns);
			AnswerArray.add(StdAns, 1);
		}else{
			AnswerArray.remove(StdAns);
			AnswerArray.add(StdAns, 0);
		}
		//(AnswerArray);
	}

	public void mouseEntered(MouseEvent b) {
		Object aa = b.getComponent();
		((JRadioButton) aa).setToolTipText(((JRadioButton) aa).getText());
	}


	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent r) {
		Object who = r.getSource();
		String yes = ((JRadioButton) who).getText();
		String StudAns = yes.substring(1, 2);
		int StdAns = 0;
		try{
		StdAns = Integer.parseInt(yes.substring(2, 5));
		}catch(Exception w){
			try{
			StdAns = Integer.parseInt(yes.substring(2, 4));
			}catch(Exception g){
				try{
				StdAns = Integer.parseInt(yes.substring(2, 3));
				}catch(Exception h){
					//("Unresolvable Student Option.");
				}
			}
		}
		
		int ArrayLoc = (StdAns)+((No*5)+(No*2));
		String TchAns = QArray.get(ArrayLoc).toString();
		if (StudAns.equalsIgnoreCase(TchAns)){
			score++;
			AnswerArray.remove(StdAns);
			AnswerArray.add(StdAns, 1);
		}else{
			AnswerArray.remove(StdAns);
			AnswerArray.add(StdAns, 0);
		}
		//(AnswerArray);
	}
	public void mouseReleased(MouseEvent r) {
		Object who = r.getSource();
		String yes = ((JRadioButton) who).getText();
		String StudAns = yes.substring(1, 2);
		int StdAns = 0;
		try{
		StdAns = Integer.parseInt(yes.substring(2, 5));
		}catch(Exception w){
			try{
			StdAns = Integer.parseInt(yes.substring(2, 4));
			}catch(Exception g){
				try{
				StdAns = Integer.parseInt(yes.substring(2, 3));
				}catch(Exception h){
					//("Unresolvable Student Option.");
				}
			}
		}
		
		int ArrayLoc = (StdAns)+((No*5)+(No*2));
		String TchAns = QArray.get(ArrayLoc).toString();
		if (StudAns.equalsIgnoreCase(TchAns)){
			score++;
			AnswerArray.remove(StdAns);
			AnswerArray.add(StdAns, 1);
		}else{
			AnswerArray.remove(StdAns);
			AnswerArray.add(StdAns, 0);
		}
		
		subclient.mcqSubmissions(filenam, class1, iDNo, Subjct, evalType
				, time2, date2, duration2, baseNo, AnswerArray, "UnsignedFocus", weight, point, Wk, Examinr, Tem);
	}
}
