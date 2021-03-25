import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
	public String command = ""; // current command
	public String[] Commands = new String[0]; // set of commands
	public int n; // index of current command

	public Parser(File asmFile) throws FileNotFoundException {
		Scanner fileReader = new Scanner(asmFile);
		n = 0;
		while (fileReader.hasNextLine()) {
			command = fileReader.nextLine();
			if (!(command.startsWith("//") || command.isEmpty())) { // valid assembly input
				command = removeWhiteSpace(command); // remove spaces
				if (command.contains("//")) { // remove comment if on same line
					command = command.substring(0, command.indexOf("//"));
				}
				String[] temp = new String[Commands.length + 1];
				for (int i = 0; i < Commands.length; i++) {
					temp[i] = Commands[i];
				}
				temp[temp.length - 1] = command;
				Commands = temp;
			}
		}
//		print("\n");
//		printCommands();
		fileReader.close();
	}

	public boolean hasMoreCommands() {
		if (n < Commands.length) {
			return true;
		}
		return false;
	}

	public void advance() {
		command = Commands[n]; // current command
		n++;
	}

	public String commandType() {
		if (command.charAt(0) == '@') {
			return "A_COMMAND";
		} else if (command.charAt(0) == '(') {
			return "L_COMMAND";
		} else {
			return "C_COMMAND";
		}
	}

	public String symbol() { // format "@XXX" or "(XXX)" and only called when A or L command
		int cl = command.length();
		if (command.charAt(0) == '(') { // L command
			cl--;
		}
		return command.substring(1, cl);
	}

	public String dest() { // called when C command
		if (command.contains("=")) {
			return command.substring(0, command.indexOf("="));
		}
		return null;
	}

	// dest=comp;jump
	public String comp() {
		if (command.contains("=")) { // A COMMAND
			return command.substring(command.indexOf("=") + 1);
		} else { // C COMMAND
			return command.substring(0, command.indexOf(";"));
		}
	}

	public String jump() {
		if (command.contains(";")) {
			return command.substring(command.indexOf(";") + 1);
		}
		return null;
	}

	public void reset() {
		n = 0; // reset back to first line asm file
	}

	public static String removeWhiteSpace(String s) {
		String r = "";
		for (int i = 0; i < s.length(); i++) {
			if (!(s.charAt(i) == ' ' || s.charAt(i) == '\t')) { // removes spaces and tabs
				r += s.charAt(i);
			}
		}
		return r;
	}

	public void printCommands() { // used for testing
		for (int i = 0; i < Commands.length; i++) {
			print(Commands[i] + "\n");
		}
	}
	
	public String getCommand() { // testing
		return command;
	}

	public void print(Object o) {
		System.out.print(o.toString());
	}

}
