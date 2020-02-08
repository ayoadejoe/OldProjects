package stringRay;

public class ArrayObj{

	Engines[] eigen = new Engines[]{new Electronics(), new Upholstery()};
	FuelTank [] fueling = new FuelTank[]{new FuelLevel(), new FuelMeter()};
	
	//ArrayObj[] ray = new ArrayObj[]{new ArrayCode(), new StringBuildBuff()}; //you cannot store other classes in an array of another class that has nothing to do with it
	
	Object[] packageClass = new Object[]{new ArrayCode(), new StringBuildBuff(), 
			new StringImmutability(), new ArrayObj(), new Electronics(), new Upholstery(),
			eigen, fueling};

}

interface Engines{
	
}

class Electronics implements Engines{
	
}

class Upholstery implements Engines{
	
}

abstract class FuelTank{
	
}

class FuelMeter extends FuelTank{
	
}

class FuelLevel extends FuelTank{
	
}

