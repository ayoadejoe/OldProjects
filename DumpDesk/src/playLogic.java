

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Control;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class playLogic {
	 boolean stopCapture = false;
	  ByteArrayOutputStream byteArrayOutputStream;
	  AudioInputStream audioInputStream;
	  SourceDataLine sourceDataLine;
	  private static AudioFormat audioFormat = null;
	  private static DataLine.Info targetDataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
	  private static TargetDataLine targetDataLine = null;
	  private static File file;
	  boolean isPlay = true, change = false, pause = false;
	  private int fileSize = 0, percent=0;
	  private long currentPosition=0, neededPosition = 0, framelength=0;
	  private PlayerInterface positions;
	  private Thread playThread;
	  String folder = "JOSEPH";
	public playLogic() {
	
	}
	
	public void play(File filel){
		try{
			file = filel;
			audioInputStream = AudioSystem.getAudioInputStream(file);
			 audioFormat = audioInputStream.getFormat();
		      DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
		      
		      
		      Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo(); 
		      Mixer mixer3= null;
		      System.out.println("There are " + mixerInfo.length + " mixer info objects"); 
		      for(int z = 0; mixerInfo.length>z; z++){
		    	  mixer3 = AudioSystem.getMixer(mixerInfo[z]);
		    	  System.out.println(z+":"+mixerInfo[z]);
		    	  if(mixerInfo[z].toString().contains("Speakers (M-Audio M-Track Quad Audio")) break;
		      }
		      
		      sourceDataLine = (SourceDataLine) mixer3.getLine(info);
		      
		      Control[] controls = sourceDataLine.getControls();
		      for(int q = 0; controls.length>q; q++)System.out.println("Controls:"+controls[q]);
		      sourceDataLine.open(audioFormat);
		      sourceDataLine.start();
		      
		      //Create a thread to play back the data and start it  running.  It will run until all the data has been played back.
		      playThread = new PlayThread();
		      playThread.start();
		      
		    } catch (Exception e) {
		      System.out.println(e);
		      System.exit(0);
		    }//end catch
	}

	class PlayThread extends Thread{
		  byte tempBuffer[] = new byte[128000];

		  public void run(){
			  
		    try{
		    	 positions.sendout(audioInputStream.getFrameLength(), 0);
		      int cnt;
		      while((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1){
		    	 positions.sendout(audioInputStream.getFrameLength(), sourceDataLine.getFramePosition());
		    	 
		        if(cnt > 0){
		          sourceDataLine.write(tempBuffer,0,cnt);
		          if(!isPlay)break;
		        }
		        
		        if(!isPlay){
		        	break;
		        }
		        synchronized(this){
		        	while(change){
		        		wait();
		        	double newLocale = (double)percent/100;
		        	System.out.println("new Locale:"+newLocale);
		        	double thet = (double)newLocale*audioInputStream.getFrameLength();
		        	System.out.println("::"+(int)thet);
		        	}
		        }
		        
		      }
		      positions.sendout(0, -1);
		      sourceDataLine.stop();
		      sourceDataLine.drain();
		      sourceDataLine.close();
		      isPlay = true;
		    }catch (Exception e) {
		      System.out.println(e);
		      System.exit(0);
		    }
		  }
		}
	
	public static void main(String[] args) {
		new playLogic().play(new File("C://CITY1051FM//oldRec//New made at 14_33_17.wav"));
	}

	public void stopPlay() {
		isPlay = false;
	}
	
	public synchronized void positionChange(int percent, boolean change){
		this.percent = percent;
		this.change = change;
		if(!change){
			notify();
		}
	}
	
	public void pausePlay(boolean state){
		if(state)playThread.suspend();
		else if(!state)playThread.resume();
	}
	
	public void setPlayerInterface(PlayerInterface value){
		this.positions = value;
	}
}


/*System.out.println("Buffer size:"+sourceDataLine.getBufferSize());
		    	System.out.println("Source Available:"+sourceDataLine.available());
		    	System.out.println("framelength:"+audioInputStream.getFrameLength());*/