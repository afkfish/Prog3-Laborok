package xmllab.SAX;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class XMLParser {
	public static void use(String[] args) {
		String filename;
		double lat = 0.0, lon = 0.0;

		if (args.length > 1 && args[0].equals("-i")) filename = args[1];
		else throw new NullPointerException("No file specified!");

		if (args.length > 5) {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-lat")) {
					lat = Double.parseDouble(args[i+1]);
				} else if (args[i].equals("-lon")) {
					lon = Double.parseDouble(args[i+1]);
				}
			}
		}

		TagCounter h = new TagCounter(lat, lon);
		SAXParserFactory factory = SAXParserFactory.newInstance();

		try {
			SAXParser p = factory.newSAXParser();
			p.parse(new File(filename), h);
		} catch (Exception e) {
			e.printStackTrace();
		}

		h.printTagCount();
		h.printBusStops();
	}
}
