
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

	}

	public static boolean isEnabled() {
		return enabled;
	}

	

	public static int getWatchdogTimer() {
		return watchdogTimer;
	}

	public static void setWatchdogTimer(int watchdogTimer) {
		WatchDogTimer.watchdogTimer = watchdogTimer;
	}

	public static int getMaxTime() {
		return maxTime;
	}

	public static void setMaxTime(int maxTime) {
		WatchDogTimer.maxTime = maxTime;
	}
	
	public static void resetTimer()
	{
		watchdogTimer = 0;
	}
	
	//Überprüft ob die maximale Zeit überschritten ist
	public static void checkMaxTime() {

		if (watchdogTimer > getMaxTime()) {
			Reset.WDTReset();
		}

	}
	public static void setEnabled() {
		enabled = !enabled;
	}
	

}
