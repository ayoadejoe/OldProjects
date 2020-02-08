package baseGui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Vector;

import javax.swing.JOptionPane;

public class TchChatClient {
	private Socket client; // socket to communicate with server
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private OutputStream os = null;
	private int dataRead, current = 0, dataSize = 6022386;
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private DataInputStream in;
	private DataOutputStream out;
	private Socket sock = null;
	private ServerSocket servsock = null;
	private static String which = null;
	
	//ports
	private int SIPort = 7377;
	private int ChatPort1 = 7378; private int ChatPort2 = 7379; private int ChatPort3 = 7380;
	private int Lists = 7381;
	private String threadName = null; private Thread t = null;
	private static SocketAddress incoming = null; private static String hello = "Yesss! Momma";
	private static ByteBuffer buffer = ByteBuffer.wrap(hello.getBytes());
    private static ServerSocketChannel ssc = null;
	//IPs
	private String IP = "192.168.0.101";
	public TchChatClient() {
	}
	public Vector getContacts(int iD, String username, String password){
			which = username+"<"+password+"<"+"contacts";
			Vector received = null;
		try {
			 client = new Socket(IP, SIPort);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(which);
	         //("Sent from getList >> "+which);
			 //("Connection waiting to receive chat contact server ...");
			 
				 	ObjectInputStream is = new ObjectInputStream(client.getInputStream());
		            received = (Vector) is.readObject();
		            //("From teacher Server: "+received.toString());
		            client.close();
		            return received;
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Cannot cast the List Object to List");
			e.printStackTrace();
		}
		return null; 
	}
	public Vector Drops(int iD, String username, String password) {
		which = "tch%"+">"+username+">"+password+">"+"Drops";
		try {
			 client = new Socket(IP, SIPort);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(which);
				 	ObjectInputStream is = new ObjectInputStream(client.getInputStream());
		            Vector received = (Vector) is.readObject();
		            client.close();
		            return received;
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Cannot cast the List Object to List");
			e.printStackTrace();
		}
		return null; 
	}
	public String Messages(String self, String friend, int exit) {
		which = self+"<"+friend+"<"+"getMsg"+"<"+"tch%"; String received = null;
		try {
			 client = new Socket(IP, ChatPort1);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(which);
			 String recef = unblockReceiver(self, friend, exit);
			 out.close();
			 client.close();
		     return recef;
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		}
		return null;
	}
	private String unblockReceiver(String self, String friend, int exit) {
	     try {
	            ssc = ServerSocketChannel.open();
	            ssc.socket().bind(new InetSocketAddress(ChatPort2));
	            ssc.configureBlocking(false);
	            while (true) {
	                SocketChannel sc = ssc.accept();
	                if (sc == null) {
	                	//return null;
	                } else { // received an incoming connection
	                    //("Received an incoming connection from " +
	                   // sc.socket().getRemoteSocketAddress());
	                    incoming = sc.socket().getRemoteSocketAddress();
	                    String resp = printRequest(sc);
	                    buffer.rewind();
	                    sc.write(buffer);
	                    if(exit == 1){
	                    	buffer.clear();
	                    	sc.close();
	                    	ssc.close();
	                    }
	                    return resp;
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "here``";
	        } finally {
	            if (ssc != null) {
	                try {
	                    ssc.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	}
	}
	private String printRequest(SocketChannel sc) throws IOException {
		ByteArrayOutputStream out= new ByteArrayOutputStream();
        ReadableByteChannel rbc = Channels.newChannel(sc.socket().getInputStream());
        WritableByteChannel wbc = Channels.newChannel(out);
        ByteBuffer b = ByteBuffer.allocate(1024); // read 8 bytes
        while (rbc.read(b) != -1) {
            b.flip();
            while (b.hasRemaining()) {
                wbc.write(b);
                String con = out.toString().trim();
                return con;
            }
            b.clear();
        }
		return null;
	}
	
	public void sendMsg(String self, String friend, String statement) {
		which = self+"`"+friend+"`"+statement+"`"+"tch%"; 
		if(which.contains("~")){
			which = which.replace("~", "-");
		}
		
		which = "X~"+which;
		try {
			 client = new Socket(IP, ChatPort1);
			 OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF(which);
			 out.close();
			 client.close();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> The Server could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n >> There is an input/output error.");
			e.printStackTrace();
		}
		
	}
}
