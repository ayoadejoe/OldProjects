package baseGui;

public class BinaryThreader {

	public BinaryThreader(String localname, String ServerDirectory, String todo, String filename) {
		//(todo);
		if(todo.equals("receive")||todo.equals("$DiagramsTeacher$")){		//server to receive
			BinaryNameClient d = new BinaryNameClient(ServerDirectory, todo, filename);
			//("Sent File name");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//("About to send File");
			BinarySendClient rt = new BinarySendClient(localname, "BinarySend");		//client to send
			rt.start();
		}
		//BinaryReceiveClient e = new BinaryReceiveClient( localname, "Receive");
		//e.start();
	}

}
