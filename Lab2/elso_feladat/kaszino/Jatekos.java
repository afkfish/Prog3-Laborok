package kaszino;

public class Jatekos {
	protected Asztal asztal;

	public void lep() {
		System.out.println("Kor: " + asztal.getKor());
		System.out.println("Tet: " + asztal.getTet());
	}

	public void setAsztal(Asztal asztal) {
		this.asztal = asztal;
	}

	@Override
	protected void finalize() {
		System.out.println(this.hashCode());
		System.out.println(this);
	}
}
