package inheritance;

import java.text.SimpleDateFormat;
import java.util.Date;

abstract class AbstractInheritance {
	private int shallow = 200;
	abstract void sing();
	abstract String sing(String song);
	abstract int sing(int key, String song);
	abstract SimpleDateFormat sing( Date date);

}


class PlayMusic extends AbstractInheritance{

	//int x = shallow;
	public PlayMusic() {
		
		sing();
		sing("fur elise");  //the overloaded method is overidden in this context
		//int t = super.shallow;
	}
	
	
	@Override
	void sing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	String sing(String song) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	int sing(int key, String song) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	SimpleDateFormat sing(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	
}