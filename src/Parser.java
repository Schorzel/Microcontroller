package DateiVerarbeitung;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import Speicher.Speicher;



public class Parser {
	
	private String datei;
	
	private static void loadFile(String datei) { //Datei ins programm laden/einlesen
		File file = new File(datei);
	
	
	if(!file.canRead()|| !file.isFile()) { //überprüfen ob Datei exisitiert und gelesen werden kann
		return;
	}
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(datei));
			String zeile=null;
			while((zeile =buffer.readLine())!= null) {
				if(zeile.substring(0,1)==" ") { //Überspringe Zeile wenn sie leer ist
					continue;
				}
				else {
					int befehlsAdresse = Integer.parseInt(zeile.substring(1,4),16); //Befehlsadresse in Programmspeicher auslesen
					int befehlsCode = Integer.parseInt(zeile.substring(6,9),16); //Befehlscode auslesen
					Speicher.setProgrammspeicher(befehlsAdresse,befehlsCode); //mit Befehlsadresse und Befehlscode Befehl im Speicher setten
				}
				}
				buffer.close();
				}
		
			catch(FileNotFoundException e) {
				System.out.println("File not found");
			}
		catch(IOException e) {
			System.out.println("IO Error");
		}
		}
		}
		
	



