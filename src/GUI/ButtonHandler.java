package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import Laufzeit.WatchDogTimer;

public class ButtonHandler implements ActionListener {

	static boolean run = false;

	Thread t;

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() instanceof JRadioButton) {

			if (((JRadioButton) event.getSource()) == GUI.watchDogON) {
				WatchDogTimer.setEnabled();
			} else if (((JRadioButton) event.getSource()) == GUI.watchDogOFF) {
				WatchDogTimer.setEnabled();
			}
		}

		else {

			JButton button = (JButton) event.getSource();

			if (button == GUI.start) {
				run = !run;
				if (run) {
					GUI.start.setText("Stop");
					GUI.step.setEnabled(false);
					t = new Thread(() -> {

						while (run) {
							GUI.step();
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					});

					t.start();
				} else {
					GUI.start.setText("Run");
					GUI.step.setEnabled(true);
					t = null;
				}
			}
			if (button == GUI.step) {
				GUI.step();
			}
		}
	}

}
