package elucidate;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class DrawPanel extends JPanel{
	
int x,y=0;
    public void paintComponent(Graphics g)
      { 
       g.setColor(Color.WHITE);
       g.fillRect(0,0,this.getWidth(),this.getHeight());
     g.setColor(Color.GREEN);
    g.fillOval(x,y,50,50);
}
}