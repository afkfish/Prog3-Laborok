package lambeer;

import java.io.*;
import java.util.*;

public class Main {
	protected static ArrayList<Beer> beerList;
	static Map<String, Comparator<Beer>> comps;
	protected static List<String> lparams;
	static {
		beerList = new ArrayList<>();
		comps = new HashMap<>();
		lparams = new LinkedList<>();

		comps.put("name", Comparator.comparing(Beer::getName));
		comps.put("style", Comparator.comparing(Beer::getStyle));
		comps.put("strength", Comparator.comparing(Beer::getStrength));

		lparams.addAll(comps.keySet());
	}

	public static void main(String[] args) {

		Main.add("add Dreher lager 4.5".split(" "));
		Main.add("add Heineken lager 4.6".split(" "));

		System.out.println(Main.beerList);

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String[] command = scanner.nextLine().split(" ");
			System.out.println(command[0] + ", count: " + command.length);

			HashMap<String, Command> hashMap = new HashMap<>();

			hashMap.put("add", Main::add);
			hashMap.put("list", Main::list);
			hashMap.put("save", Main::save);
			hashMap.put("load", Main::load);
			hashMap.put("search", Main::search);
			hashMap.put("find", Main::find);
			hashMap.put("delete", Main::delete);
			hashMap.put("exit", (String[] tmp) -> System.exit(0));

			Command tmp;
			if ((tmp = hashMap.get(command[0])) != null)
				tmp.execute(command);
			else System.err.println("Command not recognised: " + command[0]);
		}
	}

	protected static void add(String[] cmd) {
		beerList.add(new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3])));
	}

	protected static void list(String[] cmd) {
		if (cmd.length > 1) {
			lparams.remove(cmd[1]);
			lparams.add(0, cmd[1]);
		}
		Comparator<Beer>
				c1 = comps.get(lparams.get(0)),
				c2 = comps.get(lparams.get(1)),
				c3 = comps.get(lparams.get(2));

		beerList.sort(c1.thenComparing(c2.thenComparing(c3)));

		for (Beer beer : beerList) {
			System.out.println(beer);
		}
	}

	protected static void save(String[] cmd) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cmd[1]))) {
			oos.writeObject(beerList);
		} catch (IOException e) {
			System.err.println("Saving was unsuccesful!");
		}
	}

	protected static void load(String[] cmd) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cmd[1]))) {
			beerList = (ArrayList<Beer>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Loading was unsuccesful!");
		}
	}

	protected static void search(String[] cmd) {
		String querry, comp = "";
		if (cmd.length > 2) {
			querry = cmd[1];
			comp = cmd[2];
		} else {
			querry = cmd[1];
		}
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

	protected static void find(String[] cmd) {
		String querry, comp = "";
		if (cmd.length > 2) {
			querry = cmd[1];
			comp = cmd[2];
		} else {
			querry = cmd[1];
		}
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

	protected static void delete(String[] cmd) {
		String name = cmd[1];
		try {
			beerList.remove(Collections.binarySearch(beerList, new Beer(name, null, 0), Comparator.comparing(Beer::getName)));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("No beer with this name!");
		}
	}
}
