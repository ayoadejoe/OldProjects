package cityOffAir;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Control;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player extends Thread implements Runnable{
	private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    private PlayerInterface positions;
    boolean isPlay = true, change = false, pause = false, playStatus = false;
	private int fileSize = 0, percent=0;
	private long currentPosition=0, neededPosition = 0, framelength=0;
	private Play play;
	private ArrayList configurations = null;
	public Player(ArrayList<Object> configurations2) {
		this.configurations = configurations2;
	}

	public void Player(String strFilename) {
		 soundFile = new File(strFilename);
		 try {
			 audioStream = AudioSystem.getAudioInputStream(soundFile);
			 audioFormat = audioStream.getFormat();

			 DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			 
			 Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo(); 
			 Mixer mixer3 = null; 
			 String outPlay = (String) configurations.get(4);
			 System.out.println("Old Playout Line:"+outPlay);
		     if(outPlay.equals("OutMixer") || outPlay == null){
		      for(int z = 0; mixerInfo.length>z; z++){
		    	  mixer3 = AudioSystem.getMixer(mixerInfo[z]);
		    	  configurations.remove(4); configurations.add(4, "Speakers (Conexant SmartAudio HD)");
		    	  if(mixerInfo[z].toString().contains("Speakers (Conexant SmartAudio HD)")) break;
		      }
		     }else{
		    	 for(int z = 0; mixerInfo.length>z; z++){
			    	  mixer3 = AudioSystem.getMixer(mixerInfo[z]);
			    	  if(mixerInfo[z].toString().contains(outPlay)) break;
			      }
		     }
			  
			 sourceLine = (SourceDataLine) mixer3.getLine(info);
	         sourceLine.open(audioFormat);
	         Control[] controls = sourceLine.getControls();
		      for(int q = 0; controls.length>q; q++){
		    	  
		    	  //println("Controls:"+controls[q]);
		      }
		      
		      if(sourceLine.isControlSupported(FloatControl.Type.MASTER_GAIN)){
	                FloatControl control = (FloatControl) sourceLine.getControl(FloatControl.Type.MASTER_GAIN);
	                //println("Maxi:"+control.getMaximum()+" present value:"+control.getValue());
	                control.setValue(control.getMaximum()-1);
	            }
		      
		      if(sourceLine.isControlSupported(FloatControl.Type.PAN)){
	                FloatControl control = (FloatControl) sourceLine.getControl(FloatControl.Type.PAN);
	                //println("Pan:"+control.getMaximum()+" present value:"+control.getValue());
	                control.setValue(0.0F);
	            }
		      Line.Info[] lineinfos = mixer3.getTargetLineInfo();
			    
	         sourceLine.start();
	         
	         play = new Play();
	         play.start();
	         playStatus = true;
		 } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
				playStatus = false;
			}
	}
	
	class Play extends Thread {
		 int nBytesRead = 0;
         byte[] abData = new byte[BUFFER_SIZE];
         
         public void run(){
        	 positions.sendout(audioStream.getFrameLength(), 0);
		 while (nBytesRead != -1) {
			 
             try {
                 nBytesRead = audioStream.read(abData, 0, abData.length);
                 positions.sendout(audioStream.getFrameLength(), sourceLine.getFramePosition());
             } catch (IOException e) {
                 e.printStackTrace();
             }
             if (nBytesRead >= 0) {
                 int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
             }
             
             if(!isPlay){
    	         break;
             }
             
         }
		 positions.sendout(0, -1);
		 sourceLine.stop();
         sourceLine.drain();
         sourceLine.close();
         playStatus = false;
         isPlay = true;
       }
         
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
	
	public boolean getPlayStatus(){
		return playStatus;
	}
	public void pausePlay(boolean state){
		if(state)play.suspend();
		if(!state)play.resume();
	}
	
	public void setPlayerInterface(PlayerInterface value){
		this.positions = value;
	}
	
	public static void main(String[] args) {
		new Player(null).Player("C://Users//Lani//Music//edjingRecords//edjingRecord (3).wav");

	}


}
