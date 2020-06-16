package Laufzeit;


public class Laufzeit {

	private static int laufzeitzaehler;

	private static int frequenz;


	//Setzt die Frequenz standardmäßig auf 4
	public Laufzeit() {
		laufzeitzaehler = 0;
		frequenz = 4;
	}


	public static int getFrequenz() {
		return frequenz;
	}

	public static int getLaufzeitzaehler() {
		return laufzeitzaehler;
	}

	public static void setFrequenz(int frequenz) {
		Laufzeit.frequenz = frequenz;
	}

	//Erhöht die Laufzeit, den Watchdog  und gegebenenfalls den Timer wenn T0CS(Timer0 Clock Source) auf 0 ist
	public static void increaseLaufzeitzaehler() {
		laufzeitzaehler += 4 / frequenz;
		WatchDogTimer.increaseTimer();
		
		
		if (Timer.getT0CS() == 0) {
			Timer.incTimer();	
		}
		
	}

}
