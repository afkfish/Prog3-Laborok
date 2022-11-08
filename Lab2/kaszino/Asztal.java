package kaszino;

import java.util.ArrayList;
import java.util.Random;

public class Asztal {
	private ArrayList<Jatekos> jatekosok;
	private double tet;
	private int kor;
	private double goal;
	private boolean vege;

	public void ujJatek() {
		jatekosok = new ArrayList<>();
		vege = false;
		kor = 0;
		Random r = new Random();
		goal = r.nextDouble(0, 100);
	}

	public void addJatekos(Jatekos j){
		jatekosok.add(j);
		j.setAsztal(this);
	}

	public int getKor() {
		return kor;
	}

	public void emel(double e) {
		tet += e;
	}

	public double getTet() {
		return tet;
	}

	public void kor() throws NincsJatekos {
		if (jatekosok.isEmpty()){
			throw new NincsJatekos();
		} else {
			if(vege) {
				System.out.println("Vége a játéknak.");
			} else {
				for (int i = 0; i < jatekosok.size() && !vege; i++) {
					jatekosok.get(i).lep();
					System.out.println();
					if (tet / goal < 1.1 && tet / goal > 1) {
						vege = true;
						System.out.println(i+1 + ". jatekos nyert.");
					} else if (tet / goal > 1) {
						vege = true;
						System.out.println("Mindenki vesztett.");
					}
				}
				System.out.println("Tet: " + tet);
			}
			kor += 1;
		}
	}
}
