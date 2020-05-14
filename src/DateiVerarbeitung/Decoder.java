package DateiVerarbeitung;
import Speicher.Speicher;

public class Decoder
{

	
	private static final int MASK_LAST_6 = 0b11111100000000;
    private static final int MASK_LAST_5 = 0b11111000000000;
    private static final int MASK_LAST_4 = 0b11110000000000;
    private static final int MASK_LAST_3 = 0b11100000000000;
    private static final int MASK_7 = 0b00000010000000;
    private static final int MASK_FIRST_7 = 0b00000001111111;
    private static final int MASK_NOP = 0b11111110011111;
    private static final int MASK_PARAM_b = 0b00001110000000;
    private static final int MASK_PARAM_k_long = 0b00011111111111;
	
	
	//HI
	
	
    public static int decodeParameter(int index, String param) {
        int[] programmSpeicher = Speicher.getProgrammspeicher(); 
        int command = programmSpeicher[index]; // Hex Code aus dem ProgrammSpeicher
 
        switch (decodeCommand(index)) {
         
        // Befehle mit Parameter 'd', 'f'
        case "ADDWF":
        case "ANDWF":
        case "COMF":
        case "CLRF":
        case "DECF":
        case "DECFSZ":
        case "INCF":
        case "INCFSZ":
        case "IORWF":
        case "MOVF":
        case "MOVWF":
        case "RLF":
        case "RRF":
        case "SUBWF":
        case "SWAPF":
        case "XORWF":
            if (param.equalsIgnoreCase("d")) {
                return (command & MASK_7) / 128;
            } else if (param.equalsIgnoreCase("f")) {
                return (command & MASK_FIRST_7);
            }
            break;
             
        // Befehle mit Parameter 'b', 'f'
        case "BCF":
        case "BSF":
        case "BTFSC":
        case "BTFSS":
            if (param.equalsIgnoreCase("b")) {
                return (command & MASK_PARAM_b) >> 7;
            } else if (param.equalsIgnoreCase("f")) {
                return (command & MASK_FIRST_7);
            }
            break;
             
        // Befehle mit Parameter 'k' (last 8 bits)
        case "ADDLW":
        case "ANDLW":
        case "IORLW":
        case "MOVLW":
        case "RETLW":
        case "SUBLW":
        case "CORLW":
        case "XORLW":
            return (command & (MASK_FIRST_7 ^ MASK_7));
             
        // Befehle mit Parameter 'k' (last 11 bits)
        case "CALL":
        case "GOTO":
            return (command & MASK_PARAM_k_long);
        }
        return 0; // Fehler oder NOP
    }
    
    
    
    
    
    
    
    public static String decodeCommand(int index)
	{

		int[]programmSpeicher = Speicher.getProgrammspeicher(); 
		int command = programmSpeicher[index];
		
		
		
		if((command & MASK_NOP) == 0b00000000000000) {
			return "NOP";
		}
		
		
		
		switch (command & (MASK_LAST_6 ^ MASK_7)) {
		
		case 0b00_0001_1000_0000: return "CLRF";// CLRF;
		
		case 0b00_0001_0000_0000: return "CLRW";// CLRW;
		
		case 0b00_0000_1000_0000: return "MOVWF";// MOVWF;
		}
		
		
		
		
		switch (command & MASK_LAST_6) {
		case 0b00_0111_0000_0000: return "ADDWF";//ADDWF ; 
		
		case 0b00_0101_0000_0000: return "ANDWF";// ANDWF;
		
		case 0b00_1001_0000_0000: return "COMF";// COMF;
		
		case 0b00_0011_0000_0000: return "DECF";// DECF;
		
		case 0b00_1011_0000_0000: return "DECFSZ";// DECFSZ;
			
		case 0b00_1010_0000_0000: return "INCF";// INCF;
			
		case 0b00_1111_0000_0000: return "INCFSZ";// INCFSZ;
			
		case 0b00_0100_0000_0000: return "IOWF";// IORWF;
			
		case 0b00_1000_0000_0000: return "MOVF";// MOVF;
			
		
			
		case 0b00_1101_0000_0000: return "RLF";// RLF;
		
		case 0b00_1100_0000_0000: return "RRF";// RRF;
		
		case 0b00_0010_0000_0000: return "SUBWF";// SUBWF;
			
		case 0b00_1110_0000_0000: return "SWAPF";// SWAPF;
			
		case 0b00_0110_0000_0000: return "XORWF";// XORWF;
			
		case 0b11_1001_0000_0000: return "ANDLW";// ANDLW;
			
		case 0b11_1000_0000_0000: return "IORLW";// IORLW;
		
		case 0b11_1010_0000_0000: return "XORLW";// XORLW;
			

			
		}
		
		
		switch (command & MASK_LAST_4) {
		case 0b01_0000_0000_0000: return "BCF";// BCF;
			
		case 0b01_0100_0000_0000: return "BSF";// BSF;
			
		case 0b01_1000_0000_0000: return "BTFSC";// BTFSC;
			
		case 0b01_1100_0000_0000: return "BTFSS";// BTFSS;
			
		case 0b11_0000_0000_0000: return "MOVELW";// MOVELW;
			
		case 0b11_0100_0000_0000: return "RETLW";// RETLW;
			

		}
		
		switch (command) {
		case 0b00_0000_0110_0100: return "CLRWDT";// CLRWDT;
			
		case 0b00_0000_0000_1001: return "RETFIE";// RETFIE;
			
		case 0b00_0000_0000_1000: return "RETURN";// RETURN;
			
		case 0b01_1100_0110_0011: return "SLEEP";// SLEEP;
			

		}
		
		
		switch (command & MASK_LAST_5) {
		case 0b11_1110_0000_0000: return "ADDLW";// ADDLW;
			
		case 0b11_1100_0000_0000: return "SUBLW";// SUBLW;
			
		}
		
		switch (command & MASK_LAST_3) {
		case 0b10_0000_0000_0000: return "CALL";// CALL;
			
		case 0b10_1000_0000_0000: return "GOTO";// GOTO;
			
		}

		return "Error";
	}
	
		
		
		
		

	

}
