package espTest1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JComponent;

public class DataGraph extends JComponent{
	private Graphics2D g2;
	private  int r = 1, s = 0, y = 0, u = 0;
	private int width, height, i = 0, previousY = 203;
	private BufferedImage buffer;
	private boolean intersection = true;
	private static boolean drawConnectingLine = true ;
	private Vector scores = new Vector();
	public DataGraph(){
		Cursor cursor = getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(1, 1), "");
		setCursor(cursor);
		addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent arg0) {
				buffer = null;
			}
		});
		
	}
	
	protected void paintComponent(Graphics g) {
		
		if(buffer == null){
			buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		}
		g2 = (Graphics2D) buffer.getGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		width = 550;
		height = 600;
		g.drawImage(buffer, 0, 0, null);
	}
	int yes2 =0;
	public void update(int plot, String time, Vector dates, boolean mark){ int yes =0;
		//System.out.println("Plot:"+plot+" Time:"+time);
		if(g2 != null){
		g2.setStroke(new BasicStroke(1f));
		i = i+7;
		try{
		yes = plot;
		
		//System.out.print(yes+", ");
		y = (int) ((540 - (yes)));
		if(mark){
			
			g2.setColor(Color.WHITE);
			g2.drawLine(0, 441, 600, 441);			//x axis
			g2.drawLine(20, 30, 20, 700);
			g2.setFont(new Font("georgia", Font.HANGING_BASELINE, 18));
			g2.drawString("Generator Unit Consumption", 130, 20);
			g2.setFont(new Font("cambria_math", Font.HANGING_BASELINE, 12));
			g2.drawString("Unit Consumption", 15, 30);
			g2.setFont(new Font("cambria_math", Font.PLAIN, 11));
			
			int diff = yes - yes2;
			if(diff>0){
				g2.setColor(new Color(200, 150, 100));
				g2.drawString("Consumption increases by "+diff+" Litres", 360, 255);
			}else{
				g2.setColor(new Color(100, 220, 150));
				g2.drawString("Consumption dropped by "+diff+" Litres", 360, 255);
			}
			
		}
		int e = (i)*4, a = i*4; 		//the multiplier zooms graph
		if(e>0){			//shifts graph forward or backward
			e = e-8;
			a = a-8;
		}
		
		int liny = s*40;
		if(liny == 0) liny= 20;
		if (drawConnectingLine && i> 0 ){	
			g2.setColor(Color.RED);
			g2.drawLine(e, previousY, a, y);
			g2.fillOval(e+27, y-3, 3, 6);
			g2.drawString(""+yes, (i*4)-10, y);
			g2.setFont(new Font("cambria_math", Font.HANGING_BASELINE, 8));
			g2.drawString(""+time, (i*4)-10, y-20);
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("cambria_math", Font.PLAIN, 8));
			
			//g2.drawString("|", (i*4)-10, 250);
			//g2.drawString("_", 15, liny);
			
		} else {
			//g2.drawLine(i, y, i, y);
		}
		previousY = y;
		repaint();
		}catch(Exception f){}
		s= s+2; yes2=yes;
		}
	}
	
	double degToRad ( int deg){
		return (( 2 * Math .PI)/ 360.0 ) * deg;
	}
	int scale ( int i, int width){
		return ( int) ((i/( double )width)* 720.0 );
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