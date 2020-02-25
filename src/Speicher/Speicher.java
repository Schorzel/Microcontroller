package Speicher;

import Speicher.FileRegister;

public class Speicher {

	
	private static final int[] masks_status = {0b10000000, 0b01000000, 0b00100000, 0b00010000, 0b00001000, 0b00000100, 0b00000010, 0b00000001}; 
	private static final int[] masks_set = {0b00000001, 0b00000010, 0b00000100, 0b00001000, 0b00010000, 0b00100000, 0b01000000, 0b10000000};
	private static final int[] masks_clear = {0b11111110, 0b11111101, 0b11111011, 0b11110111, 0b11101111, 0b11011111, 0b10111111, 0b01111111};
	
	
	private static final int return_mask = 0b11111111;
	
	
	
	// Programmspeicher
	private static int[] programmspeicher = new int[1024];

	// Flags & Registers
	private static int PCL;
	private static int PCLATH;
	private static int PC;
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
	
	//Break Points
	private static boolean[] breakPoints = new boolean[1024];

	
	//Getter
	
	public static int[] getMasksStatus() {
		return masks_status;
	}

	public static int[] getMasksSet() {
		return masks_set;
	}

	public static int[] getMasksClear() {
		return masks_clear;
	}

	public static int getPCL() {
		return PCL;
	}

	public static int getPCLATH() {
		return PCLATH;
	}

	public static int getPC() {
		return PC;
	}

	public static int getwReg() {
		return wReg;
	}

	public static int getBankFlag() {
		return bankFlag;
	}

	public static int[] getStatusRegister() {
		return statusRegister;
	}

	public static int[] getOptionReg() {
		return optionReg;
	}

	public static int[] getIntconReg() {
		return intconReg;
	}

	public static int[] getTristPinA() {
		return tristPinA;
	}

	public static int[] getTristPinB() {
		return tristPinB;
	}

	public static int[] getPortPinA() {
		return portPinA;
	}

	public static int[] getPortPinB() {
		return portPinB;
	}

	public static int[] getProgrammspeicher() {
		return programmspeicher;
	}
	
	// Setter 
	
	//Programmspeicher(Wird mit Parser ausgelesen und dann mit set gesetzt)
	public static void setProgrammspeicher(int adresse, int befehlscode) {

		programmspeicher[adresse] = befehlscode;
	}
	
	//Break Points Setter
	public static void setBreakPoint(int pc)
	{
		if (breakPoints[pc] != true) {
			breakPoints[pc] = true;
		} else {
			breakPoints[pc] = false;
		}
	}
	

	public static void reloadArrayRegister(String command) {

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
		
		if(bank < 0 || adresse < 0) {
			System.out.println("Fehler: Falscher Befehl");
		}else {
			for(int i = 0;i < masks_status.length;i++) {
				data[i] = (FileRegister.getValue(bank, adresse) & masks_status[i]);
				
				if(data[i] != 0) {
					data[i] = data[i] / data[i];
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
		FileRegister.setData(2, PCL);
	}
	
	public static void reset()
	{
		//new Timer();
		//new Watchdog();
		new FileRegister();
		new Stack();
		PCL = 0;
		PCLATH = 0;
		PC = 0;
		wReg = 0;
		bankFlag = 0;
		// GIE_Read_only = false;

		for (int i = 0; i < programmspeicher.length; i++) {
			programmspeicher[i] = 0;
		}

		for (int i = 0; i < breakPoints.length; i++) {
			breakPoints[i] = false;
		}

		//Reset.POR();
		//reload();
	}
	
	
}
