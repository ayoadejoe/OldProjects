package FumsifolEsp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

public class GraphLines extends JPanel{
	private Graphics2D g2;
	private  int r = 1, s = 0, y = 0, u = 0;
	private int width, height, i = 0, previousY = 203;
	private BufferedImage buffer;
	private boolean intersection = true;
	private static boolean drawConnectingLine = true ;
	private static List<Integer> scores = new Vector();
	int yScale, xScale;
	static private int padding = 25;
	static private int labelPadding = 25;
	private Color lineColor = new Color(44, 102, 230, 180);
	private Color pointColor = new Color(100, 100, 100, 180);
	private Color gridColor = new Color(140, 140, 140, 140);
	private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	private int pointWidth = 5;
	private int numberYDivisions = 10;
	public GraphLines(List<Integer> scores, String panel,  int w, int h){
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
		
		if(buffer == null){
			buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		}
		g2 = (Graphics2D) buffer.getGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.drawImage(buffer, 0, 0, null);
	}
	
	int xz = 0;
	public void update(List<Integer> plots, int graphWidth, boolean go){
		xz++;
		int lineXaxis = 0;
		if(graphWidth<=600)  lineXaxis = 600;
		if(g2 != null){
			
		g2.setStroke(new BasicStroke(1f));
		i++;
		if(i%7 == 0){
		try{
		int yes = (int)plots.get(s);
		y = (int) ((540 - (yes)));
		int noOfWeeks = plots.size()/2;
		if(s==(noOfWeeks-1)){
			g2.setColor(Color.WHITE);
			g2.drawLine(0, 441, lineXaxis, 441);			//x axis
			g2.drawLine(20, 30, 20, 700);
			g2.setFont(new Font("georgia", Font.HANGING_BASELINE, 18));
			g2.drawString("Generator Unit Consumption", 130, 20);
			g2.setFont(new Font("cambria_math", Font.HANGING_BASELINE, 12));
			g2.drawString("Unit Consumption", 15, 30);
			g2.setFont(new Font("cambria_math", Font.PLAIN, 11));
			
			int diff = (int)plots.get(s) - (int)plots.get(s-2);
			if(diff>0){
				g2.setColor(new Color(200, 150, 100));
				g2.drawString("Consumption increases by "+diff+" Litres", 360, 255);
			}else{
				g2.setColor(new Color(100, 220, 150));
				g2.drawString("Consumption dropped by "+diff+" Litres", 360, 255);
			}
		}
		int e = (i-7)*4, a = i*4; 		//the multiplier zooms graph
		if(e>0){			//shifts graph forward or backward
			e = e-8;
			a = a-8;
		}
		
		int liny = s*40;
		if(liny == 0) liny= 20;
		if (drawConnectingLine && i> 0 ){	
			g2.setColor(Color.RED);
			g2.drawLine(e+30, previousY, a+30, y);
			System.out.println(e+ ","+ previousY+","+a+","+ y);
			g2.fillOval(e+30, y+4, 5, 5);
			g2.drawString(""+plots.get(s), (i*4)-10, y);
			g2.setFont(new Font("cambria_math", Font.HANGING_BASELINE, 8));
			g2.drawString(""+plots.get(s+1), (i*4)-10, y-20);
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("cambria_math", Font.PLAIN, 8));
			
			if(go){
				System.out.println("NOW func");
				System.out.println(plots);
				for (int i = 0; i < numberYDivisions + 1; i++) {
		            int x0 = padding + labelPadding;
		            int x1 = pointWidth + padding + labelPadding;
		            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
		            int y1 = y0;
		            if (plots.size() > 0) {
		                g2.setColor(gridColor);
		                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
		                g2.setColor(Color.WHITE);
		                String yLabel = Math.round(((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0) + "";
		                FontMetrics metrics = g2.getFontMetrics();
		                int labelWidth = metrics.stringWidth(yLabel);
		                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
		            }
		            g2.drawLine(x0, y0, x1, y1);
				}
			 // and for x axis
	        int o=0;
	        for (int i = 0; i < plots.size(); i++) {
	            if (plots.size() > 1) {
	                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (plots.size() - 1) + padding + labelPadding;
	                int x1 = x0;
	                int y0 = getHeight() - padding - labelPadding;
	                int y1 = y0 - pointWidth;
	                if ((i % ((int) ((plots.size() / 20.0)) + 1)) == 0) {
	                    g2.setColor(gridColor);
	                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
	                    g2.setColor(Color.WHITE);
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
		} 
			
		}
		previousY = y;
		repaint();
		}catch(Exception f){}s= s+2;}
		}
	}
	
	double degToRad ( int deg){
		return (( 2 * Math .PI)/ 360.0 ) * deg;
	}
	int scale ( int i, int width){
		return ( int) ((i/( double )width)* 720.0 );
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

}

/*//line.x1 += r;
//line.y1 += s;

line.x2 += xDirection*10;
line.y2 += yDirection*10;

line.x1 = xDirection;
line.y1 = yDirection;

if(line.y2>getHeight()){
	line.y2 = 0;
	line.y1 = 100;
}*/