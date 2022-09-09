package kaszino;

public class Mester extends Jatekos{
	private final int fok;

	public Mester(int fokozat) {
		fok = fokozat;
	}

	@Override
	public void lep() {
		System.out.println("Fokozat: " + fok);
		System.out.println("Kor: " + asztal.getKor());
		double emeles = asztal.getTet() * ((double) fok / 100);
		asztal.emel(emeles);
	}

	@Override
	public String toString() {
		return "Mester" + fok;
	}
}
