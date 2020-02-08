package inheritance;

public class Education {
	
	int primary = 6;
	int secondary = 6;
	int uni = 4;
	String stage = "three";
	
	void adultEducation(){System.out.println("Adult Education - Education Superclass");}
	
	void genderSchools(){System.out.println("Gender Schools - Education Superclass");}
	
	Education(){
		System.out.println("Superclass Education");
		System.out.println("Yes, I was called");
		}
	
	Education(String name){
		System.out.println("Superclass Education name:"+name);
	}

}
