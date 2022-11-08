package kaszino;

public abstract class Jatekos {
	protected Asztal asztal;

	public abstract void lep();

	public abstract void setAsztal(Asztal asztal);

	@Override
	protected void finalize() {
		System.out.println("Osztaly azonosito: " + this.hashCode());
		System.out.println(this);
	}
}
