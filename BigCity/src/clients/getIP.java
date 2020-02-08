package clients;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class getIP{
	public String IPget(){
		Scanner codeRetriever = null; String IP = null;
		try {
			codeRetriever = new Scanner(new File("data.ini"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (codeRetriever.hasNext()){
			IP = codeRetriever.next();
		}
		return IP;
	}
}
