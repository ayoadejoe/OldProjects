package cityOffAir;

public class HelloJNI {
	   static {
	      System.loadLibrary("portaudio_x64"); // Load native library at runtime
	                                   // hello.dll (Windows) or libhello.so (Unixes)
	   }
	 
	   // Declare a native method sayHello() that receives nothing and returns void
	   private native void sayHello();
	 
	   // Test Driver
	   public static void main(String[] args) {
	      new HelloJNI();  // invoke the native method
	   }
	}