package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import GUI.GUI;

public class ButtonHandler implements ActionListener {
	
	boolean run = false;

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		
		if(button == GUI.start) {
			run = !run;
			if(run) {			
			GUI.start.setText("Stop");
			GUI.step.setEnabled(false);
			
			}
			else {
				GUI.start.setText("Run");
				GUI.step.setEnabled(true);
			}
		}
		if(button == GUI.step) {
			
		}
		
		
	}

}
