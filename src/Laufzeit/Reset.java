package Laufzeit;

import Funktionen.Functions;
import Speicher.FileRegister;
import Speicher.Speicher;

public class Reset
{

	// Register
	private static int[] status;

	private static int[] option;

	private static int[] intcon;

	//Setzt die Werte auf die Eingangswerte wenn der Microcontroller eingeschalten wird 
	public static void POR()
	{
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

	
	public static void normalReset()
	{
		FileRegister.setDataInBank(2, 0b0000); // PCL
		// FileRegister.setData(3, FileRegister.getValueAt(0, 3) & 0b00000111); //
		FileRegister.setDataInBank(10, 0b00000000); // PCLATH
		FileRegister.setDataInBank(11,
		FileRegister.getBankValue(0, 11) & 0b00000001); // INTCON

		FileRegister.setDataInBank(1, 1, 0b11111111); // Option_Reg
		FileRegister.setDataInBank(1, 5, 0b00011111); // TRISA
		FileRegister.setDataInBank(1, 6, 0b11111111); // TRISB
		FileRegister.setDataInBank(1, 8, 0b00000000); // EECON1 ----- q

	}

	//Reset bei einem Sleep
	public static void sleepReset()
	{
		//FileRegister.setDataInBank(2, Speicher.getPC() + 1);
		//Speicher.setPC(Speicher.getPC()+1);
		FileRegister.setDataInBank(1, 8, FileRegister.getBankValue(1, 8) & 0b00001111); // EECON1
	}

	//Reset des Microcontrollers
	public static void MCLR()
	{
		normalReset();
		if (Functions.isSleep()) {
			FileRegister.setDataInBank(3, (FileRegister.getBankValue(0, 3) & 0b00000111) | 0b00010000); // Status
		} else {
			FileRegister.setDataInBank(3, FileRegister.getBankValue(0, 3) & 0b00011111); // Status
		}
		Speicher.setPC(0);

		Speicher.reload();
	}

	//L�dt das Status, Option und Intcon Register neu
	public static void reloadRegister()
	{
		status = Speicher.getStatusRegister();
		option = Speicher.getOptionReg();
		intcon = Speicher.getIntconReg();
	}

	//Falls ein Reset durch den Watchdogtimer ausgel�st wird. Falls Sleep true ist wird die "sleepReset" Methode aufgerufen andernfalls wird die "normalReset" Methode aufgerufen
	public static void WDTReset()
	{
		GUI.GUI.stop();
		WatchDogTimer.resetTimer();

		if (Functions.isSleep()) {
			Functions.setSleep(false);
			sleepReset();
			FileRegister.setDataInBank(3, FileRegister.getBankValue(0, 3) & 0b11100111);
			// Speicher.setPC(Speicher.getPC() + 1);
		} else {
			normalReset();
			FileRegister.setDataInBank(3,
					(FileRegister.getBankValue(0, 3) & 0b00000111) | 1 << 4);
			Speicher.setPC(0);
		}

	}

	//F�hrt einen Sleep Reset durch und �berpr�ft ob es ein "Wake-Up from Sleep" ist wenn das der Fall ist, wird �berpr�ft ob das GIE bit gesetzt ist falls ja wird der PC auf 4 gesetzt
	public static void Interrupt()
	{
		sleepReset();
		if (Functions.isSleep()) {
			Functions.setSleep(false);
			if ((FileRegister.getBankValue(0, 11) & 0b10000000) == 0b10000000) {
				Speicher.setPC(4);
			} else {
				Speicher.setPC(Speicher.getPC() + 1);
			}

			FileRegister.setDataInBank(3,
					(FileRegister.getBankValue(0, 3) & 0b11100111) | 0b00010000); // Status
		}
	}
}
