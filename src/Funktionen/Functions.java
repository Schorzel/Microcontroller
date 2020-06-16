package Funktionen;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;

import DateiVerarbeitung.Decoder;
import Laufzeit.Laufzeit;
import Laufzeit.WatchDogTimer;
import Speicher.FileRegister;
import Speicher.Speicher;
import Speicher.Stack;

public class Functions {

	protected static boolean cFlag;

	private static int value;

	private static int zyklen;

	private static boolean sleep = false;

	// Werte aus dem Speicher
	private static int adresse;

	private static int[][] fileReg;

	private static int stackpointer;

	private static int bankFlag;

	private static int w;

	// Parameter
	private static int b;

	private static int d;

	private static int f;

	private static int k;

	private static String functionName;

	private static Method functionToCall;

	public static void run() {
		if (!sleep) {
			reload();
			System.out.println(Integer.toHexString(adresse) + ": '" + functionName + "' was called");

			// Indirekte Adressierung. Wenn Adresse 0 abgefragt wird, benutzen wir
			// stattdessen Adresse 4
			if (f == 0) {
				f = FileRegister.getBankValue(0, 4);
			}

			if (!functionName.equals("NOP")) { // Abfrage ob überhaupt ein Befehl
												// ausgeführt werden soll

				// Goto und Return haben einen Unterstrich am ende, der nun
				// hinzugefügt werden
				// muss.
				if (functionName.equals("GOTO")) {
					functionName += "_";
				} else if (functionName.equals("RETURN")) {
					functionName += "_";
				}

				// Funktions aufruf per Reflection (Reflection geht durch den Baum
				// des codes und durchsucht diesen nach allen deklarierten
				// funktionen)
				try {
					functionToCall = Functions.class.getDeclaredMethod(functionName);
					functionToCall.invoke(null); // Null weil auf kein spezifisches
													// Objekt bezogen
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			} else {
				// Auch bei einem NOP befehl wird ein Zyklus ausgeführ, der hier
				// hochgezählt
				// werden muss
				zyklen++;
			}

			countPC(); // PC erhöhen

			// Timer für die anzahl der ausgeführten Zyklen erhöhen
			for (int i = 0; i < zyklen; i++) {
				Laufzeit.increaseLaufzeitzaehler();
			}
		} else {
			WatchDogTimer.increaseTimer();
		}
	}

	// Überprüft ob Sleep true/false ist
	public static boolean isSleep() {
		return sleep;
	}

	// Setzt den Sleep
	public static void setSleep(boolean sleep) {
		Functions.sleep = sleep;
	}

	// Byte-oriented Operations

	// ADD W and f
	public static void ADDWF() {
		Flags.checkFlagDC(w, fileReg[bankFlag][f], false);
		value = Alu.ALU(fileReg[bankFlag][f], d, f, "+");
		Flags.checkFlagsCZ(value, false);
		zyklen++;
	}

	// AND W and f
	public static void ANDWF() {

		value = Alu.ALU(fileReg[bankFlag][f], d, f, "&");
		Flags.checkFlagZ(value);
		zyklen++;
	}

	// Clear f
	public static void CLRF() {
		value = Alu.ALU(0, 1, f, "");
		Flags.checkFlagZ(value);
		zyklen++;
	}

	// Clear W
	public static void CLRW() {
		value = Alu.ALU(0, 0, 0, "");
		Flags.checkFlagZ(value);
		zyklen++;
	}

	// Complement f
	public static void COMF() {
		value = Alu.ALU(fileReg[bankFlag][f], 0b11111111, d, f, "^");
		Flags.checkFlagZ(value);
		System.out.println(value);
		zyklen++;
	}

	// Decrement f
	public static void DECF() {
		value = Alu.ALU(fileReg[bankFlag][f], 1, d, f, "-");
		Flags.checkFlagZ(value);
	}

	// Decrement f, Skip if 0
	public static void DECFSZ() {
		value = Alu.ALU(fileReg[bankFlag][f], 1, d, f, "-");
		zyklen++;
		if (value != 0) {

		} else {
			NOP();
			zyklen++;
		}
	}

	// Increment f
	public static void INCF() {
		value = Alu.ALU(fileReg[bankFlag][f], 1, d, f, "+");
		Flags.checkFlagZ(value);
		zyklen++;
	}

	// Inclusive OR Literal with W
	public static void IORWF() {
		value = Alu.ALU(fileReg[bankFlag][f], d, f, "|");
		Flags.checkFlagZ(value);
		zyklen++;
	}

	// Increment f, Skip if 0
	public static void INCFSZ() {
		value = Alu.ALU(fileReg[bankFlag][f], 1, d, f, "+");
		zyklen++;
		if (value != 0) {

		} else {
			NOP();
			zyklen++;
		}
	}

	// Move f
	public static void MOVF() {
		value = Alu.ALU(fileReg[bankFlag][f], d, f, "");
		Flags.checkFlagZ(value);
		zyklen++;
	}

	// Move W to f
	public static void MOVWF() {
		MoveToFReg(f, w);
		zyklen++;

	}

	// No Operation
	public static void NOP() {
		countPC();

	}

	// Rotate Left f through Carry
	public static void RLF() {
		value = fileReg[bankFlag][f];
		int[] statusReg = Speicher.getStatusRegister();
		int cFlag = statusReg[7];

		cFlag = (value & 0b10000000);
		value = value << 1;
		value = value & 0b11111111;

		if (statusReg[7] == 1) {
			value = value | 0b00000001;
		}

		if (cFlag != 0) {
			cFlag = 1;
		}

		saveData(d, f, value);
		Flags.setFlag(0, cFlag);
		zyklen++;
	}

	// Rotate Right f through Carry
	public static void RRF() {
		value = fileReg[bankFlag][f];
		int[] statusReg = Speicher.getStatusRegister();
		int cFlag = statusReg[7];

		cFlag = value & 0b00000001;
		value = value >> 1;
		value = value & 0b11111111;

		if (statusReg[7] == 1) {
			value = value | 0b10000000;
		}

		if (cFlag != 0) {
			cFlag = 1;
		}

		saveData(d, f, value);
		Flags.setFlag(0, cFlag);
		zyklen++;
	}

	// Substract W from f
	public static void SUBWF() {
		Flags.checkFlagDC(fileReg[bankFlag][f], Alu.zweierKomp(w), true);
		value = Alu.ALU(fileReg[bankFlag][f], d, f, "-");
		Flags.checkFlagsCZ(value, false);
		zyklen++;

	}

	// Swap Nibbles in f
	public static void SWAPF() {
		value = fileReg[bankFlag][f];
		int upper = value & 0b11110000;
		int lower = value & 0b00001111;
		upper = upper >> 4;
		lower = lower << 4;

		value = upper | lower;

		saveData(d, f, value);
		zyklen++;
	}

	// Exclusive or W with f
	public static void XORWF() {
		value = Alu.ALU(fileReg[bankFlag][f], d, f, "^");
		Flags.checkFlagZ(value);
		zyklen++;
	}

	// Bit-Oriented Operations

	// Bit Clear f
	public static void BCF() {

		value = fileReg[bankFlag][f];

		value &= ~(1 << b);

		saveData(1, f, value);
		zyklen++;

	}

	// Bit Set f
	public static void BSF() {
		value = fileReg[bankFlag][f];

		value |= 1 << b;

		saveData(1, f, value);

		zyklen++;

	}

	// Bit Test, Skip if Clear
	public static void BTFSC() {
		value = fileReg[bankFlag][f];

		if (BigInteger.valueOf(value).testBit(b)) {

		} else {
			NOP();
			zyklen++;
		}
		zyklen++;
	}

	// Bit Test f, Skip if Set
	public static void BTFSS() {

		value = fileReg[bankFlag][f];
		
		if (BigInteger.valueOf(value).testBit(b)) {
			
			NOP();
			zyklen++;
		} else {

		}
		zyklen++;
	}

	// Literal und Control Operations

	// Add Literal and W
	public static void ADDLW() {

		Flags.checkFlagDC(w, k, false);

		value = Alu.ALU(k, 0, 0, "+");

		Flags.checkFlagsCZ(value, false);

		zyklen++;
	}

	// AND Literal with W
	public static void ANDLW() {

		Flags.checkFlagDC(w, fileReg[bankFlag][f], false);

		value = Alu.ALU(k, 0, 0, "&");

		Flags.checkFlagsCZ(value, false);

		zyklen++;
	}

	// Call Subroutine
	public static void CALL() {
		Stack.setStack(adresse + 1);

		int pc = 0;
		int pclath = Speicher.getPCLATH();

		pc = (pc | k);
		pclath = (pclath & 0b11000) << 8;

		pc = (pc | pclath);
		Speicher.setPC(pc - 1); // Da der PC nach dem Befehl nochmal erhöht wird,
								// rechnen wir hier -1

		zyklen += 2;

	}

	// Clear Watchdog Timer
	public static void CLRWDT() {
		// Affects -TO,-PD Flag
		WatchDogTimer.resetTimer();
		int option = FileRegister.getBankValue(1, 1);
		int status = FileRegister.getBankValue(0, 3);
		FileRegister.setDataInBank(1, 1, option & 0b11110111); // Clear PSA
		FileRegister.setDataInBank(3, status | 0b00011000); // Set -TO and -PD

		zyklen++;

	}

	// Unconditional Branch
	public static void GOTO_() {
		int pc = 0;
		int pclath = Speicher.getPCLATH();

		pc = (pc | k);
		pclath = (pclath & 0b11000) << 8;

		pc = (pc | pclath);
		Speicher.setPC(pc - 1); // Da der PC nach dem Befehl nochmal erhöht wird,
								// rechnen wir hier -1

		zyklen += 2;

	}

	// Inclusive OR Literal with W
	public static void IORLW() {

		value = Alu.ALU(k, 0, 0, "|");

		Flags.checkFlagZ(value);

		zyklen++;
	}

	// Move Literal to W
	public static void MOVLW() {
		Speicher.setWReg(k);

		zyklen++;
	}

	// Return from Interrupt
	public static void RETFIE() {
		Stack.setStackPointer(stackpointer - 1);
		Speicher.setPC(Stack.getStackValue() - 1);
		FileRegister.setDataInBank(11, FileRegister.getBankValue(0, 11) | 0b10000000); // GIE bit setzen

		zyklen += 2;

	}

	// Return with Literal in W
	public static void RETLW() {
		Stack.setStackPointer(stackpointer - 1);
		Speicher.setPC(Stack.getStackValue() - 1);
		saveData(0, 0, k); // K ins W Register schreiben

		zyklen += 2;

	}

	// Return from Subroutine
	public static void RETURN_() {
		Stack.setStackPointer(stackpointer - 1);
		Speicher.setPC(Stack.getStackValue() - 1);

		zyklen += 2;

	}

	// Sleep
	public static void SLEEP() {
		// Affects -TO,-PD Flag
		sleep = true;
		WatchDogTimer.resetTimer();
		int option = FileRegister.getBankValue(1, 1);
		int status = FileRegister.getBankValue(0, 3);
		FileRegister.setDataInBank(1, 1, option & 0b11110111); // Clear PSA
		FileRegister.setDataInBank(3, status | 0b00010000); // -TO setzen
		FileRegister.setDataInBank(3, status & 0b11110111); // -PD löschen
		zyklen++;
	}

	// Subtract W from Literal
	public static void SUBLW() {
		Flags.checkFlagDC(k, Alu.zweierKomp(w), true);

		value = Alu.ALU(k, 0, 0, "-");

		Flags.checkFlagsCZ(value, false);

		zyklen++;
	}

	// Exclusive OR Literal with W
	public static void XORLW() {
		value = Alu.ALU(k, 0, 0, "^");
		Flags.checkFlagZ(value);

		zyklen++;
	}

	// Lädt die Variablen neu
	protected static void reload() {
		cFlag = false;
		zyklen = 0;

		adresse = Speicher.getPC();
		fileReg = FileRegister.getFReg();
		stackpointer = Stack.getStackPointer();
		bankFlag = Speicher.getBankFlag();
		w = Speicher.getwReg();
		b = Decoder.decodeParameter(adresse, "b");
		d = Decoder.decodeParameter(adresse, "d");
		f = Decoder.decodeParameter(adresse, "f");
		k = Decoder.decodeParameter(adresse, "k");

		functionName = Decoder.decodeCommand(adresse).toUpperCase();
	}

	// Zählt den Programmzähler hoch
	protected static void countPC() {
		int adresse = Speicher.getPC();
		if (adresse == 0x3FF) { // Ende des PC Speichers => Reset auf 0
			System.out.println("PC überlauf: Zurücksetzen auf Adresse 0");
			Speicher.setPC(0);
		} else {
			Speicher.setPC(++adresse);
		}
	}

	// Speicher einen Wert entweder ins F- oder W-Register
	protected static void saveData(int d, int f, int value) {
		if (d == 1) { // Speicher ins File-Register
			MoveToFReg(f, value);
		} else { // Speicher ins W-Register
			Speicher.setWReg(value);
		}
	}

	// Speicher einen Wert auf beiden Seiten des F-Registers
	public static void MoveToFReg(int i, int data) {
		Speicher.setBankFlag();
		MoveToFReg(Speicher.getBankFlag(), i, data);
	}

	// Speichert einen Wert auf einer Seite des F-Registers
	public static void MoveToFReg(int d, int i, int data) {
		// Nur auf bestimmter Bank setzen
		if (f == 1 || f == 5 || f == 6 || f == 8 || f == 9) {
			FileRegister.setDataInBank(d, i, data);
		} else {
			FileRegister.setDataInBank(i, data);
		}
		Speicher.reload();
	}

}
