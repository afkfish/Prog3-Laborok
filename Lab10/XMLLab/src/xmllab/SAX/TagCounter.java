package xmllab.SAX;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class TagCounter extends DefaultHandler {
	private final Hashtable<String, Integer> tags = new Hashtable<>();
	private final ArrayList<BusStop> busStops = new ArrayList<>();
	private final double lat, lon;
	
	public TagCounter(Double lat, Double lon) {
		this.lat = lat;
		this.lon = lon;
		
	}
	private BusStop b = new BusStop("", "", "", false, 0.0);

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		tags.putIfAbsent(qName, 0);
		tags.put(qName, tags.get(qName)+1);

		switch (qName) {
			case "node" -> {
				if (!b.valid()) {
					b = new BusStop("", "", "", false, dist(lat, lon, Double.parseDouble(attributes.getValue("lat")), Double.parseDouble(attributes.getValue("lon"))));
				}
			}
			case "tag" -> {
				if (!b.valid() && attributes.getValue("v").equals("bus_stop")) {
					b = new BusStop(b.name(), b.oldName(), b.wheelchair(), true, b.distance());
				} else if (attributes.getValue("k").equals("name")) {
					b = new BusStop(attributes.getValue("v"), b.oldName(), b.wheelchair(), b.valid(), b.distance());
				} else if (attributes.getValue("k").equals("old_name")) {
					b = new BusStop(b.name(), attributes.getValue("v"), b.wheelchair(), b.valid(), b.distance());
				} else if (attributes.getValue("k").equals("wheelchair")) {
					b = new BusStop(b.name(), b.oldName(), attributes.getValue("v"), b.valid(), b.distance());
				}
			}
		}
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("node") && b.valid()) {
			busStops.add(b);
			b  = new BusStop("", "", "", false, 0.0);
		}
		super.endElement(uri, localName, qName);
	}

	public void printTagCount() {
		for (Map.Entry<String, Integer> tag : tags.entrySet()) {
			System.out.println(tag);
		}
	}
	
	public void printBusStops() {
		busStops.sort(Comparator.comparing(BusStop::distance));
		for (BusStop b : busStops) {
			System.out.println("Megálló:");
			System.out.print("\tNév: " + b.name());
			System.out.println(b.oldName().equals("") ? "" : " (" + b.oldName() + ")");
			System.out.println("\tKerekesszék: " + b.wheelchair());
			System.out.println("\tTávolság: " + b.distance() + "\n");
		}
	}

	private double dist(double lat1, double lon1, double lat2, double lon2) { 
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
