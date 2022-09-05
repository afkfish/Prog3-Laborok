package sample.prog;

import sample.echo.*;           // minden osztaly importalasa a csomagbol
import sample.calc.Calculator;  // a Calculator osztaly beimportalasa

public class Program{
	public static void main(String[] args){
		int sum = 0;
		Calculator c = new Calculator();
		for (String arg : args) {
			sum = c.add(sum, Integer.parseInt(arg));
		}
		Echo e = new Echo();
		int sumEcho = e.number(sum);
		System.out.println("sumEcho=" + sumEcho);
	}
}
