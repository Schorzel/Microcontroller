import Funktionen.Functions;
import Speicher.FileRegister;
import Speicher.Speicher;


public class Reset {

	//Register
	private static int[] status;
	private static int[] option;
	private static int[] intcon;
	
	//Tabelle auf Datenblatt S.43
	public static void POR() {
		FileRegister.setDataInBank(2, 0b0000); // PCL
		FileRegister.setDataInBank(3, 0b00011000); // Status
		FileRegister.setDataInBank(10, 0b00000000); // PCLATH
		FileRegister.setDataInBank(11, 0b00000000); // INTCON

		FileRegister.setDataInBank(1, 1, 0b11111111); // Option_Reg
		FileRegister.setDataInBank(1, 5, 0b00011111); // TRISA
		FileRegister.setDataInBank(1, 6, 0b11111111); // TRISB
		FileRegister.setDataInBank(1, 8, 0b00000000); // EECON1

		Speicher.setPC(0);

		Speicher.reload();
	}
	
	public static void normalReset() {
		FileRegister.setDataInBank(2, 0b0000); // PCL
//		FileRegister.setData(3, FileRegister.getValueAt(0, 3) & 0b00000111); // Status Verstehen wir noch nicht
		FileRegister.setDataInBank(10, 0b00000000); // PCLATH
		FileRegister.setDataInBank(11, FileRegister.getBankValue(0, 11) & 0b00000001); // INTCON

		FileRegister.setDataInBank(1, 1, 0b11111111); // Option_Reg
		FileRegister.setDataInBank(1, 5, 0b00011111); // TRISA
		FileRegister.setDataInBank(1, 6, 0b11111111); // TRISB
		FileRegister.setDataInBank(1, 8, 0b00000000); // EECON1 ----- q

	}
	
	public static void sleepReset() {
		FileRegister.setDataInBank(2, Speicher.getPC() + 1);
		FileRegister.setDataInBank(1, 8, FileRegister.getBankValue(1, 8) & 0b00001111); // EECON1
	}
	
	
	public static void MCLR() {
		normalReset();
		if (Functions.isSleep()) {
			FileRegister.setDataInBank(3, (FileRegister.getBankValue(0, 3) & 0b00000111) | 0b00010000); // Status
		} else {
			FileRegister.setDataInBank(3, FileRegister.getBankValue(0, 3) & 0b00011111); // Status
		}
		Speicher.setPC(0);

		Speicher.reload();
	}
	
	public static void reloadRegister() {
		status = Speicher.getStatusRegister();
		option = Speicher.getOptionReg();
		intcon = Speicher.getIntconReg();
	}
}
