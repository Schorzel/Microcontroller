package Funktionen;

import Speicher.Speicher;

public class Alu extends Functions {
		
	
	//Funktion zum bilden eines Zweierkomplements	
	public static int zweierKomp(int value) {
		value = value ^ 0b11111111;
		value++;
		value = value& 0x00FF;
		return value;
	}
	
	
	//ALU Methode welche alle arithemetischen Funktionen abdeckt
	//v1 = 1. Wert, v2 = 2. Wert, d = Parameter des Befehlscode(Bit7), f = parameter des befehscode(6-0),op=Rechenoperator;
	public static int ALU(int v1,int v2,int d,int f, String op) {
		
		int value = 0;
		 if (op.equals("&")) {
	            value = v2 & v1;
	        } else if (op.equals("|")) { //OR
	            value = v2 | v1;
	        } else if (op.equals("^")) { //XOR
	            value = v2 ^ v1;
	        } else if (op.equals("+")) {
	            value = v2 + v1;
	        } else if (op.equals("-")) {
	            value = v1 + zweierKomp(v2);
	        } else {
	            value = v1;
	        }
	 
	        if (value > 255) {
	            value -= 256;
	            cFlag = true;
	        }
	         
	        saveData(d, f, value); // Speichern der Daten
	 
	        return value;
	    }
	
	//Berechnung mit dem W-Register
	public static int ALU(int v1, int d, int f, String op) {
		return ALU(v1, Speicher.getwReg(), d, f, op);
	}
	
	
		
	
	}

