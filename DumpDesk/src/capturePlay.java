
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class capturePlay {

static boolean stopCapture = false;
static ByteArrayOutputStream byteArrayOutputStream;
static AudioFormat audioFormat;
static TargetDataLine targetDataLine;
static AudioInputStream audioInputStream;
static SourceDataLine sourceDataLine;
static byte buf[] = new byte[20000];
static FloatControl control;
static File file= null;
private float rms = 0;
private static captureAndPlayThread capture;   //thread to capture and play audio
static private AudioFormat getAudioFormat() {
    float sampleRate = 44100.0F;
    int sampleSizeInBits = 16;
    int channels = 1;
    boolean signed = true;
    boolean bigEndian = true;
    return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
}

public static void main(String[] args){
	captureAudio();
}

public static void captureAudio() {
	System.out.println(stopCapture);
    try {
    	
    	 Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();    //get available mixers
         System.out.println("Available mixers:");
         
        /* ~~~~~ UPDATE THIS PART OF CODE ~~~~~*/

        for (int cnt = 0; cnt < mixerInfo.length; cnt++) {
            System.out.println(cnt+">"+mixerInfo[cnt].getName());
        }
        audioFormat = getAudioFormat();     //get the audio format

        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);

        Mixer mixer = AudioSystem.getMixer(mixerInfo[5]);   //getting the mixer for capture device
        
        targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);
        targetDataLine = AudioSystem.getTargetDataLine(audioFormat);
    	targetDataLine.open(audioFormat);
        targetDataLine.start();

        DataLine.Info dataLineInfo1 = new DataLine.Info(SourceDataLine.class, audioFormat);
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo1);
        sourceDataLine.open(audioFormat);
        sourceDataLine.start();
        
        System.out.println("Activated");
        byteArrayOutputStream = new ByteArrayOutputStream();
        stopCapture = true;
        try {
            while (stopCapture) {
                 for(int b; (b = targetDataLine.read(buf, 0, buf.length)) > -1;) {

                     if (b > 0) {
                         byteArrayOutputStream.write(buf, 0, b);
                         sourceDataLine.write(buf, 0, 20000);   //playing audio available in tempBuffer
                         //now I save it
                         byte audioData[]=byteArrayOutputStream.toByteArray();
                         InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
                         audioInputStream =new AudioInputStream(byteArrayInputStream,audioFormat, 
                        		 audioData.length/audioFormat.getFrameSize());
						if (AudioSystem.isFileTypeSupported(AudioFileFormat.Type.WAVE,audioInputStream)) {
						AudioSystem.write(audioInputStream, AudioFileFormat.Type.AU, new File("nin"));
						}
                     }
                     if(!stopCapture){
                    	// filenamer.response(file);
                    	 targetDataLine.drain();
                    	 targetDataLine.close();
                    	 sourceDataLine.drain();
                         sourceDataLine.close();
                    	 byteArrayOutputStream.close();
                    	 System.out.println("done!");
                    	 break;
                     }
                 }
            }
            byteArrayOutputStream.close();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    } catch (LineUnavailableException e) {
    	//e.printStackTrace();
        System.out.println(e);
        System.exit(0);
    }
}

float[] samples = new float[buf.length / 2];

float lastPeak = 0f;

public class captureAndPlayThread extends Thread {

    @Override
    public void run() {
    	System.out.println("Activated");
        byteArrayOutputStream = new ByteArrayOutputStream();
        stopCapture = true;
        try {
            while (stopCapture) {
                 for(int b; (b = targetDataLine.read(buf, 0, buf.length)) > -1;) {

                     if (b > 0) {
                         byteArrayOutputStream.write(buf, 0, b);
                         sourceDataLine.write(buf, 0, 20000);   //playing audio available in tempBuffer
                         //now I save it
                         byte audioData[]=byteArrayOutputStream.toByteArray();
                         InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
                         audioInputStream =new AudioInputStream(byteArrayInputStream,audioFormat, 
                        		 audioData.length/audioFormat.getFrameSize());
						if (AudioSystem.isFileTypeSupported(AudioFileFormat.Type.WAVE,audioInputStream)) {
						AudioSystem.write(audioInputStream, AudioFileFormat.Type.AU, file);
						}
                     }
                     if(!stopCapture){
                    	// filenamer.response(file);
                    	 targetDataLine.drain();
                    	 targetDataLine.close();
                    	 sourceDataLine.drain();
                         sourceDataLine.close();
                    	 byteArrayOutputStream.close();
                    	 System.out.println("done!");
                    	 break;
                     }
                 }
            }
            byteArrayOutputStream.close();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
	public void startRecord(){
		stopCapture = true;
	}
	
	public void stopRecord(){
		stopCapture = false;
		System.out.println("capture="+stopCapture);
	}
}