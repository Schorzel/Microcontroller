package Laufzeit;

import Funktionen.Functions;

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
				if (intcon[2] == 1 && intcon[5] == 1) { // T0IE + T0IF bit abfrage
					System.out.println("Timer Interrupt");
					callServiceRoutine();
				}
			}

		}
	}

	/**
	 * Calls the Service Routine Saves the return adress on the Stack and set the PC
	 * to 4
	 */
	public void callServiceRoutine() {
		//GUI.stop();
		FileRegister.setDataInBank(11, FileRegister.getBankValue(0, 11) & 0b01111111); // GIE Bit sperren

		// Service Routine aufrufen (Call auf Adresse 4)
		Stack.setStack(Speicher.getPC());
		Speicher.setPC(4);

		//GUI.reloadGUI();
	}
}
