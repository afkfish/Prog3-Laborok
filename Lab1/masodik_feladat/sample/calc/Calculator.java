package sample.calc;

public class Calculator{
	private int sum;
	private void store(int a) {
		sum += a;
		System.out.println(sum);
	}

	public int add(int a, int b) {
		store(a);
		return a+b;
	}
}