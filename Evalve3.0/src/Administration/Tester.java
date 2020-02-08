package Administration;

public class Tester {
	private static SubjectIdentifier f =  new SubjectIdentifier();
	public static void main(String[] args) {
		String Sub = "Junior Secondary School Three";
		String Cls = f.ClassList(Sub.toUpperCase());
		//(Cls);
	}

}
