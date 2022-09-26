package patternFilter;

import java.io.*;
import java.util.Scanner;

public class Filter {
	public static String pattern;

	public static void main(String[] args) {
		pattern = null;
		File in = null;
		File out = null;
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case "-p" -> pattern = ".*" + args[i+1] + ".*";
				case "-i" -> in = new File(args[i+1]);
				case "-o" -> out = new File(args[i+1]);
				default -> {}
			}
		}
		try {
			if (pattern != null) {
				assert in != null : "Input file missing!";
				assert out != null : "Output file missing!";
				Scanner scanner = new Scanner(in);
				PrintWriter printWriter = new PrintWriter(out);

				while (scanner.hasNextLine()) {
					String line;
					if ((line = scanner.nextLine()).matches(pattern)) {
						printWriter.println(line);
					}
				}

				printWriter.flush();
				printWriter.close();
				scanner.close();

			} else {
				Scanner scanner = new Scanner(System.in);
				System.out.println(scanner.nextLine());

			}
		} catch (FileNotFoundException | SecurityException e) {
			System.err.println("File not found or access denied!");
		}
	}
}
