package baseGui;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BinaryNameClient {
	private DataOutputStream fileN; 
	public BinaryNameClient(String ServerDirectory, String todo, String filename) {
		try {
			Socket sock = new Socket("192.168.0.101", 7373);
			//("Connected to "+sock.getLocalPort()+" to send directory "+ServerDirectory+" for "+filename);
			fileN = new DataOutputStream(sock.getOutputStream());
			fileN.writeUTF(ServerDirectory+"`"+todo+"`"+filename);
			fileN.flush();
			fileN.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
