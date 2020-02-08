/*
 *  Copyright 2009,2010 Martin Roth (mhroth@gmail.com)
 * 
 *  This file is part of JAsioHost.
 *
 *  JAsioHost is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  JAsioHost is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with JAsioHost.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.synthbot.jasiohost;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * The <code>ExampleHost</code> demonstrates how to use an <code>AsioDriver</code> in order to read 
 * and write audio from a loaded ASIO driver. A small GUI is presented, allowing the user to select
 * any of the available ASIO drivers on the system. The <i>Start</i> button loads the driver and 
 * plays a 440Hz tone. The <i>Stop</i> button stops this process and unloads the driver. The
 * <i>Control Panel</i> button opens the driver's control panel for any additional configuration.
 */
public class TestPlayer extends JFrame implements AsioDriverListener {
  
  private static final long serialVersionUID = 1L;
  
  private AsioDriver asioDriver;
  private Set<AsioChannel> activeChannels;
  private int sampleIndex;
  private int bufferSize;
  private double sampleRate;
  private float[] output;
  private static final long RECORD_TIME = 30000;  // 1 minute
  private static File wavFile;
  private TargetDataLine targetDataLine = null;
  private static final int BUFFER_SIZE = 4096;
  private ByteArrayOutputStream recordBytes;
  private static boolean isRunning = false;
  private AudioFormat format = null;
  private SourceDataLine sourceDataLine = null;
  
  byte[] c = new byte[BUFFER_SIZE];
  int bytesRead = 0;
  float[] input;
  private AudioFormat getAudioFormat() {
      float sampleRate = 48000.0F;
      int sampleSizeInBits = 16;
      int channels = 1;
      boolean signed = true;
      boolean bigEndian = true;
      AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                           channels, signed, bigEndian);
      return format;
  }
  AsioChannel in0;	AsioChannel in1;
  AsioChannel out0;	AsioChannel out1;
  public TestPlayer() {
    super("JAsioHost Example");
    
    activeChannels = new HashSet<AsioChannel>();
    
    final JComboBox comboBox = new JComboBox(AsioDriver.getDriverNames().toArray());
    final JButton buttonStart = new JButton("Start");
    final JButton buttonStop = new JButton("Stop");
    final JButton buttonControlPanel = new JButton("Control Panel");
    
    try {
        format = getAudioFormat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo(); 
        Mixer mixer = null; Mixer mixerOut = null;
    	 
        for(int w = 0; mixerInfo.length>w; w++){
        	if(mixerInfo[w].toString().contains("Line In (M-Audio M-Track Quad Audio")){
        		mixer = AudioSystem.getMixer(mixerInfo[w]);
        	}
        	if(mixerInfo[w].toString().contains("Speakers (Conexant SmartAudio HD)")){
        		mixerOut = AudioSystem.getMixer(mixerInfo[w]);
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
        recordBytes = new ByteArrayOutputStream();
        recordBytes = new ByteArrayOutputStream();
        
        DataLine.Info dataLineInfo1 = new DataLine.Info(SourceDataLine.class, format);
        sourceDataLine = (SourceDataLine) mixerOut.getLine(dataLineInfo1);
        sourceDataLine.open(format);
        sourceDataLine.start();
        System.out.println("Start capturing...");

    } catch (LineUnavailableException ex) {
        ex.printStackTrace();
    }
    
    final AsioDriverListener host = this;
    buttonStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (asioDriver == null) {
          asioDriver = AsioDriver.getDriver(comboBox.getSelectedItem().toString());
          asioDriver.addAsioDriverListener(host);
          in0 = asioDriver.getChannelInput(0);	in1 = asioDriver.getChannelInput(1);
          out0 = asioDriver.getChannelOutput(0); out1 = asioDriver.getChannelOutput(1);
          
          activeChannels.add(out0);
          activeChannels.add(out1);
          
          activeChannels.add(in0);
          activeChannels.add(in1);
          //activeChannels.add(asioDriver.getChannelOutput(1));
        //  sampleIndex = 10;
          bufferSize = asioDriver.getBufferPreferredSize();
          System.out.println(bufferSize);
          sampleRate = asioDriver.getSampleRate();
          System.out.println(sampleRate);
          output = new float[bufferSize];
          input = new float[bufferSize];
          asioDriver.createBuffers(activeChannels);
          asioDriver.start();
          System.out.println("started asio driver");
        }
      }
    });
    
    
    
    buttonStop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (asioDriver != null) {
          asioDriver.shutdownAndUnloadDriver();
          activeChannels.clear();
          asioDriver = null;
        }
      }
    });

    buttonControlPanel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (asioDriver != null && asioDriver.getCurrentState().ordinal() >= AsioDriverState.INITIALIZED.ordinal()) {
          asioDriver.openControlPanel();    
          System.out.println("Panel opened");
        }
      }
    });
    
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    this.add(comboBox);
    panel.add(buttonStart);
    panel.add(buttonStop);
    panel.add(buttonControlPanel);
    this.add(panel);
    setLocationRelativeTo(getParent());
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent event) {
        if (asioDriver != null) {
          asioDriver.shutdownAndUnloadDriver();
        }
      }
    });
    
    this.setSize(240, 85);
    this.setResizable(false);
    this.setVisible(true);
  }
  int x = 0;
  public void bufferSwitch(long systemTime, long samplePosition, Set<AsioChannel> channels) {x++;
	  
	 // System.out.println("sampleposition:"+samplePosition/100000);
	  for (AsioChannel channelInfo : channels) {
		  for (int i = 0; i < channelInfo.getByteBuffer().limit() + channelInfo.getByteBuffer().remaining() ; i++){
		      int ch = channelInfo.getChannelIndex();
		      System.out.println(channelInfo.getChannelName());
		     
		  }
		
	      
	    }
	  		
	  		
  }
  
  public byte[] buffy(ByteBuffer buffr){
	  if (buffr.hasArray()) {
		    final byte[] array = buffr.array();
		    final int arrayOffset = buffr.arrayOffset();
		    return Arrays.copyOfRange(array, arrayOffset + buffr.position(),
		                              arrayOffset + buffr.limit());
		}else{
			return null;
		}
  }
  
  public void bufferSizeChanged(int bufferSize) {
    System.out.println("bufferSizeChanged() callback received.");
  }

  public void latenciesChanged(int inputLatency, int outputLatency) {
    System.out.println("latenciesChanged() callback received.");
  }

  public void resetRequest() {
    /*
     * This thread will attempt to shut down the ASIO driver. However, it will
     * block on the AsioDriver object at least until the current method has returned.
     */
    new Thread() {
      @Override
      public void run() {
        System.out.println("resetRequest() callback received. Returning driver to INITIALIZED state.");
        asioDriver.returnToState(AsioDriverState.INITIALIZED);
      }
    }.start();
  }

  public void resyncRequest() {
    System.out.println("resyncRequest() callback received.");
  }

  public void sampleRateDidChange(double sampleRate) {
    System.out.println("sampleRateDidChange() callback received.");
  }
  
  public static void main(String[] args) {
    @SuppressWarnings("unused")
    TestPlayer host = new TestPlayer();
  }

}

/*// get a list of available ASIO drivers
List<String> driverNameList = AsioDriver.getDriverNames();

// load the names ASIO driver
AsioDriver asioDriver = AsioDriver.getDriver(driverNameList.get(0));

// add an AsioDriverListener in order to receive callbacks from the driver
asioDriver.addAsioDriverListener(new AsioDriverListener() {
  @Override
  public void bufferSwitch(long systemTime, long samplePosition, Set<AsioChannel> channels) {
    // fill in audio buffers here
  }

  // implement remaining AudioDriverListener interface functions
}

// create a Set of AsioChannels, defining which input and output channels will be used
Set<AsioChannel> activeChannels = new HashSet<AsioChannel>();

// configure the ASIO driver to use the given channels
activeChannels.add(asioDriver.getChannelOutput(0));
activeChannels.add(asioDriver.getChannelOutput(1));

// create the audio buffers and prepare the driver to run
asioDriver.createBuffers();

// start the driver
asioDriver.start();
try {
  Thread.sleep(1000);
} catch (InterruptedException ie) {
  ie.printStackTrace(System.err);
}

// tear everything down
AsioDriver.shutdownAndUnloadDriver();*/
