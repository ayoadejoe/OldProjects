package baseGui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Writer {
	private PrintWriter eachLine = null;
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	private List< String > pastChat = new LinkedList< String >();
	private List< Integer > pas = new LinkedList< Integer >();
	public void writeIt(File file, String info){
		//me, you
		try {
				if(!file.exists()){
				file.createNewFile();
				}
			fw = new FileWriter(file, true);
	        bw = new BufferedWriter(fw);
	        eachLine = new PrintWriter(bw);
	        eachLine.append(info+" \n");
	        
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, file+" File not found.");
				e.printStackTrace();
			}
		try {
			eachLine.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<Integer> sChat(File file1, String username, String self){
		String you = null, yah = null; Scanner schat = null;int f = 0, k = 0, w = 0, td = 0;
		String word = null, word2 = null; boolean det = false;
		word = username.toLowerCase()+"&"+self.toLowerCase();
		word2 = self.toLowerCase()+"&"+username.toLowerCase();
		try {
			schat = new Scanner(file1);
			while (schat.hasNextLine()){
				f++;
				yah = schat.nextLine().trim();
				if(yah.contains(word) || yah.contains(word2) ){
					det = true;
					pas.add(f);
				}
				if(yah.contains("`") && det == true){
					det = false;
					pas.add(f);
				}
			}
			//("Values = "+pas.toString());
			//you = getChat(k, file1);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, file1+" File not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pas;
	}
}
