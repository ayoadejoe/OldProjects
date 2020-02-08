package baseGui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FileSerializer {

	public void savetoFile(ArrayList qArray2, File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(qArray2);
		new LoggerCrypter(file);
		JOptionPane.showMessageDialog(null, " SAVED!");
	}
	
	public ArrayList loadFromFile(File file) throws IOException, ClassNotFoundException {
		new LoggerDecrypter(file);
		FileInputStream fis = new FileInputStream(file+"unlocked.evl");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList QArray = (ArrayList) ois.readObject();
		return QArray;
	}
}
