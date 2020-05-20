package Laufzeit;
import Speicher.FileRegister;
import Speicher.Speicher;


public class Timer {
	private static int vorteilsTimer;
	private static int timerMax;
	private static int timer;
	private static int prs; // Teilerfaktoren
	private static int psa; // Prescaler Assignment bit
	private static int T0CS; // Timer 0 Clock Source

	public Timer() {
		timer = FileRegister.getBankValue(0, 1);
		timerMax = 0;
		vorteilsTimer = 0;
	}

	
	public static int getTimer() {
		return timer;
	}

	public static int getTimerMax() {
		return timerMax;
	}
	
	
	
	public static int getT0CS() {
		setT0CS();
		return T0CS;
	}

	
	public static int getVorteilsTimer() {
		return vorteilsTimer;
	}
	
	public static void setTimer(int timer) {
		Timer.timer = timer;
	}
	
	public static void setTimerMax(int timerMax) {
		Timer.timerMax = timerMax;
	}
	
	
	public static void setT0CS() {
		int[] option = Speicher.getOptionReg();
		T0CS = option[2];
	}

	private static void setPRS() {
		int option = FileRegister.getBankValue(1, 1);
		prs = option & 0b00000111;
		prs = (int) Math.pow(2, prs) * 2;
	}

	private static void setPSA() {
		int[] option = Speicher.getOptionReg();
		psa = option[4];
	}

	public static void incTimer() {
		setPSA();
		setPRS();
		
		if (psa == 1) {
			prs = 1;
		}

		
		vorteilsTimer += 4 / Laufzeit.getFrequenz();

		if (vorteilsTimer >= prs) {
			
			vorteilsTimer = 0;
			timer += 4 / Laufzeit.getFrequenz();
			checkOverflow();
			FileRegister.setDataInBank(0, 1, timer);
		}

	}

	public static void checkOverflow() {
		if (timer > 255) {
			int INTCON = FileRegister.getBankValue(0, 11);
			INTCON |= 1 << 2;
			FileRegister.setDataInBank(11, INTCON);
			// Interrupt
		}
	}
}
