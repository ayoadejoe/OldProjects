package FumsifolEsp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import espTest1.EspDerby;
import espTest1.ProcessData;

public class GraphBlock extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphBlock frame = new GraphBlock(new EspDerby());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private final JPopupMenu popupMenu = new JPopupMenu();
	private final JMenuItem btRev = new JMenuItem("<<");
	private final JMenuItem btFwd = new JMenuItem(">>");
	private final JMenuItem btCapt = new JMenuItem("[+]");
	private final JPopupMenu popupMenu_1 = new JPopupMenu();
	private final JMenuItem gtRev = new JMenuItem("<<");
	private final JMenuItem gtFwd = new JMenuItem(">>");
	private final JMenuItem gtCapt = new JMenuItem("[+]");
	private final JPopupMenu popupMenu_2 = new JPopupMenu();
	private final JMenuItem g75Rev = new JMenuItem("<<");
	private final JMenuItem g75Fwd = new JMenuItem(">>");
	private final JMenuItem g75Capt = new JMenuItem("[+]");
	private final JPopupMenu popupMenu_3 = new JPopupMenu();
	private final JMenuItem g100Fwd = new JMenuItem(">>");
	private final JMenuItem g100Rev = new JMenuItem("<<");
	private final JMenuItem g100Capt = new JMenuItem("[+]");
	private GraphPanel g75;
	private GraphPanel g100;
	private GraphPanel BT;
	private GraphPanel GT;
	
	private JPanel grayTank = new JPanel();
	private JPanel Gen100 = new JPanel();
	private JPanel BlueTank = new JPanel();
	private JPanel Gen75 = new JPanel();
	
	static private int padding = 25;
    static private int labelPadding = 25;
    ProcessData data;
    
    // some changes have been made on this java File
    private List<Object> p75 = new ArrayList<Object>();
    private List<Object> p100 = new ArrayList<Object>();
    private List<Object> blu = new ArrayList<Object>();
    private List<Object> gru = new ArrayList<Object>();
    
	public GraphBlock(EspDerby derby) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 10, 10));
		
		//data = derby.inicialRaw();
		
		p75 = data.getSmallLevel();
		p100 = data.getBigLevel();
		blu = data.getBlueTrend();
		gru = data.getGrayTrend();
		
		
		final List<Integer> s75 = getScores("g75");		List<Integer> s100 = getScores("g100");
		List<Integer> ble = getScores("BT");		List<Integer> gra = getScores("GT");
		
		
		Gen75.setBorder(new TitledBorder(null, "75kVA Consumption Trend", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		contentPane.add(Gen75);
		
		BlueTank.setBorder(new TitledBorder(null, "Blue Tank Consumption Trend", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		contentPane.add(BlueTank);
		
		Gen100.setBorder(new TitledBorder(null, "100kVA Consumption Trend", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(Gen100);
		
		grayTank.setBorder(new TitledBorder(null, "Gray Tank Consumption Trend", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		contentPane.add(grayTank);
		
		
		Gen75.setLayout(new BorderLayout());
		Gen75.add(g75, BorderLayout.CENTER);
		g75.setPreferredSize(new Dimension(500, 250));
		addPopup(g75, popupMenu_2);
		g75Rev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g75.setVisible(false);
				Gen75.remove(g75);
				addPopup(g75, popupMenu_2);
				Gen75.add(g75, BorderLayout.CENTER);
				g75.setVisible(true);
				Gen75.revalidate();
			}
		});
		
		popupMenu_2.add(g75Rev);
		
		popupMenu_2.add(g75Fwd);
		
		popupMenu_2.add(g75Capt);
		
		BlueTank.setLayout(new BorderLayout());
		BlueTank.add(BT, BorderLayout.CENTER);
		
		addPopup(BT, popupMenu);
		
		popupMenu.add(btRev);
		
		popupMenu.add(btFwd);
		
		popupMenu.add(btCapt);
		
		Gen100.setLayout(new BorderLayout());
		Gen100.add(g100, BorderLayout.CENTER);
		
		addPopup(g100, popupMenu_3);
		
		popupMenu_3.add(g100Rev);
		
		popupMenu_3.add(g100Fwd);
		
		popupMenu_3.add(g100Capt);
		
		grayTank.setLayout(new BorderLayout());
		grayTank.add(GT, BorderLayout.CENTER);
		
		addPopup(GT, popupMenu_1);
		
		popupMenu_1.add(gtRev);
		
		popupMenu_1.add(gtFwd);
		
		popupMenu_1.add(gtCapt);
		
	}
	
	int w =0;
	private List<Point> graphPoints(String panel, List<Object> copy) {
        
        List<Point> graphPoints = new ArrayList<>();
        int x1 = 0, y1 = 0;
        for(int d=0; d<39; d++){
    		if(copy.get(d) instanceof Integer){
    			y1 = (int) ((int)copy.get(d)-113);
    		}else{
    			w+=20;
    			x1 = (w) +padding+labelPadding;
    		}
    		if(x1==0)x1=padding+labelPadding;
    		graphPoints.add(new Point(x1, y1));
    	}
        
        System.out.println("GraphSize:"+graphPoints.size());
		return graphPoints;
	}

private List<String> timeTicks(String panel, List<Object> copy) {
        
        List<String> ticks = new ArrayList<>();
        String tik = "";
        for(int d=0; d<39; d++){
    		if(copy.get(d) instanceof String){
    			tik = copy.get(d).toString();
    		}
    		ticks.add(tik);
    	}
		return ticks;
	}


	private List<Integer> getScores(String panel) {
        switch(panel){
        case "g75":
        	List<Integer> g75 = new ArrayList<Integer>();
        	List<Object> copy = data.getSmallLevel();
        	System.out.println("75:"+copy);
        	for(int d=0; d<80; d++){
        		if(copy.get(d) instanceof Integer){
        			g75.add((int)copy.get(d));
        		}
        	}
        	return g75;
        case "g100":
        	List<Integer> g100 = new ArrayList<Integer>();
        	List<Object> copy2 = data.getBigLevel();
        	for(int d=0; d<80; d++){
        		if(copy2.get(d) instanceof Integer){
        			g100.add((int)copy2.get(d));
        		}
        	}
        	return g100;
        case "BT":
        	List<Integer> bleu = new ArrayList<Integer>();
        	List<Object> copy3 = data.getBigLevel();
        	for(int d=0; d<39; d++){
        		if(copy3.get(d) instanceof Integer){
        			bleu.add((int)copy3.get(d));
        		}
        	}
        	return bleu;
        case "GT":
        	List<Integer> gray = new ArrayList<Integer>();
        	List<Object> copy4 = data.getBigLevel();
        	for(int d=0; d<39; d++){
        		if(copy4.get(d) instanceof Integer){
        			gray.add((int)copy4.get(d));
        		}
        	}
        	return gray;
        	default:
        		return null;
        }
	}
	
	  private static int getMinScore(List<Integer> scores) {
	        int minScore = Integer.MAX_VALUE;
	        for (Integer score : scores) {
	            minScore = Math.min(minScore, score);
	        }
	        //System.out.println("Min:"+minScore);
	        return minScore;
	    }

	    private static int getMaxScore(List<Integer> scores) {

	        int maxScore = Integer.MIN_VALUE;

	        for (Integer score : scores) {
	            maxScore = Math.max(maxScore, score);
	        }
	       // System.out.println("Max:"+maxScore);
	        return maxScore;
	    }


	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
