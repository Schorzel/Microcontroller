
public class Laufzeit {

	private static int laufzeitzaehler;

	private static int frequenz;

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

	public static void increaseRuntimecounter() {
		laufzeitzaehler += 4 / frequenz;
	}

}
