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
	//p1 = 1. Rechenoperand,w=2.Rechenoperand,d = Paramater d des Befehlscode(Bit7),f== param.f befehscode(6-0),op=operator;
	public static int ALU(int p1,int w,int d,int f, String op) {
		
		int value = 0;
		 if (op.equals("&")) {
	            value = w & p1;
	        } else if (op.equals("|")) { //OR
	            value = w | p1;
	        } else if (op.equals("^")) { //XOR
	            value = w ^ p1;
	        } else if (op.equals("+")) {
	            value = w + p1;
	        } else if (op.equals("-")) {
	            value = p1 + zweierKomp(w);
	        } else {
	            value = p1;
	        }
	 
	        if (value > 255) {
	            value -= 256;
	            cFlag = true;
	        }
	         
	        saveData(d, f, value); // Speichern der Daten
	 
	        return value;
	    }
	
	
	public static int ALU(int p1, int d, int f, String op) {
		return ALU(p1, Speicher.getwReg(), d, f, op);
	}
	
	
		
	
	}

