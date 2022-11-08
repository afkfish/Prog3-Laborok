package JavaThreads;

public class Consumer implements Runnable {
	private final Fifo fifo;
	private final String string;
	private final int n;

	public Consumer(Fifo f, String s, int n) {
		this.fifo = f;
		this.string = s;
		this.n = n;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String time = String.valueOf(System.currentTimeMillis());
				System.out.println(string + " consumed " + fifo.get() + " " + time.substring(time.length()-5));
				Thread.sleep(n);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
