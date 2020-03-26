package Funktionen;

import Speicher.FileRegister;
import Speicher.Speicher;
import Funktionen.Functions;

public class Flags extends Functions {

	public static int checkFlagC(int value, boolean subtraktion) {
		if (cFlag) {
			value += 256;
			cFlag = false;
		}
		
		// Carry Flag
		if (value > 255) {
			setFlag(0, 1);
		} else {
			setFlag(0, 0);
		}

		// Sonderregeln bei Subtraktionen
		if (subtraktion) {
			if (value >= 0) {
				setFlag(0, 1);
			} else {
				setFlag(0, 0);
			}
		}

		return value;
	}
	
	
	public static void checkFlagZ(int value) {
		// Z Flag
		if (value == 0) {
			setFlag(2, 1);
		} else {
			setFlag(2, 0);
		}
	}
	
	
	
	public static void checkFlagDC(int valueFirst, int valueSecond) {
		// DC Flag
		valueFirst = valueFirst & 0b00001111;
		valueSecond = valueSecond & 0b00001111;
		
		int value = valueFirst + valueSecond;
		
		if (value >= 16) {
			setFlag(1, 1);
		} else {
			setFlag(1, 0);
		}
	}
	
	public static void checkFlagsCZ(int value, boolean subtraktion) {
		value = checkFlagC(value, subtraktion);
		checkFlagZ(value);
	}
	
	public static void setFlag(int flag, int status) {
		int[][] rams = FileRegister.getFReg();

		
		if (flag == 0) { // C-Flag
			System.out.println("    -Flag C set to: " + status);
		} else if (flag == 1) { // DC-Flag
			System.out.println("    -Flag DC set to: " + status);
		} else { // Z-Flag
			System.out.println("    -Flag Z set to: " + status);
		}
		
		if (status == 0) { // Reset Flag
			if (flag == 0) { // C-Flag
				MoveToFReg(0, 3, (rams[0][3] & 0b11111110));
				MoveToFReg(1, 3, (rams[1][3] & 0b11111110));
			} else if (flag == 1) { // DC-Flag
				MoveToFReg(0, 3, (rams[0][3] & 0b11111101));
				MoveToFReg(1, 3, (rams[1][3] & 0b11111101));
			} else { // Z-Flag
				MoveToFReg(0, 3, (rams[0][3] & 0b11111011));
				MoveToFReg(1, 3, (rams[1][3] & 0b11111011));
			}
		} else { // Set Flag
			if (flag == 0) { // C-Flag
				MoveToFReg(0, 3, (rams[0][3] | 0b00000001));
				MoveToFReg(1, 3, (rams[1][3] | 0b00000001));
			} else if (flag == 1) { // DC-Flag
				MoveToFReg(0, 3, (rams[0][3] | 0b00000010));
				MoveToFReg(1, 3, (rams[1][3] | 0b00000010));
			} else { // Z-Flag
				MoveToFReg(0, 3, (rams[0][3] | 0b00000100));
				MoveToFReg(1, 3, (rams[1][3] | 0b00000100));
			}
		}

		Speicher.reloadArrayRegister("status");
	}
	
	
}
