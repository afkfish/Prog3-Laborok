package Caesar;

public class CaesarCode {

	public static String caesarCode(String input, char offset) {
		assert offset >= 'A' && offset <= 'Z';
		String inp = input.toUpperCase();
		char off = Character.toUpperCase(offset);
		StringBuilder tmp = new StringBuilder();

		for (int i = 0; i < inp.length(); i++) {
			if (inp.charAt(i) >= 'A' && inp.charAt(i) <= 'Z') {
				tmp.append((char)((inp.charAt(i) - 'A' + off - 'A') % 26 + 'A'));
			} else {
				tmp.append(inp.charAt(i));
			}
		}
		return tmp.toString();
	}

	public static String caesarDecode(String input, char offset) {
		assert offset >= 'A' && offset <= 'Z';
		String inp = input.toUpperCase();
		char off = Character.toUpperCase(offset);
		StringBuilder tmp = new StringBuilder();

		for (int i = 0; i < inp.length(); i++) {
			if (inp.charAt(i) >= 'A' && inp.charAt(i) <= 'Z') {
				int c = inp.charAt(i) - (off - 'A');
				if (c < 'A') tmp.append((char)(inp.charAt(i) + (26-(off-'A'))));
				else tmp.append((char)(inp.charAt(i) - (off - 'A')));
			} else {
				tmp.append(inp.charAt(i));
			}
		}
		return tmp.toString();
	}
}
