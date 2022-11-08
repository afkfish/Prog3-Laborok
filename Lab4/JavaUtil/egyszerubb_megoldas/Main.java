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

			try {
				switch (command[0]) {
					case "add" -> beers.add(new Beer(command[1], command[2], Double.parseDouble(command[3])));
					case "list" -> beers.list(command.length > 1 ? command[1] : "");
					case "save" -> beers.save(command[1]);
					case "load" -> beers.load(command[1]);
					case "search" -> beers.search(command[1], command.length > 2 ? command[2] : "");
					case "find" -> beers.find(command[1], command.length > 2 ? command[2] : "");
					case "delete" -> beers.delete(command[1]);
					case "exit" -> System.exit(0);
					default -> System.out.println("Command not recognised: " + command[0]);
				}
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Command missing arguments: " + command[0]);
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
			case "name" -> comparator = Comparator.comparing(Beer::name);
			case "style" -> comparator = Comparator.comparing(Beer::style);
			case "strength" -> comparator = Comparator.comparing(Beer::strength);
			default -> comparator = null;
		}
		beerList.sort(comparator);
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
					if (beer.name().matches(comp)) {
						System.out.println(beer);
					}
				}
				case "style" -> {
					if (beer.style().matches(comp)) {
						System.out.println(beer);
					}
				}
				case "strength" -> {
					if (beer.strength() == Double.parseDouble(comp)) {
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
					if (beer.name().contains(comp )) {
						System.out.println(beer);
					}
				}
				case "style" -> {
					if (beer.style().contains(comp)) {
						System.out.println(beer);
					}
				}
				case "strength" -> {
					if (beer.strength() >= Double.parseDouble(comp)) {
						System.out.println(beer);
					}
				}
				case "weaker" -> {
					if (beer.strength() <= Double.parseDouble(comp)) {
						System.out.println(beer);
					}
				}
			}
		}
	}

	protected void delete(String name) {
		try {
			beerList.remove(Collections.binarySearch(beerList, new Beer(name, null, 0), Comparator.comparing(Beer::name)));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("No beer with this name!");
		}
	}
}