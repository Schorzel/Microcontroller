package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import Speicher.Speicher;

public class GUI  {

	private static String datei = "C:\\Users\\Super\\git\\Microcontroller\\src\\LST_Files\\TPicSim1.LST";
	
	JFrame frame = new JFrame("PIC 16F84");
	
	JPanel buttonPanel = new JPanel();
	JPanel pinAPanel = new JPanel();
	JPanel pinBPanel = new JPanel();
	JPanel bitPanel = new JPanel();
	JLabel watchDogLabel = new JLabel();
	JPanel watchDogPanel = new JPanel();
	
	
	
	
	
	//JRadioButton WatchDog
	JRadioButton watchDogON = new JRadioButton("Ein");
	JRadioButton watchDogOFF = new JRadioButton("Aus");
	
	ButtonGroup watchDog = new ButtonGroup();
	
	
	
	// Pin Checkboxen
	JCheckBox pinRA0 = new JCheckBox("RA0", false);
	JCheckBox pinRA1 = new JCheckBox("RA1", false);
	JCheckBox pinRA2 = new JCheckBox("RA2", false);
	JCheckBox pinRA3 = new JCheckBox("RA3", false);
	JCheckBox pinRA4 = new JCheckBox("RA4", false);

	JCheckBox pinRB0 = new JCheckBox("RB0", false);
	JCheckBox pinRB1 = new JCheckBox("RB1", false);
	JCheckBox pinRB2 = new JCheckBox("RB2", false);
	JCheckBox pinRB3 = new JCheckBox("RB3", false);
	JCheckBox pinRB4 = new JCheckBox("RB4", false);
	JCheckBox pinRB5 = new JCheckBox("RB5", false);
	JCheckBox pinRB6 = new JCheckBox("RB6", false);
	JCheckBox pinRB7 = new JCheckBox("RB7", false);
	
	// Pin IO Checkboxen
	JCheckBox pinRA0IO = new JCheckBox("RA0", false);
	JCheckBox pinRA1IO = new JCheckBox("RA1", false);
	JCheckBox pinRA2IO = new JCheckBox("RA2", false);
	JCheckBox pinRA3IO = new JCheckBox("RA3", false);
	JCheckBox pinRA4IO = new JCheckBox("RA4", false);

	JCheckBox pinRB0IO = new JCheckBox("RB0", false);
	JCheckBox pinRB1IO = new JCheckBox("RB1", false);
	JCheckBox pinRB2IO = new JCheckBox("RB2", false);
	JCheckBox pinRB3IO = new JCheckBox("RB3", false);
	JCheckBox pinRB4IO = new JCheckBox("RB4", false);
	JCheckBox pinRB5IO = new JCheckBox("RB5", false);
	JCheckBox pinRB6IO = new JCheckBox("RB6", false);
	JCheckBox pinRB7IO = new JCheckBox("RB7", false);

	// Buttons
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JButton reset = new JButton("Reset");
	
	

	// Labels
	
	//Pins
	JLabel portAText = new JLabel("Port A");
	JLabel portBText = new JLabel("Port B");
	
	JLabel portATextIO = new JLabel("Port A IO");
	JLabel portBTextIO = new JLabel("Port B IO");

	
	
	//Bits
	protected JLabel statusText = new JLabel("Status:");
	protected JLabel optionText = new JLabel("Option:");
	protected JLabel intconText = new JLabel("Intcon:");

	protected JLabel IRPText = new JLabel("IRP0");
	protected JLabel RP1Text = new JLabel("RP1");
	protected JLabel RP0Text = new JLabel("RP0");
	protected JLabel TOText = new JLabel("TO");
	protected JLabel PDText = new JLabel("PD");
	protected JLabel ZText = new JLabel("Z");
	protected JLabel DCText = new JLabel("DC");
	protected JLabel CText = new JLabel("C");

	protected JLabel IRPNumberText = new JLabel("0");
	protected JLabel RP1NumberText = new JLabel("0");
	protected JLabel RP0NumberText = new JLabel("0");
	protected JLabel TONumberText = new JLabel("0");
	protected JLabel PDNumberText = new JLabel("0");
	protected JLabel ZNumberText = new JLabel("0");
	protected JLabel DCNumberText = new JLabel("0");
	protected JLabel CNumberText = new JLabel("0");

	protected JLabel RPUText = new JLabel("RPU");
	protected JLabel IEGText = new JLabel("IEG");
	protected JLabel TCSText = new JLabel("TCS");
	protected JLabel TSEText = new JLabel("TSE");
	protected JLabel PSAText = new JLabel("PSA");
	protected JLabel PS2Text = new JLabel("PS2");
	protected JLabel PS1Text = new JLabel("PS1");
	protected JLabel PS0Text = new JLabel("PS0");

	protected JLabel RPUNumberText = new JLabel("0");
	protected JLabel IEGNumberText = new JLabel("0");
	protected JLabel TCSNumberText = new JLabel("0");
	protected JLabel TSENumberText = new JLabel("0");
	protected JLabel PSANumberText = new JLabel("0");
	protected JLabel PS2NumberText = new JLabel("0");
	protected JLabel PS1NumberText = new JLabel("0");
	protected JLabel PS0NumberText = new JLabel("0");

	protected JLabel GIEText = new JLabel("GIE");
	protected JLabel EIEText = new JLabel("EIE");
	protected JLabel TIEText = new JLabel("TIE");
	protected JLabel IEText = new JLabel("IE");
	protected JLabel RIEText = new JLabel("RIE");
	protected JLabel TIFText = new JLabel("TIF");
	protected JLabel IFText = new JLabel("IF");
	protected JLabel RIFText = new JLabel("RIF");

	protected JLabel GIENumberText = new JLabel("0");
	protected JLabel EIENumberText = new JLabel("0");
	protected JLabel TIENumberText = new JLabel("0");
	protected JLabel IENumberText = new JLabel("0");
	protected JLabel RIENumberText = new JLabel("0");
	protected JLabel TIFNumberText = new JLabel("0");
	protected JLabel IFNumberText = new JLabel("0");
	protected JLabel RIFNumberText = new JLabel("0");
	

	private static String[][] loadFile(String datei) { // Datei ins programm laden/einlesen
		File file = new File(datei);

		String[][] test = new String[150][150];
		String[][] yeet = new String[1][1];
		String befehlsAdresse;
		String befehlsCode;
		String zeilenNummer;
		String startEnde;
		String kommentar;

		if (!file.canRead() || !file.isFile()) { // überprüfen ob Datei exisitiert und gelesen werden kann
			return yeet;
		}
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(datei));
			String zeile = null;
			for (int i = 0; (zeile = buffer.readLine()) != null; i++) {

				kommentar = " ";

				befehlsAdresse = zeile.substring(0, 4);
				befehlsCode = zeile.substring(5, 9);
				zeilenNummer = zeile.substring(20, 25);
				startEnde = zeile.substring(27, 32);

				if (zeile.length() > 37) {
					startEnde = zeile.substring(27, 32);
					kommentar = zeile.substring(36, zeile.length());
				} else {
					startEnde = zeile.substring(27, zeile.length());
				}

				test[i][0] = befehlsAdresse;
				test[i][1] = befehlsCode;
				test[i][2] = zeilenNummer;
				test[i][3] = startEnde;
				test[i][4] = kommentar;

			}
			buffer.close();
			return test;
		}

		catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO Error");
		}
		return yeet;
	}

	
	public GUI(){
		
		
		new Bits();
		
	/*	//Action listener Test
		start.addActionListener(this);
		stop.addActionListener(this);
		reset.addActionListener(this);
*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);

	
		
		
		// JTable

		// Column Names
		String[] columnNames = { "Adresse", "Code", "Zeile", "Start/Ende", "Kommentar" };

		JTable lstFile = new JTable(loadFile(datei), columnNames);

		// lstFile.setBounds(20,200,700,600);

		JScrollPane lstFileSP = new JScrollPane(lstFile);

		lstFile.getColumnModel().getColumn(0).setPreferredWidth(55);
		lstFile.getColumnModel().getColumn(1).setPreferredWidth(50);
		lstFile.getColumnModel().getColumn(2).setPreferredWidth(50);
		lstFile.getColumnModel().getColumn(3).setPreferredWidth(70);
		lstFile.getColumnModel().getColumn(4).setPreferredWidth(500);
		// lstFile.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		lstFile.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		// Adding
		frame.add(buttonPanel);
		frame.add(pinAPanel);
		frame.add(pinBPanel);
		frame.add(portAText);
		frame.add(portBText);
		frame.add(portATextIO);
		frame.add(portBTextIO);
		
		frame.add(lstFileSP);
		frame.add(watchDogLabel);
		frame.add(watchDogPanel);
		
		
		
		
		

		lstFileSP.setBounds(20, 200, 700, 400);
		
		watchDogPanel.add(watchDogOFF);
		watchDogPanel.add(watchDogON);
		watchDogPanel.add(watchDogLabel);

		buttonPanel.add(start);
		buttonPanel.add(stop);
		buttonPanel.add(reset);

		
		//Adding to pinPanel
		pinAPanel.add(pinRA0);
		pinAPanel.add(pinRA1);
		pinAPanel.add(pinRA2);
		pinAPanel.add(pinRA3);
		pinAPanel.add(pinRA4);

		pinBPanel.add(pinRB0);
		pinBPanel.add(pinRB1);
		pinBPanel.add(pinRB2);
		pinBPanel.add(pinRB3);
		pinBPanel.add(pinRB4);
		pinBPanel.add(pinRB5);
		pinBPanel.add(pinRB6);
		pinBPanel.add(pinRB7);
		
		//Adding to pinPanelIO
		pinAPanel.add(pinRA0IO);
		pinAPanel.add(pinRA1IO);
		pinAPanel.add(pinRA2IO);
		pinAPanel.add(pinRA3IO);
		pinAPanel.add(pinRA4IO);

		pinBPanel.add(pinRB0IO);
		pinBPanel.add(pinRB1IO);
		pinBPanel.add(pinRB2IO);
		pinBPanel.add(pinRB3IO);
		pinBPanel.add(pinRB4IO);
		pinBPanel.add(pinRB5IO);
		pinBPanel.add(pinRB6IO);
		pinBPanel.add(pinRB7IO);

		
		
		// Position and Size
		portAText.setBounds(160, 5, 50, 30);
		portBText.setBounds(280, 5, 50, 30);
		
		portATextIO.setBounds(210, 5, 50, 30);
		portBTextIO.setBounds(330, 5, 50, 30);

		buttonPanel.setBounds(10, 30, 80, 150);
		pinAPanel.setBounds(150, 30, 110, 170);
		pinBPanel.setBounds(270,30,110,170);
		
		watchDogPanel.setBounds(750, 50, 100, 100);

		start.setBounds(0, 0, 80, 50);
		stop.setBounds(0, 50, 80, 50);
		reset.setBounds(0, 100, 80, 50);

		pinRA0.setBounds(0, 0, 50, 20);
		pinRA1.setBounds(0, 20, 50, 20);
		pinRA2.setBounds(0, 40, 50, 20);
		pinRA3.setBounds(0, 60, 50, 20);
		pinRA4.setBounds(0, 80, 50, 20);

		pinRB0.setBounds(0, 0, 50, 20);
		pinRB1.setBounds(0, 20, 50, 20);
		pinRB2.setBounds(0, 40, 50, 20);
		pinRB3.setBounds(0, 60, 50, 20);
		pinRB4.setBounds(0, 80, 50, 20);
		pinRB5.setBounds(0, 100, 50, 20);
		pinRB6.setBounds(0, 120, 50, 20);
		pinRB7.setBounds(0, 140, 50, 20);
		
		pinRA0IO.setBounds(60, 0, 50, 20);
		pinRA1IO.setBounds(60, 20, 50, 20);
		pinRA2IO.setBounds(60, 40, 50, 20);
		pinRA3IO.setBounds(60, 60, 50, 20);
		pinRA4IO.setBounds(60, 80, 50, 20);

		pinRB0IO.setBounds(60, 0, 50, 20);
		pinRB1IO.setBounds(60, 20, 50, 20);
		pinRB2IO.setBounds(60, 40, 50, 20);
		pinRB3IO.setBounds(60, 60, 50, 20);
		pinRB4IO.setBounds(60, 80, 50, 20);
		pinRB5IO.setBounds(60, 100, 50, 20);
		pinRB6IO.setBounds(60, 120, 50, 20);
		pinRB7IO.setBounds(60, 140, 50, 20);

	
		
		//RadioButton WatchDog
		watchDogON.setBounds(0, 25, 50, 25);
		watchDogOFF.setBounds(50, 25, 50, 25);
		watchDogLabel.setBounds(15, 0, 100, 20);
		watchDogLabel.setText("WatchDog");
		
		watchDog.add(watchDogON);
		watchDog.add(watchDogOFF);
		


		// Color
		start.setBackground(new Color(0, 120, 0));
		stop.setBackground(new Color(255, 0, 0));
		reset.setBackground(new Color(0, 255, 255));
		
		

		// Layout
		watchDogPanel.setLayout(null);
		
		buttonPanel.setLayout(null);
		pinAPanel.setLayout(null);
		pinBPanel.setLayout(null);
		frame.setLayout(null);
		frame.setVisible(true);
	}



/*	@Override
	public void actionPerformed(ActionEvent ae) {
		 if(ae.getSource() == this.start){
	            watchDogLabel.setText(("Button 1 wurde betätigt"));
	        }
	        else if(ae.getSource() == this.stop){
	            watchDogLabel.setText("Button 2 wurde betätigt");
	        }
	        else if (ae.getSource() == this.reset){
	            watchDogLabel.setText(("Button 3 wurde betätigt"));
	        }
		
	}
	*/
	
	public static void main(String[] args) {
		new GUI();

	
	}


	

}
