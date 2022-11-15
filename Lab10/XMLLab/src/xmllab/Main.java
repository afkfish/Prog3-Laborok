package xmllab;

public class Main {
	private static final int mode = 1;

	public static void main(String[] args) {
		if (mode == 0) {
			xmllab.SAX.XMLParser.use(args);
		} else if (mode == 1) {
			xmllab.DOM.XMLParser.use(args);
		}
	}
}
