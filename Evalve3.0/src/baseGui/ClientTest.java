package baseGui;
 // Fig. 24.8: ClientTest.java
// Test the Client class.
import javax.swing.JFrame;

	
public class ClientTest {
	public static void main( String args[] )   {
		Client application; // declare client application
		// if no command line args
		if ( args.length == 0 )
			application = new Client( "192.168.0.101" ); // connect to 192.168.0.101
			//application = new Client( "192.168.0.101" ); 
		else
			application = new Client( args[ 0 ] ); // use args to connect   
		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		application.runClient(); // run client application
	} // end main
} // end class ClientTest

