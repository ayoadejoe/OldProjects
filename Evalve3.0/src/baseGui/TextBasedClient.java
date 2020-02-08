package baseGui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.sound.sampled.UnsupportedAudioFileException;

public class TextBasedClient {
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
		 Socket sock = new Socket("192.168.0.101", 7373);
		      File myFile = new File("infoC.evl");
		      byte[] mybytearray = new byte[(int) myFile.length()];
		      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
		      bis.read(mybytearray, 0, mybytearray.length);
		      OutputStream os = sock.getOutputStream();
		      os.write(mybytearray, 0, mybytearray.length);
		      os.flush();
		      sock.close();
	}

}
