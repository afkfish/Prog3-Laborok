import sample.calc.*;

public class Main {
	public static void main(String[] args) {
		int sum = 0;
		Calculator c = new Calculator();
		for (String arg : args) {
			sum = c.add(sum, Integer.parseInt(arg));
		}
		System.out.println("\nSum: " + sum);
	}
}
