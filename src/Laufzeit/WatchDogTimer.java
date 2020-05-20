package Laufzeit;

import Speicher.FileRegister;
import Speicher.Speicher;


public class WatchDogTimer {

	private static boolean enabled;
	private static int watchdogTimer;
	private static int maxTime;
	private static int prs; // Teilerfaktoren
	private static int psa; // Prescaler Assignment bit
	

	public WatchDogTimer() {
		watchdogTimer = 0;
		maxTime = 18000;
		enabled = false;
		
		setPRS();
		setPSA();

	}

	public static boolean isEnabled() {
		return enabled;
	}

	public static void increaseTimer()
	{
		if (enabled) {
			watchdogTimer += 4 / Laufzeit.getFrequenz(); // watchdogTimer + (4 / Quarzfrequenz (4MHz))
			checkMaxTime();
		}
	}
	
	
	
	public static int getWatchdogTimer() {
		return watchdogTimer;
	}



	public static int getMaxTime() {
		setPSA();
		setPRS();
		if (psa == 0) {
			return maxTime;
		} else {
			return prs * maxTime;
		}
	}


	
	public static void resetTimer()
	{
		watchdogTimer = 0;
	}
	
	//Überprüft ob die maximale Zeit überschritten ist
	public static void checkMaxTime() {

		if (watchdogTimer >= getMaxTime()) {
			Reset.WDTReset();
		}

	}
	public static void setEnabled() {
		enabled = !enabled;
	}
	
	private static void setPRS()
	{
		int option = FileRegister.getBankValue(1, 1);
		prs = option & 0b00000111;
		prs = (int) Math.pow(2, prs);
	}

	
	private static void setPSA()
	{
		int[] option = Speicher.getOptionReg();
		psa = option[4];
	}
}
