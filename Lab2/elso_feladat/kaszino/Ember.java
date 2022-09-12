package kaszino;

import java.util.Scanner;

public class Ember extends Jatekos{

	@Override
	public void setAsztal(Asztal asztal) {
		this.asztal = asztal;
	}

	@Override
	public void lep() {
		System.out.println("Aktualis tet: " + asztal.getTet());
		Scanner scanner = new Scanner(System.in);
		System.out.print("Emelesi osszeg: ");
		asztal.emel(Double.parseDouble(scanner.nextLine()));
	}
}
