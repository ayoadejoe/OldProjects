package baseGui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingUtilities;

public class runner {
	private static FileSerializer getQuestions = new FileSerializer();
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				//
				QuestionClient Serve = new QuestionClient();
				//Serve.StreamFiles();
				//new LessonPlan("Mathematics", "JSS 2", "geometry: a broad perspective and " +
						//"creation. All aspects to be studied. Including the colloqual terms and methods", 3, "joseph");
				File file = new File("Buffer.txt");
				
				//(file.getAbsolutePath());
				Date DT = new Date();
				//(DT.toGMTString());
				//(DT.toLocaleString());
				//(DT.toString());
				
			}
			
		});
	}

}
