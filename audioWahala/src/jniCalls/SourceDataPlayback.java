package jniCalls;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class SourceDataPlayback {
	    private static File wavFile;
	    private static final int BUFFER_SIZE = 4096;
	    private ByteArrayOutputStream recordBytes;
	    private AudioFormat format = null;
	    private SourceDataLine sourceDataLine = null;
	    private ArrayList configurations = null;
	    static private StreamReceiveClientEnd receive;
	    
	    
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
	    
	public SourceDataPlayback() {
		
		
		 try {
	            format = getAudioFormat();
	            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
	            Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo(); 
	            Mixer mixer = null; Mixer mixerOut = null;

	            	
		    	 
		    	 for(int w = 0; mixerInfo.length>w; w++){
		                	if(mixerInfo[w].toString().contains("Speaker")){
		                		mixerOut = AudioSystem.getMixer(mixerInfo[w]);
		                		System.out.println("mixer found:"+mixerOut);
		                		break;
		                	}
		            	}
		    	 
	            
	            // checks if system supports the data line
	            if (!AudioSystem.isLineSupported(info)) {
	                System.out.println("Line not supported");
	                System.exit(0);
	            }
	           
	            
	            DataLine.Info dataLineInfo1 = new DataLine.Info(SourceDataLine.class, format);
	            sourceDataLine = (SourceDataLine) mixerOut.getLine(dataLineInfo1);
	            sourceDataLine.open(format);
	            sourceDataLine.start();
	            System.out.println("Waiting to receive...");
	            
	            try {
					receive = new StreamReceiveClientEnd(sourceDataLine);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        } catch (LineUnavailableException ex) {
	            ex.printStackTrace();
	        }
	}

	public static void main(String[] args) {
		SourceDataPlayback source = new SourceDataPlayback();
	}

}
