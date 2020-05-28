package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import GUI.GUI;
import Speicher.Speicher;

public class ActionHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		JCheckBox checkbox = (JCheckBox) event.getSource();

		int[] pins = Speicher.getPortPinA();
		int[] pinsIO = Speicher.getTristPinA();

		if (checkbox == GUI.pinRA0) {
			if (checkbox.isSelected()) {
				Speicher.setPins(0, 0, 1);
				GUI.pinRA0IO.setEnabled(true);

				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(0, 0, 0);
				Speicher.setPinsIO(0, 0, 0);
				GUI.pinRA0IO.setSelected(false);
				GUI.pinRA0IO.setEnabled(false);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);

			}
		}
		if (checkbox == GUI.pinRA0IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(0, 0, 1);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(0, 0, 0);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
		}
		
		if (checkbox == GUI.pinRA1) {
			if (checkbox.isSelected()) {
				Speicher.setPins(0, 1, 1);
				GUI.pinRA1IO.setEnabled(true);

				System.out.println("Pins: " + pins[1]);
				System.out.println("IO: " + pinsIO[1]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(0, 1, 0);
				Speicher.setPinsIO(0, 1, 0);
				GUI.pinRA1IO.setSelected(false);
				GUI.pinRA1IO.setEnabled(false);
				System.out.println("Pins: " + pins[1]);
				System.out.println("IO: " + pinsIO[1]);

			}
		}
		if (checkbox == GUI.pinRA1IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(0, 1, 1);
				System.out.println("Pins: " + pins[1]);
				System.out.println("IO: " + pinsIO[1]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(0, 1, 0);
				System.out.println("Pins: " + pins[1]);
				System.out.println("IO: " + pinsIO[1]);
			}
		}
		
		if (checkbox == GUI.pinRA2) {
			if (checkbox.isSelected()) {
				Speicher.setPins(0, 2, 1);
				GUI.pinRA2IO.setEnabled(true);

				System.out.println("Pins: " + pins[2]);
				System.out.println("IO: " + pinsIO[2]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(0, 2, 0);
				Speicher.setPinsIO(0, 2, 0);
				GUI.pinRA2IO.setSelected(false);
				GUI.pinRA2IO.setEnabled(false);
				System.out.println("Pins: " + pins[2]);
				System.out.println("IO: " + pinsIO[2]);

			}
		}
		if (checkbox == GUI.pinRA2IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(0, 2, 1);
				System.out.println("Pins: " + pins[2]);
				System.out.println("IO: " + pinsIO[2]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(0, 2, 0);
				System.out.println("Pins: " + pins[2]);
				System.out.println("IO: " + pinsIO[2]);
			}
		}
		
		if (checkbox == GUI.pinRA3) {
			if (checkbox.isSelected()) {
				Speicher.setPins(0, 3, 1);
				GUI.pinRA3IO.setEnabled(true);

				System.out.println("Pins: " + pins[3]);
				System.out.println("IO: " + pinsIO[3]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(0, 3, 0);
				Speicher.setPinsIO(0, 3, 0);
				GUI.pinRA3IO.setSelected(false);
				GUI.pinRA3IO.setEnabled(false);
				System.out.println("Pins: " + pins[3]);
				System.out.println("IO: " + pinsIO[3]);

			}
		}
		if (checkbox == GUI.pinRA3IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(0, 3, 1);
				System.out.println("Pins: " + pins[3]);
				System.out.println("IO: " + pinsIO[3]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(0, 3, 0);
				System.out.println("Pins: " + pins[3]);
				System.out.println("IO: " + pinsIO[3]);
			}
		}
		
		if (checkbox == GUI.pinRA4) {
			if (checkbox.isSelected()) {
				Speicher.setPins(0, 4, 1);
				GUI.pinRA4IO.setEnabled(true);

				System.out.println("Pins: " + pins[4]);
				System.out.println("IO: " + pinsIO[4]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(0, 4, 0);
				Speicher.setPinsIO(0, 4, 0);
				GUI.pinRA4IO.setSelected(false);
				GUI.pinRA4IO.setEnabled(false);
				System.out.println("Pins: " + pins[4]);
				System.out.println("IO: " + pinsIO[4]);

			}
		}
		if (checkbox == GUI.pinRA4IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(0, 4, 1);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(0, 4, 0);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
		}
		
		if (checkbox == GUI.pinRB0) {
			if (checkbox.isSelected()) {
				Speicher.setPins(1, 0, 1);
				GUI.pinRB0IO.setEnabled(true);

				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(1, 0, 0);
				Speicher.setPinsIO(1, 0, 0);
				GUI.pinRB0IO.setSelected(false);
				GUI.pinRB0IO.setEnabled(false);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);

			}
		}
		if (checkbox == GUI.pinRB0IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(1, 0, 1);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(1, 0, 0);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
		}
		
		if (checkbox == GUI.pinRB1) {
			if (checkbox.isSelected()) {
				Speicher.setPins(1, 1, 1);
				GUI.pinRB1IO.setEnabled(true);

				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(1, 1, 0);
				Speicher.setPinsIO(1, 1, 0);
				GUI.pinRB1IO.setSelected(false);
				GUI.pinRB1IO.setEnabled(false);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);

			}
		}
		if (checkbox == GUI.pinRB1IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(1, 1, 1);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(1, 1, 0);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
		}
		
		if (checkbox == GUI.pinRB2) {
			if (checkbox.isSelected()) {
				Speicher.setPins(1, 2, 1);
				GUI.pinRB2IO.setEnabled(true);

				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(1, 2, 0);
				Speicher.setPinsIO(1, 2, 0);
				GUI.pinRB2IO.setSelected(false);
				GUI.pinRB2IO.setEnabled(false);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);

			}
		}
		if (checkbox == GUI.pinRB2IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(1, 2, 1);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(1, 2, 0);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
		}
		
		if (checkbox == GUI.pinRB3) {
			if (checkbox.isSelected()) {
				Speicher.setPins(1, 3, 1);
				GUI.pinRB3IO.setEnabled(true);

				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(1, 3, 0);
				Speicher.setPinsIO(1, 3, 0);
				GUI.pinRB3IO.setSelected(false);
				GUI.pinRB3IO.setEnabled(false);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);

			}
		}
		if (checkbox == GUI.pinRB3IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(1, 3, 1);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(1, 3, 0);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
		}
		
		if (checkbox == GUI.pinRB4) {
			if (checkbox.isSelected()) {
				Speicher.setPins(1, 4, 1);
				GUI.pinRB4IO.setEnabled(true);

				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(1, 4, 0);
				Speicher.setPinsIO(1, 4, 0);
				GUI.pinRB4IO.setSelected(false);
				GUI.pinRB4IO.setEnabled(false);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);

			}
		}
		if (checkbox == GUI.pinRB4IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(1, 4, 1);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(1, 4, 0);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
		}
		
		if (checkbox == GUI.pinRB5) {
			if (checkbox.isSelected()) {
				Speicher.setPins(1, 5, 1);
				GUI.pinRB5IO.setEnabled(true);

				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(1, 5, 0);
				Speicher.setPinsIO(1, 5, 0);
				GUI.pinRB5IO.setSelected(false);
				GUI.pinRB5IO.setEnabled(false);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);

			}
		}
		if (checkbox == GUI.pinRB5IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(1, 5, 1);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(1, 5, 0);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
		}
		
		if (checkbox == GUI.pinRB6) {
			if (checkbox.isSelected()) {
				Speicher.setPins(1, 6, 1);
				GUI.pinRB6IO.setEnabled(true);

				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(1, 6, 0);
				Speicher.setPinsIO(1, 6, 0);
				GUI.pinRB6IO.setSelected(false);
				GUI.pinRB6IO.setEnabled(false);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);

			}
		}
		if (checkbox == GUI.pinRB6IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(1, 6, 1);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(1, 6, 0);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
		}
		
		if (checkbox == GUI.pinRB7) {
			if (checkbox.isSelected()) {
				Speicher.setPins(1, 7, 1);
				GUI.pinRB7IO.setEnabled(true);

				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPins(1, 7, 0);
				Speicher.setPinsIO(1, 7, 0);
				GUI.pinRB7IO.setSelected(false);
				GUI.pinRB7IO.setEnabled(false);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);

			}
		}
		if (checkbox == GUI.pinRB7IO) {
			if (checkbox.isSelected()) {
				Speicher.setPinsIO(1, 7, 1);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
			if (!checkbox.isSelected()) {
				Speicher.setPinsIO(1, 7, 0);
				System.out.println("Pins: " + pins[0]);
				System.out.println("IO: " + pinsIO[0]);
			}
		}
	}

}
