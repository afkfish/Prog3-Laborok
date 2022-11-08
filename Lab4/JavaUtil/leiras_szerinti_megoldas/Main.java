package JavaUtil;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Main beers = new Main();

		beers.add(new Beer("Dreher", "lager", 4.5));
		beers.add(new Beer("Heineken", "lager", 4.6));

		System.out.println(beers.beerList);

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String[] command = scanner.nextLine().split(" ");
			System.out.println(command[0] + ", count: " + command.length);

			if (command[0].equals("add") && command.length >= 4) {
				beers.add(new Beer(command[1], command[2], Double.parseDouble(command[3])));

			} else if (command[0].equals("list")) {
				beers.list(command.length > 1 ? command[1] : "");

			} else if (command[0].equals("save") && command.length > 1) {
				beers.save(command[1]);

			} else if (command[0].equals("load") && command.length > 1) {
				beers.load(command[1]);

			} else if (command[0].equals("search") && command.length > 1) {
				if (command.length > 2) {
					beers.search(command[1], command[2]);
				} else {
					beers.search(command[1], "");
				}

			} else if (command[0].equals("find") && command.length > 1) {
				if (command.length > 2) {
					beers.find(command[1], command[2]);
				} else {
					beers.find(command[1], "");
				}

			} else if (command[0].equals("delete") && command.length > 1) {
				beers.delete(command[1]);

			} else if (command[0].equals("exit")) {
				System.exit(0);

			} else {
				System.out.println("Command not recognised or missing arguments: " + command[0]);
			}
		}
	}

	protected ArrayList<Beer> beerList = new ArrayList<>();

	protected void add(Beer beer) {
		beerList.add(beer);
	}

	protected void list(String comp) {
		Comparator<Beer> comparator;
		switch (comp) {
			case "name" -> comparator = new NameComparator();
			case "style" -> comparator = new StyleComparator();
			case "strength" -> comparator = new StrengthComparator();
			default -> comparator = null;
		}
		Collections.sort(beerList, comparator);
		for (Beer beer : beerList) {
			System.out.println(beer);
		}
	}

	protected void save(String file) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(beerList);
		} catch (IOException e) {
			System.err.println("Saving was unsuccesful!");
		}
	}

	protected void load(String file) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			beerList = (ArrayList<Beer>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Loading was unsuccesful!");
		}
	}

	protected void search(String querry, String comp) {
		for (Beer beer : beerList) {
			switch (querry) {
				default ->  {
					if (beer.getName().matches(comp)) {
						System.out.println(beer);
					}
				}
				case "style" -> {
					if (beer.getStyle().matches(comp)) {
						System.out.println(beer);
					}
				}
				case "strength" -> {
					if (beer.getStrength() == Double.parseDouble(comp)) {
						System.out.println(beer);
					}
				}
			}
		}
	}

	protected void find(String querry, String comp) {
		for (Beer beer : beerList) {
			switch (querry) {
				default ->  {
					if (beer.getName().contains(comp)) {
						System.out.println(beer);
					}
				}
				case "style" -> {
					if (beer.getStyle().contains(comp)) {
						System.out.println(beer);
					}
				}
				case "strength" -> {
					if (beer.getStrength() >= Double.parseDouble(comp)) {
						System.out.println(beer);
					}
				}
				case "weaker" -> {
					if (beer.getStrength() <= Double.parseDouble(comp)) {
						System.out.println(beer);
					}
				}
			}
		}
	}

	protected void delete(String name) {
		try {
			beerList.remove(Collections.binarySearch(beerList, new Beer(name, null, 0), new NameComparator()));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("No beer with this name!");
		}
	}
}
