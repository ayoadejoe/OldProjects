

import java.applet.Applet;
import java.util.Arrays;
import java.util.Set;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.UGen;
import net.beadsproject.beads.ugens.Gain;

import org.jaudiolibs.beads.AudioServerIO;


public class BeadsJNA {

    static AudioContext ac;
    static Set<UGen> inputs = null;
    public static void setup(){
        ac = new AudioContext(new AudioServerIO.Jack(),512,AudioContext.defaultAudioFormat(4,2));//control number of ins(4) and outs(2)

        UGen microphoneIn = ac.getAudioInput();
        Gain g = new Gain(ac, 1, 0.5f);
        g.addInput(microphoneIn);
        ac.out.addInput(g);

        System.out.println("no. of inputs:  " + ac.getAudioInput().getOuts()); 
        inputs = ac.getAudioInput().getConnectedInputs();
        System.out.println("input size:"+inputs.size());
       // while(inputs.)
        ac.start();
    }
   

    public static void main(String[] args) {
       setup();
    }

}