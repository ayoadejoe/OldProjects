package newIntros;

public class Messager implements TestInterface{

	@Override
	public String messageReturn(String message) {
		return message;
	}
	
	
	Messager(){
		System.out.println(messageReturn("I am Ok and "));
	}
	
	
	public static void main(String[] args){
		new Messager();
	}

}
