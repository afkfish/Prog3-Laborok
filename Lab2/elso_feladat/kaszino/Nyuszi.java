package kaszino;

public class Nyuszi extends Jatekos{
	private final String color;

	public Nyuszi(String szin) {
		color = szin;
	}

	@Override
	public void lep() {
		System.out.println("Szin: " + color);
		System.out.println("Kor: " + asztal.getKor());
		if (asztal.getTet() < 50) {
			asztal.emel(5);
		} else {
			System.out.println("Huha!");
		}
	}

	@Override
	public String toString() {
		return color;
	}
}
