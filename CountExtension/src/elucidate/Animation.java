package elucidate;

import javax.swing.JFrame;

public class Animation
 {
   int x=0;
   int y=0;
       public static void main(String[] args)
       {
        Animation gui=new Animation();
       gui.play();
     }
public void play()
   {
           JFrame frame=new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            DrawPanel draw=new DrawPanel();
            frame.getContentPane().add(draw);
             frame.setSize(500,500);
              frame.setVisible(true);
             for(int i=0;i<150;i++)
              {
                 x++;
                 y++;
                 draw.repaint();  //tells the panel to redraw itself so we can see the circle in new location
              try{
               Thread.sleep(25);
                  }catch(Exception e)
                  {}
         }
     }
}