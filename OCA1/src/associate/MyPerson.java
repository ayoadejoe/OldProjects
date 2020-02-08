package associate;

import java.util.ArrayList;

class MyPerson {
	String name;
	MyPerson(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MyPerson){
			MyPerson r = (MyPerson) obj;
			boolean equal = r.name.equals(this.name);
			return equal;
		}else return false;
	}

	public static void main(String[] args) {
		ArrayList<Object> myArrList = new ArrayList<Object>();
		
		MyPerson p1 = new MyPerson("Shreya");
		MyPerson p2 = new MyPerson("Paul");
		
		myArrList.add(p1);
		myArrList.add(p2);
		myArrList.add(p2);
		
		System.out.println(myArrList.contains(new MyPerson("Shreya")));
		System.out.println(myArrList.contains(p1));
		
		System.out.println(myArrList.indexOf(new MyPerson("Paul")));
		System.out.println(myArrList.indexOf(p2));
		
		System.out.println(myArrList.lastIndexOf(new MyPerson("Paul")));
		System.out.println(myArrList.lastIndexOf(p2));
		System.out.println("");
		pull();
		
	}
	
	static void pull(){
		ArrayList<StringBuilder> myArrList = new ArrayList<StringBuilder>();
		
		StringBuilder sb1 = new StringBuilder("Jan");
		StringBuilder sb2 = new StringBuilder("Feb");
		
		myArrList.add(sb1);
		myArrList.add(sb2);
		myArrList.add(sb2);

		ArrayList<StringBuilder> assignedArrList = myArrList;
		ArrayList<StringBuilder> clonedArrList = (ArrayList<StringBuilder>)myArrList.clone();
		System.out.println(myArrList == assignedArrList);
		System.out.println(myArrList == clonedArrList);
		
		StringBuilder myArrVal = myArrList.get(0);
		StringBuilder assignedArrVal = assignedArrList.get(0);
		StringBuilder clonedArrVal = clonedArrList.get(0);
		System.out.println(myArrVal == assignedArrVal);
		System.out.println(myArrVal == clonedArrVal);
		
		}

}
