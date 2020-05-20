package Funktionen;

import java.math.BigInteger;

import Laufzeit.WatchDogTimer;
import Speicher.FileRegister;
import Speicher.Speicher;
import Speicher.Stack;



public class Functions {

	protected static boolean cFlag;
	private static int value;
	private static int cycles;
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
	
	
	public static boolean isSleep() {
		return sleep;
	}
	
	public static void setSleep(boolean sleep) {
		Functions.sleep = sleep;
	}
	

	//Byte-oriented Operations
	
	public static void ADDWF() {
		Flags.checkFlagDC(w, fileReg[bankFlag][f]);
		value = Alu.ALU(fileReg[bankFlag][f], d, f, "+");
		Flags.checkFlagsCZ(value, false);
	}

	public static void ANDWF() {

		value = Alu.ALU(fileReg[bankFlag][f], d, f, "&");
		Flags.checkFlagZ(value);
	}

	public static void CLEARF() {
		value = Alu.ALU(0, 1, f, "");
		Flags.checkFlagZ(value);
	}

	public static void CLEARW() {
		value = Alu.ALU(0, 0, 0, "");
		Flags.checkFlagZ(value);
	}

	public static void COMF() {
		value = Alu.ALU(fileReg[bankFlag][f], 0b1111111, d, f, "^");
		Flags.checkFlagZ(value);
	}

	public static void DECF() {
		value = Alu.ALU(fileReg[bankFlag][f], 1, d, f, "-");
		Flags.checkFlagZ(value);
	}

	public static void DECFSZ() {
		value = Alu.ALU(fileReg[bankFlag][f], 1, d, f, "-");

		if (value != 0) {

		} else {
			NOP();
		}
	}

	public static void INCF() {
		value = Alu.ALU(fileReg[bankFlag][f], 1, d, f, "+");
		Flags.checkFlagZ(value);
	}

	public static void IORWF() {
		value = Alu.ALU(fileReg[bankFlag][f], d, f, "|");
		Flags.checkFlagZ(value);
	}

	public static void INCFSZ() {
		value = Alu.ALU(fileReg[bankFlag][f], 1, d, f, "+");

		if (value != 0) {

		} else {
			NOP();
		}
	}

	public static void MOVF() {
		value = Alu.ALU(fileReg[bankFlag][f], d, f, "");
		Flags.checkFlagZ(value);
	}

	public static void MOVWF() {
		MoveToFReg(f, w);

	}

	public static void NOP() {
		// Z�hlt PC hoch
	}

	public static void RLF() {
		value = fileReg[bankFlag][f];
		int[] statusReg = Speicher.getStatusRegister();
		int cFlag = statusReg[7];

		cFlag = value & 0b10000000;
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
	}

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
	}

	public static void SUBWF() {
		Flags.checkFlagDC(fileReg[bankFlag][f], Alu.zweierKomp(w));
		value = Alu.ALU(fileReg[bankFlag][f], d, f, "-");
		Flags.checkFlagsCZ(value, false);

	}

	public static void SWAPF() {
		value = fileReg[bankFlag][f];
		int upper = value & 0b11110000;
		int lower = value & 0b00001111;
		upper = upper >> 4;
		lower = lower << 4;

		value = upper | lower;

		saveData(d, f, value);
	}

	public static void XORWF() {
		value = Alu.ALU(fileReg[bankFlag][f], d, f, "^");
		Flags.checkFlagZ(value);
	}

	//Bit-Oriented Operations
	
	public static void BCF() {

		value = fileReg[bankFlag][f];

		value &= ~(1 << b);

		saveData(1, f, value);

	}

	public static void BSF() {
		value = fileReg[bankFlag][f];

		value |= 1 << b;

		saveData(1, f, value);

	}

	public static void BTFSC() {
		value = fileReg[bankFlag][f];

		if (BigInteger.valueOf(value).testBit(b)) {

		} else {
			NOP();
		}

	}

	public static void BTFSS() {

		value = fileReg[bankFlag][f];
		if (BigInteger.valueOf(value).testBit(b)) {
			NOP();
		} else {

		}
	}
	
	//Literal und Control Operations

	public static void ADDLW() {

		Flags.checkFlagDC(w, k);

		value = Alu.ALU(k, 0, 0, "+");

		Flags.checkFlagsCZ(value, false);
	}

	public static void ANDLW() {

		Flags.checkFlagDC(w, fileReg[bankFlag][f]);

		value = Alu.ALU(k, 0, 0, "&");

		Flags.checkFlagsCZ(value, false);
	}

	
	public static void CALL() {
		Stack.setStack(adresse + 1);

		int pc = 0;
		int pclath = Speicher.getPCLATH();

		pc = (pc | k);
		pclath = (pclath & 0b11000) << 8;

		pc = (pc | pclath);
		Speicher.setPC(pc - 1); // Da der PC nach dem Befehl nochmal erhöht wird, rechnen wir hier -1
	
	}

	public static void CLRWDT() {
		// Affects -TO,-PD Flag
		WatchDogTimer.resetTimer();
		int option = FileRegister.getBankValue(1, 1);
		int status = FileRegister.getBankValue(0, 3);
		FileRegister.setDataInBank(1, 1, option & 0b11110111); // Clear PSA
		FileRegister.setDataInBank(3, status | 0b00011000); // Set -TO and -PD
		
	}

	public static void GOTO_() {
		int pc = 0;
		int pclath = Speicher.getPCLATH();

		pc = (pc | k);
		pclath = (pclath & 0b11000) << 8;

		pc = (pc | pclath);
		Speicher.setPC(pc - 1); // Da der PC nach dem Befehl nochmal erhöht wird, rechnen wir hier -1
		
	}

	
	public static void IORLW() {

		value = Alu.ALU(k, 0, 0, "|");

		Flags.checkFlagZ(value);
	}
	
	
	
	public static void MOVELW() {
		Speicher.setWReg(k);
	}
	
	
	public static void RETFIE() {
		Stack.setStackPointer(stackpointer - 1);
		Speicher.setPC(Stack.getStackValue() - 1);
		FileRegister.setDataInBank(11, FileRegister.getBankValue(0, 11) | 0b10000000); // GIE bit setzen
		
	}

	public static void RETLW() {
		Stack.setStackPointer(stackpointer - 1);
		Speicher.setPC(Stack.getStackValue() - 1);
		saveData(0, 0, k); // K ins W Register schreiben
		
	}

	public static void RETURN_() {
		Stack.setStackPointer(stackpointer - 1);
		Speicher.setPC(Stack.getStackValue());
		
	}

	public static void SLEEP() {
		// Affects -TO,-PD Flag
		sleep = true;
		WatchDogTimer.resetTimer();
		int option = FileRegister.getBankValue(1, 1);
		int status = FileRegister.getBankValue(0, 3);
		FileRegister.setDataInBank(1, 1, option & 0b11110111); // Clear PSA
		FileRegister.setDataInBank(3, status | 0b00010000); // -TO setzen
		FileRegister.setDataInBank(3, status & 0b11110111); // -PD l�schen

		
	}
	
	public static void SUBLW() {
		Flags.checkFlagDC(k, Alu.zweierKomp(w));

		value = Alu.ALU(k, 0, 0, "-");

		Flags.checkFlagsCZ(value, false);
	}
	
	public static void XORLW() {
		value = Alu.ALU(k, 0, 0, "^");
		Flags.checkFlagZ(value);
	}
	

	

	protected static void saveData(int d, int f, int value) {
		if (d == 1) { // Speicher ins File-Register
			MoveToFReg(f, value);
		} else { // Speicher ins W-Register
			Speicher.setWReg(value);
		}
	}

	public static void MoveToFReg(int i, int data) {
		Speicher.setBankFlag();
		MoveToFReg(Speicher.getBankFlag(), i, data);
	}

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
