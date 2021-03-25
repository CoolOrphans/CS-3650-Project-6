
public class Code {
	public Code() {

	}

	public String dest(String s) { // assumes code is right
		if (s == null) {
			return "000";
		} else if (s.equals("M")) {
			return "001";
		} else if (s.equals("D")) {
			return "010";
		} else if (s.equals("MD")) {
			return "011";
		} else if (s.equals("A")) {
			return "100";
		} else if (s.equals("AM")) {
			return "101";
		} else if (s.equals("AD")) {
			return "110";
		} else if (s.equals("AMD")) {
			return "111";
		}
		return null;
	}

	public String comp(String s) {
		// a = 0
		if (s.equals("0")) {
			return "0101010";
		} else if (s.equals("1")) {
			return "0111111";
		} else if (s.equals("-1")) {
			return "0111010";
		} else if (s.equals("D")) {
			return "0001100";
		} else if (s.equals("A")) {
			return "0110000";
		} else if (s.equals("!D")) {
			return "0001101";
		} else if (s.equals("!A")) {
			return "0110001";
		} else if (s.equals("-D")) {
			return "0001111";
		} else if (s.equals("-A")) {
			return "0110011";
		} else if (s.equals("D+1")) {
			return "0011111";
		} else if (s.equals("A+1")) {
			return "0110111";
		} else if (s.equals("D-1")) {
			return "0001110";
		} else if (s.equals("A-1")) {
			return "0110010";
		} else if (s.equals("D+A")) {
			return "0000010";
		} else if (s.equals("D-A")) {
			return "0010011";
		} else if (s.equals("A-D")) {
			return "0000111";
		} else if (s.equals("D&A")) {
			return "0000000";
		} else if (s.equals("D|A")) {
			return "0010101";
		}

		// a = 1
		if (s.equals("M")) {
			return "1110000";
		} else if (s.equals("!M")) {
			return "1110001";
		} else if (s.equals("-M")) {
			return "1110011";
		} else if (s.equals("M+1")) {
			return "1110111";
		} else if (s.equals("M-1")) {
			return "1110010";
		} else if (s.equals("D+M")) {
			return "1000010";
		} else if (s.equals("D-M")) {
			return "1010011";
		} else if (s.equals("M-D")) {
			return "1000111";
		} else if (s.equals("D&M")) {
			return "1000000";
		} else if (s.equals("D|M")) {
			return "1010101";
		}
		return null;
	}

	public String jump(String s) {
		if (s == null) {
			return "000";
		} else if (s.equals("JGT")) {
			return "001";
		} else if (s.equals("JEQ")) {
			return "010";
		} else if (s.equals("JGE")) {
			return "011";
		} else if (s.equals("JLT")) {
			return "100";
		} else if (s.equals("JNE")) {
			return "101";
		} else if (s.equals("JLE")) {
			return "110";
		} else if (s.equals("JMP")) {
			return "111";
		}
		return null;
	}

	public void print(Object o) {
		System.out.print(o.toString());
	}
}
