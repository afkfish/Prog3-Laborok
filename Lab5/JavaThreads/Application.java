package JavaThreads;

import java.util.ArrayList;

public class Application {
	public static void main(String[] args) throws InterruptedException {
		Fifo fifo = new Fifo();
		ArrayList<Thread> threads = new ArrayList<>(7);

		for (int i = 0; i < 3; i++) {
			threads.add(new Thread(new Producer(fifo, "producer", (int) (Math.random() * 1000))));
		}
		for (int i = 0; i < 4; i++) {
			threads.add(new Thread(new Consumer(fifo, "consumer", (int) (Math.random() * 1000))));
		}

		for (Thread thread : threads) {
			thread.start();
		}
	}
}
