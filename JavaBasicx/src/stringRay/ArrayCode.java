package stringRay;

public class ArrayCode {
	
	ArrayCode(){
		System.out.println("Launching constructor");
	}
	
	static void oneDimensionalArray(){
		float[] d = new float[10]; 	//the allocation stores 0
		int c = d.length;
		System.out.println("starting:"+c);
		
		while(c-- !=0){
			System.out.println(d[c]);
		}
		
		c = d.length;
		System.out.println("Restarting:"+c);
		for(int r=0; r<c; r++){
			d[r] = (float)r/c;
			System.out.println(d[r]);
		}
	}
	
	static void multi2DimArray(){
		byte[][] d = new byte[3][5];
		int c = d.length;
		System.out.println("starting:"+c);

		while(c-- !=0){
			for(int y=0; y<5; y++){
				System.out.println("multiplying:"+c+"*"+y);
				d[c][y] = (byte) ((byte) c*y);
			}
		}
		
		c = d.length;
		System.out.println("Restarting:"+c);
		while(c-- !=0){
			for(int y=0; y<5; y++){
				System.out.println(d[c][y]);
			}
		}
	}
	
	
	void multi3DimArray(){
		byte[][][] d = new byte[3][5][10];
		int c = d.length;
		System.out.println("starting:"+c);

		while(c-- !=0){
			for(int y=0; y<5; y++){
				for(int x=0; x<10; x++){
				System.out.println("multiplying:"+c+"*"+y+"*"+x);
				d[c][y][x] = (byte) ((byte) c*y*x);
				}
			}
		}
		
		c = d.length;
		System.out.println("Restarting:"+c);
		while(c-- !=0){
			for(int y=0; y<5; y++){
				for(int x=0; x<10; x++){
				System.out.println(d[c][y][x]);
				}
			}
		}
	}
	public static void main(String[] args){
		//ArrayCode.oneDimensionalArray();
		//System.out.println();
		//System.out.println("2 DIMENSION");
		//ArrayCode.multi2DimArray();
		//System.out.println();
		//System.out.println("3 DIMENSION");
		//new ArrayCode().multi3DimArray();
		
		String multiStrArr[][] = new String[][]{{"A", "B"},{"coocoo","mugu"},};
		String threeDArray[][][] = { {{"A", "B"},{"coocoo","mugu"}}, {{"A", "B"},{"coocoo","mugu"}} };
										//first 2-D Array				second 2-D Array

		int c = multiStrArr.length;
		System.out.println("Restarting:"+c);
		while(c-- !=0){
			for(int y=0; y<2; y++){
				System.out.println(c+" - "+y);
				System.out.println(multiStrArr[c][y]);
			}
		}
		
		//A 3-D array is an array of 2d arrays
		String multi3StrArr[][][] = 
				new String[][][]{{{"A", "B"},{"coocoo","mugu"}},{{"Jan", "Feb", "Mar"},{"guide","favor","love"}}};
		
		//A 4-D array is an array of 3d arrays
		String fourDArray[][][][]= {{{{"A", "B"},{"coocoo","mugu"}}, {{"A", "B"},{"coocoo","mugu"}} }, { {{"A", "B"},{"coocoo","mugu"}}, {{"A", "B"},{"coocoo","mugu"}} } };
		
		//A 5-D array is an array of 4d arrays
		String fiveDArray[][][][][] = {{{{{"A", "B"},{"C","D"}},{{"E","F"},{"G","H"}}}, {{{"I", "J"},{"K","L"}},{{"M","N"},{"O","P"}}}},{null}};
		
		System.out.println("first Array:"+fiveDArray.length +", 2nd Array:"+ fiveDArray[1].length
				+", third array:"+fiveDArray[0][1].length);
		
		int a[][][][][] = new int[2][4][6][5][10];
		System.out.println("first Array:"+a.length +", 2nd Array:"+ a[1].length
				+", 3rd array:"+a[0][1].length+", 4th array:"+a[0][1][2].length+", 5th array:"+a[0][1][2][3].length);
		
	}

}
