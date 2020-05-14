package GUI;

import javax.swing.BorderFactory;

public class Bits extends GUI {

	
	public Bits() {
		initialize();
	}
	
	
	
	public void initialize() {
		
		


				//Adding to bitPanel
				bitPanel.add(statusText);
				bitPanel.add(optionText);
				bitPanel.add(intconText);

				bitPanel.add(IRPText);
				bitPanel.add(RP1Text);
				bitPanel.add(RP0Text);
				bitPanel.add(TOText);
				bitPanel.add(PDText);
				bitPanel.add(ZText);
				bitPanel.add(DCText);
				bitPanel.add(CText);

				bitPanel.add(IRPNumberText);
				bitPanel.add(RP1NumberText);
				bitPanel.add(RP0NumberText);
				bitPanel.add(TONumberText);
				bitPanel.add(PDNumberText);
				bitPanel.add(ZNumberText);
				bitPanel.add(DCNumberText);
				bitPanel.add(CNumberText);

				bitPanel.add(RPUText);
				bitPanel.add(IEGText);
				bitPanel.add(TCSText);
				bitPanel.add(TSEText);
				bitPanel.add(PSAText);
				bitPanel.add(PS2Text);
				bitPanel.add(PS1Text);
				bitPanel.add(PS0Text);

				bitPanel.add(RPUNumberText);
				bitPanel.add(IEGNumberText);
				bitPanel.add(TCSNumberText);
				bitPanel.add(TSENumberText);
				bitPanel.add(PSANumberText);
				bitPanel.add(PS2NumberText);
				bitPanel.add(PS1NumberText);
				bitPanel.add(PS0NumberText);

				bitPanel.add(GIEText);
				bitPanel.add(EIEText);
				bitPanel.add(TIEText);
				bitPanel.add(IEText);
				bitPanel.add(RIEText);
				bitPanel.add(TIFText);
				bitPanel.add(IFText);
				bitPanel.add(RIFText);

				bitPanel.add(GIENumberText);
				bitPanel.add(EIENumberText);
				bitPanel.add(TIENumberText);
				bitPanel.add(IENumberText);
				bitPanel.add(RIENumberText);
				bitPanel.add(TIFNumberText);
				bitPanel.add(IFNumberText);
				bitPanel.add(RIFNumberText);
				
				
				statusText.setBounds(20, 10, 50, 20);
				optionText.setBounds(20, 60, 50, 20);
				intconText.setBounds(20, 110, 50, 20);

				IRPText.setBounds(70, 10, 30, 20);
				RP1Text.setBounds(100, 10, 30, 20);
				RP0Text.setBounds(130, 10, 30, 20);
				TOText.setBounds(160, 10, 30, 20);
				PDText.setBounds(190, 10, 30, 20);
				ZText.setBounds(220, 10, 30, 20);
				DCText.setBounds(250, 10, 30, 20);
				CText.setBounds(280, 10, 30, 20);

				IRPNumberText.setBounds(70, 30, 30, 20);
				RP1NumberText.setBounds(100, 30, 30, 20);
				RP0NumberText.setBounds(130, 30, 30, 20);
				TONumberText.setBounds(160, 30, 30, 20);
				PDNumberText.setBounds(190, 30, 30, 20);
				ZNumberText.setBounds(220, 30, 30, 20);
				DCNumberText.setBounds(250, 30, 30, 20);
				CNumberText.setBounds(280, 30, 30, 20);

				RPUText.setBounds(70, 60, 30, 20);
				IEGText.setBounds(100, 60, 30, 20);
				TCSText.setBounds(130, 60, 30, 20);
				TSEText.setBounds(160, 60, 30, 20);
				PSAText.setBounds(190, 60, 30, 20);
				PS2Text.setBounds(220, 60, 30, 20);
				PS1Text.setBounds(250, 60, 30, 20);
				PS0Text.setBounds(280, 60, 30, 20);

				RPUNumberText.setBounds(70, 80, 30, 20);
				IEGNumberText.setBounds(100, 80, 30, 20);
				TCSNumberText.setBounds(130, 80, 30, 20);
				TSENumberText.setBounds(160, 80, 30, 20);
				PSANumberText.setBounds(190, 80, 30, 20);
				PS2NumberText.setBounds(220, 80, 30, 20);
				PS1NumberText.setBounds(250, 80, 30, 20);
				PS0NumberText.setBounds(280, 80, 30, 20);

				GIEText.setBounds(70, 110, 30, 20);
				EIEText.setBounds(100, 110, 30, 20);
				TIEText.setBounds(130, 110, 30, 20);
				IEText.setBounds(160, 110, 30, 20);
				RIEText.setBounds(190, 110, 30, 20);
				TIFText.setBounds(220, 110, 30, 20);
				IFText.setBounds(250, 110, 30, 20);
				RIFText.setBounds(280, 110, 30, 20);

				GIENumberText.setBounds(70, 130, 30, 20);
				EIENumberText.setBounds(100, 130, 30, 20);
				TIENumberText.setBounds(130, 130, 30, 20);
				IENumberText.setBounds(160, 130, 30, 20);
				RIENumberText.setBounds(190, 130, 30, 20);
				TIFNumberText.setBounds(220, 130, 30, 20);
				IFNumberText.setBounds(250, 130, 30, 20);
				RIFNumberText.setBounds(280, 130, 30, 20);
				
				bitPanel.setBounds(400, 30, 310, 150);
				
				bitPanel.setBorder(BorderFactory.createTitledBorder("Bit"));
				
				bitPanel.setLayout(null);
				
				frame.add(bitPanel);
				
		
	}
	
	
}
