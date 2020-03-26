import Speicher.FileRegister;
import Speicher.Speicher;

public class Timer {

	private static int timer;
	private static int prs;
	private static int psa;
	private static int T0CS;
	
	
	public Timer() {
		timer = FileRegister.getBankValue(0, 1);
	}
	
	
	
	public static int getT0CS() {
		return T0CS;
	}
	
	public static void setT0CS() {
		int[] option = Speicher.getOptionReg();
		T0CS = option[2];
	}
	
	
	private static void setPRS()
	{
		int option = FileRegister.getBankValue(1, 1);
		prs = option & 0b00000111;
		prs = (int) Math.pow(2, prs)*2;
	}
	
	
	private static void setPSA()
	{
		int[] option = Speicher.getOptionReg();
		psa = option[4];
	}
	
	
	public static void incTimer() {
		timer += 4 / Laufzeit.getFrequenz(); 
		checkOverflow();
		FileRegister.setDataInBank(0, 1, timer);
	}
	
	public static void checkOverflow() {
		if(timer > 255) {
			int INTCON = FileRegister.getBankValue(0, 11);
			INTCON |= 1 << 2;
			FileRegister.setDataInBank(11, INTCON);
			//Interrupt
		}
	}
}
