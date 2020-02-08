package inheritance;

public class University extends Education implements ExtraEducation{
	
	String[] uniArray = new String[5];	//has to be static because the initialization is consistent for all University instances

	
	University(){
		super("Helloooo Daddy");
		System.out.println("Call from Derived class> University School");
		System.out.println("Previous education took:"+(primary + secondary));
		adultEducation();
		System.out.println();
	
	}
	
	void levels(){}

	@Override
	public void tutorials(String... strings) {
		// TODO Auto-generated method stub
		System.out.println("University School: Interface tutorials");
	}

	@Override
	public void informalEducation() {
		// TODO Auto-generated method stub
		System.out.println("University School: Interface informal education");
	}
}
