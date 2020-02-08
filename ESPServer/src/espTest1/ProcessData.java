package espTest1;

import java.util.List;

public class ProcessData {
	private int bleu, noir,  bigTime,  smallTime, ID;
	private String bigOffOn,  smallOffOn,  ThingTime, bigFill,  smallFill, computerTime;
	private List<Object> bigLevel, smallLevel;
	private EspDerby derby = new EspDerby();
	ProcessData() {
		// TODO Auto-generated constructor stub
	}

	
	
	static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	public int getBleu() {
		return bleu;
	}

	public void setBleu(int bleu) {
		this.bleu = bleu;
	}

	public int getNoir() {
		return noir;
	}

	public void setNoir(int noir) {
		this.noir = noir;
	}

	public List<Object> getBigLevel() {
		return bigLevel;
	}

	public void setBigLevel(List<Object> bigLevel) {
		this.bigLevel = bigLevel;
	}

	public List<Object> getSmallLevel() {
		return smallLevel;
	}

	public void setSmallLevel(List<Object> smallLevel) {
		this.smallLevel = smallLevel;
	}

	public int getBigTime() {
		return bigTime;
	}

	public void setBigTime(int bigTime) {
		this.bigTime = bigTime;
	}

	public int getSmallTime() {
		return smallTime;
	}

	public void setSmallTime(int smallTime) {
		this.smallTime = smallTime;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getBigOffOn() {
		return bigOffOn;
	}

	public void setBigOffOn(String bigOffOn) {
		this.bigOffOn = bigOffOn;
	}

	public String getSmallOffOn() {
		return smallOffOn;
	}

	public void setSmallOffOn(String smallOffOn) {
		this.smallOffOn = smallOffOn;
	}

	public String getThingTime() {
		return ThingTime;
	}

	public void setThingTime(String thingTime) {
		ThingTime = thingTime;
	}

	public String getBigFill() {
		return bigFill;
	}

	public void setBigFill(String bigFill) {
		this.bigFill = bigFill;
	}

	public String getSmallFill() {
		return smallFill;
	}

	public void setSmallFill(String smallFill) {
		this.smallFill = smallFill;
	}



	public String getComputerTime() {
		return computerTime;
	}



	public void setComputerTime(String computerTime) {
		this.computerTime = computerTime;
	}



	public List<Object> getBlueTrend() {
		// TODO Auto-generated method stub
		return null;
	}



	public List<Object> getGrayTrend() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
