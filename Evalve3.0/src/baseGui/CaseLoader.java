package baseGui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CaseLoader {
	FileSerializer save = new FileSerializer();
	public void EssaycaseLoader(Object[] essayparameters, String thisfile){
		 File file4 = new File("C:\\Teachers\\Briefcase\\Essays\\");
		 	if (!file4.exists()) {
		 		new File("C:\\Teachers\\Briefcase\\Essays\\").mkdirs();
		 		//(file4+ " now exists.");
		 		}
		File file = new File("C:\\Teachers\\Briefcase\\Essays\\"+thisfile+".evl");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ArrayList essay = new ArrayList();  int size = 0;
		while(size< essayparameters.length){
			essay.add(essayparameters[size]);
			size++;
		}
		//(essay);
		
		try {
			save.savetoFile(essay, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void mcqcaseLoader(Object[] mcqparameters, String thisfile){
		 File file4 = new File("C:\\Teachers\\Briefcase\\MCQ\\");
		 	if (!file4.exists()) {
		 		new File("C:\\Teachers\\Briefcase\\MCQ\\").mkdirs();
		 		//(file4+ " now exists.");
		 		}
		File file = new File("C:\\Teachers\\Briefcase\\MCQ\\"+thisfile+".evl");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ArrayList mcq = new ArrayList();  int size = 0;
		while(size< mcqparameters.length){
			mcq.add(mcqparameters[size]);
			size++;
		}
		//("In caseloader >"+mcq);
		
		try {
			save.savetoFile(mcq, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//("In caseloader > Done");
	}

}
