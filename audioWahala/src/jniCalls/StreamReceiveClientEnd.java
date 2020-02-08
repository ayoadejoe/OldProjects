=][=]];v.j,/package jniCalls;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.sampled.SourceDataLine;

public class StreamReceiveClientEnd {
	private ServerSocket servsock = null;
	private Socket sock;
	public StreamReceiveClientEnd(SourceDataLine sourceDataLine) throws IOException {
		
		servsock = new ServerSocket(7373);
		//servsock.setSoTimeout(10000);
		while(true){
		sock = servsock.accept();
		
		DataInputStream dIn = new DataInputStream(sock.getInputStream());
		int length = dIn.readInt();                    // read length of incoming message
				if(length>0) {
				    byte[] buffer = new byte[length];
				    dIn.readFully(buffer, 0, buffer.length); // read the message
				    sourceDataLine.write(buffer, 0, buffer.length); 
				    System.out.println(buffer.length+" received");
				}
		}
	}

}
