

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.Control;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JButton;
import javax.swing.JFrame;

public class AudioCapture02 extends JFrame{

  boolean stopCapture = false;
  ByteArrayOutputStream byteArrayOutputStream;
  AudioInputStream audioInputStream;
  SourceDataLine sourceDataLine;
  private static final String THE_INPUT_TYPE_I_WANT = "Interface_Mixer";
  private static final String THE_NAME_OF_THE_MIXER_I_WANT_TO_GET_THE_INPUT_FROM = "Line In (M-Audio M-Track Quad Audio - WDM 2.9.64)";
  private static AudioFormat audioFormat = null;
  private static DataLine.Info targetDataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
  private static final Port.Info targetPort = new Port.Info((Port.class), THE_INPUT_TYPE_I_WANT, true);
  private static TargetDataLine targetDataLine = null;
  public static void main(String args[]){
    new AudioCapture02();
  }//end main

  public AudioCapture02(){//constructor
    final JButton captureBtn = new JButton("Capture");
    final JButton stopBtn = new JButton("Stop");
    final JButton playBtn = new JButton("Playback");
    audioFormat = getAudioFormat();
    captureBtn.setEnabled(true);
    stopBtn.setEnabled(false);
    playBtn.setEnabled(false);

    //Register anonymous listeners
    captureBtn.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          captureBtn.setEnabled(false);
          stopBtn.setEnabled(true);
          playBtn.setEnabled(false);
          captureAudio();
        }
      } );
    getContentPane().add(captureBtn);

    stopBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          captureBtn.setEnabled(true);
          stopBtn.setEnabled(false);
          playBtn.setEnabled(true);
          stopCapture = true;
        }}
    );
    
    getContentPane().add(stopBtn);

    playBtn.addActionListener(
      new ActionListener(){
    	  public void actionPerformed(ActionEvent e){
          playAudio();
        }}
    );
    getContentPane().add(playBtn);

    getContentPane().setLayout(new FlowLayout());
    setTitle("Capture/Playback Demo");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(350,70);
    setVisible(true);
    setLocationRelativeTo(getParent());
  }

  //This method captures audio input from a // microphone and saves it in a  // ByteArrayOutputStream object.
  private void captureAudio(){
	  getSupportedFormats();
	  Mixer portMixer = null;
	    Mixer targetMixer = null;
	    try {
	        for (Mixer.Info mi : AudioSystem.getMixerInfo()) {
	            System.out.println("-" +mi.getName() + "-");
	            if (mi.getName().equals(THE_NAME_OF_THE_MIXER_I_WANT_TO_GET_THE_INPUT_FROM)) {
	                System.out.println("Trying to get portMixer for :" + mi.getName());
	                portMixer = getPortMixerInfoFor(mi);
	                if (portMixer != null) {
	                    System.out.println("Portin....>"+portMixer.getMixerInfo().toString());
	                    targetMixer = AudioSystem.getMixer(mi);
	                    break;
	                }
	            }
	        }
	        targetDataLine = (TargetDataLine) targetMixer.getLine(targetDataLineInfo);
	        System.out.println("Got TargetDataLine from :" + targetMixer.getMixerInfo().getName());
	        //Prepare the line for use.
	        targetDataLine.open(audioFormat);
	        targetDataLine.start();
	        
	        if (targetMixer != null) {
	            targetMixer.open();

	            targetDataLine = (TargetDataLine) targetMixer.getLine(targetDataLineInfo);
	            System.out.println("Got TargetDataLine control:" + targetMixer.getMixerInfo().getName());

	            portMixer.open();

	            Port port = (Port) portMixer.getLine(targetDataLine.getLineInfo());
	            port.open();

	            Control[] controls = port.getControls();
	            System.out.println((controls.length > 0 ? "Controls for the "+ THE_INPUT_TYPE_I_WANT + " port:" : "The port has no controls."));
	            for (Control c : controls) {
	                System.out.println(c.toString());
	            }
	        }
	        //Create a thread to capture the microphone data and start it running.  It will run  // until the Stop button is clicked.
	        Thread captureThread = new CaptureThread();
	        captureThread.start();
      
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }//end catch
  }//end captureAudio method

//return the portMixer that corresponds to TargetMixer 
private static Mixer getPortMixerInfoFor(Mixer.Info mixerInfo) {
    //Check this out for interest
    //http://www.java-forum.org/spiele-multimedia-programmierung/94699-java-sound-api-zuordnung-port-mixer-input-mixer.html
    try {
        // get the requested mixer
        Mixer targetMixer = AudioSystem.getMixer(mixerInfo);
        targetMixer.open();
        //Check if it supports the desired format
        if (targetMixer.isLineSupported(targetDataLineInfo)) {
           // System.out.println(mixerInfo.getName() + " supports recording @ " + audioFormat);
            //now go back and start again trying to match a mixer to a port
            //the only way I figured how is by matching name, because 
            //the port mixer name is the same as the actual mixer with "Port " in front of it
            // there MUST be a better way
            for (Mixer.Info portMixerInfo : AudioSystem.getMixerInfo()) {
                if ((mixerInfo.getName()).equals(portMixerInfo.getName())) {
                    System.out.println("Matched Port to Mixer:" + mixerInfo.getName());
                    Mixer portMixer = AudioSystem.getMixer(portMixerInfo);
                    System.out.println("PortMixer="+portMixer.getLineInfo());
                    portMixer.open();
                    //now check the mixer has the right input type eg LINE_IN
                    System.out.println("mixer " + portMixer);
                    boolean lineTypeSupported = portMixer.isLineSupported(portMixer.getLineInfo());
                    System.out.println(portMixerInfo.getName() +" does " + (lineTypeSupported? "" : "NOT") + " support " + targetPort.getName());
                    return portMixer;
                   // portMixer.close();
                }
            }
        }
        targetMixer.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
  //This method plays back the audio data that
  // has been saved in the ByteArrayOutputStream
  private void playAudio() {
    try{
      //Get everything set up for playback.
      //Get the previously-saved data into a byte
      // array object.
      byte audioData[] = byteArrayOutputStream. toByteArray();
      //Get an input stream on the byte array
      // containing the data
      InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
      AudioFormat audioFormat = getAudioFormat();
      audioInputStream = new AudioInputStream(byteArrayInputStream,audioFormat,audioData.length/audioFormat. getFrameSize());
      DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,audioFormat);
      sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
      sourceDataLine.open(audioFormat);
      sourceDataLine.start();

      //Create a thread to play back the data and
      // start it  running.  It will run until
      // all the data has been played back.
      Thread playThread = new PlayThread();
      playThread.start();
    } catch (Exception e) {
      System.out.println(e);
      System.exit(0);
    }//end catch
  }//end playAudio

  //This method creates and returns an
  // AudioFormat object for a given set of format
  // parameters.  If these parameters don't work
  // well for you, try some of the other
  // allowable parameter values, which are shown
  // in comments following the declartions.
  private AudioFormat getAudioFormat(){
    float sampleRate = 8000.0F;
    //8000,11025,16000,22050,44100
    //format: PCM_SIGNED unknown sample rate, 16 bit, mono, 2 bytes/frame, big-endian
    int sampleSizeInBits = 16;
    //8,16
    int channels = 1;
    //1,2
    boolean signed = true;
    //true,false
    boolean bigEndian = true;
    //true,false
    return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed, bigEndian);
  }

//Inner class to capture data from microphone
class CaptureThread extends Thread{
  //An arbitrary-size temporary holding buffer
  byte tempBuffer[] = new byte[10000];
  public void run(){
    byteArrayOutputStream = new ByteArrayOutputStream();
    stopCapture = false;
    try{
      while(!stopCapture){
        //Read data from the internal buffer of
        // the data line.
        int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
        if(cnt > 0){
          //Save data in output stream object.
          byteArrayOutputStream.write(tempBuffer, 0,cnt);
        }//end if
      }//end while
      byteArrayOutputStream.close();
      targetDataLine.stop();
    }catch (Exception e) {
      System.out.println(e);
      System.exit(0);
    }}}

class PlayThread extends Thread{
  byte tempBuffer[] = new byte[10000];

  public void run(){
    try{
      int cnt;
      //Keep looping until the input read method
      // returns -1 for empty stream.
      while((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1){
        if(cnt > 0){
          //Write data to the internal buffer of
          // the data line where it will be
          // delivered to the speaker.
          sourceDataLine.write(tempBuffer,0,cnt);
        }//end if
      }//end while
      //Block and wait for internal buffer of the
      // data line to empty.
      sourceDataLine.drain();
      sourceDataLine.close();
    }catch (Exception e) {
      System.out.println(e);
      System.exit(0);
    }//end catch
  }//end run
}//end inner class PlayThread
//=============================================//

public Vector<AudioFormat> getSupportedFormats() {

    Vector<AudioFormat> formats = new Vector<AudioFormat>();

    for (Mixer.Info mixerInfo : AudioSystem.getMixerInfo()) {
    	targetDataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
                    if (AudioSystem.isLineSupported(targetDataLineInfo)) {
                    	System.out.println(targetDataLineInfo.getFormats()+" is supported");
                        if (AudioSystem.getMixer(mixerInfo).isLineSupported(targetDataLineInfo)) {
                        	System.out.println(audioFormat);
                            formats.add(audioFormat);
                        }else{
                        	System.out.println(audioFormat+" line is not support");
                        	break;
                        }
                    }
    }
    return formats;
}
}//end outer class AudioCapture02.java