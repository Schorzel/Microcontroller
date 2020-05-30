package GUI;

import Speicher.FileRegister;
import Speicher.Speicher;
import Speicher.Stack;

public class Reloads extends GUI
{

	public static void ReloadGUI()
	{

		reloadStack();

		reloadPins();

		initializeFileReg();

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

		wRegLabel.setText(Integer.toHexString(Speicher.getwReg()));
	}

	public static void ReloadAttributes()
	{
		pc = Speicher.getPC();
		rams = FileRegister.getFReg();

		statusReg = Speicher.getStatusRegister();
		optionReg = Speicher.getOptionReg();
		intconReg = Speicher.getIntconReg();
	}

	public static void reloadStack()
	{
		int[] stackArray = Stack.getStack();

		for (int i = 0; i < 8; i++) {
			stack.setValueAt(Integer.toHexString(stackArray[i]), i, 1);
		}
	}

	public static void reloadPins()
	{
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

	}

}