package JavaThreads;

public class Producer implements Runnable {
	private final String string;
	private final Fifo fifo;
	private final int sleepTime;
	private int iteration = 0;

	public Producer(Fifo f, String s, int sleepTime) {
		this.string = s;
		this.fifo = f;
		this.sleepTime = sleepTime;
	}

	public void go() throws InterruptedException {
		while(true) {
			++iteration;
			fifo.put(string + " " + iteration);

			String time = String.valueOf(System.currentTimeMillis());
			System.out.println(string + " produced " + iteration + " " + time.substring(time.length()-5));

			Thread.sleep(sleepTime);
		}
	}

	@Override
	public void run() {
		try {
			this.go();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
