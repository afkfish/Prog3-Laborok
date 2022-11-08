package javaIO;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		cmdCopy.wd = new File(System.getProperty("user.dir"));
		try {
			cmdCopy cmdCopy = new cmdCopy();
			while (true)
				cmdCopy.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
