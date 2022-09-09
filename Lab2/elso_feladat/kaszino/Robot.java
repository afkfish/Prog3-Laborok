package kaszino;

public class Robot extends Jatekos{
	private static int no;

	private final int id;

	public Robot() {
		id = (no+=1);
	}

	@Override
	public void lep() {
		System.out.println(this);
		System.out.println(asztal.getKor());
	}

	@Override
	public String toString() {
		return "Robot" + id;
	}
}
