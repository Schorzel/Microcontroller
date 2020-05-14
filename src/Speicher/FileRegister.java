package Speicher;

import Speicher.FileRegister;
import Speicher.Speicher;

public class FileRegister {

	// FileRegister
	private static int[] bank0 = new int[128];
	private static int[] bank1 = new int[128];

	// Aus den zwei Bänken wird ein zweidimensionales Array welches den RAM-Speicher
	// darstellt
	public static int[][] getFReg() {
		int[][] fileReg = { bank0, bank1 };
		return fileReg;
	}

	public static int getBankValue(int bank, int index) {
		int value;
		if (bank == 0) {
			value = bank0[index];

		} else if (bank == 1) {
			value = bank1[index];

		} else {
			System.out.println("Bank nicht gefunden");
			value = 0;
		}
		return value;
	}
	
	public static void setDataInBank(int bank,int index,int data) {
		if(index < 0x50) {
			if (bank == 0) {
				bank0[index] = data;

			} else if (bank == 1) {
				bank1[index] = data;

			} else {
				System.out.println("Bank nicht gefunden");
				
			}
		}
		
		else {
			System.out.println("Adresse kann nicht beschrieben werden. Adresse übersteigt Special Function Register & General Purpose Register");
		}
		
		if (index == 2 && (Speicher.getPCL() != data)) {
			int pclath = FileRegister.getBankValue(0, 10);

			pclath = pclath << 8;

			int pc = pclath | data;
			Speicher.setPC(pc); 
		}
	}
	
	public static void setDataInBank(int index,int data) {
		setDataInBank(0,index,data);
		setDataInBank(1,index,data);
	}

}
