package PQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class PQueue<T extends Comparable<? super T>> implements Iterable<T>{
	private final ArrayList<T> arrayList;

	public PQueue() {
		arrayList = new ArrayList<>();
	}

	public void push(T t) {
		arrayList.add(t);
	}

	public T pop() {
		T t = top();
		arrayList.remove(t);
		return t;
	}

	public T top() {
		if (arrayList.size() == 0) {
			throw new EmptyQueueException();
		}
		return Collections.max(arrayList);
	}

	public int size() {
		return arrayList.size();
	}

	public void clear() {
		arrayList.clear();
	}

	@Override
	public Iterator<T> iterator() {
		Collections.sort(arrayList, new ReverseComparator<T>());
		return arrayList.iterator();
	}
}
