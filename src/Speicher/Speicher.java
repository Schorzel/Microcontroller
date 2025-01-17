package Speicher;

import java.util.Arrays;

import Laufzeit.Laufzeit;
import Laufzeit.Reset;
import Laufzeit.Timer;
import Laufzeit.WatchDogTimer;

public class Speicher
{

	private static final int[] masks_status = { 0b10000000, 0b01000000,
			0b00100000, 0b00010000, 0b00001000, 0b00000100, 0b00000010,
			0b00000001 };

	private static final int[] masks_set = { 0b00000001, 0b00000010, 0b00000100,
			0b00001000, 0b00010000, 0b00100000, 0b01000000, 0b10000000 };

	private static final int[] masks_clear = { 0b11111110, 0b11111101,
			0b11111011, 0b11110111, 0b11101111, 0b11011111, 0b10111111,
			0b01111111 };

	private static final int return_mask = 0b11111111;

	// Programmspeicher
	private static int[] programmspeicher = new int[1024];

	// Flags & Registers
	private static int PCL;

	private static int PCLATH;

	private static int PC; // Programmz�hler

	private static int wReg;

	private static int bankFlag;

	// Status Register
	private static int[] statusRegister = new int[8];
	// [0]=IRP, [1]=RP1, [2]=RP0, [3]=(NOT)TO, [4]=(NOT)PD, [5]=Z, [6]=DC, [7]=C

	// Register File Summary
	private static int[] optionReg = new int[8];

	private static int[] intconReg = new int[8];

	private static int[] tristPinA = new int[8];

	private static int[] tristPinB = new int[8];

	private static int[] portPinA = new int[8];

	private static int[] portPinB = new int[8];

	// Break Points
	private static boolean[] breakPoints = new boolean[1024];

	// Getter

	public static int[] getMasksStatus()
	{
		return masks_status;
	}

	public static int[] getMasksSet()
	{
		return masks_set;
	}

	public static int[] getMasksClear()
	{
		return masks_clear;
	}

	public static int getPCL()
	{
		return PCL;
	}

	public static int getPCLATH()
	{
		return PCLATH;
	}

	public static int getPC()
	{
		return PC;
	}

	public static int getwReg()
	{
		return wReg;
	}

	public static int getBankFlag()
	{
		return bankFlag;
	}

	public static int[] getStatusRegister()
	{
		return statusRegister;
	}

	public static int[] getOptionReg()
	{
		return optionReg;
	}

	public static int[] getIntconReg()
	{
		return intconReg;
	}

	public static int[] getTristPinA()
	{
		return tristPinA;
	}

	public static int[] getTristPinB()
	{
		return tristPinB;
	}

	public static int[] getPortPinA()
	{
		return portPinA;
	}

	public static int[] getPortPinB()
	{
		return portPinB;
	}

	public static int[] getProgrammspeicher()
	{
		return programmspeicher;
	}

	// Setter

	// Programmspeicher(Wird mit Parser ausgelesen und dann mit set gesetzt)
	public static void setProgrammspeicher(int adresse, int befehlscode)
	{

		programmspeicher[adresse] = befehlscode;
	}

	//Erstellt einen Breakpoint an der entsprechenden Stelle im Programmspeicher
	public static void setBreakPoint(int pc)
	{
		if (breakPoints[pc] != true) {
			breakPoints[pc] = true;
		} else {
			breakPoints[pc] = false;
		}
	}

	//Holt den Wert des Bankflags aus dem Statusregister. 0 = Bank0, 1 = Bank1
	public static void setBankFlag()
	{
		bankFlag = statusRegister[2];
	}

	public static void setPC(int value)
	{
		PC = value;
		reloadPCL();
	}

	public static void setWReg(int value)
	{
		wReg = value;
	}

	//Setzt das entsprechende A IO oder B IO  Bit auf 0 oder 1
	public static void setPinsIO(int AB, int bit, int state)
	{
		if (AB == 0) {
			portPinA[bit] = state;
			
			String value = "";
	
			for(int i = 3;i < 8;i++) {
				value += Integer.toString(portPinA[i]);
			}

			FileRegister.setDataInBank(0, 5, Integer.parseInt(value,2));
			

			
		} else {
			portPinB[bit] = state;
			
			String value = "";

			for(int i = 0;i < 8;i++) {
				value += Integer.toString(portPinB[i]);
			}
			
			FileRegister.setDataInBank(0, 6, Integer.parseInt(value,2));
		}
	}

	//Setzt das entsprechende A  oder B Bit auf 0 oder 1
	public static void setPins(int AB, int bit, int state)
	{
		if (AB == 0) {
			tristPinA[bit] = state;
			
			String value = "";

			for(int i = 3;i < 8;i++) {
				value += Integer.toString(tristPinA[i]);
			}

			FileRegister.setDataInBank(1, 5, Integer.parseInt(value,2));
		} else {
			tristPinB[bit] = state;
			
			String value = "";

			for(int i = 0;i < 8;i++) {
				value += Integer.toString(tristPinB[i]);
			}
	
			FileRegister.setDataInBank(1, 6, Integer.parseInt(value,2));
		}
	}

	//L�dt das �bergeben Regiser neu
	public static void reloadArrayRegister(String command)
	{

		int[] data = new int[8];
		int bank = -1;
		int adresse = -1;

		switch (command) {
		case "status":
			bank = bankFlag;
			adresse = 3;
			break;

		case "option":
			bank = 1;
			adresse = 1;
			break;

		case "intcon":
			bank = 0;
			adresse = 11;
			break;

		case "tristPinA":
			bank = 1;
			adresse = 5;
			break;

		case "tristPinB":
			bank = 1;
			adresse = 6;
			break;

		case "portPinA":
			bank = 0;
			adresse = 5;
			break;

		case "portPinB":
			bank = 0;
			adresse = 6;
			break;

		}

		if (bank < 0 || adresse < 0) {
			System.out.println("Fehler: Falscher Befehl");
		} else {
			for (int i = 0; i < masks_status.length; i++) {
				data[i] = (FileRegister.getBankValue(bank, adresse)
						& masks_status[i]);

				if (data[i] != 0) {
					data[i] = 1;
				}
			}
		}

		switch (command) {
		case "status":
			statusRegister = data;
			bankFlag = statusRegister[2];
			break;

		case "option":
			optionReg = data;
			break;

		case "intcon":
			intconReg = data;
			break;

		case "tristPinA":
			tristPinA = data;
			break;

		case "tristPinB":
			tristPinB = data;
			break;

		case "portPinA":
			portPinA = data;
			break;

		case "portPinB":
			portPinB = data;
			break;

		}

	}

	public static void reloadPCL()
	{
		PCL = (PC & return_mask);
		FileRegister.setDataInBank(2, PCL);
	}

	//Setzt alles auf Null bzw auf den POR(Power on Reset) Wert
	public static void reset()
	{
		new Laufzeit();
		new Timer();
		new WatchDogTimer();
		new FileRegister();
		new Stack();
		PCL = 0;
		PCLATH = 0;
		PC = 0;
		wReg = 0;
		bankFlag = 0;

		for (int i = 0; i < programmspeicher.length; i++) {
			programmspeicher[i] = 0;
		}

		for (int i = 0; i < breakPoints.length; i++) {
			breakPoints[i] = false;
		}

		Reset.POR();
		reload();
	}

	public static void reload()
	{
		reloadArray();

		reloadPCL();
		PCLATH = FileRegister.getBankValue(0, 10);

	}

	//L�dt alle Register neu
	public static void reloadArray()
	{
		reloadArrayRegister("status");
		setBankFlag();

		reloadArrayRegister("option");
		reloadArrayRegister("intcon");

		reloadArrayRegister("tristPinA");
		reloadArrayRegister("tristPinB");
		reloadArrayRegister("portPinA");
		reloadArrayRegister("portPinB");
	}

}
