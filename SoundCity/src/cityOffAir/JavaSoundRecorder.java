package cityOffAir;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.Mixer.Info;
 
/**
 * A sample program is to demonstrate how to record sound in Java
 * author: www.codejava.net
 */
public class JavaSoundRecorder {
    // record duration, in milliseconds
    private static final long RECORD_TIME = 30000;  // 1 minute
    private static File wavFile;
    private TargetDataLine targetDataLine = null;
    private static final int BUFFER_SIZE = 4096;
    private ByteArrayOutputStream recordBytes;
    private static boolean isRunning = false;
    private AudioFormat format = null;
    private SourceDataLine sourceDataLine = null;
    private LevelInterface levels;
    private StringInterface filenamer;
    private int c = 0, callNo=1;
    private Thread captureAndPlayThread;   //thread to capture and play audio
    private ArrayList configurations = null;
  
	public JavaSoundRecorder(ArrayList<Object> configurations2) {
		this.configurations = configurations2;
	}


	public ArrayList getConfigurations() {
		return configurations;
	}


	public void setConfigurations(ArrayList configurations) {
		this.configurations = configurations;
	}


	private AudioFormat getAudioFormat() {
        float sampleRate = 44100.0F;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
        return format;
    }
    

    void captureAudio() {
    	
        try {
            format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo(); 
            Mixer mixer = null; Mixer mixerOut = null;

            	String inRec = (String) configurations.get(3);
	            String outPlay = (String) configurations.get(4);
	            System.out.println("Input:"+inRec);	System.out.println("Output:"+outPlay);
	     if(inRec.equals("InMixer") || inRec == null){
            for(int w = 0; mixerInfo.length>w; w++){
            	if(mixerInfo[w].toString().contains("Line In (M-Audio M-Track Quad Audio")){
            		mixer = AudioSystem.getMixer(mixerInfo[w]);
            		configurations.remove(3); configurations.add(3, "Line In (M-Audio M-Track Quad Audio");
            	}
            	if(mixerInfo[w].toString().contains("Speakers (Conexant SmartAudio HD)")){
            		mixerOut = AudioSystem.getMixer(mixerInfo[w]);
            		configurations.remove(4); configurations.add(4, "Speakers (Conexant SmartAudio HD)");
            	}
            	}
            
	     }else{
	    	 
	    	 for(int w = 0; mixerInfo.length>w; w++){
	            		if(mixerInfo[w].toString().contains(inRec)){
	                		mixer = AudioSystem.getMixer(mixerInfo[w]);
	                	}
	                	if(mixerInfo[w].toString().contains(outPlay)){
	                		mixerOut = AudioSystem.getMixer(mixerInfo[w]);
	                	}
	            	}
	    	 
            }
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            
            targetDataLine = (TargetDataLine) mixer.getLine(info);
            targetDataLine.open(format);
            targetDataLine.start();   // start capturing
            
            DataLine.Info dataLineInfo1 = new DataLine.Info(SourceDataLine.class, format);
            sourceDataLine = (SourceDataLine) mixerOut.getLine(dataLineInfo1);
            sourceDataLine.open(format);
            sourceDataLine.start();
            System.out.println("Start capturing...");

            File CitySound = new File("C://CITY1051FM//CitySound");
            if(!CitySound.exists())CitySound.mkdir();
            
            captureAndPlayThread = new captureAndPlayThread(); 
            captureAndPlayThread.start();
            
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
    
    class captureAndPlayThread extends Thread{
    	public void run() {
    	 byte[] buffer = new byte[BUFFER_SIZE];
         int bytesRead = 0;
  
         recordBytes = new ByteArrayOutputStream();
    	 int t = 0;
         while (isRunning) {
         	int d = 100 - (targetDataLine.available()/100);
         	int time = new Date().getSeconds();
         	if(d>0){
         	levels.response(d);
         	}
         	int elapse;
         	if(time != c){
         		t++;
         		filenamer.response(null, t);
         		if(t>58) t=-1;
         	}
         	c= time;
             bytesRead = targetDataLine.read(buffer, 0, buffer.length);
             recordBytes.write(buffer, 0, bytesRead);
             sourceDataLine.write(buffer, 0, buffer.length); 
             
             if(!isRunning){
            	break;
             }
             
         }
    	 }
    }
 
    void finish() {
    	System.out.println("Available before stop: " + targetDataLine.available());
    	targetDataLine.stop();
    	System.out.println("Available before drain: " + targetDataLine.available());
    	targetDataLine.drain();
    	System.out.println("Available after drain: " + targetDataLine.available());
    	 System.out.println("Draining completed");
        targetDataLine.close();
        System.out.println("Available after closing: " + targetDataLine.available());
        System.out.println("Finished");
    }
    
    public void save(String folder) throws IOException {
    	File noFile = new File("C://CITY1051FM//CitySound//"+folder+"//");
    	int no = noFile.list().length+1;
    	wavFile = new File("C://CITY1051FM//CitySound//"+folder+"//"+(no)+"%"+new SimpleDateFormat("HH_mm_ss").format(new Date()));
        byte[] audioData = recordBytes.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(audioData);
        AudioInputStream audioInputStream = new AudioInputStream(bais, format,
                audioData.length / format.getFrameSize());
 
        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, wavFile);
        bais.close();
        audioInputStream.close();
        recordBytes.close();
        filenamer.response(wavFile.getName(), 0);
        
    }
 

	public void falseRunner() {
		isRunning = false;
		System.out.println(isRunning);
	}


	public void trueRunner() {
		isRunning = true;
	}
	
	public void setLevelInterface(LevelInterface value){
		this.levels = value;
	}
	
	public void setStringInterface(StringInterface string){
		this.filenamer = string;
	}
	
	public void pauseThread() throws InterruptedException{
		if(captureAndPlayThread != null&& captureAndPlayThread.isAlive()){
			captureAndPlayThread.suspend();
			System.out.println("Thread Paused");
		}
	}


	public void resumeThread() {
		if(captureAndPlayThread != null&& captureAndPlayThread.isAlive()){
			captureAndPlayThread.resume();
			System.out.println("Thread resumes");
		}
	}
}