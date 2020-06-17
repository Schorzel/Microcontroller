package Speicher;

import Laufzeit.Timer;
import Speicher.FileRegister;
import Speicher.Speicher;

public class FileRegister {

	// FileRegister
	private static int[] bank0 = new int[128];
	private static int[] bank1 = new int[128];

		
	public FileRegister() {
		for (int i = 0; i < bank0.length; i++) {
			bank0[i] = 0;
			bank1[i] = 0;
		}
	}
	
		
	
	// Aus den zwei Bänken wird ein zweidimensionales Array 
	public static int[][] getFReg() {
		int[][] fileReg = { bank0, bank1 };
		return fileReg;
	}

	//Holt den Wert aus der angegebenen Bank und an dem entsprechenden Index
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
	
	//Speichert einen Wert in der angegebenen Bank an dem entsprechenden Index
	public static void setDataInBank(int bank,int index,int data) {
		if(index < 0x50) {
			if (bank == 0) {
				bank0[index] = data;
				
				if (index == 1) {
					Timer.setTimer(data);
				}
				
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
		Speicher.reloadArray();
	}
	
	//Speichert einen Wert in beiden Bänken an dem entsprechenden Index
	public static void setDataInBank(int index,int data) {
		setDataInBank(0,index,data);
		setDataInBank(1,index,data);
	}
}
