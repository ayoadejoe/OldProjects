package flowControl;

public class DoWhileWhile {
	
	public static void main (String[] args){
		boolean poor = true; int t=0;
		outer:
			do{				//do-while enters loop at least once to check condition
			t++;
			if(t>10)poor=!poor;
			System.out.println("t="+t+ " poor="+poor);
		}while(!poor);
		
		//labels
		
		String[] programmers = {"Outer", "Inner"};
		outer:
		for (String outer : programmers) {
		for (String inner : programmers) {
		if (inner.equals("Inner"))
		break outer; 				//this transfer control outside the loop stopping the entire operation
		System.out.println(inner + ":");
		}
		}
		
		String[] programmer = {"Paul", "Shreya", "Selvan", "Harry"};
		outer:
		for (String name1 : programmer) {
		for (String name : programmer) {
		if (name.equals("Shreya"))
		continue outer;
		System.out.println(name);
		}
		}
	


	}

}
