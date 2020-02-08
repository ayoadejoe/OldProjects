
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;


public class getMixers {

	private TargetDataLine line;
	private DataLine.Info info;
	public getMixers() throws LineUnavailableException {
		 Mixer.Info[] mi = AudioSystem.getMixerInfo();
	        for (Mixer.Info info : mi) {
	            System.out.println("info: " + info);
	            Mixer m = AudioSystem.getMixer(info);
	            System.out.println("mixer " + m);
	            Line.Info[] sl = m.getSourceLineInfo();
	            for (Line.Info info2 : sl) {
	                System.out.println("    info: " + info2);
	                Line line = AudioSystem.getLine(info2);
	                if (line instanceof SourceDataLine) {
	                    SourceDataLine source = (SourceDataLine) line;

	                    DataLine.Info i = (DataLine.Info) source.getLineInfo();
	                    for (AudioFormat format : i.getFormats()) {
	                        System.out.println("    format: " + format);
	                    }
	                }
	            }
	        }
	}

	/**
	 * @param args
	 * @throws LineUnavailableException 
	 */
	public static void main(String[] args) throws LineUnavailableException {
		new getMixers();
	}

}
