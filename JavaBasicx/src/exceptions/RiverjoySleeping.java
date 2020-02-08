package exceptions;

import javax.swing.JOptionPane;

public class RiverjoySleeping {
	int sleeptime = 2100;
	int deepSleeptime = 300;
	int waketime = 700;
	boolean eyesDrowsy = false;
	boolean eyesOpen = true;
	boolean eyesClose = false;
	boolean subConscious = false;
	public RiverjoySleeping() {
		
		int time = 2150;
		
		if(time >= sleeptime)putRiverjoyToBed();
		else riverJoyWatchCartoon();
	}
	
	
	private boolean riverJoyWatchCartoon() {
		System.out.println("Riverjoy watching cartoon oooo...");
		return false;
	}


	private void putRiverjoyToBed() {
		if(eyesDrowsy)waitTime();
		else singLullaby();
		 
		while(eyesOpen || !eyesDrowsy)singLullaby();
		
		subConscious = true;
		int y=0;
		while(subConscious){
			y++;
			waitTime();
			System.out.println("Sleeping... "+y);
			if(y>10){
				if(JOptionPane.showConfirmDialog(null, "Riverjoy pees?") == 1){
					try{
						wakeUp();
						}catch(RiverjoyPees r){
							r.printStackTrace();
						}finally{
							subConscious = riverJoyWatchCartoon();
						}
				}else{
					y=0;
					waitTime();
					System.out.println("Sleeping... "+y);
				}
			}
		}
		
	}


	private void wakeUp() throws RiverjoyPees {
			throw new RiverjoyPees();
		
	}


	private void singLullaby() {
		int n=0;
		while (n <13){
			
			System.out.println("singing... "+(n++));
			waitTime();
			if(n>=11)break;
		}
		if(n>10){
			eyesDrowsy = true;
			eyesOpen = false;
		}
		
		
	}


	private void waitTime() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		new RiverjoySleeping();
	}

}
