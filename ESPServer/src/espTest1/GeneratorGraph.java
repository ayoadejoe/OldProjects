package espTest1;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GeneratorGraph extends JPanel{

	private Graphics2D g2;
	private Stroke stroke1 = new BasicStroke(2f);
	private Stroke stroke2 = new BasicStroke(1.5f);
	private Stroke stroke3 = new BasicStroke(1f);
	private Stroke stroke4 = new BasicStroke(0.5f);
	private Color gridColor = new Color(100, 100, 100, 100);
	private int x;
	private static Date today = new Date();
	private int ytickSpace =10, xtickSpace =10, xtickNo = 30, ytickNo = 5, xlineSpace = 0, ylineSpace = 60, 
			leftSpace=60, rightSpace=60, topSpace = 60,
			bottomSpace=50;
	private String gen, date;
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(stroke1);
        g2.setColor(Color.BLACK);
        g2.drawRect(10, 10, getWidth()-20, getHeight()-20);
        
        g2.fillRect(20, 20, getWidth()-40, getHeight()-40);
        
        g2.setStroke(stroke2);
        g2.setColor(Color.RED);
        g2.drawLine(leftSpace, topSpace, rightSpace, getHeight()-bottomSpace);				//vertical y
        g2.drawLine(leftSpace, getHeight()-bottomSpace, getWidth()-rightSpace, getHeight()-bottomSpace);	//horizontal x
        
        g2.setStroke(stroke4);
        g2.setColor(gridColor);
        for(int gauzeSpace=10; gauzeSpace<(getHeight()-10); gauzeSpace+=5){
        g2.drawLine(25, gauzeSpace, getWidth()-25, gauzeSpace);
        }//horizontal x gauze
        
        for(int gauzeSpace=10; gauzeSpace<(getWidth()-10); gauzeSpace+=5){
        	g2.drawLine(gauzeSpace, 25, gauzeSpace, getHeight()-25);	//horizontal x
        }//vertical y gauze
        
        //for x axis
        	xlineSpace = 60;		
        	ylineSpace = 60;
        	
        g2.setStroke(stroke3);
        g2.setColor(Color.GRAY);
        g2.setFont(new Font("cambria_math", Font.PLAIN, 10));
        g2.drawString(gen, 30, 40);
        
        g2.setFont(new Font("cambria", Font.BOLD, 13));
        g2.drawString("PLOT FOR "+date.toUpperCase(), ((int)getWidth()/2)-100, 70);
        
        g2.setStroke(stroke3);
        g2.setFont(new Font("cambria_math", Font.BOLD, 8));
        
            if(graphData.size()>0){								
            	int z=0;
            	int div = (int)(2*((double)xtickNo/100));
    			int mSpace =(int) Math.round(((double)xtickNo/div));
    			int mlineSpace = 60;
            	for(int u =0; u<xtickNo; u++){		//if the no of readings is much decrease ticks
            		int space = (int)((int)(getWidth()-rightSpace)-leftSpace)/xtickNo;		//spacing should be relative to the size of the graphics
                    xtickSpace= space;
                    xlineSpace=((xlineSpace+xtickSpace));
            		if(graphData.get(u) instanceof Integer){		//start(x1,y1 --- end x2,y2)
            			g2.setColor(Color.WHITE);
            			try{
            			g2.drawLine(xlineSpace, getHeight()-ydataVariables.get(z), (xlineSpace+(xtickSpace*2)), getHeight()-ydataVariables.get(z+1));
            			g2.drawString(graphData.get(u).toString(), xlineSpace, getHeight()-ydataVariables.get(z));
            			}catch(IndexOutOfBoundsException a){System.out.println("ERR: "+a.getMessage());}
            			z++;
            			g2.setColor(Color.RED);
            		}else{
            			g2.drawLine(xlineSpace, getHeight()-45, xlineSpace, getHeight()-55);
            			g2.drawString(graphData.get(u).toString(), xlineSpace, getHeight()-30);
            		}
            		g2.setFont(new Font("cambria_math", Font.BOLD, 8));
            	}
        	}else{
        		//JOptionPane.showMessageDialog(MyGraphDesign.this, "No data was found for this period");
        	}
        
        
            //for y axis
            g2.setFont(new Font("cambria_math", Font.PLAIN, 12));
            if(graphData.size()>0 ){
            	
            	int Min = getMinValue();
            	int Max = getMaxValue();
            	
            	for(int u =0; u<ytickNo; u++){
            		int space = (int)((int)(getHeight()-bottomSpace)-topSpace)/ytickNo;		//spacing should be relative to the size of the graphics
                    ytickSpace= space;
                    //ticks
                    if(u==0)ytickSpace=0;
                    	else ytickSpace = space;
                    g2.drawLine(leftSpace, ylineSpace=((ylineSpace+ytickSpace)), leftSpace+10, ylineSpace);
                    
                    
                    int valSpace = Max - Min;
                    valSpace = (int)Math.round((double)valSpace/5);
                    g2.drawString((Max-=valSpace)+"", leftSpace-25, ylineSpace+5);
                    
            	}
            }
	}
	
	
	 private int getMinValue() {
		 List<Integer> values = new ArrayList<>();
	    	for(int c=0; c<graphData.size(); c++){
	    	if(graphData.get(c) instanceof Integer){
					values.add((int)graphData.get(c));
				}
	    	}
	        int minValue = Collections.min(values);
	        return minValue;

	    }
	 
	
    private int getMaxValue() {
    	List<Integer> values = new ArrayList<>();
    	for(int c=0; c<graphData.size(); c++){
    	if(graphData.get(c) instanceof Integer){
				values.add((int)graphData.get(c));
			}
    	}
    	
        int maxValue = Collections.max(values);

        return maxValue;

    }



	private List<Object> graphData = new ArrayList<>();
	private List<Integer> ydataVariables = new ArrayList<>();
	public GeneratorGraph(List<Object> data2,  String date, String gen) {
		this.graphData = data2;
		System.out.println("GData:"+graphData);
		for(int u=0; u<graphData.size(); u++){
		if(graphData.get(u) instanceof Integer){
			ydataVariables.add((int)graphData.get(u));
    		}
		}
		//.out.println("data Size:"+graphData.size());
		System.out.println("y data Size:"+ydataVariables.size());
		this.xtickNo = graphData.size();
		this.gen = gen;
		this.date = date;
	}


	
	private static JButton refresh = new JButton("Refresh"); 
	private static JPanel controlPanel = new JPanel();
	private static JFrame frame = new JFrame();
	private static GeneratorGraph graph;
	private static FileSerializer info = new FileSerializer();
	private static List<Object> data = new ArrayList<>();
	private static List<Object> defaulT = new ArrayList<>();
	private static File daily100 = new File("100kVA Fuel Log");
	private static File daily75 = new File("75kVA Fuel Log");
	private static boolean compress;
	private static int mins = 30;
	public static void main(String[] args) {
		
		try {
			data = info.loadFromFile(daily100);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		for(int d=0; d<60; d++){								//half an hour set of readings
			defaulT.add(data.get(d));
		}
		
		compress = false;
		frame.setSize(900,600);
		frame.setLayout(new BorderLayout());
		graph = new GeneratorGraph(defaulT, "g75", today.toLocaleString());
		frame.add(graph, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.SOUTH);
		controlPanel.add(refresh);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		refresh.addActionListener(new ActionListener() {
			Random rand = new Random();
			public void actionPerformed(ActionEvent arg0) {
				compress = true;
				mins = 820;
				if(compress)frame.resize(1000, 700);
				
				graph.setVisible(false);
				frame.remove(graph);
				graph = new GeneratorGraph(data, "g75", today.toLocaleString());
				graph.setVisible(true);
				frame.add(graph);
			}
		});
	}

}
