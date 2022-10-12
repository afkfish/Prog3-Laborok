package lambeer;

import java.io.Serializable;

public class Beer implements Comparable<Beer>, Serializable {
	private final String name;
	private final String style;
	private final double strength;

	public Beer(String name, String style, double strength) {
		this.name = name;
		this.style = style;
		this.strength = strength;
	}

	public String getName() {
		return name;
	}

	public String getStyle() {
		return style;
	}

	public double getStrength() {
		return strength;
	}

	@Override
	public String toString() {
		return "Beer{" +
				"name='" + name + '\'' +
				", style='" + style + '\'' +
				", strength=" + strength +
				'}';
	}

	@Override
	public int compareTo(Beer o) {
		return this.name.compareTo(o.getClass().getName());
	}
}
