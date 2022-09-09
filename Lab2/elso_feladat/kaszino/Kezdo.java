package kaszino;

public class Kezdo extends Jatekos {
	private final String nev;

	public Kezdo(String nev) {
		this.nev = nev;
	}

	@Override
	public void lep() {
		System.out.println(this);
		System.out.println("Kor: " + asztal.getKor());
		if(asztal.getKor() % 2 == 0) {
			asztal.emel(1);
		}
	}

	@Override
	public String toString() {
		return nev;
	}
}
