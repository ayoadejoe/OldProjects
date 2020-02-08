package baseGui;

public class TchThreader {

	public TchThreader(String self, String friend, String instance) {
		if(instance.equals("chat")){
			TchChatBox b = new TchChatBox(null, self, friend, instance);
			b.start();
		}
	}

}
