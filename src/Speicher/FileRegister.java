package Speicher;

import Speicher.FileRegister;
import Speicher.Speicher;

public class FileRegister {

	// FileRegister
	private static int[] bank0 = new int[128];
	private static int[] bank1 = new int[128];

	// Aus den zwei B�nken wird ein zweidimensionales Array welches den RAM-Speicher
	// darstellt
	public static int[][] getFileReg() {
		int[][] fileReg = { bank0, bank1 };
		return fileReg;
	}

	public static int getValue(int bank, int index) {
		int value;
		if (bank == 0) {
			value = bank0[index];

		} else if (bank == 1) {
			value = bank1[index];

		} else {
			System.out.println("Bank not found");
			value = 0;
		}
		return value;
	}
	
	public static void setData(int bank,int index,int data) {
		if(index < 0x50) {
			if (bank == 0) {
				bank0[index] = data;

			} else if (bank == 1) {
				bank1[index] = data;

			} else {
				System.out.println("Bank not found");
				
			}
		}
		
		else {
			System.out.println("Adresse kann nicht beschrieben werden. Adresse �bersteigt Special Function Register & General Purpose Regiser");
		}
		
		if (index == 2 && (Speicher.getPCL() != data)) {
			int pclath = FileRegister.getValue(0, 10);

			pclath = pclath << 8;

			int pc = pclath | data;
			Speicher.setPC(pc); // Da der PC nach dem Befehl nochmal erhöht wird, rechnen wir hier -1
		}
	}
	
	public static void setData(int index,int data) {
		setData(0,index,data);
		setData(1,index,data);
	}

}
