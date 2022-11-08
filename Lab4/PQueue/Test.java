package PQueue;

public class Test {
	public static void main(String[] args) {
		PQueue<String> pQueue = new PQueue<String>();

		pQueue.push("zebra");
		pQueue.push("alma");

		System.out.println(pQueue.size());
		System.out.println(pQueue.pop());
		System.out.println(pQueue.top());

		pQueue.clear();
		System.out.println(pQueue.size());

		PQueue<Integer> s = new PQueue<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		for (Integer i : s) {
			System.out.println(i);
		}
	}
}
