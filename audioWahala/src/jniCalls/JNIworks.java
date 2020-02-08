package jniCalls;

public class JNIworks {
	public native void sayHello();

	 static {
		 try{
	  System.loadLibrary("JNIworks");
		 }catch(Exception d){
			 d.printStackTrace();
		 }
	 }

	 public static void main(String[] args) {
	  JNIworks h = new JNIworks();
	  h.sayHello();
	 }

}
