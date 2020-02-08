
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.Timer;

import javazoom.jl.player.Player;

public class MP3 {

	 private String filename;
	    private Player player; 

	    // constructor that takes the name of an MP3 file
	    public MP3(String filename) {
	        this.filename = filename;
	    }

	    public void close() { if (player != null) player.close(); }
	    
	    // play the MP3 file to the sound card
	    public void play() {
	        try {
	            FileInputStream fis     = new FileInputStream(filename);
	            BufferedInputStream bis = new BufferedInputStream(fis);
	            player = new Player(bis);
	        }
	        catch (Exception e) {
	            System.out.println("Problem playing file " + filename);
	            System.out.println(e);
	        }

	        // run in new thread to play in background
	        new Thread() {
	            public void run() {
	                try { player.play(); }
	                catch (Exception e) { System.out.println(e); }
	            }
	        }.start();

	    }

	    private static JFileChooser fileChooser;
	    private static Timer t;
	    private static  MP3 mp3;
	    private static String[] files;
	public static void main(String[] args) {
		File directory = new File("C://Users//Lani//Music\\Billboard Hot 100 Singles Chart (17 Jan 2015) 320 KBPS~AryaN_L33T~[GloDLS]\\Hot 100\\");
		if(directory.exists()){
			files = directory.list(); int d = 0;
			while(files.length>d){
				System.out.println(files[d]);
				d++;
			}
		}else{
			System.out.println("Directory is not active");
		}
		fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);
		 File fileN = fileChooser.getSelectedFile(); // get selected file
		final String filename = fileN.getAbsolutePath();
		
		int result2 = fileChooser.showOpenDialog(null);
		 File fileN2 = fileChooser.getSelectedFile(); // get selected file
		final String filename2 = fileN2.getAbsolutePath();
		System.out.println(filename);
		

      t = new Timer(10000, new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(filename2);
			 mp3.close();
			 mp3 = new MP3(filename2);
		     mp3.play();
		   
		}
	});

        // when the computation is done, stop playing it
      t.start();

        // play from the beginning
        mp3 = new MP3(filename);
	    mp3.play();


	}

}
