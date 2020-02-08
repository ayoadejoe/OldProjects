package inheritance;

public class TestInheritance {
	
	public static void main(String[] args){
		Education educate = new Education();
		Education edu = new SecondarySchools();
		
		System.out.println(educate.stage);
		System.out.println(edu.sim);
		
		/*
		//use members of derived class as superclass is impossible
		//educate.juniorClasses();
		
		//all subclasses of Education are ALL types of Education except the interface
		
		Education edu = new SecondarySchools();
		edu.genderSchools();  // can only access members of education despite being a reference of secondary school
		edu.adultEducation();
		((SecondarySchools)edu).juniorClasses(3);
		
		Education education = new University();
		
		//Secondary school
		SecondarySchools second = new SecondarySchools();
		//A super() is always called automatically by the JVM when a child class object is 
		//instantiated - this is done to make the members of the Parent available to the child
		
		//interface call
		second.informalEducation();
		//Parent call
		System.out.println(second.primary);
		System.out.println(second.stage);
		second.adultEducation();
		
		//ExtraEducation extra = new Education();
		
		
		ExtraEducation extraE = new SecondarySchools();
		//it accesses secondary school interface implementation only
		extraE.informalEducation();
		extraE.tutorials("person", "love", "cool");
		System.out.println(extraE.averageAttendance);  //access interface members only
		
		ExtraEducation ext = new University();
		String[] ray = ((University)ext).uniArray;   //cast to get members of University
		
		
		
		ExtraEducation extra[] = new ExtraEducation[2];
		extra[0] = new SecondarySchools();
		extra[1] = new University();
		
		for (ExtraEducation exty: extra)exty.informalEducation();
		
		ExtraEducation extr[] = {new SecondarySchools(), new University()};
		ExtraEducation extraEd[] = new ExtraEducation[]{new SecondarySchools(), new University()};
		
		char c = 'e';
		long d = 12344355497359l;
		byte e = 12;
		short f = 32000;
		int f1 = 809429;
		
		float t = 90.5322E-12f;
		double s = 2.3482;
		//int, just like Education is superclass of all the whole no numeric class except long
		int g = c, h = (int)d, i = e, j=f;
		//long is the superclass of all numeric types
		long k = c, l=d, m = e, n =f, o = f1;
		
		// if i want to do it the other way round, I will have to cast
		
		short p = (short)f1;*/
	}

}
