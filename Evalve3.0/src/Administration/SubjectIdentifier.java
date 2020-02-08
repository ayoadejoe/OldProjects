package Administration;

public class SubjectIdentifier {
	private final String subjects[] = {"Mathematics", "English", "Biology",
			"Geography", "Economics", "Literature In English", "Agriculture","Chemistry", 
			"Physics", "Commerce", "Accounting", "Government", "History", "Technical Drawing",
			"Further Maths", "French", "Computer Science", "Social Studies",
			"Basic Technology", "Home Economics", "Basic Science","Business Studies", 
			"Fine Arts", "Civic Education", "Christian Religious Knowledge", "French", "Physical Education",
			"Sex Education", "Photography", "Yoruba", "Hausa", "Igbo", "Spanish", "Arabic", "Food and Nutrition",
			"Home Mangement", "Building Construction", "Electronics", "Visual Arts", "Music", "Book Keeping",
			"Clothing and Textile", "Typing", "Applied Electricity", "Auto-Mechanics", "Islamic Religious Knowledge",
			"Wood Work", "Metal Work"};
	
	private final String DatabaseSubjects[] = {"Mathematics", "English", "Biology",
			"Geography", "Economics", "Literature", "Agriculture","Chemistry", 
			"Physics", "Commerce", "Accounting", "Government", "History", "Technical_Drawing",
			"Further_Maths", "French", "Computer_Science", "Social_Studies",
			"Basic_Technology", "Home_Economics", "Basic_Science","Business_Studies", 
			"Fine_Arts", "Civic_Education", "Christian_Religious_Knowledge", "French", "Physical_Education",
			"Sex_Education", "Photography", "Yoruba", "Hausa", "Igbo", "Spanish", "Arabic", "Food_and_Nutrition",
			"Home_Mangement", "Building_Construction", "Electronics", "Visual_Arts", "Music", "Book_Keeping",
			"Clothing_and_Textile", "Typing", "Applied_Electricity", "Auto_Mechanics", "Islamic_Religious_Knowledge",
			"Wood_Work", "Metal_Work"};
	
	private String lvl[] = { "JSS1", "JSS2",  "JSS3", "SSS1", "SSS2",  "SSS3"};
	
	public String SubjectList(String newSubject) {
		for (String Subjt:subjects){
			if(newSubject.toLowerCase().equals("maths")){
				return "Mathematics";
			}
			if(newSubject.toLowerCase().contains("basic")){
				newSubject = newSubject.substring(6);
				if(newSubject.toLowerCase().contains("science")){
					return "Basic_Science";
				}
				if(newSubject.toLowerCase().contains("tech")){
					return "Basic_Technology";
				}
			}
			if(newSubject.toLowerCase().contains("integrated")){
				newSubject = "Basic_Science";
			}
			if(newSubject.toLowerCase().contains("introductory")){
				newSubject = "Basic_Technology";
			}
			if(newSubject.length()<=3){
				if(newSubject.toLowerCase().contains("crk")||newSubject.toLowerCase().contains("c.r.k")){
					newSubject = "Christian";
				}
				if(newSubject.toLowerCase().contains("irk")||newSubject.toLowerCase().contains("i.r.k")){
					newSubject = "Christian";
				}
			}
			
			if(newSubject.length()<=6){
				if(newSubject.toLowerCase().contains("crk")||newSubject.toLowerCase().contains("c.r.k")){
					newSubject = "Christian";
				}
				if(newSubject.toLowerCase().contains("irk")||newSubject.toLowerCase().contains("i.r.k")){
					newSubject = "Christian";
				}
			}
			
			String Sub = newSubject.substring(0, 4).trim();
			if(Sub.contains("phe")||Sub.contains("pe")){
				newSubject = "Physical";
			}
			if(newSubject.contains("physical")||Sub.contains("phy. education")){
				newSubject = "Physical";
			}
			if (Subjt.toLowerCase().contains(Sub)){
				return Subjt;
			}
			if(Subjt.toLowerCase().contains(newSubject.toLowerCase())){
				return Subjt;
			}
		}
		return null;
	}
	
	public String DBSubjectList(String DBSubject) {
		for (String Subjt:DatabaseSubjects){
			if(DBSubject.toLowerCase().equals("maths")){
				return "Mathematics";
			}
			if(DBSubject.toLowerCase().contains("basic")){
				DBSubject = DBSubject.substring(6);
				if(DBSubject.toLowerCase().contains("science")){
					return "Basic_Science";
				}
				if(DBSubject.toLowerCase().contains("tech")){
					return "Basic_Technology";
				}
			}
			if(DBSubject.toLowerCase().contains("integrated")){
				DBSubject = "Basic_Science";
			}
			if(DBSubject.toLowerCase().contains("introductory")){
				DBSubject = "Basic_Technology";
			}
			if(DBSubject.length()<=3){
				if(DBSubject.toLowerCase().contains("crk")||DBSubject.toLowerCase().contains("c.r.k")){
					DBSubject = "Christian";
				}
				if(DBSubject.toLowerCase().contains("irk")||DBSubject.toLowerCase().contains("i.r.k")){
					DBSubject = "Christian";
				}
			}
			
			if(DBSubject.length()<=6){
				if(DBSubject.toLowerCase().contains("crk")||DBSubject.toLowerCase().contains("c.r.k")){
					DBSubject = "Christian";
				}
				if(DBSubject.toLowerCase().contains("irk")||DBSubject.toLowerCase().contains("i.r.k")){
					DBSubject = "Christian";
				}
			}
			if(DBSubject.contains("phe")||DBSubject.contains("pe")){
				DBSubject = "Physical";
			}
			String Sub = "empty";
			try{
			Sub = DBSubject.substring(0, 4).trim();
			}catch(Exception f){}
			if(DBSubject.contains("physical")||Sub.contains("phy. education")){
				DBSubject = "Physical";
			}
			if (Subjt.toLowerCase().contains(Sub)){
				return Subjt;
			}
			if(Subjt.toLowerCase().contains(DBSubject.toLowerCase())){
				return Subjt;
			}
		}
		return null;
	}
	
	public String ClassList(String cls){
		cls = cls.toUpperCase();
		for(String Clas : lvl){
			if(cls.contains("J") && cls.contains("1") || cls.contains("J") && cls.contains("ONE")){
				return "JSS1";
			}
			if(cls.contains("J") && cls.contains("2")|| cls.contains("J") && cls.contains("TWO")){
				return "JSS2";
			}
			if(cls.contains("J") && cls.contains("3") || cls.contains("J") && cls.contains("THREE")){
				return "JSS3";
			}
			if(cls.contains("S") && cls.contains("1")|| cls.contains("S") && cls.contains("ONE")){
				return "SSS1";
			}
			if(cls.contains("S") && cls.contains("2")|| cls.contains("S") && cls.contains("TWO")){
				return "SSS2";
			}
			if(cls.contains("S") && cls.contains("3")|| cls.contains("S") && cls.contains("THREE")){
				return "SSS3";
			}
		}
		return null;
		
	}

}
