package kaszino;

public class Main {
	public static void main(String[] args) {
		Asztal asztal = new Asztal();
		asztal.ujJatek();
		asztal.addJatekos(new Nyuszi("red"));
		asztal.addJatekos(new Mester(3));
		asztal.addJatekos(new Ember());

		try {
			for (int i = 0; i < 10; i++) {
				asztal.kor();
				System.out.println();
			}
		} catch (NincsJatekos nincsJatekos) {
			System.out.println(nincsJatekos.reason());
		}

		asztal = null;
		System.gc();

	}
}
