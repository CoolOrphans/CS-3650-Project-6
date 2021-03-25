import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Assembler {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int inp = 0;
		do {
			print("Directory: ");
			String directory = ""; // file path of asm file(s)
			directory = sc.nextLine();
			if (directory.length() == 0) { // used for getting out of the code
				System.exit(0);
			}
			if (!directory.endsWith("\\")) { // makes sure that '\' is at the end
				directory = directory + "\\";
			}

			do {
				try {
					print("ASM File Name: ");
					String filename; // asm file name
					filename = sc.nextLine();
					String asmext = ".asm";
					File asmfile = new File(directory + filename + asmext);
					Parser parser = new Parser(asmfile);

					String hackext = ".hack";
					String hackfilename = directory + filename + hackext;
					File hackfile = new File(hackfilename); // output file
					hackfile.createNewFile(); // create new file if one does not exist
					print("Hack File: " + hackfile.getCanonicalFile());
					// writes to hack file
					FileWriter fileWriter = new FileWriter(hackfilename, false); // overwrite existing hackfile

					Code code = new Code();
					SymbolTable st = new SymbolTable();

					// First Pass
					String commandType = "";
					int ROMAddress = 0; // used for symbols and their associated addresses
					while (parser.hasMoreCommands()) {
						parser.advance();
						commandType = parser.commandType();
						if (commandType.equals("L_COMMAND")) {
							st.addEntry(parser.symbol(), ROMAddress);
						} else {
							ROMAddress++;
						}
					}
					parser.reset(); // go back to first line

					// Second Pass
					int n = 16; // next available RAM Address
					String symbol;
					String bin = "";
					while (parser.hasMoreCommands()) {
						parser.advance();
						commandType = parser.commandType();

						if (commandType.equals("A_COMMAND")) { // A COMMAND
							bin = "0";
							symbol = parser.symbol();
							if (symbol.charAt(0) >= 48 && symbol.charAt(0) <= 57) { // symbol is number
								bin += decToBin(Integer.valueOf(symbol), 15);
							} else if (st.contains(symbol)) { // symbol found in table
								bin += decToBin(st.getAddress(symbol), 15);
							} else { // symbol not found in table
								// add symbol to available RAM Address
								st.addEntry(symbol, n);
								bin += decToBin(n, 15);
								n++; // update next available RAM Address
							}

						} else if (commandType.equals("C_COMMAND")) { // C COMMAND
							bin = "111";
							bin += code.comp(parser.comp());
							bin += code.dest(parser.dest());
							bin += code.jump(parser.jump());
						}

						if (!commandType.equals("L_COMMAND")) {
							fileWriter.write(bin + "\n"); // write the binary sequence to the hack file
						}
					}
					fileWriter.close();
				} catch (FileNotFoundException e) {
					print("ASM File not found");
				}
				print("\n0: Exit\n");
				print("1: Change ASM File\n");
				print("2: Change Directory\n");
				print("Input: ");
				inp = sc.nextInt();
				sc.nextLine(); // need or scanner breaks
			} while (inp == 1); // convert different asm file
		} while (inp == 2); // change directory
		// exit

		sc.close();

	}

	public static String decToBin(int n, int z) { // decimal is an integer, not a string
		String r = "";
		while (n >= 1) {
			if (n % 2 == 0) {
				r = "0" + r;
			} else {
				r = "1" + r;
			}
			n = n / 2;
		}
		for (int i = r.length(); i < z; i++) { // pad zeros
			r = "0" + r;
		}
		return r;
	}

	public static void print(Object o) {
		System.out.print(o.toString());
	}
}
