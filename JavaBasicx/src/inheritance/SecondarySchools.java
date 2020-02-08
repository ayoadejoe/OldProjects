package inheritance;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondarySchools extends Education implements ExtraEducation{
	int primary = 9;
	SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yy HH:mm");
	
	public SecondarySchools() {
		// TODO Auto-generated constructor stub
		Date date = new Date();
		System.out.println(simpleDate.format(date));
	}
	
	void juniorClasses(int primary){
		System.out.println("Call from Derived class> Secondary School");
		System.out.println("juniorClasses primary="+primary);
		genderSchools();
		System.out.println("Secondary primary="+this.primary);
		System.out.println("Education primary="+super.primary);
	}

	@Override
	public void tutorials(String...strings ) {		//Cannot reduce the visibility of the inherited method from ExtraEducation
		//implements the interface (extreme abstract class)
		System.out.println("Secondary School: Interface tutorials");
		
	}

	@Override
	public void informalEducation() {
		//Interface helps to tie together related classes
		System.out.println("Secondary School: Interface informal education");
	}

}
