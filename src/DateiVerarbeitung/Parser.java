package DateiVerarbeitung;
import Speicher.Speicher;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;




public class Parser {
	private String file;

	//Gibt die Datei als String zurück
	public String getFile() {
		return file;
	}
	
	//Stellt aktuelle Datei ein
	public void setFile(String file) {
		this.file = file;
	}

	//Liesst die Datei ein und zieht die wichtigen Informationen raus
	public void read() {
		Speicher.reset();
		String line;
		
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while ((line = bufferedReader.readLine()) != null) {
				
				if (line.substring(0, 1).equals(" ")) { // Wenn die erste Stelle ein Leerzeichen ist überspringe die Zeile
					
					continue;
				} else {
					
					int adress = Integer.parseInt(line.substring(1, 4), 16); // Adresse auslesen und speichern.
					Speicher.setProgrammspeicher(adress, Integer.parseInt(line.substring(5, 9), 16)); // Befehlscode auslesen und speichern beim index (adresse)
				}
				
			}
			bufferedReader.close();
			
		} catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file '" + file + "'");
		} catch(IOException ex) {
	            System.out.println("Error reading file '" + file + "'");                  
	    }
	}
}
