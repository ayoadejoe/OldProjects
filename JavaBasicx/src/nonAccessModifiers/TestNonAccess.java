package nonAccessModifiers;

public class TestNonAccess {

	AbstractClass abstr;
	
	 TestNonAccess() {
		//abstr.sweetcash(); first, abstr is not instantiated hence, 
		 //sweetcash will not compile unless it is implemented in an extended class of AbstractClass
		//abstr.great();  // can only be used in the extension
		implementCheque cheque = new implementCheque();
		new implementMoney().sweetcash();
		new implementMoney().certifyGreatness();
		
		cheque.sweetcash();
		cheque.sing();
		cheque.pray();
		cheque.great(); 	//will work well because derived classes have access to methods of their parents
	
		
	 }
	 
	 static public void main(String[] args){
		 AbstractClass.sing();
		 
		 new TestNonAccess();
		 
	 }
	 
	 class implementMoney extends AbstractClass{
		void sweetcash() {
			System.out.println("Money is now being manufactured");
		}
		
		void certifyGreatness(){
			great();
		}
		//void great(){ is not possible, it has been implemented already}
	 }
	 
	 class implementCheque extends AbstractClass{
		 void sweetcash() {
			System.out.println("Cheque is now being given");
		}

		/*public void great() {
			// TODO Auto-generated method stub
			super.great();  //the importance of super call in a derived class.
			//super makes it possible to call an abstract class' methods
		}

		*/

		void pray() {
			// TODO Auto-generated method stub
			super.pray();
		}
		 
		 
	 }
}
