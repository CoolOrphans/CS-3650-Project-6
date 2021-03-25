
public class SymbolTable {
	Object[][] Symbols;
	public int lastIndex = 0; // good so don't have to search table again if just did contain method

	public SymbolTable() {
		Symbols = new Object[23][2]; // 8 for predefined symbols
		Symbols[0] = new Object[] { "SP", 0 };
		Symbols[1] = new Object[] { "LCL", 1 };
		Symbols[2] = new Object[] { "ARG", 2 };
		Symbols[3] = new Object[] { "THIS", 3 };
		Symbols[4] = new Object[] { "THAT", 4 };
		Symbols[5] = new Object[] { "SCREEN", 16384 };
		Symbols[6] = new Object[] { "KBD", 24576 };

		for (int i = 0; i <= 15; i++) { // R0-R15
			Symbols[i + 7] = new Object[] { ("R" + i), i };
		}

	}

	public void addEntry(String symbol, int address) {
		// increase size of Symbols Array
		Object[][] temp = new Object[Symbols.length + 1][2];
		for (int i = 0; i < Symbols.length; i++) {
			temp[i] = Symbols[i];
		}
		temp[temp.length - 1] = new Object[] { symbol, address };
		Symbols = temp;
	}

	public boolean contains(String symbol) {
		for (int i = 0; i < Symbols.length; i++) {
			if (Symbols[i][0].equals(symbol)) {
				lastIndex = i;
				return true;
			}
		}
		return false;
	}

	public int getAddress(String symbol) {
		if (Symbols[lastIndex][0].equals(symbol)) { // if contains method was just used
			return (int) Symbols[lastIndex][1];
		}
		for (int i = 0; i < Symbols.length; i++) {
			if (Symbols[i][0].equals(symbol)) {
				return (int) Symbols[i][1];
			}
		}
		return -1;
	}
}
