package PQueue;

import java.util.Comparator;

public class ReverseComparator<T extends Comparable<? super T>> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return -1 * o1.compareTo(o2);
	}
}
