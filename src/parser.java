import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class parser
{
	static FileReader reader; // Reads in
	// the file

	static BufferedReader bufferReader; // For reading line after line

	String fileName = "C:\\Users\\Super\\Downloads\\SimTest_OG\\TPicSim1.LST";

	// Opens a file and reads the relevant lines

	public static String[] read(String fileName)
	{

		try {

			reader = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			// return "File not found";
		}

		bufferReader = new BufferedReader(reader);
		String tmp = null;

		String[] hexcode = new String[250];
		int counter = 0;

		try {
			while ((tmp = bufferReader.readLine()) != null)
				if (tmp.startsWith(" ")) {

				} else {
					String tmp2 = tmp.substring(0, 9);

					// System.out.println(tmp2);

					hexcode[counter] = tmp2;

					counter++;
				}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// return "no line read";
		}

		return hexcode;

	}

	// Turns the lines in Integers

	public static int[] stringCode_to_int(String[] command)
	{
		int[] commandInt = new int[250];

		int counter = 0;

		String tmp1 = "";
		int tmp2 = 0;

		while (command[counter] != null)
			if (command[counter].startsWith(" ")) {

			} else {
				tmp1 = command[counter].substring(5, 9);

				tmp2 = Integer.parseInt(tmp1, 16);
				System.out.println(tmp2);

				commandInt[counter] = tmp2;

				counter++;
			}
		return commandInt;
		// return tmp2;
	}

	public static void main(String[] args)
	{
		//String fileName = "C:\\Users\\Super\\Downloads\\SimTest_OG\\TPicSim1.LST";
		String fileName = "C:\\Users\\Super\\git\\Microcontroller\\Microcontroller\\src\\TPicSim1.LST";

		// System.out.println(read(fileName));
		System.out.println(stringCode_to_int(read(fileName)));
	}
}
