package com.synthbot.jasiohost;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


// Simple class to handle audio data from an ASIO compatible Audio Interface.

// Uses JASIOHost (c) M. H. Roth 2010

//Alastair Barber - 2012

public class AudioManager implements AsioDriverListener{
	private AsioDriver driver;
	  private Set<AsioChannel> activeChannels;
	  private int sampleIndex;
	  private int bufferSize;
	  private double sampleRate;
	  private float[] output;
	  private long systemTime;
	  private long samplePosition =0;
	  private int bufferIndex;
  HashSet<AsioChannel> inputChannels,outputChannels;
  
  public static void main(String[] args){
	  new AudioManager();
  }

  public AudioManager() throws AsioException{
    // Enumerate available ASIO Drivers
    ArrayList<String> driverList = (ArrayList<String>)AsioDriver.getDriverNames();
    for(int e = 0; e<driverList.size(); e++){
    	System.out.println("Driver "+e+" > "+driverList.get(e));
    }
    //Select the first one
    driver = AsioDriver.getDriver(driverList.get(0));
   //Create 2 'Sets' of channels, input and output
    activeChannels = new HashSet<AsioChannel>();

    int inputChannelCount = driver.getNumChannelsInput();

    int outputChannelCount = driver.getNumChannelsOutput();
    
    System.out.println("Input Channels:"+inputChannelCount+"  OutputChannels:"+outputChannelCount);

    for(int i = 0; i < inputChannelCount; i ++){

      activeChannels.add(driver.getChannelInput(i));
      System.out.println(driver.getChannelInput(i));
    }

    for(int i = 0; i < inputChannelCount; i ++){
      activeChannels.add(driver.getChannelOutput(i));
      System.out.println(driver.getChannelOutput(i));
    }

    //Activate these channels and assign this class as the listener
   
    driver.addAsioDriverListener(this);

    driver.createBuffers(activeChannels);
    driver.start();

  }


  public void bufferSwitch(long sampleTime, long samplePosition,Set<AsioChannel> switchActiveChannels) {
    float[] outputLeftArray = new float[bufferSize];
    float[] outputRightArray = new float[bufferSize];
    
    for(AsioChannel activeChannel : switchActiveChannels){
      if(activeChannel.isInput()){
         for(int i = 0; i < bufferSize; i++){
            outputLeftArray[i] += ((float) activeChannel.getByteBuffer().getInt()) / Integer.MAX_VALUE;
            outputRightArray[i] += ((float) activeChannel.getByteBuffer().getInt()) / Integer.MAX_VALUE;
         }
     }
   }

   // We shall do a separate loop of the channels as there is no guarantee that all the input

   // channels will be returned before the outputs.

   boolean sideSwitch = false;

   for(AsioChannel activeChannel : switchActiveChannels){
     if(!activeChannel.isInput()){

        if(sideSwitch)  activeChannel.write(outputLeftArray);

        else activeChannel.write(outputRightArray);

        sideSwitch = !sideSwitch;

     }
   }
  }


@Override
public void sampleRateDidChange(double sampleRate) {
}


@Override
public void resetRequest() {
	// TODO Auto-generated method stub
	
}


@Override
public void resyncRequest() {
	// TODO Auto-generated method stub
	
}


@Override
public void bufferSizeChanged(int bufferSize) {
	// TODO Auto-generated method stub
	
}


@Override
public void latenciesChanged(int inputLatency, int outputLatency) {
	// TODO Auto-generated method stub
	
}

}