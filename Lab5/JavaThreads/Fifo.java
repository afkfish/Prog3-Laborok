package JavaThreads;

import java.util.ArrayList;

public class Fifo {
	private final ArrayList<String> arrayList;
	{
		arrayList = new ArrayList<>(10);
	}

	public synchronized void put(String string) throws InterruptedException {
		while (arrayList.size() >= 10)
			this.wait();

		arrayList.add(string);
		System.out.println("put " + Thread.currentThread().getId());

		this.notify();
	}

	public synchronized String get() throws InterruptedException {
		while (arrayList.size() == 0)
			this.wait();

		String temp = arrayList.get(0);
		arrayList.remove(0);
		System.out.println("get " + Thread.currentThread().getId());

		this.notify();

		return temp;
	}
}
