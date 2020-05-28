package GUI;

import javax.swing.JLabel;

import Speicher.FileRegister;
import Speicher.Speicher;

public class Reloads extends GUI {

	
	
	public static void ReloadGUI() {
		
		Stack();
		
		initializeFileReg();
		
		IRPNumberText = new JLabel(Integer.toString(statusReg[0]));
		RP1NumberText = new JLabel(Integer.toString(statusReg[1]));
		RP0NumberText = new JLabel(Integer.toString(statusReg[2]));
		TONumberText = new JLabel(Integer.toString(statusReg[3]));
		PDNumberText = new JLabel(Integer.toString(statusReg[4]));
		ZNumberText = new JLabel(Integer.toString(statusReg[5]));
		DCNumberText = new JLabel(Integer.toString(statusReg[6]));
		CNumberText = new JLabel(Integer.toString(statusReg[7]));
		
		
		RPUNumberText = new JLabel(Integer.toString(optionReg[0]));
		IEGNumberText = new JLabel(Integer.toString(optionReg[1]));
		TCSNumberText = new JLabel(Integer.toString(optionReg[2]));
		TSENumberText = new JLabel(Integer.toString(optionReg[3]));
		PSANumberText = new JLabel(Integer.toString(optionReg[4]));
		PS2NumberText = new JLabel(Integer.toString(optionReg[5]));
		PS1NumberText = new JLabel(Integer.toString(optionReg[6]));
		PS0NumberText = new JLabel(Integer.toString(optionReg[7]));
		
		
		GIENumberText = new JLabel(Integer.toString(intconReg[0]));
		EIENumberText = new JLabel(Integer.toString(intconReg[1]));
		TIENumberText = new JLabel(Integer.toString(intconReg[2]));
		IENumberText = new JLabel(Integer.toString(intconReg[3]));
		RIENumberText = new JLabel(Integer.toString(intconReg[4]));
		TIFNumberText = new JLabel(Integer.toString(intconReg[5]));
		IFNumberText = new JLabel(Integer.toString(intconReg[6]));
		RIFNumberText = new JLabel(Integer.toString(intconReg[7]));
		
	}	
	
	public static void ReloadAttributes() {
		pc = Speicher.getPC();
		rams = FileRegister.getFReg();

		statusReg = Speicher.getStatusRegister();
		optionReg = Speicher.getOptionReg();
		intconReg = Speicher.getIntconReg();
	}
		
}
