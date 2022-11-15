package xmllab.DOM;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class XMLParser {
	private static final Hashtable<String, Integer> tags = new Hashtable<>();
	private static double lat = 0.0, lon = 0.0;

	public static void use(String[] args) {
		String filename;
		String output;
		Document doc;

		if (args.length > 3 && args[0].equals("-i") && args[2].equals("-o")) {
			filename = args[1];
			output = args[3];
		}
		else throw new NullPointerException("No input or output file specified!");

		if (args.length > 7) {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-lat")) {
					lat = Double.parseDouble(args[i+1]);
				} else if (args[i].equals("-lon")) {
					lon = Double.parseDouble(args[i+1]);
				}
			}
		}

		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(new File(filename));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Element root = doc.getRootElement();
		XMLParser.countTags(root);

		for (Map.Entry<String, Integer> tag : tags.entrySet()) {
			System.out.println(tag);
		}

		try {
			filterBusStops(doc);
		} catch (DataConversionException e) {
			e.printStackTrace();
		}


		try (FileOutputStream fos = new FileOutputStream(output)) {
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			outputter.output(doc, fos);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void countTags(Element e) {
		String qName = e.getQualifiedName();

		tags.putIfAbsent(qName, 0);
		tags.put(qName, tags.get(qName)+1);

		for (Element child : e.getChildren()) {
			XMLParser.countTags(child);
		}
	}

	public static void filterBusStops(Document document) throws DataConversionException {
		ArrayList<Element> removeList = new ArrayList<>();

		XMLParser.busStopVerifier(document.getRootElement(), removeList);

		for (Element e : removeList) {
			e.detach();
		}
	}

	public static Boolean busStopVerifier(Element e, ArrayList<Element> removeList) throws DataConversionException {
		for (Element child : e.getChildren()) {
			if (e.getQualifiedName().equals("node") && child.getAttribute("v").getValue().equals("bus_stop")) {
				Element element = new Element("tag");

				element.setAttribute("k", "distance");
				element.setAttribute("v", String.valueOf(dist(lat, lon, e.getAttribute("lat").getDoubleValue(), e.getAttribute("lon").getDoubleValue())));

				e.addContent(element);

				return true;
			} else {
				if (!XMLParser.busStopVerifier(child, removeList)) {
					removeList.add(child);
				}
			}
		}
		return false;
	}

	private static double dist(double lat1, double lon1, double lat2, double lon2) {
		double R = 6371000; // metres
		double phi1 = Math.toRadians(lat1);
		double phi2 = Math.toRadians(lat2);
		double dphi = phi2-phi1;
		double dl = Math.toRadians(lon2-lon1);
		double a = Math.sin(dphi/2) * Math.sin(dphi/2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(dl/2) * Math.sin(dl/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return R * c;
	}
}
