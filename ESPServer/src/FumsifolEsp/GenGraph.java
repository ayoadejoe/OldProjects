package FumsifolEsp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GenGraph extends JPanel {

    static private int padding = 25;
    static private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(140, 140, 140, 140);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 5;
    private int numberYDivisions = 10;
    private static List<Integer> scores = new ArrayList<>();
    private static List<String> timeTicks = new ArrayList<>();
    private int xScale, yScale;
    private Graphics2D g2;
    private BufferedImage buffer;
    private static Timer t;
    public GenGraph(List<Integer> scores, String panel,  int w, int h) {
    	setPreferredSize(new Dimension(800, 600));
    	addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent arg0) {
				buffer = null;
			}
		});
        this.scores = scores;
        this.xScale = w;
        this.yScale = h;
        setColour(panel);
        System.out.println(this.scores.size());
    }

    private void setColour(String panel) {
		switch(panel){
		case "g75":
			lineColor = new Color(244, 220, 30, 80);
			break;
		case "g100":
			lineColor = new Color(4, 12, 230, 180);
			break;
		case "BT":
			lineColor = new Color(200, 102, 130, 210);
			break;
		case "GT":
			lineColor = new Color(44, 206, 250, 80);
			break;
			
			default:
				break;
		}
	}
    
protected void paintComponent(Graphics g) {
	 super.paintComponent(g);
     	g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		 xScale = ((int) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
	        yScale = ((int) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());


	        // draw white background
	        g2.setColor(Color.BLACK);
	        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
	        g2.setColor(Color.BLACK);

	        // create hatch marks and grid lines for y axis.

	        for (int i = 0; i < numberYDivisions + 1; i++) {
	            int x0 = padding + labelPadding;
	            int x1 = pointWidth + padding + labelPadding;
	            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
	            int y1 = y0;
	            if (scores.size() > 0) {
	                g2.setColor(gridColor);
	                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
	                g2.setColor(Color.BLACK);
	                String yLabel = Math.round(((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0) + "";
	                FontMetrics metrics = g2.getFontMetrics();
	                int labelWidth = metrics.stringWidth(yLabel);
	                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
	            }
	            g2.drawLine(x0, y0, x1, y1);
	        }

	        // and for x axis
	        int o=0;
	        for (int i = 0; i < scores.size(); i++) {
	            if (scores.size() > 1) {
	                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
	                int x1 = x0;
	                int y0 = getHeight() - padding - labelPadding;
	                int y1 = y0 - pointWidth;
	                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
	                    g2.setColor(gridColor);
	                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
	                    g2.setColor(Color.BLACK);
	                    String xLabel = i + "";
	                    FontMetrics metrics = g2.getFontMetrics();
	                    int labelWidth = metrics.stringWidth(xLabel);
	                    g2.setFont(new Font("cambria_math", Font.BOLD, 8));
	                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
	                }
	                g2.drawLine(x0, y0, x1, y1);
	            }
	        }

	        // create x and y axes 
	        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
	        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

	        Stroke oldStroke = g2.getStroke();

	        g2.setColor(lineColor);

	        g2.setStroke(GRAPH_STROKE);
	        
	}


		int k=0;

		public void update(List<Integer> readings, boolean go){
			for(int i=0; i< readings.size()-1; i++){
				
				int x1 = k;
		
			    int y1 = readings.get(i);
		
			    int x2 = k+=30;
		
			    int y2 = readings.get(i + 1);
			    
			    System.out.println(x1+","+ y1+","+ x2+","+y2);
			    g2.drawLine(x1, y1, x2, y2);
			}
			
			//if(go)k=0;
			//repaint();
		}


    private static int getMinScore() {

        int minScore = Integer.MAX_VALUE;

        for (Integer score : scores) {

            minScore = Math.min(minScore, score);

        }
        //System.out.println("Min:"+minScore);
        return minScore;

    }



    private static int getMaxScore() {

        int maxScore = Integer.MIN_VALUE;

        for (Integer score : scores) {

            maxScore = Math.max(maxScore, score);

        }
       // System.out.println("Max:"+maxScore);
        return maxScore;

    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();

    }



    public List<Integer> getScores() {
        return scores;
    }

    final static List<Integer> plots = new ArrayList<>();
    private static void createAndShowGui() {
        Random random = new Random();
        int maxDataPoints = 100;
        int maxScore = 10;
        for (int i = 0; i < maxDataPoints; i++) {
        	//scores.add((int) random.nextDouble() * maxScore);
            scores.add((int) i);
        }
        
        double xScale = ((int) 800 - (2 * padding) - labelPadding) / (scores.size() - 1);
        System.out.println("g:"+(getMaxScore() - getMinScore()));
        double yScale = ((int) 600 - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();

        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }
        
        final GenGraph mainPanel = new GenGraph(scores, "g75", 800, 600);

        mainPanel.setPreferredSize(new Dimension(800, 600));
        t = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});

        JFrame frame = new JFrame("DrawGraph");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(mainPanel);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        
        int a = 150;
        for (int i = 0; i < 60 ; i++) {
            int x1 = a+=3;
            plots.add(x1);
        }
    }

    

    public static void main(String[] args) {

      SwingUtilities.invokeLater(new Runnable() {

         public void run() {

            createAndShowGui();
            
         }

      });

   }

}