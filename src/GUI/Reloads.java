package GUI;

import java.io.File;

import javax.swing.JTable;

import DateiVerarbeitung.Parser;
import Laufzeit.Laufzeit;
import Laufzeit.Timer;
import Laufzeit.WatchDogTimer;
import Speicher.FileRegister;
import Speicher.Speicher;
import Speicher.Stack;

public class Reloads extends GUI {

	public static void ReloadGUI() {
		
		
		
		
		
		
		
		reloadStack();

		reloadPins();

		initializeFileReg();

		setMarker();
		
		reloadTimer();
		
		GUI.lstFileTable();

		IRPNumberText.setText(Integer.toString(statusReg[0]));
		RP1NumberText.setText(Integer.toString(statusReg[1]));
		RP0NumberText.setText(Integer.toString(statusReg[2]));
		TONumberText.setText(Integer.toString(statusReg[3]));
		PDNumberText.setText(Integer.toString(statusReg[4]));
		ZNumberText.setText(Integer.toString(statusReg[5]));
		DCNumberText.setText(Integer.toString(statusReg[6]));
		CNumberText.setText(Integer.toString(statusReg[7]));

		RPUNumberText.setText(Integer.toString(optionReg[0]));
		IEGNumberText.setText(Integer.toString(optionReg[1]));
		TCSNumberText.setText(Integer.toString(optionReg[2]));
		TSENumberText.setText(Integer.toString(optionReg[3]));
		PSANumberText.setText(Integer.toString(optionReg[4]));
		PS2NumberText.setText(Integer.toString(optionReg[5]));
		PS1NumberText.setText(Integer.toString(optionReg[6]));
		PS0NumberText.setText(Integer.toString(optionReg[7]));

		GIENumberText.setText(Integer.toString(intconReg[0]));
		EIENumberText.setText(Integer.toString(intconReg[1]));
		TIENumberText.setText(Integer.toString(intconReg[2]));
		IENumberText.setText(Integer.toString(intconReg[3]));
		RIENumberText.setText(Integer.toString(intconReg[4]));
		TIFNumberText.setText(Integer.toString(intconReg[5]));
		IFNumberText.setText(Integer.toString(intconReg[6]));
		RIFNumberText.setText(Integer.toString(intconReg[7]));
		
		optionValue.setText(Integer.toHexString(rams[1][1]));
		
		statusValue.setText(Integer.toHexString(rams[1][3]));
		
		intconValue.setText(Integer.toHexString(rams[1][11]));

		wRegLabel.setText(Integer.toHexString(Speicher.getwReg()));
		fsrValue.setText(Integer.toHexString(rams[0][4]));
		pclValue.setText(Integer.toHexString(Speicher.getPCL()));
		pclathValue.setText(Integer.toHexString(Speicher.getPCLATH()));
		pcValue.setText(Integer.toHexString(Speicher.getPC()));
		
		
		
		
		
		
	}

	public static void ReloadAttributes() {
		pc = Speicher.getPC();
		rams = FileRegister.getFReg();

		statusReg = Speicher.getStatusRegister();
		optionReg = Speicher.getOptionReg();
		intconReg = Speicher.getIntconReg();
	}

	public static void reloadStack() {
		int[] stackArray = Stack.getStack();

		for (int i = 0; i < 8; i++) {
			stack.setValueAt(Integer.toHexString(stackArray[i]), i, 1);
		}
	}

	public static void reloadPins() {
		int[] portPinA = Speicher.getPortPinA();
		int[] portPinB = Speicher.getPortPinB();
		int[] tristPinA = Speicher.getTristPinA();
		int[] tristPinB = Speicher.getTristPinB();

		pinRA0IO.setSelected(portPinA[7] == 1);
		pinRA1IO.setSelected(portPinA[6] == 1);
		pinRA2IO.setSelected(portPinA[5] == 1);
		pinRA3IO.setSelected(portPinA[4] == 1);
		pinRA4IO.setSelected(portPinA[3] == 1);

		pinRB0IO.setSelected(portPinB[7] == 1);
		pinRB1IO.setSelected(portPinB[6] == 1);
		pinRB2IO.setSelected(portPinB[5] == 1);
		pinRB3IO.setSelected(portPinB[4] == 1);
		pinRB4IO.setSelected(portPinB[3] == 1);
		pinRB5IO.setSelected(portPinB[2] == 1);
		pinRB6IO.setSelected(portPinB[1] == 1);
		pinRB7IO.setSelected(portPinB[0] == 1);

		pinRA0.setSelected(tristPinA[7] == 1);
		pinRA1.setSelected(tristPinA[6] == 1);
		pinRA2.setSelected(tristPinA[5] == 1);
		pinRA3.setSelected(tristPinA[4] == 1);
		pinRA4.setSelected(tristPinA[3] == 1);

		pinRB0.setSelected(tristPinB[7] == 1);
		pinRB1.setSelected(tristPinB[6] == 1);
		pinRB2.setSelected(tristPinB[5] == 1);
		pinRB3.setSelected(tristPinB[4] == 1);
		pinRB4.setSelected(tristPinB[3] == 1);
		pinRB5.setSelected(tristPinB[2] == 1);
		pinRB6.setSelected(tristPinB[1] == 1);
		pinRB7.setSelected(tristPinB[0] == 1);

		pinRA0IO.setEnabled(pinRA0.isSelected());
		pinRA1IO.setEnabled(pinRA1.isSelected());
		pinRA2IO.setEnabled(pinRA2.isSelected());
		pinRA3IO.setEnabled(pinRA3.isSelected());
		pinRA4IO.setEnabled(pinRA4.isSelected());

		pinRB0IO.setEnabled(pinRB0.isSelected());
		pinRB1IO.setEnabled(pinRB1.isSelected());
		pinRB2IO.setEnabled(pinRB2.isSelected());
		pinRB3IO.setEnabled(pinRB3.isSelected());
		pinRB4IO.setEnabled(pinRB4.isSelected());
		pinRB5IO.setEnabled(pinRB5.isSelected());
		pinRB6IO.setEnabled(pinRB6.isSelected());
		pinRB7IO.setEnabled(pinRB7.isSelected());

	}
	
	public static void reloadTimer() {
		

		laufzeitValue.setText(Integer.toString(Laufzeit.getLaufzeitzaehler()));
		frequenzValue.setText(Integer.toString(Laufzeit.getFrequenz()));
		timerValue.setText(Integer.toString(Timer.getTimer()));
		timerMaxValue.setText(Integer.toString(Timer.getTimerMax()));
		watchDogTimerValue.setText(Integer.toString(WatchDogTimer.getWatchdogTimer()));
		watchDogTimerMaxValue.setText(Integer.toString(WatchDogTimer.getMaxTime()));
	}

	public static void setMarker() {


		for (int i = 0; i < lstFile.getRowCount(); i++) {
			String row = (String) lstFile.getValueAt(i, 1);
			if (row != null) {

				if (!row.equals("    ")) {

					if (Speicher.getPC() == Integer.parseInt(row, 16)) {
						lstFile.setRowSelectionInterval(i, i);
					}
				}
			}
		}

	}
}
