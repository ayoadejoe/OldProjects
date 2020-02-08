package exceptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MultipleExceptions {
	
	public static void main(String args[]) {
		FileInputStream fis = null;
				try {
					File file = new File("file.txt");
					if(!file.exists())file.createNewFile();
					fis = new FileInputStream("file.txt");
					System.out.println("File Opened");
					fis.read();
					System.out.println("Read File ");
				}
				catch (FileNotFoundException fnfe) {
				System.out.println("File not found");
				}
			catch (IOException ioe) {
			System.out.println("File Closing Exception");
			}
			finally {
			System.out.println("finally");
			}
		System.out.println("Next task..");
		}
	}

