package PQueue;

public class EmptyQueueException extends ArrayIndexOutOfBoundsException{
	public EmptyQueueException() {
		super("PQueue is empty!");
	}

	public EmptyQueueException(String err) {
		super(err);
	}

	public EmptyQueueException(int i) {
		super(i);
	}
}
