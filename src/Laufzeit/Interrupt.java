package Laufzeit;

import Funktionen.Functions;
import GUI.GUI;
import Speicher.FileRegister;
import Speicher.Speicher;
import Speicher.Stack;

public class Interrupt {
	public Interrupt(String type) {
		int[] intcon = Speicher.getIntconReg();

		if (intcon[0] == 1) { // GIE bit abfrage
			if (type.equals("RB0")) {
				if (intcon[3] == 1) { // INTE bit abfrage
					System.out.println("RB0 Interrupt");
					
					if (Functions.isSleep()) {
						Functions.setSleep(false);
						Reset.Interrupt();
					}
					callServiceRoutine();
				}

			} else if (type.equals("RB4-7")) {
				if (intcon[4] == 1) { // RBIE bit abfrage
					System.out.println("RB4-7 Interrupt");
					
					if (Functions.isSleep()) {
						Functions.setSleep(false);
						Reset.Interrupt();
					}
					
					callServiceRoutine();
				}
				
			} else if (type.equals("TMR0")) {
				if (intcon[2] == 1) { // T0IE bit abfrage
					int INTCON = FileRegister.getBankValue(0, 11);
					INTCON |= 1 << 2;
					FileRegister.setDataInBank(11, INTCON);
					
					System.out.println("Timer Interrupt");
					
					callServiceRoutine();
				}
			}

		}
	}

	// Ruft die Service Routine auf, speichert den Return wert auf dem Stack und setzt den Programmzähler auf 4
	public void callServiceRoutine() {
		GUI.stop();
		FileRegister.setDataInBank(11, FileRegister.getBankValue(0, 11) & 0b01111111); // GIE Bit sperren

		Stack.setStack(Speicher.getPC());
		Speicher.setPC(4);
		
	}
}
