package Administration;

public class wordTest {
	public static void main(String[] args) {
		String word1 = "JSS1:JSS2:JSS3";
		String word2 = "JSS1:JSS3:JSS3";
		int position = 0;
		String[] oldWord = null; String[] newWord = null;
		for (int x = 0; x<=3; x++){
			oldWord = word1.split(":", x);
			newWord = word2.split(":", x);
			}
		if (oldWord[0].trim().equals(newWord[0].trim())){
			position = 1;
			//(oldWord[0]+"-"+newWord[0]+"-"+position);
		}
		if (oldWord[1].trim().equals(newWord[1].trim())){
			position = 2;
			//(oldWord[1]+"-"+newWord[1]+"-"+position);
		}
		if (oldWord[2].trim().equals(newWord[2].trim())){
			position = 3;
			//(oldWord[2]+"-"+newWord[2]+"-"+position);
		}
	}
	public static String split(String f, int d){
		String[] parts = f.split("`");
		String part1 = parts[0];
		String part2 = parts[1];
		String part3 = parts[2];
		switch (d){
		case 1:
			return part1;
		case 2:
			return part2;
		case 3:
			return part3;
		}
		
		return null;
	}
}
