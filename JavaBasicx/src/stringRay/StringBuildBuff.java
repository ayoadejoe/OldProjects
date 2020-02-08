package stringRay;

import basic.ToplevelClass;

public class StringBuildBuff {

	public static void main(String[] args){
		StringBuilder stringbuilder = new StringBuilder("Prosperous");
		System.out.println(stringbuilder.append(" Joseph"));
		
		stringbuilder.append(new ToplevelClass());
		System.out.println(stringbuilder);
		
		System.out.println(stringbuilder.capacity());
		
		System.out.println(stringbuilder.codePointAt(0));
		
		System.out.println(stringbuilder.delete(10, 15));
		
		System.out.println(stringbuilder.insert(3, true));
		
		System.out.println(stringbuilder.reverse());
		
		System.out.println(stringbuilder.reverse());
		
		stringbuilder.ensureCapacity(10);
		
		stringbuilder.trimToSize();
		
		System.out.println(stringbuilder);
		
		System.out.println(stringbuilder.subSequence(10, 15));
		
		StringBuilder stringA = new StringBuilder("Joseph");
		StringBuilder stringB = new StringBuilder("Joseph");
		
		System.out.println();
		System.out.println("stringA == stringB=  "+(stringA == stringB));
		System.out.println(stringA.equals(stringB));
		//System.out.println(stringA == "Joseph");
		System.out.println("---");
		System.out.println(stringA.equals("Joseph"));
		System.out.println(stringB.equals("Joseph"));
		System.out.println("--");
		System.out.println(stringA.subSequence(2, 4));
		System.out.println(stringA);
		System.out.println(stringA.append(" blessed"));
		System.out.println(stringA);
		System.out.println();
		
		String stringC = new String("Joseph");
		System.out.println(stringC.equals("Joseph"));
		System.out.println(stringC.subSequence(2, 4));
		System.out.println(stringC);
		//System.out.println(stringC.append(" blessed"));
		System.out.println(stringC.replace("seph", "e"));
		System.out.println(stringC);
		System.out.println();
		
		
	}
}
