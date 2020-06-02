package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JTable;

import DateiVerarbeitung.Parser;
import Laufzeit.WatchDogTimer;
import Speicher.Speicher;

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
			
			final JFileChooser fc = new JFileChooser("C:\\Users\\Super\\git\\Microcontroller\\src\\LST_Files");

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
			
			if(button == GUI.load) {
			
				
				if(fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					
					GUI.datei = (fc.getSelectedFile().getAbsolutePath());
					
					GUI.readFile(fc.getSelectedFile().getPath());
					
					
					
				

					
					
					
				}
			}
		}
	}

}
