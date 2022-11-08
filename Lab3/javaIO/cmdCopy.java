package javaIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class cmdCopy {
	public static File wd;
	Scanner scanner = new Scanner(System.in);
	public void run() throws IOException {
		System.out.print(wd.getCanonicalPath()+"> ");
		String[] cmd = (scanner.nextLine()).split(" ");
		switch (cmd[0]) {
			case "exit" -> System.exit(0);
			case "pwd" -> pwd(cmd);
			case "ls" -> ls(cmd);
			case "cd" -> cd(cmd);
			case "rm" -> rm(cmd);
			case "mkdir" -> mkdir(cmd);
			case "cp" -> cp(cmd);
			case "mv" -> mv(cmd);
			case "cat" -> cat(cmd);
			case "length" -> length(cmd);
			case "head" -> head(cmd);
			case "tail" -> tail(cmd);
			case "wc" -> wc(cmd);
			case "grep" -> grep(cmd);
			default -> {}
		}
	}

	protected void pwd(String[] cmd) {
		try {
			System.out.println("\nPath\n-----\n" + wd.getCanonicalPath() + "\n\n");
		} catch (IOException e) {
			System.out.println("Working directory is not found.");
		}
	}

	protected void ls(String[] cmd) {
		try {
			if (cmd.length > 1 && cmd[1].equals("-l")) {
				for (File file : wd.listFiles()) {
					System.out.print(file.getName()+ "\t");
					System.out.print(file.length()/1024 + "KB" + "\t");
					System.out.print(file.isDirectory() ? "d\n" : "f\n");
				}
			} else {
				for (File file: wd.listFiles())
					System.out.println(file.getName());
			}
		} catch (NullPointerException e) {
			System.err.println("Working directory is not found!");
		}
	}

	protected void cd(String[] cmd) {
		try {
			if (cmd[1].equals(".."))
				wd = wd.getParentFile();
			else {
				for (File f : wd.listFiles()) {
					if (f.getName().equals(cmd[1])) {
						wd = new File(wd, cmd[1]);
					} else {
						throw new NullPointerException();
					}
				}
			}

		} catch (NullPointerException e) {
			System.out.println("There is no such directory: " + cmd[1]);
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Missing argument!\n");
			System.out.println("Possible arguments:");
			System.out.println("<directory> - defined directory");
			System.out.println(".. - parent directory");
		}
	}

	protected void rm(String[] cmd) {
		for (int i = 1; i < cmd.length-1; i++) {
			try {
				File file = new File(wd + cmd[i]);
				if (file.delete())
					System.out.println(cmd[i]);
			} catch (NullPointerException e) {
				System.err.println("No file named: " + cmd[i]);
			} catch (SecurityException e) {
				System.err.println("Permission denied!");
			}
		}
	}

	protected void mkdir(String[] cmd) {
		try {
			File file = new File(cmd[1]);
			for (File f : wd.listFiles()) {
				if (f.getName().equals(file.getName())) {
					System.err.println("Directory already exists!");
				} else if (file.mkdir()) {
					System.out.println(cmd[1]);
				}
			}
		} catch (SecurityException e) {
			System.err.println("Permission denied!");
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Missing argument!\n");
			System.out.println("Possible arguments:");
			System.out.println("<directory> - defined directory");
		} catch (NullPointerException e) {
			System.err.println("Working directory is not found!");
		}
	}

	protected void cp(String[] cmd) {
		try {
			FileInputStream fileIn = new FileInputStream(cmd[1]);
			FileOutputStream fileOut = new FileOutputStream(cmd[2]);
			int c;
			while((c=fileIn.read())!=-1)
			{
				fileOut.write(c);
			}
			fileIn.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
		} catch(IOException e) {
			System.err.println(e);
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Missing argument!\n");
			System.out.println("Possible arguments:");
			System.out.println("<file1> <file2> - copy from file1 to file2");
		}
	}

	protected void mv(String[] cmd) {
		try {
			File file1 = new File(cmd[1]);
			File file2 = new File(cmd[2]);
			if (file1.renameTo(file2))
				System.out.println(file2.getName());
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Missing argument!\n");
			System.out.println("Possible arguments:");
			System.out.println("<file1> <file2> - rename file1 to file2");
		} catch (SecurityException e) {
			System.err.println("Permission denied!");
		}
	}

	protected void cat(String[] cmd) {
		try {
			File file = new File(cmd[1]);
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	protected void length(String[] cmd) {
		try {
			File file = new File(cmd[1]);
			System.out.print(file.length()/1024 + "KB");
		} catch (NullPointerException e) {
			System.err.println("File not found!");
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Missing argument!\n");
			System.out.println("Possible arguments:");
			System.out.println("<file1> - get file1's length");
		} catch (SecurityException e) {
			System.err.println("Permission denied!");
		}
	}

	protected void head(String[] cmd) {
		try {
			int n = 10;
			File file;
			if (cmd.length > 2 && cmd[1].equals("-n")) {
				n = Integer.parseInt(cmd[2]);
				file = new File(cmd[3]);
			} else if (cmd.length == 2) {
				file = new File(cmd[1]);
			} else {
				throw new IndexOutOfBoundsException();
			}
			try (Scanner scanner = new Scanner(file)) {
				for (int i = 0; i < n && scanner.hasNextLine(); i++) {
					System.out.println(scanner.nextLine());
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Missing or wrong argument!\n");
			System.out.println("Possible arguments:");
			System.out.println("<file1> - get file1's first 10 lines");
			System.out.println("-n <n> <file1> - get file1's first n lines");
		}
	}

	protected void tail(String[] cmd) {
		try {
			int n = 10;
			File file;
			LinkedList<String> fileString = new LinkedList<>();
			if (cmd.length > 2 && cmd[1].equals("-n")) {
				n = Integer.parseInt(cmd[2]);
				file = new File(cmd[3]);
			} else if (cmd.length == 2) {
				file = new File(cmd[1]);
			} else {
				throw new IndexOutOfBoundsException();
			}
			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNextLine()) {
					fileString.add(scanner.nextLine());
				}
			}
			for (int i = fileString.size() - n; i < fileString.size(); i++) {
				System.out.println(fileString.get(i));
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Missing or wrong argument!\n");
			System.out.println("Possible arguments:");
			System.out.println("<file1> - get file1's last 10 lines");
			System.out.println("-n <n> <file1> - get file1's last n lines");
		}
	}

	protected void wc(String[] cmd) {
		try {
			File file = new File(cmd[1]);
			Scanner scanner = new Scanner(file);
			ArrayList<String> words = new ArrayList<>();
			int line = 0;
			int ch = 0;
			while (scanner.hasNextLine()) {
				line += 1;
				words.addAll(Arrays.asList(scanner.nextLine().split(" ")));
			}
			for (String s: words) {
				ch += s.length();
			}
			System.out.println("Line count: " + line);
			System.out.println("Word count: " + words.size());
			System.out.println("Char count: " + ch);
			// System.out.println(words);
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Missing or wrong argument!\n");
			System.out.println("Possible arguments:");
			System.out.println("<file> - get file's statistics");
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
		}
	}

	protected void grep(String[] cmd) {
		try {
			String pattern = ".*" + cmd[1] + ".*";
			File file = new File(cmd[2]);
			Scanner scanner = new Scanner(file);
			ArrayList<String> lines = new ArrayList<>();
			while (scanner.hasNextLine()) {
				String line;
				if ((line=scanner.nextLine()).matches(pattern)) {
					lines.add(line);
				}
			}
			scanner.close();
			for (String line : lines) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Missing or wrong argument!\n");
			System.out.println("Possible arguments:");
			System.out.println("<pattern> <file> - get file's lines that matches the pattern");
		}
	}
}
