package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import DateiVerarbeitung.Parser;
import Funktionen.Functions;
import Speicher.FileRegister;
import Speicher.Speicher;
import Speicher.Stack;
import Laufzeit.Timer;
import Laufzeit.WatchDogTimer;
import Laufzeit.Laufzeit;

public class GUI implements ActionListener {

	static int[][] rams = FileRegister.getFReg();

	static String datei = "src\\LST_Files\\TPicSim14.LST";

	static JFrame frame;

	static JLabel wRegLabel;

	static JLabel wRegTitle;

	static JTable lstFile;

	JPanel buttonPanel = new JPanel();

	JPanel pinAPanel = new JPanel();

	JPanel pinBPanel = new JPanel();

	JPanel bitPanel = new JPanel();

	JLabel watchDogLabel = new JLabel();

	JPanel watchDogPanel = new JPanel();

	JPanel timerPanel = new JPanel();

	static String[] stackColumnNames = { "Index", "Value" };
	// Speicher speicher = new Speicher();

	static int[] statusReg = Speicher.getStatusRegister();

	static int[] optionReg = Speicher.getOptionReg();

	static int[] intconReg = Speicher.getIntconReg();

	static int pc = Speicher.getPC();

	// JRadioButton WatchDog
	static JRadioButton watchDogON = new JRadioButton("Ein");

	static JRadioButton watchDogOFF = new JRadioButton("Aus");

	static JLabel laufzeit = new JLabel("Laufzeit Zähler");

	static JLabel frequenz = new JLabel("Quarzfrequenz");

	static JLabel timer = new JLabel("Timer");

	static JLabel timerMax = new JLabel("Timer Max");

	static JLabel watchDogTimer = new JLabel("Watchdogtimer");

	static JLabel watchDogTimerMax = new JLabel("Watchdogtimer Max");

	static JLabel laufzeitValue = new JLabel(Integer.toString(Laufzeit.getLaufzeitzaehler()));

	static JTextField frequenzValue = new JTextField(Integer.toString(Laufzeit.getFrequenz()));

	static JLabel timerValue = new JLabel(Integer.toString(Timer.getTimer()));

	static JLabel timerMaxValue = new JLabel(Integer.toString(Timer.getTimerMax()));

	static JLabel watchDogTimerValue = new JLabel(Integer.toString(WatchDogTimer.getWatchdogTimer()));

	static JLabel watchDogTimerMaxValue = new JLabel(Integer.toString(WatchDogTimer.getMaxTime()));

	ButtonGroup watchDog = new ButtonGroup();

	static JTable stack;

	// Pin Checkboxen
	static JCheckBox pinRA0 = new JCheckBox("RA0", false);

	static JCheckBox pinRA1 = new JCheckBox("RA1", false);

	static JCheckBox pinRA2 = new JCheckBox("RA2", false);

	static JCheckBox pinRA3 = new JCheckBox("RA3", false);

	static JCheckBox pinRA4 = new JCheckBox("RA4", false);

	static JCheckBox pinRB0 = new JCheckBox("RB0", false);

	static JCheckBox pinRB1 = new JCheckBox("RB1", false);

	static JCheckBox pinRB2 = new JCheckBox("RB2", false);

	static JCheckBox pinRB3 = new JCheckBox("RB3", false);

	static JCheckBox pinRB4 = new JCheckBox("RB4", false);

	static JCheckBox pinRB5 = new JCheckBox("RB5", false);

	static JCheckBox pinRB6 = new JCheckBox("RB6", false);

	static JCheckBox pinRB7 = new JCheckBox("RB7", false);

	// Pin IO Checkboxen
	static JCheckBox pinRA0IO = new JCheckBox("RA0", false);

	static JCheckBox pinRA1IO = new JCheckBox("RA1", false);

	static JCheckBox pinRA2IO = new JCheckBox("RA2", false);

	static JCheckBox pinRA3IO = new JCheckBox("RA3", false);

	static JCheckBox pinRA4IO = new JCheckBox("RA4", false);

	static JCheckBox pinRB0IO = new JCheckBox("RB0", false);

	static JCheckBox pinRB1IO = new JCheckBox("RB1", false);

	static JCheckBox pinRB2IO = new JCheckBox("RB2", false);

	static JCheckBox pinRB3IO = new JCheckBox("RB3", false);

	static JCheckBox pinRB4IO = new JCheckBox("RB4", false);

	static JCheckBox pinRB5IO = new JCheckBox("RB5", false);

	static JCheckBox pinRB6IO = new JCheckBox("RB6", false);

	static JCheckBox pinRB7IO = new JCheckBox("RB7", false);

	// Buttons
	static JButton start = new JButton("Start");

	static JButton load = new JButton("Load");

	static JButton step = new JButton("Step");

	// FileRegister
	static JTable fileRegister0;

	static JTable fileRegister1;

	static JScrollPane fileRegSP0;

	static JScrollPane fileRegSP1;

	static JTabbedPane fileRegTP;

	// Labels

	// Pins
	static JLabel portAText = new JLabel("Port A IO");

	static JLabel portBText = new JLabel("Port B IO");

	static JLabel portATextIO = new JLabel("Port A ");

	static JLabel portBTextIO = new JLabel("Port B ");

	JLabel stackText = new JLabel("Stack");

	// Bits
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

	protected static JLabel IRPNumberText = new JLabel(Integer.toString(statusReg[0]));

	protected static JLabel RP1NumberText = new JLabel(Integer.toString(statusReg[1]));

	protected static JLabel RP0NumberText = new JLabel(Integer.toString(statusReg[2]));

	protected static JLabel TONumberText = new JLabel(Integer.toString(statusReg[3]));

	protected static JLabel PDNumberText = new JLabel(Integer.toString(statusReg[4]));

	protected static JLabel ZNumberText = new JLabel(Integer.toString(statusReg[5]));

	protected static JLabel DCNumberText = new JLabel(Integer.toString(statusReg[6]));

	protected static JLabel CNumberText = new JLabel(Integer.toString(statusReg[7]));

	protected JLabel RPUText = new JLabel("RPU");

	protected JLabel IEGText = new JLabel("IEG");

	protected JLabel TCSText = new JLabel("TCS");

	protected JLabel TSEText = new JLabel("TSE");

	protected JLabel PSAText = new JLabel("PSA");

	protected JLabel PS2Text = new JLabel("PS2");

	protected JLabel PS1Text = new JLabel("PS1");

	protected JLabel PS0Text = new JLabel("PS0");

	protected static JLabel RPUNumberText = new JLabel(Integer.toString(optionReg[0]));

	protected static JLabel IEGNumberText = new JLabel(Integer.toString(optionReg[1]));

	protected static JLabel TCSNumberText = new JLabel(Integer.toString(optionReg[2]));

	protected static JLabel TSENumberText = new JLabel(Integer.toString(optionReg[3]));

	protected static JLabel PSANumberText = new JLabel(Integer.toString(optionReg[4]));

	protected static JLabel PS2NumberText = new JLabel(Integer.toString(optionReg[5]));

	protected static JLabel PS1NumberText = new JLabel(Integer.toString(optionReg[6]));

	protected static JLabel PS0NumberText = new JLabel(Integer.toString(optionReg[7]));

	protected JLabel GIEText = new JLabel("GIE");

	protected JLabel EIEText = new JLabel("EIE");

	protected JLabel TIEText = new JLabel("TIE");

	protected JLabel IEText = new JLabel("IE");

	protected JLabel RIEText = new JLabel("RIE");

	protected JLabel TIFText = new JLabel("TIF");

	protected JLabel IFText = new JLabel("IF");

	protected JLabel RIFText = new JLabel("RIF");

	protected static JLabel GIENumberText = new JLabel(Integer.toString(intconReg[0]));

	protected static JLabel EIENumberText = new JLabel(Integer.toString(intconReg[1]));

	protected static JLabel TIENumberText = new JLabel(Integer.toString(intconReg[2]));

	protected static JLabel IENumberText = new JLabel(Integer.toString(intconReg[3]));

	protected static JLabel RIENumberText = new JLabel(Integer.toString(intconReg[4]));

	protected static JLabel TIFNumberText = new JLabel(Integer.toString(intconReg[5]));

	protected static JLabel IFNumberText = new JLabel(Integer.toString(intconReg[6]));

	protected static JLabel RIFNumberText = new JLabel(Integer.toString(intconReg[7]));

	private static String[][] loadFile(String datei) { // Datei ins programm laden/einlesen
		File file = new File(datei);

		String[][] test = new String[150][150];
		String[][] yeet = new String[1][1];
		String befehlsAdresse;
		String befehlsCode;
		String zeilenNummer;
		String startEnde;
		String kommentar;

		if (!file.canRead() || !file.isFile()) { // überprüfen ob Datei exisitiert
													// und gelesen werden kann
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

	public static void Stack() {
		int[] tempStack = Stack.getStack();
		String[][] stackArray = { { "0", Integer.toString(tempStack[0]) }, { "1", Integer.toString(tempStack[1]) },
				{ "2", Integer.toString(tempStack[2]) }, { "3", Integer.toString(tempStack[3]) },
				{ "4", Integer.toString(tempStack[4]) }, { "5", Integer.toString(tempStack[5]) },
				{ "6", Integer.toString(tempStack[6]) }, { "7", Integer.toString(tempStack[7]) } };

		stack = new JTable(stackArray, stackColumnNames);

	}

	public static String leadingZero(int number) {
		return (number < 16 ? "0" : "") + Integer.toHexString(number).toUpperCase();
	}

	public static void initializeFileReg() {
		fileRegTP = null;
		fileRegTP = new JTabbedPane();

		String[] fileRegisterColum = { "", "00", "10", "20", "30", "40", "50", "60", "70" };

		String[][] fileData0 = new String[16][9];
		String[][] fileData1 = new String[16][9];

		fileData0[0][0] = "";
		fileData1[0][0] = "";

		for (int i = 0; i < fileData0.length; i++) {
			fileData0[i][0] = leadingZero(i);
			fileData1[i][0] = leadingZero(i);
		}

		int columnMultiplicator = 0;
		for (int i = 0; i < rams[0].length; i++) {

			fileData0[i % 16][columnMultiplicator + 1] = leadingZero(FileRegister.getBankValue(0, i));
			fileData1[i % 16][columnMultiplicator + 1] = leadingZero(FileRegister.getBankValue(1, i));

			if (i % 16 == 15 && i > 0) {
				columnMultiplicator++;
			}
		}

		fileRegister0 = new JTable(fileData0, fileRegisterColum);
		fileRegister1 = new JTable(fileData1, fileRegisterColum);

		fileRegSP0 = new JScrollPane(fileRegister0);
		fileRegSP1 = new JScrollPane(fileRegister1);

		fileRegTP.addTab("Bank0", fileRegSP0);
		fileRegTP.addTab("Bank1", fileRegSP1);

		fileRegTP.setBounds(750, 290, 500, 308);

		frame.getContentPane().add(fileRegTP);

		if (fileRegTP != null)
			fileRegTP.repaint();

	}

	public GUI() {
		Speicher.reset();
		// frame();
		
		

		frame = new JFrame("Pic");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(1300, 700);

		wRegLabel = new JLabel("0");

		wRegLabel.setBounds(900, 237, 100, 20);

		wRegTitle = new JLabel("W-Register");

		wRegTitle.setBounds(870, 210, 100, 20);

		frame.add(wRegTitle);

		frame.add(wRegLabel);

		// Action listener Test
		ActionListener actionListener = new ActionHandler();
		pinRA0.addActionListener(actionListener);
		pinRA1.addActionListener(actionListener);
		pinRA2.addActionListener(actionListener);
		pinRA3.addActionListener(actionListener);
		pinRA4.addActionListener(actionListener);

		pinRA0IO.addActionListener(actionListener);
		pinRA1IO.addActionListener(actionListener);
		pinRA2IO.addActionListener(actionListener);
		pinRA3IO.addActionListener(actionListener);
		pinRA4IO.addActionListener(actionListener);

		pinRB0.addActionListener(actionListener);
		pinRB1.addActionListener(actionListener);
		pinRB2.addActionListener(actionListener);
		pinRB3.addActionListener(actionListener);
		pinRB4.addActionListener(actionListener);
		pinRB5.addActionListener(actionListener);
		pinRB6.addActionListener(actionListener);
		pinRB7.addActionListener(actionListener);

		pinRB0IO.addActionListener(actionListener);
		pinRB1IO.addActionListener(actionListener);
		pinRB2IO.addActionListener(actionListener);
		pinRB3IO.addActionListener(actionListener);
		pinRB4IO.addActionListener(actionListener);
		pinRB5IO.addActionListener(actionListener);
		pinRB6IO.addActionListener(actionListener);
		pinRB7IO.addActionListener(actionListener);

		// JTable

		// Column Names
		String[] lstColumnNames = { "Adresse", "Code", "Zeile", "Start/Ende", "Kommentar" };

		Stack();

		lstFile = new JTable(loadFile(datei), lstColumnNames);

		// lstFile.setBounds(0,0,700,600);

		JScrollPane stackSP = new JScrollPane(stack);

		JScrollPane lstFileSP = new JScrollPane(lstFile);

		lstFile.getColumnModel().getColumn(0).setPreferredWidth(55);
		lstFile.getColumnModel().getColumn(1).setPreferredWidth(50);
		lstFile.getColumnModel().getColumn(2).setPreferredWidth(50);
		lstFile.getColumnModel().getColumn(3).setPreferredWidth(70);
		lstFile.getColumnModel().getColumn(4).setPreferredWidth(500);
		// lstFile.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		lstFile.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		// Adding

		// lstFileSP.add(lstFile);

		watchDogPanel.add(watchDogOFF);
		watchDogPanel.add(watchDogON);
		watchDogPanel.add(watchDogLabel);
		
		watchDogON.setSelected(!WatchDogTimer.isEnabled());

		timerPanel.add(laufzeit);
		timerPanel.add(frequenz);
		timerPanel.add(timer);
		timerPanel.add(timerMax);
		timerPanel.add(watchDogTimer);
		timerPanel.add(watchDogTimerMax);

		timerPanel.add(laufzeitValue);
		timerPanel.add(frequenzValue);
		timerPanel.add(timerValue);
		timerPanel.add(timerMaxValue);
		timerPanel.add(watchDogTimerValue);
		timerPanel.add(watchDogTimerMaxValue);

		ActionListener buttonListener = new ButtonHandler();

		watchDogON.addActionListener(buttonListener);
		watchDogOFF.addActionListener(buttonListener);

		buttonPanel.add(start);
		buttonPanel.add(load);
		buttonPanel.add(step);

		start.addActionListener(buttonListener);
		load.addActionListener(buttonListener);
		step.addActionListener(buttonListener);

		// Adding to pinPanel
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

		// Adding to pinPanelIO
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

		// Adding to bitpanel
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

		// Position and Size
		portAText.setBounds(150, 5, 50, 30);
		portBText.setBounds(270, 5, 50, 30);

		portATextIO.setBounds(210, 5, 50, 30);
		portBTextIO.setBounds(330, 5, 50, 30);

		buttonPanel.setBounds(10, 30, 80, 150);
		pinAPanel.setBounds(150, 30, 110, 170);
		pinBPanel.setBounds(270, 30, 110, 170);

		watchDogPanel.setBounds(750, 210, 100, 100);

		start.setBounds(0, 0, 80, 50);
		load.setBounds(0, 50, 80, 50);
		step.setBounds(0, 100, 80, 50);

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

		// RadioButton WatchDog
		watchDogON.setBounds(0, 25, 50, 25);
		watchDogOFF.setBounds(50, 25, 50, 25);
		watchDogLabel.setBounds(770, 210, 100, 20);
		watchDogLabel.setText("WatchDog");

		watchDog.add(watchDogON);
		watchDog.add(watchDogOFF);

		lstFileSP.setBounds(20, 200, 700, 400);

		stackSP.setBounds(750, 40, 200, 151);

		stackText.setBounds(830, 20, 50, 10);

		laufzeit.setBounds(0, 0, 100, 20);
		frequenz.setBounds(0, 20, 100, 20);
		timer.setBounds(0, 40, 100, 20);
		timerMax.setBounds(0, 60, 100, 20);
		watchDogTimer.setBounds(0, 80, 100, 20);
		watchDogTimerMax.setBounds(0, 100, 120, 20);

		laufzeitValue.setBounds(150, 0, 50, 20);
		frequenzValue.setBounds(150, 20, 50, 20);
		timerValue.setBounds(150, 40, 50, 20);
		timerMaxValue.setBounds(150, 60, 50, 20);
		watchDogTimerValue.setBounds(150, 80, 50, 20);
		watchDogTimerMaxValue.setBounds(150, 100, 50, 20);

		timerPanel.setBounds(1000, 40, 200, 150);

		// Color
		start.setBackground(new Color(0, 120, 0));
		load.setBackground(new Color(255, 0, 0));
		step.setBackground(new Color(0, 255, 255));

		bitPanel.setBorder(BorderFactory.createTitledBorder("Bit"));

		initializeFileReg();

		frame.add(timerPanel);
		frame.add(stackText);
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
		frame.add(bitPanel);
		frame.add(stackSP);

		// Layout

		watchDogPanel.setLayout(null);
		timerPanel.setLayout(null);
		// stack.setLayout(null);
		bitPanel.setLayout(null);
		buttonPanel.setLayout(null);
		pinAPanel.setLayout(null);
		pinBPanel.setLayout(null);
		frame.setLayout(null);
		frame.setVisible(true);

	}

	public static void step() {
		Functions.run();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reloads.ReloadAttributes();
		Reloads.ReloadGUI();
	}

	public static void main(String[] args) {
		new GUI();
		readFile();
		Reloads.ReloadAttributes();
		Reloads.ReloadGUI();

	}

	public static void readFile() {
		Parser p = new Parser();
		p.setFile(datei);
		p.read();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
