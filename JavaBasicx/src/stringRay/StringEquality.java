package stringRay;

public class StringEquality {
	
	StringBuilder stringA = new StringBuilder("Joseph");
	String stringB = new String("Ayoade");
	String stringC = "Adetunji";
	
	StringBuilder stringD = new StringBuilder("Joseph");
	String stringE = new String("Ayoade");
	String stringF = "Adetunji";
	
	public StringEquality(){
		System.out.println("stringA == stringD=  "+(stringA == stringD));
		System.out.println("stringB == stringE=  "+(stringB == stringE));
		System.out.println("stringC == stringF=  "+(stringC == stringF));
		System.out.println("stringB==Ayoade > "+(stringB=="Ayoade"));
		System.out.println("stringC==Adetunji > "+(stringC=="Adetunji"));
		System.out.println("--");
		System.out.println("stringA equals stringD=  "+(stringA.equals(stringD)));
		System.out.println("stringB equals stringE=  "+(stringB.equals(stringE)));
		System.out.println("stringC equals stringF=  "+(stringC.equals(stringF)));
		System.out.println("stringB equals Ayoade > "+(stringB.equals("Ayoade")));
		System.out.println("stringC equals Adetunji > "+(stringC.equals("Adetunji")));
		System.out.println("--");
	}

	
	public static void main(String[] args){
		StringEquality A = new StringEquality();
		StringEquality B = new StringEquality();
		
		System.out.println(A instanceof StringEquality);
		System.out.println(B instanceof StringEquality);
		
		System.out.println(A == B);
		System.out.println(A.equals(B));   // equals was false by default before override
		
		String aDay = new String("SUN");
		System.out.println("--");
		System.out.println(aDay == "SUN");

	}

/*
	@Override
	public boolean equals(Object anObject) {
		if (anObject instanceof StringEquality) {
			StringEquality b = (StringEquality)anObject;
			return (stringB.equals(b.stringE) &&
			stringC == b.stringF);
			}
			else
			return false;

		}
*/
}
